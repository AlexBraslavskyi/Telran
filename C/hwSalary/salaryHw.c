#include <stdio.h>
float calcSalary(float hours,float wage,float tax,float bonus);

int main()
{
    float salaryNetto = 0;
    salaryNetto = calcSalary(180,100,20,10);
    printf("Salary : %.2f NIS\n",salaryNetto);

    return 0;
}
float calcSalary(float hours,float wage,float tax,float bonus)
{
    float brutto = hours*wage;
    float taxMoney = (brutto*tax)/100;
    float bonusMoney = (brutto*bonus)/100;
    return brutto - taxMoney + bonusMoney;
}
