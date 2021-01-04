/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staff;

import Helpers.DatabaseConnector;
import Helpers.SessionController;
import Models.Appointment;
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
@WebServlet(name = "AppointmentController", urlPatterns = {"/AppointmentController"})
public class AppointmentController extends HttpServlet {

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

            ArrayList<Appointment> appointments = new ArrayList<>();

            try {

                PreparedStatement stm = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM appointment\n"
                        + "INNER JOIN office_hours on appointment.office_hour_id = office_hours.id\n"
                        + "INNER JOIN staff  on appointment.staff_id = staff.id\n"
                        + "INNER JOIN student on appointment.student_id = student.id WHERE appointment.staff_id=?");
                stm.setInt(1, Integer.parseInt(SessionController.getSessionAtrributeValue(request, "id").toString()));
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Appointment appointment = new Appointment();

                    appointment.student.user_name = rs.getString("student.user_name");
                    appointment.student.mail = rs.getString("student.mail");
                    appointment.student.student_level = rs.getInt("student_level");
                    appointment.officeHours.day = rs.getString("day");
                    appointment.officeHours.time = rs.getString("time");

                    appointments.add(appointment);

                }

                request.setAttribute("appointments", appointments);
                request.getRequestDispatcher("reserved_office_hours.jsp").forward(request, response);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
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
