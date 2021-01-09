<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Edytuj dane</title>
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

        .labelStyle {
            font-weight: bold;
            color: brown;
            margin-bottom: 50px;
            width: 50%;
            margin-top: 10%;
            margin-left: 25%;
            background-color: aliceblue;
            padding: 20px;
            border-color: red;
        }


    </style>
</head>
<div style="background-color: #f5e7e9">
    <div style="padding: 10px">
        <div class="labelStyle">
            <div style="margin-bottom: 30px">
                <h3>
                    <a>Twoje dane</a>
                </h3>
            </div>
            <div>
                <h6>
                    <a>Imię: ${appUser.fistName}</a>
                </h6>
            </div>
            <div>
                <h6>

                    <a>Nazwisko: ${appUser.lastName}</a>
                </h6>
            </div>
            <div>
                <h6>

                    <a>email: ${appUser.email}</a>
                </h6>
            </div>
            <div>
                <h6>

                    <a>Uprawnienia administratora:
                        <c:if test="${appUser.admin}">TAK
                        </c:if>
                        <c:if test="${!appUser.admin}">NIE
                        </c:if>
                    </a>
                </h6>
            </div>
            <div>
                <h6>
                    <fmt:formatDate var="regDate" value="${appUser.registeredSince}" pattern="yyyy-MM-dd"/>
                    <a>Data rejestracji: ${regDate}</a>
                </h6>
            </div>
        </div>
        <div style="margin-bottom: 10px">
            <a href="back" onclick="javascript:cancelAction()" style="text-decoration: none">
                <input class="btn btn-primary btn-block blue" type="button"
                       style="width: 200px;position: relative; left:41%" value="Zmień hasło">
            </a>
        </div>
    </
    <br>
    <div>
        <a href="back" onclick="javascript:cancelAction()" style="text-decoration: none">
            <input class="btn btn-primary btn-block blue" type="button"
                   style="width: 200px;position: relative; left:41%" value="Anuluj i wróć">
        </a>
    </div>
</div>
</body>
</html>
