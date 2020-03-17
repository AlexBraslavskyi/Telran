#include <stdio.h>
int printMonth(int dayInMonth, int firstDay);
void printDayNames();
int getMonthLength(int number);
void printMonthName(int number);
void printYear(int firstDay);

int main() {
	printYear(3);
	return 0;
}

// Print all year:
// 1. print month ++
// 2. create function day name --
// 3. print day names ++
// 4. create function month length ++
// 5. create function month name ++
// 6. print all months ++

void printYear(int firstDay){
	int i = 0;
	int lastDay = firstDay;
	while(i<12){
		printf("\n");
		printMonthName(i);
		printf("\n");
		printDayNames();
		lastDay = printMonth(getMonthLength(i),lastDay)+1;
		printf("\n");
		i++;
	}
}

void printMonthName(int number){
	switch(number){
		case 0: printf("January"); break;
		case 1: printf("February"); break;
		case 2: printf("March"); break;
		case 3: printf("April"); break;
		case 4: printf("May"); break;
		case 5: printf("June"); break;
		case 6: printf("July"); break;
		case 7: printf("August"); break;
		case 8: printf("September"); break;
		case 9: printf("October"); break;
		case 10: printf("November"); break;
		case 11: printf("December"); break;
	}
}

int getMonthLength(int number){
	switch(number){
		case 0: return 31;
		case 1: return 29;
		case 2: return 31;
		case 3: return 30;
		case 4: return 31;
		case 5: return 30;
		case 6: return 31;
		case 7: return 31;
		case 8: return 30;
		case 9: return 31;
		case 10: return 30;
		case 11: return 31;
	}
}

void printDayNames(){
	printf("Mn Tu Wn Th Fr St Sn\n");
}

int printMonth(int dayInMonth, int firstDay) {
	int position = 0;
	while(position<firstDay-1){
		printf("   ");
		position++;
	}

	int day = 0;
	while (day < dayInMonth) {
		if (position!=0 && position % 7 == 0) {
			printf("\n");
		}
		day++;
		position++;
		printf("%2d ", day);
	}
	return position%7;
}

