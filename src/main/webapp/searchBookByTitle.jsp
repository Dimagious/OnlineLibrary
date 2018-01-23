<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19.01.2018
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Поиск книг по названию</title>
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
            <th>Название книги</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>
                    <a href="/readbook?title=${requestScope.foundedBook.title}&page=0">
                        <c:out value="${requestScope.foundedBook.title}"/>
                    </a>
                </td>
            </tr>
        </tbody>
    </table>
</body>
</html>