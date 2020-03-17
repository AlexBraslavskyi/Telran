var stringElement = document.getElementById('string');
var substringElement = document.getElementById('substring');
var resultElement = document.querySelector('.result-text');
var stringValue='';
var substringValue='';
stringElement.addEventListener('change',
    function(event) {
    stringValue = event.target.value;
    })
substringElement.addEventListener('change',
    function(event) {
        substringValue = event.target.value;
    })
function getIndexOf () {
    const result = stringValue.indexOf(substringValue);
    if (result === -1) {
        resultElement.textContent = "substring isn't included"
    } else {
        resultElement.textContent = '' + result;
    }
}
