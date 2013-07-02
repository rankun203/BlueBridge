
/*
  参考答案： 
  x > 0
  
  或者 0<x
  或者 x
*/


#include "stdafx.h"

int main(int argc, char* argv[])
{
	int JC[] = {1,1,2,6,24,120,720,5040,40320,362880};
	int i;
	for(i=100; i<1000; i++)
	{
		int sum = 0;
		int x = i;
		while(x>0)  // 填空
		{
			sum += JC[x%10];
			x /= 10;
		}
		if(i==sum) printf("%d\n", i);
	}

	return 0;
}

