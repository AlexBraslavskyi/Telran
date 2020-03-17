/*#include <stdio.h>
void printOption4(char day, char month, int year);
void printMonthName(char month);
void printDate(char mode, char seconds, char minutes, char hours,
		char day, char month, int year, char era);

int main(){
	printDate(4, 20, 9, 5, 24, 10, 2019, 1);
	return 0;
}

//	mode:
 //     	0 -> „асы(24):минуты
 // 		1 -> „асы(24):минуты:секунды
 // 		2 -> „асы(12):минуты:секунды
 // 		3 -> день.мес€ц
 // 		4 -> день мес€ц(название) год
 // 		5 -> мес€ц(название) год эра (1 = A.C.\ 0 = B.C)
 // 		default -> день мес€ц(название) год - эра (A.C.\B.C)

void printOption4(char day, char month, int year){
		printf("%02d ",day);
		printMonthName(month);
		printf(" %d",year);
	}
void printDate(char mode, char seconds, char minutes, char hours,
		char day, char month, int year, char era){
	//TODO validate input data
	switch(mode){
		case 0: printf("%02d:%02d", hours, minutes); break;
		case 1: printf("%02d:%02d:%02d", hours, minutes, seconds); break;
		case 2: printf("%02d:%02d:%02d", hours%12, minutes, seconds); break;
		case 3: printf("%02d.%02d", day, month); break;
		case 4: printOption4(day,month,year);	break;
	}
}

	void printMonthName(char month){
		switch(month){
			case 1: printf("January"); break;
			case 2: printf("February"); break;
			case 3: printf("March"); break;
			case 4: printf("April"); break;
			case 5: printf("May"); break;
			case 6: printf("June"); break;
			case 7: printf("July"); break;
			case 8: printf("August"); break;
			case 9: printf("September"); break;
			case 10: printf("October"); break;
			case 11: printf("November"); break;
			case 12: printf("December"); break;
		}
	}


//	hours:  23/12 = 3 целых 2 остаток
//	    0->0
//		1->1
//		11->11
//	    12->0
//		14->2
//		16->4
//		19->7
//		23->11
//
*/
