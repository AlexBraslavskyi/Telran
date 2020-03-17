/*#include<stdio.h>
long long int factorial (long long int number);
int XpowerY (int x, int y);
int main(){
	long long int res = factorial (15);
	printf("Fact = %lld\n",res);
	int result = XpowerY (2,4);
	printf("Power = %d\n",result);
	return 0;
}

long long int factorial (long long int number){
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
}
	int XpowerY (int x, int y){
	int res = 1;
	while(y>0){
		res = res*x;
		y--;
	}
	return res;
}
*/
