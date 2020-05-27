

export default class TransformNumbers {
    constructor(numbers) {
        this.numbers = numbers;
    }

    getNumbers() {
        const rows = this.numbers.length;
        const columns = this.numbers[0].length;
        for (let i = 0; i < rows; i++) {
            for (let j = 0; j < columns; j++) {
                this.numbers[i][j] = Math.round(Math.random());
                if (this.numbers[i][j] == 1) {
                }
            }
        }
        return this.numbers
    }
    // checkNumbers(){
    //     this.numbers.forEach(function (i,j) {
    //         if(i==1){
    //             if()
    //         }
    //     })
    // }
}
