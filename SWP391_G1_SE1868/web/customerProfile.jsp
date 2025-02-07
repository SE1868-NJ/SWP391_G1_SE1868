<%-- 
    Document   : customerProfile.jsp
    Created on : Feb 7, 2025, 8:21:30 AM
    Author     : Acer Nitro 5
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Profile</title>
    <style>
        .form-container { width: 500px; margin: 20px auto; padding: 20px; border: 1px solid #ddd; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; }
        input, textarea { width: 100%; padding: 8px; }
        .readonly { background-color: #f5f5f5; }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Customer Profile</h2>
        <form id="profileForm" action="CustomerServlet" method="POST" onsubmit="return validateForm()">
            <div class="form-group">
                <label>Full Name:</label>
                <input type="text" name="customerName" value="${customer.customerName}" required>
            </div>
            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" value="${customer.email}" required>
            </div>
            <div class="form-group">
                <label>Phone Number:</label>
                <input type="tel" name="phoneNumber" value="${customer.phoneNumber}">
            </div>
            <div class="form-group">
                <label>Address:</label>
                <textarea name="address">${customer.address}</textarea>
            </div>
            <div class="form-group">
                <label>Created At:</label>
                <input type="text" class="readonly" 
                    value="<fmt:formatDate value="${customer.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly>
            </div>
            <div class="form-group">
                <label>Updated At:</label>
                <input type="text" class="readonly" 
                    value="<fmt:formatDate value="${customer.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly>
            </div>
            <button type="submit">Update Profile</button>
        </form>
    </div>

    <script>
        function validateForm() {
            const phone = document.forms["profileForm"]["phoneNumber"].value;
            if (phone && !/^\d+$/.test(phone)) {
                alert("Phone number must contain only digits.");
                return false;
            }
            return true;
        }
    </script>
</body>
</html>
