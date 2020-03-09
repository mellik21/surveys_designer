<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home page</title>

    <link rel="stylesheet" type="text/css" href="res/css/homepage.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="res/js/scripts.js"></script>
</head>
<body>
<div class="topbar">
    <a href="<c:url value="/dashboard"/>">Questionnaire editing</a>
</div>

<input type="button" class="bal" value="Создать опрос" onClick='location.href="/designer/create_form"'>

<div class="block">
    <form method="post" id="formId">
        <label>Questionnaire ID: ${questionnaire.id} </label>
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
                <p><input type="text" value=" ${pair.getKey().name}"></p>
                <% int i = 0; %>

                <c:forEach items="${pair.getValue()}" var="answer">

                    <c:if test="${pair.getKey().type == 1}">

                        <p><input type="radio" name="answer_<%=i%>">
                            <input type="text" value="  ${answer.name}"></p>
                    </c:if>
                    <c:if test="${pair.getKey().type == 2}">
                        <p> <%=i%>. <input type="checkbox" name="answer_<%=i%>">
                            <input type="text" value=" ${answer.name}"></p>
                    </c:if>
                </c:forEach>
                <% i = i + 1; %>
            </c:forEach>
        </div>
        <br>
        <input type="submit" class="add-questionnaire" value="Отправить форму" id="s_submit">
        <br>

    </form>
</div>
<br>
</body>
</html>
