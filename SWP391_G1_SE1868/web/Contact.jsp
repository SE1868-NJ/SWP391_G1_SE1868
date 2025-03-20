<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Liên hệ với chúng tôi</title>
        <!-- Bootstrap 5 CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- CSS từ header.jsp -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css" type="text/css">
        <style>
            body {
                display: flex;
                flex-direction: column;
                min-height: 100vh;
                margin: 0; /* Loại bỏ margin mặc định của body */

            }
            .main-content {
                flex: 1;
                padding: 10px 0; /* Giảm padding từ 40px xuống 10px */
            }
            .contact-form {
                max-width: 800px;
                margin: 0 auto;
                padding: 15px; /* Giảm padding từ 20px xuống 15px */
                background: #fff;
                border-radius: 5px;

            }
            .contact-heading {
                color: #1a3066;
                font-weight: bold;
                margin-bottom: 20px; /* Giảm từ 25px xuống 20px */
            }
            .contact-info {
                margin-bottom: 20px; /* Giảm từ 30px xuống 20px */
            }
            .contact-info p {
                margin-bottom: 5px;
            }
            .contact-form .form-control {
                border-radius: 0;
                padding: 8px 12px; /* Giảm padding từ 10px 15px xuống 8px 12px */
                margin-bottom: 10px; /* Giảm từ 15px xuống 10px */
            }
            .contact-form textarea.form-control {
                min-height: 120px; /* Giảm từ 150px xuống 120px */
            }
            .btn-submit {
                background-color: #0d6efd !important; /* Màu xanh nước biển */
                color: white !important;
                border: none;
                padding: 6px 20px;
                font-weight: 500;
            }
            .contact-label {
                font-size: 18px;
                font-weight: 600;
                margin-bottom: 15px; /* Giảm từ 20px xuống 15px */
            }
            .highlight {
                color: #f7941d;
            }
        </style>
    </head>
    <body>
        <!-- Include header -->
        <jsp:include page="header.jsp" />

        <!-- Main content -->
        <div class="main-content">
            <div class="container py-0"> <!-- Giảm py-4 xuống py-2 -->
                <div class="contact-form">
                    <h3 class="contact-heading">NƠI GIẢI ĐÁP TOÀN BỘ MỌI THẮC MẮC CỦA BẠN?</h3>

                    <div class="contact-info">
                        <p><strong>Hotline:</strong> <span class="highlight">097979999</span> | <span class="highlight">0352688888</span></p>
                        <p><strong>Email:</strong> <span class="highlight">info@cholang.com</span></p>
                    </div>

                    <div class="contact-label">LIÊN HỆ VỚI CHÚNG TÔI</div>

                    <% if (request.getAttribute("message") != null) { %>
                    <div class="alert alert-success" role="alert">
                        <%= request.getAttribute("message") %>
                        <a href="${pageContext.request.contextPath}/home" class="btn btn-primary btn-sm ms-3">Trở về trang chủ</a>
                    </div>
                    <% } %>

                    <form action="contact" method="post">
                        <div class="row">
                            <div class="col-md-6">
                                <input type="text" class="form-control" id="fullName" name="fullName" placeholder="Họ và tên" required>
                            </div>
                            <div class="col-md-6">
                                <input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <input type="tel" class="form-control" id="phone" name="phone" placeholder="Điện thoại">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <textarea class="form-control" id="message" name="message" placeholder="Nội dung"></textarea>
                            </div>
                        </div>
                        <div class="mt-2"> <!-- Giảm mt-3 xuống mt-2 -->
                            <button type="submit" class="btn btn-submit">Gửi thông tin</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Include footer -->
        <jsp:include page="footer.jsp" />

        <!-- Scripts -->
        <script src="${pageContext.request.contextPath}/assets/js/jquery-3.3.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.nice-select.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery-ui.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.slicknav.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/mixitup.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/owl.carousel.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>