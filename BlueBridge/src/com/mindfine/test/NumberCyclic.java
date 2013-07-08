package com.mindfine.test;

public class NumberCyclic {
	public static void main(String[] args) {
		double x = 5;
		for(int i = 0; i < 100; i++){
System.out.println(x);
			x = x * (1 - x) * 3.62;
		}
	}

}
