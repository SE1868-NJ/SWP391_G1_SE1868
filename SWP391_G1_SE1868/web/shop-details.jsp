<%-- 
    Document   : shop-details
    Created on : Feb 4, 2025, 12:18:14 PM
    Author     : Đạt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ogani | Template</title>


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
                                     src="assets/img/product/details/product-details-1.jpg" alt="">
                            </div>
                            <div class="product__details__pic__slider owl-carousel">
                                <img data-imgbigurl="img/product/details/product-details-2.jpg"
                                     src="assets/img/product/details/thumb-1.jpg" alt="">
                                <img data-imgbigurl="img/product/details/product-details-3.jpg"
                                     src="assets/img/product/details/thumb-2.jpg" alt="">
                                <img data-imgbigurl="img/product/details/product-details-5.jpg"
                                     src="assets/img/product/details/thumb-3.jpg" alt="">
                                <img data-imgbigurl="img/product/details/product-details-4.jpg"
                                     src="assets/img/product/details/thumb-4.jpg" alt="">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6">
                        <div class="product__details__text">
                            <h3>Vetgetable’s Package</h3>
                            <div class="product__details__rating">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star-half-o"></i>
                                <span>(18 reviews)</span>
                            </div>
                            <div class="product__details__price">$50.00</div>
                            <p>Mauris blandit aliquet elit, eget tincidunt nibh pulvinar a. Vestibulum ac diam sit amet quam
                                vehicula elementum sed sit amet dui. Sed porttitor lectus nibh. Vestibulum ac diam sit amet
                                quam vehicula elementum sed sit amet dui. Proin eget tortor risus.</p>
                            <div class="product__details__quantity">
                                <div class="quantity">
                                    <div class="pro-qty">
                                        <input type="text" value="1">
                                    </div>
                                </div>
                            </div>
                            <a href="#" class="primary-btn">ADD TO CARD</a>
                            <a href="#" class="heart-icon"><span class="icon_heart_alt"></span></a>
                            <ul>
                                <li><b>Availability</b> <span>In Stock</span></li>
                                <li><b>Shipping</b> <span>01 day shipping. <samp>Free pickup today</samp></span></li>
                                <li><b>Weight</b> <span>0.5 kg</span></li>
                                <li><b>Share on</b>
                                    <div class="share">
                                        <a href="#"><i class="fa fa-facebook"></i></a>
                                        <a href="#"><i class="fa fa-twitter"></i></a>
                                        <a href="#"><i class="fa fa-instagram"></i></a>
                                        <a href="#"><i class="fa fa-pinterest"></i></a>
                                    </div>
                                </li>
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
                                            <p><strong>Tổng số đánh giá:</strong> <span id="total-reviews">7</span></p>
                                            <p><strong>Trung bình:</strong> <span class="stars" id="average-rating" data-rating="4"> </span> 4.0/5.0</p>
                                        </div>

                                        <!-- Bộ lọc số sao -->
                                        <div class="filter-rating">
                                            <strong>Lọc theo số sao:</strong>
                                            <ul>
                                                <li><button data-star="0" class="filter-active" onclick="filterReviews(parseInt(this.getAttribute('data-star')))">Tất cả</button></li>
                                                <li><button data-star="5" onclick="filterReviews(parseInt(this.getAttribute('data-star')))">5 Sao</button></li>
                                                <li><button data-star="4" onclick="filterReviews(parseInt(this.getAttribute('data-star')))">4 Sao</button></li>
                                                <li><button data-star="3" onclick="filterReviews(parseInt(this.getAttribute('data-star')))">3 Sao</button></li>
                                                <li><button data-star="2" onclick="filterReviews(parseInt(this.getAttribute('data-star')))">2 Sao</button></li>
                                            </ul>
                                        </div>


                                        <!-- Danh sách đánh giá -->
                                        <ul class="review-list" id="review-list">
                                            <li data-rating="5"><span class="stars"></span> Nguyễn Văn A - "Rất tuyệt vời!"</li>
                                            <li data-rating="4"><span class="stars"></span> Trần Thị B - "Ổn, nhưng đóng gói chưa kỹ."</li>
                                            <li data-rating="3"><span class="stars"></span> Lê Văn C - "Giao hàng hơi chậm."</li>
                                            <li data-rating="5"><span class="stars"></span> Phạm Thị D - "Giá hợp lý, chất lượng tốt!"</li>
                                            <li data-rating="4"><span class="stars"></span> Hoàng Văn E - "Hàng dùng ổn, nhưng chờ lâu."</li>
                                            <li data-rating="2"><span class="stars"></span> Đặng Văn F - "Không như mong đợi."</li>
                                            <li data-rating="5"><span class="stars"></span> Trần Thị G - "Chất lượng tốt, đáng tiền!"</li>
                                        </ul>
                                    </div>
                                </div>
                                <!-- Bộ điều hướng phân trang -->
                                <div class="pagination-controls">
                                    <button id="prev-page" onclick="changePage(-1)">Trước</button>
                                    <span id="page-info"></span>
                                    <button id="next-page" onclick="changePage(1)">Sau</button>
                                </div>

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

                                // Hàm lọc đánh giá theo số sao
                                function filterReviews(star) {
                                    const reviews = document.querySelectorAll(".review-list li");

                                    // Ẩn/hiện đánh giá theo số sao
                                    reviews.forEach(review => {
                                        const rating = parseInt(review.getAttribute("data-rating"));
                                        review.style.display = (star === 0 || rating === star) ? "flex" : "none";
                                    });

                                    // Xóa class "filter-active" khỏi tất cả các nút trước khi thêm vào nút mới
                                    document.querySelectorAll(".filter-rating button").forEach(btn => btn.classList.remove("filter-active"));

                                    //  Kiểm tra danh sách nút trước khi tìm activeButton
                                    const allButtons = document.querySelectorAll(".filter-rating button");
                                    console.log("Danh sách tất cả nút:", allButtons);

                                    //  Tìm nút có `data-star` phù hợp
                                    const activeButton = Array.from(allButtons).find(btn => btn.getAttribute("data-star") == star);
                                    console.log("Nút active:", activeButton);

                                    if (activeButton) {
                                        activeButton.classList.add("filter-active");
                                    } else {
                                        console.warn(`Không tìm thấy nút với data-star = ${star}`);
                                    }
                                }

                                // Đảm bảo JavaScript chạy sau khi HTML load
