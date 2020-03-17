// variables
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
/*********************************************************/
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

function insertPerson(person) {
    persons.push(person);
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
function showTable() {

    tablePersons.clear();
    persons.forEach(function(person) {
        tablePersons.addRow(person);
    })

}
