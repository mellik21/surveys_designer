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
    <link href="bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <script src = "res/js/answers.js"></script>
    <script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://snipp.ru/cdn/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <style type="text/css">
        .containerBlock {
            list-style-type: none;
            margin: 10px auto;
            padding: 0;
        }
        .handle {
            display: inline-block;
            width: 16px;
            height: 16px;
            background: url(res/img/v.jpg) 0 50% no-repeat;
            margin-right: 10px;
            vertical-align: middle;
        }
    </style>
</head>

<body class="d-flex flex-column h-100" style="background-color: #F5F5F5">
<header>
    <!-- Fixed navbar -->
    <nav class="navbar navbar-expand-md navbar-dark ">
        <a class="navbar-brand" href="#" style="color:#0000CD;">Questionnaire designer</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
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
                <a class="btn dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false" style="color: #000000">
                    <i class="fa fa-user-circle" aria-hidden="true"></i>
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
<main role="main" class="flex-shrink-0" id="main">

    <div class="container">
        <h3 style="margin-top: 40px">Создайте опрос</h3>
        <form method="post" id="formId">
            <div class="container"
                 style="background-color: #FFFFFF; margin-outside: 10px; margin-inside: 10px; border-radius: 10px;">
                <br>
                <label for="title">Название опроса: </label>
                <input type="text" id="title" class="form-control" aria-label="Text input with checkbox">

                <div class="form-group">
                    <label for="description">Описание опроса:</label>
                    <textarea class="form-control" id="description" rows="3"></textarea>
                </div>
                <br>
            </div>
        </form>
    </div>


    <div class="container">
        <h3 style="margin-top: 20px">Придумайте вопросы</h3>
        <form method="post" id="formId1">

            <div id="containers" class="containerBlock">

                <div class="container"
                     style="background-color: #FFFFFF; margin-outside: 10px; margin-inside: 10px; margin-top:20px; border-radius: 10px;"
                     id="container0">
                    <br>

                    <i class="handle" aria-hidden="true"></i>
                    <h5 style="margin: 10px">Вопрос</h5>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="inputGroupSelect0">Тип вопроса</label>
                        </div>
                        <select class="custom-select" id="inputGroupSelect0" onchange="addAnswerBlockF()">
                            <option selected>Choose...</option>
                            <option value="1">Текстовый</option>
                            <option value="2">Один из многих</option>
                            <option value="3">Многие из многих</option>
                        </select>
                    </div>

                    <div class="new-question" id="q0">
                        <div class="name-question">
                            <label for="name-question">Название вопроса</label>
                            <input type="text" id="name-question" class="form-control questionName "
                                   aria-label="Text input with checkbox"  value="">
                        </div>
                    </div>
                    <br>
                    <button class="btn btn-light" href="#"
                            style=" position: relative; float:right; top:100%; margin-top:-20px;"
                            id="deleteQuestionButton0"
                            onclick="deleteQuestion(0)">
                        Удалить вопрос
                    </button>
                    <br>
                </div>


                <div id="questions">
                    ${questions}
                </div>
            </div>
            <br>
            <button type="button" class="btn btn-secondary btn-lg btn-block" onclick="addQuestionContainer()"><i
                    class="fa fa-plus" aria-hidden="true"></i>
                Добавить вопрос
            </button>
            <br>

            <div class="container">
                <input type="submit" class="btn btn-success" value="Отправить форму" id="s_submit">
            </div>
        </form>
    </div>

    <div id="scripts">
        <script>
            $('.containerBlock').sortable({
                handle: '.handle'
            });


            $('#formId1').submit(function() {
                addHidden();
                return true;
            });
        </script>
    </div>
</main>
</body>
</html>
