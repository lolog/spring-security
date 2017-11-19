<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<html>
<head>
    <title>Security Login</title>
</head>
<body>
<form action="/login1.do" method="post">
    <table>
        <tr>
            <td>Username：</td>
            <td><input type="text" name="username"/></td>
            <input type="hidden" name="_csrf" value="${_csrf.token}">

            <input type="hidden" name="_csrf" value="_csrf">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="hidden" name="_csrf_header" value="${_csrf.headerName}">
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
<br>
<core:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
    error = <core:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
</core:if>
</body>
</html>