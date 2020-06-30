<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vielen
  Date: 6/24/20
  Time: 8:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>CABINET</h3>
    <c:choose>
        <c:when test="${sessionScope.user != null}">
            <h2>Username is ${sessionScope.user.username}</h2>
            <h2>Full name is ${sessionScope.user.fullName}</h2>
        </c:when>
        <c:otherwise>
            <c:out value="User not found"/>
        </c:otherwise>
    </c:choose>
    <a href="index.jsp">Main page</a>
</body>
</html>
