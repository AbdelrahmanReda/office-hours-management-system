<%-- 
    Document   : changeInfo
    Created on : Dec 28, 2020, 3:50:11 AM
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
    </head>
    <body>
        <%
            String studentEmail = (String) session.getAttribute("email");
            String url = "jdbc:mysql://localhost:3306/office_hours";
            Connection con = null;
            Statement Stmt = null;
            ResultSet RS = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, "root", "1234");
                Stmt = (Statement) con.createStatement();
                RS = Stmt.executeQuery("SELECT * FROM student where email ='" + studentEmail + "' ");
        %>
        <h1 style="text-align:center">
            Your Information
        </h1>
        <form action="changeInfo" method="POST">
            <table border=1 align=center style="text-align:center">
                <thead>
                </thead>
                <tbody>
                    <%
                        while (RS.next()) {
                    %>
                    <tr>
                        <th>Email</th>
                        <td><%=RS.getString("email")%></td>
                        <td><input type="email" placeholder="Email" name="email"></td>
                    </tr>
                    <tr>
                        <th>Password</th>
                        <td><%=RS.getString("password")%></td>
                        <td><input type="password" placeholder="Password" name="password"></td>
                    </tr>
                    <tr>
                        <th>Gender</th>
                        <td><%=RS.getString("gender")%></td>
                        <td><input type="text" placeholder="Gender" name="gender"></td>
                    </tr>
                    <tr>
                        <th>Country</th>
                        <td><%=RS.getString("country")%></td>
                        <td><input type="text" placeholder="Country" name="country"></td>
                    </tr>
                    <tr>
                        <th></th>
                        <td><input type="submit" placeholder="Change" style="text-align:center"></td>
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
    </body>
</html>