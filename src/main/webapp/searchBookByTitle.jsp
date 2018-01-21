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
</head>
<body>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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
        <jsp:useBean id="foundedBook" class="servlets.SearchBookByTitle" />
        <jsp:getProperty name="foundedBook" property="searchByTitle" />
            <tr>
                <td><c:out value="${foundedBook.toString}"/></td>
            </tr>
        </tbody>
    </table>
</body>
</html>