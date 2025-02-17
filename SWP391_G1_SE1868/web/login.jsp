<%-- 
    Document   : login
    Created on : Feb 17, 2025, 11:50:12 AM
    Author     : Đạt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Bootstrap JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

    </head>
    <body>

        <div class="container d-flex justify-content-center align-items-center vh-100">
            <div class="card shadow p-4" style="max-width: 450px; width: 100%;">
                <!-- Tabs -->
                <ul class="nav nav-pills nav-justified mb-3" id="pills-tab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="tab-login" data-bs-toggle="pill" href="#pills-login" role="tab"
                           aria-controls="pills-login" aria-selected="true">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="tab-register" data-bs-toggle="pill" href="#pills-register" role="tab"
                           aria-controls="pills-register" aria-selected="false">Register</a>
                    </li>
                </ul>

                <!-- Tab Content -->
                <div class="tab-content">
                    <!-- Login Form -->
                    <div class="tab-pane fade show active" id="pills-login" role="tabpanel">
                        <form method="post">


                            <div class="form-outline mb-3">
                                <label class="form-label" for="loginName">Email or username</label>
                                <input type="email" id="loginName" class="form-control" />
                            </div>

                            <div class="form-outline mb-3">
                                <label class="form-label" for="loginPassword">Password</label>
                                <input type="password" id="loginPassword" class="form-control" />

                            </div>

                            <div class="d-flex justify-content-between mb-3">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="loginCheck" checked />
                                    <label class="form-check-label" for="loginCheck">Remember me</label>
                                </div>
                                <a href="#">Forgot password?</a>
                            </div>

                            <button type="submit" class="btn btn-primary w-100">Sign in</button>



                        </form>
                    </div>


                    <!-- Register Form -->
                    <div class="tab-pane fade" id="pills-register" role="tabpanel">
                        <form method="post" onsubmit="return validateForm()">
                            <div class="form-outline mb-3">
                                <label class="form-label" for="name">Name</label>
                                <input type="text" id="name" class="form-control" />
                                <div id="nameError" class="invalid-feedback">Name must be filled out</div>
                            </div>

                            <div class="form-outline mb-3">
                                <label class="form-label" for="phone">Phone</label>
                                <input type="text" id="phone" class="form-control" />
                                <div id="phoneError" class="invalid-feedback">Phone number must be 10 digits</div>
                            </div>

                            <div class="form-outline mb-3">
                                <label class="form-label" for="email">Email</label>
                                <input type="text" id="email" class="form-control" />
                                <div id="emailError" class="invalid-feedback">Please enter a valid email address</div>
                            </div>

                            <div class="form-outline mb-3">
                                <label class="form-label" for="address">Address</label>
                                <input type="text" id="address" class="form-control" />
                                <div id="addressError" class="invalid-feedback">Address must be filled out</div>
                            </div>

                            <div class="form-outline mb-3">
                                <label class="form-label" for="password">Password</label>
                                <input type="password" id="password" class="form-control" />
                                <div id="passwordError" class="invalid-feedback">Password must be at least 8 characters long, with at least one special character and one number.</div>
                            </div>

                            <div class="form-outline mb-3">
                                <label class="form-label" for="repeatPassword">Repeat password</label>
                                <input type="password" id="repeatPassword" class="form-control" />
                                <div id="repeatPasswordError" class="invalid-feedback">Please confirm your password</div>
                            </div>

                            <div class="form-check d-flex justify-content-center mb-3">
                                <input class="form-check-input me-2" type="checkbox" id="check" checked />
                                <label class="form-check-label" for="check">I agree to the terms</label>
                            </div>

                            <button type="submit" class="btn btn-primary w-100">Register</button>
                        </form>

                        <script>
                            function validateForm() {
                                // Ẩn tất cả thông báo lỗi trước khi kiểm tra
                                var errorFields = document.querySelectorAll('.invalid-feedback');
                                errorFields.forEach(function (field) {
                                    field.style.display = 'none';
                                    field.parentElement.querySelector('.form-control').classList.remove('is-invalid');
                                });

                                var isValid = true;

                                // Kiểm tra tên
                                var name = document.getElementById("name").value;
                                if (name == "") {
                                    document.getElementById("nameError").style.display = "block";
                                    document.getElementById("name").classList.add("is-invalid");
                                    isValid = false;
                                }

                                // Kiểm tra số điện thoại (chỉ chứa số)
                                var phone = document.getElementById("phone").value;
                                var phonePattern = /^[0-9]{10}$/;
                                if (!phone.match(phonePattern)) {
                                    document.getElementById("phoneError").style.display = "block";
                                    document.getElementById("phone").classList.add("is-invalid");
                                    isValid = false;
                                }

                                // Kiểm tra email
                                var email = document.getElementById("email").value;
                                var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
                                if (!email.match(emailPattern)) {
                                    document.getElementById("emailError").style.display = "block";
                                    document.getElementById("email").classList.add("is-invalid");
                                    isValid = false;
                                }

                                // Kiểm tra địa chỉ
                                var address = document.getElementById("address").value;
                                if (address == "") {
                                    document.getElementById("addressError").style.display = "block";
                                    document.getElementById("address").classList.add("is-invalid");
                                    isValid = false;
                                }

                                // Kiểm tra mật khẩu (ít nhất 8 ký tự, có ký tự đặc biệt và số)
                                var password = document.getElementById("password").value;
                                var passwordPattern = /^(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{8,}$/;
                                if (!password.match(passwordPattern)) {
                                    document.getElementById("passwordError").style.display = "block";
                                    document.getElementById("password").classList.add("is-invalid");
                                    isValid = false;
                                }

                                // Kiểm tra xác nhận mật khẩu
                                var repeatPassword = document.getElementById("repeatPassword").value;
                                if (repeatPassword == "") {
                                    document.getElementById("repeatPasswordError").style.display = "block";
                                    document.getElementById("repeatPassword").classList.add("is-invalid");
                                    isValid = false;
                                }

                                // Kiểm tra mật khẩu và xác nhận mật khẩu trùng nhau
                                if (password !== repeatPassword) {
                                    document.getElementById("repeatPasswordError").style.display = "block";
                                    document.getElementById("repeatPassword").classList.add("is-invalid");
                                    isValid = false;
                                }

                                // Kiểm tra điều khoản (checkbox)
                                var check = document.getElementById("check").checked;
                                if (!check) {
                                    alert("You must agree to the terms");
                                    isValid = false;
                                }

                                return isValid; // Nếu tất cả hợp lệ, form sẽ được gửi đi
                            }

                            // Sự kiện khi người dùng nhập lại dữ liệu
                            var inputs = document.querySelectorAll('.form-control');
                            inputs.forEach(function (input) {
                                input.addEventListener('input', function () {
                                    var errorDiv = input.parentElement.querySelector('.invalid-feedback');
                                    if (input.value.trim() !== "" && errorDiv) {
                                        errorDiv.style.display = 'none';
                                        input.classList.remove('is-invalid');
                                    }
                                });
                            });
                        </script>


                    </div>
                </div>
            </div>
        </div>

        <style>
            body {
                background-color: #e3f2fd; /* Màu xanh nhạt */
            }
        </style>

    </body>
</html>
