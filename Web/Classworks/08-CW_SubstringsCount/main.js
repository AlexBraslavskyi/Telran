var stringElement = document.getElementById('string');
var stringValue='';
var substringElement = document.getElementById('substring');
var substringValue='';
var resultElement = document.getElementById('result');
function substringsCount() {
    var ind=-1;
    var count=-1;
    var str = stringValue.substr(0);
   do {
      var str = str.substr(ind+1) ;
      ind = str.indexOf(substringValue);
      count++;
   }while(ind >= 0)
   resultElement.textContent = count.toString();
}
stringElement.addEventListener('change',
    function (event) {
        stringValue = event.target.value;
    });
substringElement.addEventListener('change',
    function (event) {
        substringValue = event.target.value;
    });
