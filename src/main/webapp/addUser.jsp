<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Dodawanie użytkownia</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        body {
            background: #f5e7e9
        }

        .border-bottom {
            border-bottom: 1px solid #e5e5e5;
        }

        .box-shadow {
            box-shadow: 0 .25rem .75rem rgba(0, 0, 0, .05);
        }

        .blue {
            background-color: #1bec84 !important;
        }


    </style>
</head>
<body>
<main role="main" class="container">

    <div class="my-3 p-3 bg-white rounded box-shadow">
        <c:if test="${creatingNewUserError != null}">
            <c:forEach items="${creatingNewUserError}" var="error">
                <div class="alert alert-danger">
                    <strong>${error.header}</strong> <br>
                    <p>${error.message}<p>
                </div>
            </c:forEach>
        </c:if>
        <h4 class="text-center mb-4 mt-1">Wprowadź dane użytkownika</h4>
        <hr>
        <c:if test="${errors != null}">
            <c:forEach items="${errors}" var="error">
                <div class="alert alert-danger">
                    <strong>${error.header}</strong> <br>
                    <p>${error.message}<p>
                </div>
            </c:forEach>
        </c:if>

        <form action="addUser" method="POST">
            <div class="form-group">
                <div class="input-group">
                    <label class="col-sm-2 col-form-label">Imie</label>
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="userFirstName" class="form-control" type="text" required>
                </div>
            </div>

            <div class="form-group">
                <div class="input-group">
                    <label class="col-sm-2 col-form-label">Nazwisko</label>
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="userLastName" class="form-control" type="text" required>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <label class="col-sm-2 col-form-label">e-mail</label>
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="userEmail" class="form-control" type="text" required>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <label class="col-sm-2 col-form-label">Uprawnienia administratora</label>
                    <input name="userAdmin" type="checkbox">
                </div>
            </div>


            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block blue"> Dodaj</button>
            </div>
            <div>
                <button onclick="goBack()" class="btn btn-primary btn-block blue">Anuluj i wróć</button>

                <script>
                    function goBack() {
                        window.history.back();
                    }
                </script>
            </div>
    </div>
    </form>
</main>
</body>
</html>
