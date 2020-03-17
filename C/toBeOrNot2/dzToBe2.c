#include <stdio.h>
void f1();
void f2();
void f3();
void f4();

int main()
{
	f3();
	f1();
	printf ("?\n");
	f2();
	printf ("?\n");

	return 0;
}

void f1()
{
	printf("be");
}
void f2()
{
	printf("or\n");
	f4(); // possible include f3 and f1 in f4
	f3();
	f1();
}
void f3()
{
	printf("to ");
}
void f4()
{
	printf("not ");
}
