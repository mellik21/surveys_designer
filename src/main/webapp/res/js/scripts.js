let countQuestion = 0;

function addQuestion() {
    let e = document.getElementById("typeQuestion");
    createQuestion(e.selectedIndex);
    document.getElementById("name-question").value="";
}

function createQuestion(typeQuestion) {
    countQuestion++;
    let questions = document.getElementById("questions");

    let question = document.createElement("div");
    question.setAttribute("id", "question-" + countQuestion);
    question.setAttribute("class", "question");
    questions.appendChild(question);

    let name = document.createElement("h2");
    name.textContent =  document.getElementById("name-question").value;
    name.setAttribute("name","questionName"+countQuestion);
    question.appendChild(name);

    let hiddenQuestion = document.createElement("input");
    hiddenQuestion.type="hidden";
    hiddenQuestion.setAttribute("name","questionInformation");
    hiddenQuestion.setAttribute("id","qInfo"+countQuestion);
    hiddenQuestion.value=typeQuestion+"/";
    hiddenQuestion.value +=document.getElementById("name-question").value+"/";
    question.appendChild(hiddenQuestion);

    let e;

    switch (typeQuestion) {
        case 0:
            e = createTextInput();
            break;
        case 1:
            e = createRadioInput();
            break;
        case 2:
            e = createCheckboxInput();
            break;
    }

    question.appendChild(e);
}

function createTextInput() {
    let textInput = document.createElement("input");
    textInput.setAttribute("type", "text");
    return textInput;
}

function createRadioInput() {
    let question = document.createElement("div");
    question.setAttribute("class", "radio-question");

    let radioQuestions = document.createElement("div");
    radioQuestions.setAttribute("id", "radio-questions-" + countQuestion);
    question.appendChild(radioQuestions);

    let text = document.createElement("input");
    text.setAttribute("type", "text");
    text.setAttribute("id", "text-radio-" + countQuestion);
    question.appendChild(text);

    let button = document.createElement("input");
    button.value = "Добавить вариант ответа";
    button.setAttribute("onclick", "addRadio(" + countQuestion + ")");
    button.setAttribute("class","btn");
    question.appendChild(button);

    return question;
}

function addRadio(id) {
    let question = document.getElementById("radio-questions-" + id);
    let p = document.createElement("p");

    let radioQuestion = document.createElement("input");
    radioQuestion.className="my_radio";
    radioQuestion.type="radio";
    radioQuestion.name = "my_radio_"+id;

    let text = document.getElementById("text-radio-" + id).value;
    let hiddenQuestion = document.getElementById("qInfo"+id);
    hiddenQuestion.value += text+"/";
    p.appendChild(radioQuestion);
    p.appendChild(document.createTextNode(text));
    question.appendChild(p);
    document.getElementById("text-radio-" + id).value = "";
}

function createCheckboxInput() {
    let question = document.createElement("div");
    question.setAttribute("class", "checkbox-question");

    let checkboxQuestions = document.createElement("div");
    checkboxQuestions.setAttribute("class", "checkbox-questions");
    checkboxQuestions.setAttribute("id", "checkbox-questions-" + countQuestion);
    question.appendChild(checkboxQuestions);

    let text = document.createElement("input");
    text.setAttribute("type", "text");
    text.setAttribute("id", "text-checkbox-" + countQuestion);
    question.appendChild(text);

    let button = document.createElement("input");
    button.value = "Добавить вариант ответа";
    button.setAttribute("onclick", "addCheckbox(" + countQuestion + ")");
    question.appendChild(button);

    return question;
}

function addCheckbox(id) {
    let question = document.getElementById("checkbox-questions-" + id);

    let p = document.createElement("p");

    let checkboxQuestion = document.createElement("input");
    checkboxQuestion.setAttribute("type", "checkbox");
    let text = document.getElementById("text-checkbox-" + id).value;

    let hiddenQuestion = document.getElementById("qInfo"+id);
    hiddenQuestion.value += text+"/";
    checkboxQuestion.setAttribute("value", text);
    p.appendChild(checkboxQuestion);
    p.appendChild(document.createTextNode(text));
    question.appendChild(p);

    document.getElementById("text-checkbox-" + id).value = "";
}