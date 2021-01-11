<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Wyszukiwarka użytkowników</title>
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
        <h4 class="text-center mb-4 mt-1">Wyszukiwarka znajduje użytkownikow na podstawie Nazwiska lub e-maila</h4>
        <hr>
        <form action="searchUser" method="POST">
            <div class="form-group">
                <div class="input-group">

                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input placeholder="wpisz szukaną frazę" name="searchUser" class="form-control" type="text" style="text-align: center" required>
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
    <c:if test="${searchedUsersList != null}">
    <table class="table table-striped" style="font-size: 13px; text-align: center">
        <tr style="font-weight: bold; color: brown">
            <td>lp</td>
            <td>Data wpisania do systemu</td>
            <td>Imię</td>
            <td>Nazwisko</td>
            <td>e-mail</td>
            <td>Czy admin</td>
            <td>Działania</td>
        </tr>
        <c:set var="counter" value="1" />
        <c:forEach items="${searchedUsersList}" var="usersList">
        <tr>
            <td style="width: 20px">${counter}</td>
            <td><fmt:formatDate value="${usersList.registeredSince}" pattern="yyyy-MM-dd"/></td>
            <td>${usersList.fistName}</td>
            <td>${usersList.lastName}</td>
            <td>${usersList.email}</td>
            <td>
                <c:if test="${usersList.admin}">TAK
                </c:if>
                <c:if test="${!usersList.admin}">NIE
                </c:if>
            </td>
            <td style="width: 300px">
                <a href="editPatient?patientId=${usersList.id}"><input class="btn btn-warning" style="color: white; font-size: 14px; margin-right: 10px" type="button" value="Edytuj"></a>
                <a href="editPatient?patientId=${usersList.id}"><input class="btn btn-info" style="color: white; font-size: 14px;margin-right: 10px" type="button" value="Resetuj hasło"></a>
                <a href="deleteUser?userId=${usersList.id}"><input class="btn btn-danger" style="color: white; font-size: 14px" type="button" value="Usuń"></a>
            </td>
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
