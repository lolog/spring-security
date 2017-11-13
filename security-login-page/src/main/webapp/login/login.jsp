<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Security Login</title>
</head>
<body>
<form action="login.do" method="post">
    <table>
        <tr>
            <td>Username：</td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td>Password：</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Login"/>
                <input type="reset" value="Reset"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>