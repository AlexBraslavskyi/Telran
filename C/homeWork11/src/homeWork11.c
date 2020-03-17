#include<stdio.h>
int XpowerY(int x, int y);
int digitsCount(int number);
int numberSum(int number);
void printStars(int stars);
int findNumber(int number, int sub);

int main() {
	printStars(10);
	int res = XpowerY(2, 4);
	printf("Power = %d\n", res);
	res = digitsCount(-34567);
	printf("Digits in number = %d\n", res);
	res = numberSum(-10);
	printf("Sum of digits = %d\n", res);
	res = findNumber(562356756, -56);
	printf("Find number - %d times\n", res);
	return 0;
}
int findNumber(int number, int sub) {
	if (number < 0)
		number = -number;
	if (sub < 0)
		sub = -sub;
	int count = 0;
	int dCount = digitsCount(sub);
	int div = XpowerY(10, dCount);
	int rem = 0;
	do {
		rem = number % div;
		if (rem == sub)
			count++;
		number = number / 10;

	} while (number != 0);
	return count;

}
int XpowerY(int x, int y) {
	int res;
	if (x >= 0 && y == 0)
		res = 1;
	else if (x < 0 || y < 0)
		printf("Task without negative numbers\n");
	else
		for (res = 1; y > 0; y--)
			res = res * x;
	return res;
}

int digitsCount(int number) {
	int count;
	if (number == 0) {
		count = 1;
		return count;
	} else if (number < 0)
		number = -number;
	for (count = 0; number != 0; count++)
		number = number / 10;
	return count;
}
int numberSum(int number) {
	int sum;
	if (number < 0)
		number = -number;
	for (sum = 0; number > 0; number--)
		sum = sum + number;
	return sum;
}
void printStars(int stars) {
	for (; stars > 0; stars--)
		printf("*");
	printf("\n");
}

