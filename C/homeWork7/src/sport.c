#include <stdio.h>
int medicalTest(int age, int time, int weight, int puls, int pressure);
int getInput();

int main() {

	printf("Please enter \"Age\"\n");
	int age = getInput();
	printf("Please enter \"Time\"\n");
	int time = getInput();
	printf("Please enter \"Weight\"\n");
	int weight = getInput();
	printf("Please enter \"Puls\"\n");
	int puls = getInput();
	printf("Please enter \"Pressure\"\n");
	int pressure = getInput();
	int treining = medicalTest(age, time, weight, puls, pressure);
	if (treining == 1)
		printf("Pass test");
	else
		printf("Fail test");

	return 0;

}
int getInput() {
	int res = 0;
	fflush(stdout);
	scanf("%d", &res);
	return res;

}
int medicalTest(int age, int time, int weight, int puls, int pressure) {
	if (age >= 10 && age <= 30 && weight < 80 && time < 20 && puls < 110
			&& pressure < 120)
		return 1;
	else if (age >= 10 && age <= 30 && weight < 80 && time > 20 && puls < 130
			&& pressure < 122)
		return 1;
	else if (age >= 10 && age <= 30 && weight > 80 && time < 20 && puls < 120
			&& pressure < 125)
		return 1;
	else if (age >= 10 && age <= 30 && weight > 80 && time > 20 && puls < 130
			&& pressure < 129)
		return 1;
	else if (age > 30 && age <= 50 && weight < 80 && time < 20 && puls < 120
			&& pressure < 130)
		return 1;
	else if (age > 30 && age <= 50 && weight < 80 && time > 20 && puls < 130
			&& pressure < 135)
		return 1;
	else if (age > 30 && age <= 50 && weight > 80 && time < 20 && puls < 125
			&& pressure < 135)
		return 1;
	else if (age > 30 && age <= 50 && weight > 80 && time > 20 && puls < 135
			&& pressure < 139)
		return 1;
	else if (age > 50 && age <= 200 && weight < 80 && time < 20 && puls < 130
			&& pressure < 135)
		return 1;
	else if (age > 50 && age <= 200 && weight < 80 && time > 20 && puls < 135
			&& pressure < 139)
		return 1;
	else if (age > 50 && age <= 200 && weight > 80 && time < 20 && puls < 135
			&& pressure < 137)
		return 1;
	else if (age > 50 && age <= 200 && weight > 80 && time > 20 && puls < 139
			&& pressure < 139)
		return 1;
	else
		return 0;
}

