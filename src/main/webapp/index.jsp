<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 16.01.2018
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>OnlineLibrary</title>
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
<style>
    .center {
        text-align: center;
    }
</style>
<h1 align="center">Добро Пожаловать в онлайн библиотеку</h1>
<h3 align="center">Для скачивания книг необходимо быть зарегистрированным в библиотеке</h3>
<table align="center">
    <td>
        <div class="center">
            <form method="get" action="http://localhost:8080/login">
                <button>Авторизация</button>
            </form>
        </div>
    </td>
    <td>
        <div class="center">
            <form method="get" action="http://localhost:8080/registration">
                <button>Регистрация</button>
            </form>
        </div>
    </td>
</table>
<form method="get" action="http://localhost:8080/usermenu">
    <div class="center">
        <h3 align="center">В читальном зале можно читать книги без регистрации</h3>
        <button>Посетить читальный зал в качестве гостя</button>
    </div>
</form>
</body>
</html>