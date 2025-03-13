<!DOCTYPE html>
<html lang="zxx">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ include file="header.jsp" %>
    <c:if test="${empty sessionScope.user}">
        <script>
            alert("Vui lòng đăng nhập để truy cập trang profile!");
            window.location.href = "login.jsp"; // Chuyển hướng đến trang login
        </script>
    </c:if>

    <head>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <meta name="description" content="Ogani Template">
        <meta name="keywords" content="Ogani, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <%@ page import="java.util.List" %>
        <%@ page import="java.text.SimpleDateFormat" %>
        <%@ page import="java.util.Date" %>
        <%@ page import="entity.Customer" %>


      

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

        <!-- Css Styles -->

        <
        <script src="https://cdn.tailwindcss.com"></script>
        <script>
        // Enable the fields for editing and focus the input when "Thay Đổi" is clicked
        document.querySelectorAll('.changeField').forEach(function (link) {
            link.addEventListener('click', function (event) {
                var field = event.target.getAttribute('data-field');
                var input = document.querySelector(`#${field}`);

                if (input) {
                    // Enable the input field
                    input.removeAttribute('disabled');
                    // Focus the input field
                    input.focus();
                }
            });
        });

        // Allow only numbers and limit to 10 digits for the phone input field
        document.number('input[type="number"]').forEach(function (input) {
            input.addEventListener('input', function (event) {
                // Remove any non-digit characters
                input.value = input.value.replace(/[^0-9]/g, '');

                // Limit the input to 10 characters
                if (input.value.length > 10) {
                    input.value = input.value.substring(0, 10); // Truncate to 10 characters
                }
            });
        });
        </script>

        <script>
            // Preview the image before upload
            function previewImage(event) {
                const file = event.target.files[0];
                if (file) {
                    if (file.size > 1024 * 1024) {
                        alert("Dung lượng file tối đa 1 MB.");
                        return;
                    }

                    const validFormats = ['image/jpeg', 'image/png'];
                    if (!validFormats.includes(file.type)) {
                        alert("Chỉ chấp nhận định dạng JPEG hoặc PNG.");
                        return;
                    }

                    const reader = new FileReader();
                    reader.onload = function (e) {
                        document.getElementById('profileImage').src = e.target.result;
                    };
                    reader.readAsDataURL(file);
                }
            }
        </script>
        <style>
            .input-with-button {
                display: flex;
                align-items: center;
            }
            .input-with-button input {
                flex: 1;
            }
            .input-with-button a {
                margin-left: -50px;
                padding-left: 50px;
                background-color: white;
                z-index: 10;
            }
            /* Styling the date picker dropdowns */
            .datepicker-dropdown {
                font-size: 1rem;
                padding: 0.5rem 1rem;
                border-radius: 0.375rem; /* rounded corners */
                border: 2px solid #d1d5db; /* Light gray border */
                background-color: #fff; /* white background */
                transition: border-color 0.3s ease;
                width: 100%; /* Ensure the select element takes full width */
                max-width: 150px; /* Limit max width for better design */
            }

            /* Spacing between dropdowns */
            .datepicker-dropdown + .datepicker-dropdown {
                margin-left: 0.5rem;
            }

            /* Hover and focus effects */
            .datepicker-dropdown:hover,
            .datepicker-dropdown:focus {
                border-color: #4CAF50; /* Green color on hover/focus */
                outline: none;
            }

            /* Label Styling */
            label {
                margin-bottom: 0.5rem;
            }

            /* Additional styling for the form container */
            .mb-4 {
                margin-bottom: 1rem;
            }

            /* Flexbox container for spacing the dropdowns */
            .flex {
                display: flex;
                gap: 10px;
            }
        </style>


    </head>

    <body>
       

        <!-- Hero Section End -->

        <!-- Breadcrumb Section Begin -->
    <body class="bg-gray-100">
        <div class="max-w-4xl mx-auto p-6 bg-white shadow-md mt-10">
            <h1 class="text-2xl font-semibold mb-2">Hồ Sơ Của Tôi</h1>

            <p class="text-gray-600 mb-6">Quản lý thông tin hồ sơ để bảo mật tài khoản</p>

            <!-- FORM UPDATE PROFILE -->
            <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
                <div class="md:col-span-2">
                    <!-- Email -->
                    <div class="mb-4">
                        <label class="block text-gray-700">Tên đăng nhập (Email)</label>
                        <input name="email" class="w-full border border-gray-300 p-2 rounded bg-gray-100" type="email" value="${sessionScope.user.email}" required="" />
                    </div>

                    <!-- Họ và Tên -->
                    <div class="mb-4">
                        <label class="block text-gray-700">Họ và Tên</label>
                        <input name="fullName" class="w-full border border-gray-300 p-2 rounded" type="text" value="${sessionScope.user.fullName}" required="" />
                    </div>

                    <!-- Số Điện Thoại -->
                    <div class="mb-4">
                        <label class="block text-gray-700">Số Điện Thoại</label>
                        <input name="phoneNumber" class="w-full border border-gray-300 p-2 rounded" type="tel" value="${sessionScope.user.phoneNumber}" required="" />
                    </div>

                    <!-- Địa Chỉ -->
                    <div class="mb-4">
                        <label class="block text-gray-700">Địa Chỉ</label>
                        <input name="address" class="w-full border border-gray-300 p-2 rounded" type="text" value="${sessionScope.user.address}" required="" />
                    </div>
                    <!-- Ngày Sinh -->
                    <div class="mb-4">
                        <label class="block text-gray-700">Ngày Sinh</label>
                        <input name="birthdate" class="w-full border border-gray-300 p-2 rounded" type="date="value="${sessionScope.user.birthDate}" required=""/>
                    </div>        
                    <!-- Giới tính  -->
                    <div class="mb-4">
                        <label class="block text-gray-700">Giới tính</label>
                        <input name="address" class="w-full border border-gray-300 p-2 rounded" type="text" value="${sessionScope.user.gender}" required="" />
                    </div>



                    <!-- Đổi mật khẩu -->
                    <div class="mb-4">
                        <label class="block text-gray-700">Đổi mật khẩu (không bắt buộc)</label>
                        <input name="currentPassword" class="w-full border border-gray-300 p-2 rounded mb-2" placeholder="Mật khẩu hiện tại" type="password" />
                        <input name="newPassword" class="w-full border border-gray-300 p-2 rounded mb-2" placeholder="Mật khẩu mới" type="password" />
                        <input name="confirmPassword" class="w-full border border-gray-300 p-2 rounded" placeholder="Xác nhận mật khẩu mới" type="password" />
                    </div>

                    <!-- Nút lưu cập nhật -->
                    <a href="updateporifile.jsp" class="bg-blue-500 text-white px-4 py-2 rounded">Chỉnh sửa hồ sơ</a>

                    <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">Logout</button>
                </div>

                <!-- Ảnh đại diện -->
                <div class="flex flex-col items-center">
                    <img alt="Profile picture" id="profileImage" class="rounded-full mb-4" height="100" src="${sessionScope.user.profileImage}" width="100" />

                    <!-- Upload hình ảnh -->
                    <input type="file" id="fileInput" name="profileImage" class="hidden" accept=".jpeg,.jpg,.png" onchange="previewImage(event)" />
                    <button type="button" class="bg-gray-200 text-gray-700 px-4 py-2 rounded" onclick="document.getElementById('fileInput').click();">Chọn Ảnh</button>

                    <p class="text-gray-500 mt-2 text-center">
                        Dung lượng file tối đa 1 MB <br />
                        Định dạng: .JPEG, .PNG
                    </p>
                </div>
            </div>

        </div>

        <!-- Xem trước hình ảnh khi chọn ảnh mới -->
        <script>
            function previewImage(event) {
                var reader = new FileReader();
                reader.onload = function () {
                    var output = document.getElementById('profileImage');
                    output.src = reader.result;
                };
                reader.readAsDataURL(event.target.files[0]);
            }
        </script>
    </body>



    <!-- Shoping Cart Section End -->

    <!-- Footer Section Begin -->
    <footer class="footer spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-6 col-sm-6">
                    <div class="footer__about">
                        <div class="footer__about__logo">
                            <a href="./index.html"><img src="assets/img/logo.png" alt=""></a>
                        </div>
                        <ul>
                            <li>Address: 60-49 Road 11378 New York</li>
                            <li>Phone: +65 11.188.888</li>
                            <li>Email: hello@colorlib.com</li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-6 offset-lg-1">
                    <div class="footer__widget">
                        <h6>Useful Links</h6>
                        <ul>
                            <li><a href="#">About Us</a></li>
                            <li><a href="#">About Our Shop</a></li>
                            <li><a href="#">Secure Shopping</a></li>
                            <li><a href="#">Delivery infomation</a></li>
                            <li><a href="#">Privacy Policy</a></li>
                            <li><a href="#">Our Sitemap</a></li>
                        </ul>
                        <ul>
                            <li><a href="#">Who We Are</a></li>
                            <li><a href="#">Our Services</a></li>
                            <li><a href="#">Projects</a></li>
                            <li><a href="#">Contact</a></li>
                            <li><a href="#">Innovation</a></li>
                            <li><a href="#">Testimonials</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-4 col-md-12">
                    <div class="footer__widget">
                        <h6>Join Our Newsletter Now</h6>
                        <p>Get E-mail updates about our latest shop and special offers.</p>
                        <form action="#">
                            <input type="text" placeholder="Enter your mail">
                            <button type="submit" class="site-btn">Subscribe</button>
                        </form>
                        <div class="footer__widget__social">
                            <a href="#"><i class="fa fa-facebook"></i></a>
                            <a href="#"><i class="fa fa-instagram"></i></a>
                            <a href="#"><i class="fa fa-twitter"></i></a>
                            <a href="#"><i class="fa fa-pinterest"></i></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="footer__copyright">
                        <div class="footer__copyright__text"><p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                                Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p></div>
                        <div class="footer__copyright__payment"><img src="assets/img/payment-item.png" alt=""></div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!-- Footer Section End -->

    <!-- Js Plugins -->
    <script src="assets/js/jquery-3.3.1.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/jquery.nice-select.min.js"></script>
    <script src="assets/js/jquery-ui.min.js"></script>
    <script src="assets/js/jquery.slicknav.js"></script>
    <script src="assets/js/mixitup.min.js"></script>
    <script src="assets/js/owl.carousel.min.js"></script>
    <script src="assets/js/main.js"></script>


</body>

</html>