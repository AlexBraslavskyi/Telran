var trHeadElement = document.getElementById('thCars');
var tBodyElement = document.getElementById('tbodyCars');
var keys = [
    'reg_number', 'model'
];
var tableCars = new Table(keys, trHeadElement, tBodyElement);
// var car1={reg_number: 123, model: 'Toyota'};
// var car2={reg_number: 124, model: 'Subaru'};
// tableCars.addRow(car1);


function getRandomNumber(min, max) {
    return min + Math.round(Math.random()*(max-min));
}

// tableCars.addRow(car2);
function createRandomCar() {
    var rNumber = getRandomNumber(1000, 9999);
    var models=[
        'Zaporozhec', 'Mercedes', 'Subaru', 'Toyota', 'Suzuki'
    ];
    var ind = getRandomNumber(0, models.length-1);
    tableCars.addRow({reg_number: rNumber, model: models[ind] })
}
function clearTable(){
    tableCars.clear();
}
