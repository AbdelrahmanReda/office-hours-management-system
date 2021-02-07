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
@WebServlet(urlPatterns = {"/changeInfo"})
public class changeInfo extends HttpServlet {

    private boolean hasRows(PreparedStatement statement) throws SQLException {
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            return true;
        }
        return false;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            if (request.getParameter("operation") != null) {
                HttpSession session = request.getSession();
                String username = (String) session.getAttribute("username");
/*
                String userType = (String) session.getAttribute("user_type");

                if (userType.equals("staff")) {
                    PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM staff WHERE  mail=? ");
                    statement.setString(1, request.getParameter("mail"));
                    if (hasRows(statement)) {
                        session.setAttribute("invalid_data", "true");
                        request.getRequestDispatcher("changeInfo.jsp").forward(request, response);
                        return;
                    }
                } else {
                    PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM studet WHERE  mail=? ");
                    statement.setString(1, request.getParameter("mail"));
                    if (hasRows(statement)) {
                        session.setAttribute("invalid_data", "true");
                        request.getRequestDispatcher("changeInfo.jsp").forward(request, response);
                        return;
                    }
                }
                */

                try {

                    if (!request.getParameter("mail").equals("")) {
                        PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("UPDATE student set student.mail =?  WHERE user_name=? ;");
                        stmt.setString(1, request.getParameter("mail"));
                        stmt.setString(2, username);
                        stmt.executeUpdate();

                    }
                    if (!request.getParameter("password").equals("")) {

                        PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("UPDATE student set student.password =?   WHERE user_name=? ;");
                        stmt.setString(1, request.getParameter("password"));
                        stmt.setString(2, username);
                        stmt.executeUpdate();

                    }
                    if (!request.getParameter("gender").equals("")) {

                        PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("UPDATE student set student.gender =?   WHERE user_name=? ;");
                        stmt.setString(1, request.getParameter("gender"));
                        stmt.setString(2, username);
                        stmt.executeUpdate();

                    }
                    if (!request.getParameter("country").equals("")) {

                        PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("UPDATE student set student.country =?  WHERE user_name=? ;");
                        stmt.setString(1, request.getParameter("country"));
                        stmt.setString(2, username);
                        stmt.executeUpdate();
                    }
                    session.setAttribute("update_status", "true");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }

            request.getRequestDispatcher("changeInfo.jsp").forward(request, response);

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
            Logger.getLogger(changeInfo.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(changeInfo.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(changeInfo.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(changeInfo.class
                    .getName()).log(Level.SEVERE, null, ex);
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
