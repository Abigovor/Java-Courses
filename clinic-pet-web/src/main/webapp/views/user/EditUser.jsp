<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Edit user</title>
    <meta charset="utf-8"/>
</head>
<body>
<h1>Edit user page</h1>

<p>Here you can edit the existing client.</p>

<p>${message}</p>

<form action="${pageContext.servletContext.contextPath}/user/edit" method="post">
    <input type="hidden" name="id" value="${user.id}">
    <input type="hidden" name="role_id" value="${user.getRole().getId()}">
    <table>
        <tr>
            <td>Client's name:</td>
            <td>
                <input type="type" name="clientName" value="${user.name}"/>
            </td>
        </tr>

        <tr>
            <td>Client's surname:</td>
            <td>
                <input type="type" name="clientSurname" value="${user.surname}"/>
            </td>
        </tr>

        <tr>
            <td>Gender</td>
            <td>
                <input type="radio" name="sex" required value="${user.sex}"> Famale
                <input type="radio" name="sex" required value="${user.sex}"> Male
            </td>
        </tr>

        <tr>
            <td>Password:</td>
            <td>
                <input type="type" name="password" value="${user.password}"/>
            </td>
        </tr>

        <tr>
            <td>E-mail:</td>
            <td>
                <input type="email" name="email" value="${user.email}"/>
            </td>
        </tr>

        <tr>
            <td>
                <input type="submit" value="Edit">
            </td>
        </tr>
    </table>
</form>
</body>
</html>