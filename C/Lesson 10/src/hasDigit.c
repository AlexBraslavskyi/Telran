/*#include<stdio.h>
int hasDigit (int number, int digit);
int evenDigitsCount(int number);
int digitsSum (int number);
int main(){
	int res = digitsSum (1234);
	// int res = evenDigitsCount(7236485);
	// int res = hasDigit (35554567,-5);
	if (res == 0)
	printf("Not found\n");
	else
		printf("Found %d\n",res);
	return 0;
}
/*
int evenDigitsCount(int number){

	int count = 0, rem = 0;
	do{
		rem = number %10;
		if(rem %2 == 0)
			count++;
		number = number/10;
	}while (number!=0);

	return count;
}

int digitsSum (int number){
	if (number<0)
			number=-number;
	int sum = 0, rem = 0;
		do{
			rem = number %10;
			sum = sum+rem;
			number = number/10;
		}while (number!=0);

		return sum;

}

int evenDigitsCount(int number){

	int count = 0;
	do{
		if(number %2 == 0)
			count++;
		number = number/10;
	}while (number!=0);

	return count;
}


int hasDigit (int number, int digit){
	if (number<0)
		number=-number;
	if (digit<0)
		digit =-digit;
	int rem = 0;
	int count =0;
	do{
		rem = number %10;
		if (rem == digit)
			count++;
		number = number/10;
	}
	while (number !=0);
	return count;
}

*/
