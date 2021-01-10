<%-- 
    Document   : wrong_logging_credentials
    Created on : Jan 10, 2021, 11:39:04 AM
    Author     : boody
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    if (session.getAttribute("wrong_credentials") != null) {
%>
<div class="alert alert-danger" role="alert">
    This is a danger alert—check it out!
</div>                            
<%}
    session.removeAttribute("wrong_credentials");
%>
