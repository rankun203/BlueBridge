// HanZi.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "stdio.h"
#include "stdlib.h"
#include "conio.h"
#include "string.h"

/*
  从键盘获得汉字的编码
  在中文windows下，默认编码方式为GB2312, 每个汉字由两个字节组成。
  函数的目的是：把这个汉字的高字节写入 pc1指向的位置，低字节写入 pc2指向的位置
*/
int get_han_zi(unsigned char* pc1, unsigned char* pc2)
{
	char buf[100];
	printf("\n请输入一个汉字：");
	gets(buf);
	if(strlen(buf) != 2) return -1;

	*pc1 = buf[0];
	*pc2 = buf[1];
	if(*pc1 < 0xa1) return -2;
	if(*pc2 < 0xa1) return -2;

	return 0;
}

/*
  从文件（字形文件HZK16）把字形装入到一个32字节的缓冲区 buf 中。
  c1 是该汉字GB2312编码的高字节，c2是低字节。

  每个bit代表1个像素点，16点阵的字模需要256个像素点信息，故需32字节
  字节的排列与16点阵像素的对应关系如下图：
  
  第0字节 第1字节
  第2字节 第3字节
  .......
  第14字节 第15字节

  也就是说：每1行的16个点由2个字节提供。顺序是：从上到下，从左到右。

  GB2312的编码规则是：前一个字节表示区号，后一个表示区中的偏移序号。
  每个区有94个汉字。区号和序号的编码都是从 0xA1开始（为了避免和西文冲突）
  因此，已知某个汉字的编码，就可以计算出文件中所在的绝对位置。
*/
void load_han_zi(unsigned char* buf, unsigned char c1, unsigned char c2)
{
	int n = (c1 - 0xa1) * 94 + (c2 - 0xa1); // 计算在文件中的位置

	FILE* fp = fopen("HZK16.DAT", "rb");
	if(fp==NULL){
		printf("汉字库打开错！");
		exit(1);
	}

	fseek(fp, n * 32L, SEEK_SET);

	for(int i=0; i<32; i++){
		buf[i] = (unsigned char)fgetc(fp);
	}

	fclose(fp);
}

/*
  显示汉字
  buf: 从文件中读出的汉字字模信息，32字节，说明同上
  italic: =1，如果要求斜体， =0 正常
  kx: 水平放大率
  ky: 垂直放大率
  fill: 用于填充的字符
*/
void show_han_zi(unsigned char* buf, int italic, int kx, int ky, char fill)
{
	for(int i=0; i<16; i++){
		for(int k1=0; k1<ky; k1++){
			printf("\n");
			if(italic){
				for(int k3=0; k3<15-i; k3++)
					printf(" ");
			}
			
			for(int j=0; j<16; j++){
				if(buf[i*2 + (j/8)] & (0x80 >> (j%8)))
					for(int k2=0; k2<kx; k2++)
						printf("%c", fill);
				else
					for(int k2=0; k2<kx; k2++)
						printf(" ");
			}
		}
	}
}


void write_to_file(unsigned char* buf, int italic, int kx, int ky, char fill)
{
	FILE* fp = fopen("a.txt", "w");

	for(int i=0; i<16; i++){
		for(int k1=0; k1<ky; k1++){
			fputc('\n', fp);
			if(italic){
				for(int k3=0; k3<15-i; k3++)
					fputc(' ', fp);
			}
			
			for(int j=0; j<16; j++){
				if(buf[i*2 + (j/8)] & (0x80 >> (j%8)))
					for(int k2=0; k2<kx; k2++)
						fputc(fill, fp);
				else
					for(int k2=0; k2<kx; k2++)
						fputc(' ', fp);
			}
		}
	}

	fclose(fp);
}

int set_zoom_k(int* pk)
{
	printf("请输入放大率(1-4)：");
	char buf[100];
	gets(buf);
	
	int n = atoi(buf);
	if(n<1 || n>4) return -1;

	*pk = n;
	return 0;
}

char get_fill_char()
{
	for(;;){
		printf("请输入用于填充的字符");
		char c = getch();
		if(c>32 && c<125) return c;
	}
}

int main(int argc, char* argv[])
{
	unsigned char han_zi_buf[32];  // 存储16点阵汉字的字模
	int font_italic = 0;
	int font_kx = 1;  // 水平放大率
	int font_ky = 1;  // 垂直放大率
	char fill_char = '@';  // 填充字符

	for(;;){
		printf("\n----------------\n");
		printf("1. 输入一个汉字\n");
		printf("2. 切换斜体\n");
		printf("3. 设置水平放大\n");
		printf("4. 设置垂直放大\n");
		printf("5. 设置填充字符\n");
		printf("6. 输出到文件a.txt\n");
		printf("7. 退出\n");
		printf("----------------\n");

		printf("请选择相应的数字\n");
		char c = (char)getch();

		switch(c){
		case '1':
			{
			unsigned char c1;
			unsigned char c2;
			if(get_han_zi(&c1, &c2) != 0)
				printf("\n输入无效！\n");
			else{
				load_han_zi(han_zi_buf, c1, c2);
				show_han_zi(han_zi_buf, font_italic, font_kx, font_ky, fill_char);
			}
			}
			break;

		case '2':
			font_italic = (font_italic + 1) % 2;
			show_han_zi(han_zi_buf, font_italic, font_kx, font_ky, fill_char);
			break;

		case '3':
			if(set_zoom_k(&font_kx) !=0)
				printf("\n输入无效！\n");
			else
				show_han_zi(han_zi_buf, font_italic, font_kx, font_ky, fill_char);
			break;

		case '4':
			if(set_zoom_k(&font_ky) !=0)
				printf("\n输入无效！\n");
			else
				show_han_zi(han_zi_buf, font_italic, font_kx, font_ky, fill_char);
			break;

		case '5':
			fill_char = get_fill_char();
			show_han_zi(han_zi_buf, font_italic, font_kx, font_ky, fill_char);
			break;

		case '6':
			write_to_file(han_zi_buf, font_italic, font_kx, font_ky, fill_char);
			break;

		case '7': 
			exit(0);
			break;
		default:
			printf("\n您的选择无效！\n");
		}
	}
	
	return 0;
}
