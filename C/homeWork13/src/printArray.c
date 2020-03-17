/*#include<stdio.h>
void printArrayRevRange(int ar1[],int iS,int iE);
void printEvens (int ar2[],int length);
void printOddsRev (int ar3[],int length);
int sumArray (int ar4[],int length);
int main() {
int ar[] = {9,15,47,28,56,14,23,37,78};

printf("Reverse range\n");
printArrayRevRange (ar,3,6);
printf("\nEvens\n");
printEvens (ar,9);
printf("\nOdds reverse\n");
printOddsRev (ar,9);
int res = sumArray (ar,9);
printf("\nSum of array = %d\n",res);

	return 0;

}


void printArrayRevRange(int ar1[],int iS,int iE){

	int i;
	for (i = iE;i>=iS;i--)
		printf("%d ",ar1[i]);
}

void printEvens (int ar2[],int length){
	int i;
	for (i=0;i<=length-1;i++)
		if (ar2[i]%2 == 0)
		printf("%d ",ar2[i]);
}
void printOddsRev (int ar3[],int length){
	int i;
	for (i=length-1;i>=0;i--)
		if (ar3[i]%2 == 1)
		printf("%d ",ar3[i]);
}
int sumArray (int ar4[],int length){
	int i,sum = 0;
		for (i=0;i<=length-1;i++)
			sum = sum +ar4[i];
				return sum;
}
*/
