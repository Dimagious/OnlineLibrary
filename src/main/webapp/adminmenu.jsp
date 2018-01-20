<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 20.01.2018
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Читальный зал</title>
</head>
<body>
<!-- Bootstrap -->
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<style>
    .center {
        text-align: center;
    }
</style>
<% String userName = null;
if (session.getAttribute("user") == null) {
    response.sendRedirect("/index.jsp");
} else {
    userName = (String) session.getAttribute("user");
    if (!userName.equals("Admin")) response.sendRedirect("/index.jsp");
}%>
<h3 align="center">Находите книги и читайте их прямо на сайте</h3>
<form method="get" action="http://localhost:8080/allbooks">
    <h5 align="center">Раздел, который отображается только у админа</h5>
    <div class = "center">
        <button>Список всех книг</button>
    </div>
</form>
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
            <form method="get" action="http://localhost:8080/searchbooksbytitle">
                <h5>Поиск по жанру</h5>
                <input type="text" name="searchByGenre" placeholder="Введите название жанра">
                <button type="submit">Поиск</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>