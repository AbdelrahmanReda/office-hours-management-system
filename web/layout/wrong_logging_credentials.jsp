<%-- 
    Document   : wrong_logging_credentials
    Created on : Jan 10, 2021, 11:39:04 AM
    Author     : boody
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<%
    if (session.getAttribute("user_type")!=null){
    if (session.getAttribute("user_type").equals("student"))
    {
                    request.getRequestDispatcher("DashboardController").forward(request, response);

    }
    else
    {
                    request.getRequestDispatcher("DashboardController").forward(request, response);

    }
    }
    if (session.getAttribute("wrong_credentials") != null) {
%>
<div class="alert alert-danger" role="alert">
    Wrong Creditals Info
</div>                            
<%}
    session.removeAttribute("wrong_credentials");
%>
