
<%--<%@ page import="org.step.model.User" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: vielen
  Date: 6/23/20
  Time: 8:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%--<%@ taglib prefix="xml" uri="http://java.sun.com/jsp/jstl/xml" %>--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
    <h3>This is main page</h3>
    <c:forEach items="${requestScope.users}" var="insideUser">
        ${insideUser.username}
        ${insideUser.fullName}
    </c:forEach>

    <c:out value="some" default="something"/>
<%--    <%=request.getAttribute("users")%>--%>
<%--    <jsp:useBean id="userBean" class="org.step.model.User" scope="page" beanName="goodUser"/>--%>
<%--    <jsp:setProperty name="myProperty" property="name" value="Very good message"/>--%>
<%--    ${requestScope.users}--%>
<%--    ${pageScope.userBean}--%>
<%--    ${sessionScope.user}--%>
<%--    ${applicationScope}--%>

<%--    <jsp:useBean id="userBean" class="org.step.model.User" scope="session"/>--%>

<%--    <jsp:setProperty name="userBean" property="username" value="${sessionScope.user.username}"/>--%>

<%--    <jsp:getProperty name="userBean" property="username"/>--%>

<%--    <jsp:include page=""/>--%>

<%--    <jsp:forward page="main.jsp">Forward to main jsp</jsp:forward>--%>

<%--    <jsp:params>--%>
<%--        <jsp:param name="nameSomething" value="valueSomething"/>--%>
<%--    </jsp:params>--%>

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
<%--    <%=new User().getFullName()%>--%>
</body>
</html>
