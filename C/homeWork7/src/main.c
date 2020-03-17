/*#include <stdio.h>
int getNumberFromRange(int min, int max);
int getNumber();
int estimate(int price, int year, int kilometers, char hand);
int main() {
	int car1Estimate = estimate(30000, 2009, 190000, 2);
	printf("Estimate 1 - %d\n", car1Estimate); // 0

	int car2Estimate = estimate(30000, 2001, 190000, 1);
	printf("Estimate 2 - %d\n", car2Estimate); // 1

	int car3Estimate = estimate(40000, 2013, 120000, 2);
	printf("Estimate 3 - %d\n", car3Estimate); // 0

	int car4Estimate = estimate(70000, 2015, 100000, 2);
	printf("Estimate 4 - %d\n", car4Estimate); // 1

	printf("Input price:\n");
	int price = getNumberFromRange(5000,1000000);
	printf("Input year:\n");
	int year = getNumberFromRange(1980,2019);
	printf("Input kilometers:\n");
	int kilometers = getNumberFromRange(1000,500000);
	printf("Input hand:\n");
	char hand = getNumberFromRange(0,5);
	int car5Estimate = estimate(price, year, kilometers, hand);
	printf("Estimate 5 - %d\n", car5Estimate);
	return 0;
}

int getNumberFromRange(int min, int max){
	int res = getNumber();
	if (res>=min && res<=max)
		return res;
	else{
		printf("Try again!\n");
		return getNumberFromRange(min,max);
	}
}

int getNumber(){
	int res = 0;
	fflush(stdout);
	scanf("%d", &res);
	return res;
}

int estimate(int price, int year, int kilometers, char hand) {
	if (price >= 20000 && price <= 36000) { //1. Price: 20'000 - 36'000
		if ((year >= 2007 && year <= 2010) && kilometers <= 200000 && hand <= 3) {
			return 0;
		} else {
			return 1;
		}
	} else if (price > 36000 && price <= 50000) {//2. Price: 36'000 - 50'000
		if ((year >= 2009 && year <= 2015) && kilometers <= 150000 && hand <= 2) {
			return 0;
		} else {
			return 1;
		}
	} else if (price > 50000 && price <= 72000) {//3. Price: 50'000 - 72'000
		if ((year >= 2010 && year <= 2017) && kilometers <= 80000 && hand <= 1) {
			return 0;
		} else {
			return 1;
		}
	} else {//4. failed!!!
		return 1;
	}
}
*/
/*	0 - success!!!    1 - failed :(
 * 1. Price: 20'000 - 36'000
 * 		- year: 2007 - 2010
 * 		- kilometers: <= 200'000
 * 		- hand: <=3
 *
 * 2. Price: 36'000 - 50'000
 * 		- year: 2009 - 2015
 * 		- kilometers: <= 150'000
 * 		- hand: <=2
 *
 * 3. Price: 50'000 - 72'000
 * 		- year: 2010 - 2017
 * 		- kilometers: <= 80'000
 * 		- hand: <=1
 * 4. failed!!!
 * */
