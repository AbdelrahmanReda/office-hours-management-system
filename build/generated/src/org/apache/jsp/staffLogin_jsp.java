package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class staffLogin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/layout/wrong_logging_credentials.jsp");
  }

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
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>TODO supply a title</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1\" crossorigin=\"anonymous\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"style2.css\">\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW\" crossorigin=\"anonymous\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"row\" >\n");
      out.write("                <div class=\"col-lg-12 \" style=\"padding-top: 225px\">\n");
      out.write("                    <div class=\"card login-form \">\n");
      out.write("                        <div class=\"card-header\">\n");
      out.write("                            <h6>Login in as staff member</h6>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"card-body \">\n");
      out.write("                            <form  class=\"\" method=\"POST\" action=\"validate\" >\n");
      out.write("                                ");
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
      out.write("\n");
      out.write("                                ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("\n");

    if (session.getAttribute("user_type")!=null){
    if (session.getAttribute("user_type").equals("student"))
    {
                    request.getRequestDispatcher("DashboardController").forward(request, response);

    }
    else
    {
                    request.getRequestDispatcher("DashboardController").forward(request, response);

    }
    }
    if (session.getAttribute("wrong_credentials") != null) {

      out.write("\n");
      out.write("<div class=\"alert alert-danger\" role=\"alert\">\n");
      out.write("    Wrong Creditals Info\n");
      out.write("</div>                            \n");
}
    session.removeAttribute("wrong_credentials");

      out.write('\n');
      out.write("\n");
      out.write("                                <div class=\"mb-3\">\n");
      out.write("                                    <label for=\"customer_id\" class=\"form-label\">Staff Mail</label>\n");
      out.write("                                    <input type=\"mail\" class=\"form-control\" id=\"customer_id\" name=\"email\" required>  \n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"mb-3\">\n");
      out.write("                                    <label for=\"customer_password\" class=\"form-label\">Password</label>\n");
      out.write("                                    <input type=\"password\" class=\"form-control\" id=\"customer_password\" name=\"password\">\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"mb-3\">\n");
      out.write("                                </div>\n");
      out.write("                                <input type=\"hidden\"  name=\"user_type\" value=\"staff\">\n");
      out.write("                                <button style=\"width: 100%\" type=\"submit\" class=\"btn btn-primary\">Sign in</button>  \n");
      out.write("                                <div class=\"d-flex justify-content-center align-items-center mt-2 flex-column\">\n");
      out.write("                                    <small text-center>Are you Student?</small>\n");
      out.write("                                    <a style=\"text-decoration: underline\" href=\"login.jsp\">Login in as Student</a>\n");
      out.write("                                </div>\n");
      out.write("                            </form>    \n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
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
