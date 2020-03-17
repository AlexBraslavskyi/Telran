#include <stdio.h>
int f1 (int m, int k);
int main()
{

	int m =3;
	int k =4;
	int res =0;
	res=f1(m,k);
	printf("m(main) = %d, k(main)=%d\n",m,k);
	printf ("%d \n", res);

	f1(m,k);

	return 0;
}
int f1 (int m, int k)
{
	m=1000;
	k=5;
	return m+k;
}
