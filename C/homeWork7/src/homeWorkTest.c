/*#include <stdio.h>
int timeToWork(int transport);
int trafficDelay(float hours);
int delayLocation(int city);

int main() {
	int transport=0;
	float hours=0;
	int city=0;

	int transportTime = timeToWork(transport);
	printf("%d\n", transportTime);

	float trafficTime = trafficDelay(hours);
	printf("%.2f\n", trafficTime);

	int cityTime = delayLocation(city);
	printf("%d \n", cityTime);

	int tottalTime = transportTime + trafficTime + cityTime;
	printf("Tottal time: %d \n", tottalTime);

	int temp = 0;
	fflush(stdout);
	scanf("%d", &temp);

	return 0;
}

int timeToWork(int transport) {
	printf(
				"Choose kind of transport: \"Auto - 1\", \"Bike - 2\", \"Bus - 3\", \"Train - 4\"");
		fflush(stdout);
		scanf("%d", &transport);

	if (transport == 1)
		return 25;
	else if (transport == 2)
		return 20;
	else if (transport == 3)
		return 35;
	else if (transport == 4)
		return 30;
	else {
		printf("Error: wrong number transport\n");
		return timeToWork(transport);
	}
}


int trafficDelay(float hours) {
	printf("Choose time start traffic:");
		fflush(stdout);
		scanf("%f", &hours);
	if (hours >= 6 && hours <= 8)
		return 25;
	else if (hours > 8 && hours <= 10)
		return 15;
	else if (hours > 10 && hours <= 12)
		return 10;
	else {
		printf("Error: wrong parameters\n");
		return trafficDelay(hours);
	}
}
int delayLocation(int city) {

		printf(
				"Choose a city: \"Tel Aviv - 1\", \"Ashdod - 2\", \"Beer Sheva - 3\", \"Jerusalim - 4\"");
		fflush(stdout);
		scanf("%d", &city);
	if (city == 1)
		return 25;
	else if (city == 2)
		return 20;
	else if (city == 3)
		return 15;
	else if (city == 4)
		return 27;
	else{
		printf("Error: wrong parameters\n");
		return delayLocation(city);
	}
}


*/
