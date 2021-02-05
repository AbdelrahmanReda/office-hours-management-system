/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mail;

import Helpers.DatabaseConnector;
import Helpers.SessionController;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author boody
 */
@WebServlet(name = "MailController", urlPatterns = {"/MailController"})

public class MailController extends HttpServlet {

    public static void hello() {
        System.out.println("Hello wordl fom1");

    }

    private void getMail(HttpServletRequest request) {
        String email = SessionController.getSessionAtrributeValue(request, "email");

    }

    private Set<String> getRecipents(HttpServletRequest request) throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DatabaseConnector.getConnection().prepareStatement("SELECT sender_id , recipient_id FROM user_message\n"
                + "WHERE   user_message.conversation_id=?");
        stm.setInt(1, Integer.parseInt(request.getParameter("conversation_id")));
        ResultSet rs = stm.executeQuery();
        Set<String> recipentes = new HashSet<String>();
        while (rs.next()) {
            recipentes.add(rs.getString("sender_id"));
            recipentes.add(rs.getString("recipient_id"));
        }
        recipentes.remove(SessionController.getSessionAtrributeValue(request, "email"));
        return recipentes;
    }
    private String  getConversationSubjectById(HttpServletRequest request) throws ClassNotFoundException, SQLException{
        
        PreparedStatement stm = DatabaseConnector.getConnection().prepareStatement("SELECT  subject FROM conversation WHERE id=?;");
        stm.setInt(1, Integer.parseInt(request.getParameter("conversation_id")));
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {            
            return rs.getString("subject");
        }
        return null;
    }
    private void storeUserMessageReply(HttpServletRequest request) throws ClassNotFoundException, SQLException {
        System.out.println("request has now ya reda "+request );
        Set<String> recipents = getRecipents(request);
        String subject = getConversationSubjectById(request);
        int messageId = storeMessage(request.getParameter("message_body"));
        String from = SessionController.getSessionAtrributeValue(request, "email");
        String pass = SessionController.getSessionAtrributeValue(request, "mail_password");
        for (String recipent : recipents) {
            PreparedStatement stm = DatabaseConnector.getConnection().prepareStatement("INSERT INTO  user_message VALUES  (DEFAULT,?,?,?,?)");
            stm.setInt(1, messageId);
            stm.setInt(2, Integer.parseInt(request.getParameter("conversation_id")));
            stm.setString(3, SessionController.getSessionAtrributeValue(request, "email"));
            stm.setString(4, recipent);
            stm.executeUpdate();
            MailConfiguration.SendEmailToStaff(from, recipent, subject, request.getParameter("message_body"), pass);
        }

    }

    private int storeMessage(String message) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DatabaseConnector.getConnection().prepareStatement("INSERT INTO message VALUES (DEFAULT,?,DEFAULT)", Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, message);
        stm.executeUpdate();

        try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return (int) generatedKeys.getLong(1);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
    }

    private int StoreConversation(String subject) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DatabaseConnector.getConnection().prepareStatement("INSERT INTO conversation VALUES (DEFAULT,?)", Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, subject);
        stm.executeUpdate();

        try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return (int) generatedKeys.getLong(1);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
    }

    private void storeUserMessage(String message, String subject, String recipients, HttpServletRequest request) {
        try {
            int generatedMessageId = storeMessage(message);
            int generatedConversationId = StoreConversation(subject);
            String[] recipientsArr = recipients.split(";");
            for (String rec : recipientsArr) {
                PreparedStatement stm = DatabaseConnector.getConnection().prepareStatement("INSERT INTO  user_message VALUES  (DEFAULT,?,?,?,?)");
                stm.setInt(1, generatedMessageId);
                stm.setInt(2, generatedConversationId);
                stm.setString(3, SessionController.getSessionAtrributeValue(request, "email"));
                stm.setString(4, rec);
                stm.executeUpdate();
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            if (SessionController.getSessionAtrributeValue(request, "user_type") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            if (request.getParameter("operation") != null) {
                if (request.getParameter("operation").equals("reply")) {
                    System.out.println("request has" + request);
                    storeUserMessageReply(request);
                    request.getRequestDispatcher("InboxController").forward(request, response);
                    return;
                }
            }

            if (request.getParameter("recipients") != null) {
                System.out.println("request has "+request);
                
                System.out.println("i am trying to send tranditional mai;");
                String message = request.getParameter("message");
                String subject = request.getParameter("subject");
                String recipients = request.getParameter("recipients");
                System.out.println("message " + message);
                System.out.println("subject " + subject);
                System.out.println("recipients " + recipients);
                String from = SessionController.getSessionAtrributeValue(request, "email");
                String pass = SessionController.getSessionAtrributeValue(request, "mail_password");

                MailConfiguration.SendEmailToStaff(from, recipients, subject, message, pass);
                request.setAttribute("status", "success");
                request.getRequestDispatcher("compose_mail.jsp").forward(request, response);
                storeUserMessage(message, subject, recipients, request);
                System.out.println("called success");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("compose_mail.jsp").forward(request, response);
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
