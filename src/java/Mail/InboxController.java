/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mail;

import Helpers.DatabaseConnector;
import Helpers.SessionController;
import Models.Message;
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

    private ArrayList<UserMessage> getConverations(HttpServletRequest request) {

        try {
            PreparedStatement stm = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM conversation\n"
                    + "    INNER JOIN user_message  on conversation.id = conversation_id\n"
                    + "    INNER JOIN message  on user_message.message_id = message.id\n"
                    + "    WHERE sender_id=? OR recipient_id = ? ORDER BY created_at DESC LIMIT 1;");

            stm.setString(1, SessionController.getSessionAtrributeValue(request, "email"));
            stm.setString(2, SessionController.getSessionAtrributeValue(request, "email"));
            
            ResultSet rs = stm.executeQuery();
            /*
            
            ...to be continued
            */
            ArrayList<UserMessage> messages = new ArrayList<>();
            while (rs.next()) {
                UserMessage obj = new UserMessage();
                obj.conversation.subject=rs.getString("subject");
                obj.message.messageBoody=rs.getString("message");
                obj.message.create_at=rs.getTimestamp("created_at");
                System.out.println("subject is"+obj.conversation.subject);
                System.out.println("messeage is is"+obj.message.messageBoody);
                System.out.println("messeage sent at"+ obj.message.create_at);
                messages.add(obj);
                
                
            }
            return messages;

        } catch (SQLException ex) {
            Logger.getLogger(InboxController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InboxController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           
            request.setAttribute("conversations",  getConverations(request));
            request.getRequestDispatcher("inbox.jsp").forward(request, response);
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
        
        System.out.println(">>>>>>>>>>><><>"+request.getParameter("todo"));
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
