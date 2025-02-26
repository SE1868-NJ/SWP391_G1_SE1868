<%-- 
    Document   : view-order
    Created on : Feb 26, 2025, 1:53:25 PM
    Author     : Đạt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Order</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
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

        <div class="table-responsive">
            <table class="table table-hover table-striped">
                <thead class="table-light">
                    <tr>
                        <th scope="col">Order ID</th>
                        <th scope="col">Date</th>
                        <th scope="col">Product</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Unit Price</th>
                        <th scope="col">Total</th>
                        <th scope="col">Status</th>
                        <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>#ORD001</td>
                        <td>2024-01-15</td>
                        <td>Wireless Earbuds Pro</td>
                        <td>2</td>
                        <td>$129.99</td>
                        <td>$259.98</td>
                        <td><span class="badge bg-success">Completed</span></td>
                        <td>
                            <button class="btn btn-sm btn-outline-primary" data-bs-toggle="modal" data-bs-target="#orderModal">View</button>
                        </td>
                    </tr>
                    <tr>
                        <td>#ORD002</td>
                        <td>2024-01-14</td>
                        <td>Smart Watch Elite</td>
                        <td>1</td>
                        <td>$199.99</td>
                        <td>$199.99</td>
                        <td><span class="badge bg-warning text-dark">Pending</span></td>
                        <td>
                            <button class="btn btn-sm btn-outline-primary" data-bs-toggle="modal" data-bs-target="#orderModal">View</button>
                        </td>
                    </tr>
                    <tr>
                        <td>#ORD003</td>
                        <td>2024-01-13</td>
                        <td>Phone Case Premium</td>
                        <td>3</td>
                        <td>$29.99</td>
                        <td>$89.97</td>
                        <td><span class="badge bg-danger">Cancelled</span></td>
                        <td>
                            <button class="btn btn-sm btn-outline-primary" data-bs-toggle="modal" data-bs-target="#orderModal">View</button>
                        </td>
                    </tr>
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
