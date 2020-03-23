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
    <nav class="navbar navbar-expand-md navbar-light bg-light">
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



    <div class="container">

    <% int i = 1; %>
    <c:forEach var="questionnaire" items="${questionnaireList}">

        <div class="container" style="background:#FFFFFF; margin-top: 20px">
            <h4><a href="<c:url value="/view?questionnaireId=${questionnaire.id}"/>"
                   title="${questionnaire.title}" target="_blank">${questionnaire.title}</a> </h4>

            <p>${questionnaire.description}</p>

          <p>
                <a href="<c:url value="/view_answers?questionnaireId=${questionnaire.id}"/>"
                   title="посмотреть ответы"><i
                        class="fa fa-users" aria-hidden="true"></i>Ответы ${questionnaire.numberOfAnswers}</a>

                <a href="<c:url value="/edit?q=${questionnaire.id}"/>" title="edit"><i
                        class="fa fa-pencil-square-o" aria-hidden="true"></i>Редактировать</a>
                <a href="<c:url value="/delete?questionnaireId=${questionnaire.id}"/>" title="delete"><i
                        class="fa fa-trash-o" aria-hidden="true"></i>Удалить</a>

                <a href="<c:url value="/questionnaire?q=${questionnaire.id}"/>"
                   title="open questionnaire" target="_blank"><i class="fa fa-share"
                                                                 aria-hidden="true"></i>Поделиться</a>

          </p>
        </div>

        <% i = i + 1; %>
    </c:forEach>


    <c:if test="${questionnaireList.size() == 0}">
        <div class="block">
            <h4>Список опросов пуст!</h4>
        </div>
    </c:if>
    </div>
</main>
<footer class="footer mt-auto py-3">
    <div class="container">
        <span class="text-muted">Place sticky footer content here.</span>
    </div>
</footer>
</body>
</html>