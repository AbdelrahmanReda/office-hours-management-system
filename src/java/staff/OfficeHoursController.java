/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staff;

import Helpers.DatabaseConnector;
import Helpers.SessionController;
import Models.OfficeHours;
import com.google.gson.Gson;

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
@WebServlet(name = "OfficeHoursController", urlPatterns = {"/OfficeHoursController"})
public class OfficeHoursController extends HttpServlet {

    public ArrayList<OfficeHours> getStaffStaffOfficeHourse(HttpServletRequest request) {
        try {
            ArrayList<OfficeHours> officeHours = new ArrayList<>();
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM staff\n"
                    + "INNER  JOIN office_hours  on staff.id = staff_id\n"
                    + "INNER  JOIN slot  on office_hours.slot = slot.id\n"
                    + "WHERE staff.id =?");
            stmt.setInt(1, Integer.parseInt(request.getParameter("getStaffMembers")));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OfficeHours obj = new OfficeHours();
                obj.id = rs.getInt("office_hours.id");
                obj.day = rs.getString("day");
                obj.staff.id = rs.getInt("staff_id");
                obj.staff.dapartment = rs.getString("dapartment");
                obj.staff.user_name = rs.getString("user_name");
                obj.staff.mail = rs.getString("mail");
                obj.slot.slot_name = rs.getString("slot_name");
                obj.slot.from_hour = rs.getTime("from_hour");
                obj.slot.to_hour = rs.getTime("to_hour");
                officeHours.add(obj);
            }
            return officeHours;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OfficeHoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public ArrayList<OfficeHours> getOfficeHourse(HttpServletRequest request) {
        try {
            ArrayList<OfficeHours> officeHours = new ArrayList<>();
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM office_hours INNER JOIN slot on  office_hours.slot = slot.id  WHERE staff_id=?");
            stmt.setInt(1, Integer.parseInt(SessionController.getSessionAtrributeValue(request, "id")));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OfficeHours obj = new OfficeHours();
                obj.id = rs.getInt("id");
                obj.staff_id = rs.getInt("staff_id");
                obj.day = rs.getString("day");
                obj.slot.slot_name = rs.getString("slot_name");
                obj.slot.from_hour = rs.getTime("from_hour");
                obj.slot.to_hour = rs.getTime("to_hour");

                officeHours.add(obj);
            }
            return officeHours;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OfficeHoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertOfficeHour(HttpServletRequest request) {
        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("INSERT  INTO  office_hours VALUES (DEFAULT,?,?,?)");
            stmt.setInt(1, Integer.parseInt(SessionController.getSessionAtrributeValue(request, "id")));
            stmt.setString(2, request.getParameter("day"));
            stmt.setString(3, request.getParameter("slot"));
            stmt.executeUpdate();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OfficeHoursController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OfficeHoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateOfficeHour(HttpServletRequest request) {
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        
        try {
            System.out.println("req is "+request);
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("UPDATE office_hours set office_hours.day =? , slot = ? WHERE id=?");
        
            
            stmt.setString(1,request.getParameter("day") );
            stmt.setInt(2, Integer.parseInt(request.getParameter("slot")));
            stmt.setInt(3, Integer.parseInt(request.getParameter("id")));
            System.out.println("update query is "+stmt);
            stmt.executeUpdate();
            
            
            
            System.out.println("####################################################");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OfficeHoursController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OfficeHoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public OfficeHours showOfficeHour(HttpServletRequest request) {
        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM office_hours WHERE id=?");
            stmt.setInt(1, Integer.parseInt(request.getParameter("id")));
            ResultSet rs = stmt.executeQuery();
            OfficeHours obj = new OfficeHours();
            while (rs.next()) {
                obj.id = rs.getInt("id");
                obj.staff_id = rs.getInt("staff_id");
                obj.day = rs.getString("day");
                return obj;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OfficeHoursController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OfficeHoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void deleteOfficeHour(HttpServletRequest request) {
        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("DELETE FROM office_hours where id=?");
            stmt.setInt(1, Integer.parseInt(request.getParameter("id")));
            stmt.executeUpdate();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OfficeHoursController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OfficeHoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void reserveOfficeHours(HttpServletRequest request) {

        try {
            PreparedStatement stm = DatabaseConnector.getConnection().prepareStatement("INSERT INTO appointment VALUES (DEFAULT,?,?,?);");
            stm.setInt(1, Integer.parseInt(request.getParameter("staff_id")));
            stm.setInt(2, Integer.parseInt(SessionController.getSessionAtrributeValue(request, "id")));
            stm.setInt(3, Integer.parseInt(request.getParameter("office_hour_id")));

            stm.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OfficeHoursController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            

            if (request.getParameter("getStaffMembers") != null) {
                request.setAttribute("OfficeHours", getStaffStaffOfficeHourse(request));
                request.getRequestDispatcher("staff_member.jsp").forward(request, response);

            } else if (request.getParameter("office_hour_id") != null && request.getParameter("staff_id") != null) {


                reserveOfficeHours(request);
                response.sendRedirect("AppointmentController");

            } else {
                request.setAttribute("OfficeHours", getOfficeHourse(request));

                if (request.getParameter("operation").equals("selection")) {
                    String json = new Gson().toJson(getOfficeHourse(request));
                    System.out.println(json);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                    System.out.println("----------------------------------------------------------------------------------*-*-**-**-*-*--------------------");

                }
                if (request.getParameter("operation").equals("delete")) {
                    System.out.println("delete operation");
                    deleteOfficeHour(request);

                    String json = new Gson().toJson(getOfficeHourse(request));
                    System.out.println(json);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);

                }
                if (request.getParameter("operation").equals("update")) {
                   
                    updateOfficeHour(request);
                }
                if (request.getParameter("operation").equals("insertion")) {
                    System.out.println("insert operation");
                    insertOfficeHour(request);
                }

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
