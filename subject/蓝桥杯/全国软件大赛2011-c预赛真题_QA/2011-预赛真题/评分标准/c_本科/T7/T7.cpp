/*
  参考答案：
  空1：pro(m-1,n,x-1,y-1)  (5分)
  空2：pro(m,n-1,x-1,y)    (5分)

  未知答案代入试验一下（多试验几个样例）
*/

#include "stdafx.h"

/*
m: 袋中红球的数目
n: 袋中白球的数目
x: 需要取出的数目
y: 红球至少出现的次数
*/
double pro(int m, int n, int x, int y)
{
	if(y>x) return 0;
	if(y==0) return 1;
	if(y>m) return 0;
	if(x-n>y) return 1;
	double p1 = pro(m-1,n,x-1,y-1);  // 填空1
	double p2 = pro(m,n-1,x-1,y);    // 填空2
	return (double)m/(m+n) * p1 + (double)n/(m+n) * p2;
}


int main(int argc, char* argv[])
{
	printf("%f\n",pro(5,5,3,1));
	return 0;
}

