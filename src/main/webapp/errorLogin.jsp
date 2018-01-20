<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 20.01.2018
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Авторизация</h1>
<form method="get" action="http://localhost:8080/usermenu">
    <h4>Неправильный логин или пароль</h4>
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
