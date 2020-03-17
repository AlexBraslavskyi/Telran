var isbnElement = document.getElementById('isbn');
var titleElement = document.getElementById('title');
var pagesElement = document.getElementById('pages');
var booksElement = document.querySelector('.books-list');
var resultElement = document.getElementById('result-output');
var books = [];
var addButtonElement = document.getElementById('add-button');
pagesElement.addEventListener('change',
    function(event) {
    var pages = +event.target.value;
    var invalid = !(pages > 0);
    addButtonElement.disabled = invalid;
    })
function addBookToList(book) {
    // creating new elements
    var liElement = document.createElement('li');
    liElement.setAttribute('id', book.isbn);
    var removeButtonElement = document
        .createElement('button');
    var spanElement = document.createElement('span');
    booksElement.appendChild(liElement);
    liElement.appendChild(spanElement);
    liElement.appendChild(removeButtonElement);
    spanElement.textContent = JSON.stringify(book);
    removeButtonElement.textContent = 'Remove Book';
    removeButtonElement.addEventListener('click',
        function() {
        //document.getElementById(book.isbn).remove();
            var index = books.findIndex(function(b){
                return b.isbn === book.isbn;
            })
            if (index >= 0) {
                books.splice(index,1);
                var removedElement =
                    document.getElementById(book.isbn);
                removedElement.remove();
            }

        })
}

function addBook() {
    var book = {
        isbn: isbnElement.value,
        title: titleElement.value,
        pages: pagesElement.value
    }
    books.push(book);
    addBookToList(book);
    isbnElement.value = '';
    titleElement.value = '';
    pagesElement.value = '';
    addButtonElement.disabled = true;
}
function getMaxPages() {
    var maxPages = 0;
    books.forEach(function (book) {
        if (+book.pages > maxPages) {
            maxPages = book.pages;
        }
    })
    resultElement.textContent = '' + maxPages;
}
