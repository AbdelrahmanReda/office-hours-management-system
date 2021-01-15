/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staff;

import Helpers.DatabaseConnector;
import Helpers.SessionController;
import Models.Staff;

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
@WebServlet(name = "StaffController", urlPatterns = {"/StaffController"})

public class StaffController extends HttpServlet {

    private ArrayList<Staff> getAllStafMemebrs() {
        try {
            ArrayList<Staff> staffMembers = new ArrayList<>();
            PreparedStatement stm = DatabaseConnector.getConnection().prepareStatement("SELECT * from  staff");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Staff obj = new Staff();
                obj.id = rs.getInt("id");
                obj.user_name = rs.getString("user_name");
                obj.first_name=rs.getString("first_name");
                obj.last_name=rs.getString("last_name");
                obj.mail = rs.getString("mail");
                obj.dapartment = rs.getString("dapartment");
                staffMembers.add(obj);
            }
            return staffMembers;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private ArrayList<Staff> getStaffMembers(HttpServletRequest request) {
        try {
            ArrayList<Staff> staffMembers = new ArrayList<>();
            String course = request.getParameter("course");
            PreparedStatement stm = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM  course\n"
                    + "INNER  JOIN teach  on course.id = teach.course_id\n"
                    + "INNER JOIN staff  on teach.staff_id = staff.id where course_code = ? OR course_name = ?;");
            stm.setString(1, course);
            stm.setString(2, course);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Staff obj = new Staff();
                obj.user_name = rs.getString("user_name");
                obj.mail = rs.getString("mail");
                obj.dapartment = rs.getString("dapartment");
                staffMembers.add(obj);
            }
            return staffMembers;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
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

            if (SessionController.getSessionAtrributeValue(request, "user_type") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            if (request.getParameter("getStaffMembers") != null) {

                request.setAttribute("staffMembers", getAllStafMemebrs());
                request.getRequestDispatcher("staff_members.jsp").forward(request, response);
                return;

            }

            if (request.getParameter("course") != null) {

                request.setAttribute("staffMembers", getStaffMembers(request));
            }

            request.getRequestDispatcher("staffCourse.jsp").forward(request, response);

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
