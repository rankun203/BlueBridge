package com.mindfine.test;

public class AllPermutation {
	public static void main(String[] args) {
		char[] c = "ABCD".toCharArray();
		perm(c, 0);
	}

	//不能重复
	private static void perm(char[] c, int lock) {
		if(lock == c.length - 1) {
			System.out.println(new String(c));
			return;
		}
		for (int i = lock; i < c.length; i++) {
			swap(c, lock, i);
			perm(c, lock+1);
			swap(c, lock, i);
		}
	}
	private static void swap(char[] c, int a, int b) {
		char temp = c[a];
		c[a] = c[b];
		c[b] = temp;
	}

}
