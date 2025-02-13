

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="utf-8"/>
        <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
        <title>
            Ogani | Purchase
        </title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f5f5f5;
            }
            .header {
                background-color: #7fad39;
                color: white;
                padding: 10px 20px;
                display: flex;
                align-items: center;
                justify-content: space-between;
            }
            .header .left, .header .right {
                display: flex;
                align-items: center;
            }
            .header .left a, .header .right a {
                color: white;
                margin-right: 15px;
                text-decoration: none;
            }
            .header .right a:last-child {
                margin-right: 0;
            }
            .header .logo {
                display: flex;
                align-items: center;
            }
            .header .logo img {
                height: 40px;
                margin-right: 10px;
            }
            .header .search-bar {
                flex-grow: 1;
                display: flex;
                align-items: center;
                margin: 0 20px;
            }
            .header .search-bar input {
                width: 100%;
                padding: 5px;
                border: none;
                border-radius: 3px;
            }
            .header .search-bar button {
                background-color: #f5f5f5;
                border: none;
                padding: 5px 10px;
                margin-left: 5px;
                border-radius: 3px;
                cursor: pointer;
            }
            .header .cart {
                position: relative;
            }
            .header .cart .cart-count {
                position: absolute;
                top: -5px;
                right: -10px;
                background-color: #ff424e;
                color: white;
                border-radius: 50%;
                padding: 2px 5px;
                font-size: 12px;
            }
            .main {
                display: flex;
                padding: 20px;
            }
            .sidebar {
                width: 200px;
                background-color: white;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .sidebar .profile {
                display: flex;
                align-items: center;
                margin-bottom: 20px;
            }
            .sidebar .profile img {
                border-radius: 50%;
                width: 50px;
                height: 50px;
                margin-right: 10px;
            }
            .sidebar .profile .name {
                font-weight: bold;
            }
            .sidebar .menu {
                list-style: none;
                padding: 0;
                margin: 0;
            }
            .sidebar .menu li {
                margin-bottom: 10px;
            }
            .sidebar .menu li a {
                text-decoration: none;
                color: #333;
                display: flex;
                align-items: center;
            }
            .sidebar .menu li a i {
                margin-right: 10px;
            }
            .content {
                flex-grow: 1;
                margin-left: 20px;
            }
            .content .tabs {
                display: flex;
                border-bottom: 2px solid #f5f5f5;
                margin-bottom: 20px;
            }
            .content .tabs a {
                padding: 10px 20px;
                text-decoration: none;
                color: #333;
                border-bottom: 2px solid transparent;
            }
            .content .tabs a.active {
                border-bottom: 2px solid #ee4d2d;
                color: #ee4d2d;
            }
            .content .order {
                background-color: white;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .content .order .order-header {
                display: flex;
                justify-content: space-between;
                margin-bottom: 20px;
            }
            .content .order .order-header .order-title {
                font-weight: bold;
            }
            .content .order .order-header .order-actions {
                display: flex;
                align-items: center;
            }
            .content .order .order-header .order-actions button {
                background-color: #f5f5f5;
                border: none;
                padding: 5px 10px;
                margin-left: 10px;
                border-radius: 3px;
                cursor: pointer;
            }
            .content .order .order-item {
                display: flex;
                align-items: center;
                margin-bottom: 20px;
            }
            .content .order .order-item img {
                width: 100px;
                height: 100px;
                margin-right: 20px;
            }
            .content .order .order-item .item-details {
                flex-grow: 1;
            }
            .content .order .order-item .item-details .item-name {
                font-weight: bold;
                margin-bottom: 5px;
            }
            .content .order .order-item .item-details .item-price {
                color: #ee4d2d;
                margin-bottom: 5px;
            }
            .content .order .order-item .item-details .item-quantity {
                color: #999;
            }
            .content .order .order-summary {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-top: 20px;
            }
            .content .order .order-summary .total-price {
                font-size: 20px;
                color: #ee4d2d;
            }
            .content .order .order-summary .order-actions {
                display: flex;
                align-items: center;
            }
            .content .order .order-summary .order-actions button {
                background-color: #f5f5f5;
                border: none;
                padding: 10px 20px;
                margin-left: 10px;
                border-radius: 3px;
                cursor: pointer;
            }
        </style>
        <script>
            function filterOrders(status) {
                // Cập nhật URL mà không tải lại trang
                let baseUrl = window.location.origin + window.location.pathname;
                window.history.pushState({}, '', baseUrl + "?tab=" + status);

                // Lọc đơn hàng dựa trên trạng thái
                document.querySelectorAll('.order').forEach(order => {
                    if (status === 'all' || order.getAttribute('data-status') === status) {
                        order.style.display = 'block';
                    } else {
                        order.style.display = 'none';
                    }
                });

                // Cập nhật trạng thái active của tab
                document.querySelectorAll('.tabs a').forEach(tab => {
                    tab.classList.remove('active');
                    if (tab.getAttribute('onclick').includes(status)) {
                        tab.classList.add('active');
                    }
                });
            }

            // Khi tải lại trang, giữ trạng thái tab dựa vào URL
            document.addEventListener("DOMContentLoaded", function () {
                let params = new URLSearchParams(window.location.search);
                let activeTab = params.get("tab") || "all";
                filterOrders(activeTab);
            });
        </script>
    </head>
    <body>
        <div class="header">
            <div class="left">
                <div class="logo">
                    <a href="http://localhost:8080/index.html" target="target">
                    <img alt="Shopee logo" height="40" src="assets/img/logo.png" width="70" />
                    </a>
                    <span>
                        #
                    </span>
                </div>
                <a href="#">
                    Kênh Người Bán
                </a>
                <a href="#">
                    Tải ứng dụng
                </a>
                <a href="#">
                    Kết nối
                </a>
            </div>
            <div class="search-bar">
                <input placeholder="Search" type="text"/>
                <button>
                    <i class="fas fa-search">
                    </i>
                </button>
            </div>
            <div class="right">
                <a href="#">
                    Thông Báo
                </a>
                <a href="#">
                    Hỗ Trợ
                </a>
                <a href="#">
                    Tiếng Việt
                </a>
                <a href="#">
                    DUC_DUY8386
                </a>
                <div class="cart">
                    <a href="#">
                        <i class="fas fa-shopping-cart">
                        </i>
                    </a>
                    <span class="cart-count">
                        100
                    </span>
                </div>
            </div>
        </div>
        <div class="main">
            <div class="sidebar">
                <div class="profile">
                    <img alt="User profile picture" height="50" src="https://hoanghamobile.com/tin-tuc/wp-content/uploads/2024/08/pikachu-game-3.jpg" width="50"/>
                    <div>
                        <div class="name">
                            DUC_Duy8386
                        </div>
                        <a href="#">
                            Sửa Hồ Sơ
                        </a>
                    </div>
                </div>
                <ul class="menu">
                    <li>
                        <a href="#">
                            <i class="fas fa-bell">
                            </i>
                            Thông Báo
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fas fa-user">
                            </i>
                            Tài Khoản Của Tôi
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fas fa-shopping-bag">
                            </i>
                            Đơn Mua
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fas fa-ticket-alt">
                            </i>
                            Kho Voucher
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fas fa-coins">
                            </i>
                            BIT COIN
                        </a>
                    </li>
                </ul>
            </div>
            <div class="content">
                <div class="tabs">
                    <a href="#" onclick="filterOrders('all')">Tất cả</a>
                    <a href="#" onclick="filterOrders('pending')" >Chờ thanh toán</a>
                    <a href="#" onclick="filterOrders('shipping')">Vận chuyển</a>
                    <a href="#" onclick="filterOrders('delivering')">Chờ giao hàng</a>
                    <a href="#" onclick="filterOrders('completed')">Hoàn thành</a>
                    <a href="#" onclick="filterOrders('cancelled')">Đã hủy</a>
                    <a href="#" onclick="filterOrders('refund')">Trả hàng/Hoàn tiền</a>
                </div>
                <div class="order" data-status="pending">
                    <div class="order-header">
                        <div class="order-title">ỚT NHÀ TÁO NGỐ</div>
                        <div class="order-actions">
                            <button>Yêu thích</button>
                            <button>Chat</button>
                            <button>Xem Shop</button>
                        </div>
                    </div>
                    <div class="order-item">
                        <img src="assets/img/product/details/product-details-2.jpg" width="50" height="50" alt="Product image">
                        <div class="item-details">
                            <div class="item-name">500gr Ớt Chuông đỏ + vàng</div>
                            <div class="item-price">₫500.000</div>
                            <div class="item-quantity">Phân loại hàng: Hàng tươi mới</div>
                        </div>
                    </div>
                    <div class="order-summary">
                        <div class="total-price">Số tiền phải trả: ₫505.000</div>
                        <div class="order-actions">
                            <button>Mua Lại</button>
                            <button>Xem Chi Tiết Hủy Đơn</button>
                            <button>Liên Hệ Người Bán</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <a href="index.html">Quay lại Trang Chủ</a>

    </body>
</html>
