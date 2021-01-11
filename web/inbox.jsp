<%-- 
    Document   : inbox
    Created on : Jan 7, 2021, 10:42:55 AM
    Author     : boody
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Models.Appointment"%>
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

                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12 p-0">
                            <div class="card">
                                <div class="card-header">
                                    <h3>Primary Mails</h3>  
                                </div>
                                <div class="card-body">
                                              <div class="list-group overflow-auto" >


                                <%
                                    // retrieve your list from the request, with casting 
                                    ArrayList<Models.UserMessage> list = (ArrayList<Models.UserMessage>) request.getAttribute("conversations");
                                    for (int i = 0; i < list.size(); i++) {
                                %>
                                <a href="${pageContext.request.contextPath}/InboxController?conversation_id=<% out.print(list.get(i).conversation.id);%>" class="list-group-item list-group-item-action flex-column align-items-start">
                                    <div class="d-flex w-100 justify-content-between">
                                            
                                        

                                        <%
                                               
                                            out.print("<h5 class=\"mb-1\">" + list.get(i).conversation.subject + "</h5>   ");
                                            out.print("<small>"+list.get(i).message.create_at+"</small>");
                                            out.print("</div>");
                                            out.print("<p class=\"mb-1\">" + list.get(i).message.messageBoody + "</p>");
                                            out.print(" <small> <span class=\"badge badge-primary\">Sent From > </span> "+list.get(i).sender+"</small></a>");


                                        %>

                                        <%}%>


                                    </div>
                            </div>
                                </div>
                            </div>
                                      
                           

                  
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

                $('.inbox').toggleClass('activeElement');

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
