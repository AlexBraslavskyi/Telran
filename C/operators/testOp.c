#include <stdio.h>
void isEquals(int num1, int num2);
void isPositive(int number);
void isEvenOrOdd (int number);
int main(){
	isEquals(5,5);
	isPositive (-5);
	isEvenOrOdd (8);
	return 0;
}
void isPositive(int number){

	number>=0 ? printf("positive \n") : printf("negative \n");
}

void isEquals(int num1, int num2){

	num1 == num2 ? printf("Equals \n") : printf("Not equals \n");

}
void isEvenOrOdd (int number) {

	(number % 2) == 0 ? printf("Even \n") : printf("Odd");
}
