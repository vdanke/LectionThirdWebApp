<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>
<h2>Hello World!</h2>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local"/>
<fmt:message key="greeting.message" var="Greeting_message"/>

<c:out value="${Greeting_message}"/>
    <form action="${pageContext.request.contextPath}/locale" method="get">
        <input type="hidden" name="locale" value="ru_RU">
        <input type="submit" name="submit" value="RU">
    </form>

    <form action="${pageContext.request.contextPath}/locale" method="get">
        <input type="hidden" name="locale" value="en_US">
        <input type="submit" name="submit" value="EN">
    </form>

    <a href="${pageContext.request.contextPath}/login-registration">Main page</a>
    <c:if test="${sessionScope.user != null}">
        <a href="${pageContext.request.contextPath}/cabinet">Cabinet</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </c:if>

    <form action="${pageContext.request.contextPath}/test" method="post">
        <input type="text" name="hello" placeholder="Insert your text">
        <input type="submit" name="Tap">
    </form>
</body>
</html>
