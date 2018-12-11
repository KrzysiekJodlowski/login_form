'use strict';

function setOnKeyUpEvents() {
    document.getElementById("button").disabled = true;

    let userNameField = document.getElementById("username");
    let userPasswordField = document.getElementById("password");

    userNameField.addEventListener('keyup', checkIfFieldsFilled, false);
    userPasswordField.addEventListener('keyup', checkIfFieldsFilled, false);
}

function checkIfFieldsFilled() {
    let userNameValue = document.getElementById("username").value;
    let userPasswordValue = document.getElementById("password").value;

    if (userNameValue != "" && userPasswordValue != "") {
        document.getElementById("button").disabled = false;
    } else {
        document.getElementById("button").disabled = true;
    }
}

setOnKeyUpEvents();