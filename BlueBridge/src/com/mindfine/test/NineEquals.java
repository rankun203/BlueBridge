package com.mindfine.test;

public class NineEquals {
	private static int count;//计数器，有多少个符合条件的式子

	public static void main(String[] args) {
		perm("123456789".toCharArray(), 0);
	}
	
	public static boolean eval(char []ch){
		int a = Integer.parseInt(new String(ch, 0, 3));
		int b = Integer.parseInt(new String(ch, 3, 3));
		int c = Integer.parseInt(new String(ch, 6, 3));
		if(a + b == c)	return true;
		return false;
	}
	
	public static void perm(char []ch, int lock){
		if(lock == ch.length-1) {
			//如果符合 xxx + xxx = xxx 的模式的话。。。
			if(eval(ch)) {
				count++;
				System.out.println(count + ": " + new String(ch, 0, 3) + "+" + new String(ch, 3, 3) + "=" + new String(ch, 6, 3));
			}
			return;
		}
		//循环交换，1：1； 1：2； 1：3；
		for(int i = lock; i < ch.length; i++){
			swap(ch, lock, i);
			perm(ch, lock+1);
			swap(ch, lock, i);
		}
	}
	
	public static char[] swap(char []ch, int a, int b){
		char temp = ch[a];
		ch[a] = ch[b];
		ch[b] = temp;
		return ch;
	}

}
