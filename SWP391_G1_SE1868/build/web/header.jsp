<%-- 
    Document   : header
    Created on : Feb 4, 2025, 12:40:43 PM
    Author     : Đạt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Google Font -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <!-- Bootstrap 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="assets/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="assets/css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="assets/css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/style.css" type="text/css">

    </head>

    <body>

        <!-- Header Section Begin -->
        <header class="header">
            <div class="header__top">
                <div class="container">
                    <div class="row">


                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-3">
                        <div class="header__logo">
                            <a href="home"><img src="assets/img/cholang.png" width="70px"   alt=""></a>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <nav class="header__menu">
                        </nav>
                    </div>
                    <div class="col-lg-3">
                        <div class="header__cart">
                            <ul>
                                <div class="header__top__right__language">
                                    <!-- Kiểm tra xem người dùng đã đăng nhập hay chưa --> 
                                    <c:choose>
                                        <c:when test="${not empty sessionScope.user}">
                                            <!-- Nếu có người dùng, hiển thị tên người dùng và dropdown -->
                                            <div class="fa fa-user">    ${sessionScope.user.fullName}</div> <!-- Hiển thị tên người dùng -->
                                            <span class="arrow_carrot-down"></span>
                                            <ul class="w-100">
                                                <li class="d-block"><a href="/viewOrder">View Order</a></li>
                                                <li class="d-block"><a href="#">b</a></li>
                                                <li class="d-block"><a href="#">c</a></li>
                                                <li class="d-block"><a href="/logout">Logout</a></li>
                                            </ul>
                                        </c:when>
                                        <c:otherwise>
                                            <!-- Nếu không có người dùng, chỉ hiển thị Login -->
                                            <div class="fa fa-user"><a class="text-black fw-bold" href="login.jsp">Login</a></div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
                                <li>
                                    <a href="/cart"><i class="fa fa-shopping-bag"></i> 
                                        <span>
                                            <c:choose>
                                                <c:when test="${not empty sessionScope.cart}">
                                                    ${sessionScope.cart}
                                                </c:when>
                                                <c:otherwise>
                                                    0
                                                </c:otherwise>
                                            </c:choose>
                                        </span>
                                    </a>
                                </li>
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


        <!-- Breadcrumb Section Begin -->
        <section class="breadcrumb-section set-bg" data-setbg="assets/img/breadcrumb.jpg">
            <div class="container">
                <div class="row">
                    <h2 class="text-white fw-bold text-center">Chợ Làng</h2>
                </div>
            </div>
        </section>
        <div class="container w-50">
            <div class="row ">
                <div class="d-flex align-items-center justify-content-between ">
                    <a href="/home" class="text-black fw-bold">Home</a>
                    <a href="/products" class="text-black fw-bold">Product</a>
                    <a href="/blog" class="text-black fw-bold">Blog</a>
                    <a href="/contact" class="text-black fw-bold">Contact</a>
                </div>
            </div>
        </div>

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
