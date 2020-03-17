/*#include<stdio.h>
int arraySum(int ar[], int length);
int arraySumRen(int ar[], int iS, int iE);
int arreyEvensSum (int ar[],int length);
int serchInt (int ar[], int length,int value);
int main (){
	int ar1[] = {1,3,5,28,56,5,23,37,78};
	int res=arraySum(ar1,8);
	res = arraySumRen(ar1, 0, 2);
	res = arreyEvensSum (ar1,5);
	res = serchInt (ar1,10,5);
printf("serch %d  times %d\n",value,res);
	return 0;

}

int serchInt (int ar[],int length, int value){
	int i=0,count =0;
	for (i =0;i<length;i++){
		if (ar[i] == value)
		count ++;
	}
		return count;
}


int arraySum(int ar[], int length){

int i,sum;
	for (i =0, sum = 0;i<length;i++)
		sum +=ar[i];

		return sum;
}
int arraySumRen(int ar[], int iS, int iE){

int i,sum;
	for (i =iS, sum = 0;i<=iE;i++)
		sum +=ar[i];

		return sum;
}
int arreyEvensSum (int ar[],int length){

	int i;
	int sum = 0;
	for (i=0;i<length;i++){
		if (ar[i]%2 == 0)
			sum +=ar[i];
	}
	return sum;
}
*/
