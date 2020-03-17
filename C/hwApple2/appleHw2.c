#include <stdio.h>
float david();
int haim();
float rivka();
float lost();

int main() {
	float basket = 0;
	basket = haim() + david() + rivka() - lost();
	printf("Total weight = %.2fkg.", basket); //How I can write "kg." after "basket"?
	return 0;
}

int haim() {
	int apple = 3;
	return apple;

}

float david() {
	float apple = 4.5;
	return apple;
}

float rivka() {
	float apple = 1.75;
	return apple;
}

float lost() {
	float apple = (haim() + david() + rivka()) * 15 / 100;
	return apple;
}
