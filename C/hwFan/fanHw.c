#include <stdio.h>
void fan(int mode);

int main() {

	int mode;
	printf("Choose number of program:");
	fflush(stdout);
	scanf("%d", &mode);

	fan(mode);

	int temp = 0;
	fflush(stdout);
	scanf("%d", &temp);
	return 0;
}

void fan(int mode) {

	if (mode < 0 || mode > 3)
		printf("Error: Wrong program");
	if (mode == 0)
		printf("Switch Off");
	if (mode == 1)
		printf("Speed - 1");
	if (mode == 2)
		printf("Speed - 2");
	if (mode == 3)
		printf("Turbo");

}
