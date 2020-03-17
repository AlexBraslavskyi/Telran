#include <stdio.h>;
void fan(int mode);
float calculator(float number1, float number2, int action);

int main() {

	float res = calculator(10, 2, 4);
	printf("Result = %f\n", res);
	fan(2);

	return 0;
}
//actions: 1 ->+, 2-> -, 3-> *, 4->/
float calculator(float number1, float number2, int action) {
	float res = 0;
	switch (action) {
	case 1:
		res = number1 + number2;
		break;
	case 2:
		res = number1 - number2;
		break;
	case 3:
		res = number1 * number2;
		break;
	case 4:
		if (number2 == 0)
		printf("error division by zero \n");
		else
			res = number1 / number2;
		break;

	default:
		printf("Error\n");

	}
	return res;
}

void fan(int mode) {
	switch (mode) {
	case 0:
		printf("Switched off \n");
		break;
	case 3:
		printf("Turbo \n");
		break;
	case 1:
		printf("Speed 1 \n");
		break;
	case 2:
		printf("Speed 2 \n");
		break;
	default:
		printf("Error");
	}

}

