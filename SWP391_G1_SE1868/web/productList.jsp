<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Danh sách sản phẩm</title>
        <!-- Css Styles -->
        <link rel="stylesheet" href="assets/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="assets/css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="assets/css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/style.css" type="text/css">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }

            .container {
                max-width: 1200px;
                margin: 0 auto;
                padding: 20px;
            }

            h1 {
                text-align: center;
                color: #333;
                margin-bottom: 30px;
            }

            .product-grid {
                display: grid;
                grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
                gap: 20px;
            }

            .product-card {
                background: #fff;
                border: 1px solid #ddd;
                border-radius: 8px;
                overflow: hidden;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                transition: transform 0.2s ease-in-out;
            }

            .product-card:hover {
                transform: translateY(-5px);
            }

            .product-image {
                width: 100%;
                height: 200px;
                object-fit: cover;
                display: block;
            }

            .product-info {
                padding: 15px;
            }

            .product-name {
                font-size: 18px;
                font-weight: bold;
                color: #333;
                margin: 10px 0;
            }

            .product-price {
                font-size: 16px;
                color: #e67e22;
                font-weight: bold;
                margin: 5px 0;
            }

            .product-description {
                font-size: 14px;
                color: #666;
                margin: 10px 0;
                overflow: hidden;
                display: -webkit-box;
                -webkit-line-clamp: 3;
                -webkit-box-orient: vertical;
            }

            .view-button {
                display: inline-block;
                text-align: center;
                background: #3498db;
                color: #fff;
                padding: 10px 15px;
                text-decoration: none;
                font-size: 14px;
                border-radius: 5px;
                margin-top: 10px;
            }

            .view-button:hover {
                background: #2980b9;
            }

            .filters {
                margin-bottom: 20px;
            }

            .filters a {
                margin-right: 10px;
                text-decoration: none;
                color: #fff;
                background-color: #007bff;
                padding: 8px 12px;
                border-radius: 4px;
            }

            .filters a:hover {
                background-color: #0056b3;
            }

            .product-card {
                border: 1px solid #ccc;
                border-radius: 8px;
                padding: 16px;
                margin: 16px;
                text-align: center;
                width: 200px;
                display: inline-block;
                vertical-align: top;
            }

            .product-card img {
                max-width: 100%;
                height: auto;
                border-radius: 8px;
            }

            .product-card button {
                background-color: #28a745;
                color: #fff;
                border: none;
                padding: 8px 16px;
                border-radius: 4px;
                cursor: pointer;
            }

            .pagination {
                margin-top: 20px;
                text-align: center;
            }

            .pagination a {
                margin: 0 5px;
                text-decoration: none;
                color: #007bff;
            }

            .pagination a:hover {
                text-decoration: underline;
            }

            .clear {
                clear: both;
            }

            .filter-box{
                display: flex;
                flex-direction: row;
                justify-content: space-between;
            }

            /* Định dạng cho form */
            form {
                display: flex;
                flex-wrap: wrap;
                gap: 10px;
                align-items: center;
                margin: 20px 50px;
            }

            /* Định dạng cho các input */
            .form-control {
                padding: 10px;
                border-radius: 5px;
                border: 1px solid #ccc;
                font-size: 14px;
                width: 200px;
                transition: border-color 0.3s ease;
            }

            /* Thêm hiệu ứng khi input được focus */
            .form-control:focus {
                border-color: #007bff;
                outline: none;
            }

            /* Định dạng cho nút tìm kiếm */
            button.btn-primary {
                padding: 10px 20px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
                font-size: 14px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            /* Thêm hiệu ứng hover cho nút */
            button.btn-primary:hover {
                background-color: #0056b3;
            }

            /* Định dạng cho link */
            .filters a {
                display: inline-block;
                padding: 10px;
                background-color: #28a745;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                font-size: 14px;
                margin-top: 10px;
                transition: background-color 0.3s ease;
            }

            /* Thêm hiệu ứng hover cho link */
            .filters a:hover {
                background-color: #218838;
            }

        </style>
    </head>
    <body>
        <div class="container">
            <div class="filter-box">
                <h1>${title}</h1>

                <form method="get" action="products">
                    <input type="text" name="searchKeyword" placeholder="Tìm kiếm theo tên phẩm" value="${searchQuery}" class="form-control">
                    <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                </form>

                <!-- Pagination -->
                <div class="pagination">
                    <c:if test="${page > 1}">
                        <a href="products?action=list&page=${page - 1}&searchQuery=${searchQuery}">Trước</a>
                    </c:if>
                    <span>Page ${page} of ${totalPages}</span>
                    <c:if test="${page < totalPages}">
                        <a href="products?action=list&page=${page + 1}&searchQuery=${searchQuery}">Sau</a>
                    </c:if>
                </div>
            </div>

            <section class="hero">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3">
                            <div class="hero__categories">
                                <div class="hero__categories__all">
                                    <i class="fa fa-bars"></i>
                                    <span><a href="products" style="color: white;"> Tất cả các danh mục </a> </span>
                                </div>
                                <ul>
                                    <c:forEach var="category" items="${categories}">
                                        <li><a href="products?action=filterByCategory&categoryId=${category.categoryId}&searchQuery=${searchQuery}">${category.name}</a></li>
                                        </c:forEach>
                                </ul>
                            </div>
                        </div>

                        <div class="product-grid col-lg-9">
                            <c:forEach var="product" items="${products}">
                                <div class="product-card">
                                    <img src="${product.imageUrl}" alt="${product.name}" class="product-image" />
                                    <div class="product-info">
                                        <div class="product-name">${product.name}</div>
                                        <div class="product-price">$${product.price}</div>
                                        <div class="product-rating">Rating: ${product.averageRating}</div>
                                        <a href="productDetail?productId=${product.productId}" class="view-button">View Details</a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </body>
</html>
