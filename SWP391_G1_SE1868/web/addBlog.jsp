<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm Blog</title>
    <style>
        /* Thiết lập chung */
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
            width: 400px;
            text-align: center;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
            display: block;
            text-align: left;
            margin: 10px 0 5px;
        }

        input, textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 14px;
        }

        textarea {
            resize: none;
            height: 100px;
        }

        /* Input file */
        input[type="file"] {
            padding: 8px 0;
            border: none;
            background: none;
        }

        /* Nút submit */
        .btn-submit {
            background-color: #007bff;
            color: white;
            font-size: 16px;
            padding: 12px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            margin-top: 15px;
            transition: background 0.3s ease;
        }

        .btn-submit:hover {
            background-color: #0056b3;
        }

        /* Hiệu ứng khi focus vào input */
        input:focus, textarea:focus {
            border-color: #007bff;
            outline: none;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Thêm Blog Mới</h2>
        <form action="blog" method="post" enctype="multipart/form-data">
            <label for="name">Tên Blog:</label>
            <input type="text" id="name" name="name" required>

            <label for="description">Mô Tả:</label>
            <textarea id="description" name="description" required></textarea>

            <label for="customerId">ID Khách Hàng:</label>
            <input type="number" id="customerId" name="customerId" required>

            <label for="image">Chọn Ảnh:</label>
            <input type="file" id="image" name="image" accept="image/*" required>

            <label for="content">Nội dung chi tiết:</label>
            <textarea id="content" name="content" required></textarea>

            <button type="submit" class="btn-submit">Thêm Blog</button>
        </form>
    </div>
</body>
</html>