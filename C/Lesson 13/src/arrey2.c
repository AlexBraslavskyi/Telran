#include<stdio.h>
void printArrey (int ar[],int length);
void printArreyRev (int ar[], int length);
void printArreyInRange (int ar[],int iS,int iE);
int main() {
//                0 1 2 3 4
	int ar1[8] = {3,9,1,8,2};
	int i;
	//for(i = 0;i<8;i++)
	//printf("%d\n",ar1[i]);
	//printArrey (ar1,8);
	//printArreyRev (ar1, 8);
	printArreyInRange (ar1,1,5);
	return 0;

}
void printArreyInRange (int ar[],int iS,int iE){
	int i;
	for (i=iS;i<=iE;i++)
			printf("%d",ar[i]);

}


void printArrey (int ar[],int length){
	int i;
	for (i=0;i<length;i++)
		printf("%d",ar[i]);

printf("\n");
}
void printArreyRev (int ar[], int length){
	int i;
	for (i=length-1;i>=0;i--)
			printf("%d",ar[i]);

}
