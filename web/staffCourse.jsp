<%-- 
    Document   : staffCourse
    Created on : Dec 26, 2020, 9:40:13 PM
    Author     : toqa khaled
--%>

<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link rel="stylesheet" href="style.css">
         <script src="App.js" type ="text/javascript"></script>
    </head>
    
    <body>

        <div class="topnav" id="myTopnav">
            <a href="design.html" class="active">Home</a>
            <a href="staffCourse.jsp" >Courses</a>
            <a href="veiwMessages.jsp" >Message</a>
            <div class="dropdown">
                <button class="dropbtn" id="myTopnav">Contact 
                    <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <a href="contact.jsp">staff email</a>
                    <a href="officehourse.jsp">staff office hours</a>
                </div>
            </div> 
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

        <p id="searchBar"></p>
        <p id="show"></p>

       
        <form class="example"  style="margin:auto;max-width:300px">
            <input type="text" placeholder="Search for course.." name="search">
            <button type="submit"><i class="fa fa-search"></i></button>
        </form>


        <%
        
            String courseID = request.getParameter("search");
            boolean status = false;
            String url = "jdbc:mysql://localhost:3306/office_hours";
            Connection con = null;
            Statement Stmt = null;
            ResultSet RS = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, "root", "1234");
                Stmt = (Statement) con.createStatement();
                RS = Stmt.executeQuery("SELECT * FROM course where courseID ='" + courseID + "' OR courseName='" + courseID + "' ");
                status = RS.next();
                if (status) {
        %>
        <table border=1 align=center style="text-align:center">
            <thead>
                <tr>
                    <th>Course ID</th>
                    <th>Course Name</th>
                    <th>TA</th>

                </tr>
            </thead>
            <tbody>
                <%while (RS.next()) {
                %>
                <tr>
                    <td><%=RS.getString("courseID")%></td>
                    <td><%=RS.getString("courseName")%></td>
                    <td><%=RS.getString("staffName")%></td>
                </tr>
                <% } %>
            </tbody>
        </table><br>
        <% } 
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        %>
    </body>
</html>
