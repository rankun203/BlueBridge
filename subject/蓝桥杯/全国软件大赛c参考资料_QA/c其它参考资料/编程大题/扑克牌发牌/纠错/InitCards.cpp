// InitCards.cpp : Defines the entry point for the console application.
// CTest.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <stdlib.h>
#include <time.h>
#include <conio.h>

/*
  用1个整数m表示某张扑克牌
  规则：
  m / 13： =0: 红心  =1: 方块  =2: 草花  =3: 黑桃
  m % 13:  =0:2 =1:3  =2:4 ....  =8:10  =9:J =10:Q  =11: K =12:A
*/
void show_card(int x)
{
	int type = x / 13;
	int point = x % 13;

	printf("%c", type+3);

	switch(point)
	{
	case 0:
	case 1:
	case 2:
	case 3:
	case 4:
	case 5:
	case 6:
	case 7:
	case 8:
		printf("%d", point+2);
		break;
	case 9:
		printf("J");
		break;
	case 10:
		printf("Q");
		break;
	case 11:
		printf("K");
		break;
	case 12:
		printf("A");
		break;
	}
}


void show_cards(int x[])
{
	for(int i=0; i<13; i++)
	{
		show_card(x[i]);
		printf(" ");
	}
	printf("\n");
}


void init_cards(int x[])
{
	int cards[52];
	for(int k=0; k<52; k++)	cards[k] = k;

	for(int i=0; i<13; i++)
	{
		int n = rand() % (52-i);
		x[i] = cards[n];
		cards[n] = cards[52-i-1];
	}
}

int compare(int x, int y)
{
	static int type[] = {0, 1, 2, 3};
	return (type[x/13]*13 + x%13) - (type[y/13]*13 + y%13);
}

void sort(int x[])
{
	// 冒泡
	for(int i=0; i<12; i++)
	for(int j=0; j<12-i; j++)
		if(compare(x[j],x[j+1])<0)
		{
			int t = x[j];
			x[j] = x[j+1];
			x[j+1] = t;
		}
}


void find(int x[])
{
	printf("等待考生完成！\n");	
}

int main(int argc, char* argv[])
{
	srand( (unsigned)time( NULL ) );
	
	int x[13];
	init_cards(x);
	show_cards(x);
	
	printf("按任意键排序....\n");
	getch();
	
	sort(x);
	show_cards(x);

	printf("按任意键输出最大同花色连续牌....\n");
	getch();

	find(x);

	getch();

	return 0;
}




