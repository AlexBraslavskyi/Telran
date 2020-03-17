#include<stdio.h>


int main() {

	int i,j,size = 10;
for (j=0;j<size;j++){
	for(i=0;i<size;i++){
		if(i==0||i==size-1||j==0||j==size-1||i==j||(i+j)==size-1)
	printf("*");
		else
			printf(" ");
	}
	printf("\n");
}
	return 0;

}
