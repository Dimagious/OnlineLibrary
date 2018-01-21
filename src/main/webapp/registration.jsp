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
    <link href="css.css" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Регистрация</title>
</head>
<body>
<style>
    .center {
        text-align: center;
    }
</style>
<h1 align="center">Регистрация посетителей</h1>
<div class="center">
    <form method="post" action="http://localhost:8080/registration">
        <div class='form-row'>
            <label for="firstname-field">Имя</label>
            <input type="text" name="firstname" id="firstname-field" placeholder="Введите имя">
        </div>
        <br>
        <div class='form-row'>
            <label for="lastname-field">Фамилия</label>
            <input type="text" name="lastname" id="lastname-field" placeholder="Введите фамилию">
        </div>
        <br>
        <div class='form-row'>
            <label for="sex-field">Пол</label>
            <input type="text" name="sex" id="sex-field" placeholder="Введите м или ж">
        </div>
        <br>
        <div class='form-row'>
            <label for="login-field">Ваш логин</label>
            <input type="text" name="login" id="login-field" placeholder="Введите логин">
        </div>
        <br>
        <div class='form-row'>
            <label for="password-field">Ваш пароль</label>
            <input type="text" name="password" id="password-field" placeholder="Введите пароль">
        </div>
        <br>
        <button type="submit">Зарегистрироваться</button>
    </form>
    <form method="get" action="http://localhost:8080">
        <button type="submit">Назад</button>
    </form>
</div>
</body>
</html>