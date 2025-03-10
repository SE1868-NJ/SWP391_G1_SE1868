<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quên mật khẩu</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Quên mật khẩu</h2>
        <form action="forgot-password" method="POST">
            <div class="form-group">
                <label for="email">Nhập email của bạn</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <button type="submit" class="btn btn-primary mt-3">Gửi mã xác nhận</button>
        </form>
        
        <c:if test="${not empty error}">
            <div class="alert alert-danger mt-3">
                ${error}
            </div>
        </c:if>

        <div class="mt-3">
            <a href="/WEB-INF/login/login.jsp">Trở lại trang đăng nhập</a>
        </div>
    </div>
</body>
</html>
