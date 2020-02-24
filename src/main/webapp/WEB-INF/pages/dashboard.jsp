<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link href="<c:url value="/res/img/v6.png"/>" rel="icon" type="image/png"/>
    <link rel="stylesheet" type="text/css"
          href="https://fonts.googleapis.com/css?family=Roboto:400,300|Raleway:300,400,900,700italic,700,300,600">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="<c:url value="/res/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css"/>

    <link href="<c:url value="/res/css/style.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/res/css/dashboard.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="loader"></div>
<div id="myDiv">
    <div class="header-dogo">
        <div class="bg-color-dogo">
            <nav class="navbar navbar-default navbar-fixed-top">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        </button>
                        <a class="navbar-brand">Na<span class="logo-dec">stya</span></a>
                    </div>
                    <ul class="navbar-nav navbar-right">
                        <li><a href="<c:url value="/dashboard"/>"><i class="fa fa-th-list" aria-hidden="true"></i> Мои опросы</a></li>
                        <li><a href="<c:url value="/create_form"/>"> Создать опрос</a></li>
                        <li class="active"><a href="<c:url value="/"/>"><i class="fa fa-sign-out" aria-hidden="true"></i>Выход</a></li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
</div>

<h2>List of surveys!</h2>
<table>
    <tr>
        <th>№</th>
        <th style="width: 200px">Название опроса</th>
        <th style="width: 100px">Число вопросов</th>
        <th style="width: 700px"></th>
    </tr>
    <% int i = 1; %>
    <c:forEach var="questionnaire" items="${questionnairesList}">
        <tr>
        <td><%=i %>
        </td>
        <td >
            <a href="<c:url value="/view?questionnaireId=${questionnaire.id}"/>"
               title="${questionnaire.title}" target="_blank">${questionnaire.title}</a>
        </td>
        <td >
            <a href="<c:url value="/view?questionnaireId=${questionnaire.id}"/>" title="view">${questionnaire.size}</a>
        </td>

        <td >
            <p>
            <a href="<c:url value="/view_answers?questionnaireId=${questionnaire.id}"/>" title="посмотреть ответы"><i
                    class="fa fa-users" aria-hidden="true"></i>Ответы ${questionnaire.numberOfAnswers}</a>

               <a href="<c:url value="/edit?q=${questionnaire.id}"/>" title="edit"><i
                       class="fa fa-pencil-square-o" aria-hidden="true"></i>Редактировать</a>
               <a href="<c:url value="/delete?questionnaireId=${questionnaire.id}"/>" title="delete"><i
                    class="fa fa-trash-o" aria-hidden="true" ></i>Удалить</a>

                <a href="<c:url value="/questionnaire?q=${questionnaire.id}"/>"
                   title="open questionnaire" target="_blank"><i class="fa fa-share" aria-hidden="true"></i>Поделиться</a>
            </p>

        </td>

        </tr>
        <% i = i + 1; %>
    </c:forEach>
</table>
</body>
</html>
