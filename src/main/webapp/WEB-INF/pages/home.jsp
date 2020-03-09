<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home page</title>

    <link rel="stylesheet" type="text/css" href="res/css/homepage.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="topbar">
    <a href="<c:url value="/dashboard"/>">Questionnaire designer</a>
    <a href="<c:url value="/"/>"><i class="fa fa-sign-out" aria-hidden="true"></i>Выйти</a>
</div>



<input type="button" class="bal" value="Создать опрос" onClick='location.href="/designer/create_form"'>

<% int i = 1; %>
<c:forEach var="questionnaire" items="${questionnaireList}">

    <div class="block">
        <h4><a href="<c:url value="/view?questionnaireId=${questionnaire.id}"/>"
               title="${questionnaire.title}" target="_blank">${questionnaire.title}</a>
        </h4>

        <p style=" margin: 10px;">
                ${questionnaire.description}
        </p>


        <div class="icons">
            <a href="<c:url value="/view_answers?questionnaireId=${questionnaire.id}"/>" title="посмотреть ответы"><i
                    class="fa fa-users" aria-hidden="true"></i>Ответы ${questionnaire.numberOfAnswers}</a>

            <a href="<c:url value="/edit?q=${questionnaire.id}"/>" title="edit"><i
                    class="fa fa-pencil-square-o" aria-hidden="true"></i>Редактировать</a>
            <a href="<c:url value="/delete?questionnaireId=${questionnaire.id}"/>" title="delete"><i
                    class="fa fa-trash-o" aria-hidden="true"></i>Удалить</a>

            <a href="<c:url value="/questionnaire?q=${questionnaire.id}"/>"
               title="open questionnaire" target="_blank"><i class="fa fa-share" aria-hidden="true"></i>Поделиться</a>
        </div>
    </div>

    <% i = i + 1; %>
</c:forEach>


<c:if test="${questionnaireList.size() == 0}">
    <div class="block">
        <h4>Список опросов пуст!</h4>
    </div>
</c:if>
</body>
</html>