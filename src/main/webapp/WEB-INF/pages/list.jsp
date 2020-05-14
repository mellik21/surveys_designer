<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список опросов</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


    <!-- Bootstrap core CSS -->
    <link href="bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body class="d-flex flex-column h-100" style="background-color: #F5F5F5">
<header>
    <nav class="navbar navbar-expand-md navbar-light bg-light">
        <a class="navbar-brand" href="#" style="color:#0000CD;">Questionnaire designer</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/designer/dashboard" style="color: #000000">Мои опросы<span
                            class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#" style="color: #000000">Просмотр ответов</a>
                </li>
            </ul>

            <a class="btn btn-primary" href="/designer/create_form" role="button">Новый опрос</a>


        </div>
    </nav>
</header>


<!-- Begin page content -->
<main role="main" class="flex-shrink-0">
    <br> <br> <br>

    <h2>Общий список опросов</h2>

    <c:forEach var="questionnaire" items="${questionnairesList}">
        <div class="container" style="background:#FFFFFF; margin-top: 20px">
            <h4><a href="<c:url value="/view?questionnaireId=${questionnaire.id}"/>"
                   title="${questionnaire.title}" target="_blank">${questionnaire.title}</a></h4>

            <p>${questionnaire.description}</p>

        </div>
    </c:forEach>


</main>
</body>
</html>