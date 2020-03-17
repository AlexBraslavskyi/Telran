var idElement = document.getElementById('id');
var nameElement = document.getElementById('name');
var birthYearElement = document.getElementById('birth-year');
var personsElement = document.getElementById('persons');
var oldestPersonsElement = document.getElementById('oldest-persons');
var persons = [];
var minBirthYear = 3000;
function addPersonToList(listElement, person) {
    var liElement = document.createElement('li');
    liElement.textContent = JSON.stringify(person);
    listElement.appendChild(liElement);
}

function addPerson() {
    var person = {
        id: idElement.value,
        name: nameElement.value,
        birthYear: +birthYearElement.value
    }
    persons.push(person);
    addPersonToList(personsElement, person);
    if (person.birthYear < minBirthYear) {
        minBirthYear = person.birthYear; //minBirthYear should be always number
    }
    idElement.value = '';
    nameElement.value = '';
    birthYearElement.value = '';

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
            addPersonToList(oldestPersonsElement, person)
        }
    })
}

