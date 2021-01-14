/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Models.Student;
import Helpers.DatabaseConnector;
import Helpers.SessionController;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author boody
 */
@WebServlet(name = "StudentController", urlPatterns = {"/StudentController"})
public class StudentController extends HttpServlet {

    public Student getStudentById(HttpServletRequest request) {
        try {
        
            PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM student WHERE student_id=?");
            statement.setString(1, request.getParameter("student_id"));
            ResultSet rs = statement.executeQuery();
            Student student = new Student();
            while (rs.next()) {
                student.user_name = rs.getString("user_name");
                student.mail = rs.getString("mail");
                student.gender = rs.getString("gender");
                student.country = rs.getString("country");
                student.student_level = rs.getInt("student_level");
                student.student_gpa = rs.getFloat("student_gpa");
            }
            return student;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
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

            if (request.getParameter("student_id") != null) {
                Student student = new Student();
                student = getStudentById(request);

                 request.setAttribute("studentInforamtion", student);
                 request.getRequestDispatcher("student-info.jsp").forward(request, response);

            }
            else
            {
                  
             request.getRequestDispatcher("student-info.jsp").forward(request, response);

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
