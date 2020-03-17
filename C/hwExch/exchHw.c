#include <stdio.h>

float sumExch (float money,float rate,float commission);


int main()
{
	float exchenge = 0;
	exchenge = sumExch(1000,3.5,0.5);
	printf ("sumExch = %.2f USD",exchenge);

	return 0;
}

float sumExch (float money,float rate,float commission)
{

	float bruttoDollar = money/rate;                        //Variant 1
	float sumCommission = (bruttoDollar*commission)/100;
	return bruttoDollar-sumCommission;
	//float sumCommission = (money*commission)/100;           Variant 2
	//return (money-sumCommission)/rate;

}
