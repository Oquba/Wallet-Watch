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
    console.log(properDate.getFullYear() + '-' + properDate.getMonth() + '-' + properDate.getDate())

    var formattedDate = (properDate.getMonth() + 1) +
        '/' + (parseInt(properDate.getDate()) + 1) +
        '/' + properDate.getFullYear();
    document.getElementById("transactionDate").value = formattedDate;
    console.log(formattedDate);
    console.log(properDate);
}
// When editing, set the date of the visible date box to the date in the database.
window.onload = (event) => {
    fn();
    var hiddenBox = document.getElementById("transactionDate");
    var visibleBox = document.getElementById("date");
    console.log(hiddenBox.value);

    if (hiddenBox.value) {
        visibleBox.value = hiddenBox.value;
    }
}