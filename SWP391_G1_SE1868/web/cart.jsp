<%-- 
    Document   : cart
    Created on : Mar 6, 2025, 9:15:50 PM
    Author     : Đạt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <!-- Thêm Bootstrap CSS nếu chưa có (giả sử bạn đang sử dụng Bootstrap) -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container px-3 my-5 clearfix">
            <!-- Shopping cart table -->
            <div class="card">
                <div class="card-header">
                    <h2>Shopping Cart</h2>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered m-0">
                            <thead class="thead-light">
                                <tr>
                                    <th class="text-center py-3 px-4">Sản phẩm</th>
                                    <th class="text-center py-3 px-4">Cửa hàng</th>
                                    <th class="text-center py-3 px-4">Giá</th>
                                    <th class="text-center py-3 px-4">Số lượng</th>
                                    <th class="text-center py-3 px-4">Tổng</th>
                                    <th class="text-center py-3 px-0">Hành động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="cart" items="${carts}">
                                    <tr>

                                        <td class="p-4">
                                            <div class="d-flex align-items-center justify-content-start">
                                                <img src="${cart.product.images[2].imageUrl}" class="img-fluid rounded" style="width: 80px; height: auto;" alt="${cart.product.name}">
                                                <div class="ms-3">
                                                    <a href="/getReviews?productId=${cart.product.productId}" class="d-block text-dark fw-bold">${cart.product.name}</a>
                                                </div>
                                            </div>
                                        </td>

                                        <td class="text-center font-weight-semibold align-middle p-4">
                                            ${cart.product.shop.name}
                                        </td>

                                        <td class="text-center font-weight-semibold align-middle p-4">
                                            <fmt:formatNumber value="${cart.product.price}" currencySymbol="true"/> đ
                                        </td>

                                <form action="cart" method="post" id="addToCartForm${cart.product.productId}">
                                    <td class="align-middle text-center">
                                        <div class="d-flex justify-content-center align-items-center">
                                            <button type="button" class="btn btn-outline-primary btn-sm decreaseQuantity" id="decreaseQuantity" onclick="updateQuantity(${cart.product.productId}, -1)" 
                                                    data-id="${cart.product.productId}">
                                                -
                                            </button>

                                            <input class="form-control text-center mx-2"
                                                   style="width: 100px;" type="number" name="quantity" id="quantityInput${cart.product.productId}" 
                                                   value="${cart.quantity}" min="1" max="${cart.product.stockQuantity}" onchange="updateInputQuantity(${cart.product.productId})">

                                            <button type="button" class="btn btn-outline-primary btn-sm increaseQuantity" id="increaseQuantity" onclick="updateQuantity(${cart.product.productId}, 1)"
                                                    data-id="${cart.product.productId}">
                                                +
                                            </button>
                                        </div>
                                    </td>

                                    <input type="hidden" name="productId" value="${cart.product.productId}">
                                    <input type="hidden" name="action" id="actionQuantity${cart.product.productId}">

                                </form>

                                <td class="text-center font-weight-semibold align-middle p-4">
                                    <fmt:formatNumber value="${cart.product.price * cart.quantity}" currencySymbol="true"/> đ
                                </td>

                                <td class="text-center align-middle px-0">
                                    <a id="addToCartBtn${cart.product.productId}" onclick="removeProduct(${cart.product.productId})" class="btn btn-danger btn-sm">
                                        <strong class="bi bi-trash">Xóa</strongi> 
                                    </a>
                                </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <script>
                        function removeProduct(productId) {
                            let action = document.getElementById("actionQuantity" + productId);
                            action.value = 'remove';
                            submitUpdate(productId);
                        }

                        function updateInputQuantity(productId) {
                            let input = document.getElementById("quantityInput" + productId);
                            let action = document.getElementById("actionQuantity" + productId);
                            let maxStock = parseInt(input.max);

                            let value = parseInt(input.value);

                            if (isNaN(value) || value < 1) {
                                input.value = 1;
                                action.value = 'update';
                            } else if (value > maxStock) {
                                input.value = maxStock;
                                action.value = 'update';
                            } else {
                                input.value = value;
                                action.value = 'update';
                            }

                            submitUpdate(productId);
                        }

                        function updateQuantity(productId, change) {
                            let action = document.getElementById("actionQuantity" + productId);
                            let input = document.getElementById("quantityInput" + productId);
                            let maxStock = parseInt(input.max);
                            let minStock = 1;
                            let newValue = parseInt(input.value) + change;

                            if (newValue <= 0 && newValue <= maxStock) {
                                action.value = 'remove';
                                input.value = newValue;
                                submitUpdate(productId);
                            } else if (newValue >= minStock && newValue <= maxStock) {
                                input.value = newValue;
                                action.value = 'update';
                                submitUpdate(productId);
                            }
                        }

                        function submitUpdate(productId) {
                            document.getElementById("addToCartForm" + productId).submit();
                        }
                    </script>

                    <!-- / Shopping cart table -->
                    <div class="d-flex justify-content-end pb-4">
                        <div class="d-flex align-items-center">
                            <div class="text-left mt-4 ml-0">
                                <label class="text-muted font-weight-normal m-0">Total price</label>
                                <div class="text-large"><strong>
                                        <fmt:formatNumber value="${totalCart}" currencySymbol="true"/> đ
                                    </strong>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="float-right">
                        <a href="/home"><button type="button" class="btn btn-lg btn-default md-btn-flat mt-2 mr-3">Trở về trang chủ</button></a>
                        <a href="/checkout"><button type="button" class="btn btn-lg btn-primary mt-2">Checkout</button></a>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>