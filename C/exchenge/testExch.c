#include <stdio.h>
float exchange(float money, float rate, float comm);

int main() {
	float money = 0;
	float rate = 0;
	float comm = 0;

	printf("Please enter amount ...\n");
	fflush(stdout); //clean buffer
	scanf("%f", &money);
	printf("Please enter rate ...\n");
	fflush(stdout); //clean buffer
	scanf("%f", &rate);
	printf("Please enter commission ...\n");
	fflush(stdout); //clean buffer
	scanf("%f", &comm);  //& write in comm

	float total = exchange(money, rate, comm);
	printf("total : %.2f NIS\n", total);

	return 0;
}
float exchange(float money, float rate, float comm) {
	float result = money * rate;
	float commMoney = (result * comm) / 100;
	return result - commMoney;

}
