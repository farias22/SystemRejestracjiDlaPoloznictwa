<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Profil</title>
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
<script>
    $('#resetPassword').change(function(){
        if($(this).is(':checked')) {
            $('#password1').attr('enabled', true)
            $('#password2').attr('enabled', true)
        } else {
            $('#password1').attr('enabled', true)
            $('#password2').attr('enabled', true)
        }
    })
</script>
<body>
<main role="main" class="container">

    <div class="my-3 p-3 bg-white rounded box-shadow">
        <h4 class="text-center mb-4 mt-1">Twoje dane:</h4>
        <hr>


        <form action="editUser" method="POST">
            <c:if test="${editingUserError != null}">
            <c:forEach items="${editingUserError}" var="error">
            <div class="alert alert-danger">
                <strong>${error.header}</strong> <br>
                <p>${error.message}<p>
            </div>
            </c:forEach>
            </c:if>
            <div class="form-group">
                <div class="input-group">
                    <label class="col-sm-2 col-form-label">Imie</label>
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input value="${appUser.fistName}" name="userFirstName" class="form-control" type="text" required>
                </div>
            </div>

            <div class="form-group">
                <div class="input-group">
                    <label class="col-sm-2 col-form-label">Nazwisko</label>
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input value="${appUser.lastName}" name="userLastName" class="form-control" type="text" required>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <label class="col-sm-2 col-form-label">login</label>
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input value="${appUser.login}" name="login" class="form-control" type="text" required>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <label class="col-sm-2 col-form-label">Resetuj hasło</label>
                    <input name="resetPassword" id="resetPassword" type="checkbox">
                </div>
            </div>




            <div class="form-group">
                <div class="input-group">
                    <label class="col-sm-2 col-form-label">nowe hasło</label>
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="password1" id="password1" class="form-control" type="password" required>
                </div>
            </div>

            <div class="form-group">
                <div class="input-group">
                    <label class="col-sm-2 col-form-label">powtórz hasło</label>
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="password2" id="password2" class="form-control" type="password" required>
                </div>
            </div>


            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block blue"> Zapisz zmiany</button>
            </div>
            <div>
                <a href="back" onclick="javascript:cancelAction()" style="text-decoration: none">
                    <input class="btn btn-primary btn-block blue" type="button" value="Porzuć zmiany i wróć">
                </a>
            </div>
    </div>
    </form>
    <script>
        const checkbox = document.querySelector("#resetPassword");
        const input = document.querySelector("#password1");
        const input2 = document.querySelector("#password2");
        const toogleInput = function (e) {
            input.disabled = !e.target.checked;
            input2.disabled = !e.target.checked;
        }
        toogleInput({target: checkbox});
        checkbox.addEventListener("change", toogleInput);
    </script>
</main>
</body>
</html>
