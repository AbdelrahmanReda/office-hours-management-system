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
        <li>
            <a class="Dashboard" href="DashboardController">Home</a>
        </li>
        <li>
            <a class="Transactins" href="staffCourse.jsp">Courses</a>
        </li>
        <li>
            <a class="Transactins" href="veiwMessages.jsp">Messages</a>
        </li>  
        <li>
            <a class="Transactins" href="contact.jsp">Staff E-mails</a>
        </li>
        <li>
            <a class="Transactins" href="officehourse.jsp">Staff office hours</a>
        </li>
        
        <%
            
            if (session.getAttribute("user_type").equals("staff_member")) {%>
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
        
         <li>
            <a class="inbox" href="InboxController">Inbox</a>
        </li>
       
        

        <%}%>
    </ul>
</nav>