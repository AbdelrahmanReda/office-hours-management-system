/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staff;

import Helpers.DatabaseConnector;
import Helpers.SessionController;
import Mail.MailConfiguration;
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

    public void notifyStudentAsOficeHourCanceled(int officeHourId, HttpServletRequest request) throws SQLException, ClassNotFoundException {
        ArrayList<String> studentMails = new ArrayList<>();
        PreparedStatement stm = DatabaseConnector.getConnection().prepareStatement("SELECT mail FROM appointment INNER JOIN student ON appointment.student_id = student.id\n"
                + "WHERE office_hour_id=?");
        stm.setInt(1, officeHourId);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            notifyStudent(rs.getString("mail"), request);
        }
    }

    private void notifyStudent(String studentMail, HttpServletRequest request) {
        String from = SessionController.getSessionAtrributeValue(request, "email");
        String pass = SessionController.getSessionAtrributeValue(request, "mail_password");
        String recipients = studentMail;
        String subject = "Meeting Cancelation";
        String message = "We are sorry we well cancel the meeting";
        MailConfiguration.SendEmailToStaff(from, recipients, subject, message, pass);
    }

    private void notifyStaffMember(String staffMail, HttpServletRequest request) {
        String from = SessionController.getSessionAtrributeValue(request, "email");
        String pass = SessionController.getSessionAtrributeValue(request, "mail_password");
        String recipients = staffMail;
        String subject = "Meeting Cancelation";
        String message = "We are sorry we well cancel the meeting";
        MailConfiguration.SendEmailToStaff(from, recipients, subject, message, pass);
    }

    public void delteAppointment(HttpServletRequest request) {

        try {
            if (isNumeric(request.getParameter("reservation_id"))) {
                Integer id = Integer.parseInt(request.getParameter("reservation_id"));
                if (SessionController.getSessionAtrributeValue(request, "user_type").equals("staff_member")) {
                    String studentMail = null;
                    PreparedStatement stm = DatabaseConnector.getConnection().prepareStatement("SELECT student.mail FROM appointment\n"
                            + "INNER JOIN student  on appointment.student_id = student.id\n"
                            + "WHERE appointment.id=?;");
                    stm.setInt(1, id);
                    ResultSet rs = stm.executeQuery();

                    while (rs.next()) {
                        studentMail = rs.getString("mail");
                    }

                    notifyStudent(studentMail, request);

                } else {

                    String StaffMail = null;
                    PreparedStatement stm = DatabaseConnector.getConnection().prepareStatement("SELECT staff.mail FROM appointment\n"
                            + "INNER JOIN staff  on appointment.staff_id = staff.id\n"
                            + "WHERE appointment.id=?;");
                    stm.setInt(1, id);
                    ResultSet rs = stm.executeQuery();
                    while (rs.next()) {
                        StaffMail = rs.getString("mail");
                        notifyStaffMember(StaffMail, request);
                    }

                }
                PreparedStatement stm = DatabaseConnector.getConnection().prepareStatement("DELETE FROM appointment where id  =  ?");
                stm.setInt(1, id);
                stm.executeUpdate();

            } else {

                //bulk day
                String day = request.getParameter("reservation_id");

                PreparedStatement stmt = DatabaseConnector.getConnection().prepareCall("SELECT DISTINCT student.mail FROM student\n"
                        + "INNER  JOIN appointment ON student.id = appointment.student_id\n"
                        + "INNER JOIN office_hours  on appointment.office_hour_id = office_hours.id\n"
                        + "WHERE appointment.staff_id = ? AND office_hours.day=?");

                stmt.setInt(1, Integer.parseInt(SessionController.getSessionAtrributeValue(request, "id")));
                stmt.setString(2, day);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String mail = rs.getString("mail");
                    System.out.println("we will bulk " + mail);

                    notifyStudent(mail, request);
                }

                PreparedStatement stm = DatabaseConnector.getConnection().prepareStatement("DELETE  FROM  appointment WHERE id IN (\n"
                        + "SELECT appointment.id FROM appointment\n"
                        + "INNER JOIN office_hours ON appointment.office_hour_id = office_hours.id\n"
                        + "INNER JOIN staff  ON appointment.staff_id = staff.id\n"
                        + "INNER JOIN slot ON office_hours.slot = slot.id\n"
                        + "INNER JOIN student ON appointment.student_id = student.id WHERE office_hours.day=?)");
                stm.setString(1, day);
                stm.executeUpdate();
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            if (SessionController.getSessionAtrributeValue(request, "user_type") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            if (request.getParameter("operation") != null) {
                if (request.getParameter("operation").equals("delete")) {
                    delteAppointment(request);
                }
            }

            ArrayList<Appointment> appointments = new ArrayList<>();

            try {
                PreparedStatement stm;

                if (SessionController.getSessionAtrributeValue(request, "user_type").equals("staff_member")) {
                    stm = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM appointment\n"
                            + "INNER JOIN office_hours on appointment.office_hour_id = office_hours.id\n"
                            + "INNER JOIN staff  on appointment.staff_id = staff.id\n"
                            + "INNER JOIN slot ON office_hours.slot = slot.id\n"
                            + "INNER JOIN student on appointment.student_id = student.id WHERE appointment.staff_id=?;");

                } else {

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
                    appointment.staff.user_name = rs.getString("staff.user_name");
                    appointment.staff.first_name = rs.getString("staff.first_name");
                    appointment.staff.last_name = rs.getString("staff.last_name");
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
