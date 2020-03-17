
/*
class Deferred {
    constructor () {
        this.allFunc = [];
        this.lastResult=0;
    }

    then (newFunc) {
        this.allFunc.push(newFunc);
    }

    resolve (arg) {
        this.lastResult = arg;
        for (let i = 0; i < this.allFunc.length; i++) {
            const item = this.allFunc[i];
            const res = item(this.lastResult);
            if (res instanceof item) {
                const lastFunc = this.allFunc.slice(i + 1);
                res.allFunc = lastFunc;
                break;
            } else {
                this.lastResult = res;
            }
        }
    }
}
var d = new Deferred();
d.then(function(res){ console.log("1 ", res); return "a"; });
d.then(function(res){ console.log("2 ", res); return "b"; });
d.then(function(res){ console.log("3 ", res); return "c"; });
d.resolve("hello");
*/

function isAnagram(word, anagram) {
    const wordChar = [];
    wordChar.push(...word);
    let arrLowerWord = wordChar.map(item => item.toLowerCase());
    arrLowerWord.sort();
    const anagramChar = [];
    anagramChar.push(...anagram);
    let arrLowerAnagram = anagramChar.map(item => item.toLowerCase());
    arrLowerAnagram.sort();
    let equals = true;
    for (let i = 0; i < arrLowerAnagram.length; i++) {
        if (arrLowerWord[i] != arrLowerAnagram[i] || arrLowerAnagram.length > arrLowerWord.length) {
            equals = false;
            return equals;
        }
    }
        return equals;
}
  let res =  isAnagram("Sasha", "AsaHs");

console.log(res);