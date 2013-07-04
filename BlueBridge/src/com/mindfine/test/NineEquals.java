package com.mindfine.test;

public class NineEquals {
	public static void main(String[] args) {
		perm("ABCD".toCharArray(), 0);
	}
	
	public static void perm(char []ch, int lock){
		if(lock == ch.length-1) {
			for(char c : ch){
				System.out.print(c);
			}
			System.out.println();
			return;
		}
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
