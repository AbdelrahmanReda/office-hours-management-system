
import java.sql.PreparedStatement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(urlPatterns = {"/validate"})
public class validate extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String email = request.getParameter("email");
            String pass = request.getParameter("password");
            String type = request.getParameter("user_type");

            if (type.equals("student")) {

                PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM student WHERE student.mail = ? AND student.password = ?");
                stmt.setString(1, email);
                stmt.setString(2, pass);
                ResultSet rs = stmt.executeQuery();
                boolean notExists = true;
                while (rs.next()) {
                    notExists = false;

                    out.print(rs.getString("user_name"));
                    out.print(rs.getString("mail"));
                    out.print(rs.getString("gender"));
                    String user_name = rs.getString("user_name");

                    HttpSession session = request.getSession();

                    session.setAttribute("email", email);
                    session.setAttribute("username", user_name);
                    session.setAttribute("password", pass);
                    response.sendRedirect("DashboardController");

                }
                if (notExists) {
                    out.print("student not founded");
                }

            } else {

                PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM staff WHERE staff.mail = ? AND staff.password = ?");
                stmt.setString(1, email);
                stmt.setString(2, pass);
                ResultSet rs = stmt.executeQuery();
                boolean notExists = true;
                while (rs.next()) {
                    notExists = false;

                    out.print(rs.getString("user_name"));
                    out.print(rs.getString("mail"));
                    out.print(rs.getString("gender"));
                    String user_name = rs.getString("user_name");

                    HttpSession session = request.getSession();

                    session.setAttribute("email", email);
                    session.setAttribute("username", user_name);
                    session.setAttribute("password", pass);
                    session.setAttribute("user_type", "staff");
                    response.sendRedirect("DashboardController");
                }
                if (notExists) {
                    out.print("prof not founded");
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(validate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(validate.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(validate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(validate.class.getName()).log(Level.SEVERE, null, ex);
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
