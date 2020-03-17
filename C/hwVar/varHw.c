#include <stdio.h>

int f1(int m, int k, int d);
int f2(int i);

int main() {

	int a, b, c, res;
	printf("Please enter first number:\n");
	fflush(stdout);
	scanf("%d", &a);
	printf("Please enter second number:\n");
	fflush(stdout);
	scanf("%d", &b);
	printf("Please enter third number:\n");
	fflush(stdout);
	scanf("%d", &c);

	res = f1(a,b,c);
	printf ("Result = %d\n",res);


	int temp =0;  //stop console
	fflush(stdout);
	scanf("%d", &temp);
	return 0;

}

int f1(int m, int k, int d) {
	return (f2(m+k+d)*3);
}

int f2(int i) {
	return i*4;
}
