/*#include <stdio.h>
void testBase();
void testWithInput();
void testBrake(int maxMoney);
int inputNumber(int min, int max);
int main() {
	//testWithInput();
	//testBrake(1000);

	int a = inputNumber(1,10);
	printf("res - %d",a);
	return 0;
}

int inputNumber(int min, int max){
	int res = 0;
	do{
		printf("Input number:\n");
		fflush(stdout);
		scanf("%d",&res);
	//} while(!(res>min && res<max));
	} while(res<min || res>max);

	// if (res>min && res<max){}
	return res;
}

void testBrake(int maxMoney){
	int count = 0;
	int iteration = 0;
	while(count<=maxMoney){
		continue;
		int a = 0;
		printf("Price - %d. Input next price:\n",count);
		fflush(stdout);
		scanf("%d",&a);
		if(a<5) continue;

		iteration++;
		printf("iteration - %d\n",iteration);
		if(iteration==4){
			break;
		}
		count=count+a;
	}
	printf("%d",count);
}

void testWithInput(){
	int var = 0;
	while(var>=0){
		printf("Success input - %d\n",var);

		fflush(stdout);
		scanf("%d",&var);
	}
	printf("End of work");
}

void testBase() {
	printf("Hello!\n");
	int counter = 0;
	//       true
	//if(counter<10){
	while (counter < 10) {
		printf("%d\n", counter);
		counter++;
	}
}
*/
