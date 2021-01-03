<%-- 
    Document   : veiwMessages
    Created on : Dec 28, 2020, 8:47:37 PM
    Author     : toqa khaled
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Message</title>
    </head>
    <body>
        <%
            String studentEmail = (String) session.getAttribute("email");
            boolean status = false;
            String url = "jdbc:mysql://localhost:3306/office_hours";
            Connection con = null;
            Statement Stmt = null;
            ResultSet RS = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, "root", "1234");
                Stmt = (Statement) con.createStatement();
                RS = Stmt.executeQuery("SELECT * FROM message where toMail ='" + studentEmail + "' ");
        %>
        <h1 style="text-align:center">
            Your Mails
        </h1>
        <table border=1 align=center style="text-align:center">
            <thead>
                <tr>
                    <th>From</th>
                    <th>Mail Content</th>
                </tr>
            </thead>
            <tbody>
                <%while (RS.next()) {
                %>
                <tr>
                    <td><%=RS.getString("fromMail")%></td>
                    <td><%=RS.getString("subject")%></td>
                </tr>
                <% } %>
            </tbody>
        </table><br>
        <%
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        %>

        <a href="sendMessage.html"> send </a>
    </body>
</html>
