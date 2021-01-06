/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 *
 * @author toqa khaled
 */
@WebServlet(urlPatterns = {"/signUp"})
public class signUp extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String userName = request.getParameter("username");
            String email = request.getParameter("FromEmailAddress");
            String gender = request.getParameter("gender");
            String country = request.getParameter("Country");

            char[] password = MailConfiguration.generatePassword(10);
            String pass = String.valueOf(password);
            MailConfiguration.SendEmail(pass,email);
            
            String url = "jdbc:mysql://localhost:3306/office_hours";
            Connection con = null;
            Statement Stmt = null;
            ResultSet RS = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, "root", "1234");
                Stmt = (Statement) con.createStatement();
                
               Stmt.executeUpdate("INSERT INTO student VALUES("
                        + "'" + userName + "',"
                        + "'" + email + "',"
                        + "'" + pass+ "',"
                        + "'" + gender+ "',"
                        + "'" + "student"+ "',"
                        + "'" + country + "')");
       
                
            } catch (SQLException ex) {
                ex.printStackTrace();
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
