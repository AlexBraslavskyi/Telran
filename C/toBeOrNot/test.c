#include <stdio.h>
void printArray(int ar[], int length);
void sortAr(int ar[], int length);
int main()
{
	int array[] = {0, 31, 74, 25, 16, -8, 3, 5, 41, 27};
	int length = sizeof(array)/sizeof(int);
	printArray(array,length);
	printf("\n");
	sortAr(array,length);
	return 0;
}
void sortAr(int ar[], int length) {
	int i,maxInd,temp=0,length2;
	length2 = length;
		for (;(length-1)>0; length--){
			for(i=1,maxInd = 0;i<length;i++){
					if (ar[i] > ar[maxInd])
						maxInd = i;
}
			temp = ar[length-1];
			ar[length-1]=ar[maxInd];
			ar[maxInd]=temp;
}
		printArray(ar, length2);
	}
void printArray(int ar[], int length) {
	int i;
	for (i = 0; i < length; i++)
		printf("[%d]", ar[i]);
}
