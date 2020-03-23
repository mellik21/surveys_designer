<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en" class="h-100">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Sticky Footer Navbar Template · Bootstrap</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.4/examples/sticky-footer-navbar/">

    <!-- Bootstrap core CSS -->
    <link href="bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body class="d-flex flex-column h-100"  style="background-color: #F5F5F5">
<header>
    <!-- Fixed navbar -->
    <nav class="navbar navbar-expand-md navbar-dark">
        <a class="navbar-brand" href="#" style="color:#0000CD;">Questionnaire designer</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation" >
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/designer/dashboard" style="color: #000000">Мои опросы<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#" style="color: #000000">Просмотр ответов</a>
                </li>
            </ul>

            <a class="btn btn-primary" href="/designer/create_form" role="button">Новый опрос</a>

            <div class="dropdown show">
                <a class="btn dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color: #000000">
                    <i class="fa fa-user-circle" aria-hidden="true" ></i>
                    ${username}
                </a>

                <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </div>



        </div>
    </nav>
</header>


<!-- Begin page content -->
<main role="main" class="flex-shrink-0">
    <br> <br> <br>
<input type="button" class="bal" value="Создать опрос" onClick='location.href="/designer/create_form"'>

<div class="block">
    <form method="post" id="formId">
        <input type="hidden" id="qId" value="${q}" name="qId" >
        <label for="title">Questionnaire title: </label>
        <input id="title" name="title" type="text" value="${questionnaire.title}">
        <label for="description">Description: </label>
        <input id="description" name="description" type="text" value="${questionnaire.description}">

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
            <c:forEach items="${map.entrySet()}" var="pair">
                <% int ii = 0; %>
                <input type="hidden" name = "questionInformation" id="qInfo+<%=ii %>" value="${pair.getKey().type}/${pair.getKey().name}/">
                <c:forEach items="${pair.getValue()}" var="answer">
                <% int i = 0; %>
                    <c:if test="${pair.getKey().type == 1}">

                        <p><input type="radio" name="answer_<%=i%>">
                            <input type="text" value="  ${answer.name}"></p>
                    </c:if>
                    <c:if test="${pair.getKey().type == 2}">
                        <p> <%=i%>. <input type="checkbox" name="answer_<%=i%>">
                            <input type="text" value=" ${answer.name}"></p>
                    </c:if>
                    <% i = i + 1; %>
                </c:forEach>

                <% ii = ii + 1; %>
            </c:forEach>
        </div>
        <br>
        <input type="submit" class="add-questionnaire" value="Отправить форму" id="s_submit">
        <br>

    </form>
</div>
<br>
</main>
</body>
</html>
