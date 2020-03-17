
#include <stdio.h>
int f1();//function declaration
void f2();


int main()
{
	printf (" main ");
	f1();
	return 0;
}

int f1()
{
	printf (" f1 ");
	f2();
	return 5;
}

void f2()
{
	printf (" f2 ");
	return;
}
