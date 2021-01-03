package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public final class staffCourse_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <style>\n");
      out.write("        body {\n");
      out.write("            font-family: Arial;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        * {\n");
      out.write("            box-sizing: border-box;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        form.example input[type=text] {\n");
      out.write("            padding: 10px;\n");
      out.write("            font-size: 17px;\n");
      out.write("            border: 1px solid grey;\n");
      out.write("            float: left;\n");
      out.write("            width: 80%;\n");
      out.write("            background: #f1f1f1;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        form.example button {\n");
      out.write("            float: left;\n");
      out.write("            width: 20%;\n");
      out.write("            padding: 10px;\n");
      out.write("            background: #4CAF50;\n");
      out.write("            color: white;\n");
      out.write("            font-size: 17px;\n");
      out.write("            border: 1px solid grey;\n");
      out.write("            border-left: none;\n");
      out.write("            cursor: pointer;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        form.example::after {\n");
      out.write("            content: \"\";\n");
      out.write("            clear: both;\n");
      out.write("            display: table;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("    </head>\n");
      out.write("    \n");
      out.write("<body>\n");
      out.write("    <form class=\"example\"  style=\"margin:auto;max-width:300px\">\n");
      out.write("        <input type=\"text\" placeholder=\"Search..\" name=\"search\">\n");
      out.write("        <button type=\"submit\"><i class=\"fa fa-search\"></i></button>\n");
      out.write("    </form>\n");
      out.write("\n");
      out.write("    <p id=\"searchBar\"></p>\n");
      out.write("    <p id=\"show\"></p>\n");
      out.write("\n");
      out.write("    ");


        String courseID = request.getParameter("search");
        boolean status = false;
        String url = "jdbc:mysql://localhost:1212/office_hours";
        Connection con = null;
        Statement Stmt = null;
        ResultSet RS = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "1234");
            Stmt = (Statement) con.createStatement();
            RS = Stmt.executeQuery("SELECT * FROM course where courseID ='" + courseID + "' OR courseName='" + courseID + "' ");
    
      out.write("\n");
      out.write("    <table border=1 align=center style=\"text-align:center\">\n");
      out.write("        <thead>\n");
      out.write("            <tr>\n");
      out.write("                <th>Course ID</th>\n");
      out.write("                <th>Course Name</th>\n");
      out.write("                <th>TA</th>\n");
      out.write("\n");
      out.write("            </tr>\n");
      out.write("        </thead>\n");
      out.write("        <tbody>\n");
      out.write("            ");
while (RS.next()) {
            
      out.write("\n");
      out.write("            <tr>\n");
      out.write("                <td>");
      out.print(RS.getString("courseID"));
      out.write("</td>\n");
      out.write("                <td>");
      out.print(RS.getString("courseName"));
      out.write("</td>\n");
      out.write("                <td>");
      out.print(RS.getString("staffName"));
      out.write("</td>\n");
      out.write("            </tr>\n");
      out.write("            ");
}
      out.write("\n");
      out.write("        </tbody>\n");
      out.write("    </table><br>\n");
      out.write("    ");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
