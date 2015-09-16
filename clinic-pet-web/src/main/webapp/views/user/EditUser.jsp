<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Edit user</title>
</head>
<body>
<h1>Edit user page</h1>

<p>Here you can edit the existing client.</p>
<p>${message}</p>
<form action="${pageContext.servletContext.contextPath}/user/edit" method="post">
    <input type="hidden" name="id" value="${user.id}">
    <table>
        <tr>
            <td>Client's name:</td>
            <td>
                <input type="type" name="clientName" value="${user.name}"/>
            </td>
        </tr>
        <tr>
            <td>Pet's name</td>
            <td>
                <input type="text" name="petName" value="${user.pet}">
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
