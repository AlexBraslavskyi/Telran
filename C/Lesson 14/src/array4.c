#include<stdio.h>
int searchMin(int ar[], int length);
void printArray (int ar[], int length);
void mergeArrays (int ar1[], int length1,int ar2[], int length2);
int main(){
	int ar1[] = {9,2,-3,4,6};
	int ar2[] = {5,3,7,1};
	int res = searchMin (ar1,10);
	printf("search  min %d\n",res);
 mergeArrays (ar1,5,ar2,4);

	return 0;

}
void mergeArrays (int ar1[], int length1,int ar2[], int length2){

	int length3 = length1+length2;
	int ar3[length3];
	int i;
	for(i=0;i<length1;i++){
	ar3[i]=ar1[i];
	}
	int j;
	for (j=0;j<length2;j++,i++){
		ar3[i]=ar2[j];
	}
	printArray(ar3,length3);
}
void printArray (int ar[], int length){
	int i;
	for (i=0;i<length;i++)
		printf("%d",ar[i]);

}
int searchMin(int ar[], int length){
	int i,min,imin = 0;
	for(i=0,min = ar[i];i<length;i++){
		if(ar[i] <min){
			min=ar[i];
			imin = i;
		}
	}
	return min;
}
