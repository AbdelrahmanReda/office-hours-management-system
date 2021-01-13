<%-- 
    Document   : sidebar
    Created on : Dec 25, 2020, 11:02:50 PM
    Author     : boody
--%>

<%@page import="Helpers.SessionController"%>
<%@ page import="staff.OfficeHoursController" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav id="sidebar">
    <%
        if (SessionController.getSessionAtrributeValue(request, "user_type")==null){
        
        System.out.println(" ana null");
        response.sendRedirect("login.jsp");
        return;
        }
    
    %>
    <div class="sidebar-header">
        <h3>welcome </h3>
    </div>
    <ul class="list-unstyled components">
        <%  
            if (SessionController.getSessionAtrributeValue(request, "user_type").equals("student")) {%>
        <li>
            <a class="Dashboard" href="DashboardController">Home</a>
        </li>
        <li>
            <a class="find-staff" href="StaffController">Find Staff</a>
        </li>
        <li>
            <a class="staff-member" href="${pageContext.request.contextPath}/StaffController?getStaffMembers=true">Staff Members</a>
        </li>
        <li>
            <a class="reserved-office-hours" href="AppointmentController">Reserved Office Hours</a>
        </li>
         <li>
            <a class="compose-mail" href="MailController">Compose Mail</a>
        </li>
        <%}%>
        <% 
            if (SessionController.getSessionAtrributeValue(request, "user_type").equals("staff_member")) {%>
        <li>
            <a class="inbox" href="InboxController">Inbox</a>
        </li>
        <li>
            <a class="student" href="StudentController">Students</a>
        </li>
        <li>
            <a class="reserved-office-hours" href="AppointmentController">Reserved Office Hours</a>
        </li>
        <li>
            <a class="Manage-office-hours" href="manage_office_hours.jsp">Manage office hours</a>
        </li>
        <li>
            <a class="compose-mail" href="MailController">Compose Mail</a>
        </li>

        <%}%>
    </ul>
</nav>