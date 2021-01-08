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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void getMail(HttpServletRequest request) {
        String email = SessionController.getSessionAtrributeValue(request, "email");

    }

    private void storeMessage(String message, String subject, String recipients, HttpServletRequest request) {

        try {
            int generatedConversationId = -1;
            int generatedMessageId=-1;
            PreparedStatement stm = DatabaseConnector.getConnection().prepareStatement("INSERT INTO conversation VALUES (DEFAULT,?)", Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, subject);
            stm.executeUpdate();
            

            try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                   generatedConversationId = (int)generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            

            
            stm = DatabaseConnector.getConnection().prepareStatement("INSERT INTO message VALUES (DEFAULT,?,DEFAULT)", Statement.RETURN_GENERATED_KEYS);
    
            stm.setString(1, message);

            stm.executeUpdate();

           
            try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                   generatedMessageId = (int)generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            

            stm = DatabaseConnector.getConnection().prepareStatement("INSERT INTO  user_message VALUES  (DEFAULT,?,?,?,?)");
            stm.setInt(1, generatedMessageId);
            stm.setInt(2, generatedConversationId);
            stm.setString(3, SessionController.getSessionAtrributeValue(request, "email"));
            stm.setString(4, recipients);
            stm.executeUpdate();
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            System.out.println("sau something great");
            if (request.getParameter("recipients") != null) {

                String message = request.getParameter("message");
                String subject = request.getParameter("subject");
                String recipients = request.getParameter("recipients");

                System.out.println("message " + message);
                System.out.println("subject " + subject);
                System.out.println("recipients " + recipients);

                String from = "boodycat009@gmail.com";
                String pass = "2266554488";
                MailConfiguration.SendEmailToStaff(from, recipients, subject, message, pass);

                request.setAttribute("status", "success");
                request.getRequestDispatcher("compose_mail.jsp").forward(request, response);
                storeMessage(message, subject, recipients, request);
                System.out.println("called success");

            }

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

        request.getRequestDispatcher("compose_mail.jsp").forward(request, response);
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
