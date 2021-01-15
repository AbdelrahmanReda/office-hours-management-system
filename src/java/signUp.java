/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Helpers.DatabaseConnector;
import Mail.MailConfiguration;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
 * @author toqa khaled
 */
@WebServlet(urlPatterns = {"/signUp"})
public class signUp extends HttpServlet {
    private boolean hasRows(PreparedStatement statement) throws SQLException{
    ResultSet rs = statement.executeQuery();
        while (rs.next()) {        
            return true;  
        }
    return false;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            System.out.println("reqest has " + request);
            String password = MailConfiguration.generatePassword(8).toString();
            if (request.getParameter("user_type").equals("staff")) {
                 PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM staff WHERE  mail=? OR mail=?;");
                statement.setString(1, request.getParameter("email"));
                statement.setString(2, request.getParameter("user_name"));
                if (hasRows(statement)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("invalid_data", "true");
                    request.getRequestDispatcher("signup.jsp").forward(request, response);
                    return;  
                }
                MailConfiguration.SendEmail(password, request.getParameter("email"));
                statement = DatabaseConnector.getConnection().prepareStatement("INSERT INTO staff VALUES (DEFAULT,?,?,?,?,?,?,?,?,?);");
                statement.setString(1, request.getParameter("user_name"));
                statement.setString(2, request.getParameter("first_name"));
                statement.setString(3, request.getParameter("last_name"));
                statement.setString(4, request.getParameter("email"));
                statement.setString(5, password);
                statement.setString(6, request.getParameter("mail_password"));
                statement.setString(7, request.getParameter("gender"));
                statement.setString(8, request.getParameter("country"));
                statement.setString(9, "IS");
                statement.executeUpdate();
                request.getRequestDispatcher("staffLogin.jsp").forward(request, response);
            } else {
     
                PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM student WHERE  mail=? OR mail=?;");
                statement.setString(1, request.getParameter("email"));
                statement.setString(2, request.getParameter("user_name"));
                if (hasRows(statement)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("invalid_data", "true");
                    request.getRequestDispatcher("signup.jsp").forward(request, response);
                    return;
                    
                }
                MailConfiguration.SendEmail(password, request.getParameter("email"));
                statement = DatabaseConnector.getConnection().prepareStatement("INSERT INTO student VALUES (DEFAULT,?,?,?,?,?,?,?,?,?,?);");
                statement.setString(1, request.getParameter("user_name"));
                statement.setString(2, request.getParameter("first_name"));
                statement.setString(3, request.getParameter("last_name"));
                statement.setString(4, request.getParameter("email"));
                statement.setString(5, password);
                statement.setString(6, request.getParameter("mail_password"));
                statement.setString(7, request.getParameter("gender"));
                statement.setString(8, request.getParameter("country"));
                statement.setInt(9, 3);
                statement.setInt(10, 3);
                statement.executeUpdate();
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

            return;
        }
        finally{
        
        
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(signUp.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(signUp.class.getName()).log(Level.SEVERE, null, ex);
        }
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
