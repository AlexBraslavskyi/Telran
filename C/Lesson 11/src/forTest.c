#include<stdio.h>

int main (){
	int a = 5;
	while (a>0){
		printf("a=%d\n",a);
		a--;
	}
	printf("End of while\n");

	for (a = 5;a>0;a--)
		printf("a=%d\n",a);
	printf("End of for loop\n");
	return 0;
}


