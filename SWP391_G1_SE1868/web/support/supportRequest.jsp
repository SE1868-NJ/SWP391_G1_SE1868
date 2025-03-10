<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liên hệ hỗ trợ</title>
</head>
<body>
    <h2>Gửi yêu cầu hỗ trợ</h2>
    <form action="SubmitSupportRequest" method="post">
        <label for="subject">Tiêu đề:</label>
        <input type="text" id="subject" name="subject" required><br><br>
        
        <label for="message">Nội dung yêu cầu:</label><br>
        <textarea id="message" name="message" rows="5" cols="50" required></textarea><br><br>

        <input type="hidden" name="customerId" value="${customer.id}"> <!-- Thông tin khách hàng -->
        
        <input type="submit" value="Gửi yêu cầu">
    </form>
</body>
</html>
