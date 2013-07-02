
/*
   本题满分： 9分

  填空1: (3分)
  n==0
  或者：0==n

  填空2: (6分)
  f(n-i*i, a, idx+1)
  或者：
  f(n-i*i, a, idx+1) > 0
  f(n-i*i, a, idx+1) == 1
 
对每个不同的答案要细心，首先带入程序，看结果是否正确
再换数据测试

*/



#include "stdafx.h"
#include "math.h"

// 四方定理：所有自然数至多只要用四个数的平方和就可以表示。

int f(int n, int a[], int idx)
{
	if(n==0) return 1;
	if(idx==4)  return 0;

	for(int i=(int)sqrt(n); i>=1; i--)
	{
		a[idx] = i;

		if(f(n-i*i, a, idx+1))
			return 1;
	}

	return 0;
}

int main(int argc, char* argv[])
{
	for(;;)
	{
		int number;
		printf("输入整数(1~10亿)：");
		scanf("%d",&number);
		
		int a[] = {0,0,0,0};

		int r = f(number, a, 0);

		printf("%d: %d %d %d %d\n", r, a[0], a[1], a[2], a[3]);
		
	}

	return 0;
}

