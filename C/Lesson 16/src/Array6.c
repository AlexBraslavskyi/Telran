/*#include<stdio.h>
void margeArrayInOrder(int ar1[], int length1, int ar2[], int length2);
int searchMin (int ar[], int length);
int searchMax (int ar[], int length);
void printArray(int ar[], int length);
int main(){

	int ar1[]={9,6,1,0};
	int ar2[] = {8,4,5,2,7,3};
	margeArrayInOrder(ar1,4,ar2,6);

	return 0;
}

void margeArrayInOrder(int ar1[], int length1, int ar2[], int length2){
	int length3 = length1+length2;
	int ar3[length3];
	int iMax1 = searchMax(ar1,length1);
	int iMax2 = searchMax(ar2,length2);
	int max,i,iMin1,iMin2,min;
	if(ar1[iMax1] >= ar2[iMax2])
		max = ar1[iMax1];
	else
		max = ar2[iMax2];
	for (i=0;i<length3;i++){
		iMin1 = searchMin(ar1,length1);
		iMin2 = searchMin(ar2,length2);
		if(ar1[iMin1]<=ar2[iMin2]){
			min = ar1[iMin1];
			ar3[i] = min;
			ar1[iMin1]= max+1;
		}
			else{
				min = ar2[iMin2];
				ar3[i] = min;
				ar2[iMin2] = max+1;
		}
	}
	printArray(ar3,length3);
}
int searchMax (int ar[], int length){
	int max, maxInd,i;
	for(max = ar[0],maxInd = 0, i =1;i<length;i++){
		if(ar[i]>max){
					max = ar[i];
					maxInd = i;
				}
			}
			return maxInd;
}
int searchMin (int ar[], int length){
	int min, minInd, i;
	for(min =ar[0],minInd = 0,i = 1;i<length;i++){
		if(ar[i]<min){
			min = ar[i];
			minInd = i;
		}
	}
	return minInd;
}
void printArray(int ar[], int length) {
	int i;
	for (i = 0; i < length; i++)
		printf("[%d]", ar[i]);
}

*/
