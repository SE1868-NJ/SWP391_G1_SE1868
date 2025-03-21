<%-- 
    Document   : addProductReview
    Created on : Mar 17, 2025, 9:12:50 PM
    Author     : Đạt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>addShipperReview</title>
    </head>
     <%@include file="header.jsp" %>
    <body>

        <div class="container d-flex justify-content-center align-items-center" style="min-height:80vh;">
            
            <div class="card p-4" style="max-width: 500px; width: 100%;">
                <h4 class="text-center">Đánh giá sản phẩm </h4>
                <form action="addProductReview" method="post" >
                     <input type="hidden" name="orderDetailId" value="${orderDetail.orderDetailId}"">
                    <!-- Sản phẩm -->
                    <div class="mb-3">
                        <label for="productName" class="form-label">Sản phẩm:</label>
                        <input type="text" id="productName" name="productName" value="${orderDetail.product.name}"  class="form-control" readonly>
                    </div>

                    <!-- Khách hàng -->
                    <div class="mb-3">
                        <label for="customerName" class="form-label">Khách hàng:</label>
                        <input type="text" id="customerName" name="customerName" value="${sessionScope.user.fullName}"   class="form-control" readonly>
                    </div>

                    <!-- Đánh giá (Rating) -->
                    <div class="mb-3">
                        <label for="rating" class="form-label">Đánh giá:</label>
                        <div class="form-check form-check-inline" >
                            <input type="radio" name="rating" id="rating1" value="1" checked class="form-check-input" >
                            <label for="rating1" class="form-check-label">1 sao</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input type="radio" name="rating" id="rating2" value="2" class="form-check-input">
                            <label for="rating2" class="form-check-label">2 sao</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input type="radio" name="rating" id="rating3" value="3"  class="form-check-input">
                            <label for="rating3" class="form-check-label">3 sao</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input type="radio" name="rating" id="rating4" value="4"class="form-check-input">
                            <label for="rating4" class="form-check-label">4 sao</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input type="radio" name="rating" id="rating5" value="5" checked class="form-check-input">
                            <label for="rating5" class="form-check-label">5 sao</label>
                        </div>
                    </div>



                    <!-- Bình luận -->
                    <div class="mb-3">
                        <label for="comment" class="form-label">Bình luận:</label>
                        <textarea id="comment" name="comment" rows="4" class="form-control" required placeholder="Đánh giá vào đây"></textarea>
                    </div>

                    <!-- Nút Cập nhật -->
                    <div class="mb-3">
                        <button type="submit" class="btn btn-primary w-100">Thêm đánh giá</button>
                    </div>
                </form>
                    <div class="mb-3">
                        <a href="/viewOrder"><button type="button" class="btn btn-primary w-100">Quay lại đơn hàng</button></a>
                    </div>
            </div>
        </div>


    </body>
    <%@include file="footer.jsp"%>
</html>
