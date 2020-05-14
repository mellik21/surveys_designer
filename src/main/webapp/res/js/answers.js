var counter = 0;
var question = 0;


function deleteHeader(index) {
    var element = document.getElementById("answersHeader" + index);
    if (element) {

        element.parentNode.removeChild(element);
    }
}

function addHeader(index) {
    let block = document.getElementById("answers" + index);
    deleteHeader(index);

    let h = document.createElement("h5");
    h.setAttribute("style", "margin: 10px");
    h.textContent = "Ответы";
    h.setAttribute("id", "answersHeader" + index);
    block.appendChild(h);

    h.append(document.createElement("br"));
}

function createAnswer(index) {

    if (!document.getElementById("answers" + index)) {
        let answers = document.createElement("div");
        answers.setAttribute("id", "answers" + index);

        let prev = document.getElementById("q" + index);
        prev.append(answers);
    }

    addHeader(index);

    if (!document.getElementById("answersZone" + index)) {
        let answers = document.createElement("div");
        answers.setAttribute("id", "answersZone" + index);

        let prev = document.getElementById("q" + index);
        prev.append(answers);
        addBlock(index);
    }
}

function addDeleteIcon(prev) {
    let deleteIcon = document.createElement("a");
    deleteIcon.setAttribute("class", "btn-light");
    deleteIcon.setAttribute("href", "#");
    deleteIcon.setAttribute("onclick", "deleteAnswer(" + counter + ")");
    deleteIcon.textContent = "х";
    prev.appendChild(deleteIcon);

}

function addButton(index) {
    let block = document.getElementById("answersZone" + index);
    let button = document.createElement("button");
    button.setAttribute("class", "btn btn-light");
    button.setAttribute("type", "button");
    button.setAttribute("onclick", "addAnswer(" + index + ")");
    button.setAttribute("id", "button" + index);

    button.textContent = " + Ответ";
    block.appendChild(button);
}

function addBlock(index) {
    let block = document.getElementById("answersZone" + index);

    let row = document.createElement("div");
    row.setAttribute("class", "form-row");
    row.setAttribute("id", "row" + counter);


    let ans = document.createElement("input");
    ans.setAttribute("type", "text");
    ans.setAttribute("id", "ans" + counter);
    ans.setAttribute("class", "form-control answer" + index);
    ans.setAttribute("value", counter);


    let col1 = document.createElement("div");
    col1.setAttribute("class", "col");
    col1.appendChild(ans);

    let col2 = document.createElement("div");
    col2.setAttribute("class", "col");
    addDeleteIcon(col2);
    //addUpAndDownButtons(col2);

    row.appendChild(col1);
    row.appendChild(col2);

    block.append(row);

    addButton(index);
}

function deleteAll(index) {
    var element = document.getElementById("answers" + index);
    if (element) {
        element.parentNode.removeChild(element);
    }

    var element1 = document.getElementById("answersZone" + index);
    if (element1) {
        element1.parentNode.removeChild(element1);
    }
}

function addAnswer(index) {
    deleteButton(index);

    if (counter > 0) {
        let prev = document.getElementById("answersZone" + index);

        let row = document.createElement("div");
        row.setAttribute("id", "row" + counter);
        row.setAttribute("class", "form-row");

        let ans = document.createElement("input");
        ans.setAttribute("type", "text");
        ans.setAttribute("id", "ans" + counter);
        ans.setAttribute("class", "form-control answer" + index);
        ans.setAttribute("value", counter);


        let col1 = document.createElement("div");
        col1.setAttribute("class", "col");
        col1.appendChild(ans);

        let col2 = document.createElement("div");
        col2.setAttribute("class", "col");
        addDeleteIcon(col2);
        // addUpAndDownButtons(col2);

        row.appendChild(col1);
        row.appendChild(col2);

        prev.append(row);
    }
    counter++;
    addButton(index);
}

function deleteButton(index) {
    var element = document.getElementById("button" + index);
    if (element) {
        element.parentNode.removeChild(element);
    }
}

function addUpAndDownButtons(node) {
    let upButton = document.createElement("a");
    upButton.setAttribute("class", "btn btn-light");
    upButton.setAttribute("id", "upButton" + index);
    upButton.textContent = "ᐃ";

    let downButton = document.createElement("a");
    downButton.setAttribute("class", "btn btn-light");
    downButton.setAttribute("id", "downButton" + index);
    downButton.textContent = "ᐁ";

    node.appendChild(upButton);
    node.appendChild(downButton);

}

function deleteAnswer(index) {
    var element = document.getElementById("row" + index);
    if (element) {
        element.parentNode.removeChild(element);
    }
}

