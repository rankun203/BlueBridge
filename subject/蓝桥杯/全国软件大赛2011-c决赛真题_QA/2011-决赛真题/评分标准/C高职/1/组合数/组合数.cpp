

/*
  组合数问题
  本题满分 8 分

  填空1：(3分)
  return 1

  填空2：(5分)
  f(n-1,m)

对每个不同的答案要细心，首先带入程序，看结果是否正确
再换数据测试

*/





#include "stdafx.h"

// n 个元素中任取 m 个元素，有多少种取法
int f(int n, int m)
{
	if(m>n) return 0;
	if(m==0) return 1;

	return f(n-1,m-1) + f(n-1,m);
}


int main(int argc, char* argv[])
{
	printf("%d\n", f(10,3));
	printf("%d\n", f(5,3));
	printf("%d\n", f(5,2));

	return 0;
}

