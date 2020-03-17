#include <stdio.h>
float calcSalary(float hours,float wage,float tax);

int main()
{
    float salaryNetto = 0;
    salaryNetto = calcSalary(182,200,25);
    printf("Salary : %.2f NIS\n",salaryNetto);

    return 0;
}
float calcSalary(float hours,float wage,float tax)
{
    float brutto = hours*wage;
    float taxMoney = (brutto*tax)/100;
    return brutto - taxMoney;
}
