<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 18.01.2018
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Регистрация</title>
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
<%--<h1 align="center">Регистрация посетителей</h1>--%>
<%--<form method="post" action="${requestScope.contextPath}/public/registration">--%>
    <%--<table align="center">--%>
        <%--<tr>--%>
            <%--<td><label for="firstname-field">Имя</label></td>--%>
            <%--<td><input required type="text" name="firstname" id="firstname-field" placeholder="Введите имя"></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><label for="lastname-field">Фамилия</label></td>--%>
            <%--<td><input required type="text" name="lastname" id="lastname-field" placeholder="Введите фамилию"></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><label for="sex-field">Пол</label></td>--%>
            <%--<td>--%>
                <%--<select name="sex" id="sex-field">--%>
                    <%--<option disabled>Выберите пол</option>--%>
                    <%--<option value="м">Мужской</option>--%>
                    <%--<option value="ж">Женский</option>--%>
                <%--</select>--%>
            <%--</td>--%>
            <%--&lt;%&ndash;<td><input  type="s" name="sex" id="sex-field"></td>&ndash;%&gt;--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><label for="login-field">Ваш логин</label></td>--%>
            <%--<td><input required type="text" name="login" id="login-field" placeholder="Введите логин"></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><label for="password-field">Ваш пароль</label></td>--%>
            <%--<td><input required type="password" name="password" id="password-field" placeholder="Введите пароль"></td>--%>
            <%--<td align="left"><span style="color: red;"></span></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>--%>
                <%--<button type="submit">Зарегистрироваться</button>--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td align="center" colspan="2"><span style="color: red;">${requestScope.loginError}</span></td>--%>
        <%--</tr>--%>
    <%--</table>--%>
<%--</form>--%>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-default" style="margin-top:45px">
                <div class="panel-heading">
                    <h3 align="center">Регистрация</h3>
                </div>
                <div class="panel-body">
                    <form method="post" action="${requestScope.contextPath}/public/registration">
                        <div class="form-group">
                            <label for="firstname">Имя</label>
                            <input required type="text" class="form-control" id="firstname" placeholder="Введите имя"
                                   name="firstname">
                        </div>
                        <div class="form-group">
                            <label for="lastname">Фамилия</label>
                            <input required type="text" class="form-control" id="lastname" placeholder="Введите фамилию"
                                   name="lastname">
                        </div>
                        <div class="form-group">
                            <label for="sex-field">Пол</label>
                            <select class="form-control" name="sex" id="sex-field">
                            <option disabled>Выберите пол</option>
                            <option selected value="м">Мужской</option>
                            <option value="ж">Женский</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="username">Логин</label>
                            <input required type="text" class="form-control" id="username" placeholder="Введите логин"
                                   name="username">
                        </div>
                        <div class="form-group">
                            <label for="password">Пароль</label>
                            <input required type="password" class="form-control" id="password" placeholder="Введите пароль"
                                   name="password">
                        </div>
                        <div>
                            <input type="hidden" name="loginError">
                            <span style="color: red;">${requestScope.loginError}</span>
                        </div>
                        <button type="submit" class="btn btn-default">Зарегистрироваться</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>