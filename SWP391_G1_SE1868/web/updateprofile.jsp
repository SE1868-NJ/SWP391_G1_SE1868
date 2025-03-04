<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${empty sessionScope.user}">
    <c:redirect url="login.jsp"/>
</c:if>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            width: 350px;
            text-align: center;
        }

        h2 {
            color: #333;
        }

        input, select {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        input[type="submit"] {
            background-color: #28a745;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 18px;
            transition: 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }

        .profile-img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            object-fit: cover;
            margin-bottom: 10px;
        }

        .back-link {
            display: block;
            margin-top: 10px;
            color: #007bff;
            text-decoration: none;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Edit Profile</h2>

        <!-- Hiển thị ảnh đại diện nếu có -->
        <c:if test="${not empty sessionScope.user.profileImage}">
            <img class="profile-img" src="${sessionScope.user.profileImage}" alt="Profile Image">
        </c:if>

        <form action="/profile" method="post">
            <input type="text" name="fullName" value="${sessionScope.user.fullName}" placeholder="Full Name" required>
            <input type="password" name="password" value="${sessionScope.user.password}" placeholder="Password" required>
            <input type="text" name="phoneNumber" value="${sessionScope.user.phoneNumber}" placeholder="Phone Number" required>
            <input type="text" name="address" value="${sessionScope.user.address}" placeholder="Address" required>
            <input type="date" name="birthDate" value="${sessionScope.user.birthDate}" required>

            <select name="gender">
                <option value="Male" ${sessionScope.user.gender == 'Male' ? 'selected' : ''}>Male</option>
                <option value="Female" ${sessionScope.user.gender == 'Female' ? 'selected' : ''}>Female</option>
            </select>

            <input type="text" name="profileImage" value="${sessionScope.user.profileImage}" placeholder="Profile Image URL">

            <input type="submit" value="Update">
        </form>

        <a class="back-link" href="/profile">Back to Profile</a>
    </div>
</body>
</html>
