<%-- 
    Document   : invalide_registration
    Created on : Jan 15, 2021, 5:00:34 PM
    Author     : boody
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
 if (session.getAttribute("invalid_data") != null) {
%>
<div class="alert alert-danger" role="alert">
    Invalid Registration Data
</div>                            
<%}
    session.removeAttribute("invalid_data");
%>


