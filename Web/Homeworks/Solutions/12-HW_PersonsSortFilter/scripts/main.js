// variables and event listeners
var idElement = document.getElementById('id');
var nameElement = document.getElementById('name');
var birthYearElement = document.getElementById('birth-year');
var personsElement = document.getElementById('persons');
var oldestPersonsElement = document.getElementById('oldest-persons');
var persons = [];
var HIDDEN = 'hidden'
var minBirthYear = 3000;
var addButtonElement = document.getElementById('add-button');
idElement.addEventListener('change', function(event) {
    var id = +event.target.value;
    if (getIndex(id) >= 0) {
        idElement.value = '';
        alert('id isn’t unique');


    } else if (!(id >= 100 && id <= 999)) {
        alert('id has wrong value');
        idElement.value = '';
    }else if (birthYearElement.value != '') {
        addButtonElement.disabled = false;
    }

});
birthYearElement.addEventListener('change',
    function(event) {
    var birthYear = +event.target.value ;
    if (!(birthYear >= 1920 && birthYear <= 2020)) {
        alert('Birth Year value should be in the range [1920-2020]');
        birthYearElement.value = '';
    }else if (idElement.value != '') {
        addButtonElement.disabled = false;
    }
    })
var manuallyAddSection = document.getElementById('manually-add');
var showTableSection = document.getElementById('show-table');
var generationSection = document.getElementById('generation')
var trHeadElement = document.getElementById('trHead');
var tBodyElement = document.getElementById('persons-table');
var tablePersons = new Table(['id', 'name', 'birthYear'],
    trHeadElement, tBodyElement);
var amountGenerationElement = document.getElementById('amount-input')
var sortMode = 'Unsorted';
var selectElement =
    document.getElementById('select-element');
selectElement.addEventListener('change', showTableView);
var filterSectionElement = document.getElementById('filter');
var birthDateToElement = document.getElementById('birth-date-to');
var birthDateFromElement = document.getElementById('birth-date-from');
var personsFilter = [];
var trHeadFilterElement = document.getElementById('trHeadFilter');
var personsFilterElement = document.getElementById('persons-table-filter');
var tablePersonsFilter =
    new Table(['id', 'name', 'birthYear'], trHeadFilterElement,
        personsFilterElement);
var mainElement = document.querySelector('main');
amountGenerationElement.addEventListener('change',
    function(event) {
        var amount = +event.target.value;
        if (!(amount >= 1 && amount <= 10 )) {
            alert('wrong amount value');

        } else {
            generatePersons(amount);
        }
        amountGenerationElement.value = '';
    })
