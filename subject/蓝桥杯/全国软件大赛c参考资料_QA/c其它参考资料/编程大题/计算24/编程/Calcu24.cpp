// Calcu24.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "conio.h"
#include "stdlib.h"
#include "time.h"
#include "math.h"
#include "string.h"

/*
  从一副扑克牌中，任取4张。
  2-10 按其点数计算(为了表示方便10用T表示)，J,Q,K,A 统一按 1 计算
  要求通过加减乘除四则运算得到数字 24。
  本程序可以随机抽取纸牌，并用试探法求解。
*/

void GivePuzzle(char* buf)
{
	char card[] = {'A','2','3','4','5','6','7','8','9','T','J','Q','K'};

	for(int i=0; i<4; i++){
		buf[i] = card[rand() % 13];
	}
}


void shuffle(char * buf)
{
	for(int i=0; i<5; i++){
		int k = rand() % 4;
		char t = buf[k];
		buf[k] = buf[0];
		buf[0] = t;
	}
}


int GetCardValue(int c)
{
	if(c=='T')  return 10;
	if(c>='0' && c<='9') return c - '0';
	return 1;
}


char GetOper(int n)
{
	switch(n)
	{
	case 0:
		return '+';
	case 1:
		return '-';
	case 2:
		return '*';
	case 3:
		return '/';
	}

	return ' ';
}

double MyCalcu(double op1, double op2, int oper)
{
	switch(oper)
	{
	case 0:
		return op1 + op2;
	case 1:
		return op1 - op2;
	case 2:
		return op1 * op2;
	case 3:
		if(fabs(op2)>0.0001)
			return op1 / op2;
		else
			return 100000;
	}

	return 0;
}


void MakeAnswer(char* answer, int type, char* question, int* oper)
{
	char p[4][3];

	for(int i=0; i<4; i++)
	{
		if( question[i] == 'T' )
			strcpy(p[i], "10");
		else
			sprintf(p[i], "%c", question[i]);
	}
	
	switch(type)
	{
	case 0:
		sprintf(answer, "%s %c (%s %c (%s %c %s))", 
			p[0], GetOper(oper[0]), p[1], GetOper(oper[1]), p[2], GetOper(oper[2]), p[3]);
		break;
	case 1:
		sprintf(answer, "%s %c ((%s %c %s) %c %s)", 
			p[0], GetOper(oper[0]), p[1], GetOper(oper[1]), p[2], GetOper(oper[2]), p[3]);
		break;
	case 2:
		sprintf(answer, "(%s %c %s) %c (%s %c %s)", 
			p[0], GetOper(oper[0]), p[1], GetOper(oper[1]), p[2], GetOper(oper[2]), p[3]);
		break;
	case 3:
		sprintf(answer, "((%s %c %s) %c %s) %c %s", 
			p[0], GetOper(oper[0]), p[1], GetOper(oper[1]), p[2], GetOper(oper[2]), p[3]);
		break;
	case 4:
		sprintf(answer, "(%s %c (%s %c %s)) %c %s", 
			p[0], GetOper(oper[0]), p[1], GetOper(oper[1]), p[2], GetOper(oper[2]), p[3]);
		break;
	}
}


bool TestResolve(char* question, int* oper, char* answer)
{
	// 等待考生完成
	return false;
}


/*
  采用随机试探法：就是通过随机数字产生 加减乘除的 组合，通过大量的测试来命中的解法
  提示：
  1. 需要考虑用括号控制计算次序的问题 比如：( 10 - 4 ) * ( 3 + A ), 实际上计算次序的数目是有限的：
     A*(B*(c*D))
	 A*((B*C)*D)
	 (A*B)*(C*D)
	 ((A*B)*C)*D
	 (A*(B*C))*D
  2. 需要考虑计算结果为分数的情况：( 3 + (3 / 7) ) * 7
  3. 题目中牌的位置可以任意交换
*/
bool TryResolve(char* question, char* answer)
{
	int oper[3];  // 存储运算符，0:加法 1:减法 2:乘法 3:除法

	
	for(int i=0; i<1000 * 1000; i++)
	{
		// 打乱纸牌顺序
		shuffle(question);
		
		// 随机产生运算符
		for(int j=0; j<3; j++)
			oper[j] = rand() % 4;

		if( TestResolve(question, oper, answer) )  return true;
	}

	return false;
}


int main(int argc, char* argv[])
{
	// 初始化随机种子
	srand( (unsigned)time( NULL ) );

	char buf1[4];   // 题目
	char buf2[30];  // 解答


	printf("***************************\n");
	printf("计算24\n");
	printf("A J Q K 均按1计算，其它按牌点计算\n");
	printf("目标是：通过四则运算组合出结果：24\n");
	printf("***************************\n\n");


	for(;;)
	{
		GivePuzzle(buf1);  // 出题

		
		printf("题目：");
		for(int j=0; j<4; j++){
			if( buf1[j] == 'T' )
				printf("10 ");
			else
				printf("%c ", buf1[j]);
		}

		printf("\n按任意键参考答案...\n");
		getch();

		if( TryResolve(buf1, buf2) )  // 解题
			printf("参考：%s\n", buf2);
		else
			printf("可能是无解...\n");

		printf("按任意键出下一题目，x 键退出...\n");
		if( getch() == 'x' ) break;
	}

	return 0;
}

