/*#include <stdio.h>
int time_to_work(int transport);
int traffic_delay (float hour);

int main() {
	int delay = traffic_delay (7.5);
	printf ("Delay: %d min \n", delay);

	int time = time_to_work(2);
	if (time != -1)
	printf("Time : %d min \n", time);

	return 0;
}
//bus -> 1; train - >2; bike ->3; car ->4

int traffic_delay (float hour){
	int time = 0;
	if (hour>=6 && hour<=8)
		time = 25;
	else if (hour>8 && hour<=10)
		time = 15;
	else if (hour>10 && hour<=12)
		time = 10;
	else
		time = 2;
	return time;
}

int time_to_work(int transport) {
	int time = 0;
	if (transport < 1 || transport > 4) {
		printf("Error: wrong transport code\n");
		return -1;
	}
	if (transport == 1)
		time = 35;
	else if (transport == 2)
		time = 27;
	else if (transport == 3)
		time = 20;
	else if (transport == 4)
		time = 22;
return time;
}*/
