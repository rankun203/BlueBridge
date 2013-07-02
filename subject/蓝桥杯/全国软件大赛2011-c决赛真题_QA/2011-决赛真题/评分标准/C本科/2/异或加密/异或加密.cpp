

/*
  本题满分：14分
  
  填空1：(7分)
  uckey[i/8] |= (unsigned char)0x80 >> (i%8);

  填空2：(7分)
  uckey[i/8] &= ~((unsigned char)0x80 >> (i%8));

  注意所有逻辑等价形式都是正确的答案，比如可以使用左移位。
  (unsignec char)0x80 >> 2  等价于：0x01 << 5
  对每个不同的答案要细心，首先带入程序，看结果是否正确
  再换数据测试
*/



#include "stdafx.h"
#include <String.h>
#include <stdlib.h>

// 对一个串用若干二进制位循环异或加密
// 同样方式解密


void f(char* buf, unsigned char* uckey, int n)
{
	int i;
	for(i=0; i<n; i++)
		buf[i] = buf[i] ^ uckey[i];
}

int main(int argc, char* argv[])
{
	char p[] = "abcd中国人123";  // 待加密串
	
	//char* key = "11001100010001110";  //二进制密匙串
	char* key = "11100";  //二进制密匙串
	
	int np = strlen(p);
	int nk = strlen(key);
	unsigned char* uckey = (unsigned char*)malloc(np); 
	
	int i;
	for(i=0; i<np*8; i++)
	{
		if(key[i%nk]=='1')
			uckey[i/8] |= (unsigned char)0x80 >> (i%8);
		else
			uckey[i/8] &= ~((unsigned char)0x80 >> (i%8));
		
	}

	for(i=0; i<np; i++)
	{
		printf("%02X ", (unsigned char)p[i]);
	}
	printf("\n");	

	f(p, uckey, strlen(p));
	
	for(i=0; i<np; i++)
	{
		printf("%02X ", (unsigned char)p[i]);
	}
	printf("\n");

	f(p, uckey, strlen(p));
	
	printf("%s\n", p);
	
	free(uckey);
	
	return 0;
}

