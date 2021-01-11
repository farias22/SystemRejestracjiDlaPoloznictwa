<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>hello</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        body {
            background: #f5e7e9
        }

        .text-white-50 {
            color: rgba(255, 255, 255, .5);
        }

        .bg-blue {
            background-color: #26ec89;
        }

        .box-shadow {
            box-shadow: 0 .25rem .75rem rgba(0, 0, 0, .05);
        }

        .tab {
            padding-left: 50px;
        }
    </style>
</head>
<body>

<div class="d-flex align-items-center p-3 my-3 text-white-50 bg-blue rounded box-shadow">
    <img class="mr-3" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRpBcB-SR28YhSrhVdgja0dPZFzyvL2x_zOaQ&usqp=CAU" alt="" width="48" height="48">
    <div class="lh-100">
        <h6 class="mb-0 lh-100 tab">
            <a class="text-white" href="addPatient">Dodaj pacjentkę</a>
        </h6>
    </div>
    <div class="lh-100">
        <h6 class="mb-0 lh-100 tab">
            <a class="text-white" href="searchPatient">Wyszukaj pacjentkę</a>
        </h6>
    </div>

    <c:if test="${idAdmin}">
        <div class="lh-100">
            <h6 class="mb-0 lh-100 tab">
                <a class="text-white" href="addUser">Dodaj użytkownika</a>
            </h6>
        </div>
        <div class="lh-100">
            <h6 class="mb-0 lh-100 tab">
                <a class="text-white" href="searchUser">Wyszukaj użytkownika</a>
            </h6>
        </div>

    </c:if>


    <div class="lh-100 ml-auto">
        <h6 class="mb-0 lh-100" style="text-align: right">
            <a class="text-white">Jesteś zalogowany jako:<br></a>
            <a class="text-white" href="editUser">${appUser.fistName} ${appUser.lastName}</a>
        </h6>
    </div>
    <div class="lh-100 ml-auto">
        <h6 class="mb-0 lh-100">
            <a class="text-white" href="logout">Wyloguj</a>
        </h6>
    </div>
</div>

</body>
</html>
