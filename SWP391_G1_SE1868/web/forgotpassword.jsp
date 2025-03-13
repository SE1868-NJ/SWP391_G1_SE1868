<%-- 
    Document   : forgotpassword
    Created on : Feb 21, 2025, 9:40:37 PM
    Author     : Đạt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot PassWord</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    </head>
    <body>
        <section class="bg-light min-vh-100 d-flex justify-content-center align-items-center py-3 py-md-5">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12 col-sm-10 col-md-8 col-lg-6 col-xl-5 col-xxl-4">
                        <div class="card border border-light-subtle rounded-3 shadow-sm w-100">
                            <div class="card-body p-3 p-md-4 p-xl-5">
                                <div class="text-center mb-3">
                                    <a href="#!">
                                        <img src="./assets/img/login.png" alt="BootstrapBrain Logo" width="175" >
                                    </a>
                                </div>
                                <h2 class="fs-6 fw-normal text-center text-secondary mb-4">Forgot PassWord</h2>
                                <form action="forgotpassword" method="post">
                                    <div class="row gy-2">
                                        <div class="col-12">
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" name="email" id="email" placeholder="name@example.com" required>
                                                <label for="email" class="form-label">Email</label>
                                                <div class="text-danger">${error_email}</div>
                                            </div>
                                        </div>


                                        <div class="col-12">
                                            <div class="d-grid my-3">
                                                <button class="btn btn-primary btn-lg" type="submit">Submit</button>
                                            </div>

                                        </div>

                                        <div class="col-12">
                                            <p class="m-0 text-secondary text-center">Already have an account? <a href="login.jsp" class="link-primary text-decoration-none">Login</a></p>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    </body>
</html>
