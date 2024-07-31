/*!
* Start Bootstrap - Modern Business v5.0.7 (https://startbootstrap.com/template-overviews/modern-business)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-modern-business/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project
// Function for changing colors for effect.
// Function for changing colors for effect.

function fn() {
    var amountBox = document.getElementById("amount");
    var categoryBox = document.getElementById("category")
    var optionBox = document.getElementById("isDeposit");
    var option = optionBox.value;

    if (option == "Deposit") {
        amountBox.classList.remove("spending");
        optionBox.classList.remove("spending");
        categoryBox.value = "1";
    } else {
        amountBox.classList.add("spending");
        optionBox.classList.add("spending");
        categoryBox.value = "0";
        if (categoryBox.value == "1"){
            categoryBox.value = "1";
        }
    }
}
// Formats the date to a proper format for Java.
function formatDate() {
    console.log("UPDATED THE DATE")
    var originalDate = document.getElementById("date").value;
    var properDate = new Date(originalDate);
    console.log(originalDate);
    console.log(properDate);
    console.log(properDate.getDate())

    var formattedDate = (properDate.getMonth() + 1) +
        '/' + (parseInt(properDate.getDate()) + 1) +
        '/' + properDate.getFullYear();
    document.getElementById("transactionDate").value =
        properDate.getFullYear() + '-' +
        (properDate.getMonth() + 1) + '-' +
        (properDate.getDate() + 1);
    console.log(formattedDate);
    console.log(document.getElementById("transactionDate").value);
}
// When editing, set the date of the visible date box to the date in the database.
function loadUpdateTransaction () {
    fn();
    console.log("RUN LOAD");
    var hiddenBox = document.getElementById("transactionDate");
    var visibleBox = document.getElementById("date");
    console.log(hiddenBox.value);

    if (hiddenBox.value) {
        visibleBox.value = hiddenBox.value;
    }
}

window.onload = (event) => {
    let pageTitle = document.title.split('-')[0].trim();

    switch (pageTitle) {
        case 'Transaction Edit':
            loadUpdateTransaction();
            break;
    }
}

/*
SCRIPT FOR ALL TRANSCATIONS

transactions.forEach(function(transaction) {
                var dateParts = transaction.transactionDate.split("-");
                let tranDate = new Date(dateParts[0], dateParts[1] - 1);

                let monthKey = `${tranDate.getFullYear()}-${tranDate.getMonth()}`;

                if (!monthlyData[monthKey]) {
                    monthlyData[monthKey] = { spending: 0, deposit: 0 };
                }

                if (transaction.type === "Deposit") {
                    monthlyData[monthKey].deposit += transaction.amount;
                } else {
                    monthlyData[monthKey].spending += transaction.amount;
                }
            });
 */