<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Dodawanie pacjentki</title>
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
                    <label class="col-sm-2 col-form-label">Imie</label>
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
                    <input name="foreigner" type="checkbox">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <label class="col-sm-2 col-form-label">PESEL</label>
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="pesel" class="form-control" type="text" required>
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
                    <input name="lastPeriodDate" class="form-control" type="date" required>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <label class="col-sm-2 col-form-label">Wiek ciąży w dniu przyjęcia</label>
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <select class="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref" value="40">
                        <% for( int i=30; i<=39; i++) { %>
                        <option value="<%=i %>"><%=i %> <option/>
                        <% } %>
                        <option selected value="40">40</option>
                        <% for( int i=41; i<=50; i++) { %>
                        <option value="<%=i %>"><%=i %> <option/>
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
                    <label class="col-sm-2 col-form-label">Lekarz zapisujący</label>
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="prescribingDoctor" class="form-control" type="text" required>
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
