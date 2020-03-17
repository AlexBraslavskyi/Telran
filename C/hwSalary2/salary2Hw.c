#include <stdio.h>
float salary(float hours, float wage, float tax, float bonus, int isBonus,
		int isTax, float res);

int main() {
	float hours = 0, wage = 0, tax = 0, bonus = 0, res = 0;
	int isTax = 0, isBonus = 0;

	printf("Enter number works hours:");
	fflush(stdout);
	scanf("%f", &hours);

	printf("Enter number wage:");
	fflush(stdout);
	scanf("%f", &wage);

	res = salary(hours, wage, tax, bonus, isBonus, isTax, res);
	printf("Total:%.2f", res);

	int temp = 0;
	fflush(stdout);
	scanf("%d", &temp);
	return 0;
}

float salary(float hours, float wage, float tax, float bonus, int isBonus,
		int isTax, float res) {

	printf("Is a tax?  Yes - 1, No - 0:");
	fflush(stdout);
	scanf("%d", &isTax);

	if (isTax == 1)
		printf("Enter number tax:");
	fflush(stdout);
	scanf("%f", &tax);

	if (isTax == 0){
		printf("Wosn't tax in this month.\n");} //почему если 0 то для продолжения надо 2 раза его вводить

	printf("Is a bonus?  Yes - 1, No - 0:");
	fflush(stdout);
	scanf("%d", &isBonus);

	if (isBonus == 1)
		printf("Enter number bonus:");
	fflush(stdout);
	scanf("%f", &bonus);
	if (isBonus == 0)                            //почему если 0 то для продолжения надо 2 раза его вводить
		printf("Wosn't bonus in this month.\n");

	if ((isBonus < 0 || isBonus > 1) || (isTax < 0 || isTax > 1))
		printf(
				"Error: Wrong parameter in field \"Is a bonus\" or \"Is a Tax\" - Use 0 or 1 \n ");

	if (isBonus == 1 && isTax == 1)
		res = (hours * wage) + (hours * wage) * bonus / 100 - (((hours * wage)
				+ (hours * wage) * bonus / 100) * tax / 100);

	if (isBonus == 0 && isTax == 0)
		res = hours * wage;

	if (isBonus == 1 && isTax == 0)
		res = hours * wage + ((hours * wage) * bonus / 100);

	if (isBonus == 0 && isTax == 1)
		res = (hours * wage) - ((hours * wage) * tax / 100);

	return res;

}
