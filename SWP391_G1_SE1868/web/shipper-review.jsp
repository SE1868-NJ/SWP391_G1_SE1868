<%-- 
    Document   : shipper-review
    Created on : Mar 2, 2025, 8:59:53 PM
    Author     : Đạt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <title>shipper-review</title>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <style>
            .shipper-card {
                max-width: 500px;
                margin: 30px auto;
                padding: 20px;
                border-radius: 10px;
                background: #ffffff;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            }
            .shipper-avatar {
                width: 80px;
                height: 80px;
                border-radius: 50%;
                object-fit: cover;
            }
            .shipper-info {
                display: flex;
                flex-direction: column;
                align-items: flex-start;
            }
            .shipper-info p {
                display: flex;
                justify-content: space-between;
                width: 100%;
            }
            .shipper-info p strong {
                width: 40%;
            }
            .shipper-info p span {
                width: 60%;
                text-align: right;
            }
        </style>
        <div class="container">
            <div class="shipper-card">
                <!-- Ảnh đại diện -->
                <div class="text-center mb-3">
                    <img src="./assets/img/login.png" alt="Avatar" class="shipper-avatar">
                </div>

                <!-- Thông tin Shipper -->
                <h5 class="fw-bold text-center" id="shipper-name">${shipper.fullName}</h5>

                <div class="shipper-info">
                    <p><strong>Email:</strong> <span id="shipper-email">${shipper.email}</span></p>
                    <p><strong>Điện thoại:</strong> <span id="shipper-phone">${shipper.phoneNumber}</span></p>
                    <p><strong>Trạng thái:</strong> <span id="shipper-status" class="text-info ">${shipper.status}</span></p>
                    <p><strong>Ngày tạo:</strong> <span id="shipper-createdAt">${shipper.createdAt}</span></p>
                    <p><strong>Cập nhật lần cuối:</strong> <span id="shipper-updatedAt">${shipper.updatedAt}</span></p>
                    <p><strong>Trung bình số đánh giá:</strong> <span data-rating="${totalRating}" class="stars text-warning"></span><fmt:formatNumber maxFractionDigits="3" value="${totalRating}"/></p>
                    <p><strong>Tổng số đánh giá:</strong> <span class="text-info ">${totalReview}</span></p>
                </div>
            </div>


        </div>
        <!-- Bộ lọc số sao -->

        <div class="container">
            <div class="filter-rating d-flex align-items-center justify-content-between flex-wrap">
                <!-- Nút Back to Order căn trái -->
                <a href="/viewOrder" class="btn btn-outline-secondary">Back to Order</a>



                <!-- Các nút lọc căn giữa -->
                <div class="d-flex justify-content-center flex-wrap gap-2 filter-rating">
                    <button class="btn btn-outline-primary active" data-star="0" onclick="filterReviews(0, ${shipper.shipperId})">Tất cả</button>
                    <button class="btn btn-outline-primary" data-star="5" onclick="filterReviews(5, ${shipper.shipperId})">5 Sao</button>
                    <button class="btn btn-outline-primary" data-star="4" onclick="filterReviews(4, ${shipper.shipperId})">4 Sao</button>
                    <button class="btn btn-outline-primary" data-star="3" onclick="filterReviews(3, ${shipper.shipperId})">3 Sao</button>
                    <button class="btn btn-outline-primary" data-star="2" onclick="filterReviews(2, ${shipper.shipperId})">2 Sao</button>
                    <button class="btn btn-outline-primary" data-star="1" onclick="filterReviews(1, ${shipper.shipperId})">1 Sao</button>
                </div>

            </div>
        </div>





        <br>


        <div class="container d-flex justify-content-center">
            <ul class="w-100"  >
                <!-- Review 1 -->


                <c:forEach var="review" items="${shipperReviews}">

                    <li data-rating="${review.rating}" class="d-flex flex-column mb-3 p-3 border rounded">
                        <div class="d-flex justify-content-between">
                            <div class="d-flex align-items-center">
                                <img src="${review.customer.profileImage}" 
                                     alt="Avatar" 
                                     style="width: 50px" 
                                     onerror="this.onerror=null; this.src='./assets/img/login.png'" >



                                <span class="fw-bold">${review.customer.fullName}</span>
                            </div>
                            <span class="stars text-warning"></span>
                        </div>
                        <div class="mt-2">
                            <span class="ms-2">${review.comment}</span>
                        </div>
                        <!--${review.customer.customerId == sessionScope.user.customerId}-->
                        <c:if test="${review.customer.customerId == sessionScope.user.customerId}">
                            <div class="d-flex justify-content-end mt-auto">
                                <a href="updateShipperReview?reviewId=${review.reviewId}" class="btn btn-warning btn-sm">Sửa</a>
                            </div>
                        </c:if>
                    </li>
                </c:forEach>



                <!--                                <li data-rating="4" class="d-flex flex-column mb-3 p-3 border rounded">
                                                    <div class="d-flex justify-content-between">
                                                        <div class="d-flex align-items-center">
                                                            <img src="./assets/img/login.png" alt="Avatar" style="width: 50px">
                                                            <span class="fw-bold">Trần Thị B</span>
                                                        </div>
                                                        <span class="stars text-warning"></span>
                                                    </div>
                                                    <div class="mt-2">
                                                        <span class="ms-2">Sản phẩm ổn, nhưng giao hàng hơi chậm.</span>
                                                    </div>
                                                </li>
                                
                                               
                                                <li data-rating="3" class="d-flex flex-column mb-3 p-3 border rounded">
                                                    <div class="d-flex justify-content-between">
                                                        <div class="d-flex align-items-center">
                                                            <img src="./assets/img/login.png" alt="Avatar" style="width: 50px">
                                                            <span class="fw-bold">Lê Văn C</span>
                                                        </div>
                                                        <span class="stars text-warning"></span>
                                                    </div>
                                                    <div class="mt-2">
                                                        <span class="ms-2">Chất lượng khá, nhưng giá hơi cao so với mong đợi.</span>
                                                    </div>
                                                </li>-->

            </ul>
        </div>
        <hr/>
        <br>

        <nav aria-label="Order history pagination">
            <ul class="pagination justify-content-center">

                <c:if test="${totalPage > 1}">
                    <c:forEach var="pageNum" begin="1" end="${totalPage}">
                        <c:choose>
                            <c:when test="${pageNum == currentPage}">
                                <span class="page-link active">${pageNum}</span>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item "><a class="page-link" href="shipperReview?shipperId=${param.shipperId}&page=${pageNum}&&rating=${param.rating}">${pageNum}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:if>


            </ul>
        </nav>
        <!-- Popup Success -->
        <div id="successPopupp" class="popupp success" >
            <span>Cập nhập đánh giá thành công !!!!</span>
            <button class="close-btn" onclick="closePopupp('successPopupp')">×</button>
        </div>

        <!-- Popup Error -->
        <div id="errorPopupp" class="popupp error ">
            <span > Cập nhập đánh giá thất bại !!!!</span>
            <button class="close-btn " onclick="closePopupp('errorPopupp')">×</button>
        </div>

        <!--            // check Updateshipperreview succes or false-->
        <c:if test="${ not empty param.success}">
            <c:choose>

                <c:when test="${ param.success == 'true'}">
                    <script>
                        // Gọi hàm showPopup khi isSuccess = true
                        $(document).ready(function () {
                            showPopup('successPopupp');  // successPopup là ID của popup thành công
                        });
                    </script>
                </c:when>

                <c:when test="${ param.success == 'false'}">
                    <script>
                        // Gọi hàm showPopup khi isSuccess = true
                        $(document).ready(function () {
                            showPopup('errorPopupp');  // successPopup là ID của popup thành công
                        });
                    </script>
                </c:when>

            </c:choose>
        </c:if>
        <style>
            /* CSS chung cho popup */
            .popupp {
                position: fixed;
                bottom: 20px;
                right: 20px;
                padding: 15px 20px;
                border-radius: 8px;
                font-family: Arial, sans-serif;
                font-size: 16px;
                font-weight: bold;
                display: flex;
                align-items: center;
                gap: 10px;
                min-width: 350px;
                max-width: 400px;
                box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.3);
                opacity: 0;
                transform: translateX(100%);
                transition: opacity 0.4s ease, transform 0.4s ease;
                pointer-events: none;
                z-index: 9999;
            }

            /* Hiển thị popup */
            .popupp.show {
                opacity: 1;
                transform: translateX(0);
                pointer-events: auto;
            }

            /* Success Popup */
            .popupp.success {
                background-color: #4CAF50;
                color: white;
            }

            /* Error Popup */
            .popupp.error {
                background-color: #F44336;
                color: white;
            }

            /* Close Button */
            .popupp .close-btn {
                margin-left: auto;
                background: none;
                border: none;
                color: white;
                font-size: 18px;
                cursor: pointer;
            }
        </style>




        <script>
            // Hiển thị popup
            function showPopup(id) {
                let popup = document.getElementById(id);
                popup.classList.add("show");
            }



            // Đóng popup khi bấm nút close
            function closePopupp(id) {
                let popup = document.getElementById(id);
                popup.classList.remove("show");
            }
        </script>




        <%@include file="footer.jsp" %>
        <script>
            // Hàm hiển thị sao tự động
            function updateStars() {
                document.querySelectorAll(".stars").forEach(star => {
                    const rating = parseInt(star.closest("li")?.getAttribute("data-rating") || star.getAttribute("data-rating"));
                    if (rating === 5)
                        star.innerHTML = "★★★★★";
                    else if (rating === 4)
                        star.innerHTML = "★★★★☆";
                    else if (rating === 3)
                        star.innerHTML = "★★★☆☆";
                    else if (rating === 2)
                        star.innerHTML = "★★☆☆☆";
                    else if (rating === 1)
                        star.innerHTML = "★☆☆☆☆";
                    else
                        star.innerHTML = "☆☆☆☆☆";
                });
            }



            function filterReviews(star, shipperId) {
                // Lưu trạng thái của nút đã chọn vào localStorage
                localStorage.setItem("selectedStar", star);
                const url = "shipperReview?shipperId=" + shipperId + "&rating=" + star;
                console.log("Chuyển hướng đến URL:", url);
                window.location.href = url;
            }

            window.addEventListener("load", () => {
                const selectedStar = localStorage.getItem("selectedStar");
                // Xóa class "active" khỏi tất cả các nút trước khi thêm vào nút mới
                document.querySelectorAll(".filter-rating button").forEach(btn => btn.classList.remove("active"));

                if (selectedStar) {
                    const allButtons = document.querySelectorAll(".filter-rating button");
                    const activeButton = Array.from(allButtons).find(btn => btn.getAttribute("data-star") === selectedStar);
                    if (activeButton) {
                        activeButton.classList.add("active");
                    }
                }
            });

            updateStars();
        </script>
    </body>
</html>
