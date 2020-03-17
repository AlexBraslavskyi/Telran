var idElement = document.getElementById('id');
var nameElement = document.getElementById('name');
var birthYearElement = document.getElementById('birth-year');
var personsElement = document.getElementById('persons');
var oldestPersonsElement = document.getElementById('oldest-persons');
var persons = [];
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

function addPerson() {
    var person = {
        id: +idElement.value,
        name: nameElement.value,
        birthYear: +birthYearElement.value
    }
    persons.push(person);
    addPersonToAllList(person);
    if (person.birthYear <= minBirthYear) {
        minBirthYear = person.birthYear;
        displayOldestPersons();
    }
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

