<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Dodawanie pacjentki</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
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
    $('#foreigner').change(function(){
        if($(this).is(':checked')) {
            $('#pesel').attr('disabled', true)
        } else {
            $('#pesel').attr('disabled', false)
        }
    })
</script>
<body>
<main role="main" style="width: 80%; margin-left: auto; margin-right: auto">

    <h4 class="text-center mb-4 mt-1">Wprowadź dane pacjentki</h4>
    <hr>
    <c:if test="${errors != null}">
        <c:forEach items="${errors}" var="error">
            <div class="alert alert-danger">
                <strong>${error.header}</strong> <br>
                <p>${error.message}<p>
            </div>
        </c:forEach>
    </c:if>


    <form action="addPatient" method="POST">
        <div class="form-group">
            <div class="input-group">
                <label class="col-sm-2 col-form-label">Imię</label>
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                </div>
                <input name="firstName" class="form-control" type="text" required>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <label class="col-sm-2 col-form-label">Nazwisko</label>
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                </div>
                <input name="lastName" class="form-control" type="text" required>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <label class="col-sm-2 col-form-label">Obcokrajowiec</label>
                <input name="foreigner" id="foreigner" type="checkbox">
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <label class="col-sm-2 col-form-label">PESEL</label>
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                </div>
                <input name="pesel" id="pesel" class="form-control" type="text" required>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <label class="col-sm-2 col-form-label">Numer telefonu</label>
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                </div>
                <input name="phoneNumber" class="form-control" type="text" required>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <label class="col-sm-2 col-form-label">Czy planowane przyjęcie</label>
                <input name="scheludedRegistration" type="checkbox">
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <label class="col-sm-2 col-form-label">Rozpoznanie</label>
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                </div>
                <textarea name="diagnosis" class="form-control" rows="2"></textarea>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <label class="col-sm-2 col-form-label">Ostatnia miesiączka</label>
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                </div>
                <input name="lastPeriodDate" class="form-control" type="date"  required>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <label class="col-sm-2 col-form-label">Wiek ciąży w dniu przyjęcia</label>
                <div class="input-group-prepend">
                    <span class="input-group-text" style="height: 46px"> <i class="fa fa-user"></i> </span>
                </div>
                <select name="pragnancyAge" class="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref"
                        value="40">
                    <% for (int i = 30; i <= 37; i++) { %>
                    <option value="<%=i %>"><%=i %>
                    </option>
                    <% } %>
                    <option selected value="38">38</option>
                    <% for (int i = 39; i <= 50; i++) { %>
                    <option value="<%=i %>"><%=i %>
                    </option>
                    <% } %>
                </select>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <label class="col-sm-2 col-form-label">Lekarz kierujący</label>
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                </div>
                <input name="refferingDoctor" class="form-control" type="text" required>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <label class="col-sm-2 col-form-label">Komentarz</label>
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                </div>
                <textarea name="comment" class="form-control" rows="2"></textarea>
            </div>
        </div>


        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block blue" style="width: 100px; margin-right: auto; margin-left: auto"> Dodaj</button>
        </div>
        <div>
            <a href="back" onclick="javascript:cancelAction()" style="text-decoration: none">
                <input class="btn btn-primary btn-block blue" style="width: 150px; margin-right: auto; margin-left: auto" type="button" value="Anuluj i wróć">
            </a>
        </div>
    </form>
    <script>
        const checkbox = document.querySelector("#foreigner");
        const input = document.querySelector("#pesel");
        const toogleInput = function (e) {
            input.disabled = e.target.checked;
        };
        toogleInput({target: checkbox});
        checkbox.addEventListener("change", toogleInput);
    </script>
</main>
</body>
</html>

