<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Danh Sách Blog</title>
    <style>
        /* CSS chung */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        
        /* Nội dung chính */
        .main-content {
            flex: 1;
            padding: 0; /* Xóa padding để dính sát với header và footer */
            margin: 0; /* Xóa margin để dính sát */
            text-align: center;
        }
        
        .container {
            width: 90%;
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        
        .add-blog {
            display: inline-block;
            background: #28a745;
            color: white;
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 5px;
            margin-bottom: 20px;
            font-size: 16px;
        }
        
        .add-blog:hover {
            background: #218838;
        }
        
        .blog-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
            gap: 20px;
            padding: 10px;
        }
        
        .blog-card {
            background: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s;
            padding: 15px;
        }
        
        .blog-card:hover {
            transform: translateY(-5px);
        }
        
        .blog-image {
            width: 100%;
            height: 200px;
            object-fit: cover;
            border-radius: 5px;
        }
        
        .blog-info {
            padding: 10px;
        }
        
        .blog-info h3 {
            font-size: 20px;
            color: #333;
            margin-bottom: 5px;
        }
        
        .blog-info p {
            font-size: 14px;
            color: #666;
            margin: 5px 0;
        }
        
        .no-data {
            padding: 20px;
            font-size: 16px;
            color: #666;
        }
        
        .pagination {
            display: inline-flex;
            list-style: none;
            padding: 0;
            margin: 20px 0;
            justify-content: center;
        }
        
        .pagination li {
            margin: 0 5px;
        }
        
        .pagination a {
            display: block;
            padding: 8px 12px;
            background: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        
        .pagination a:hover {
            background: #218838;
        }
        
        .pagination .active a {
            background: #333;
        }
        
        .pagination .disabled a {
            background: #ddd;
        }
        
        /* Footer */
        .footer {
            background: #f8f8f8;
            padding: 20px 0;
            text-align: center;
            margin: 0; /* Xóa margin để dính sát */
        }
        
        .footer__about ul {
            list-style: none;
            padding: 0;
        }
        
        .footer__about ul li {
            margin: 5px 0;
            color: #666;
        }
    </style>
</head>
<body>
    <!-- Header -->
    <jsp:include page="header.jsp" />

    <!-- Nội dung chính -->
    <div class="main-content">
        <div class="container">
            <h2>Danh Sách Blog</h2>
            <a href="addBlog.jsp" class="add-blog">Thêm Blog Mới</a>

            <c:choose>
                <c:when test="${not empty blogs}">
                    <div class="blog-grid">
                        <c:forEach var="blog" items="${blogs}">
                            <div class="blog-card">
                                <c:if test="${not empty blog.imageUrl}">
                                    <img src="${blog.imageUrl}" class="blog-image" alt="Blog Image">
                                </c:if>
                                <div class="blog-info">
                                    <h3>${blog.name}</h3>
                                    <p><strong>Mô tả:</strong> ${blog.description}</p>
                                    <p><strong>Khách hàng:</strong> ${blog.customerId}</p>
                                    <p><strong>Ngày tạo:</strong> 
                                        <fmt:formatDate value="${blog.createdDate}" pattern="dd/MM/yyyy HH:mm:ss" />
                                    </p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="pagination">
                        <c:forEach var="pageNum" begin="1" end="${totalPages}">
                            <li class="${pageNum == currentPage ? 'active' : ''}">
                                <a href="${pageContext.request.contextPath}/blog?page=${pageNum}">${pageNum}</a>
                            </li>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <p class="no-data">Không có blog nào để hiển thị.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="footer.jsp" />
</body>
</html>