


function showTable(id) {
    let highestSaldoMKB = document.getElementById("highestSaldosMkb").classList;
    let averageBalanceMKB = document.getElementById("averageBalanceBySector").classList;
    let mostActiveCus = document.getElementById("mostActiveMkbCustomers").classList;

    if (id === "option1") {
        highestSaldoMKB.replace("d-none", "d-block");
        averageBalanceMKB.replace("d-block", "d-none");
        mostActiveCus.replace("d-block", "d-none");
    }

    if (id === "option2") {
        averageBalanceMKB.replace("d-none", "d-block");
        mostActiveCus.replace("d-block", "d-none");
        highestSaldoMKB.replace("d-block", "d-none");
    }

    if (id === "option3") {
        mostActiveCus.replace("d-none", "d-block");
        averageBalanceMKB.replace("d-block", "d-none");
        highestSaldoMKB.replace("d-block", "d-none");
    }
    if (id === "option4") {
        mostActiveCus.replace("d-block", "d-none");
        averageBalanceMKB.replace("d-block", "d-none");
        highestSaldoMKB.replace("d-block", "d-none");
    }
}
