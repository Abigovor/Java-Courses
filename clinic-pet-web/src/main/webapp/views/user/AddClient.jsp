<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<h1>Create user page</h1>

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
            <td>Pet's name:</td>
            <td>
                <input type="text" placeholder="Input name your pet" name="petName">
            </td>
        </tr>
        <td>
            <input type="radio" name="1" value="cat" id="dev"> Cat
        </td>
        <td>
            <input type="radio" name="1" value="dog" > Dog
        </td>
        <tr>
            <td>
                <input type="submit" align="center" value="Submit">
            </td>
        </tr>
    </table>
</form>

<p><a href="${pageContext.servletContext.contextPath}/views/user/index.jsp">Home page</a></p>

</body>
</html>
