/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            HttpSession session = request.getSession();
            String normalEmail = (String) session.getAttribute("email");
            

            String ChangeEmail = request.getParameter("email");
            String password = request.getParameter("password");
            String gender = request.getParameter("gender");
            String coutry = request.getParameter("country");

//            out.println("<h1>" + ChangeEmail + "</h1>");
//            out.println("<h1>" + password + "</h1>");
//            out.println("<h1>" + gender + "</h1>");
            boolean flag=false;
            
            
           

            String url = "jdbc:mysql://localhost:3306/office_hours";
            Connection con = null;
            Statement Stmt = null;
            ResultSet RS = null;

            

            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, "root", "1234");
                Stmt = (Statement) con.createStatement();
                
                 RS = Stmt.executeQuery("SELECT * FROM student where email = '" + normalEmail + "'");
                 while(RS.next()){
                 String userName = RS.getString("username");
                
                if (ChangeEmail.equals("") && password.equals("") && gender.equals("") && coutry.equals("")) {
                out.print("<p> You Didnt Change your Info</p>");}
                
                if(!ChangeEmail.equals("")){
                    flag = true;
                    PreparedStatement preparedStmt2 = con.prepareStatement("UPDATE student SET email = '" + ChangeEmail + "' WHERE username = '" + userName + "'");
                    preparedStmt2.executeUpdate();
                    
                }
                if(!password.equals("") ){
                     flag = true;
                    PreparedStatement preparedStmt3 = con.prepareStatement("UPDATE student SET password = '" + password + "' WHERE username = '" + userName + "'");
                    preparedStmt3.executeUpdate();
                    
                }
                if(!gender.equals("") ){
                     flag = true;
                    PreparedStatement preparedStmt4 = con.prepareStatement("UPDATE student SET gender = '" + gender + "' WHERE username = '" + userName + "'");
                    preparedStmt4.executeUpdate();
                    
                }
                if(!coutry.equals("")){
                     flag = true;
                    PreparedStatement preparedStmt5 = con.prepareStatement("UPDATE student SET country = '" + coutry + "' WHERE username = '" + userName + "'");
                    preparedStmt5.executeUpdate();
                }
                if (flag == true){
                    out.print("<p> success changes</p>");
                }
                else{
                    out.print("<p> You Didnt Change your Info</p>");
                }

                 }
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
            Logger.getLogger(changeInfo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(changeInfo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(changeInfo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(changeInfo.class.getName()).log(Level.SEVERE, null, ex);
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
