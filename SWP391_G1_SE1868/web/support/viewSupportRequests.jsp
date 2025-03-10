<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách yêu cầu hỗ trợ</title>
</head>
<body>
    <h2>Danh sách yêu cầu hỗ trợ của bạn</h2>
    
    <table border="1">
        <thead>
            <tr>
                <th>Tiêu đề</th>
                <th>Nội dung</th>
                <th>Trạng thái</th>
                <th>Ngày tạo</th>
                <th>Ngày cập nhật</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="request" items="${requests}">
                <tr>
                    <td>${request.subject}</td>
                    <td>${request.message}</td>
                    <td>${request.status}</td>
                    <td>${request.createdAt}</td>
                    <td>${request.updatedAt}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
