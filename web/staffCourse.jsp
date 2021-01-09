<%-- 
    Document   : staffCourse
    Created on : Dec 26, 2020, 9:40:13 PM
    Author     : toqa khaled
--%>
<%@page import="Models.Staff"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>



    <%@include file="layout/header.jsp" %> 




    <body>
        <%  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");%>

        <div class="wrapper">
            <!-- Sidebar  -->
            <%@ include file = "layout\sidebar.jsp" %>
            <!-- Page Content  -->
            <div id="content">
                <%@ include file = "layout\navbar.jsp" %>
                <div class="card" >
                    <div class="card-header">
                        <h3>Find Staff Member</h3>
                    </div>
                    <div class="card-body">
                        <form>
                            <div class="form-group">
                                <label for="course">Course Name</label>
                                <input type="text" class="form-control" id="course" name="course"   placeholder="Linear Programming">
                                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                            </div>
                            <button type="submit" class="btn btn-primary">Search Staff</button>
                        </form>
                       

                        <%
                            if (request.getAttribute("staffMembers") != null) {

                                ArrayList<Staff> list = (ArrayList<Staff>) request.getAttribute("staffMembers");
                                for (int i = 0; i < list.size(); i++) {
                                    out.print(list.get(i).user_name);
                                    out.print(list.get(i).dapartment);
                                    out.print(list.get(i).mail);
                                }
                            }

                        %>







                    </div>
                </div>









            </div>
        </div>

        <!-- jQuery CDN - Slim version (=without AJAX) -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <!-- Popper.JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
        <!-- Bootstrap JS -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
        <!-- jQuery Custom Scroller CDN -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>

        <script type="text/javascript">

            $('.find-staff').toggleClass('activeElement');

            $(document).ready(function () {
                $("#sidebar").mCustomScrollbar({
                    theme: "minimal"
                });

                $('#sidebarCollapse').on('click', function () {
                    $('#sidebar, #content').toggleClass('active');
                    $('.collapse.in').toggleClass('in');
                    $('a[aria-expanded=true]').attr('aria-expanded', 'false');
                });
            });
        </script>
    </body>

</html>


