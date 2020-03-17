var stringElement = document.getElementById('string');
var firstIndexElement = document.getElementById('first-index');
var resultElement = document.querySelector('.result-text');
var lengthElement = document.getElementById('length');
var stringValue = '';
var firstIndexValue = 0;
var lengthValue = 0
stringElement.addEventListener('change',
    function(event) {
    stringValue = event.target.value;
    })
firstIndexElement.addEventListener('change',
    function(event) {
        firstIndexValue = event.target.value;
    })
lengthElement.addEventListener('change',
    function(event) {
        lengthValue = event.target.value;
    })
function getSubstring () {
    const result = stringValue.substr(firstIndexValue, lengthValue);

    resultElement.textContent = result;
}
