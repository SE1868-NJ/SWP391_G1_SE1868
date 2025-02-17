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

        <!-- Product Details Section Begin -->
        <section class="product-details spad">
            <div class="container">
                <div class="row">

                    <div class="col-lg-6 col-md-6">
                        <div class="product__details__pic">
                            <div class="product__details__pic__item">
                                <img class="product__details__pic__item--large"
                                     src="${product.images[1].imageUrl}"  alt=""
                                    >
                            </div>


                            <div class="thumbnail-images">
                                <c:forEach var="img" items="${product.images}" varStatus="status">
                                    <c:if test="${status.index < 5}"> <!-- Giới hạn số lượng ảnh hiển thị -->
                                        <img data-imgbigurl="${img.imageUrl}" 
                                             src="${img.imageUrl}" alt="Product Image" 
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
                                <li><b>Availability</b> <span>${product.stockQuantity}</span></li>

                                <li><b>Weight</b> <span>0.5 kg</span></li>

                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="product__details__tab">
                            <ul class="nav nav-tabs" role="tablist">
                                <li class="nav-item">
                                    Reviews
                                </li>
                            </ul>

                            <div class="tab-content">
                                <div class="tab-pane active" id="tabs-3" role="tabpanel">
                                    <div class="product__details__tab__desc">
                                        <h4>Danh sách đánh giá của người dùng</h4>

                                        <!-- Tổng số sao và trung bình đánh giá -->
                                        <div class="rating-summary">
                                            <p><strong>Tổng số đánh giá:</strong> <span id="total-reviews">${totalReview}</span></p>
                                            <p><strong>Trung bình:</strong> <span class="stars" id="average-rating" 
                                                                                  data-rating="<fmt:formatNumber value="${totalRating}" type="number"  maxFractionDigits="1" />"> </span>
                                                <fmt:formatNumber value="${totalRating}" type="number"  maxFractionDigits="1"  /> /5.0</p>
                                        </div>

                                        <!-- Bộ lọc số sao -->
                                        <div class="filter-rating">
                                            <strong>Lọc theo số sao:</strong>
                                            <ul>
                                                <li><button data-star="0" class="filter-active"  onclick="filterReviews(0, 1)">Tất cả</button></li>
                                                <li><button data-star="5" onclick="filterReviews(5, 1)">5 Sao</button></li>
                                                <li><button data-star="4" onclick="filterReviews(4, 1)">4 Sao</button></li>
                                                <li><button data-star="3" onclick="filterReviews(3, 1)">3 Sao</button></li>
                                                <li><button data-star="2" onclick="filterReviews(2, 1)">2 Sao</button></li>
                                                <li><button data-star="1" onclick="filterReviews(1, 1)">1 Sao</button></li>

                                            </ul>
                                        </div>
                                        <style>
                                            /* Bộ lọc số sao */
                                            .filter-rating ul {
                                                display: flex;
                                                gap: 10px;
                                                list-style: none;
                                                padding: 0;
                                            }



                                            .filter-rating button:hover  {
                                                background-color: #007bff;
                                                color: white;
                                            }


                                            /* Khi nút được chọn (active), nó đổi màu xanh */
                                            .filter-rating button.filter-active {
                                                background-color: #007bff;
                                                color: white;
                                            }


                                            /* Định dạng danh sách đánh giá */
                                            .review-list {
                                                list-style: none;
                                                padding: 0;
                                            }

                                            .review-list li {
                                                padding: 10px;
                                                border-bottom: 1px solid #ddd;
                                                display: flex;
                                                align-items: center;
                                                gap: 10px;
                                            }




                                            .pagination-controls {
                                                display: flex;
                                                justify-content: center;
                                                margin: 20px 0;
                                                gap: 10px;
                                            }

                                            .pagination-controls button {
                                                padding: 5px 10px;
                                                border: 1px solid #007bff;
                                                background-color: white;
                                                color: #007bff;
                                                cursor: pointer;
                                                border-radius: 3px;
                                            }

                                            .pagination-controls button:disabled {
                                                background-color: #ccc;
                                                color: #666;
                                                cursor: not-allowed;
                                            }

                                        </style>


                                        <!-- Danh sách đánh giá -->
                                        <ul class="review-list" id="review-list">
                                            <c:forEach var="review" items="${reviews}">

                                                <li data-rating="${review.rating}">
                                                    <img src="assets/img/star-icon.png"  alt="Avatar" class="avatar">
                                                    ${review.customer.fullName} -  ${review.comment}<span class="stars"></span></li>
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
                                <div class="pagination">
                                    <c:if test="${totalPages > 1}">
                                        <c:forEach var="pageNum" begin="1" end="${totalPages}">
                                            <c:choose>
                                                <c:when test="${pageNum == currentPage}">
                                                    <span class="page-link active">${pageNum}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <a class="page-link" href="getReviews?productId=1&page=${pageNum}&starFilter=${param.starFilter}">${pageNum}</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </c:if>
                                </div>
                                <style>

                                    .pagination {
                                        text-align: center;
                                        margin-top: 20px;
                                    }

                                    .page-link {
                                        display: inline-block;
                                        padding: 8px 15px;
                                        margin: 0 5px;
                                        text-decoration: none;
                                        font-size: 16px;
                                        color: #007bff;
                                        border: 1px solid #007bff;
                                        border-radius: 5px;
                                        transition: background 0.3s, color 0.3s;
                                    }

                                    .page-link:hover {
                                        background: #007bff;
                                        color: white;
                                    }

                                    .page-link.active {
                                        background: #007bff;
                                        color: white;
                                        font-weight: bold;
                                        border: 1px solid #0056b3;
                                        cursor: default;
                                    }




                                    .avatar {
                                        width: 40px; /* Kích thước ảnh */
                                        height: 40px;
                                        border-radius: 50%; /* Làm tròn ảnh */
                                        object-fit: cover; /* Giữ tỷ lệ ảnh không bị méo */
                                        margin-right: 10px;
                                        vertical-align: middle; /* Căn giữa với text */
                                    }
                                </style>

                            </div>



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
                                    document.querySelectorAll(".filter-rating button").forEach(btn => btn.classList.remove("filter-active"));

                                    if (selectedStar) {
                                        const allButtons = document.querySelectorAll(".filter-rating button");
                                        const activeButton = Array.from(allButtons).find(btn => btn.getAttribute("data-star") === selectedStar);
                                        if (activeButton) {
                                            activeButton.classList.add("filter-active");
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
