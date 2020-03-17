#include <stdio.h>
float addF(float num1, float num2);
float substF(float num1, float num2);
float multF(float num1, float num2);


int main() {
	float result = 0;
	result = addF(6.2, 6.3);
	printf("result = %.2f \n", result);
	result = substF(result, 2.1);
	printf("result = %.2f \n", result);
	result = multF(result, 2.25);
	printf("result = %.2f \n", result);

	return 0;
}
	float addF(float num1, float num2) {
		return num1 + num2;
	}
	float substF(float num1, float num2) {
		return num1 - num2;
	}
	float multF(float num1, float num2) {
		return num1 * num2;
	}

