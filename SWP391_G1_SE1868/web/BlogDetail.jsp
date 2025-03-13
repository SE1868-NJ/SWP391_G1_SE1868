<%-- 
    Document   : BlogDetail
    Created on : Mar 13, 2025, 4:02:07 PM
    Author     : nguyen
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Blog Detail</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center; /* Căn giữa theo chiều ngang */
            align-items: center; /* Căn giữa theo chiều dọc */
            min-height: 100vh; /* Đảm bảo chiều cao tối thiểu bằng màn hình */
            background-color: #f4f4f4; /* Màu nền nhẹ */
        }
        .blog-container {
            max-width: 800px; /* Giới hạn chiều rộng tối đa */
            width: 90%; /* Đảm bảo responsive trên màn hình nhỏ */
            background-color: #fff; /* Nền trắng cho nội dung */
            padding: 30px; /* Khoảng cách bên trong */
            border-radius: 10px; /* Bo góc nhẹ */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Hiệu ứng bóng đổ */
            margin: 20px 0; /* Khoảng cách trên dưới */
        }
        .blog-title {
            font-size: 28px; /* Tăng kích thước chữ tiêu đề */
            font-weight: bold;
            color: #333; /* Màu chữ tối hơn */
            text-align: center; /* Căn giữa tiêu đề */
            margin-bottom: 10px; /* Khoảng cách dưới */
        }
        .blog-date {
            color: #666; /* Màu chữ nhạt hơn */
            font-size: 14px;
            text-align: center; /* Căn giữa ngày */
            margin-bottom: 20px; /* Khoảng cách dưới */
        }
        .blog-content {
            font-size: 16px; /* Kích thước chữ nội dung */
            line-height: 1.6; /* Khoảng cách dòng dễ đọc */
            color: #444; /* Màu chữ nhạt tối */
            white-space: pre-wrap; /* Giữ định dạng dòng từ file */
            text-align: justify; /* Căn đều nội dung */
        }
    </style>
</head>
<body>
    <div class="blog-container">
        <div class="blog-title">${blogDetail.title}</div>
        <div class="blog-date">Created on: ${blogDetail.createdDate}</div>
        <div class="blog-content">${content}</div>
    </div>
</body>
</html>