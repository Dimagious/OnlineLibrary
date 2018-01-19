<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19.01.2018
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Авторизация</h1>
<form method="post" action="http://localhost:8080/dashboard">
    <label for="login-field">Ваш логин</label>
    <input type="text" name="login" id="login-field" placeholder="Введите логин">
    <br><br>
    <label for="password-field">Ваш пароль</label>
    <input type="text" name="password" id="password-field" placeholder="Введите пароль">
    <br><br>
    <button type="submit">Войти</button>
</form>
</body>
</html>