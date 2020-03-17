var trHeadElement = document.getElementById('thCars');
var tBodyElement = document.getElementById('tbodyCars');
var buttonModelElement =
    document.getElementById('button-model');
var inputModelElement = document.getElementById('input-model')
inputModelElement.addEventListener('change',
    function (event) {
    var modelName = event.target.value;
    if (modelName != '') {
        buttonModelElement.disabled = false;
    }
})
var flSortModel=false;
var flSortRegNumber=false;
var keys = [
    'reg_number', 'model'
];
var cars = [];
var tableCars = new Table(keys, trHeadElement, tBodyElement);
// var car1={reg_number: 123, model: 'Toyota'};
// var car2={reg_number: 124, model: 'Subaru'};
// tableCars.addRow(car1);


function getRandomNumber(min, max) {
    return min + Math.round(Math.random()*(max-min));
}

function insertCar(car) {
    var index=-1;
    if (flSortModel) {
       index = cars.findIndex(function(c) {
           return c.model >= car.model
       })
    } else if (flSortRegNumber) {
        index = cars.findIndex(function(c) {
            return c.reg_number >= car.reg_number
        })
    }
    if(index==-1) {
        cars.push(car);
    }
    else {
      cars.splice(index,0, car);
    }
    tableCars.clear();
    cars.forEach(function(c) {
        tableCars.addRow(c);
    })

}

// tableCars.addRow(car2);
function createRandomCar() {
    var reg_number = getRandomNumber(1000, 9999);
    var models=[
        'Zaporozhec', 'Mercedes', 'Subaru', 'Toyota', 'Suzuki'
    ];
    var ind = getRandomNumber(0, models.length-1);
    var model = models[ind];
    var car = {reg_number, model};
    insertCar(car);

}
function clearTable(){
    tableCars.clear();
}
function sortByRegNumber() {
    flSortModel=false;
    flSortRegNumber=true;
    cars.sort(function(c1,c2) {
        return c1.reg_number - c2.reg_number;
    })
    tableCars.clear();
    cars.forEach(function(c) {
        tableCars.addRow(c);
    })
}
function sortByModel() {
    flSortModel=true;
    flSortRegNumber=false;
    cars.sort(function(c1,c2) {
        if (c1.model === c2.model) {
            return c1.reg_number - c2.reg_number;
        }
        return c1.model <= c2.model ? -1 : 1;
    })
    tableCars.clear();
    cars.forEach(function(c) {
        tableCars.addRow(c);
    })
}
function filterByModel() {
    var modelName = inputModelElement.value;
    var carsModel = cars.filter(function(car){
        return car.model === modelName;
    })
    tableCars.clear();
    carsModel.forEach(function(car) {
        tableCars.addRow(car);
    })

}
