#include <stdio.h>
int f1();
int alex();
float elesei ();

int main() {
	float wallet = alex();
	printf("In my wallet %.2f NIS\n", wallet);
	wallet = wallet+elesei();
	printf("In my wallet %.2f NIS\n", wallet);
	return 0;
}

int alex() {
	int money = 20;
	return money;
}

float elesei ()
{
	float money = 100.50;
	return money;
}
