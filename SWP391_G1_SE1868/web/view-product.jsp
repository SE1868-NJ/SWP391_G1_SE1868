
<%-- 
    Document   : home
    Created on : Feb 18, 2025, 10:01:46 PM
    Author     : Đạt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">



    </head>
    <body>
        <%@include file="header.jsp" %>


        <section class="featured spad">
            <div class="container">

                <!-- Thanh tìm kiếm -->
                <div class="row">
                    <div class="col-lg-12">
                        <form class="row gx-3 align-items-center" action="products" method="GET">
                            <!-- Tiêu đề -->
                            <div class="col-auto">
                                <strong><h5 class=" btn text-success mb-0">Tìm kiếm sản phẩm</h5></strong>
                            </div>

                            <!-- Ô nhập -->
                            <div class="col flex-grow-1">
                                <input class="form-control" name="keyword" type="search" placeholder="Tìm kiếm sản phẩm...">
                            </div>

                            <!-- Nút tìm kiếm -->
                            <div class="col-auto">
                                <button class="btn btn-success" type="submit">Tìm kiếm</button>
                            </div>
                        </form>
                    </div>
                </div>
                <br>




                <div class="row">
                    <!-- Sidebar: Danh mục và Bộ lọc giá -->
                    <div class="col-lg-3">
                        <!-- Danh mục sản phẩm -->
                        <div class="list-group shadow-sm bg-white rounded mb-2 ">
                            <h5 class="btn btn-success text-dark"><a href="products?categoryId=" class=" btn btn-success"> Danh mục sản phẩm</a></h5>

                            <c:forEach var="category" items="${categorys}"  begin="0" end="6">
                                <a href="products?categoryId=${category.categoryId}" class="list-group-item list-group-item-action">
                                    ${category.name}
                                </a>
                            </c:forEach>

                            <!--                            <a href="?category=2" class="list-group-item list-group-item-action"> Laptop</a>
                                                        <a href="?category=3" class="list-group-item list-group-item-action"> Máy tính bảng</a>
                                                        <a href="?category=4" class="list-group-item list-group-item-action"> Phụ kiện</a>-->
                        </div>
                        
                        <br>

                        <!-- Bộ lọc giá -->
                        <div class="list-group mb-2 shadow-none bg-white rounded">
                            <h5 class="fw-bold text-success">Lọc theo giá</h5>
                            <br>

                            <form id="filterForm" action="products" method="GET">

                                <!-- Giá tối thiểu -->
                                <div class="mb-2">
                                    <label for="minPrice" class="form-label">Giá tối thiểu</label>
                                    <input type="number" class="form-control" id="minPrice" name="minPrice" placeholder="0" value="${param.minPrice}">
                                </div>

                                <!-- Giá tối đa -->
                                <div class="mb-2">
                                    <label for="maxPrice" class="form-label">Giá tối đa</label>
                                    <input type="number" class="form-control" id="maxPrice" name="maxPrice" placeholder="1000000" value="${param.maxPrice}">
                                </div>

                                <!-- Nút áp dụng -->
                                <button type="button" id="btnfilterForm" class="btn btn-success w-100">Áp dụng</button>
                                
                                <br>
                                 <br>

                                <!-- Sắp xếp theo -->
                                <div class="mb-3">
                                    <label class="fw-bold text-success">Sắp xếp theo</label>
                                    <div class="form-check">
                                        <input type="radio" class="form-check-input" name="sortBy" value="price" id="sortByPrice"
                                               ${param.sortBy == 'price' || empty param.sortBy ? 'checked' : ''} onchange="updateSort()">
                                        <label class="form-check-label" for="sortByPrice">Giá</label>
                                    </div>
                                    <div class="form-check">
                                        <input type="radio" class="form-check-input" name="sortBy" value="rating" id="sortByRating"
                                               ${param.sortBy == 'rating' ? 'checked' : ''} onchange="updateSort()">
                                        <label class="form-check-label" for="sortByRating">Đánh giá</label>
                                    </div>
                                </div>

                                <!-- Thứ tự -->
                                <div class="mb-3">
                                    <label class="fw-bold text-success">Thứ tự</label>
                                    <div class="form-check">
                                        <input type="radio" class="form-check-input" name="order" value="asc" id="orderAsc"
                                               ${param.order == 'asc' || empty param.order ? 'checked' : ''} onchange="updateSort()">
                                        <label class="form-check-label" for="orderAsc">Tăng dần</label>
                                    </div>
                                    <div class="form-check">
                                        <input type="radio" class="form-check-input" name="order" value="desc" id="orderDesc"
                                               ${param.order == 'desc' ? 'checked' : ''} onchange="updateSort()">
                                        <label class="form-check-label" for="orderDesc">Giảm dần</label>
                                    </div>
                                </div>
                        </div>
                                        
                        </form>

                        <br>




                    </div>

                    <!-- Danh sách sản phẩm -->
                    <div class="col-lg-9 ">
                        <div class="row featured__filter">
                            <c:forEach var="product" items="${products}">
                                <div class="col-lg-4 col-md-6 col-sm-6 mb-5">
                                    <div class="featured__item card p-3 shadow-sm rounded">
                                        <div class="featured__item__pic set-bg rounded" data-setbg="${product.images[2].imageUrl}" style="height: 200px; background-size: cover; background-position: center;">
                                        </div>
                                        <div class="featured__item__text text-center mt-3">
                                            <h6>
                                                <a href="/getReviews?productId=${product.productId}" class="text-dark text-decoration-none fw-bold">
                                                    ${product.name}
                                                </a>
                                            </h6>
                                            <p class="mb-1">
                                                <span class="text-warning fw-bold">
                                                    <fmt:formatNumber value="${product.avgRating}" maxFractionDigits="1"/> ⭐
                                                </span>
                                            </p>
                                            <h5 class="text-danger fw-bold">
                                                <fmt:formatNumber value="${product.price}" currencyCode="true"/>đ
                                            </h5>
                                            <c:if test="${product.stockQuantity > 0}">
                                                <form action="cart" method="post">
                                                    <input type="hidden" name="productId" value="${product.productId}">
                                                    <input type="hidden" name="action" value="add">
                                                    <input type="hidden" name="quantity" value="1">
                                                    <button type="submit" class="btn btn-success btn-sm mt-2">Thêm vào giỏ</button>
                                                </form>
                                            </c:if>
                                            <c:if test="${product.stockQuantity <= 0}">
                                                <button disabled class="btn btn-secondary btn-sm mt-2">
                                                    <i class="bi bi-cart-plus"></i> Hết hàng
                                                </button>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                        <nav class="pagination-md" >
                            <ul class="pagination justify-content-sm-start ">
                                <c:if test="${totalPages > 1}">
                                    <c:forEach var="pageNum" begin="1" end="${totalPages}">
                                        <c:choose>
                                            <c:when test="${pageNum == currentPage}">
                                                <span class="page-link active">${pageNum}</span>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item "><a class="page-link" href="products?keyword=${param.keyword}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}&sortBy=${param.sortBy}&order=${param.order}&page=${pageNum}&categoryId=${param.categoryId}">${pageNum}</a></li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </c:if>
                            </ul>
                        </nav>

                    </div>
                </div>
            </div>
        </section>

        <!-- JavaScript để cập nhật URL -->
        <script>
            function updateSort() {
                const urlParams = new URLSearchParams(window.location.search);
                const keyword = urlParams.get("keyword") || ""; // Lấy keyword từ URL

                const sortBy = document.querySelector('input[name="sortBy"]:checked')?.value || "";
                const order = document.querySelector('input[name="order"]:checked')?.value || "";

                const minPrice = document.getElementById("minPrice")?.value || "";
                const maxPrice = document.getElementById("maxPrice")?.value || "";

                // Kiểm tra nếu minPrice > maxPrice
                if (minPrice && maxPrice && parseFloat(minPrice) > parseFloat(maxPrice)) {
                    alert("Lỗi: Giá tối thiểu không thể lớn hơn giá tối đa!");
                    return; // Dừng lại nếu giá không hợp lệ
                }

                let url = "products?";
                let params = [];

                // Giữ nguyên keyword từ URL nếu có
                if (keyword)
                    params.push("keyword=" + encodeURIComponent(keyword));

                // Chỉ thêm minPrice & maxPrice nếu có nhập
                if (minPrice)
                    params.push("minPrice=" + encodeURIComponent(minPrice));
                if (maxPrice)
                    params.push("maxPrice=" + encodeURIComponent(maxPrice));

                // Thêm tham số sắp xếp
                if (sortBy)
                    params.push("sortBy=" + encodeURIComponent(sortBy));
                if (order)
                    params.push("order=" + encodeURIComponent(order));

                url += params.join("&");

                console.log("Redirecting to:", url);
                window.location.href = url;
            }


            document.addEventListener("DOMContentLoaded", function () {
                document.getElementById("btnfilterForm").addEventListener("click", function () {
                    const minPrice = document.getElementById("minPrice").value;
                    const maxPrice = document.getElementById("maxPrice").value;

                    const sortBy = document.querySelector('input[name="sortBy"]:checked')?.value || "";
                    const order = document.querySelector('input[name="order"]:checked')?.value || "";

                    // Kiểm tra nếu giá tối thiểu lớn hơn giá tối đa
                    if (minPrice && maxPrice && parseFloat(minPrice) > parseFloat(maxPrice)) {
                        alert("Lỗi: Giá tối thiểu không thể lớn hơn giá tối đa!");
                        return; // Dừng lại nếu giá không hợp lệ
                    }

                    // Lấy form và cập nhật action URL với tham số sortBy và order
                    let form = document.getElementById("filterForm");
                    form.submit();
                });
            });




        </script>





        <%@include file="footer.jsp" %>
    </body>
</html>
