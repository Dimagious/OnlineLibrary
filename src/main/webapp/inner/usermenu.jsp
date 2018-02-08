<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19.01.2018
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Читальный зал</title>
</head>
<body>
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
<style>
    /*.background {*/
    /*background: url("http://library.rsue.ru/ism/image/slides/_u/1486715705716_118604.jpg") no-repeat;*/
    /*}*/
</style>
<h3 align="center">Находите книги и читайте их прямо на сайте</h3>
<div style="position: fixed; top: 0%; right: 0;">
    <form method="get" action="${requestScope.contextPath}/inner/logout">
        <button type="submit" class="btn btn-default">Выйти</button>
    </form>
</div>
<br>
<div class="row">
    <div class="col-md-4" style="text-align: center;">
        <div style="display: inline-block;">
            <form method="get" action="${requestScope.contextPath}/inner/searchBookByTitle">
                <h4>Поиск по названию</h4>
                <input type="text" name="searchByTitle" placeholder="Введите название книги">
                <button type="submit" class="btn btn-default">Поиск</button>
            </form>
        </div>
    </div>
    <div class="col-md-4" style="text-align: center;">
        <div style="display: inline-block;">
            <form method="get" action="${requestScope.contextPath}/inner/searchBooksByAuthor">
                <h4>Поиск по автору</h4>
                <input type="text" name="searchByAuthor" placeholder="Введите фамилию автора">
                <button type="submit" class="btn btn-default">Поиск</button>
            </form>
        </div>
    </div>
    <div class="col-md-4" style="text-align: center;">
        <div style="display: inline-block;">
            <form method="get" action="${requestScope.contextPath}/inner/searchBookByGenre">
                <h4>Поиск по жанру</h4>
                <input type="text" name="searchByGenre" placeholder="Введите название жанра">
                <button type="submit" class="btn btn-default">Поиск</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>