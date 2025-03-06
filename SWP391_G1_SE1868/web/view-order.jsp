<%-- 
    Document   : view-order
    Created on : Feb 26, 2025, 1:53:25 PM
    Author     : Đạt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Order</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
                <%@include file="chat.jsp" %>

        <div class="container py-5">
            <div class="row mb-4">
                <div class="col-md-8">
                    <h2 class="mb-3">Order History</h2>
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" placeholder="Search orders..." aria-label="Search orders">
                        <input type="date" class="form-control" aria-label="Start date">
                        <input type="date" class="form-control" aria-label="End date">
                        <button class="btn btn-primary" type="button">Search</button>
                    </div>
                </div>
            </div>

            <div>
                <table class="table  table-striped table-bordered">
                    <thead class="table-light">
                        <tr>
                            <th >OrderID</th>
                            <th >Date</th>
                            <th >Total</th>
                            <th >Status</th>
                            <th >Address</th>
                            <th >Shipper</th>
                            <th >PaymentMethod</th>

                            <th >Actions</th>
                        </tr>
                    </thead>
                    <tbody> 
                        <c:forEach var="order" items="${orders}">
                            <tr>
                                <td>${order.orderId}</td>
                                <td>${order.orderDate}</td>
                                <td><fmt:formatNumber type="number" currencyCode="true" value="${order.totalAmount}"/> đ</td>
                                <td>${order.status}</td>
                                <td>${order.shippingAddress}</td>
                                <td>${order.shipper.fullName}</td>
                                <td>${order.payment.paymentMethod !=null ?"ATM" :"Thanh toán khi nhận hàng"}</td>
                                <td>
                                    <button class="btn btn-sm btn-outline-primary" >View</button>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>

            <nav aria-label="Order history pagination">
                <ul class="pagination justify-content-center">
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1">Previous</a>
                    </li>
                    <li class="page-item active"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item">
                        <a class="page-link" href="#">Next</a>
                    </li>
                </ul>
            </nav>

        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
