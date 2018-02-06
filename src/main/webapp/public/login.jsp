<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
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
<h1 align="center">Добро Пожаловать в онлайн библиотеку</h1>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-default" style="margin-top:45px">
                <div class="panel-heading">
                    <h3 align="center">Авторизация</h3>
                </div>
                <div class="panel-body">
                    <form method="post" action="${requestScope.contextPath}/public/login">
                        <div class="form-group">
                            <label for="username">Логин</label>
                            <input required type="text" class="form-control" id="username" placeholder="Введите Ваш логин"
                                   name="username">
                        </div>
                        <div class="form-group">
                            <label for="password">Пароль</label>
                            <input required type="password" class="form-control" id="password" placeholder="Введите Ваш пароль"
                                   name="password">
                        </div>
                        <div>
                            <input type="hidden" name="loginError">
                            <span style="color: red;">${requestScope.loginError}</span>
                        </div>
                        <button type="submit" class="btn btn-default">Войти</button>
                    </form>
                    <form method="get" action="${requestScope.contextPath}/public/registration">
                        <button type="submit" class="btn btn-default">Регистрация</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>