/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Helpers.DatabaseConnector;
import Mail.MailConfiguration;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
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
@WebServlet(urlPatterns = {"/DashboardController"})
public class DashboardController extends HttpServlet {

    private void sendReminderMail(String staffMail, String studentMail, String slotName) {

        System.out.println("sending daily reports");

        studentMail = "boodycat09@gmail.com";
        staffMail = "boodycat009@gmail.com";
        System.out.println("notifing student on meeting cancellation");
        String from = "boodycat009@gmail.com";
        String pass = "2266554488";

        String subject = "Today there is a meeting";
        String message = "don't froget today's meeting ";

        MailConfiguration.SendEmailToStaff(from, staffMail, subject, message, pass);
        MailConfiguration.SendEmailToStaff(from, studentMail, subject, message, pass);

    }

    private void sendDailyMail() throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DatabaseConnector.getConnection().prepareStatement("SELECT staff.mail ,slot.slot_name , student.mail ,day FROM appointment\n"
                + "INNER JOIN staff  on appointment.staff_id = staff.id\n"
                + "INNER  JOIN student  on appointment.student_id = student.id\n"
                + "INNER JOIN office_hours oh on appointment.office_hour_id = oh.id\n"
                + "INNER  JOIN slot on oh.slot = slot.id\n"
                + "WHERE day = ?");
        stm.setString(1, LocalDate.now().getDayOfWeek().name());
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            //sendReminderMail(rs.getString("staff.mail"),rs.getString("student.mail"),rs.getString("slot_name"));

        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            try {
                sendDailyMail();

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
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
