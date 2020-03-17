/*#include<stdio.h>
void printNumberReverse (int number);
int reverseNumber (int number);
int main (){
	printNumberReverse (34567);
	int res = reverseNumber (-34567);
	printf("res = %d",res);
	return 0;
}

void printNumberReverse (int number){

	if (number<0)
		number =-number;
	int rem = 0;
	do{
		rem = number %10;
		printf("%d",rem);
		number = number/10;
	}
	while (number!=0);
	printf("\n");
}
int reverseNumber (int number){
	if (number<0)
			number =-number;
	int rev = 0,rem = 0;
	do{
		rem = number%10;
		rev = (rev*10)+rem;
		number = number/10;
	}while(number!=0);
return rev;
}
*/
