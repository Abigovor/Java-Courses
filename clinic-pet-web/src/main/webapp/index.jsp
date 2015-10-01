<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>

<h2>${message}<h2><br>
<form action="login" method="POST" autocomplete="on">
    <table>
        <tr>
            <td>Login:</td>
            <td>
                <input type="text" placeholder="Input your login" name="login" required>
            </td>
        </tr>

        <tr>
            <td>Password:</td>
            <td>
                <input type="password" placeholder="Input your password" name="password" required>
            </td>
        </tr>

        <tr>
            <td>
                <input type="submit" value="Sing In">
            </td>
        </tr>
    </table>
</form>
</body>
</html>