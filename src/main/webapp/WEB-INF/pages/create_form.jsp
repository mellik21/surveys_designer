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

    <script src="res/js/scripts.js"></script>
</head>

<body class="d-flex flex-column h-100"  style="background-color: #F5F5F5">
<header>
    <!-- Fixed navbar -->
    <nav class="navbar navbar-expand-md navbar-dark fixed-top ">
        <a class="navbar-brand" href="#" style="color:#0000CD;">Questionnaire designer</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation" >
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#" style="color: #000000">Мои опросы<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#" style="color: #000000">Просмотр ответов</a>
                </li>
            </ul>

            <a class="btn btn-primary" href="#" role="button">Новый опрос</a>

            <div class="dropdown show">
                <a class="btn dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color: #000000">
                    <i class="fa fa-user-circle" aria-hidden="true" ></i>
                    USERNAME
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
        <h3>
            Придумайте вопросы
        </h3>

<div class="container">
<form method="post" id="formId">
    <label for="title">Название опроса: </label>
    <input id="title" name="title" type="text">
    <label for="description">Описание опроса: </label>
    <input id="description" name="description" type="text">

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
</div>
</div>
</main>
</body>
</html>
