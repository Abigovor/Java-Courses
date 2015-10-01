<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>User View</title>
</head>
<body>
<h1>List of clients</h1>

<p>Here you can see the list of the clients, edit them, remove or update.</p>

<p><a href="${pageContext.request.contextPath}/views/user/Home.jsp">Home page</a></p>

<table border="1px" cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <th width="10%">id</th>
        <th width="15%">name</th>
        <th width="10%">pet</th>
        <th width="10%">type</th>
        <th width="10%">actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${clients}" var="client" varStatus="status">
        <tr>
            <td>${client.id}</td>
            <td>${client.name}</td>
            <td>${client.pet}</td>
            <td>${client.pet.clazz}</td>
            <td>
                <a href="${pageContext.servletContext.contextPath}/user/edit?id=${client.id}">Edit</a><br>
                <a href="${pageContext.servletContext.contextPath}/user/delete?id=${client.id}">Delete</a><br>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
