// MorseCode.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "string.h"

void CodeMorse(char* x, char* fname)
{
	char* code[10];
	code[0] = "-----";
	code[1] = ".----";
	code[2] = "..---";
	code[3] = "...--";
	code[4] = "....-";
	code[5] = ".....";
	code[6] = "-....";
	code[7] = "--...";
	code[8] = "---..";
	code[9] = "----.";

	FILE* fp = fopen(fname, "");
	
	char* p = x;
	while(*p)
	{
		if(*p == ' ')
		{
			fputc(' ', fp);
			fputc(' ', fp);
			fputc(' ', fp);
			p++;
			continue;
		}

		if(*p >= '0' && *p <= '9')
		{
			int v = *p;
			for(int i=0; i<5; i++)
			{
				fputc(code[v][i], fp);
			}
			fputc(' ', fp);
		}

		p++;
	}

	fclose(fp);
}


void DecodeMorse(char* fname)
{
}


int main(int argc, char* argv[])
{
	CodeMorse("4543 4744 6545 5456 5455 9877 2815 1742 ", "b.txt");
	//DecodeMorse("a.txt");

	return 0;
}

