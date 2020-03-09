<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html lang="en">
<head>
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link href="<c:url value="/res/img/v6.png"/>" rel="icon" type="image/png"/>
    <!--===============================================================================================-->
    <link href="<c:url value="/res/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css"/>
    <!--===============================================================================================-->
    <link href="<c:url value="/res/css/hamburgers.min.css"/>" rel="stylesheet" type="text/css"/>
    <!--===============================================================================================-->
    <link href="<c:url value="/res/css/select2.min.css"/>" rel="stylesheet" type="text/css"/>
    <!--===============================================================================================-->
    <link href="<c:url value="/res/css/util.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/res/css/main.css"/>" rel="stylesheet" type="text/css"/>
    <!--===============================================================================================-->
    <link href="https://fonts.googleapis.com/css?family=Vollkorn&display=swap" rel="stylesheet">
</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <div class="login100-form-title">${title}</div>
            <div class="login100-form-title">${description}</div>
            <form method="post" id="formId">
                <div class="questions" style="margin-left: 10%">
                    <% int i = 0; %>
                    <c:forEach items="${map.entrySet()}" var="pair">
                        <p><label for="answer">${pair.getKey().name}</label></p>
                        <c:forEach items="${pair.getValue()}" var="answer">
                            <c:if test="${pair.getKey().type == 1}">
                                <p><input type="radio" id="answer" name="answer_<%=i%>">${answer.name}</p>
                            </c:if>
                            <c:if test="${pair.getKey().type == 2}">
                                <p><input type="checkbox" id="answer" name="answer_<%=i%>">${answer.name}</p>
                            </c:if>
                        </c:forEach>
                        <c:if test="${pair.getKey().type == 0}">
                            <input class="input100" type="text" name=<%=i%> class="input100">
                        </c:if>
                        <% i = i + 1; %>
                    </c:forEach>
                </div>

                <div class="container-login100-form-btn ">

                    <button class="login100-form-btn" type="submit" value="$checkUser">
                        Отправить
                    </button>
                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>
