package Mail;


import Mail.MailConfiguration;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.RequestDispatcher;
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
@WebServlet(urlPatterns = {"/sendMessage"})
public class sendMessage extends HttpServlet {

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
               String message ="Hello world";
                
                String subject = "msg subject" ;
                
                String from = "boodycat009@gmail.com";
                String pass = "2266554488";
                 MailConfiguration.SendEmailToStaff(from, "abredafadl@gmail.com", subject, message, pass);
                 System.out.println("called success");
                 
                // List<String> Emails = new ArrayList<String>();
//                RS = Stmt.executeQuery("SELECT * FROM student where email ='" + to + "' ");
//                status = RS.next();

//                if (status) {
//
//                  
//
//                    String line = "INSERT INTO message VALUES("
//                            + "'" + from + "',"
//                            + "'" + to + "',"
//                            + "'" + message + "',"
//                            + "'" + subject + "') ";
//                    int Rows = Stmt.executeUpdate(line);
//                    Stmt.close();
//                } else {
//                    out.println("<script type=\"text/javascript\">");
//                    out.println("alert('Email You Want To Sent Does Not Exist');");
//                    out.println("location='sendMessage.html';");
//                    out.println("</script>");
//                    //RequestDispatcher rd = request.getRequestDispatcher("sendMessage");
//                    //rd.forward(request, response);
//                }

           
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
