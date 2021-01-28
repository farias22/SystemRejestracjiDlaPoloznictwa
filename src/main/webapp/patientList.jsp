<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pl-PL">
<head>
    <meta charset="UTF-8">
    <title>Lista pacjentek</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
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


    </style>
</head>
<body>
<div role="main"  style="width: 80%; align-content: center; margin: 0 auto">

    <%@include file="header.jsp" %>

</div>
<main style="width: 90%; align-content: center; margin: 0 auto">
    <div class="my-3 p-3 bg-white rounded box-shadow">
        <h6 class="border-bottom border-gray pb-2 mb-0">Lista pacjentek</h6>
        </br>

        <a href="exportList">
            <input class="btn btn-info"
                   style="color: white; font-size: 14px"
                   type="button" value="Eksportuj do xls">
        </a>
            <c:if test="${emptyXLSListError != null}">
                <c:forEach items="${emptyXLSListError}" var="error">
                    <div class="alert alert-danger">
                        <strong>${error.header}</strong> <br>
                        <p>${error.message}<p>
                    </div>
                </c:forEach>
            </c:if>
        <br>
        <table class="table table-striped" style="font-size: 13px; text-align: center; table-layout: fixed">
            <tr style="font-weight: bold; color: brown">
                <td style="width: 20px">Koszyk</td>
                <td>lp</td>
                <td>Data wpisania do systemu</td>
                <td>Data hospitalizacji</td>
                <td>Wiek ciąży w dniu przyjęcia wg OM</td>
                <td>Imię</td>
                <td>Nazwisko</td>
                <td>PESEL</td>
                <td>Numer telefonu</td>
                <td>Termin przyjęcia wg. OM?</td>
                <td>Rozpoznanie</td>
                <td>Data ostatniej miesiączki</td>
                <td>Lekarz kierujący</td>
                <td>Lekarz zapisujący</td>
                <td>Komentarz</td>
                <td>Działania</td>
            </tr>
            <c:set var="counter" value="1"/>
            <c:forEach items="${patientList}" var="patientList">
                <tr>
                    <td>
                        <c:if test="${patientList.basket}">
                            <a href="removeFromBasket?userId=${patientList.id}"><input class="btn btn-danger" style="color: white; font-size: 15px;font-weight: 900; margin-right: 5px" type="button" value="-"></a>
                        </c:if>
                        <c:if test="${!patientList.basket}">
                            <a href="addToBasket?userId=${patientList.id}"><input class="btn btn-success" style="color: white; font-size: 15px;font-weight: 900; margin-right: 5px" type="button" value="+"></a>
                        </c:if>
                    </td>
                    <td>${counter}</td>
                    <td><fmt:formatDate value="${patientList.registrationDate}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${patientList.hospitalizationDate}" pattern="yyyy-MM-dd"/></td>
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
                    <td><a href="deletePatient?patientId=${patientList.id}"><input class="btn btn-warning"
                                                                                   style="color: white; font-size: 14px"
                                                                                   type="button" value="Usuń"></a></td>
                </tr>


                <c:set var="counter" value="${counter+1}"/>
            </c:forEach>

        </table>
    </div>


</main>
</body>
</html>
