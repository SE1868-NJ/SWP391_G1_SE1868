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
                <form method="post" action="register" enctype="multipart/form-data">
                    <!-- Name -->
                    <div class="form-outline mb-3">
                        <label class="form-label" for="name">Full Name</label>
                        <input type="text" id="name" name="fullName" class="form-control" />
                        <div class="text-danger">${error_name}</div>
                    </div>

                    <!-- Email -->
                    <div class="form-outline mb-3">
                        <label class="form-label" for="email">Email</label>
                        <input type="email" id="email" name="email" class="form-control" />
                        <div class="text-danger">${error_email}</div>
                    </div>
                    <!-- Phone Number -->
                    <div class="form-outline mb-3">
                        <label class="form-label" for="phone">Phone Number</label>
                        <input type="text" id="phone" name="phoneNumber" class="form-control" />
                        <div class="text-danger">${error_phone}</div>
                    </div>

                    <!-- Address -->
                    <div class="form-outline mb-3">
                        <label class="form-label" for="address">Address</label>
                        <input type="text" id="address" name="address" class="form-control" />
                        <div class="text-danger">${error_address}</div>
                    </div>

                    <!-- Birth Date -->
                    <div class="form-outline mb-3">
                        <label class="form-label" for="birthDate">Birth Date</label>
                        <input type="date" id="birthDate" name="birthDate" class="form-control" />
                        <div class="text-danger">${error_birthDate}</div>
                    </div>

                    <!-- Gender -->
                    <div class="form-outline mb-3">
                        <label class="form-label">Gender</label>
                        <div class="d-flex">
                            <div class="form-check me-3">
                                <input class="form-check-input" type="radio" id="male" name="gender" value="Male">
                                <label class="form-check-label" for="male">Male</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" id="female" name="gender" value="Female">
                                <label class="form-check-label" for="female">Female</label>
                            </div>
                        </div>
                        <div class="text-danger">${error_gender}</div>
                    </div>



                    <!-- Password -->
                    <div class="form-outline mb-3">
                        <label class="form-label" for="password">Password</label>
                        <input type="password" id="password" name="password" class="form-control" />
                        <div class="text-danger">${error_password}</div>
                    </div>

                    <!-- Repeat Password -->
                    <div class="form-outline mb-3">
                        <label class="form-label" for="repeatPassword">Repeat Password</label>
                        <input type="password" id="repeatPassword" name="repeatPassword" class="form-control" />
                        <div class="text-danger">${error_repeatPassword}</div>
                    </div>


                    <!-- Terms & Conditions -->
                    <div class="form-check d-flex justify-content-center mb-3">
                        <input class="form-check-input me-2" type="checkbox" id="check" name="" checked />
                        <label class="form-check-label" for="check">I agree to the terms</label>
                        <div class="text-danger">${error_check}</div>
                    </div>

                    <!-- Submit Button -->
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
