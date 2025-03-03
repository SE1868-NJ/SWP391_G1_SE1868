<%@ page import="entity.Cart" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Shopping Cart</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f7f7f7;
                color: #333;
                margin: 0;
                padding: 0;
            }

            .container {
                width: 80%;
                margin: 30px auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                position: relative;
            }

            h2 {
                text-align: center;
                color: #333;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin: 20px 0;
            }

            th, td {
                padding: 15px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #f8f9fa;
            }

            .remove-button,
            .update-button {
                padding: 10px 20px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                text-decoration: none;
            }

            .remove-button:disabled {
                background-color: #ccc;
                cursor: not-allowed;
            }

            .clear-cart-button {
                background-color: #dc3545;
                color: white;
                padding: 10px 20px;
                border-radius: 5px;
                border: none;
                font-size: 16px;
                cursor: pointer;
                transition: background-color 0.3s, transform 0.2s;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }

            .clear-cart-button:hover {
                background-color: #c82333; /* Màu đậm hơn khi hover */
                transform: scale(1.05); /* Phóng to nút một chút khi hover */
            }

            .clear-cart-button:active {
                transform: scale(1); /* Giảm kích thước trở lại khi nhấn */
            }

            .clear-cart-button:focus {
                outline: none; /* Loại bỏ viền mặc định khi nút được focus */
                box-shadow: 0 0 5px rgba(0, 0, 0, 0.2); /* Hiệu ứng khi focus */
            }


            .total-price {
                font-size: 18px;
                font-weight: bold;
                color: #333;
                position: absolute;
                bottom: 20px;
                right: 20px;
            }

            .footer {
                text-align: center;
                margin-top: 20px;
            }

            .footer a {
                text-decoration: none;
                color: #007bff;
            }

            .back-to-products {
                text-decoration: none;
                background-color: #28a745;
                color: white;
                padding: 10px 20px;
                border-radius: 5px;
                font-size: 16px;
            }

            img {
                width: 100px; /* Đảm bảo ảnh chiếm hết chiều rộng của thẻ chứa */
                height: auto; /* Giữ tỷ lệ chiều cao của ảnh */
                object-fit: contain; /* Đảm bảo ảnh không bị vỡ tỷ lệ, nếu không đủ không gian, ảnh sẽ được thu nhỏ */
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Your Shopping Cart</h2>

            <!-- Link to back to products -->
            <div class="footer">
                <a href="/products" class="back-to-products">Back to Products</a>
            </div>

            <!-- Cart Table -->
            <table>
                <thead>
                    <tr>
                        <th>Product Name</th>
                        <th>Image</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Loop through the cart items and display them -->
                    <c:forEach var="cartItem" items="${cartList}">
                        <tr>
                            <td><strong>${cartItem.productName}</strong></td>
                            <td><img src="${cartItem.image}" /></td>
                            <td>${cartItem.price} VND</td>
                            <td>
                                <form action="/myCarts" method="GET">
                                    <input type="number" name="quantity" value="${cartItem.quantity}" min="1" />
                                    <input type="hidden" name="action" value="updateCartItemQuantity">
                                    <input type="hidden" name="productId" value="${cartItem.productId}" />
                                    <button type="submit" class="update-button">Update Quantity</button>
                                </form>
                            </td>
                            <td>${cartItem.price * cartItem.quantity} VND</td>
                            <td>
                                <a href="/myCarts?action=removeFromCart&productId=${cartItem.productId}" class="remove-button">Remove</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Calculate total price -->
            <div class="total-price">
                Total: 
                <c:set var="totalPrice" value="0" />
                <c:forEach var="cartItem" items="${cartList}">
                    <c:set var="totalPrice" value="${totalPrice + cartItem.price * cartItem.quantity}" />
                </c:forEach>
                ${totalPrice} VND
            </div>

            <!-- Clear Cart button -->
            <div class="footer">
                <a href="/myCarts?action=clearCart" class="clear-cart-button">Clear Cart</a>
            </div>
        </div>
    </body>
</html>
