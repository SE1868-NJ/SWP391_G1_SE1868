<%-- 
    Document   : register
    Created on : Feb 17, 2025, 1:19:09 PM
    Author     : Đạt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <div class="container d-flex justify-content-center align-items-center vh-100">
            <div class="card shadow p-4" style="max-width: 450px; width: 100%;">
                <!-- Register Form -->
                <form method="post" >
                    <div class="form-outline mb-3">
                        <label class="form-label" for="name">Name</label>
                        <input type="text" id="name" class="form-control" />
                         <div class="text-danger">${error_name}</div>
                    </div>

                    <div class="form-outline mb-3">
                        <label class="form-label" for="phone">Phone</label>
                        <input type="text" id="phone" class="form-control" />
                         <div class="text-danger">${error_phone}</div>
                    </div>

                    <div class="form-outline mb-3">
                        <label class="form-label" for="email">Email</label>
                        <input type="text" id="email" class="form-control" />
                         <div class="text-danger">${error_email}</div>
                    </div>

                    <div class="form-outline mb-3">
                        <label class="form-label" for="password">Password</label>
                        <input type="password" id="password" class="form-control" />
                         <div class="text-danger">${error_password}</div>
                    </div>

                    <div class="form-outline mb-3">
                        <label class="form-label" for="repeatPassword">Repeat password</label>
                        <input type="password" id="repeatPassword" class="form-control" />
                         <div class="text-danger">${error_repeatPassword}</div>
                    </div>

                    <div class="form-check d-flex justify-content-center mb-3">
                        <input class="form-check-input me-2" type="checkbox" id="check" checked />
                        <label class="form-check-label" for="check">I agree to the terms</label>
                    </div>

                    <button type="submit" class="btn btn-primary w-100">Register</button>
                </form>
                <!-- Link to switch to Login form -->
                <div class="text-center mt-3">
                    <span>Already have an account? </span>
                    <a href="login.jsp" class="link-primary text-decoration-none" >Login</a>
                </div>

            </div>
        </div>
    </body>
</html>
