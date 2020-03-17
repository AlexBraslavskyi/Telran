#include<stdio.h>
void getArray ();
void printArray(int ar[], int length);
int main() {
	int length;
	printf("Enter length of array\n");
		fflush (stdout);
		scanf ("%d",&length);
int array[length];
getArray(array,length);

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
	void printArray(int ar[], int length) {
		int i;
		for (i = 0; i < length; i++)
			printf("[%d]", ar[i]);
	}
