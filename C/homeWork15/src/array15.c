#include<stdio.h>
void print3min (int ar[], int length);
void printArray(int ar[],int length);
void reverseInRange (int ar[], int iS,int iE);
void getArray (int ar[],int length);
int main(){
	int length;
	printf("Enter length of array\n");
		fflush (stdout);
		scanf ("%d",&length);
	int array[length];
	getArray(array,length);
	printf("Reverse in range (2-5) - ");
	reverseInRange (array, 2,5);
	printArray(array, length);
	printf("3 min of array - ");
	print3min (array, length);
	return 0;
}
void getArray (int ar[],int length){
	int i;
	printf("Enter numbers.");
for (i=0;i<length;i++){
			fflush (stdout);
			scanf ("%d",&ar[i]);
}
printArray(ar,length);
}
void reverseInRange (int ar[], int iS,int iE){
	int temp;
	for(;iS<=iE;iS++,iE--){
		temp = ar[iS];
		ar[iS] = ar[iE];
		ar[iE] = temp;
	}
}
void print3min (int ar[], int length){
	int i,min,temp,n=0,ar1[n];
	for (;n<3;n++){
	for (i=1,min = ar[0];i<=length;i++){
		if(ar[i]<min){
			temp = ar[i];
			ar[i]= min;
			min = temp;
		}
	}
	ar1[n]=min;
	}
	printArray(ar1,n);
}
void printArray(int ar[],int length){
	int i;
	for(i=0;i<length;i++){
		printf("[%d]",ar[i]);
}
	printf("\n");
}
