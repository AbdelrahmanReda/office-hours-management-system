<%-- 
    Document   : login
    Created on : Jan 3, 2021, 8:54:45 AM
    Author     : boody
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!DOCTYPE html>
<html>
    <%@include file="layout/header.jsp" %> 
    <body>
        <div class="container">
            <div class="row" >
                <div class="col-lg-12 " style="padding-top: 225px">
                    <div class="card login-form">
                        <div class="card-header">
                            <h6>Login in as student</h6>
                        </div>
                        <div class="card-body" >
                            <form   method="POST" action="validate" >
                                <%  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");%>
                                <%@ include file = "layout\wrong_logging_credentials.jsp" %>
                                <div class="mb-3">
                                    <label for="customer_id" class="form-label">Student Mail</label>
                                    <input type="mail" class="form-control  " id="customer_id" name="email">
                                </div>
                                <div class="mb-3">
                                    <label for="customer_password" class="form-label">Password</label>
                                    <input type="password" class="form-control " id="customer_password" name="password">
                                </div>
                                <input type="hidden"  name="user_type" value="student">
                                <button style="width: 100%" type="submit" class="btn btn-primary">Sign in</button>
                                <div class="d-flex justify-content-center align-items-center mt-2 flex-column">
                                    <br>
                                    <small text-center>Are you Staff Member?</small>                                   
                                    <a style="text-decoration: underline" href="staffLogin.jsp">Login in as Staff Member</a>
                                </div>
                            </form>    
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
