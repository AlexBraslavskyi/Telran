/*#include<stdio.h>
void printABC ();
void printABCReverse ();
void printABCInRows (int inRow);

int main() {
//*  \t - tab
//*  %o - octal
//*	%x - hexa
//* %c - symbol
	printABCInRows (5);
	printABC ();
	printABCReverse ();
	int a = 673, b = 1234;
	printf("\ta = %c b = %d\n", a,b);

	b = 69; // 'E'
	printf("%d -> %c\n",b,b);
printf("%c%c%c%c%c\n",'H','e','l','l','o');
	return 0;
}

void printABC (){
	int sym;
	for (sym = 'A';sym<='Z';sym++)
		printf("%c - > %d\n",sym,sym);

}
void printABCReverse (){
	int sym;
	for (sym = 'z';sym>='a';sym--)
		printf("%c - > %d\n",sym,sym);
}

void printABCInRows (int inRow){
	int sym,count;
	for (sym = 'a',count = 0;sym<='z';sym++){

		printf("%c - >%3d\t",sym,sym);
		count++;
		if(count==inRow){
			printf("\n");
			count =0;
	}
	}
}
*/
