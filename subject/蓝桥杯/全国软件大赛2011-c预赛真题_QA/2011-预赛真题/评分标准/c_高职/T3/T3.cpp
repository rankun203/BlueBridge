
/*
参考答案：i+=a
或者 i = i + a;

注意：i++
虽然可以得到正确结果，但不符题意（高效运行），不给分。

*/

#include "stdafx.h"

int f(int a, int b)
{
	int i;
	for(i=a;;i+=a)  // 填空
	{
		if(i%b==0) return i;
	}
}

int main(int argc, char* argv[])
{
	printf("%d\n", f(12,15));
	return 0;
}

