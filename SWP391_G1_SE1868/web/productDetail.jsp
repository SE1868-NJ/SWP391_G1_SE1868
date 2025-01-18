<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Detail</title>
        <style>
            /* Reset một số thuộc tính cơ bản */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            /* Căn giữa toàn bộ nội dung */
            body {
                font-family: Arial, sans-serif;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                background-color: #f8f8f8;
                margin: 0;
            }

            .product-detail-container {
                width: 80%;
                max-width: 800px;
                background-color: #fff;
                border-radius: 12px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                padding: 20px;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                text-align: center;
            }

            /* Tạo card sản phẩm */
            .product-detail-container h2 {
                font-size: 2rem;
                margin-bottom: 20px;
                color: #333;
            }

            .product-image img {
                max-width: 100%;
                height: auto;
                border-radius: 8px;
                margin-bottom: 20px;
            }

            /* Hiển thị thông tin chi tiết sản phẩm */
            .product-info p {
                font-size: 1rem;
                margin: 8px 0;
                color: #555;
            }

            .product-info strong {
                color: #333;
            }

            /* Căn chỉnh các nút */
            .buttons {
                margin-top: 20px;
                display: flex;
                justify-content: space-around;
                width: 100%;
            }

            .back-button, .add-to-cart-button {
                padding: 10px 20px;
                font-size: 1rem;
                border-radius: 5px;
                text-decoration: none;
                text-align: center;
                cursor: pointer;
            }

            .back-button {
                background-color: #f1f1f1;
                color: #333;
                border: 1px solid #ddd;
            }

            .back-button:hover {
                background-color: #e1e1e1;
            }

            .add-to-cart-button {
                background-color: #4CAF50;
                color: white;
                border: none;
            }

            .add-to-cart-button:hover {
                background-color: #45a049;
            }

        </style>
    </head>
    <body>
        <div class="product-detail-container">
            <h2>${product.productName}</h2>

            <div class="product-image">
                <img src="${product.image}" alt="${product.productName}" />
            </div>

            <div class="product-info">
                <p><strong>Price:</strong> $${product.price}</p>
                <p><strong>Discount Price:</strong> $${product.discountPrice}</p>
                <p><strong>Stock Quantity:</strong> ${product.stockQuantity}</p>
                <p><strong>Description:</strong> ${product.description}</p>
                <p><strong>Average Rating:</strong> ${product.averageRating}</p>
            </div>

            <div class="buttons">
                <a href="products" class="back-button">Back to Product List</a>
                <c:if test="${product.stockQuantity > 0}">
                    <a href="/myCarts?action=addToCart&productId=${product.productId}" 
                       class="add-to-cart-button">Add to Cart</a>
                </c:if>
                <c:if test="${product.stockQuantity == 0}">
                    <button disabled class="add-to-cart-button">Out of Stock</button>
                </c:if>
            </div>
        </div>
    </body>
</html>
