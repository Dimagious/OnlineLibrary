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
    <%--<style>--%>
        <%--body {--%>
            <%--background-opacity: 0.5;--%>
            <%--background: url(http://sepimages.ru/uploads/images/f/o/t/foto_dlja_biblioteki_1.jpg) no-repeat center;--%>
        <%--}--%>
    <%--</style>--%>
</head>
<body>
<style>
    .center {
        text-align: center;
    }
</style>
<h1 align="center">Добро Пожаловать в онлайн библиотеку</h1>
<h3 align="center">Для скачивания книг необходимо быть зарегистрированным в библиотеке</h3>
<form method="get" action="http://localhost:8080/login">
    <div class = "center">
        <button>Авторизация</button>
    </div>
</form>
<form method="get" action="http://localhost:8080/registration">
    <div class = "center">
        <button>Регистрация</button>
    </div>
</form>
<form method="get" action="http://localhost:8080/usermenu">
    <div class = "center">
        <h3 align="center">В читальном зале можно читать книги без регистрации</h3>
        <button>Посетить читатльный зал в качестве гостя</button>
    </div>
</form>
</body>
</html>