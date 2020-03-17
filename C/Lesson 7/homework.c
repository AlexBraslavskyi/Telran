/*#include <stdio.h>
float salary(float hours, float wage, float tax, float bonus, int isBonus,
		int isTax);

int main(){

	float netto = salary(180,30,10,7,1,0);
	if(netto != -1){
		printf("Salary: %.2f NIS \n", netto);

	}
	else{
	printf("Error: Please check parameters\n");
	}
	return 0;
}
float salary(float hours, float wage, float tax, float bonus, int isBonus,
		int isTax) {
	if (hours<0||wage<0||bonus<0||tax<0||isBonus<0||isBonus>1||isTax<0||isTax>1){
		printf("Error: Please check parameters\n");
		return 0;

	}

	float brutto = hours*wage;

	if (isBonus = 1){
		float bns = (brutto*bonus)/100;
		brutto = brutto + bns;
	}
	if (isTax ==1)
	{
	float tx = (brutto*tax)/100;
		brutto = brutto-tx;
	}
	return brutto;

}
*/
