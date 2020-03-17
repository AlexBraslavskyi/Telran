var stringElement = document.getElementById('string');
var substringElement = document.getElementById('substring');
var resultElement = document.querySelector('.result-text');
var stringValue = '';
var substringValue = '';

stringElement.addEventListener('change',
    function(event) {
    stringValue = event.target.value;
    })
substringElement.addEventListener('change',
    function(event) {
        substringValue = event.target.value;
    })

function getResult () {
    var result = stringValue;

    while(result.indexOf(substringValue) >= 0) {
        result = result.replace(substringValue, '');
    }

    resultElement.textContent = result;
}
