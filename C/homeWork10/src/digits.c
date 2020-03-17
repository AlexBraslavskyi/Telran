/*#include <stdio.h>
int oddDigitsCount (int number);
int evenDigitsSum (int number);
void printNumberReverse(int number);
int reverseNumber(int number);
int XpowerY (int x, int y);
int digitsCount (int number);

int main(){

	int res = oddDigitsCount (34567);
	printf ("Odd digits : %d\n", res);
	res = evenDigitsSum (34567);
	printf ("Sum of even digits : %d\n", res);
	printNumberReverse(34567);
	res = reverseNumber(34567);
	printf ("Reverse number : %d\n", res);
	return 0;
}

int oddDigitsCount (int number){
	if (number<0)
		number =-number;
	int count = 0;
	do{
	if (number %2 !=0)
		count ++;
	number = number/10;
	}while (number>0);
	return count;
}
int evenDigitsSum (int number){
	int sum = 0;
	if (number<0)
		number =-number;
		do{
			if(number %2 == 0)
		sum = sum + number % 10;
		number = number/10;
		}while (number!=0);
		return sum;

}
void printNumberReverse(int number){
	int newNumber = 0;
	do{
		newNumber = number % 10;
	printf("%d\n",newNumber);
	number = number/10;
	}while (number!=0);
}

int reverseNumber(int number){
	int newNumber = 0;
	int newDigits = 0;
	if (number<0)
			number =-number;
	do{
		newNumber = number % 10;
		int dCount = digitsCount(number);
		int div = XpowerY(10,dCount-1);
		newDigits = newDigits + newNumber*div;
			number = number/10;
		}while (number!=0);
	return newDigits;

}
int XpowerY (int x, int y){
	int res = 1;
	while(y>0){
		res = res*x;
		y--;
	}
	return res;
}
int digitsCount (int number){
	int count = 0;
	do {
		number = number/10;
		count ++;
	}while (number !=0);
	return count;
}
*/
