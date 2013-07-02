
/*
参考答案：
空1： h(space+1, x-1)   (6分)
空2： x-i              （3分）

(char)((int)x-i) 当然也是可以的
如不能确定，需要代入程序运行实验
*/

#include "stdafx.h"

void h(int space, char x)
{
	int i;
	if(x<'A' || x>'Z') return;
	h(space+1, x-1);  // 填空1
	for(i=0; i<space; i++) printf(" ");
	for(i=0; i<x-'A'; i++) printf("%c",'A'+i);
	for(i=0; i<=x-'A'; i++) printf("%c",x-i);  //填空2
	printf("\n");
}

int main(int argc, char* argv[])
{
	
	h(6,'E');
	return 0;
}

