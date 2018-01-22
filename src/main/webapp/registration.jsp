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
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>
<h1 align="center">Регистрация посетителей</h1>
<form method="post" action="http://localhost:8080/registration">
    <table align="center">
        <tr>
            <td><label for="firstname-field">Имя</label></td>
            <td><input required type="text" name="firstname" id="firstname-field" placeholder="Введите имя"></td>
        </tr>
        <tr>
            <td><label for="lastname-field">Фамилия</label></td>
            <td><input required type="text" name="lastname" id="lastname-field" placeholder="Введите фамилию"></td>
        </tr>
        <tr>
            <td><label for="sex-field">Пол</label></td>
            <td><input required type="text" name="sex" id="sex-field" placeholder="Введите м или ж"></td>
        </tr>
        <tr>
            <td><label for="login-field">Ваш логин</label></td>
            <td><input required type="text" name="login" id="login-field" placeholder="Введите логин"></td>
        </tr>
        <tr>
            <td><label for="password-field">Ваш пароль</label></td>
            <td><input required type="password" name="password" id="password-field" placeholder="Введите пароль"></td>
            <td align="left"><span style="color: red;"></span></td>
        </tr>
        <tr>
            <td>
                <button type="submit">Зарегистрироваться</button>
            </td>
        </tr>
        <tr>
            <td align="center" colspan="2"><span style="color: red;">${requestScope.loginError}</span></td>
        </tr>
        <tr>
            <td align="center" colspan="2"><span style="color: red;">${requestScope.sexError}</span></td>
        </tr>
    </table>
</form>
</body>
</html>