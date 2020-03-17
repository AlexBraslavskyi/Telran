#include <stdio.h>
int f1();
void f2();
int main()
{
   f1();
   printf(" main\n");
   return 0;
}
int f1()
{
    printf(" f1\n");
    f2();
    return 5;
}
void f2()
{
    printf(" f2\n");
    return;
}
