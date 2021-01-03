<%-- 
    Document   : studentInfo
    Created on : Dec 31, 2020, 11:21:40 PM
    Author     : toqa khaled
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="style.css">
         <script src="App.js" type ="text/javascript"></script>
        <title>student Info</title>
    </head>
   <body>

       <div class="topnav" id="myTopnav">
            <a href="staff.html" class="active">Home</a>
            <a href="veiwMessages.jsp" >Message</a>
            <a href="studentInfo.jsp" >Student Info</a>
            <a href="viewAppointment.jsp" >My Appointment</a>
            <a href="#" class="icon" onclick="myFunction()">
                <i class="fa fa-bars"></i>
            </a>
            <div class="dropdown" id="topnav-right">
                <button class="dropbtn" id="myTopnav">Setting 
                    <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content" >
                    <a href="logOut">Log-Out</a>
                    <a href="changeInfo.jsp">Change Info</a>
                </div>
            </div>  
        </div>

        <form class="example"  style="margin:auto;max-width:300px">
            <input type="text" placeholder="Search for email.." name="search">
            <button type="submit"><i class="fa fa-search"></i></button>
        </form>

        <p id="searchBar"></p>
        <p id="show"></p>

        <%

            String name = request.getParameter("search");
            boolean status = false;
            String url = "jdbc:mysql://localhost:3306/office_hours";
            Connection con = null;
            Statement Stmt = null;
            ResultSet RS = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, "root", "1234");
                Stmt = (Statement) con.createStatement();
                RS = Stmt.executeQuery("SELECT * FROM student where username ='" + name + "' ");
        %>

        <table border=1 align=center style="text-align:center">
            <thead>
                <tr>
                    <th>User Name</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <%while (RS.next()) {
                %>
                <tr>
                    <td><%=RS.getString("username")%></td>
                    <td><%=RS.getString("email")%></td>

                </tr>
                <%}%>
            </tbody>
        </table><br>
        <%
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        %>
    </body>
</html>
