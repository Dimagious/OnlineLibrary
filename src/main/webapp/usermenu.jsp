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
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<h3 align="center">Находите книги и читайте их прямо на сайте</h3>
</nav>
<br>
<div class="row">
    <div class="col-md-4" style="text-align: center;">
        <div style="display: inline-block;">
            <form method="get" action="http://localhost:8080/searchbooksbytitle">
                <h5>Поиск по названию</h5>
                <input type="text" name="searchByTitle" placeholder="Введите название книги">
                <button type="submit">Поиск</button>
            </form>
        </div>
    </div>
    <div class="col-md-4" style="text-align: center;">
        <div style="display: inline-block;">
            <form method="get" action="http://localhost:8080/searchbooksbyauthor">
                <h5>Поиск по автору</h5>
                <input type="text" name="searchByAuthor" placeholder="Введите фамилию автора">
                <button type="submit">Поиск</button>
            </form>
        </div>
    </div>
    <div class="col-md-4" style="text-align: center;">
        <div style="display: inline-block;">
            <form method="get" action="http://localhost:8080/searchbooksbygenre">
                <h5>Поиск по жанру</h5>
                <input type="text" name="searchByGenre" placeholder="Введите название жанра">
                <button type="submit">Поиск</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>