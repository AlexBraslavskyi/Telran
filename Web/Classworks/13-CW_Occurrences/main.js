var strings = [
    'lmn','ab', 'abc', 'abc','lmn','lmn','a'
];
var occurrences = {};
var processFunction = function(str) {
    if (occurrences[str]) {
        occurrences[str] = occurrences[str] + 1;
    } else {
        occurrences[str] = 1;
    }
}
strings.forEach(processFunction);
var occurKeys = Object.keys(occurrences);
occurKeys.forEach(function (key) {
    console.log(key + ':' + occurrences[key]);
})
