export function getRandomNumber(min,max) {
return Math.round(Math.random()*(max-min)+min)
}

export function getRandomElement(array) {
const index = getRandomNumber(0,array.length-1)
    return array[index];
}