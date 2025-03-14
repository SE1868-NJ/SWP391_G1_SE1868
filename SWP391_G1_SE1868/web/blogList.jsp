<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Danh Sách Blog</title>
    <!-- Thêm Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .blog-card {
            height: 100%;
            display: flex;
            flex-direction: column;
            text-decoration: none;
            color: inherit;
        }
        .blog-image {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }
        .blog-info {
            padding: 15px;
            flex-grow: 1;
        }
        .add-blog {
            display: inline-block;
            padding: 8px 15px;
            text-decoration: none;
            color: white;
            border-radius: 5px;
            white-space: nowrap; /* Ngăn nút xuống dòng */
        }
        .no-data {
            text-align: center;
            padding: 20px;
        }
        .blog-card:hover {
            box-shadow: 0 0 10px rgba(0,0,0,0.2);
            text-decoration: none;
            color: inherit;
        }
        .card-title {
            margin: 0;
            font-weight: 700; /* In đậm hơn */
        }
        .blog-card:hover .card-title {
            display: block !important;
            visibility: visible !important;
            opacity: 1 !important;
        }
        /* Style cho header và thanh tìm kiếm */
        .header-section {
            display: flex;
            align-items: center; /* Căn giữa theo chiều dọc */
            justify-content: space-between; /* Phân bố đều không gian */
            flex-wrap: nowrap; /* Ngăn xuống dòng */
            gap: 15px; /* Khoảng cách giữa các phần tử */
        }
        .search-form {
            flex-grow: 1; /* Thanh tìm kiếm chiếm không gian còn lại */
            max-width: 500px; /* Giới hạn chiều rộng tối đa */
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="main-content">
        <div class="container py-4">
            <div class="header-section mb-4">
                <h2>Danh Sách Blog</h2>
                <!-- Thanh tìm kiếm -->
                <form class="search-form" action="${pageContext.request.contextPath}/blog" method="get">
                    <div class="input-group">
                        <input type="text" class="form-control" name="search" placeholder="Tìm kiếm bài viết..." 
                               value="${param.search}">
                        <button class="btn btn-primary" type="submit">Tìm</button>
                    </div>
                </form>
                <a href="addBlog.jsp" class="add-blog bg-primary">Thêm Blog Mới</a>
            </div>

            <c:choose>
                <c:when test="${not empty blogs}">
                    <div class="row row-cols-1 row-cols-md-3 g-4">
                        <c:forEach var="blog" items="${blogs}">
                            <div class="col">
                                <a href="${pageContext.request.contextPath}/BlogDetailServlet?id=${blog.id}" 
                                   class="card blog-card h-100">
                                    <c:if test="${not empty blog.imageUrl}">
                                        <img src="${blog.imageUrl}" class="card-img-top blog-image" alt="Blog Image">
                                    </c:if>
                                    <div class="blog-info">
                                        <h5 class="card-title">${blog.name}</h5>
                                        <p class="card-text"><strong>Mô tả:</strong> ${blog.description}</p>
                                        <p class="card-text"><strong>Khách hàng:</strong> ${blog.customerId}</p>
                                        <p class="card-text"><strong>Ngày tạo:</strong> 
                                            <fmt:formatDate value="${blog.createdDateAsUtilDate}" pattern="dd/MM/yyyy" />
                                        </p>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>

                    <!-- Phân trang -->
                    <nav aria-label="Page navigation" class="mt-4">
                        <ul class="pagination justify-content-center">
                            <c:forEach var="pageNum" begin="1" end="${totalPages}">
                                <li class="page-item ${pageNum == currentPage ? 'active' : ''}">
                                    <a class="page-link" 
                                       href="${pageContext.request.contextPath}/blog?page=${pageNum}&search=${param.search}">
                                        ${pageNum}
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </nav>
                </c:when>
                <c:otherwise>
                    <p class="no-data">Không có blog nào để hiển thị.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <jsp:include page="footer.jsp" />
    <!-- Thêm Bootstrap JS (tùy chọn) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>