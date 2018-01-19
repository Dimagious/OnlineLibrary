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
</head>
<body>
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
            <td>${list_.title}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>