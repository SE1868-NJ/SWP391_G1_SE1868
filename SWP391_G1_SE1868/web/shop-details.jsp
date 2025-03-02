<%-- 
    Document   : shop-details
    Created on : Feb 4, 2025, 12:18:14 PM
    Author     : Đạt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>abc</title>


    </head>
    <body>

        <%@include file="header.jsp" %>
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

        <!--            // check UpdateProdcutreview succes or false-->
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




        <!-- Product Details Section Begin -->
        <section class="product-details spad">
            <div class="container">
                <div class="row">

                    <div class="col-lg-6 col-md-6">
                        <div class="product__details__pic">
                            <div class="product__details__pic__item">
                                <img class="rounded-circle"
                                     src="${product.images[1].imageUrl}"   alt=""
                                     >
                            </div>


                            <div class="thumbnail-images">
                                <c:forEach var="img" items="${product.images}" varStatus="status">
                                    <c:if test="${status.index < 5}"> <!-- Giới hạn số lượng ảnh hiển thị -->
                                        <img data-imgbigurl="${img.imageUrl}" 
                                             src="${img.imageUrl}"  alt="Product Image" 
                                             class="thumbnail" onclick="openPopup(this)">
                                    </c:if>
                                </c:forEach>
                            </div>

                            <!-- Popup để phóng to ảnh -->
                            <div id="imagePopup" class="popup" onclick="closePopup(event)">
                                <span class="close-btn" onclick="closePopup(event)">&times;</span>
                                <img id="popupImage" class="popup-content">
                            </div>

                            <style>
                                /* Style cho danh sách ảnh nhỏ */
                                .thumbnail-images {
                                    display: flex;
                                    gap: 10px;
                                    overflow: hidden;
                                    width: 80%;
                                }

                                .thumbnail {
                                    width: 80px;
                                    height: 80px;
                                    cursor: pointer;
                                    transition: transform 0.3s ease;
                                }

                                .thumbnail:hover {
                                    transform: scale(1.1);
                                }

                                /* Style cho popup phóng to ảnh */
                                .popup {
                                    display: none;  /* Ẩn mặc định */
                                    position: fixed;
                                    top: 0;
                                    left: 0;
                                    width: 100%;
                                    height: 100%;
                                    background-color: rgba(0, 0, 0, 0.8);
                                    display: flex;
                                    justify-content: center;
                                    align-items: center;
                                    z-index: 1000;
                                    opacity: 0;
                                    transition: opacity 0.3s ease-in-out;
                                }

                                .popup.show {
                                    opacity: 1;
                                }

                                .popup.show .popup-content {
                                    transform: scale(2);
                                }

                                /* Nút đóng popup */
                                .close-btn {
                                    position: absolute;
                                    top: 20px;
                                    right: 30px;
                                    font-size: 30px;
                                    color: white;
                                    cursor: pointer;
                                    background: none;
                                    border: none;
                                }

                                .close-btn:hover {
                                    color: red;
                                }

                            </style>


                        </div>
                    </div>




                    <div class="col-lg-6 col-md-6"> 
                        <div class="product__details__text">
                            <h3>${product.name}</h3>
                            <div class="product__details__rating">

                                <span class="stars" id="average-rating" 
                                      data-rating="<fmt:formatNumber value="${totalRating}" type="number"  maxFractionDigits="1" />"> 
                                </span>

                                <fmt:formatNumber value="${totalRating}" type="number"  maxFractionDigits="1"  />
                            </div>
                            <div class="product__details__price">
                                <fmt:formatNumber value="${product.price}" type="number" groupingUsed="true"/> đ
                            </div>


                            <p> ${product.category.name}<br>${product.description}</p>



                            <div class="product__details__quantity">
                                <div class="quantity">
                                    <div class="pro-qty">
                                        <input type="text" value="1">
                                    </div>
                                </div>
                            </div>
                            <a href="#" class="primary-btn">ADD TO CARD</a>


                            <ul>
                                <li><strong>Số lượng:</strong> &nbsp ${product.stockQuantity}  </li>

                                <li><strong>Ngày tạo:</strong> &nbsp ${product.getCreatedAt()} </li>
                            </ul>

                        </div>

                    </div>




                    <div class="col-lg-12">

                        <div class="product__details__tab">

                            <div class="col-lg-6 col-md-6 mb-4"> 
                                <a href="/home" class="primary-btn">Back to shop</a>
                                <br>
                                <br>
                                <div class="d-flex align-items-center p-3 border-0 shadow-sm">
                                    <!-- Icon shop -->

                                    <div class="icon-shop me-3">
                                        <img src="${product.shop.logo} " class="rounded-circle" style="width: 50px" alt="Avatar"
                                             onerror="this.onerror=null; this.src='./assets/img/login.png'" >
                                    </div>
                                    <!-- Thông tin shop -->
                                    <div>
                                        <h5 class="mb-1 fw-bold"><strong>${product.shop.name}</strong></h5> <!-- Tên shop -->
                                        <p class="mb-0  fw-normal"><strong>${product.shop.location}</strong></p> <!-- Địa chỉ shop -->
                                    </div>
                                </div>
                            </div>
                            <ul class="nav nav-tabs" role="tablist">
                                <li class="nav-item">
                                    Reviews
                                </li>
                            </ul>

                            <div class="tab-content">
                                <div class="tab-pane active" id="tabs-3" role="tabpanel">
                                    <div class="product__details__tab__desc">
                                        <h4>Danh sách đánh giá của người dùng</h4>




                                        <div class="container">
                                            <div class="filter-rating d-flex align-items-center justify-content-between flex-wrap">
                                                <!-- Tổng số sao và trung bình đánh giá -->
                                                <div class="rating-summary">
                                                    <p><strong>Tổng số đánh giá:</strong> <span id="total-reviews">${totalReview}</span></p>
                                                    <p><strong>Trung bình:</strong> <span class="stars" id="average-rating" 
                                                                                          data-rating="<fmt:formatNumber value="${totalRating}" type="number"  maxFractionDigits="1" />"> </span>
                                                        <fmt:formatNumber value="${totalRating}" type="number"  maxFractionDigits="1"  /> /5.0</p>
                                                </div>
                                                <!-- Các nút lọc căn giữa -->
                                                <div class="d-flex justify-content-center flex-wrap gap-2 filter-rating">
                                                    <button class="btn btn-outline-primary active" data-star="0" onclick="filterReviews(0, ${product.productId})">Tất cả</button>
                                                    <button class="btn btn-outline-primary" data-star="5" onclick="filterReviews(5, ${product.productId})">5 Sao</button>
                                                    <button class="btn btn-outline-primary" data-star="4" onclick="filterReviews(4, ${product.productId})">4 Sao</button>
                                                    <button class="btn btn-outline-primary" data-star="3" onclick="filterReviews(3, ${product.productId})">3 Sao</button>
                                                    <button class="btn btn-outline-primary" data-star="2" onclick="filterReviews(2, ${product.productId})">2 Sao</button>
                                                    <button class="btn btn-outline-primary" data-star="1" onclick="filterReviews(1, ${product.productId})">1 Sao</button>
                                                </div>

                                            </div>
                                        </div>
                                        <br>



                                        <!-- Danh sách đánh giá -->
                                        <ul>
                                            <c:forEach var="review" items="${reviews}">
                                                <li data-rating="${review.rating}" class="d-flex flex-column mb-3 p-3 border">


                                                    <!-- Phần hiển thị thông tin review -->
                                                    <div class="d-flex justify-content-between">

                                                        <!-- Phần chứa ảnh và tên người dùng trên cùng một hàng -->
                                                        <div class="d-flex align-items-center">
                                                            <img src="${review.customer.profileImage}" class="rounded-circle" alt="Avatar"  
                                                                 style="width: 50px"
                                                                 onerror="this.onerror=null; this.src='./assets/img/login.png'">
                                                                 <span class="fw-bold">${review.customer.fullName}</span>
                                                        </div>


                                                        <!-- Đánh giá sao -->
                                                        <span class="stars text-warning">${review.rating} </span>
                                                    </div>

                                                    <!-- Bình luận nằm dưới tên người dùng -->
                                                    <div class="mt-2">
                                                        <span class="ms-2">${review.comment}</span>
                                                    </div>

                                                    <!-- Nút "Sửa" nằm ở bên phải và ở dưới cùng -->
                                                    <c:if test="${review.customer.customerId == sessionScope.user.customerId}">
                                                        <div class="d-flex justify-content-end mt-auto">
                                                            <a href="updateProductReview?reviewId=${review.reviewId}" class="btn btn-warning btn-sm">Sửa</a>
                                                        </div>
                                                    </c:if>



                                                </li>
                                            </c:forEach>




                                            <!--                                            <li data-rating="4"><span class="stars"></span> Trần Thị B - "Ổn, nhưng đóng gói chưa kỹ."</li>
                                                                                        <li data-rating="3"><span class="stars"></span> Lê Văn C - "Giao hàng hơi chậm."</li>
                                                                                        <li data-rating="5"><span class="stars"></span> Phạm Thị D - "Giá hợp lý, chất lượng tốt!"</li>
                                                                                        <li data-rating="4"><span class="stars"></span> Hoàng Văn E - "Hàng dùng ổn, nhưng chờ lâu."</li>
                                                                                        <li data-rating="2"><span class="stars"></span> Đặng Văn F - "Không như mong đợi."</li>
                                                                                        <li data-rating="5"><span class="stars"></span> Trần Thị G - "Chất lượng tốt, đáng tiền!"</li>-->
                                        </ul>
                                    </div>
                                </div>
                                <!-- Phân trang -->

                                <nav aria-label="Order history pagination">
                                    <ul class="pagination justify-content-center">

                                        <c:if test="${totalPages > 1}">
                                            <c:forEach var="pageNum" begin="1" end="${totalPages}">
                                                <c:choose>
                                                    <c:when test="${pageNum == currentPage}">
                                                        <span class="page-link active">${pageNum}</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a class="page-link" href="getReviews?productId=${param.productId}&page=${pageNum}&starFilter=${param.starFilter}">${pageNum}</a>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:if>

                                    </ul>
                                </nav>



                                <script>


                                    // Hiển thị popup và phóng to ảnh
                                    function openPopup(imgElement) {
                                        let popup = document.getElementById("imagePopup");
                                        let popupImage = document.getElementById("popupImage");

                                        popupImage.src = imgElement.src;  // Lấy ảnh từ thumbnail
                                        popup.classList.add("show");  // Hiển thị popup
                                        popup.style.display = "flex";
                                    }

                                    // Đóng popup khi bấm vào X hoặc bên ngoài ảnh
                                    function closePopup(event) {
                                        let popup = document.getElementById("imagePopup");
                                        if (event.target === popup || event.target.classList.contains("close-btn")) {
                                            popup.classList.remove("show");
                                            setTimeout(() => {
                                                popup.style.display = "none"; // Delay để giữ hiệu ứng
                                            }, 300);
                                        }
                                    }

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



                                    function filterReviews(star, productId) {
                                        // Lưu trạng thái của nút đã chọn vào localStorage
                                        localStorage.setItem("selectedStar", star);
                                        const url = "getReviews?productId=" + productId + "&starFilter=" + star;
                                        console.log("Chuyển hướng đến URL:", url);
                                        window.location.href = url;
                                    }

                                    window.addEventListener("load", () => {
                                        const selectedStar = localStorage.getItem("selectedStar");
                                        // Xóa class "filter-active" khỏi tất cả các nút trước khi thêm vào nút mới
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


                            </div>
                        </div>
                    </div>
                </div>
        </section>
        <!-- Product Details Section End -->

        <%@include file="footer.jsp" %>


    </body>
</html>
