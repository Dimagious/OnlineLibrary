<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19.01.2018
  Time: 9:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Поиск книг по жанру</title>
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
<form method="get" action="http://localhost:8080/usermenu">
    <button type="submit">Назад</button>
</form>
<table class="table table-hover">
    <thead>
    <tr>
        <th>Список книг жанра</th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="list" scope="request" type="java.util.List"/>
    <c:forEach var="list_" items="${requestScope.list}">
        <tr>
            <td><a href="/readbook?title=${list_.title}&page=0"> ${list_.title}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>