<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
    <meta charset="UTF-8">
</head>
<body>
<h1>Page create user</h1>

<p>Here you can add a new client.</p>

<form action="${pageContext.servletContext.contextPath}/user/create" method="POST">
    <table>
        <tr>
            <td>Client's name:</td>
            <td>
                <input type="text" placeholder="Input your name" name="clientName" required>
            </td>
        </tr>
        <tr>
            <td>Client's surname:</td>
            <td>
                <input type="text" placeholder="Input your surname" name="clientSurname" required>
            </td>
        </tr>
        <tr>
            <td>Password:</td>
            <td>
                <input type="password" placeholder="Input your password" name="password" required>
            </td>
        </tr>
        <tr>
            <td>E-mail:</td>
            <td>
                <input type="email" placeholder="Input your e-mail" name="email" required>
            </td>
        </tr>

        <tr>
            <td>Пол</td>
            <td>
                <input type="radio" name="sexH" value="f" required> Famale
                <input type="radio" name="sexH" value="m" required> Male
            </td>
        </tr>

<%--        <tr>
            <td>Pet's name:</td>
            <td>
                <input type="text" placeholder="Input name your pet" name="petName">
            </td>
        </tr>

        <tr>
            <td>Type of pet</td>
            <td>
                <input type="radio" name="petType" value="cat" > Cat
                <input type="radio" name="petType" value="dog" > Dog
            </td>
        </tr>--%>

        <tr>
            <td>Role:</td>
            <td>
                <select name="role" required>
                    <option disabled selected>Не выбрано</option>
                    <c:forEach items="${roles}" var="role">
                        <option value="${role.name}">${role.name}</option>
                        <input type="hidden" name="role_id" value="${role.id}">
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td>
                <input type="submit" align="center" value="Submit">
            </td>
        </tr>
    </table>
</form>

<p><a href="${pageContext.servletContext.contextPath}/views/user/Home.jsp">Home page</a></p>
<a href="#top">Top</a>

</body>
</html>
