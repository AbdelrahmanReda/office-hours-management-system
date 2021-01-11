<%-- 
    Document   : navbar
    Created on : Dec 26, 2020, 1:32:12 AM
    Author     : boody
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav class="navbar navbar-expand-md ">
    <div class="container-fluid">
        <button type="button" id="sidebarCollapse" class="btn btn-info">
            <i class="fas fa-align-left"></i>

        </button>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item dropdown" style="">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                       <%  out.print(request.getSession().getAttribute("username"));%>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                       <a class="dropdown-item" href="changeInfo">Change Info</a>
                       
                        <div class="dropdown-divider"></div>
                         <a class="dropdown-item" href="logOut">Log out</a>
                    </div>
                </li>
                          
            </ul>
        </div>
    </div>
</nav>