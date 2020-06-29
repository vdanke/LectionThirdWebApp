<%--
  Created by IntelliJ IDEA.
  User: vielen
  Date: 6/23/20
  Time: 8:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
    ${users}
    <h3>This is main page</h3>

    <form action="${pageContext.request.contextPath}/users/login" method="post">
        <input type="text" name="username" placeholder="Insert your login">
        <input type="password" name="password" placeholder="Insert your password">
        <input type="submit" value="Login">
    </form>
    <form action="${pageContext.request.contextPath}/users/registration" method="post">
        <input type="text" name="username" placeholder="Insert your login">
        <input type="password" name="password" placeholder="Insert your password">
        <input type="text" name="fullName" placeholder="Insert your name">
        <input type="submit" value="Registration">
    </form>
</body>
</html>
