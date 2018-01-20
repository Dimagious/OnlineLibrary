<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 18.01.2018
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Регистрация</title>
</head>
<body>
<h1>Регистрация посетителей</h1>
<form method="post" action="http://localhost:8080/registration">
    <label for="firstname-field">Имя</label>
    <input type="text" name="firstname" id="firstname-field" placeholder="Введите имя">
    <br><br>
    <label for="lastname-field">Фамилия</label>
    <input type="text" name="lastname" id="lastname-field" placeholder="Введите фамилию">
    <br><br>
    <label for="sex-field">Пол</label>
    <input type="text" name="sex" id="sex-field" placeholder="Введите м или ж">
    <br><br>
    <label for="login-field">Ваш логин</label>
    <input type="text" name="login" id="login-field" placeholder="Введите логин">
    <br><br>
    <label for="password-field">Ваш пароль</label>
    <input type="text" name="password" id="password-field" placeholder="Введите пароль">
    <br><br>
    <button type="submit">Зарегистрироваться</button>
</form>
</body>
</html>