<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Wyszukiwarka pacjentek</title>
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
<main style="width: 90%; align-content: center; margin: 0 auto">

    <div class="my-3 p-3 bg-white rounded box-shadow" style="width: 75%; margin-right: auto; margin-left: auto">
        <h4 class="text-center mb-4 mt-1">Wyszukiwarka znajduje pacjentki na podstawie Nazwiska lub PESEL-u</h4>
        <hr>
        <form action="searchPatient" method="POST">
            <div class="form-group">
                <div class="input-group">
                    <%--<label class="col-sm-2 col-form-label">Wpisz szukaną frazę</label>--%>
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input placeholder="wpisz szukaną frazę" name="search" class="form-control" type="text" style="text-align: center" required>
                </div>
            </div>


            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block blue" style="width: 120px;position: relative; left: 45%"> Szukaj</button>
            </div>

            <div>
                <a href="back" onclick="javascript:cancelAction()" style="text-decoration: none">
                    <input class="btn btn-primary btn-block blue" type="button" style="width: 200px;position: relative; left:41%" value="Anuluj i wróć">
                </a>
            </div>
    </div>
    </form>
    <c:if test="${searchList != null}">
    <table class="table table-striped" style="font-size: 13px; text-align: center">
        <tr style="font-weight: bold; color: brown">
            <td>lp</td>
            <td>Data wpisania do systemu</td>
            <td>Data hospitalizacji</td>
            <td>Wiek ciąży w dniu przyjęcia wg OM</td>
            <td>Imię</td>
            <td>Nazwisko</td>
            <td>PESEL</td>
            <td>Numer telefonu</td>
            <td>Czy planowane przyjęcie</td>
            <td>Rozpoznanie</td>
            <td>Data ostatniej miesiączki</td>
            <td>Lekarz kierujący</td>
            <td>Lekarz zapisujący</td>
            <td>Komentarz</td>
            <td>Archiwalna</td>
            <td>Działania</td>
        </tr>
        <c:set var="counter" value="1" />
        <c:forEach items="${searchList}" var="patientList">
        <tr>
            <td>${counter}</td>
            <td><fmt:formatDate value="${patientList.registrationDate}" pattern="yyyy-MM-dd"/></td>
            <td><fmt:formatDate var="hospDate" value="${patientList.hospitalizationDate}" pattern="yyyy-MM-dd"/>
                <c:if test="${hospDate=='1900-01-01'}">*
                </c:if>
                <c:if test="${hospDate!='1900-01-01'}">${hospDate}
                </c:if>
            </td>
            <td>${patientList.pregnancyAge}</td>
            <td>${patientList.firstName}</td>
            <td>${patientList.lastName}</td>
            <td>${patientList.pesel}</td>
            <td>${patientList.phoneNumber}</td>
            <td>
                <c:if test="${patientList.scheludedRegistration}">TAK
                </c:if>
                <c:if test="${!patientList.scheludedRegistration}">NIE
                </c:if>
            </td>
            <td>${patientList.diagnosis}</td>
            <td><fmt:formatDate value="${patientList.lastPeriodDate}" pattern="yyyy-MM-dd"/></td>
            <td>${patientList.refferingDoctor}</td>
            <td>${patientList.prescribingDoctor}</td>
            <td>${patientList.comment}</td>
            <td>
                <c:if test="${patientList.active}">NIE
                </c:if>
                <c:if test="${!patientList.active}">TAK
                </c:if>
            </td>
            <td><a href="editPatient?patientId=${patientList.id}"><input class="btn btn-warning" style="color: white; font-size: 14px" type="button" value="Edytuj"></a></td>
        </tr>
            <c:set var="counter" value="${counter+1}"/>
    </c:forEach>
    </table>
    </c:if>
    <c:if test="${noResults != null}">
        <div><h6 class="text-center mb-4 mt-1" style="color: gray; text-align: center">Brak wyników wyszukiwania</h6></div>
    </c:if>
</main>
<div>
</div>
</body>
</html>
