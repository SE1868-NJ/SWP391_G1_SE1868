<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>
    <form action="Login" method="post">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>

        <button type="submit">Login</button>
    </form>

    <c:if test="${param.error == 'invalid'}">
        <p style="color: red;">Invalid email or password. Please try again.</p>
    </c:if>
</body>
</html>
