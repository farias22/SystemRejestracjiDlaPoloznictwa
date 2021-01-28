<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Logowanie</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        body {
            background: #f5e7e9
        }

        .blue {
            background-color: #1bec84 !important;
        }
    </style>
</head>
<body>
<main role="main" class="container">
    <%--<div>--%>
        <%--<img src="https://gumed.edu.pl/image/image/69815/large/uck_logo_t%C5%82o.png" alt="" width="400" height="170">--%>
    <%--</div>--%>
    <div class="my-3 p-3 bg-white rounded box-shadow">
        <h4 class="text-center mb-4 mt-1">System Rejestracji Położnictwa</h4>
        <hr>
        <c:if test="${errors != null}">
            <c:forEach items="${errors}" var="error">
                <div class="alert alert-danger">
                    <strong>${error.header}</strong> <br>
                    <p>${error.message}<p>
                </div>
            </c:forEach>
        </c:if>
        <form action="login" method="POST">
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="email" class="form-control" placeholder="email" autocomplete="off" type="text" required>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="password" class="form-control" placeholder="hasło" autocomplete="new-password" type="password" required>
                </div>
            </div>
            <div class="form-group">
                <%--<div class="form-check">--%>
                    <%--<input id="remember" name="remember" class="form-check-input" type="checkbox">--%>
                    <%--<label for="remember" class="form-check-label">Remember?</label>--%>
                <%--</div>--%>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block blue"> Zaloguj</button>
            </div>
            <%--<p class="text-center"><a href="register" class="btn">Sign up</a></p>--%>
        </form>
    </div>
    <%--<div style="padding-left: 300px">--%>
        <%--<img src="https://scontent.fpoz4-1.fna.fbcdn.net/v/t31.0-8/23674670_1940608359287324_5432559508572518836_o.jpg?_nc_cat=103&ccb=2&_nc_sid=cdbe9c&_nc_ohc=7U_6L-Li_iAAX8R4LKz&_nc_ht=scontent.fpoz4-1.fna&oh=cf33e249ec2daf8d097ebbafdb33bac5&oe=5FE1D062" alt="" width="400" height="270">--%>
    <%--</div>--%>
</main>
</body>
</html>
