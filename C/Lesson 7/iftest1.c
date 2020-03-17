#include <stdio.h>
void fan(int mode);
void yourAge(int age);
float calculator(float number1, float number2,int action);



int main(){

	//fan(3);
	//float result = calculator(10,0,4);
	//printf ("Result: %.2f",result);

	yourAge(50);
	printf ("My age",yourAge);
	return 0;
}
//action = 1 ->*, 2->+,3-> -, 4->/
float calculator(float number1, float number2,int action){

	float result = 0;
	if (action ==1)
		result = number1*number2;
	else if (action ==2)
		result = number1+number2;
	else if (action ==3)
		result = number1-number2;
	else if (action == 4){
		if (number2!=0)
		result = number1/number2;
		else
			printf("Error: division by Zero\n");
	}
	else
		printf("Error\n");
	return result;
}

void fan(int mode){

if (mode == 0)
	printf ("Switch off \n");
else if (mode == 1)
	printf ("Speed 1 \n");
else if (mode == 2)
	printf ("Speed 2 \n");
else if (mode == 3)
	printf ("Turbo \n");
else
	printf ("Error");

}

//0-17 -> child, 18-62 -> adult, 62+ -> senior

void yourAge(int age){
	if (age >=0 && age <=17)
		printf("Child");
	else if (age >=18 && age <=62)
		printf ("Adult");
	else if (age > 62 && age<200)
		printf ("Senior");
	else
	 printf("Error: wrong age");

}
