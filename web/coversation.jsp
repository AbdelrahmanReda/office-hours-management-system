<%-- 
    Document   : coversation
    Created on : Jan 8, 2021, 3:31:03 PM
    Author     : boody
--%>

<%@page import="java.util.ArrayList"%>
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

                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12 p-0">
                            <div class="card">
                                <div class="card-header">
                                    
                                    <h3>Primary Mails</h3>  
                                </div>
                                <div class="card-body">


                                    
                                    <%
                                        ArrayList<Models.UserMessage> list = (ArrayList<Models.UserMessage>) request.getAttribute("conversation");
                                        for (int i = 0; i < list.size(); i++) {
                                    %>

                                    <div class="media mb-3" style="border-bottom: 1px solid #d8d8d8;">
                                        <img src="https://res.cloudinary.com/mhmd/image/upload/v1564960395/avatar_usae7z.svg" alt="user" width="50" class="rounded-circle">

                                        <div class="media-body ml-3">
                                            <div class="bg-light rounded py-2 px-3 mb-2">

                                                <%
                                                        out.print(" <h6>" + list.get(i).sender + " </h6>");
                                                        out.print( " <p class=\"text-small mb-0 text-muted\">"+list.get(i).message.messageBoody+"</p> </div> ");
                                                        
                                                        out.print(" <p class=\"small text-muted \">"+list.get(i).message.create_at+"</p></div></div>");
                                                    }
                                                %>
                                            </div>
                                        </div>
                                    </div>
                                </div>



                            </div>
                                              
                                        <div style="background-color: red; ">
                                            <h1>This is test</h1> 
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
