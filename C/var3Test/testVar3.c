#include <stdio.h>
int f1(int a);
float f2(float a);

int main()
{
    int result = 0;
    float resF = 0;
    result = f1(8.6);
    printf("result  = %d\n", result);
    result = f1(32);
    printf("result  = %d\n", result);

    resF = f2(10);
    printf("resF = %.3f\n",resF);

    return 0;
}
float f2(float a)
{
    return a/3;
}
int f1(int a)
{
    return a*2;
}
