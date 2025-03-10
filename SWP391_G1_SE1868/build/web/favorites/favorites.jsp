<!DOCTYPE html>
<html lang="zxx">
    <%@ page import="entity.Product" %>
    <%@ page import="java.util.List" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    

    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Ogani Template">
        <meta name="keywords" content="Ogani, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
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
        <link rel="stylesheet" href="styles.css">
        <script src="https://cdn.tailwindcss.com">
        </script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
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
                <div class="header__cart__price">item: <span>$150.00</span></div>
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
                            <div class="header__cart__price">item: <span>$150.00</span></div>
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
        <section class="hero hero-normal">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3">
                        <div class="hero__categories">
                            <div class="hero__categories__all">
                                <i class="fa fa-bars"></i>
                                <span>All departments</span>
                            </div>
                            <ul>
                                <li><a href="#">Fresh Meat</a></li>
                                <li><a href="#">Vegetables</a></li>
                                <li><a href="#">Fruit & Nut Gifts</a></li>
                                <li><a href="#">Fresh Berries</a></li>
                                <li><a href="#">Ocean Foods</a></li>
                                <li><a href="#">Butter & Eggs</a></li>
                                <li><a href="#">Fastfood</a></li>
                                <li><a href="#">Fresh Onion</a></li>
                                <li><a href="#">Papayaya & Crisps</a></li>
                                <li><a href="#">Oatmeal</a></li>
                                <li><a href="#">Fresh Bananas</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-9">
                        <div class="hero__search">
                            <div class="hero__search__form">
                                <form action="#">
                                    <div class="hero__search__categories">
                                        All Categories
                                        <span class="arrow_carrot-down"></span>
                                    </div>
                                    <input type="text" placeholder="What do yo u need?">
                                    <button type="submit" class="site-btn">SEARCH</button>
                                </form>
                            </div>
                            <div class="hero__search__phone">
                                <div class="hero__search__phone__icon">
                                    <i class="fa fa-phone"></i>
                                </div>
                                <div class="hero__search__phone__text">
                                    <h5>+65 11.188.888</h5>
                                    <span>support 24/7 time</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Hero Section End -->

        <!-- Breadcrumb Section Begin -->
        <section class="breadcrumb-section set-bg" data-setbg="assets/img/breadcrumb.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <div class="breadcrumb__text">
                            <h2>Shopping Cart</h2>
                            <div class="breadcrumb__option">
                                <a href="./Home.jsp">Home</a>
                                <span>Shopping Cart</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Breadcrumb Section End -->

        <!-- Shoping Cart Section Begin -->
    <body class="bg-gray-100">

        <div class="container mx-auto p-4">
            <!-- Header -->
            <div class="flex items-center justify-between mb-4">
                <div class="flex items-center">
                    <img alt="Shopee logo" class="mr-2" height="40" src="" width="40"/>
                    <h1 class="text-xl font-bold text-orange-500">
                        Ogani
                    </h1>
                    <span class="ml-2 text-lg">
                        Sản Phẩm Yêu Thích
                    </span>
                </div>
                <div class="flex items-center">
                    <input class="border border-gray-300 p-2 rounded-l-md w-96" placeholder="Tìm sản phẩm, thương hiệu, và tên shop" type="text"/>
                    <button class="bg-orange-500 text-white p-2 rounded-r-md">
                        <i class="fas fa-search">
                        </i>
                    </button>
                </div>
            </div>
            <!-- Cart Items -->
            <div class="bg-white p-4 rounded-md shadow-md">
                <div class="flex items-center justify-between border-b pb-2 mb-2">
                    <div class="flex items-center">
                        <input class="mr-2" type="checkbox"/>
                        <span class="font-bold">
                            Sản Phẩm
                        </span>
                    </div>
                    <div class="flex items-center space-x-16">
                        <span class="font-bold">
                            Đơn Giá
                        </span>
                        <span class="font-bold">
                            Số Lượng
                        </span>
                        <span class="font-bold">
                            Số Tiền
                        </span>
                        <span class="font-bold">
                            Thao Tác
                        </span>
                    </div>
                </div>
                <!-- Item 1 -->
                <c:forEach var="product" items="${favoriteProducts}">
                    <div class="product-card flex items-center justify-between border-b pb-2 mb-2">
                        <div class="flex items-center">
                            <input class="mr-2" type="checkbox"/>
                            <div>
                                <span class="block font-bold"></span>
                                <div class="flex items-center mt-2">
                                    <img alt="Product image" class="mr-2" height="60" src="${product.imageURL}" width="60"/>
                                    <div>
                                        <span class="block font-bold">${product.name}</span>
                                        <span class="block text-sm text-gray-500">Phân Loại Hàng: ${product.stockQuantity}</span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="flex items-center space-x-16">
                            <span class="text-gray-500 line-through">₫469.000</span>
                            <span class="text-red-500">₫${product.price}</span>
                            <div class="flex items-center">
                                <button class="border border-gray-300 px-2">-</button>
                                <input class="w-12 text-center border-t border-b border-gray-300" type="text" value="1"/>
                                <button class="border border-gray-300 px-2">+</button>
                            </div>
                            <span class="text-red-500">₫${product.price}</span>
                            <button class="text-red-500">Xóa</button>
                        </div>
                    </div>
                </c:forEach>

                <!-- Phân trang -->
                <div class="pagination">
                    <c:if test="${currentPage > 1}">
                        <a href="favoriteProducts?page=${currentPage - 1}" class="pagination-link">Previous</a>
                    </c:if>

                    <span>Page ${currentPage} of ${totalPages}</span>

                    <c:if test="${currentPage < totalPages}">
                        <a href="favoriteProducts?page=${currentPage + 1}" class="pagination-link">Next</a>
                    </c:if>
                </div>
                <!-- Voucher -->
                <div class="flex items-center justify-between border-b pb-2 mb-2">
                    <div class="flex items-center">
                        <input class="mr-2" type="checkbox"/>
                        <span class="block">
                            Poemax Tech
                        </span>
                    </div>
                    <div class="flex items-center space-x-16">
                        <span class="text-sm text-gray-500">
                            Mua Kèm Deal Sốc với mức giá ưu đãi
                        </span>
                        <button class="text-blue-500">
                            Thêm
                        </button>
                    </div>
                </div>
                <!-- Item 2 -->
                <div class="flex items-center justify-between border-b pb-2 mb-2">
                    <div class="flex items-center">
                        <input class="mr-2" type="checkbox"/>
                        <div>
                            <span class="block font-bold">
                                Poemax Tech
                            </span>
                            <div class="flex items-center mt-2">
                                <img alt="Product image" class="mr-2" height="60" src="https://storage.googleapis.com/a1aa/image/xj5EKUJtqi7Sq2wJzNuttt720JLLsQ3SVIyEk8uBwF8.jpg" width="60"/>
                                <div>
                                    <span class="block">
                                        Tai nghe PC gaming có dây cho máy tính bàn PC có mic đàm thoại
                                    </span>
                                    <span class="block text-sm text-gray-500">
                                        Phân Loại Hàng: V6
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="flex items-center space-x-16">
                        <span class="text-gray-500 line-through">
                            ₫469.000
                        </span>
                        <span class="text-red-500">
                            ₫119.000
                        </span>
                        <div class="flex items-center">
                            <button class="border border-gray-300 px-2">
                                -
                            </button>
                            <input class="w-12 text-center border-t border-b border-gray-300" type="text" value="1"/>
                            <button class="border border-gray-300 px-2">
                                +
                            </button>
                        </div>
                        <span class="text-red-500">
                            ₫119.000
                        </span>
                        <button class="text-red-500">
                            Xóa
                        </button>
                    </div>
                </div>
                <!-- Voucher -->
                <div class="flex items-center justify-between border-b pb-2 mb-2">
                    <div class="flex items-center">
                        <input class="mr-2" type="checkbox"/>
                        <span class="block">
                            Voucher giảm đến ₫50k
                        </span>
                    </div>
                    <div class="flex items-center space-x-16">
                        <button class="text-blue-500">
                            Xem thêm voucher
                        </button>
                    </div>
                </div>
                <!-- Item 3 -->
                <div class="flex items-center justify-between border-b pb-2 mb-2">
                    <div class="flex items-center">
                        <input class="mr-2" type="checkbox"/>
                        <div>
                            <span class="block font-bold">
                                viettuo12812003vn
                            </span>
                            <div class="flex items-center mt-2">
                                <img alt="Product image" class="mr-2" height="60" src="https://storage.googleapis.com/a1aa/image/xj5EKUJtqi7Sq2wJzNuttt720JLLsQ3SVIyEk8uBwF8.jpg" width="60"/>
                                <div>
                                    <span class="block">
                                        Bóng Led T10 Siêu Sáng, Đèn Led T10 Lắp Mặt Đồng Hồ, Đèn Led T10
                                    </span>
                                    <span class="block text-sm text-gray-500">
                                        Phân Loại Hàng: Kính Đục,Xanh Dương
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="flex items-center space-x-16">
                        <span class="text-red-500">
                            ₫1.349
                        </span>
                        <div class="flex items-center">
                            <button class="border border-gray-300 px-2">
                                -
                            </button>
                            <input class="w-12 text-center border-t border-b border-gray-300" type="text" value="1"/>
                            <button class="border border-gray-300 px-2">
                                +
                            </button>
                        </div>
                        <span class="text-red-500">
                            ₫1.349
                        </span>
                        <button class="text-red-500">
                            Xóa
                        </button>
                    </div>
                </div>



                <!-- Voucher -->
                <div class="flex items-center justify-between border-b pb-2 mb-2">
                    <div class="flex items-center">
                        <input class="mr-2" type="checkbox"/>
                        <span class="block">
                            Voucher giảm đến ₫50k
                        </span>
                    </div>
                    <div class="flex items-center space-x-16">
                        <button class="text-blue-500">
                            Xem thêm voucher
                        </button>
                    </div>
                </div>
                <!-- Pagination Controls -->
                <div>
                    <c:if test="${currentPage > 1}">
                        <a href="favorite-products?page=${currentPage - 1}">Previous</a>
                    </c:if>

                    <span>Page ${currentPage} of ${totalPages}</span>

                    <c:if test="${currentPage < totalPages}">
                        <a href="favorite-products?page=${currentPage + 1}">Next</a>
                    </c:if>
                </div>
                <!-- Footer -->
                <div class="flex items-center justify-between mt-4">
                    <div class="flex items-center">
                        <input class="mr-2" type="checkbox"/>
                        <span>
                            Chọn Tất Cả (12)
                        </span>
                        <button class="ml-4 text-blue-500">
                            Xóa
                        </button>
                        <button class="ml-4 text-blue-500">
                            Bỏ sản phẩm không hoạt động
                        </button>
                    </div>
                    <div class="flex items-center">
                        <span class="mr-4">
                            Voucher
                        </span>
                        <button class="border border-gray-300 px-4 py-2">
                            Chọn hoặc nhập mã
                        </button>
                    </div>
                </div>
                <div class="flex items-center justify-between mt-4">
                    <div class="flex items-center">
                        <span class="text-red-500">
                            Tổng thanh toán (0 Sản phẩm): ₫0
                        </span>
                    </div>
                    <button class="bg-orange-500 text-white px-6 py-2 rounded-md">
                        Chuyển vào giỏ hàng
                    </button>
                </div>
            </div>
        </div>
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