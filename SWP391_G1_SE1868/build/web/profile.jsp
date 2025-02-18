<!DOCTYPE html>
<html lang="zxx">

    <head>
        <meta charset="UTF-8">
        <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <meta name="description" content="Ogani Template">
        <meta name="keywords" content="Ogani, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <%@ page import="java.util.List, entity.Product" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



        <title>Ogani | Template</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

        <!-- Css Styles -->

        <link rel="stylesheet" href="assets/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="assets/css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="assets/css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/style.css" type="text/css">
        <link rel="stylesheet" href="assets/css/wish.css">
        <script src="https://cdn.tailwindcss.com">
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
        </style>


    </head>

    <body>
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>

        <!-- Humberger Begin -->
        <div class="humberger__menu__overlay"></div>
        <div class="humberger__menu__wrapper">
            <div class="humberger__menu__logo">
                <a href="#"><img src="assets/img/logo.png" alt=""></a>
            </div>
            <div class="humberger__menu__cart">
                <ul>
                    <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
                    <li><a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
                </ul>
            </div>
            <div class="humberger__menu__widget">
                <div class="header__top__right__language">
                    <img src="img/language.png" alt="">
                    <div>English</div>
                    <span class="arrow_carrot-down"></span>
                    <ul>
                        <li><a href="#">Spanis</a></li>
                        <li><a href="#">English</a></li>
                    </ul>
                </div>
                <div class="header__top__right__auth">
                    <c:choose>
                        <c:when test="${not empty customer}">
                            <a href="/ProfileController" class="profile-link">
                                <i class="fa fa-user"></i> <p>${customer.fullName}</p>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="/login" class="login-link">
                                <i class="fa fa-sign-in"></i> <p>Đăng nhập</p>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <nav class="humberger__menu__nav mobile-menu">
                <ul>
                    <li class="active"><a href="./index.html">Home</a></li>
                    <li><a href="./shop-grid.html">Shop</a></li>
                    <li><a href="#">Pages</a>
                        <ul class="header__menu__dropdown">
                            <li><a href="./shop-details.html">Shop Details</a></li>
                            <li><a href="./shoping-cart.html">Shoping Cart</a></li>
                            <li><a href="./checkout.html">Check Out</a></li>
                            <li><a href="./blog-details.html">Blog Details</a></li>
                        </ul>
                    </li>
                    <li><a href="./blog.html">Blog</a></li>
                    <li><a href="./contact.html">Contact</a></li>
                </ul>
            </nav>
            <div id="mobile-menu-wrap"></div>
            <div class="header__top__right__social">
                <a href="#"><i class="fa fa-facebook"></i></a>
                <a href="#"><i class="fa fa-twitter"></i></a>
                <a href="#"><i class="fa fa-linkedin"></i></a>
                <a href="#"><i class="fa fa-pinterest-p"></i></a>
            </div>
            <div class="humberger__menu__contact">
                <ul>
                    <li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
                    <li>Free Shipping for all Order of $99</li>
                </ul>
            </div>
        </div>
        <!-- Humberger End -->

        <!-- Header Section Begin -->
        <header class="header">
            <div class="header__top">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="header__top__left">
                                <ul>
                                    <li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
                                    <li>Free Shipping for all Order of $99</li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="header__top__right">
                                <div class="header__top__right__social">
                                    <a href="#"><i class="fa fa-facebook"></i></a>
                                    <a href="#"><i class="fa fa-twitter"></i></a>
                                    <a href="#"><i class="fa fa-linkedin"></i></a>
                                    <a href="#"><i class="fa fa-pinterest-p"></i></a>
                                </div>
                                <div class="header__top__right__language">
                                    <img src="assets/img/language.png" alt="">
                                    <div>English</div>
                                    <span class="arrow_carrot-down"></span>
                                    <ul>
                                        <li><a href="#">Spanis</a></li>
                                        <li><a href="#">English</a></li>
                                    </ul>
                                </div>
                                <div class="header__top__right__auth">
                    <c:choose>
                        <c:when test="${not empty customer}">
                            <a href="/ProfileController" class="profile-link">
                                <i class="fa fa-user"></i> <p>${customer.fullName}</p>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="/login" class="login-link">
                                <i class="fa fa-sign-in"></i> <p>Đăng nhập</p>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-3">
                        <div class="header__logo">
                            <a href="./index.html"><img src="assets/img/logo.png" alt=""></a>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <nav class="header__menu">
                            <ul>
                                <li><a href="./index.html">Home</a></li>
                                <li class="active"><a href="./shop-grid.html">Shop</a></li>
                                <li><a href="#">Pages</a>
                                    <ul class="header__menu__dropdown">
                                        <li><a href="./shop-details.html">Shop Details</a></li>
                                        <li><a href="./shoping-cart.html">Shoping Cart</a></li>
                                        <li><a href="./checkout.html">Check Out</a></li>
                                        <li><a href="./blog-details.html">Blog Details</a></li>
                                    </ul>
                                </li>
                                <li><a href="./blog.html">Blog</a></li>
                                <li><a href="./contact.html">Contact</a></li>
                            </ul>
                        </nav>
                    </div>
                    <div class="col-lg-3">
                        <div class="header__cart">
                            <ul>
                                <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
                                <li><a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="humberger__open">
                    <i class="fa fa-bars"></i>
                </div>
            </div>
        </header>
        <!-- Header Section End -->

        <!-- Hero Section Begin -->
        <
        <!-- Hero Section End -->

        <!-- Breadcrumb Section Begin -->
    <body class="bg-gray-100">
        <div class="max-w-4xl mx-auto p-6 bg-white shadow-md mt-10">
            <h1 class="text-2xl font-semibold mb-2">
                Hồ Sơ Của Tôi
            </h1>
            <p class="text-gray-600 mb-6">
                Quản lý thông tin hồ sơ để bảo mật tài khoản
            </p>
            <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
                <div class="md:col-span-2">
                    <div class="mb-4">
                        <label class="block text-gray-700">
                            Tên đăng nhập(Email)
                        </label>
                        <div class="input-with-button">
                            <input class="w-full border border-gray-300 p-2 rounded" placeholder="" type="text" value="${customer.email}"/>
                            <a class="text-blue-500" href="#">
                                Thay Đổi
                            </a>
                        </div>
                    </div>
                    <div class="mb-4">
                        <label class="block text-gray-700">
                            Họ và Tên
                        </label>
                        <div class="input-with-button">
                            <input class="w-full border border-gray-300 p-2 rounded" placeholder="" type="text" value=" ${customer.fullName}"/>
                            <a class="text-blue-500" href="#">
                                Thay Đổi
                            </a>
                        </div>
                    </div>
                    <div class="mb-4">
                        <label class="block text-gray-700">
                            Số Điện Thoại
                        </label>
                        <div class="input-with-button">
                            <input class="w-full border border-gray-300 p-2 rounded" placeholder="" type="text" value=" ${customer.phoneNumber}"/>
                            <a class="text-blue-500" href="#">
                                Thay Đổi
                            </a>
                        </div>
                    </div>
                    <div class="mb-4">
                        <label class="block text-gray-700">
                            Địa Chỉ
                        </label>
                        <div class="input-with-button">
                            <input class="w-full border border-gray-300 p-2 rounded" placeholder="" type="text" value=" ${customer.address}"/>
                            <a class="text-blue-500" href="#">
                                Thay Đổi
                            </a>
                        </div>
                    </div>
                    <div class="mb-4">
                        <label class="block text-gray-700">
                            Giới tính
                        </label>
                        <div class="flex items-center">
                            <input class="mr-2" id="male" name="gender" type="radio"/>
                            <label class="mr-4" for="male">
                                Nam
                            </label>
                            <input class="mr-2" id="female" name="gender" type="radio"/>
                            <label class="mr-4" for="female">
                                Nữ
                            </label>
                            <input class="mr-2" id="other" name="gender" type="radio"/>
                            <label for="other">
                                Khác
                            </label>
                        </div>
                    </div>
                    <div class="mb-4">
                        <label class="block text-gray-700">
                            Ngày sinh
                        </label>
                        <div class="flex space-x-4">
                            <select class="border border-gray-300 p-2 rounded">
                                <option>
                                    Ngày
                                </option>
                            </select>
                            <select class="border border-gray-300 p-2 rounded">
                                <option>
                                    Tháng
                                </option>
                            </select>
                            <select class="border border-gray-300 p-2 rounded">
                                <option>
                                    Năm
                                </option>
                            </select>
                        </div>
                    </div>
                    <div class="mb-4">
                        <label class="block text-gray-700">
                            Đổi mật khẩu
                        </label>
                        <input class="w-full border border-gray-300 p-2 rounded mb-2" placeholder="Mật khẩu hiện tại" type="password"/>
                        <input class="w-full border border-gray-300 p-2 rounded mb-2" placeholder="Mật khẩu mới" type="password"/>
                        <input class="w-full border border-gray-300 p-2 rounded" placeholder="Xác nhận mật khẩu mới" type="password"/>
                    </div>
                    <button class="bg-red-500 text-white px-4 py-2 rounded">
                        Lưu
                    </button>
                </div>
                <div class="flex flex-col items-center">
                    <img alt="Profile picture" class="rounded-full mb-4" height="100" src="https://storage.googleapis.com/a1aa/image/x3sww_KKU8u-OHkN2GyzlZ0VmIVlNF4-sJyS-ARYI64.jpg" width="100"/>
                    <button class="bg-gray-200 text-gray-700 px-4 py-2 rounded">
                        Chọn Ảnh
                    </button>
                    <p class="text-gray-500 mt-2 text-center">
                        Dung lượng file tối đa 1 MB
                        <br/>
                        Định dạng: .JPEG, .PNG
                    </p>
                </div>
            </div>
        </div>
    </body>
</html>
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