#include<stdio.h>
void printArray(int ar[], int length);
int searchMax(int ar[], int length);
void reversArray (int ar[], int length);
int main(){
	int array[] = {0,45,78,25,36,45};
	int size = sizeof(array)/sizeof(int);
	int res = searchMax(array,size);
	printf("max ind %d\n",res);
	reversArray (array, size);
	printArray(array,size);
	return 0;

}

int searchMax(int ar[], int length){
	int max, maxInd, i;
	for(max = ar[0],maxInd = 0, i = 1; i<length; i ++){
		if(ar[i]>max){
			max = ar[i];
			maxInd = i;
		}
	}
	return maxInd;
}
void reversArray (int ar[], int length){ //swap
	int i,temp,j;
	for(i = 0,j=length-1;i<j;i++,j--){
		temp =ar[i];
		ar[i]=ar[j];
		ar[j]=temp;
	}

}
void printArray(int ar[], int length) {
	int i;
	for (i = 0; i < length; i++)
		printf("[%d]", ar[i]);
}
