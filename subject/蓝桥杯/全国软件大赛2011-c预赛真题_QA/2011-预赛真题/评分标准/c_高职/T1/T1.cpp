
/*
参考答案：money * 1.03

也可以是逻辑等价：
money + money * 3 / 100;

*/

#include "stdafx.h"

int main(int argc, char* argv[])
{
	double money = 1000;
	int n = 5;
	int i;
	for(i=0; i<n; i++)
	{
		money = money * 1.03;  // 填空
		money -= 100; 
	}
	printf("%.2f\n", money);

	return 0;
}

