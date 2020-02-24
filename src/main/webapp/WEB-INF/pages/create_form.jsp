<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Dashboard</title>
    <link href="<c:url value="/res/img/v6.png"/>" rel="icon" type="image/png"/>
    <link rel="stylesheet" type="text/css"
          href="https://fonts.googleapis.com/css?family=Roboto:400,300|Raleway:300,400,900,700italic,700,300,600">
    <link href="<c:url value="/res/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/res/css/style.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/res/css/dashboard.css"/>" rel="stylesheet" type="text/css"/>
    <script src="res/js/scripts.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

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
                        <a class="navbar-brand" href="<c:url value="/"/>">Na<span class="logo-dec">stya</span></a>
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

<h2>Questionnaire creation</h2>
<h2><small>${message}</small></h2>

<form method="post" id="formId">
    <label for="title">Questionnaire title: </label>
    <input id="title" name="title" type="text">
    <div class="new-question">
        <div class="name-question">
            <label for="name-question">Название вопроса</label>
            <input type="text" id="name-question">
        </div>
        <div class="type-question">
            <label for="typeQuestion"> Тип вопроса</label>
            <select id="typeQuestion">
                <option>Текстовый</option>
                <option>Один из многих</option>
                <option>Многие из многих</option>
            </select>
        </div>
    </div>
    <div class="add-question">
        <button onclick="addQuestion()" type="button">Добавить вопрос</button>
    </div>

    <div id="questions">
    </div>
    <br>
    <input type="submit" class="add-questionnaire" value="Отправить форму" id="s_submit">
    <br>

</form>

<br>
</body>
</html>
