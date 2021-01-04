<%-- 
    Document   : student-info
    Created on : Jan 4, 2021, 4:36:18 PM
    Author     : boody
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
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
                        <h3>Student Personal Information</h3>
                    </div>
                    <div class="card-body">
                        <div class="container-fluid" >
                            <div class="row" >

                                <div class="col-lg-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <h5>Search Student</h5>
                                        </div>
                                        <div class="card-body">
                                            <form method="POST" action="StudentController">

                                                <div class="form-group">
                                                    <label for="student-id">Student id</label>
                                                    <input type="number" class="form-control" id="student-id" name="student_id" placeholder="20170145">
                                                </div>

                                                <button type="submit" class="btn btn-primary">Search for Student</button>
                                            </form>
                                        </div>

                                    </div>
                                </div>

                                <div class="" > </div>





                                <div class="col-lg-12 mt-4">
                                    <div class="card">
                                        <div class="card-header">
                                            <h5>Student Personal Information</h5>
                                        </div>
                                        <div class="card-body">
                                            ${studentInforamtion.getUser_name()}
                                            ${studentInforamtion.getMail()}
                                            ${studentInforamtion.getGender()}
                                            ${studentInforamtion.getCountry()}
                                            ${studentInforamtion.getStudent_level()}
                                            ${studentInforamtion.getStudent_gpa()}
                                        </div>
                                    </div>

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
<script>  $('.student').toggleClass('activeElement');</script>