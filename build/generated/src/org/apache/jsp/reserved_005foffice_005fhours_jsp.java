package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Models.Appointment;
import java.util.ArrayList;
import staff.OfficeHoursController;

public final class reserved_005foffice_005fhours_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(4);
    _jspx_dependants.add("/layout/header.jsp");
    _jspx_dependants.add("/layout/sidebar.jsp");
    _jspx_dependants.add("/layout/navbar.jsp");
    _jspx_dependants.add("/layout/scripts.jsp");
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
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("\n");
      out.write("    ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("   <head>\n");
      out.write("       \n");
      out.write("       <link href=\"https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js\">\n");
      out.write("        <link href=\"https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("        <title>Collapsible sidebar using Bootstrap 4</title>\n");
      out.write("        <link href=\"//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css\" rel=\"stylesheet\">\n");
      out.write("        <!-- Our Custom CSS -->\n");
      out.write("        <link rel=\"stylesheet\" href=\"style2.css\">\n");
      out.write("        <!-- Scrollbar Custom CSS -->\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css\">\n");
      out.write("        <!-- Font Awesome JS -->\n");
      out.write("        <script defer src=\"https://use.fontawesome.com/releases/v5.0.13/js/solid.js\" integrity=\"sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ\" crossorigin=\"anonymous\"></script>\n");
      out.write("        <script defer src=\"https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js\" integrity=\"sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY\" crossorigin=\"anonymous\"></script>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write(" \n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        <div class=\"wrapper\">\n");
      out.write("            <!-- Sidebar  -->\n");
      out.write("            ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<nav id=\"sidebar\">\n");
      out.write("    <div class=\"sidebar-header\">\n");
      out.write("        <h3>welcome </h3>\n");
      out.write("    </div>\n");
      out.write("    <ul class=\"list-unstyled components\">\n");
      out.write("        <li>\n");
      out.write("            <a class=\"Dashboard\" href=\"DashboardController\">Home</a>\n");
      out.write("        </li>\n");
      out.write("        <li>\n");
      out.write("            <a class=\"Transactins\" href=\"staffCourse.jsp\">Courses</a>\n");
      out.write("        </li>\n");
      out.write("        <li>\n");
      out.write("            <a class=\"Transactins\" href=\"veiwMessages.jsp\">Messages</a>\n");
      out.write("        </li>  \n");
      out.write("        <li>\n");
      out.write("            <a class=\"Transactins\" href=\"contact.jsp\">Staff E-mails</a>\n");
      out.write("        </li>\n");
      out.write("        <li>\n");
      out.write("            <a class=\"Transactins\" href=\"officehourse.jsp\">Staff office hours</a>\n");
      out.write("        </li>\n");
      out.write("        \n");
      out.write("        ");

            
            if (session.getAttribute("user_type").equals("staff_member")) {
      out.write("\n");
      out.write("        <li>\n");
      out.write("            <a class=\"student\" href=\"StudentController\">Students</a>\n");
      out.write("        </li>\n");
      out.write("        <li>\n");
      out.write("            <a class=\"reserved-office-hours\" href=\"AppointmentController\">Reserved Office Hours</a>\n");
      out.write("        </li>\n");
      out.write("        <li>\n");
      out.write("            <a class=\"Manage-office-hours\" href=\"manage_office_hours.jsp\">Manage office hours</a>\n");
      out.write("        </li>\n");
      out.write("        \n");
      out.write("\n");
      out.write("        ");
}
      out.write("\n");
      out.write("    </ul>\n");
      out.write("</nav>");
      out.write("\n");
      out.write("            <!-- Page Content  -->\n");
      out.write("            <div id=\"content\">\n");
      out.write("                ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<nav class=\"navbar navbar-expand-md \">\n");
      out.write("    <div class=\"container-fluid\">\n");
      out.write("        <button type=\"button\" id=\"sidebarCollapse\" class=\"btn btn-info\">\n");
      out.write("            <i class=\"fas fa-align-left\"></i>\n");
      out.write("\n");
      out.write("        </button>\n");
      out.write("        <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n");
      out.write("            <span class=\"navbar-toggler-icon\"></span>\n");
      out.write("        </button>\n");
      out.write("        <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n");
      out.write("            <ul class=\"navbar-nav ml-auto\">\n");
      out.write("                <li class=\"nav-item dropdown\" style=\"\">\n");
      out.write("                    <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n");
      out.write("                       ");
  out.print(request.getSession().getAttribute("username"));
      out.write("\n");
      out.write("                    </a>\n");
      out.write("                    <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\n");
      out.write("                       <a class=\"dropdown-item\" href=\"changeInfo.jsp\">Change Info</a>\n");
      out.write("                       \n");
      out.write("                        <div class=\"dropdown-divider\"></div>\n");
      out.write("                         <a class=\"dropdown-item\" href=\"logOut\">Log out</a>\n");
      out.write("                    </div>\n");
      out.write("                </li>\n");
      out.write("                          \n");
      out.write("            </ul>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</nav>");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                <div class=\"card\">\n");
      out.write("                    <div class=\"card-header\">\n");
      out.write("                        <form id=\"select-form\"><input type=\"hidden\" name=\"operation\" value=\"selection\" ></form>\n");
      out.write("                        <h3>Office Hours Management</h3>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"card-body\">\n");
      out.write("                        <div class=\"container-fluid\" >\n");
      out.write("                            <div class=\"row\" >\n");
      out.write("                                \n");
      out.write("                                <div class=\"col-lg-12\">\n");
      out.write("\n");
      out.write("                                    <table id=\"example\" class=\"table table-striped table-bordered\" style=\"width:100%\">\n");
      out.write("                                        <thead>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <th>#</th>\n");
      out.write("                                                <th>Day</th>\n");
      out.write("                                                <th>Slot</th>\n");
      out.write("                                                <th>from</th>\n");
      out.write("                                                <th>to</th>\n");
      out.write("                                                <th>Student Name</th>\n");
      out.write("                                                <th>Student Mail</th>\n");
      out.write("                                                <th>Student Level</th>\n");
      out.write("                                            </tr>\n");
      out.write("                                        </thead>\n");
      out.write("                                        <tbody>\n");
      out.write("                                            ");

                                                // retrieve your list from the request, with casting 
                                                ArrayList<Appointment> list = (ArrayList<Appointment>) request.getAttribute("appointments");

                                                // print the information about every category of the list
                                                for (int i = 0; i < list.size(); i++) {
                                                    out.print("<tr>");
                                                    out.print("<td>"+(i+1)+"</td>");
                                                    out.print("<td>"+list.get(i).officeHours.day+"</td>");
                                                    out.print("<td>"+list.get(i).officeHours.slot.slot_name+"</td>");
                                                    out.print("<td>"+list.get(i).officeHours.slot.from_hour+"</td>");
                                                    out.print("<td>"+list.get(i).officeHours.slot.to_hour+"</td>");
                                                    out.print("<td>"+list.get(i).student.user_name+"</td>");
                                                    out.print("<td>"+list.get(i).student.mail+"</td>");
                                                    out.print("<td>"+list.get(i).student.student_level+"</td>");
                                                    out.print("</td>");

                                                }

                                            
      out.write("  \n");
      out.write("\n");
      out.write("                                        </tbody>\n");
      out.write("\n");
      out.write("                                    </table>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("                    <!-- Modal -->\n");
      out.write("                    <div class=\"modal fade\" id=\"exampleModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\n");
      out.write("                        <div class=\"modal-dialog\" role=\"document\">\n");
      out.write("                            <div class=\"modal-content\">\n");
      out.write("                                <div class=\"modal-header\">\n");
      out.write("                                    <h5 class=\"modal-title\" id=\"exampleModalLabel\">Add new office hour</h5>\n");
      out.write("                                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n");
      out.write("                                        <span aria-hidden=\"true\">&times;</span>\n");
      out.write("                                    </button>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"modal-body\">\n");
      out.write("                                    <form id=\"registrationForm\">\n");
      out.write("                                        <input type=\"hidden\" name=\"operation\" value=\"insertion\">\n");
      out.write("                                        <div class=\"form-group row\">\n");
      out.write("                                            <label for=\"example-time-input\" class=\"col-2 col-form-label\">Time</label>\n");
      out.write("                                            <div class=\"col-12\">\n");
      out.write("                                                <input class=\"form-control\" name=\"time\" type=\"time\" value=\"13:45:00\" id=\"date\">\n");
      out.write("                                            </div>\n");
      out.write("                                        </div>\n");
      out.write("                                        <div class=\"form-group\">\n");
      out.write("                                            <label for=\"day\">Example select</label>\n");
      out.write("                                            <select class=\"form-control\"  name=\"day\" id=\"day\">\n");
      out.write("                                                <option>Saturday</option>\n");
      out.write("                                                <option>Sunday</option>\n");
      out.write("                                                <option>Monday</option>\n");
      out.write("                                                <option>Tuesday</option>\n");
      out.write("                                                <option>Wednesday</option>\n");
      out.write("                                                <option>Thursday</option>\n");
      out.write("                                                <option>Friday</option>\n");
      out.write("\n");
      out.write("                                            </select>\n");
      out.write("                                        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("                                    </form>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"modal-footer\">\n");
      out.write("                                    <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n");
      out.write("                                    <button type=\"button\" class=\"btn btn-success\" onclick=\"callJqueryAjax()\">Add Office Hour</button>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("<script src=\"https://code.jquery.com/jquery-3.5.1.js\" integrity=\"sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=\" crossorigin=\"anonymous\"></script>\n");
      out.write("<!-- jQuery CDN - Slim version (=without AJAX) -->\n");
      out.write("<!-- Popper.JS -->\n");
      out.write("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js\" integrity=\"sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ\" crossorigin=\"anonymous\"></script>\n");
      out.write("<!-- Bootstrap JS -->\n");
      out.write("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js\" integrity=\"sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm\" crossorigin=\"anonymous\"></script>\n");
      out.write("<!-- jQuery Custom Scroller CDN -->\n");
      out.write("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js\"></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\n");
      out.write("  \n");
      out.write("\n");
      out.write("    $(document).ready(function () {\n");
      out.write("        $(\"#sidebar\").mCustomScrollbar({\n");
      out.write("            theme: \"minimal\"\n");
      out.write("        });\n");
      out.write("\n");
      out.write("        $('#sidebarCollapse').on('click', function () {\n");
      out.write("            $('#sidebar, #content').toggleClass('active');\n");
      out.write("            $('.collapse.in').toggleClass('in');\n");
      out.write("            $('a[aria-expanded=true]').attr('aria-expanded', 'false');\n");
      out.write("        });\n");
      out.write("    });\n");
      out.write("</script>");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<script src=\"https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js\" ></script>\n");
      out.write("<script src=\"https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js\" ></script>\n");
      out.write("<script>\n");
      out.write("                                        $(document).ready(function () {\n");
      out.write("                                            $('#example').DataTable();\n");
      out.write("                                        });\n");
      out.write("\n");
      out.write("</script>\n");
      out.write("<script>  $('.reserved-office-hours').toggleClass('activeElement');</script>\n");
      out.write("\n");
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