function addQuestionContainer(num) {
    question = num;
    question++;
    let containers = document.getElementById("containers");

    let container = document.createElement("div");
    container.setAttribute("class", "container");
    container.setAttribute("style", "background-color: #FFFFFF; margin-outside: 10px; margin-inside: 10px; border-radius: 10px; margin-top:20px;");
    container.setAttribute("id", "container" + question);
    containers.append(container);

    container.append(document.createElement("br"));

    let i = document.createElement("i");
    i.setAttribute("class", "handle");
    i.setAttribute("aria-hidden", "true");
    container.appendChild(i);

    let header = document.createElement("h5");
    header.setAttribute("style", "margin: 10px");
    header.textContent = "Вопрос";
    container.appendChild(header);

    let inputGroup = document.createElement("div");
    inputGroup.setAttribute("class", "input-group mb-3");
    container.appendChild(inputGroup);

    let inputGroupPrepend = document.createElement("div");
    inputGroupPrepend.setAttribute("class", "input-group-prepend");
    inputGroup.appendChild(inputGroupPrepend);

    let label = document.createElement("label");
    label.setAttribute("class", "input-group-text");
    label.setAttribute("for", "inputGroupSelect" + question);
    label.textContent = "Тип вопроса";
    inputGroupPrepend.appendChild(label);

    let select = document.createElement("select");
    select.setAttribute("class", "custom-select");
    select.setAttribute("id", "inputGroupSelect" + question);
    select.setAttribute("onchange", "addAnswerBlock(" + question + ")");   //Выбор типа вопроса для 2+ вопросов
    inputGroup.appendChild(select);

    let option0 = document.createElement("option");
    option0.selected = true;
    option0.textContent = "Choose..";
    select.appendChild(option0);

    let option1 = document.createElement("option");
    option1.value = "1";
    option1.textContent = "Текстовый";
    select.appendChild(option1);

    let option2 = document.createElement("option");
    option2.value = "2";
    option2.textContent = "Один из многих";
    select.appendChild(option2);

    let option3 = document.createElement("option");
    option3.value = "3";
    option3.textContent = "Многие из многих";
    select.appendChild(option3);

    let q = document.createElement("div");
    q.setAttribute("class", "new-question");
    q.setAttribute("id", "q" + question);
    container.appendChild(q);

    let qName = document.createElement("div");
    qName.setAttribute("class", "name-question");
    q.appendChild(qName);

    let label1 = document.createElement("label");
    label1.setAttribute("for", "name-question");
    label1.textContent = "Название вопроса";
    qName.appendChild(label1);

    let question1 = document.createElement("input");
    question1.setAttribute("type", "text");
    question1.setAttribute("id", "name-question");
    question1.setAttribute("class", "form-control questionName");
    question1.setAttribute("value", "");
    qName.appendChild(question1);

    container.appendChild(document.createElement("br"));

    let a = document.createElement("button");
    a.setAttribute("class", "btn btn-light");
    a.setAttribute("style", "position: relative; float:right; top:100%; margin-top:-20px;");
    a.setAttribute("id", "deleteQuestionButton" + question);
    a.setAttribute("onclick", "deleteQuestion(" + question + ")");
    a.textContent = "Удалить вопрос";

    container.appendChild(a);
    container.appendChild(document.createElement("br"));
}

function deleteQuestion(index) {
    var element = document.getElementById("container" + index);
    if (element) {
        element.parentNode.removeChild(element);
    }
}

function addHidden() { // format : question text / type / answers / id
    let block = document.getElementById("questions");

    let head = document.getElementById("title").value + "/" + document.getElementById("description").value;

    let element = document.createElement("input");
    element.setAttribute("type", "hidden");
    element.setAttribute("name", "questionInformation");
    element.setAttribute("value", head);
    block.appendChild(element);

    var questionNames = document.getElementsByClassName("questionName");
    alert(questionNames.length);
    var i;

    for (i = 0; i < questionNames.length; i++) {
        var type = document.getElementById("inputGroupSelect" + i);

        var el = document.createElement("input");
        element.setAttribute("type", "hidden");

        var code = questionNames[i].value;
        code += "/" + type.options[type.selectedIndex].value;

        if (type.options[type.selectedIndex].value > 1) {
            let answers = document.getElementsByClassName("answer" + i);
            let j;
            for (j = 0; j < answers.length + 1; j++) {
                let id = document.getElementById("answerId" + i + "/" + j);
                code += "/" + id;
                code += "/" + answers[j].value;
            }
        }

        let id = document.getElementById("questionId" + i);
        if (id) {
            code += "/" + id.value;
        } else {
            code += "/" + "-1";
        }
        alert(code);
        el.setAttribute("value", code);
        el.setAttribute("name", "questionInformation");
        block.appendChild(el);
    }
}

/* //todo testGrammar
  Тип должен быть !=0
  Поле названия должно быть заполнено
  Название вопроса должно быть введено
  Все ответы непустые

  Если что-то из этого не так - подсветить нужный блок и вывести сообщение рядом с кнопкой отправки:
  Поле %причина% не может быть пустым
 */
function testGrammar() {
    let types = document.getElementsByClassName("custom-select");
    for (let i = 0; i < types.length; i++) {
        if (types[i].options[types[i].selectedIndex].value === "Choose..") {
            alert("OH NO WRONG TYPE");
        }
    }
    if (document.getElementById("title").value === "") {
        alert("OH NO WRONG TITLE");
    }
    if (document.getElementById("description").value === "") {
        alert("OH NO WRONG DESCRIPTION");
    }
}

function addAnswerBlock(index) {
    let e = document.getElementById("inputGroupSelect" + index);
    if (e.options.selectedIndex > 1) {
        createAnswer(index);
    } else {
        //  counter = 0;
        deleteAll(index);
    }
}

function addAnswerBlockF() {
    addAnswerBlock(0);
}

function setQuestionNumber(num) {
    alert(num);
    question = num;
}