<%-- 
    Document   : orderList.jsp
    Created on : Jan 20, 2025, 9:18:38 PM
    Author     : DUCDUY2003
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

    <head>
        <title>
            Shopee Kênh Người Bán
        </title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&amp;display=swap" rel="stylesheet"/>
        <style>
            body {
                font-family: 'Roboto', sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f5f5f5;
            }
            .container {
                display: flex;
            }
            .sidebar {
                width: 250px;
                background-color: #fff;
                border-right: 1px solid #ddd;
                padding: 20px;
            }
            .sidebar h2 {
                font-size: 18px;
                margin-bottom: 20px;
            }
            .sidebar ul {
                list-style: none;
                padding: 0;
            }
            .sidebar ul li {
                margin-bottom: 10px;
            }
            .sidebar ul li a {
                text-decoration: none;
                color: #333;
                font-size: 14px;
            }
            .sidebar ul li a.active {
                color: #ee4d2d;
                font-weight: bold;
            }
            .main-content {
                flex-grow: 1;
                padding: 20px;
            }
            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }
            .header img {
                height: 40px;
            }
            .header .right {
                display: flex;
                align-items: center;
            }
            .header .right a {
                margin-left: 20px;
                text-decoration: none;
                color: #333;
                font-size: 14px;
            }
            .banner {
                margin-bottom: 20px;
            }
            .banner img {
                width: 100%;
            }
            .content {
                display: flex;
                justify-content: space-between;
            }
            .content .left {
                width: 70%;
            }
            .content .right {
                width: 28%;
                background-color: #fff;
                padding: 20px;
                border: 1px solid #ddd;
            }
            .content .right h3 {
                font-size: 16px;
                margin-bottom: 10px;
            }
            .content .right p {
                font-size: 14px;
                margin-bottom: 10px;
            }
            .content .right a {
                text-decoration: none;
                color: #ee4d2d;
                font-size: 14px;
            }
            .task-list, .sales-analysis {
                background-color: #fff;
                padding: 20px;
                border: 1px solid #ddd;
                margin-bottom: 20px;
            }
            .task-list h3, .sales-analysis h3 {
                font-size: 16px;
                margin-bottom: 20px;
            }
            .task-list .tasks, .sales-analysis .analysis {
                display: flex;
                justify-content: space-between;
            }
            .task-list .tasks div, .sales-analysis .analysis div {
                text-align: center;
                width: 18%;
            }
            .task-list .tasks div p, .sales-analysis .analysis div p {
                font-size: 14px;
                margin: 5px 0;
            }
            .task-list .tasks div span, .sales-analysis .analysis div span {
                font-size: 20px;
                font-weight: bold;
                color: #333;
            }
            .coppy p{
                text-align: center;
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <div class="header">
            <div class="right">
                <a href="#">
                    Shopee Fake
                </a>
                <a href="#">
                    Trung Tâm Trợ Giúp
                </a>
            </div>
        </div>
        <div class="container">
            <div class="sidebar">
                <h2>
                    View Order List (Xem danh sách đơn hàng)
                </h2>
                <ul>
                    <li>
                        <a class="active" href="#">
                            Quản Lý Vận Chuyển
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            Giao Hàng Loạt
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            Cài Đặt Vận Chuyển
                        </a>
                    </li>
                </ul>
                <h2>
                    View Order Details (Chi tiết đơn hàng)
                </h2>
                <ul>
                    <li>
                        <a href="#">
                            Quản Lý Sản Phẩm
                        </a>
                    </li>
                </ul>
                <h2>
                    Cancel Order (Hủy đơn hàng)
                </h2>
                <ul>
                    <li>
                        <a href="#">
                            Kênh Marketing
                        </a>
                    </li>
                </ul>
                <h2>
                    Request Return/Exchange (Yêu cầu đổi/trả hàng)
                </h2>
                <ul>
                    <li>
                        <a href="#">
                            Tài Chính
                        </a>
                    </li>
                </ul>
            </div>
            <div class="main-content">
                <div class="banner">
                    <img alt="#" height="300" src="https://1office.vn/wp-content/uploads/2023/11/bien-ban-giao-nhan-hang-hoa-la-gi.jpg" width="800"/>
                </div>
                <div class="content">
                    <div class="left">
                        <div class="task-list">
                            <h3>
                                Danh sách cần làm
                            </h3>
                            <div class="tasks">
                                <div>
                                    <p>
                                        Chờ Xác Nhận
                                    </p>
                                    <span>
                                        100
                                    </span>
                                </div>
                                <div>
                                    <p>
                                        Chờ Lấy Hàng
                                    </p>
                                    <span>
                                        500
                                    </span>
                                </div>
                                <div>
                                    <p>
                                        Đã Xác Nhận
                                    </p>
                                    <span>
                                        100
                                    </span>
                                </div>
                                <div>
                                    <p>
                                        Trả Hàng / Hoàn Tiền
                                    </p>
                                    <span>
                                        2
                                    </span>
                                </div>
                                <div>
                                    <p>
                                        Chờ Xử Lý
                                    </p>
                                    <span>
                                        0
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="sales-analysis">
                            <h3>
                                Phân Tích Bán Hàng
                            </h3>
                            <div class="analysis">
                                <div>
                                    <p>
                                        Đã Bán
                                    </p>
                                    <span>
                                        10.000
                                    </span>
                                </div>
                                <div>
                                    <p>
                                        Đơn Hàng
                                    </p>
                                    <span>
                                        0
                                    </span>
                                </div>
                                <div>
                                    <p>
                                        Đơn Hàng
                                    </p>
                                    <span>
                                        0
                                    </span>
                                </div>
                                <div>
                                    <p>
                                        Đơn Hàng
                                    </p>
                                    <span>
                                        0
                                    </span>
                                </div>
                                <div>
                                    <p>
                                        Đơn Hàng
                                    </p>
                                    <span>
                                        0
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="right">
                        <h3>
                            Thông Báo
                        </h3>
                        <p>
                            THƯỞNG XU CHO KHÁCH - SHOP NHẬN QUÀ TO
                        </p>
                        <p>
                            Dùng ngay tính năng Thưởng Xu tại Shopee Live để nhận được nhiều phần quà giá trị lên đến 30 TRIỆU ĐỒNG. THAM GIA NGAY!
                        </p>
                        <a href="#">
                            Xem thêm &gt;
                        </a>
                        <p>
                            CÓ CƠNG ĐANG FEED - CÓ NGAY TĂNG ĐƠN
                        </p>
                        <p>
                            Đăng ngay công thức hoặc sản phẩm của bạn để có cơ hội nhận được giải thưởng giá trị lên đến 4 TRIỆU ĐỒNG. ĐĂNG KÝ THAM GIA NGAY!
                        </p>
                        <a href="#">
                            Xem thêm &gt;
                        </a>
                    </div>
                </div>
            </div>
        </div>
    <dív class="coppy">
        <p>Copyright &copy; Website by SWP_G1_SE1868</p>
    </dív>
</body>
</html>

