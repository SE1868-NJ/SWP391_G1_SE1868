<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Blog Detail - Chợ Làng</title>
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css" type="text/css">

    <style>
        .blog-image {
            max-width: 65%; /* Giảm kích thước ảnh xuống còn 70% chiều rộng container */
            height: auto; /* Giữ tỷ lệ ảnh */
            border-radius: 8px;
            margin-bottom: 20px;
            display: block; /* Đảm bảo ảnh là block element */
            margin-left: auto; /* Căn giữa ảnh */
            margin-right: auto; /* Căn giữa ảnh */
        }
        .blog-title {
            font-size: 2.0rem;
            font-weight: 700;
            margin-bottom: 15px;
            color: #333;
        }
        .blog-date {
            font-size: 1rem;
            color: #6c757d;
            margin-bottom: 20px;
        }
        .blog-content {
            font-size: 1.1rem;
            line-height: 1.6;
            color: #555;
            white-space: pre-wrap; /* Giữ xuống dòng và wrap text */
        }
        .main-content {
            padding: 40px 0;
        }
    </style>
</head>
<body>
    <!-- Header -->
    <jsp:include page="header.jsp" />

    <!-- Nội dung chính -->
    <div class="main-content">
        <div class="container">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h1 class="blog-title">${blogDetail.title}</h1>
                    <!-- Hiển thị ảnh từ bloglist -->
                    <c:if test="${not empty blogDetail.imageUrl}">
                        <img src="${blogDetail.imageUrl}" class="blog-image" alt="Blog Image">
                    </c:if>
                    <!-- Ngày tạo đặt dưới ảnh -->
                    <p class="blog-date">
                        Ngày tạo: 
                        <fmt:formatDate value="${blogDetail.createdDateAsUtilDate}" pattern="dd/MM/yyyy" />
                    </p>
                    <div class="blog-content">${content}</div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="footer.jsp" />

    <!-- Scripts -->
    <script src="${pageContext.request.contextPath}/assets/js/jquery-3.3.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.nice-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery-ui.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.slicknav.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/mixitup.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/owl.carousel.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>
</html>