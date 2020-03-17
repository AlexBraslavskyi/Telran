#include<stdio.h>
int arrayOddsSum(int ar[], int length);
int searchMax(int ar[], int length);
int searchMaxInd(int ar[], int length);
void printArray(int ar[], int length);
void margeArrays(int ar1[], int length1, int ar2[], int length2);
void sortAr(int ar[], int length);
void del (int ar[],int length, int i);


int main() {
	int array[] = { 12, 31, 74, -25, 16, 5, 3, -5, 41, 27 };
	printArray(array, 10);
	printf("\n");
	//int array2[] = { 3, 0, 7, 8, -5, 7, 4 };
	//int res = arrayOddsSum(array, 10);
	//printf("Sum odds = %d\n", res);
	//res = searchMaxInd(array, 10);
	//printf("Max index - %d max number - %d\n", res, array[res]);
	//margeArrays(array, 10, array2, 7);
	//printf("\n");
	printf("\n");
	printf("Delete\n");
	del(array,10,31);
	return 0;
}

 void del (int ar[],int length, int i){
	int ar2[length];
	int j,count = 0;
	for(j=0;j<length;j++){
		if(ar[j]!=i&&count == j){
			ar2[j]=ar[j];
		count ++;}
		else if (ar[j]==i||count<j){

			ar2[j]=ar[j+1];

		}
	}
		printArray(ar2, length-1);
		printf("\n");

 }
void margeArrays(int ar1[], int length1, int ar2[], int length2) {
	int length3 = length1 + length2;
	int ar3[length3];
	int i, j;
	for (i = 0, j = 0; i < length3; i++) {
		if (i < length1)
			ar3[i] = ar1[i];
		if (i > length1 - 1) {
			ar3[i] = ar2[j];
			j++;
		}
	}
	printArray(ar3, length3);
	printf("\n");
	sortAr(ar3, length3);
}
void printArray(int ar[], int length) {
	int i;
	for (i = 0; i < length; i++)
		printf("[%d]", ar[i]);
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
int searchMaxInd(int ar[], int length) {
	int i, indMax;
	for (i = 1, indMax = 0; i < length; i++) {
		if (ar[i] > ar[indMax]) {
			indMax = i;
		}
	}
	return indMax;
}
int arrayOddsSum(int ar[], int length) {
	int i, sum;
	for (i = 0, sum = 0; i < length; i++) {
		if (ar[i] % 2 == 1) {
			sum += ar[i];
		}
	}
	return sum;
}

