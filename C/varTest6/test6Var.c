#include <stdio.h>
int f1(int a,int b);
int f2(int c,int d);
int main()
{
    int m = 6,k = 4,res;
    res = f1(m,k);
    printf("res = %d \n",res);
    res = f2(m,k);
    printf("res = %d \n",res);
    return 0;
}
int f1(int a,int b){
    return a*b;
}
int f2(int c,int d){
    int res = f1(c+1,d-2);
    return res*2;
}
