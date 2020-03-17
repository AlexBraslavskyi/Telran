#include <stdio.h>
void printStars(int stars, int inRow);
int factorial(int number);
int xPowerY(int x, int y);
int main() {
	printStars(12, 4);
	int res = factorial(3);
	printf("Factorial = %d\n", res);
	int result = xPowerY(2, 6);
	printf("Power = %d\n", result);
	return 0;
}

void printStars(int stars, int inRow) {
	int count = inRow;
	if (stars <= 0 || inRow <= 0)
		printf("Error: number must be >0\n");
	else {
		while (stars > 0) {
			printf("*");
			inRow--;
			stars--;
			if (inRow == 0) {
				printf("\n");
				inRow = count;
			}
		}
	}
}
int factorial(int number) {
	int fact = 1;
	if (number == 0)
		fact = 1;
	else {
		while (number >1) {
			fact = fact * number;
			number--;
		}
	}
	return fact;
}
/*long long int factorial (long long int number){
	if (number<0){
	printf ("Error: please use numbers  from 0");
	return -1;
	}
	long long int res = 1;
	while (number>1){
		res=res*number;
		number--;
	}
	return res;
}*/
int xPowerY(int x, int y) {
	int power = x;
	if (x >= 0 && y == 0)
		power = 1;

	else if (x < 0 || y < 0)
		printf("Task without negative numbers\n");

	else {
		while (y >= 2) {
			power = power * x;
			y--;
		}
	}
	return power;
}
