#include <stdio.h>
void f1();
void f2();
void f3();

int main()
{
	f2();
	f1();
	f3();
	printf ("Sasha");
	return 0;
}

void f1()
{
	printf ("name ");
}
void f2()
{
	printf ("My ");
}
void f3()
{
	printf ("is ");
}
