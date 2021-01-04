<%-- 
    Document   : reserved_office_hours
    Created on : Jan 4, 2021, 12:25:29 PM
    Author     : boody
--%>

<%@page import="Models.Appointment"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>


    <%@include file="layout/header.jsp" %> 
    <body>

        <div class="wrapper">
            <!-- Sidebar  -->
            <%@ include file = "layout\sidebar.jsp" %>
            <!-- Page Content  -->
            <div id="content">
                <%@ include file = "layout\navbar.jsp" %>




                <div class="card">
                    <div class="card-header">
                        <form id="select-form"><input type="hidden" name="operation" value="selection" ></form>
                        <h3>Office Hours Management</h3>
                    </div>
                    <div class="card-body">
                        <div class="container-fluid" >
                            <div class="row" >
                                <div class="col-lg-12">
                                    <button type="button" style="float: right" class="btn btn-success btn-xs"  data-toggle="modal" data-target="#exampleModal">Add New Office Hour<span class="glyphicon glyphicon-plus"></span></button>
                                </div>
                                <div class="col-lg-12">

                                    <table id="example" class="table table-striped table-bordered" style="width:100%">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Day</th>
                                                <th>Time</th>
                                                <th>Student Name</th>
                                                <th>Student Mail</th>
                                                <th>Student Level</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                // retrieve your list from the request, with casting 
                                                ArrayList<Appointment> list = (ArrayList<Appointment>) request.getAttribute("appointments");

                                                // print the information about every category of the list
                                                for (int i = 0; i < list.size(); i++) {
                                                    out.print("<tr>");
                                                    out.print("<td>"+(i+1)+"</td>");
                                                    out.print("<td>"+list.get(i).officeHours.day+"</td>");
                                                    out.print("<td>"+list.get(i).officeHours.time+"</td>");
                                                    out.print("<td>"+list.get(i).student.user_name+"</td>");
                                                    out.print("<td>"+list.get(i).student.mail+"</td>");
                                                    out.print("<td>"+list.get(i).student.student_level+"</td>");
                                                    out.print("</td>");

                                                }

                                            %>  

                                        </tbody>

                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>


                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Add new office hour</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form id="registrationForm">
                                        <input type="hidden" name="operation" value="insertion">
                                        <div class="form-group row">
                                            <label for="example-time-input" class="col-2 col-form-label">Time</label>
                                            <div class="col-12">
                                                <input class="form-control" name="time" type="time" value="13:45:00" id="date">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="day">Example select</label>
                                            <select class="form-control"  name="day" id="day">
                                                <option>Saturday</option>
                                                <option>Sunday</option>
                                                <option>Monday</option>
                                                <option>Tuesday</option>
                                                <option>Wednesday</option>
                                                <option>Thursday</option>
                                                <option>Friday</option>

                                            </select>
                                        </div>


                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-success" onclick="callJqueryAjax()">Add Office Hour</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
<%@ include file = "layout\scripts.jsp" %>


<script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js" ></script>
<script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js" ></script>
<script>
                                        $(document).ready(function () {
                                            $('#example').DataTable();
                                        });

</script>
<script>  $('.reserved-office-hours').toggleClass('activeElement');</script>
