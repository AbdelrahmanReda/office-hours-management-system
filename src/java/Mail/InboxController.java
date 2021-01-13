/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mail;

import Helpers.DatabaseConnector;
import Helpers.SessionController;
import Models.UserMessage;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author boody
 */
@WebServlet(name = "InboxController", urlPatterns = {"/InboxController/*"})
public class InboxController extends HttpServlet {

    private ArrayList<UserMessage> showMesseages(int conversationId) throws SQLException {
        ArrayList<UserMessage> messages = new ArrayList<>();
        try {

            PreparedStatement stm = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM  message\n"
                    + "    INNER JOIN user_message on message.id = message_id\n"
                    + "    INNER JOIN conversation on user_message.conversation_id = conversation.id\n"
                    + "    WHERE conversation_id = ? GROUP BY message_id ASC ;");
            stm.setInt(1, conversationId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                UserMessage obj = new UserMessage();
                obj.conversation.id = rs.getInt("conversation.id");
                obj.message.messageBoody = rs.getString("message");
                obj.message.create_at = rs.getTimestamp("created_at");
                obj.sender = rs.getNString("sender_id");
                obj.conversation.subject = rs.getString("subject");
                messages.add(obj);
            }
            return messages;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InboxController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    private ArrayList<UserMessage> getConverations(HttpServletRequest request) {
        try {
            ArrayList<UserMessage> messages = new ArrayList<>();

            /**
             * Get conversation id and conversation subject
             */
            PreparedStatement stm = DatabaseConnector.getConnection().prepareStatement("SELECT conversation_id ,subject ,  MAX(created_at) as 'created_at' FROM conversation\n"
                    + "    INNER JOIN user_message  on conversation.id = conversation_id\n"
                    + "    INNER JOIN message  on user_message.message_id = message.id\n"
                    + "     WHERE sender_id=? OR recipient_id = ? GROUP BY conversation_id , subject  ORDER BY created_at DESC");

            stm.setString(1, SessionController.getSessionAtrributeValue(request, "email"));
            stm.setString(2, SessionController.getSessionAtrributeValue(request, "email"));
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                UserMessage obj = new UserMessage();

                obj.conversation.id = rs.getInt("conversation_id");
                obj.conversation.subject = rs.getString("subject");
                messages.add(obj);

            }
            System.out.println("numbere of messages is " + messages.size());
            for (int i = 0; i < messages.size(); i++) {

                stm = DatabaseConnector.getConnection().prepareStatement("SELECT message.message , message.created_at , user_message.sender_id FROM  message\n"
                        + "    INNER JOIN user_message on message.id = message_id\n"
                        + "    INNER JOIN conversation on user_message.conversation_id = conversation.id\n"
                        + "    WHERE conversation_id = ? ORDER BY created_at DESC LIMIT 1");

                stm.setInt(1, messages.get(i).conversation.id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    messages.get(i).message.messageBoody = rs.getString("message");
                    messages.get(i).message.create_at = rs.getTimestamp("created_at");
                    messages.get(i).sender = rs.getString("sender_id");
                }

            }

            return messages;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(InboxController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            if (SessionController.getSessionAtrributeValue(request, "user_type") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            /* TODO output your page here. You may use following sample code. */
            if (request.getParameter("conversation_id") != null) {

                request.setAttribute("conversation", showMesseages(Integer.parseInt(request.getParameter("conversation_id"))));
                request.getRequestDispatcher("coversation.jsp").forward(request, response);
            } else {
                request.setAttribute("conversations", getConverations(request));
                request.getRequestDispatcher("inbox.jsp").forward(request, response);

            }

        } catch (SQLException ex) {
            Logger.getLogger(InboxController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
