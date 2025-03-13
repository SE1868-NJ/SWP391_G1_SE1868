<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh Sách Blog</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        h2 {
            color: #333;
            padding-top: 20px;
        }

        .container {
            width: 90%;
            max-width: 1200px;
            margin: auto;
            background: white;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }

        /* Nút "Thêm Blog Mới" */
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

        /* Lưới Blog */
        .blog-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(280px, 1fr)); /* Điều chỉnh kích thước cell */
            gap: 20px;
            padding: 10px;
        }

        /* Các Card Blog */
        .blog-card {
            background: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s;
            padding: 15px;
            position: relative;
        }

        /* Chỉnh kích thước của các blog đặc biệt */
        .blog-card:nth-child(4n + 1) {
            grid-column: span 2;
            grid-row: span 2;
        }

        /* Hiệu ứng hover */
        .blog-card:hover {
            transform: translateY(-5px);
        }

        .blog-image {
            width: 100%;
            height: 200px;
            object-fit: cover;
            border-radius: 5px;
        }

        .blog-card:nth-child(4n + 1) .blog-image {
            height: 300px;
        }

        .blog-info {
            padding: 10px;
        }

        .blog-info h3 {
            font-size: 20px;
            color: #333;
            margin-bottom: 5px;
        }

        .blog-card:nth-child(4n + 1) .blog-info h3 {
            font-size: 24px;
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

    </style>
</head>
<body>
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
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <p class="no-data">Không có blog nào để hiển thị.</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
