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
<!-- Bootstrap -->
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<h2>Находите книги и читайте их прямо на сайте</h2>
<form method="get" action="http://localhost:8080/allbooks">
    <h3>Раздел, который отображается только у админа</h3>
    <button type="submit">Список всех книг</button>
</form>
<br>
<div class="row">
    <div class="col-md-4">
        <form method="get" action="http://localhost:8080/searchbooksbytitle">
            <h3>Поиск по названию</h3>
            <input type="text" name="searchByTitle" placeholder="Введите название книги">
            <button type="submit">Поиск</button>
        </form>
    </div>
    <div class="col-md-4">
        <form method="get" action="http://localhost:8080/searchbooksbyauthor">
            <h3>Поиск по автору</h3>
            <input type="text" name="searchByAuthor" placeholder="Введите фамилию автора">
            <button type="submit">Поиск</button>
        </form>
    </div>
    <div class="col-md-4">
        <form method="get" action="http://localhost:8080/searchbooksbytitle">
            <h3>Поиск по жанру</h3>
            <input type="text" name="searchByGenre" placeholder="Введите название жанра">
            <button type="submit">Поиск</button>
        </form>
    </div>
</div>
</body>
</html>