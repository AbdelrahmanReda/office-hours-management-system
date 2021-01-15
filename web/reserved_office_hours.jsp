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
                                <%if (session.getAttribute("user_type").equals("staff_member")) {%>
                                <div class="col-lg-12" >
                                    <form class="needs-validation" action="AppointmentController" method="POST" >
                                        <div class="form-row">
                                            <div class="col-md-9 mb-3">
                                                <label for="exampleFormControlSelect1">Reservation day</label>
                                                <select class="form-control"  name="reservation_id" id="day">
                                                    <option>SATURDAY</option>
                                                    <option>SUNDAY</option>
                                                    <option>MONDAY</option>
                                                    <option>TUESDAY</option>
                                                    <option>WEDNESDAY</option>
                                                    <option>THURSDAY</option>
                                                    <option>FRIDAY</option>
                                                </select>
                                            </div>
                                            <div class="col-md-3 mb-3" style="display: flex; flex-direction: column-reverse;">
                                                <input type="hidden" name="operation" value="delete">
                                                <button class="btn btn-danger" type="submit">Cancel Reservation</button>
                                            </div>
                                        </div>
                                    </form> 
                                </div>
                                <%}%>
                                <div class="col-lg-12">
                                    <table id="example" class="table table-striped table-bordered" style="width:100%">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Day</th>
                                                <th>Slot</th>
                                                <th>from</th>
                                                <th>to</th>
                                                    <%if (session.getAttribute("user_type").equals("staff_member")) {%>
                                                <th>Student</th>
                                                <th>Student Mail</th>
                                                <th>Level</th>
                                                    <%}
                                                    else{
                                                    out.print("<th>Staff</td>");
                                                    }
                                                    %>
                                                <th>Operation</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                ArrayList<Appointment> list = (ArrayList<Appointment>) request.getAttribute("appointments");
                                                for (int i = 0; i < list.size(); i++) {
                                                    out.print("<tr>");
                                                    out.print("<td>" + (i + 1) + "</td>");
                                                    out.print("<td>" + list.get(i).officeHours.day + "</td>");
                                                    out.print("<td>" + list.get(i).officeHours.slot.slot_name + "</td>");
                                                    out.print("<td>" + list.get(i).officeHours.slot.from_hour + "</td>");
                                                    out.print("<td>" + list.get(i).officeHours.slot.to_hour + "</td>");
                                                    if (session.getAttribute("user_type").equals("staff_member")){
                                                    out.print("<td>" + list.get(i).student.user_name + "</td>");
                                                    out.print("<td>" + list.get(i).student.mail + "</td>");
                                                    out.print("<td>" + list.get(i).student.student_level + "</td>");
                                                    }
                                                    else
                                                    {
                                                         out.print("<td>" + list.get(i).staff.first_name+" "+list.get(i).staff.last_name + "</td>");
                                                    }
                                                    out.print("<td> "); %>
                                        <form action="AppointmentController" method="POST" style="display: flex; justify-content: center;">
                                            <input type="hidden" name="operation" value="delete">
                                            <input type="hidden" name="reservation_id" value= <% out.print(list.get(i).id); %> >
                                            <button class="btn btn-danger" > Delete </button>
                                        </form>
                                        <%out.print("</td></td>");}%>  
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

