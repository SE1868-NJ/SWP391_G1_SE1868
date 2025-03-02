<%-- 
    Document   : checkout
    Created on : Feb 23, 2025, 10:46:31 PM
    Author     : Đạt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>checkout</title>


    </head>
    <body>
        <%@include file="header.jsp"%>

        <div class="container">
            <main>
                <!--                cart-->

                <div class="row g-5">
                    <div class="col-md-6 col-lg-5 order-md-last">
                        <h4 class="d-flex justify-content-between align-items-center mb-3">
                            <span class="text-primary">Your cart</span>
                            <!--                            <span class="badge bg-primary rounded-pill">3</span>-->
                        </h4>
                        <ul class="list-group mb-3">

                            <!-- Sản phẩm 1 -->
                            <c:forEach var="cart" items="${requestScope.carts}">
                                <li class="list-group-item d-flex justify-content-between lh-sm align-items-center">
                                    <div class="d-flex">
                                        <img src="${cart.product.images[0].imageUrl}" class="rounded me-3 img-thumbnail" style="max-width: 40px;" 
                                             alt="Product Image" onerror="this.onerror=null; this.src='./assets/img/login.png' ">
                                        <div>
                                            <h6 class="my-0">${cart.product.name}</h6>
                                            <small class="text-muted">${cart.product.category.name}</small>
                                        </div>
                                    </div>
                                    <div class="text-end">
                                        <small class="text-muted">Price: <fmt:formatNumber type="number" value="${cart.product.price}" currencyCode="true"/>đ</small> <br>
                                        <small class="text-muted ">Quantity: ${cart.quantity}  </small> <br>
                                        <h6 class="text-dark">Total: <fmt:formatNumber type="number" value="${cart.product.price * cart.quantity}" currencyCode="true"/> đ</h6>
                                    </div>
                                </li>
                            </c:forEach>

                            <!--                             Sản phẩm 2 
                                                        <li class="list-group-item d-flex justify-content-between lh-sm align-items-center">
                                                            <div class="d-flex">
                                                                <img src="/assets/img/star-icon.png" class="rounded me-3 img-thumbnail" style="max-width: 40px;"  alt="Product Image">
                                                                <div>
                                                                    <h6 class="my-0">Another Product</h6>
                                                                    <small class="text-muted">Category: Clothing </small>
                                                                </div>
                                                            </div>
                                                            <div class="text-end">
                                                                <small class="text-muted">Price: 1000000</small> <br>
                                                                <small class="text-muted">Quantity:    2</small> <br>
                                                                <h6 class="text-dark">Total: 2000000</h6>
                                                            </div>
                                                        </li>-->


                            <li class="list-group-item d-flex justify-content-between">
                                <span>Total (VND)</span>
                                <strong><fmt:formatNumber type="number" value="${totalCart}" currencyCode="true"/> đ</strong>
                            </li>
                        </ul>


                    </div>

                    <!--                    billing-->
                    <div class="col-md-6 col-lg-7">
                        <h4 class="mb-3 text-lg-center">Billing address</h4>
                        <form action="checkoutsubmit" method="post" >
                            <div class="row g-3">




                                <div class="col-12">
                                    <label for="username" class="form-label">Username</label>
                                    <div >

                                        <input type="text" class="form-control" id="username" name ="name" value="${customer.fullName}" placeholder="Nguyen VAn A" >
                                        <div class="text-danger">${error_name}</div>
                                    </div>
                                </div>
  
                        
                       
                         
                                <div class="col-12">
                                    <label for="email" class="form-label">Email</label>
                                    <input type="email" class="form-control" id="email" name="email" value="${customer.email}"  placeholder="you@example.com">
                                   <div class="text-danger">${error_email}</div>
                                </div>

                                <!--                                <div class="col-12">
                                                                    <label for="address" class="form-label">Address</label>
                                                                    <input type="text" class="form-control" id="address" name="address" placeholder="Hà Nội" required>
                                                                    <div class="invalid-feedback">
                                                                        Please enter your shipping address.
                                                                    </div>
                                                                </div>-->

                                <div class="col-12">
                                    <label for="phone" class="form-label">Phone</label>
                                    <input type="text" class="form-control" id="phone" name="phone" value="${customer.phoneNumber}" placeholder="0975222222" >
                                      <div class="text-danger">${error_phone}</div>
                                </div>
                                
                                <div class="col-12">
                                    <label for="address" class="form-label">Address</label>
                                    <input type="text" class="form-control" id="address" name="address"   value="${customer.address}" placeholder="Hà Nội" >
                                      <div class="text-danger">${error_address}</div>
                                </div>

                             
                            </div>

                           

                            <h4 class="mb-3">Payment</h4>

                            <div class="my-3">
                                <div >
                                    <input id="ATM" name="paymentMethod" type="radio" value="ATM"  checked required>
                                    <label  for="ATM">ATM</label>
                                </div>
                                <div >
                                    <input id="debit" name="paymentMethod" type="radio" value="debit" required>
                                    <label  for="debit">Thanh toán khi nhận hàng</label>
                                </div>

                            </div>


                            <hr class="my-4">
                            <button class="w-100 btn btn-primary btn-lg" type="submit">Tiếp tục thanh toán</button>
                            <br>
                            <br>
                            <a href="/home" class="w-100 btn btn-primary btn-lg">Trở lại trang chủ</a>
                        </form>
                    </div>
                </div>
            </main>


        </div>

        <%@include file="footer.jsp"%>
    </body>
</html>
