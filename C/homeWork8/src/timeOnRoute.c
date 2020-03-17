#include <stdio.h>;
int time_on_route(int busNum);
int delay_traffic (float startHour);
int delayYear (int year);
float getInput();


int main (){
	int busNum = 0;
	float startHour = 0;
	int year =0;
	int res = time_on_route(busNum)+delay_traffic (startHour)+ delayYear (year);
	printf("Total time = %d min", res);
	return 0;
}

float getInput() {
	float res = 0;
	fflush(stdout);
	scanf("%f", &res);
	return res;
}

int time_on_route(int busNum){
	int timeBus = 0;
	printf ("Enter number bus:");
	busNum = getInput();
	switch (busNum){
	case 48: timeBus = 112; break;
	case 51: timeBus = 35; break;
	case 19: timeBus = 59; break;
	case 26: timeBus = 137; break;
	default: {
		printf ("Error: Incorrect number bus, try again (48,51,19 or 26).\n");
		return time_on_route (busNum);}
	}
return timeBus;
}

int delay_traffic (float startHour){
	int dilayTime = 0;
		printf ("Enter start time:");
		startHour = getInput();
	if (startHour <=0 || startHour >=24){
		printf ("Error: Incorrect start time, try again (start time must be >=0 and <=24).\n");
				return delay_traffic (startHour);}
	else if (startHour >=5.3 && startHour <7)
		dilayTime = 17;
	else if (startHour >=7 && startHour <=9)
			dilayTime = 22;
	else if (startHour >9 && startHour <=11)
			dilayTime = 19;
	else
		dilayTime = 5;

	return dilayTime;
}

int delayYear (int year){
	int delayTime = 0;
			printf ("Enter year of the bus:") ;
			year = getInput();
			if (year <2000 || year >2019){
				printf("Error: Incorrect year, bus can't be use(choose between 2000-2019)\n");
				return delayYear (year);}
			else if (year <2005)
				delayTime = 7;
			else if (year >=2005 && year<2010)
				delayTime = 5;
			else if (year >=2010 && year<=2017)
				delayTime = 2;
			else
				delayTime = 1;
			return delayTime;
			}


