<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Home page</title>
</head>
<body>

<h1>Home page</h1>
<p>
    ${message}<br>
    <a href="${pageContext.servletContext.contextPath}/views/user/AddClient.jsp">Add new client</a><br>
    <a href="${pageContext.servletContext.contextPath}/user/view">Client list</a><br>
</p>

<form action="${pageContext.servletContext.contextPath}/search" method="POST">
    Search of client: <input type="search" name="search" required><br>
</form>

</body>
</html>
