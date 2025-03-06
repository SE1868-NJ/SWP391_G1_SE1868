<%-- 
    Document   : home
    Created on : Feb 18, 2025, 10:01:46 PM
    Author     : Đạt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%@include file="header.jsp" %>
        <%@include file="chat.jsp" %>

    <body>
        <!-- Categories Section Begin -->
        <section class="categories">
            <div class="container">
                <div class="row">
                    <div class="categories__slider owl-carousel">
                    </div>
                </div>
            </div>
        </section>
        <!-- Categories Section End -->

        <!-- Featured Section Begin -->
        <section class="featured spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title">
                            <h2>Sản phẩm nổi bật</h2>
                        </div>
                    </div>
                    <div class="row featured__filter">
                        <c:forEach var="product" items="${products}">
                            <div class="col-lg-3 col-md-4 col-sm-6 mix oranges fresh-meat">
                                <div class="featured__item">
                                    <div class="featured__item__pic set-bg" data-setbg="${product.imageUrl}">
                                        
                                        <ul class="featured__item__pic__hover">
                                            <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                                    <c:if test="${product.stockQuantity > 0}">
                                                        <li><a href="/myCarts?action=addToCart&productId=${product.productId}"><i class="fa fa-shopping-cart"></i></a></li>
                                                    </c:if>
                                                    <c:if test="${product.stockQuantity <= 0}">
                                                        <button disabled class="add-to-cart-button">Hết hàng</button>
                                                    </c:if>
                                        </ul>
                                    </div>
                                    <div class="featured__item__text">
                                        <h6><a href="/getReviews?productId=${product.productId}">${product.name}</a></h6>
                                        <h5>$${product.price}</h5>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
        </section>
        <!-- Featured Section End -->

        <!-- Banner Begin -->
        <div class="banner">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-6">
                        <div class="banner__pic">
                            <img src="assets/img/banner/banner-1.jpg" alt="">
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6">
                        <div class="banner__pic">
                            <img src="assets/img/banner/banner-2.jpg" alt="">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Banner End -->

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
        
         <!--        thông báo khi đã thanh toán thành công  -->
        <!-- Popup Success -->
        <div id="successPopuppPay" class="popupp success" >
            <span>Đã thanh toán đơn hàng ${paymentResponse.vnp_OrderInfo} thành công!!!!</span>
            <button class="close-btn" onclick="closePopupp('successPopuppPay')">×</button>
        </div>

        <!-- Popup Error -->
        <div id="errorPopuppPay" class="popupp error ">
            <span > Đã xảy ra lỗi thanh toán đơn hàng ${paymentResponse.vnp_OrderInfo}!!!!</span>
            <button class="close-btn " onclick="closePopupp('errorPopuppPay')">×</button>
        </div>


       

       
            <c:choose>

                <c:when test="${ paymentResponse.vnp_ResponseCode == '00'}">
                    <script>
                        // Gọi hàm showPopup khi isSuccess = true
                        $(document).ready(function () {
                            showPopup('successPopuppPay');  // successPopup là ID của popup thành công
                        });
                    </script>
                </c:when>

                <c:when test="${ paymentResponse.vnp_ResponseCode == '24'}">
                    <script>
                        // Gọi hàm showPopup khi isSuccess = true
                        $(document).ready(function () {
                            showPopup('errorPopuppPay');  // successPopup là ID của popup thành công
                        });
                    </script>
                </c:when>

            </c:choose>
       

                    
                    
                    
                    
                     <!--        thông báo khi đặt hàng thành công  -->
        <!-- Popup Success -->
        <div id="successPopuppOrder" class="popupp success" >
            <span>Đã đặt thành công!!!!</span>
            <button class="close-btn" onclick="closePopupp('successPopuppOrder')">×</button>
        </div>

        <!-- Popup Error -->
        <div id="errorPopuppOrder" class="popupp error ">
            <span > Đã xảy ra lỗi khi đặt hàng!!!!</span>
            <button class="close-btn " onclick="closePopupp('errorPopuppOrder')">×</button>
        </div>


       

       
            <c:choose>

                <c:when test="${ (param.statusOrder == 'true') || (statusOrder =='true') }">
                    <script>
                        // Gọi hàm showPopup khi isSuccess = true
                        $(document).ready(function () {
                            showPopup('successPopuppOrder');  // successPopup là ID của popup thành công
                        });
                    </script>
                </c:when>

                <c:when test="${ (param.statusOrder == 'false')  || (statusOrder =='false')}">
                    <script>
                        // Gọi hàm showPopup khi isSuccess = true
                        $(document).ready(function () {
                            showPopup('errorPopuppOrder');  // successPopup là ID của popup thành công
                        });
                    </script>
                </c:when>

            </c:choose>
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
        <!-- Latest Product Section End -->
        <%@include file="footer.jsp" %>
    </body>
</html>
