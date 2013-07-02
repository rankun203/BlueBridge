// ShowDigit.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "stdlib.h"
#include "conio.h"
#include "string.h"

/*
这是每个数字对应的数码管号的矩阵
例如第一行表示数字0所对应的数码管的标号为0 1 2 3 5 6 7
也就是说只有3号数码管是暗的，其它的数码管都是点亮的
再如：数字8所对应的所有数码管都是亮的。
数码管的标号是按照从上到下，从左到右的顺序进行的，
下列示意图：
 
 0000
1    2
1    2
 3333
4    5
4    5
 6666
*/

static int MX[10][7] = 
{
	{1,1,1,0,1,1,1},
	{0,1,1,0,0,1,0},
	{1,0,1,1,1,0,1},
	{1,0,1,1,0,1,1},
	{0,1,1,1,0,1,0},
	{1,1,0,1,0,1,1},
	{1,1,0,1,1,1,1},
	{1,0,1,0,0,1,0},
	{1,1,1,1,1,1,1},
	{1,1,1,1,0,1,1}
};

/*
这是用于模拟显示效果的缓冲
因为无法驱动真正的数码管，我们把要驱动的数码管所在的位置“描绘”到缓冲区中
每个缓冲区只能描绘1个数字，缓冲区“像素”数为：7x4
比如，3号数码管在缓冲区中的描绘为：
....
....
....
@@@@
....
....
....
再比如，5号管描述为：
....
....
....
...@
...@
...@
...@
注意，不同数码管所占据的模拟位置稍有重叠，这并不影响效果。
*/
static char BUF1[7][4];
static char BUF2[7][4];
static char BUF3[7][4];


void init_buf(char p[][4])
{
	for(int i=0; i<7; i++){
		for(int j=0; j<4; j++){
			p[i][j] = '.';
		}
	}
}


void write_one_to_buf(char buf[][4], int n)
{
	int i;
	switch(n){
	case 0:
		for(i=0; i<4; i++) 
			buf[0][i] = '@';
		break;
	case 1:
		for(i=0; i<4; i++) 
			buf[i][0] = '@';
		break;
	case 2:
		for(i=0; i<4; i++) 
			buf[i][3] = '@';
		break;
	case 3:
		for(i=0; i<4; i++) 
			buf[3][i] = '@';
		break;
	case 4:
		for(i=0; i<4; i++) 
			buf[i+3][0] = '@';
		break;
	case 5:
		for(i=0; i<4; i++) 
			buf[i+3][3] = '@';
		break;
	case 6:
		for(i=0; i<4; i++) 
			buf[6][i] = '@';
		break;
	}
}


void show_buf()
{
	printf("\n");
	for(int i=0; i<7; i++){
		for(int j=0; j<4; j++){
			printf( "%c", BUF1[i][j]);
		}
		printf("   ");
		for(j=0; j<4; j++){
			printf( "%c", BUF2[i][j]);
		}
		printf("   ");
		for(j=0; j<4; j++){
			printf( "%c", BUF3[i][j]);
		}

		printf("\n");
	}
}

// lk: 水平放大系数， hk: 垂直放大系数
void show_buf_big(int lk, int hk)
{
	// 限制参数的幅度
	if(lk<1)  lk = 1;
	if(lk>5)  lk = 5;
	if(hk<1)  hk = 1;
	if(hk>5)  hk = 5;

	printf("....该功能请考生参照原型效果完成！\n");
}


void show_buf_lean()
{
	printf("\n");
	for(int i=0; i<7; i++){
		
		for(int k=0; k<i; k++){
			printf(" ");
		}

		for(int j=0; j<4; j++){
			printf( "%c", BUF1[i][j]);
		}
		printf("   ");
		for(j=0; j<4; j++){
			printf( "%c", BUF2[i][j]);
		}
		printf("   ");
		for(j=0; j<4; j++){
			printf( "%c", BUF3[i][j]);
		}
		printf("\n");
	}
}


void write_to_buf(char buf[7][4], int digit)
{
	// 某个数字由那些根数码管组成？
	for(int i=0; i<7; i++){
		if(MX[digit][i] == 1)
			write_one_to_buf(buf, i);
	}
}


int main(int argc, char* argv[])
{
	for(;;){
		printf("请输入要显示的3位整数（100-999）, 输入exit退出： ");
		char buf[100];
		gets(buf);
		
		if(buf=="exit") break;

		int n = atoi(buf);
		if(n<100 || n>999){
			printf("输入的数字无效！重新输入...\n");
			continue;
		}

		// 分解为：千位、百位、个位
		int a = n / 100;  
		int b = n / 10 % 10;
		int c = n % 10;

		init_buf(BUF1);
		init_buf(BUF2);
		init_buf(BUF3);

		//在实际应用中，只要知道了需要显示哪些数码管，就可以直接控制它们，
		//在此处，我们通过刷新到一个用于输出的缓冲区来模拟这个过程：
		//不亮的位置，我们用“.”来表示，点亮的位置，我们用“@”来表示
		write_to_buf(BUF1, a);  
		write_to_buf(BUF2, b);
		write_to_buf(BUF3, c);

		show_buf();  // 显示模拟缓冲区中的内容

		printf("按任意键继续..");
		getch();
		show_buf_lean();  // 倾斜显示

		printf("按任意键继续..");
		getch();
		show_buf_big(2,3);  // 水平放大2倍，垂直放大3倍

		printf("按任意键继续..");
		getch();
		show_buf_big(5,1);  // 水平放大5倍，垂直放大1倍（垂直不放大）
	}

	return 0;
}

