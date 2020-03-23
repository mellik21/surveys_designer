<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Answer Viewer</title>
    <link href="<c:url value="/res/img/v6.png"/>" rel="icon" type="image/png"/>
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:400,300|Raleway:300,400,900,700italic,700,300,600">
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
                        <li class="active"><a href="create_form">Create new form</a></li>
                        <li class=""><a href="">? Help</a></li>
                        <li class=""><a href="">Answers</a></li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
</div>

<h2>List of surveys!</h2>
<table>
    <tr>
        <th>username</th>
        <th>questionnaire title</th>
        <th>question</th>
        <th>value</th>
        <th></th>
    </tr>
    <c:forEach var="answer" items="${answerList}">
        <tr>
            <td>${answer.username}</td>
            <td>${answer.questionnaireTitle}</td>
            <td>${answer.question}</td>
            <td></td>
            <td></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
