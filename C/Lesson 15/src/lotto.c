/*#include<stdio.h>
#include<stdlib.h>
#include<time.h>
void loto (int min, int max, int attempts,int numbers);
int main(){


	int t = time(0);
	printf("t = %d\n",t);
	srand(t);
	int min = 1,max = 36;
	int number = min + rand()%(max-min+1);
	printf("%d\n",number);

	loto (1, 36,10000,6);
	return 0;

}
void loto (int min, int max, int attempts,int numbers){
	int ar[max+1];
	int i,n;
	for(i=0;i<=max;i++){
		ar[i]=0;
	}
	for(i = 0;i<attempts;i++){
		n = min+rand()%(max-min+1);
		ar[n]++;
	}
	for (i = 0;i<=max;i++){
		printf("%d -> %d times\n",i,ar[i]);

	}
	for(n=0;n<numbers;n++){
	i = searchMax(ar,max+1);
			printf("Max number %d times %d\n",i,ar[i]);
			ar[i]=-1;
}
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
*/
