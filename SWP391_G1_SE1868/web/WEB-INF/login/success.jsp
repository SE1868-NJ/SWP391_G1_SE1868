<%-- 
    Document   : success
    Created on : Sep 20, 2024, 10:33:10 AM
    Author     : gu424
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Thành công</h1>
        <input name="name" type="text" required="" value="${name != null ? name : ''}"><br/>
        <a href="home">Về trang home</a><br/>
        <c:if test="${empty sessionScope.user}">
        <a href="login">Đăng nhập</a><br/>
        </c:if>
    </body>
</html>
