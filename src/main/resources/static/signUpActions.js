




//fields with minimum sizes
let MIN_SIZES = [["surname", 2], ["username", 6]]

//all inputs
let listInputs = document.querySelectorAll('input');

document.getElementById("confirmButton").disabled = true
runAll()

// //toggle button for choosing validation
// let toggleButton = document.getElementById('validationToggle')
// toggleButton.addEventListener('click', function(){
//     if (toggleButton.value == "Selecteer client-side validatie") {
//         runAll()
//         toggleButton.value = "Selecteer server-side validatie"
//     } else {
//         location.href='pre-sign-up'
//         return
//     }
// })


//start function (toggled on/off)
function runAll() {
    // if (document.getElementById('validationToggle').value == "Selecteer client-side validatie") {
        for (input of listInputs) {
            input.addEventListener('focusout', function() {
                allChecker(this)
                checkContents()
            })
        }
    // }
}



//contains all checks - logical sequence is important!
function allChecker(input) {

    normalizeInputField(input)

    document.getElementById("confirmButton").disabled = true

    if (isEmpty(input)) return;
    if (onlyNumbers(input)) return;
    if (onlyLetters(input)) return;
    if (minSize(input)) return;
    if (pass(input)) return;
    if (elftest(input)) return;
    if (userOccupied(input)) return;
}


//individual checks with class triggers and error messages

function isEmpty(input, exception = false) {
    if (!exception && !input.classList.contains("notEmpty")) {
        return false
    }
    if (input.value.trim().length == 0) {
        displayMessage(input, "Veld is leeg")
        return true
    }
    return false
}

function minSize(input) {
    if (!input.classList.contains("minSize")) {
        return false
    }

    let minSize
    for (let item of MIN_SIZES) {
        if (input.id == item[0]) minSize = item[1]
    }

    if (input.value.trim().length < minSize) {
        displayMessage(input, `Moet minstens ${minSize} tekens zijn`)
        return true
    }

    return false
}

function onlyNumbers(input) {
    if (!input.classList.contains("onlyNumbers")) {
        return false
    }
    if (!/^[0-9]+$/.test(input.value)) {
        displayMessage(input, "Alleen getallen toegestaan")
        return true
    }
    return false
}

function onlyLetters(input) {
    if (!input.classList.contains("onlyLetters")) {
        return false
    }

    //two special cases; to be extracted?
    if (input.id == "inserts" && input.value.trim().length == 0) {
        return false
    }
    if (input.id == "initials") {
        input.value = input.value.replaceAll(".", "")
        if (isEmpty(input, true)) return false;
    }

    //actual check
    if (!/^[a-zA-Z -]*$/.test(input.value)) {
        displayMessage(input, "Alleen letters toegestaan")
        return true
    }
    return false
}

function pass(input) {
    if (!input.classList.contains("passCheck")) {
        return false
    }
    let password = document.getElementById("password").value
    let password2 = document.getElementById("password2").value

    if (password != password2) {
        displayMessage(input, "Wachtwoorden niet identiek")
        return true;
    }
}

function elftest(input){
    if (!input.classList.contains("elfTest")) {
        return false
    }

    var numbers, check;
    var number = input.value;

    if(number.length == 8){
        number = 0+number;
    }
    if(number.length != 9){
        displayMessage(input, "Ongeldig BSN")
        return true;
    }

    numbers = number.split("");
    check = (parseInt(numbers[0],10)*9) +
        (parseInt(numbers[1],10)*8) +
        (parseInt(numbers[2],10)*7) +
        (parseInt(numbers[3],10)*6) +
        (parseInt(numbers[4],10)*5) +
        (parseInt(numbers[5],10)*4) +
        (parseInt(numbers[6],10)*3) +
        (parseInt(numbers[7],10)*2) +
        (parseInt(numbers[8],10)*-1);


    if (check % 11 !== 0) {
        displayMessage(input, "Ongeldig BSN")
        return true
    } else return false

}

function userOccupied(input) {
    if (!input.classList.contains("userOccupied")) {
        return false
    }

    let url = `/username-occupied-check/${input.value}`

    fetch(url)
        .then(response => response.json())
        .then(jsonObject => {

            if (jsonObject) {
                displayMessage(input, "Naam al in gebruik")
                return true
            }
            return false
        })
        .catch(function (error) {
            console.log(error)
        })
}




//general methods for final processing

function checkContents() {
    let allFull = true;

    for (input of listInputs) {
        if (input.classList.contains("notEmpty") && input.value.length == 0) {
            allFull = false
        }
    }

    if (allFull) {
        document.getElementById("confirmButton").disabled = false
    }
}

function displayMessage(input, message) {
    input.value = null
    input.setAttribute("placeholder", message)
    input.style.border = "3px solid red"
}

function normalizeInputField(input) {
    input.removeAttribute('placeholder')
    input.style.border = "thin solid black"
}

















// Code for retrieving ZIP information from bwnr.nl API
// Declare empty variables
var zipcode = "";
var housenumber = "";
var affix = "";

function retrieve_address(id, value) {
    console.log("Methode wordt aangeroepen")
    // Load form data into variables
    // Return if no valid input (yet)
    if (id == "zipcode") {
        var zip = value.trim();
        console.log(zip);
        // Check for minimum of 6 characters
        if (zip.length < 6) {
            return;
        }
        // Check for numeric value of >1000
        var zip_numeric = zip.substr(0, 4);
        if (parseFloat(zip_numeric) < 1000) {
            return;
        }
        // Check if last 2 characters are alphabetical
        var zip_alpha = zip.substr(-2);
        if (zip_alpha.charCodeAt(0) < 65 || zip_alpha.charCodeAt(0) > 122 || zip_alpha.charCodeAt(1) < 65 || zip_alpha.charCodeAt(1) > 122) {
            return;
        }
        // Concatenate final zipcode
        zipcode = zip_numeric + zip_alpha.toUpperCase();
        document.getElementById("zipcode").value = zipcode;
    }
    if (id == "housenumber") {
        // Check if housenumber is not empty and >0
        housenumber = parseFloat(value);
        if (!housenumber || housenumber == 0) {
            return;
        }
        document.getElementById("housenumber").value = housenumber;
    }
    if (id == "affix") {
        affix = value.trim();
        document.getElementById("affix").value = affix;
    }
    if (!housenumber) {
        return;
    }

    // Create API url
    var apiRequest = "https://bwnr.nl/postcode.php?pc=" + zipcode + "&hn=" + housenumber + "&tv=" + affix + "&tg=data";
    var xmlHttpRequest = new XMLHttpRequest();

    // Catch responses
    xmlHttpRequest.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var rString = this.responseText;
            if (rString == "Geen resultaat.") {
                return;
            }
            if (rString == "Input onvolledig.") {
                return;
            }
            if (rString == "Onbekende API Key.") {
                return;
            }
            if (rString == "Over quota") {
                return;
            }

            // Autofill responses
            var aResponse = rString.split(";");
            document.getElementById("street").value = aResponse[0];
            document.getElementById("city").value = aResponse[1];
            normalizeInputField(document.getElementById("street"))
            normalizeInputField(document.getElementById("city"))

        }
    }

    // Execute API request
    xmlHttpRequest.open("GET", apiRequest, true);
    xmlHttpRequest.send();
}




/*TODO Dit moet nog aan*/

// function pass() {
//     var password = document.getElementById("password").value
//     var password2 = document.getElementById("password2").value
//     if (password != password2) {
//         alert("Wachtwoorden komen niet overeen");
//         return false;
//     }
//     return true;
//
//
// }
