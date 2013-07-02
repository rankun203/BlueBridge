
/*
  答案：
  空1：  (int)dTestNo   （2分）
  空2：  dTestNo>=1.0   （3分）

  注意等价形式，如不能判定，代入程序进行运行试验
*/

#include "stdafx.h"

void fun(double dTestNo, int iBase)
{
	int iT[8];
	int iNo;
	
	printf("十进制正小数 %f 转换成 %d 进制数为: ",dTestNo, iBase);
	
	for(iNo=0;iNo<8;iNo++)
	{
		dTestNo *= iBase;
		iT[iNo] = (int)dTestNo;    // 填空1
		if(dTestNo>=1.0) dTestNo -= iT[iNo];  // 填空2
	}
	
	printf("0.");
	for(iNo=0; iNo<8; iNo++) printf("%d", iT[iNo]);
	printf("\n");
}

int main (int argc, char* argv[])
{	
	double dTestNo= 0.795;
	int iBase;
	
	for(iBase=2;iBase<=9;iBase++)
		fun(dTestNo,iBase);
	printf("\n");

	return 0;
}


