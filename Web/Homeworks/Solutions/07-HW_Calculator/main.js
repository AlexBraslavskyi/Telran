var firstNumberElement = document.getElementById('first-number');
var secondNumberElement = document.getElementById('second-number');
var resultElement = document.querySelector('.result-text');
var operationElement = document.getElementById('operation');
var firstNumber=0;
var secondNumber=0;
var operation='';
firstNumberElement.addEventListener('change',
    function(event) {
    firstNumber = +event.target.value;
    })
secondNumberElement.addEventListener('change',
    function(event) {
        secondNumber = +event.target.value;
    })
operationElement.addEventListener('blur',
    function(event) {
        operation = event.target.value;
    })
var info;
function compute() {
    switch (operation) {
        case 'multiply': info =
            '' + firstNumber + ' * ' + secondNumber + ' = '+
            (firstNumber * secondNumber);break;
        case 'divide': resultElement.textContent =
            '' + firstNumber + ' / ' + secondNumber + ' = '+
            (firstNumber / secondNumber);break;
        case 'add': resultElement.textContent =
            '' + firstNumber + ' + ' + secondNumber + ' = '+
            (firstNumber + secondNumber);break;
        case 'subtract': resultElement.textContent =
            '' + firstNumber + ' - ' + secondNumber + ' = '+
            (firstNumber - secondNumber);

    }
    firstNumberElement.value = '';
    secondNumberElement.value='';
}
