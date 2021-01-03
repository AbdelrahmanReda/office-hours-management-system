<%-- 
    Document   : viewAppointment
    Created on : Dec 31, 2020, 11:41:07 PM
    Author     : toqa khaled
--%>
<%@page import="javax.mail.Session"%>
<%@page import="java.sql.Timestamp"%>
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
             <a href="veiwMessages.jsp" >My Appointment</a>
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
       
    </body>
    <%
        String name = request.getParameter("search");
        HttpSession s = request.getSession();
        s.setAttribute("staffName", name);
        String staffEmail= (String) session.getAttribute("email");
        
        boolean status = false;
        String url = "jdbc:mysql://localhost:3306/office_hours";
        Connection con = null;
        Statement Stmt = null;
        ResultSet RS = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "1234");
            Stmt = (Statement) con.createStatement();
            RS = Stmt.executeQuery("SELECT * FROM appointment where staffEmail ='" + staffEmail + "' ");
    %>
    <form action="appointment" method="POST">
        <table border=1 align=center style="text-align:center">
            <thead>
                <tr>
                   
                    <th>Office Hours</th>
                     <th>Student</th>
                    
                    
                </tr>
            </thead>
            <tbody>
                <%while (RS.next()) {
                       
                %>
                <tr>
                    <td id="staffName"><%=RS.getTimestamp("appoint")%></td>
                    <td><%=RS.getString("studentEmail")%></td>
                    <td>
                        <button type="submit" name="appoint" >View Reservation</button>
                    </td> 
                   
                </tr>
                <%}%>
            </tbody>
        </table>
    </form>
    <%
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    %>
     
</html>
