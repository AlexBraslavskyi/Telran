var isbnElement = document.getElementById('isbn');
var titleElement = document.getElementById('title');
var pagesElement = document.getElementById('pages');
var booksElement = document.querySelector('.books-list');
var resultElement = document.getElementById('result-output');
var books = [];


function addBookToList(book) {
    var liElement = document.createElement('li');
    liElement.textContent = JSON.stringify(book);
    booksElement.appendChild(liElement)
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
}
function getMaxPages() {
    var maxPages = 0;
    books.forEach(function (book) {
        if (book.pages > maxPages) {
            maxPages = book.pages;
        }
    })
    resultElement.textContent = '' + maxPages;
}
