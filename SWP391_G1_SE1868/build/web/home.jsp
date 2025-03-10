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
                                        <h6><a href="#">${product.name}</a></h6>
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


        <!-- Latest Product Section End -->
        <%@include file="footer.jsp" %>
    </body>
</html>
