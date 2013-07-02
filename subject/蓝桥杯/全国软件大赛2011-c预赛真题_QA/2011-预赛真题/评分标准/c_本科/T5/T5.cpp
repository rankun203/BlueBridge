
/*
  答案：
  空1：  len+1     (2分)
  空2：  0         (4分)

注意：
空2 等价形式，可以是：
'\0'  
(char)0  
NULL

如不能断定，需要代入测试
比如：
空2：s[len]  就比较变态，但可行。
*/

#include "stdafx.h"
#include "string.h"
#include "stdlib.h"


void shift(char* s, int n)
{
	char* p;
	char* q;
	int len = strlen(s);
	if(len==0) return;
	if(n<=0 || n>=len) return;
	
	char* s2 = (char*)malloc(len+1);   // 填空1
	p = s;
	q = s2 + n % len;
	while(*p)
	{	
		*q++ = *p++;
		if(q-s2>=len)
		{
			*q = 0;  // 填空2
			q = s2;
		}
	}
	strcpy(s,s2);
	free(s2);
}


int main(int argc, char* argv[])
{
	char x[] = "abcdefg";
	shift(x,2);
	printf("%s\n", x);
	return 0;
}

