// MyMenu.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "stdlib.h"
#include "string.h"


/*
  使得一个菜单项可以和其它菜单项保持联系
  由它出发可以：
  找到第一个“子菜单”
  找到下一个“兄弟”
  找到它的“父节点”菜单项
*/
struct MenuItem
{
	char  text[30];
	int level;
	MenuItem*  next;
	MenuItem*  child;
	MenuItem*  parent;
};


/*
  读入一个菜单项的描述文本，生成菜单项，挂接到合适的位置。
  文本的描述中由若干TAB字符（制表符）引导。
  仔细观察示例的文本文件，前边的空白不是空格而是TAB。
  TAB符号越多，表示该项的 level 越大，即菜单路径越“深”。
  通过判断菜单项的level 与上一个菜单项的 level关系，可以决定如何“挂接”
*/
MenuItem* AddMenu( MenuItem* cur, char* text )
{
	MenuItem* pNew = new MenuItem();
	pNew->next = pNew->child = pNew->parent = NULL;

	char* p = text;
	int level = 0;

	while( *p == '\t' ){
		level++;
		p++;
	}

	strcpy( pNew->text, text+level );
	pNew->level = level;
	
	if( pNew->level == cur->level )
	{
		cur->next = pNew;
		pNew->parent = cur->parent;
	}
	else if ( pNew->level > cur->level )
	{
		cur->child = pNew;
		pNew->parent = cur;
	}
	else
	{
		MenuItem* p = cur;
		for(int i=0; i < cur->level - pNew->level; i++) 
			p = p->parent;

		p->next = pNew;
		pNew->parent = p->parent;
	}

	return pNew;
}


void CreateMenu(MenuItem* root)
{
	MenuItem* cur_menu = root;

	FILE* fp = fopen("a.txt", "r"); 
	if( fp == NULL ) exit(-1);

	while(true){
		char buf[100];
		if( fgets( buf, 99, fp ) == NULL ) break;
		// 去掉尾部不必要的换行符号
		if(buf[strlen(buf)-1] == '\n' )
			buf[strlen(buf)-1] = '\0';

		cur_menu = AddMenu(cur_menu, buf);
	}

	fclose(fp);
}


void RunMenu(MenuItem* menu)
{
	printf("等待考生完成！");
}


// 释放菜单所占用的内存
void FreeMenu(MenuItem* menu)
{
	if(menu->child != NULL)
		FreeMenu( menu->child );
	if(menu->next != NULL)
		FreeMenu( menu->next );

	delete menu;
}

int main(int argc, char* argv[])
{
	MenuItem* root = new MenuItem();
	root->text[0] = '\0';
	root->next = root->parent = root->child = NULL;
	root->level = -1;

	// 创建菜单项在内存中的结构
	CreateMenu( root );

	// 运行菜单
	RunMenu( root );

	// 释放资源
	FreeMenu( root );

	return 0;
}

