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
        <!-- Bootstrap 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container-lg">
            <div class="row mb-4">
                <div class="col-md-12">
                    <h2 class="mb-3 text-center">Order History</h2>

                    <hr class="mb-4">
                    <br>
                    <div class="row g-2">
                        <!-- Input ngày và nút Search cùng hàng -->
                        <div class="col-md-4">
                            <input type="date" class="form-control" id="startDate" name="startDate" aria-label="Start date">
                        </div>
                        <div class="col-md-4">
                            <input type="date" class="form-control" id="endDate" name="endDate" aria-label="End date">
                        </div>
                        <div class="col-md-4 d-flex align-items-end">
                            <button class="btn btn-primary w-100" type="button" onclick="searchOrders()">Search</button>
                        </div>


                        <!-- Chọn tiêu chí sắp xếp -->
                        <div class="col-md-8">
                            <label class="d-block fw-bold">Sắp xếp theo:</label>
                            <div class="d-flex">
                                <div class="form-check me-3">
                                    <input class="form-check-input" type="radio" name="sortBy" id="sortByDate" value="orderDate"
                                           ${sortBy == 'orderDate' || sortBy == null ? 'checked' : ''} onchange="updateSort()">
                                    <label class="form-check-label" for="sortByDate">Ngày đặt hàng</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="sortBy" id="sortByTotal" value="totalAmount"
                                           ${sortBy == 'totalAmount' ? 'checked' : ''} onchange="updateSort()">
                                    <label class="form-check-label" for="sortByTotal">Tổng tiền</label>
                                </div>
                            </div>
                        </div>

                        <!-- Hướng sắp xếp -->
                        <div class="col-md-4">
                            <label class="d-block fw-bold">Hướng sắp xếp:</label>
                            <div class="d-flex">
                                <div class="form-check me-3">
                                    <input class="form-check-input" type="radio" name="sortOrder" id="sortDesc" value="DESC"
                                           ${sortOrder == 'DESC' || sortOrder == null ? 'checked' : ''} onchange="updateSort()">
                                    <label class="form-check-label" for="sortDesc">Giảm dần</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="sortOrder" id="sortAsc" value="ASC"
                                           ${sortOrder == 'ASC' ? 'checked' : ''} onchange="updateSort()">
                                    <label class="form-check-label" for="sortAsc">Tăng dần</label>
                                </div>
                            </div>
                        </div>


                    </div>
                </div>
            </div>

            <script>
                function searchOrders() {
                    const startDate = document.getElementById("startDate")?.value || "";
                    const endDate = document.getElementById("endDate")?.value || "";
                    const sortBy = document.querySelector('input[name="sortBy"]:checked')?.value || "";
                    const sortOrder = document.querySelector('input[name="sortOrder"]:checked')?.value || "";

                    console.log("Start Date:", startDate);
                    console.log("End Date:", endDate);
                    console.log("Sort By:", sortBy);
                    console.log("Sort Order:", sortOrder);

                    // Nếu người dùng nhập một trong hai ngày nhưng thiếu ngày còn lại
                    if ((startDate && !endDate) || (!startDate && endDate)) {
                        alert("Vui lòng nhập cả ngày bắt đầu và ngày kết thúc hoặc để trống cả hai!");
                        return;
                    }

                    // Nếu có ngày bắt đầu và ngày kết thúc, kiểm tra hợp lệ
                    if (startDate && endDate && new Date(startDate) > new Date(endDate)) {
                        alert("Lỗi: Ngày bắt đầu không thể lớn hơn ngày kết thúc!");
                        return;
                    }

                    // Xây dựng URL động: nếu người dùng không nhập ngày thì không thêm vào URL
                    let url = "viewOrder?";
                    let params = [];

                    if (startDate)
                        params.push("startDate=" + encodeURIComponent(startDate));
                    if (endDate)
                        params.push("endDate=" + encodeURIComponent(endDate));
                    if (sortBy)
                        params.push("sortBy=" + encodeURIComponent(sortBy));
                    if (sortOrder)
                        params.push("sortOrder=" + encodeURIComponent(sortOrder));

                    url += params.join("&"); // Ghép các tham số lại với "&"

                    console.log("Redirecting to:", url);
                    window.location.href = url; // Chuyển hướng đến URL mới
                }


                // Gửi request ngay khi người dùng thay đổi radio button
                function updateSort() {


                    const sortBy = document.querySelector('input[name="sortBy"]:checked')?.value || "";
                    const sortOrder = document.querySelector('input[name="sortOrder"]:checked')?.value || "";

                    const startDate = document.getElementById("startDate")?.value || "";
                    const endDate = document.getElementById("endDate")?.value || "";

                    // Nếu chỉ nhập một trong hai ngày, yêu cầu nhập đủ
                    if ((startDate && !endDate) || (!startDate && endDate)) {
                        alert("Vui lòng nhập cả ngày bắt đầu và ngày kết thúc hoặc để trống cả hai!");
                        return;
                    }

                    // Nếu ngày bắt đầu lớn hơn ngày kết thúc, báo lỗi
                    if (startDate && endDate && new Date(startDate) > new Date(endDate)) {
                        alert("Lỗi: Ngày bắt đầu không thể lớn hơn ngày kết thúc!");
                        return;
                    }

                    // Xây dựng URL động
                    let url = "viewOrder?";
                    let params = [];

                    // Chỉ thêm startDate & endDate nếu người dùng nhập đủ ngày
                    if (startDate && endDate) {
                        params.push("startDate=" + encodeURIComponent(startDate));
                        params.push("endDate=" + encodeURIComponent(endDate));
                    }

                    // Thêm tham số sắp xếp
                    if (sortBy)
                        params.push("sortBy=" + encodeURIComponent(sortBy));
                    if (sortOrder)
                        params.push("sortOrder=" + encodeURIComponent(sortOrder));

                    url += params.join("&");

                    console.log("Redirecting to:", url);
                    window.location.href = url;
                }


            </script>


            <br>



            <div >
                <table class="table  table-striped table-bordered text-nowrap">
                    <thead class="table-light">
                        <tr>
                            <th class="text-center" >OrderID</th>
                            <th  class="text-center">Date</th>
                            <th  class="text-center">Total</th>
                            <th  class="text-center">Status</th>
                            <th  class="text-center">Address</th>
                            <th  class="text-center">Shipper</th>
                            <th class="text-center">PaymentMethod</th>

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
                                <td>${order.payment.paymentMethod }</td>
                                <td>
                                    <div class="d-flex gap-2">
                                        <a href="/viewOrderDetail?orderId=${order.orderId}" class="btn btn-sm btn-outline-primary">Xem</a> &nbsp;

                                        <a href="/shipperReview?shipperId=${order.shipper.shipperId}&orderId=${order.orderId}" class="btn btn-sm btn-outline-primary">Đánh giá shipper</a> &nbsp;
                                    </div>
                                </td>

                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>

            <nav aria-label="Order history pagination">
                <ul class="pagination justify-content-center">


                    <c:if test="${totalPage > 1}">
                        <c:forEach var="pageNum" begin="1" end="${totalPage}">
                            <c:choose>
                                <c:when test="${pageNum == currentPage}">
                                    <span class="page-link active">${pageNum}</span>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item "><a class="page-link" href="viewOrder?startDate=${param.startDate}&endDate=${param.endDate}&sortBy=${param.sortBy}&sortOrder=${param.sortOrder}&page=${pageNum}">${pageNum}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:if>

                </ul>
            </nav>

        </div>

        <!-- Popup Success -->
        <div id="successPopupp" class="popupp success" >
            <span>Thêm đánh giá sản phẩm !!!!</span>
            <button class="close-btn" onclick="closePopupp('successPopupp')">×</button>
        </div>

        <!-- Popup Error -->
        <div id="errorPopupp" class="popupp error ">
            <span > Thêm đánh giá sản phẩm thất bại !!!!</span>
            <button class="close-btn " onclick="closePopupp('errorPopupp')">×</button>
        </div>

        <!--            // check addProdcutreview succes or false-->

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



        <%@include file="footer.jsp" %>
    </body>
</html>
