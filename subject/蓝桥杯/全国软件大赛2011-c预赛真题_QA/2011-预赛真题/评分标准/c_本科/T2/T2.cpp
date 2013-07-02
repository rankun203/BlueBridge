
/*
  答案：j != i

  注意逻辑等价形式
   i != j
   i-j != 0
   i-j   (比较变态)
*/

#include "stdafx.h"

double score(double x[], int n)
{
	int i,j;
	double dif = -1;
	double bad;
	for(i=0; i<n; i++)
	{
		double sum = 0;
		for(j=0; j<n; j++)
		{
			if( j != i ) sum += x[j];     // 填空
		}
		double t = x[i] - sum / (n-1);
		if(t<0) t = -t;
		if(t>dif)
		{
			dif = t;
			bad = x[i];
			printf("%d, %f\n", i, x[i]);
		}
	}
	
	return bad;
}



int main(int argc, char* argv[])
{
	double x[] = {40,20,30,10,60};
	printf("%f\n", score(x,5));

	return 0;
}

