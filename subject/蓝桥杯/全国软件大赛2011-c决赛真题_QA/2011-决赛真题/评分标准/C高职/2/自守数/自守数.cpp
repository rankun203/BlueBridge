
/*
  自守数

  本题满分：15分

  填空1：
  m % 10 != k 或者：
  k = m % 10
  m%10-k==0

  填空2：
  n2 / 10

  对每个不同的答案要细心，首先带入程序，看结果是否正确
  再换数据测试
*/


#include "stdafx.h"


void zishou()
{
	int n;
	for(n=1; n<20 * 1000 * 1000; n++)
	{
		int n2 = n;
		int m = 0;
		for(;;)
		{
			if(n2==0) 
			{
				printf("%d\n", n);
				break;
			}
			int k = n2 % 10;
			m += k * n;
			if(m % 10 != k) break;
			m = m / 10;
			n2 = n2 / 10;
		}
	}
}

int main(int argc, char* argv[])
{
	zishou();
	return 0;
}

