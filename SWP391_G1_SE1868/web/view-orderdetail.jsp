<%-- 
    Document   : view-orderdetail
    Created on : Mar 1, 2025, 1:53:29 PM
    Author     : Đạt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View OrderDetail</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container py-5">
            <div class="row mb-4">
                <div class="col-md-12">
                    <h2 class="mb-3 text-center">Order Detail </h2>
                    <hr class="mb-4">
                    <br>
                    <div class="row g-2">



                        <!-- Chọn tiêu chí sắp xếp -->
                        <div class="col-md-8">
                            <label class="d-block fw-bold">Sắp xếp theo:</label>
                            <div class="d-flex">
                                <div class="form-check me-3">
                                    <input class="form-check-input" type="radio" name="sortOrderDetailBy" id="quantity" value="quantity"
                                           ${sortOrderDetailBy == 'quantity' || sortOrderDetailBy == null ? 'checked' : ''} onchange="updateSort()">
                                    <label class="form-check-label" for="quantity">Số lượng </label>&nbsp;&nbsp;
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="sortOrderDetailBy" id="subTotal" value="subTotal"
                                           ${sortOrderDetailBy == 'subTotal' ? 'checked' : ''} onchange="updateSort()">
                                    <label class="form-check-label" for="subTotal">Tổng tiền</label>
                                </div>
                            </div>
                        </div>

                        <!-- Hướng sắp xếp -->
                        <div class="col-md-4">
                            <label class="d-block fw-bold">Hướng sắp xếp:</label>
                            <div class="d-flex">
                                <div class="form-check me-3">
                                    <input class="form-check-input" type="radio" name="sortOrderDetail" id="sortDesc" value="DESC"
                                           ${sortOrderDetail == 'DESC' || sortOrderDetail == null ? 'checked' : ''} onchange="updateSort()">
                                    <label class="form-check-label" for="sortDesc">Giảm dần</label>&nbsp;&nbsp;
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="sortOrderDetail" id="sortAsc" value="ASC"
                                           ${sortOrderDetail == 'ASC' ? 'checked' : ''} onchange="updateSort()">
                                    <label class="form-check-label" for="sortAsc">Tăng dần</label>
                                </div>
                            </div>
                        </div>


                    </div>
                </div>
            </div>

            <script>


                // Gửi request ngay khi người dùng thay đổi radio button
                function updateSort() {
                    const urlParams = new URLSearchParams(window.location.search);
                    const orderId = urlParams.get("orderId"); // Lấy orderId từ URL

                    const sortOrderDetailBy = document.querySelector('input[name="sortOrderDetailBy"]:checked')?.value || "";
                    const sortOrderDetail = document.querySelector('input[name="sortOrderDetail"]:checked')?.value || "";

                    // Xây dựng URL động
                    let url = "viewOrderDetail?";
                    let params = [];

                    // Luôn thêm orderId nếu có
                    if (orderId)
                        params.push("orderId=" + encodeURIComponent(orderId));

                    // Thêm tham số sắp xếp
                    if (sortOrderDetailBy)
                        params.push("sortOrderDetailBy=" + encodeURIComponent(sortOrderDetailBy));
                    if (sortOrderDetail)
                        params.push("sortOrderDetail=" + encodeURIComponent(sortOrderDetail));

                    url += params.join("&");

                    console.log("Redirecting to:", url);
                    window.location.href = url;
                }


            </script>





            <div>
                <table class="table  table-striped table-bordered">
                    <thead class="table-light">
                        <tr>
                            <th class="text-center" >OrderDetailId</th>
                            <th  class="text-center" >Product Name</th>
                            <th class="text-center" >Category Name</th>
                            <th class="text-center" >Product Image</th>
                            <th class="text-center" >Quantity</th>
                            <th class="text-center" >Unit Price</th>
                            <th class="text-center" >SubTotal</th>
                            <th class="text-center" >Actions</th>
                        </tr>
                    </thead>
                    <tbody> 
                        <c:forEach var="detail" items="${details}">
                            <tr>
                                <td>${detail.orderDetailId}</td>
                                <td>${detail.product.name}</td>

                                <td>${detail.product.category.name}</td>
                                <td><img src="${detail.product.images[0].imageUrl}" class="rounded-circle" style="width: 50px" alt="ProductImage" 
                                         onerror="this.onerror=null; this.src='./assets/img/login.png'" /></td>
                                <td>${detail.quantity}</td>
                                <td><fmt:formatNumber type="number" currencyCode="true" value="${detail.unitPrice}"/> đ</td>
                                <td><fmt:formatNumber type="number" currencyCode="true" value="${detail.subTotal}"/> đ</td>
                                <td>
                                    <div class="d-flex gap-2">
                                        <a href="addProductReview?orderDetailId=${detail.orderDetailId}" class="btn btn-sm btn-outline-primary">Đánh giá sản phẩm</a> &nbsp;
                                    </div>
                                </td>

                            </tr>
                        </c:forEach>



                    </tbody>
                </table>
                <hr class="mb-4">
                <br>
                <div class="d-flex justify-content-between align-items-center">
                    <a href="/viewOrder" class="btn btn-outline-primary">Back to Order</a>

                    <div class="ms-auto text-center px-5"> <!-- Thêm margin-end (me-auto) và padding-x (px-3) -->
                        <span>Total (VND): </span>
                        <strong><fmt:formatNumber type="number" value="${totalOrderDetail}" currencyCode="true"/> đ</strong>
                    </div>
                </div>



            </div>



        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>
