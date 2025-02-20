<%-- 
    Document   : login
    Created on : Feb 17, 2025, 11:50:12 AM
    Author     : Đạt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
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
                                <h2 class="fs-6 fw-normal text-center text-secondary mb-4">Sign in to your account</h2>
                                <form action="login" method="post">
                                    <div class="row gy-2">
                                        <div class="col-12">
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" name="email" id="email" placeholder="name@example.com" required>
                                                <label for="email" class="form-label">Email</label>
                                                <div class="text-danger">${error_email}</div>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="form-floating mb-3">
                                                <input type="password" class="form-control" name="password" id="password" value="" placeholder="Password" required>
                                                <label for="password" class="form-label">Password</label>
                                                <div class="text-danger">${error_password}</div>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="d-flex gap-2 justify-content-between">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" value="" name="rememberMe" id="rememberMe">
                                                    <label class="form-check-label text-secondary" for="rememberMe">
                                                        Keep me logged in
                                                    </label>
                                                </div>
                                                <a href="#!" class="link-primary text-decoration-none">Forgot password?</a>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <div class="d-grid my-3">
                                                <button class="btn btn-primary btn-lg" type="submit">Log in</button>
                                            </div>
                                            <div class="text-danger">${error}</div>
                                        </div>
                                        <div class="col-12">
                                            <p class="m-0 text-secondary text-center">Don't have an account? <a href="register.jsp" class="link-primary text-decoration-none">Sign up</a></p>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>




        <style>
            /* CSS chung cho popup */
            .popupp {
                position: fixed;
                bottom: 20px;
                right: 20px;
                padding: 15px 20px;
                border-radius: 8px;
                font-family: Arial, sans-serif;
                font-size: 16px;
                font-weight: bold;
                display: flex;
                align-items: center;
                gap: 10px;
                min-width: 350px;
                max-width: 400px;
                box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.3);
                opacity: 0;
                transform: translateX(100%);
                transition: opacity 0.4s ease, transform 0.4s ease;
                pointer-events: none;
                z-index: 9999;
            }

            /* Hiển thị popup */
            .popupp.show {
                opacity: 1;
                transform: translateX(0);
                pointer-events: auto;
            }

            /* Success Popup */
            .popupp.success {
                background-color: #4CAF50;
                color: white;
            }

            /* Error Popup */
            .popupp.error {
                background-color: #F44336;
                color: white;
            }

            /* Close Button */
            .popupp .close-btn {
                margin-left: auto;
                background: none;
                border: none;
                color: white;
                font-size: 18px;
                cursor: pointer;
            }
        </style>
        <!-- Popup Success -->
        <div id="successPopupp" class="popupp success" >
            <span>Vui lòng kiểm tra gmail để xác minh tài khoản.!</span>
            <button class="close-btn" onclick="closePopupp('successPopupp')">×</button>
        </div>

        <!-- Popup Error -->
        <div id="errorPopupp" class="popupp error ">
            <span > Error! Something went wrong.</span>
            <button class="close-btn " onclick="closePopupp('errorPopupp')">×</button>
        </div>

               
        <c:if test="${ not empty param.success}">
            <c:choose>

                <c:when test="${ param.success == 'true'}">
                    <script>
                        // Gọi hàm showPopup khi isSuccess = true
                        $(document).ready(function () {
                            showPopup('successPopupp');  // successPopup là ID của popup thành công
                        });

                        // Tự động ẩn popup sau một khoảng thời gian
                        closePopupp(function () {
                            closePopupp('successPopupp'); // Thay 'successPopupp' bằng ID của popup bạn muốn ẩn
                        }, 3000); // Ẩn popup sau 3 giây (3000ms)
                    </script>
                </c:when>

                <c:when test="${ param.success == 'false'}">
                    <script>
                        // Gọi hàm showPopup khi isSuccess = true
                        $(document).ready(function () {
                            showPopup('errorPopupp');  // successPopup là ID của popup thành công
                        });
                    </script>
                </c:when>

            </c:choose>
        </c:if>





        <script>
            // Hiển thị popup
            function showPopup(id) {
                let popup = document.getElementById(id);
                popup.classList.add("show");
            }



            // Đóng popup khi bấm nút close
            function closePopupp(id) {
                let popup = document.getElementById(id);
                popup.classList.remove("show");
            }
        </script>
        
    </body>
</html>
