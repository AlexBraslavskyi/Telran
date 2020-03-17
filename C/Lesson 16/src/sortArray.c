#include<stdio.h>
int maxToEnd(int ar[], int length);
void printArray(int ar[], int length);
void sortBabble (int ar[], int length);
int main(){

int ar[] = {8,2,9,6,7,5,2,7};
sortBabble(ar,8);
printArray(ar,8);
	return 0;
}
void sortBabble (int ar[], int length){
int res;
	do{
	res = maxToEnd(ar,length);
}
	while(res !=0);
}
int maxToEnd(int ar[], int length){
	int i,temp,flag = 0;
	for(i=0;i<length-1;i++){
		if(ar[i]>ar[i+1]){
			temp = ar[i];
			ar[i] = ar[i+1];
			ar[i+1] = temp;
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
