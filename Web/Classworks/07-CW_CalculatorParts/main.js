var firstInput = document.getElementById('first-number');

var secondInput = document.getElementById('second-number');
var firstNumber = 0;
var selectElement = document.getElementById('operation');
var result = document.getElementById('result')
firstInput.addEventListener('change', function (event) {
    firstNumber = +event.target.value;

})
secondInput.addEventListener('change', function (event) {
   var res = firstNumber + +event.target.value;
   result.textContent = res;


})
selectElement.addEventListener('blur', function (event) {
    var value = event.target.value;
    var info = 'selected ';
    switch (value) {
        case 'multiply': info = info + '*'; break;
        case 'divide': info = info + '/'; break;
        case 'sum': info = info + '+'; break;
        case 'subtract': info = info + '-';


    }
    result.textContent = info + result.textContent;
 });
function reset() {
    firstInput.value='';
}
// <option value="multiply">Multiply</option>
//     <option value="divide">Divide</option>
//     <option value="sum">Add</option>
//     <option value="subtract">Subtract</option>