//                                document.addEventListener("DOMContentLoaded", function () {
//                                    filterReviews(0);
//                                });




                                // Gọi hàm khi trang tải xong
                                updateStars();
                            </script>



                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Product Details Section End -->



        <!-- Related Product Section Begin -->
        <section class="related-product">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title related__product__title">
                            <h2>Related Product</h2>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-3 col-md-4 col-sm-6">
                        <div class="product__item">
                            <div class="product__item__pic set-bg" data-setbg="assets/img/product/product-1.jpg">
                                <ul class="product__item__pic__hover">
                                    <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                    <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                    <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                </ul>
                            </div>
                            <div class="product__item__text">
                                <h6><a href="#">Crab Pool Security</a></h6>
                                <h5>$30.00</h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-4 col-sm-6">
                        <div class="product__item">
                            <div class="product__item__pic set-bg" data-setbg="assets/img/product/product-2.jpg">
                                <ul class="product__item__pic__hover">
                                    <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                    <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                    <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                </ul>
                            </div>
                            <div class="product__item__text">
                                <h6><a href="#">Crab Pool Security</a></h6>
                                <h5>$30.00</h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-4 col-sm-6">
                        <div class="product__item">
                            <div class="product__item__pic set-bg" data-setbg="assets/img/product/product-3.jpg">
                                <ul class="product__item__pic__hover">
                                    <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                    <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                    <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                </ul>
                            </div>
                            <div class="product__item__text">
                                <h6><a href="#">Crab Pool Security</a></h6>
                                <h5>$30.00</h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-4 col-sm-6">
                        <div class="product__item">
                            <div class="product__item__pic set-bg" data-setbg="assets/img/product/product-7.jpg">
                                <ul class="product__item__pic__hover">
                                    <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                    <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                    <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                </ul>
                            </div>
                            <div class="product__item__text">
                                <h6><a href="#">Crab Pool Security</a></h6>
                                <h5>$30.00</h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Related Product Section End -->

        <%@include file="footer.jsp" %>


    </body>
</html>
