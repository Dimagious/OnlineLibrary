<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
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
<h1 align="center">Авторизация</h1>
<form method="post" action="http://localhost:8080/login">
    <table align="center">
        <tr>
            <td><label for="login-field">Ваш логин</label></td>
            <td><input required type="text" name="login" id="login-field" placeholder="Введите логин"></td>
        </tr>
        <tr>
            <td><label for="password-field">Ваш пароль</label></td>
            <td><input required type="text" name="password" id="password-field" placeholder="Введите пароль"></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="hidden" name="loginError">
                <span style="color: red;">${requestScope.loginError}</span>
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit">Войти</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>