<%-- 
    Document   : appointment
    Created on : Dec 27, 2020, 5:17:47 PM
    Author     : toqa khaled
--%>
<%@page import="javax.mail.Session"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.SQLException"%>
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
            //String appointment = request.getParameter("appoint");
         
            //String staffName = (String) session.getAttribute("staffemil");
            //out.print(staffName);
            //String StudentEmail = (String) session.getAttribute("email");
            String transaction = request.getParameter("appoint");
            out.print(transaction);
            
String split[] = transaction.split(",");
out.print(split[0]);
           
            
           
        
        %>
    </body>
</html>
