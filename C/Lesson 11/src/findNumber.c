/*#include<stdio.h>
int digitsCount (int number);
int XpowerY (int x, int y);
int findNumber (int number, int sub);

int main (){
	int res = findNumber (56478,56);
	printf("res = %d",res);

	return 0;
}
	int findNumber (int number, int sub){
		if(number <0)
			number =-number;
		if(sub<0)
			sub =-sub;
		int dCount = digitsCount (sub);
		int div = XpowerY(10,dCount);
		int rem = 0;
		do{
			rem = number%div;
			if (rem == sub)
				return 1;
			number = number/10;

		}while(number!=0);
		return 0;

	}
	int digitsCount (int number){
		int count = 0;
		do{
			number = number/10;
			count++;
		}while(number!=0);
		return count;
	}

	int XpowerY (int x, int y){
		int res = 1;
		while (y>0){
		res = res*x;
		y--;
		}
		return res;
	}
*/
