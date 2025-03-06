<!DOCTYPE html>
<html lang="zxx">
    <%@ page import="entity.Product" %>
    <%@ page import="java.util.List" %>
    <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>





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
        <style>
            /* Styling for the pagination container */
            .pagination-wrapper {
                list-style: none;
                padding: 0;
                display: flex; /* Arrange the list items in a row */
                gap: 12px; /* Space between items */
            }

            /* Styling for individual pagination item */
            .pagination-item {
                cursor: pointer;
                padding: 12px 24px;
                background-color: #f2f2f2;
                border-radius: 6px;
                transition: background-color 0.3s;
            }

            /* Active pagination item styling */
            .pagination-item.active {
                background-color: #2FB468;
                color: white;
            }

            .pagination-item a {
                text-decoration: none;
                color: inherit;
            }

            /* Hover effect for pagination items */
            .pagination-item:hover {
                background-color: #ddd;
            }
        </style>
        <script src="https://cdn.tailwindcss.com">
        </script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                let totalPrice = 0; // Tổng tiền
                let totalQuantity = 0; // Tổng số sản phẩm

                // Cập nhật tổng thanh toán khi có sự thay đổi
                function updateTotalDisplay() {
                    let selectedProducts = document.querySelectorAll(".productCheckbox:checked");
                    let selectedTotalPrice = 0;
                    let selectedTotalQuantity = 0;

                    selectedProducts.forEach((checkbox) => {
                        let productCard = checkbox.closest(".product-card");
                        let productPrice = parseFloat(productCard.querySelector(".product-price").dataset.price);
                        let quantityInput = productCard.querySelector(".quantityInput");
                        let quantity = parseInt(quantityInput.value) || 1;

                        selectedTotalPrice += productPrice * quantity;
                        selectedTotalQuantity += quantity;
                    });

                    document.getElementById("totalPriceText").textContent = `Tổng thanh toán (${selectedTotalQuantity} Sản phẩm): ₫${selectedTotalPrice.toLocaleString()} VND`;
                }


                // Xử lý checkbox "Chọn Tất Cả"
                const selectAllCheckbox = document.getElementById("selectAllCheckboxFooter");
                selectAllCheckbox.addEventListener("change", function () {
                    let isChecked = this.checked;
                    document.querySelectorAll(".productCheckbox").forEach((checkbox) => {
                        checkbox.checked = isChecked;
                        updateTotalPriceForCheckbox(checkbox);
                    });
                });

                // Xử lý khi checkbox sản phẩm được chọn hoặc bỏ chọn
                document.querySelectorAll(".productCheckbox").forEach((checkbox) => {
                    checkbox.addEventListener("change", function () {
                        updateTotalPriceForCheckbox(this);
                    });
                });

                // Cập nhật tổng tiền và số lượng khi checkbox thay đổi
                function updateTotalPriceForCheckbox(checkbox) {
                    let productCard = checkbox.closest(".product-card");
                    let productPrice = parseFloat(productCard.querySelector(".product-price").dataset.price);
                    let quantityInput = productCard.querySelector(".quantityInput");
                    let quantity = parseInt(quantityInput.value) || 1;

                    if (checkbox.checked) {
                        totalPrice += productPrice * quantity;
                        totalQuantity += quantity;
                    } else {
                        totalPrice -= productPrice * quantity;
                        totalQuantity -= quantity;
                    }

                    updateTotalDisplay();
                }


                // Tính số tiền của sản phẩm khi thay đổi số lượng
                document.querySelectorAll(".product-card").forEach((productCard) => {
                    let decreaseBtn = productCard.querySelector(".decreaseBtn");
                    let increaseBtn = productCard.querySelector(".increaseBtn");
                    let quantityInput = productCard.querySelector(".quantityInput");

                    // Xử lý giảm số lượng
                    decreaseBtn.addEventListener("click", function () {
                        let quantity = parseInt(quantityInput.value);
                        if (quantity > 1) {
                            quantity--;
                            quantityInput.value = quantity;
                            updateTotalPriceForCheckbox(productCard.querySelector(".productCheckbox"));
                            updateTotalPriceForProduct(productCard);
                        }
                    });

                    // Xử lý tăng số lượng
                    increaseBtn.addEventListener("click", function () {
                        let quantity = parseInt(quantityInput.value);
                        quantity++;
                        quantityInput.value = quantity;
                        updateTotalPriceForCheckbox(productCard.querySelector(".productCheckbox"));
                        updateTotalPriceForProduct(productCard);
                    });

                    // Xử lý thay đổi số lượng trong input
                    quantityInput.addEventListener("input", function () {
                        let quantity = parseInt(quantityInput.value) || 1;
                        if (quantity < 1) {
                            quantity = 1;
                        }
                        quantityInput.value = quantity;
                        updateTotalPriceForCheckbox(productCard.querySelector(".productCheckbox"));
                        updateTotalPriceForProduct(productCard);
                    });
                });

                // Cập nhật lại giá tiền cho sản phẩm khi thay đổi số lượng
                function updateTotalPriceForProduct(productCard) {
                    let quantityInput = productCard.querySelector(".quantityInput");
                    let totalPriceElement = productCard.querySelector(".totalPrice");
                    let productPrice = parseFloat(productCard.querySelector(".product-price").dataset.price);
                    let quantity = parseInt(quantityInput.value) || 1;

                    totalPriceElement.textContent = '₫' + (productPrice * quantity).toLocaleString() + ' VND'; // Cập nhật giá tiền cho sản phẩm
                }

                // Xử lý xóa các sản phẩm đã chọn
                document.getElementById("deleteSelectedBtn").addEventListener("click", function () {
                    document.querySelectorAll(".productCheckbox:checked").forEach((checkbox) => {
                        let productCard = checkbox.closest(".product-card");
                        let productId = productCard.dataset.productId;

                        // Gửi yêu cầu xóa sản phẩm đã chọn
                        fetch("DeleteFavoriteController", {
                            method: "POST",
                            headers: {"Content-Type": "application/x-www-form-urlencoded"},
                            body: "productId=" + encodeURIComponent(productId)
                        })
                                .then(response => response.text())
                                .then(data => {
                                    if (data === "success") {
                                        productCard.remove(); // Xóa sản phẩm khỏi danh sách
                                        updateTotalDisplay(); // Cập nhật tổng tiền sau khi xóa
                                    } else {
                                        alert("Xóa không thành công. Vui lòng thử lại!");
                                    }
                                })
                                .catch(error => console.error("Lỗi xóa sản phẩm:", error));
                    });
                });

                // Xử lý xóa sản phẩm riêng khi không chọn
                document.querySelectorAll(".deleteBtn").forEach((deleteBtn) => {
                    deleteBtn.addEventListener("click", function () {
                        let productCard = this.closest(".product-card");
                        let productId = productCard.dataset.productId;

                        if (confirm("Bạn có chắc muốn xóa sản phẩm này khỏi danh sách yêu thích?")) {
                            fetch("DeleteFavoriteController", {
                                method: 'POST',
                                headers: {
                                    'Content-Type': 'application/x-www-form-urlencoded'
                                },
                                body: new URLSearchParams({
                                    'productId': productId
                                })
                            })
                                    .then(response => response.text())
                                    .then(result => {
                                        if (result === "success") {
                                            alert("Sản phẩm đã được xóa!");
                                            location.reload();  // Reload page to update list
                                        } else {
                                            alert("Xóa không thành công. Vui lòng thử lại.");
                                        }
                                    })
                                    .catch(error => {
                                        console.error("Error:", error);
                                        alert("Có lỗi xảy ra.");
                                    });
                        }
                    });
                });

                // Cập nhật tổng thanh toán khi trang được tải
                updateTotalDisplay();
            });

        </script>






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
                                <li><a href="/HomePage">Home</a></li>
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
                            <h2>Sản Phẩm Yêu Thích</h2>
                            <div class="breadcrumb__option">
                                <a href="/HomePage">Home</a>
                                <span>Sản Phẩm Yêu Thích</span>
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
                    <img alt="Shopee logo" class="mr-2" height="40" src="assets/img/logo.png" width="40"/>
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
            <div class="bg-white p-4 rounded-md shadow-md">
                <!-- Header -->
                <div class="bg-white p-4 rounded-md shadow-md">
                    <!-- Tiêu đề của danh sách sản phẩm -->
                    <div class="flex items-center justify-between border-b pb-2 mb-2">
                        <div class="flex items-center w-1/4">
                            <!-- Checkbox ở tiêu đề để chọn tất cả sản phẩm -->
                            <input class="mr-2" type="checkbox" id="selectAllCheckboxFooter"/>
                            <span class="font-bold">Sản Phẩm</span>
                        </div>
                        <div class="flex items-center space-x-8 w-3/4">
                            <span class="font-bold w-1/5 text-center">Giá Gốc</span>
                            <span class="font-bold w-1/5 text-center">Đơn Giá</span>
                            <span class="font-bold w-1/5 text-center">Số Lượng</span>
                            <span class="font-bold w-1/5 text-center">Số Tiền</span>
                            <span class="font-bold w-1/5 text-center">Thao Tác</span>
                        </div>
                    </div>

                    <!-- Loop for each product -->
                    <c:forEach var="product" items="${favoriteProducts}">
                        <div class="product-card flex items-center justify-between border-b pb-2 mb-2" data-product-id="${product.productId}">
                            <div class="flex items-center w-1/4">
                                <!-- Checkbox cho từng sản phẩm -->
                                <input class="productCheckbox mr-2" type="checkbox" data-product-id="${product.productId}" />
                                <div>
                                    <span class="block font-bold"><p>${product.shopName}</p></span>
                                    <div class="flex items-center mt-2">
                                        <img alt="Product image" class="mr-2" height="60" src="${product.imageURL}" width="60"/>
                                        <div>
                                            <span class="block font-bold">${product.name}</span>
                                            <span class="block text-sm text-gray-500">Số lượng hàng: ${product.stockQuantity}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="flex items-center space-x-8 w-3/4">
                                <span class="text-gray-500 line-through w-1/5 text-center">469.000VND</span>
                                <span class="text-red-500 product-price w-1/5 text-center" data-price="${product.price}">${product.formattedPrice}VND</span>
                                <div class="flex items-center w-1/5">
                                    <button class="decreaseBtn border border-gray-300 px-2">-</button>
                                    <input class="quantityInput w-12 text-center border-t border-b border-gray-300" type="text" value="1"/>
                                    <button class="increaseBtn border border-gray-300 px-2">+</button>
                                </div>
                                <span class="totalPrice text-red-500 w-1/5 text-center">${product.formattedPrice}VND</span>   
                                <button class="deleteBtn w-1/5 text-red-500" data-product-id="${product.productId}">Xóa</button>
                            </div>
                        </div>
                    </c:forEach>








                    <!-- Pagination -->
                    <ul class="pagination-wrapper">
                        <c:if test="${currentPage > 1}">
                            <li class="pagination-item"><a href="?page=${currentPage - 1}">«</a></li>
                            </c:if>

                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <li class="pagination-item ${i == currentPage ? 'active' : ''}">
                                <a href="?page=${i}">${i}</a>
                            </li>
                        </c:forEach>

                        <c:if test="${currentPage < totalPages}">
                            <li class="pagination-item"><a href="?page=${currentPage + 1}">»</a></li>
                            </c:if>
                    </ul>


                    <!-- Footer -->
                    <div class="flex items-center justify-between mt-4">
                        <div class="flex items-center">
                            <input class="mr-2" type="checkbox" id="selectAllCheckboxFooter"/>
                            <span class="font-bold">
                                Chọn Tất Cả 
                            </span>
                            <button class="ml-4 text-blue-500" id="deleteSelectedBtn">
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

                    <!-- Tổng thanh toán -->
                    <div class="flex items-center justify-between mt-4">
                        <div class="flex items-center">
                            <span class="text-red-500" id="totalPriceText">
                                Tổng thanh toán (0 Sản phẩm): ₫0
                            </span>
                        </div>
                        <button class="bg-orange-500 text-white px-6 py-2 rounded-md">
                            Chuyển vào giỏ hàng
                        </button>
                    </div>
                </div>

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