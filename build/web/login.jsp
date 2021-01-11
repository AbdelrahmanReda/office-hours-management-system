<%-- 
    Document   : login
    Created on : Jan 3, 2021, 8:54:45 AM
    Author     : boody
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <link rel="stylesheet" href="style2.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    </head>
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
