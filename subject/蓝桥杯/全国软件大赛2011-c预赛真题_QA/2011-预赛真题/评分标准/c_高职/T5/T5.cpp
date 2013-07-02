
/*
 参考答案：
 空1： len+1     (2分)
 空2： 0         (5分)

 空1：strlen(s) + 1  是一样的
 空2：'\0'  (char)0  NULL 是一样的  

 其它未知的都代入程序试验一下
*/



#include "stdafx.h"
#include "stdlib.h"
#include "string.h"

void shift(char* s, int n)
{
	char* p;
	char* q;
	int len = strlen(s);
	if(len==0) return;
	if(n<=0 || n>=len) return;

	char* s2 = (char*)malloc(len+1);  // 填空1
	p = s;
	q = s2 + n % len;
	while(*p)
	{	
		*q++ = *p++;
		if(q-s2>=len)
		{
			*q = 0; // 填空2
			q = s2;
		}
	}
	strcpy(s,s2);
	free(s2);
}

int main(int argc, char* argv[])
{
	char x[] = "abcdefg";
	shift(x,6);
	printf("%s\n", x);
	return 0;
}

