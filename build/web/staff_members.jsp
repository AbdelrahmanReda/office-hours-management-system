<%-- 
    Document   : staff_members
    Created on : Jan 9, 2021, 12:43:11 AM
    Author     : boody
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Models.Staff"%>
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
                                <th>Staff Name</th>
                                <th>Department</th>
                                <th>Mail</th>

                            </tr>
                        </thead>
                        <tbody>
                            

                            <%
                                if (request.getAttribute("staffMembers") != null) {

                                    ArrayList<Staff> list = (ArrayList<Staff>) request.getAttribute("staffMembers");
                                    for (int i = 0; i < list.size(); i++) {
                                        out.print("<tr><td>" + (i + 1) + "</td>");
                                        out.print("<td><a  style=\"text-decoration:underline\" href = /Office_Hours/OfficeHoursController?getStaffMembers=" + list.get(i).id +" >" + list.get(i).user_name + "</a></td>");
                                        out.print("<td>" + list.get(i).dapartment + "</td>");
                                        out.print("<td><a style=\"text-decoration:underline\" href = \"\" >" + list.get(i).mail + "</td></tr>");
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
