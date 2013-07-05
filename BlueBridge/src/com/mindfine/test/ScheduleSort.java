package com.mindfine.test;

import java.util.LinkedList;
import java.util.List;

/*
	人	一	二	三	四	五	六	日
	A 	0	0	0	0	0	0	0
	B	1	1	1	1	1	1	1
	C	0	1	0	1	0	1	0
	D	1	0	1	0	1	0	1
	E	0	1	0	1	0	1	0
要求：
	1.至多：
		1.所有人的连续工作日不能多于3天（注意：周日连到下周一也是连续）。
	2.至少：
		1.一周中，至少有3天所有人都是上班的。
		2.任何一天，必须保证 A B C D 中至少有2人上班。
		3.B D E 在周日那天必须休息。
		4.A E 周三必须上班。
		5.A C 一周中必须至少有4天能见面（即同时上班）。

note：
	1.某天，有哪些人可排（某天，有哪些人不可排）		一次性条件
	2.某天，有哪些人必排								一次性条件
	3.每次排后还要检查结果，与 至少.1 & 至少.2 & 至少.3 & 至少.4 & 至少.5等条件进行比对，
			更新相应的至少记录		比对多次匹配条件匹配结果
	4.某几天要满足的条件	至少：1、2、5；至多：1		多次匹配条件，使用变量记录匹配状况
 */
public class ScheduleSort {
	private List<int[][]>	schemeList = new LinkedList<int[][]>();//方案列表
	private int []continuum = new int[5];//连续工作日,不能大于3天
	private int [][]tempScheme = new int[5][7]; 

	//至少，每周的排列生成后即清空这些值
	private int allJoin = 0;//至少有3天，所有人都是上班的，每轮开始都是0天！每天完了检查+1
	private int abcdJoin = 0;//每一天，A、B、C、D 中至少有2人上班，每天开始都是0人！每天完了检查+1
	private int acJoin = 0;//A、C 两人同时上班的天数，每轮开始是没有！每天完了检查+1
	
	
	public static void schedule(){
		for(int i = 0; i < 5; i++){
			
		}
	}
	
	/**
	 * 生成给定数组的全排列
	 * a[0]、a[1]、a[2] 、、、都有两种取值，整个排列有2^35种排列方法。。。。。。
	 * @deprecated 不划算。。。
	 * @param ia 要排列的数组
	 */
	public static void perm(int []ia){
	}
	
	public static void main(String[] args) {
		int []ia = new int[35];//ia : int array
		perm(ia);
	}
	public int[] plusOne(int [] a){
		return null;
	}
}
