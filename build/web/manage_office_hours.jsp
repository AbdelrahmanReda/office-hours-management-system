<%-- 
    Document   : manage_office_hours
    Created on : Jan 3, 2021, 2:08:05 PM
    Author     : boody
--%>

<%@page import="Models.OfficeHours"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="staff.OfficeHoursController"%>
<%@page import="Helpers.SessionController"%>
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
                                <div class="col-lg-12 pt-3">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Day</th>
                                                <th scope="col">Slot</th>
                                                <th scope="col">From</th>
                                                <th scope="col">To</th>
                                                <th scope="col">Operation</th>


                                            </tr>
                                        </thead>
                                        <tbody id="office_hourse_data">

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
                                        <div class="form-group">
                                            <label for="day">Slot</label>
                                            <select class="form-control"  name="slot" id="day">
                                                <option value="1">First Slot</option>
                                                <option value="2">Second Slot</option>
                                                <option value="3">Third Slot</option>
                                                <option value="4" >Fourth Slot</option>


                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="day">Day</label>
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
<script>  $('.Manage-office-hours').toggleClass('activeElement');</script>
<script>
    function jsonToURI(p) {
        text = ""
        for (var key in p) {
            if (p.hasOwnProperty(key)) {
                text = text + key + "=" + p[key] + "&"
            }
        }
        return text;
    }
    function update(id) {
        data = {
            operation: "update",
            id: id
        };
        $.ajax({
            url: 'OfficeHoursController',
            method: 'POST',
            data: jsonToURI(data),
            success: function (resultText) {
                console.log(resultText);
                buildTable(resultText);
            },
            error: function (jqXHR, exception) {
                console.log('Error occured!!');
            }
        });
    }
    function deleteOfficeHour(id) {
        data = {
            operation: "delete",
            id: id
        };
        $.ajax({
            url: 'OfficeHoursController',
            method: 'POST',
            data: jsonToURI(data),
            success: function (resultText) {
                getOfficeHourse();
            },
            error: function (jqXHR, exception) {
                console.log('Error occured!!');
            }
        });
    }
    function buildTable(jsonObject) {
        $('#office_hourse_data').empty();
        for (var i = 0; i < jsonObject.length; i++) {
            $('#office_hourse_data').append(
                    '<tr> <td>'
                    + (i + 1)
                    + '</td> <td>'
                    + jsonObject[i]['day']
                    + '</td> <td>'
                    + jsonObject[i]['slot']['slot_name']
                    + '</td> <td>'
                    + jsonObject[i]['slot']['from_hour']
                    + '</td> <td>'
                    + jsonObject[i]['slot']['to_hour']
                    + '</td> <td style = \"display: flex; justify-content: space-evenly;\" >'
                    + '<button type="submit" onclick="deleteOfficeHour(' + jsonObject[i]['id'] + ') " class="btn btn-danger">Delete</button>'
                    + '<button type="submit" onclick="update(' + jsonObject[i]['id'] + ') " class="btn btn-success">Update</button> </td>'
                    + '</tr>'
                    );
        }
    }
    getOfficeHourse();
    function getOfficeHourse() {
        $.ajax({
            url: 'OfficeHoursController',
            method: 'POST',
            data: $('#select-form').serialize(),
            success: function (resultText) {
                buildTable(resultText);
            },
            error: function (jqXHR, exception) {
                console.log('Error occured!!');
            }
        });
    }
    function callJqueryAjax() {
        $.ajax({
            url: 'OfficeHoursController',
            method: 'POST',
            data: $('#registrationForm').serialize(),
            success: function (resultText) {
                getOfficeHourse();
            },
            error: function (jqXHR, exception) {
                console.log('Error occured!!');
            }
        });
    }
</script>




