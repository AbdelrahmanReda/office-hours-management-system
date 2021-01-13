<%-- 
    Document   : compose_mail
    Created on : Jan 6, 2021, 7:02:01 PM
    Author     : boody
--%>

<%@page import="Mail.MailController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="layout/header.jsp" %> 
    <body>
        <%  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>

        <div class="wrapper">
            <!-- Sidebar  -->
            <%@ include file = "layout\sidebar.jsp" %>
            <!-- Page Content  -->
            <div id="content">
                <%@ include file = "layout\navbar.jsp" %>
                <%     if (session.getAttribute("email") == null) {
                        response.sendRedirect("login.jsp");
                    }
                    if (request.getAttribute("status") != null) {
                        if (request.getAttribute("status").equals("success")) {
                            out.print("<script> swal(\"Good job!\", \"You clicked the button!\", \"success\")</script>");
                        }
                    }
                %>

                <div class="card">
                    <div class="card-header">
                        <h3>Compose an E-mail</h3>
                    </div>
                    <div class="card-body">
                        <form action="MailController" method="POST" >
                            <div class="form-group">
                                <label for="recipients">Email address</label>
                                <input type="text" class="form-control" name="recipients" id="recipients" placeholder="name@example.com">
                            </div>
                            <div class="form-group">
                                <label for="subject">Subject</label>
                                <input type="text" class="form-control" id="subject" name="subject" placeholder="Regarding to assignment 1">
                            </div>
                            <div class="form-group">
                                <label for="message">Message</label>
                                <textarea class="form-control" name="message" id="message" rows="15"></textarea>
                            </div>                           
                            <button type="submit" class="btn btn-success">Send Mail</button>
                        </form>
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

            $('.compose-mail').toggleClass('activeElement');

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
