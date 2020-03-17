#include <stdio.h>
int f1(int a,int b,int c);
int f2 (int d);


int main(){
	 int res = f1(1,2,3);
	printf("res: %d \n",res);

	return 0;
}

int f1(int a,int b,int c){
	int sum = a+b+c;
	int res = f2(sum);
	return res*3;
}

int f2 (int d){
	return d*4;


}
