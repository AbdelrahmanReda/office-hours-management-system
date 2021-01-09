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

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
  

    public void delteAppointment(HttpServletRequest request) {
        if (isNumeric(request.getParameter("reservation_id"))) {
            try {
                System.out.println("it is number");
                Integer id = Integer.parseInt(request.getParameter("reservation_id"));
                PreparedStatement stm = DatabaseConnector.getConnection().prepareStatement("DELETE FROM appointment where id  =  ?");
                stm.setInt(1, id);
                stm.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("it is a day");
            String day = request.getParameter("reservation_id");
            try {
                PreparedStatement stm = DatabaseConnector.getConnection().prepareStatement("DELETE  FROM  appointment WHERE id IN (\n"
                        + "SELECT appointment.id FROM appointment\n"
                        + "INNER JOIN office_hours ON appointment.office_hour_id = office_hours.id\n"
                        + "INNER JOIN staff  ON appointment.staff_id = staff.id\n"
                        + "INNER JOIN slot ON office_hours.slot = slot.id\n"
                        + "INNER JOIN student ON appointment.student_id = student.id WHERE office_hours.day=?)");
                stm.setString(1, day);
                stm.executeUpdate();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
            if (request.getParameter("operation") != null) {
                System.out.println("wala ana hena 1");
                if (request.getParameter("operation").equals("delete")) {
                    delteAppointment(request);
                }
            }

            System.err.println(SessionController.getSessionAtrributeValue(request, "id"));

            ArrayList<Appointment> appointments = new ArrayList<>();

            try {
                PreparedStatement stm ;
                
                if (SessionController.getSessionAtrributeValue(request, "user_type").equals("staff_member"))
                {
                      stm = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM appointment\n"
                        + "INNER JOIN office_hours on appointment.office_hour_id = office_hours.id\n"
                        + "INNER JOIN staff  on appointment.staff_id = staff.id\n"
                        + "INNER JOIN slot ON office_hours.slot = slot.id\n"
                        + "INNER JOIN student on appointment.student_id = student.id WHERE appointment.staff_id=?;");
                
                    
                    
                }
                else
                    
                {
                    System.out.println("i am studemt ya man");
                 stm = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM appointment\n"
                        + "INNER JOIN office_hours on appointment.office_hour_id = office_hours.id\n"
                        + "INNER JOIN staff  on appointment.staff_id = staff.id\n"
                        + "INNER JOIN slot ON office_hours.slot = slot.id\n"
                        + "INNER JOIN student on appointment.student_id = student.id WHERE appointment.student_id=?;");
                
                
                }
               
                
                
                stm.setInt(1, Integer.parseInt(SessionController.getSessionAtrributeValue(request, "id").toString()));
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Appointment appointment = new Appointment();
                    appointment.id = rs.getInt("appointment.id");
                    appointment.student.user_name = rs.getString("student.user_name");
                    appointment.student.mail = rs.getString("student.mail");
                    appointment.student.student_level = rs.getInt("student_level");
                    appointment.officeHours.day = rs.getString("day");
                    appointment.officeHours.slot.slot_name = rs.getString("slot_name");
                    appointment.officeHours.slot.from_hour = rs.getTime("from_hour");
                    appointment.officeHours.slot.to_hour = rs.getTime("to_hour");
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
        System.err.println(SessionController.getSessionAtrributeValue(request, "id"));
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
