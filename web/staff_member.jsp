<%-- 
    Document   : staff_member
    Created on : Jan 9, 2021, 11:19:00 AM
    Author     : boody
--%>

<%@page import="Models.OfficeHours"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<body>
    <%@include file="layout/header.jsp" %> 
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

                    <table id="example" class="table table-striped table-bordered" style="width:100%">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Day</th>
                                <th>Slot</th>
                                <th>From</th>
                                <th>To</th>
                                <th>operation</th>

                            </tr>
                        </thead>
                        <tbody>

                            <%
                                if (request.getAttribute("OfficeHours") != null) {

                                    ArrayList<OfficeHours> list = (ArrayList<OfficeHours>) request.getAttribute("OfficeHours");
                                    for (int i = 0; i < list.size(); i++) {
                                        out.print("<tr><td>" + (i + 1) + "</td>");
                                        out.print("<td>" + list.get(i).day + "</td>");
                                        out.print("<td>" + list.get(i).slot.slot_name + "</td>");
                                        out.print("<td>" + list.get(i).slot.from_hour + "</td>");
                                        out.print("<td>" + list.get(i).slot.to_hour + "</td>");
                                        out.print("<td> <a href = /Office_Hours/OfficeHoursController?office_hour_id="+ list.get(i).id +"?staff_id="+list.get(i).staff.id + " \" class=\"badge badge-primary\">Primary</a> </tr></td>");

                                    }
                                }

                            %>

                        </tbody>
                    </table>









                </div>
            </div>
        </div>
    </div>


</body>

<%@ include file = "layout\scripts.jsp" %>
<script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js" ></script>
<script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js" ></script>
<script>
    $(document).ready(function () {
        $('#example').DataTable();
    });

</script>
<script>  $('.staff-member').toggleClass('activeElement');</script>