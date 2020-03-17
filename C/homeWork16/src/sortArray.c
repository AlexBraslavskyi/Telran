#include<stdio.h>
void minToEnd(int ar[],int length);
void printArray(int ar[], int length);
int sortToMin(int ar[], int length);
void sortEvenToEnd(int ar[], int length);
int evenToEnd(int ar[],int length);
int main(){
	int array[] = {2,5,0,8,9,7,-13,11,25,18,23,11,15,17,8,10,0,16};
	int length = sizeof(array)/sizeof(int);
	printf("Start array\n");
	printArray(array,length);
	printf("\n");
	printf("Sort array from max to min\n");
	minToEnd(array,length);
	printArray(array,length);
	printf("\n");
	printf("Sort array - Even to end from min to max, Odds to start from min to max\n");
	sortEvenToEnd(array,length);
	printArray(array,length);
	printf("\n");

	return 0;
}
void sortEvenToEnd(int ar[],int length){
	int res;
	do{
		res = evenToEnd(ar,length);
	}
		while(res!=0);
}
int evenToEnd(int ar[], int length){
		int i,temp=0,flag = 0;
		for(i=0;i<length-1;i++){
			if(ar[i]%2==0&&ar[i+1]%2 == 0&& ar[i]>ar[i+1]){
				temp = ar[i+1];
				ar[i+1]=ar[i];
				ar[i]=temp;
				flag = 1;
			}
			else if(ar[i]%2==0&&ar[i+1]%2 != 0){
				temp = ar[i+1];
				ar[i+1]=ar[i];
				ar[i]=temp;
				flag = 1;
			}
			else if(ar[i]%2!=0 && ar[i+1]!=0&&ar[i]>ar[i+1]){
				temp = ar[i+1];
				ar[i+1]=ar[i];
				ar[i]=temp;
				flag = 1;
			}
		}
		return flag;
}
void minToEnd(int ar[],int length){
	int res;
	do{
		res = sortToMin(ar,length);
	}
		while(res!=0);
}

int sortToMin(int ar[], int length){
	int i,flag=0,temp;
	for(i=0;i<length-1;i++){
		if(ar[i+1]>ar[i]){
			temp = ar[i];
			ar[i]= ar[i+1];
			ar[i+1]=temp;
			flag = 1;
		}
	}
	return flag;
}
void printArray(int ar[], int length) {
	int i;
	for (i = 0; i < length; i++)
		printf("[%d]", ar[i]);
}
