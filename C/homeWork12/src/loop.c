#include<stdio.h>
void print_ascii(int first, int last);
void printPicture (int size);
void printPicClock(int size);
int main() {

int ind,b;
for (ind = 0,b=100;ind<b;ind++)
	printf("%d",ind);
	printf("\n");
	printPicture (10);
	printf("\n");
	printPicClock(12);
	int first = 33;
	int last = 126;
	printf("\n");
	printf("\tprinting ASCII table from %c to %c\n",first,last);
	print_ascii(first,last);
	return 0;
}

void printPicture (int size){
	int v,h;
	for(v  =0;v < size;v++){
	for (h = 0;h < size;h++){
if (v == 0||h == 0||v ==size-1||h == size-1||v == h||h<v)
	printf("*");
else
	printf(" ");
}
	printf("\n");
}
}
void printPicClock(int size){
	int v,h;
	for(v  =0;v < size;v++){
	for (h = 0;h < size;h++){
				if(v<=h&&(v+1<=size-h)||(v+1>=size-h)&&v>=h
						||v == 0||h == 0||v ==size-1||h == size-1||v == h)
	printf("*");
else
	printf(" ");
}
	printf("\n");
}
}
void print_ascii(int first, int last){
	int count = 0;
	for (first = 33;first<=last;first++){
		printf("code %3d => symbol %c;\t",first,first);
 count++;
 if (count == 4){
	 printf("\n");
	 count =0;
 }
}
}


