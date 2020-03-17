#include <stdio.h>
void f1();
void f2();
void f3();
void f4();
void f5();
void f6();

int main()
{

	printf (" main ");
	f6();
	f1();
	f2();
	return 0;
}

void f1()
{
	printf(" f1 ");
	f4();
}

void f2()
{
	printf(" f2 ");
	f6();
}
void f3()
{
	printf(" f3 ");
}
void f4()
{
	printf(" f4 ");
	f5();
}
void f5()
{
	printf(" f5 ");
	f6();
}
void f6()
{
	printf(" f6 ");
}