var labelFilterElement = document.getElementById('label-sorted-filter')
/**************************************************************/
//functions
function filterByBirthYear() {
    filterSectionElement.classList.remove(HIDDEN);
    mainElement.classList.add(HIDDEN);
    labelFilterElement.textContent = selectElement.value;
    filterProcessing();
}
function onBack() {
    filterSectionElement.classList.add(HIDDEN);
    mainElement.classList.remove(HIDDEN);
}
function createRandomPerson() {
    var names = [
        'Abraham', 'Yakov',  'Itshak', 'Yosef', 'Benyamin',
        'Dan', 'David', 'Asaf', 'Sara', 'Rivka', 'Rahel'
    ]
    var id = getRandomNumber(100, 999);
    if (getIndex(id) < 0) {
        var name = names[getRandomNumber(0, names.length -1)];
        var birthYear = getRandomNumber(1920, 2020);
        insertPerson({id: id, name: name, birthYear: birthYear});
    }



}
function getRandomNumber(min, max) {
    return min + Math.round(Math.random()*(max-min));
}
function generatePersons(amount) {
    for (var i = 0; i < amount; i++) {
        createRandomPerson();
    }
}
function addPersonToOldestList(listElement, person) {
    var liElement = document.createElement('li');
    liElement.textContent = JSON.stringify(person);
    listElement.appendChild(liElement);
}
function addPersonToAllList(person) {
    var liElement = document.createElement('li');
    createLiChildren(liElement, person);
    personsElement.appendChild(liElement);
}
function findMinBirthYear() {
    minBirthYear = persons.length == 0 ? 3000 : persons[0].birthYear;
    persons.forEach(function(p) {
        if (p.birthYear < minBirthYear) {
            minBirthYear = p.birthYear;
        }
    })
}
function getIndex(id) {
    return persons.findIndex(function (p) {
        return p.id == id;
    });
}
function createLiChildren(liElement, person) {
    var spanElement = document.createElement('span');
    spanElement.textContent = JSON.stringify(person);
    var buttonRemoveElement =
        document.createElement('button');
    buttonRemoveElement.textContent='Remove Person'
    liElement.appendChild(spanElement);
    liElement.appendChild(buttonRemoveElement);
    buttonRemoveElement.addEventListener('click', function(){
        var index = getIndex(person.id);
        if (index >= 0) {
            persons.splice(index, 1);
            liElement.remove();
            if (person.birthYear == minBirthYear) {
                findMinBirthYear();
                displayOldestPersons();
            }
        }
    })
}
function getFindIndexFunction(person) {
   switch (sortMode) {
       case 'Id': return function (p) {
           return person.id <= p.id;
       }
       case 'Name': return function(p) {
           return person.name <= p.name;
       }
       case 'Birth Year': return function(p) {
           return person.birthYear <= p.birthYear;
       }
       default: return function(p) {
           return false;
       }
   }
}
function insertPerson(person) {
    var findIndexFunction = getFindIndexFunction(person);
    var index = persons.findIndex(findIndexFunction);
    if (index == -1) {
        persons.push(person)
    } else {
        persons.splice(index, 0, person)
    }
    addPersonToAllList(person);
    if (person.birthYear <= minBirthYear) {
        minBirthYear = person.birthYear;
        displayOldestPersons();
    }
}
function addPerson() {
    var person = {
        id: +idElement.value,

        name: nameElement.value,
        birthYear: +birthYearElement.value
    }
    insertPerson(person);
    idElement.value = '';
    nameElement.value = '';
    birthYearElement.value = '';
    addButtonElement.disabled = true;

}
function removeChildren(element) {
    //element.innerHTML =''; non-standard option
    while (element.hasChildNodes()) {
        element.removeChild(element.lastChild)
    }

}
function displayOldestPersons () {
    removeChildren(oldestPersonsElement);
    persons.forEach(function (person) {
        if (person.birthYear === minBirthYear ) {
            addPersonToOldestList(oldestPersonsElement, person)
        }
    })
}
function showTableView() {
    manuallyAddSection.classList.add(HIDDEN);
    showTableSection.classList.remove(HIDDEN);
    generationSection.classList.add(HIDDEN);
    showTable();

}
function addPersonManuallyView() {
    manuallyAddSection.classList.remove(HIDDEN);
    showTableSection.classList.add(HIDDEN);
    generationSection.classList.add(HIDDEN);
}
function generatePersonsView() {
    manuallyAddSection.classList.add(HIDDEN);
    showTableSection.classList.add(HIDDEN);
    generationSection.classList.remove(HIDDEN);

}
function drawTable(table, data) {
    table.clear();
    data.forEach(function (person) {
        table.addRow(person);
    })
}
function getCompFunction() {
    switch(selectElement.value) {
        case 'Id': return function(p1, p2) {
            return p1.id - p2.id;
        };
        case 'Name': return function (p1, p2) {
            return p1.name <= p2.name ? -1 : 1;
        };
        case 'Birth Year': return function (p1, p2) {
            return p1.birthYear - p2.birthYear;
        };
        case 'Unsorted': return function() {
            return false;
        }

    }
}
function showTable() {
if(sortMode !== selectElement.value) {
    sortMode = selectElement.value;
    if (sortMode !== 'Unsorted') {
        var compFunction = getCompFunction();

        persons.sort(compFunction);
    }
}
    drawTable(tablePersons, persons);

}
function filterProcessing() {
    if (birthDateToElement.value !== '' && birthDateFromElement.value !== '') {
        if (+birthDateToElement.value < +birthDateFromElement) {
            alert ('birthDate "to" can not be less than birthDate "from"')
        } else {
            personsFilter = persons.filter ( function (p) {
                return p.birthYear >= birthDateFromElement.value &&
                    p.birthYear <= birthDateToElement.value;
            })
            personsFilter.sort(getCompFunction());
            drawTable(tablePersonsFilter, personsFilter)
        }
    }

}
/********************************************/
