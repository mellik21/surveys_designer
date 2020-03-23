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

    <h2>List of surveys!</h2>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th>username</th>
        <th>questionnaire title</th>
        <th>question</th>
        <th>value</th>
        <th></th>
    </tr>
    </thead>
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
</main>
</body>
</html>
