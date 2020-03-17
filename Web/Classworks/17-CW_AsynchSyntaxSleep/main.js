function sleep(timeout) {
    return new Promise(function( resolve) {
        setTimeout(function() {
            resolve();
        }, timeout);
    })
}
function first() {
    console.log('first action');
    return 1;
}
function second(number) {
    console.log('second action',number);
    return number + 1;
}
function third(number) {
    console.log('third action',number);

}
sleep(5000).then(function() {
    first();
}).then(function(number) {
    return second(number)
}).then(function(number) {
    third(number);
});



