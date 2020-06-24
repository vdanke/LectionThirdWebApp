<html>
<body>
<h2>Hello World!</h2>
${sessionScope.user}

    <a href="${pageContext.request.contextPath}/users">Main page</a>
    <a href="${pageContext.request.contextPath}/cabinet">Cabinet</a>

    <form action="${pageContext.request.contextPath}/test" method="post">
        <input type="text" name="hello" placeholder="Insert your text">
        <input type="submit" name="Tap">
    </form>
</body>
</html>
