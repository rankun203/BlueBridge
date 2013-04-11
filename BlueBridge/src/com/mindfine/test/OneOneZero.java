package com.mindfine.test;

public class OneOneZero {

	public static void main(String[] args) {
		sum110("", 1);
	}

	private static void sum110(String str, int start) {
		String temp = str;
		if(str.indexOf("3") != -1){
			System.out.println("expression: " + str);
		}
		for (int i=start; i<=3; i++) {
			temp += "" + i;
			sum110(temp, ++start);
System.out.println("____:" + temp);
			temp = str;
			start = i;
			
			temp += "+" + i;
			sum110(temp, ++start);
System.out.println("++++:" + temp);
			temp = str;
			start = i;
			
			temp += "-" + i;
			sum110(temp, ++start);
System.out.println("----:" + temp);
			temp = str;
			start = i;

			break;
		}
	}

}
