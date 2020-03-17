#include <stdio.h>


int main(){
	void taxi (int code);

	//int a = -1;
	//if(a>0)
	//printf("Hello \n");
	//printf("Bye \n");
	int code =0;
	printf ("Please enter code: \n");
	fflush (stdout);
	scanf ("%d",&code);
	taxi(code);
	return 0;
}

void taxi (int code){
	if (code <1 || code > 4)
		printf("Error: wrong code \n");
		return;
	if (code == 1)
		printf("Haim go! \n");
	if (code == 2)
		printf("David go! \n");
	if (code == 3)
		printf("Alex go! \n");
	if (code == 4)
		printf("Rivka go! \n");

}
