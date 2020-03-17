#include <stdio.h>;
void printStars (int stars);
int numberSum (int number);
int digitsCount (int number);

int main (){

	int res = digitsCount(555);
	printf("%d\n",res);
	printStars(10);
	res = numberSum (10);
	printf("%d\n",res);
	return 0;
}
int digitsCount (int number){
	int count = 0;
	do {
		number = number/10;
		count ++;
	}while (number !=0);
	return count;
}

int numberSum (int number){
	int sum = 0;
	while (number>0){
		sum = sum+number;
		number--;
	}

	return sum;
}
void printStars (int stars){
	int count = 0;
	while (stars>0){
	printf("*");
	count++;
	if (count ==3){
		printf("\n");
		count =0;
	}
	stars --;
	}
}
