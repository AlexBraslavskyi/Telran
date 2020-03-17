#include <stdio.h>
int add(int number1,int number2);
int subst(int num1,int num2);
int mult(int num1,int num2);
float div(float num1,float num2);
float calcPercent(float number,float percent);

int main()
{
   int res = 0;
   float resF = 0;
   res = add(5,6);
   printf("res = %d \n",res);
   res = subst(res,7);
   printf("res = %d \n",res);
   res = mult(res,5);
   printf("res = %d \n",res);

   resF = div(34.7,5.2);
   printf("resF = %.2f \n",resF);

   resF = calcPercent(25000,11.5);
   printf("resF = %.2f \n",resF);

    return 0;
}
float calcPercent(float number,float percent)
{
    return (number*percent)/100;
}

float div(float num1,float num2)
{
   return num1/num2;
}
int mult(int num1,int num2)
{
   return num1*num2;
}
int subst(int num1,int num2)
{
   return num1-num2;
}
int add(int number1,int number2)
{
    return number1+number2;
}

