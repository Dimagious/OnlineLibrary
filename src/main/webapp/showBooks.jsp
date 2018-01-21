<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 18.01.2018
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<title>Список всех книг библиотеки</title>
<table class="table table-hover">
    <thead>
    <tr>
        <th>Название книги</th>
        <th>Автор</th>
        <th>Жанр</th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="list" scope="request" type="java.util.List"/>
    <c:forEach var="list" items="${requestScope.list}">
        <tr>
            <td>${list.title}</td>
            <td>${list.authors.first_name} ${list.authors.last_name} </td>
            <td>${list.genres.name}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
