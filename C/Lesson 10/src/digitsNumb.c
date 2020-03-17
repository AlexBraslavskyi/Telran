#include<stdio.h>
int XpowerY (int x, int y);
int digitsCount (int number);
int removeFirstDigit (int number);
int main(){

	int res = removeFirstDigit (3456);
	printf("result = %d\n",res);
	return 0;
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

int removeFirstDigit (int number){
	int dCount = digitsCount(number);
	int div = XpowerY(10,dCount-1);

	return number % div;
}
