<%-- 
    Document   : sidebar
    Created on : Dec 25, 2020, 11:02:50 PM
    Author     : boody
--%>

<%@ page import="staff.OfficeHoursController" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav id="sidebar">
    <div class="sidebar-header">
        <h3>welcome </h3>
    </div>
    <ul class="list-unstyled components">
        <%  
        
            if (session.getAttribute("user_type")==("student")) {%>

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
            <a class="Transactins" href="contact.jsp">Staff E-mails</a>
        </li>
        <li>
            <a class="Transactins" href="officehourse.jsp">Staff office hours</a>
        </li>
        <%}%>
        <% if (session.getAttribute("user_type")==("staff_member")) {%>
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