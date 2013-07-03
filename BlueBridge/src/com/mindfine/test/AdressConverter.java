package com.mindfine.test;

/**
	Excel是最常用的办公软件。每个单元格都有唯一的地址表示。比如：第12行第4列表示为：“D12”，第5行第255列表示为“IU5”。
	事实上，Excel提供了两种地址表示方法，还有一种表示法叫做RC格式地址。 第12行第4列表示为：“R12C4”，第5行第255列表示为“R5C255”。
	你的任务是：编写程序，实现从RC地址格式到常规地址格式的转换。	
	【输入、输出格式要求】	
	    用户先输入一个整数n（n<100），表示接下来有n行输入数据。	
	    接着输入的n行数据是RC格式的Excel单元格地址表示法。	
	    程序则输出n行数据，每行是转换后的常规地址表示法。	
	    例如：用户输入：
			2
			R12C4
			R5C255	
	    则程序应该输出：
			D12
			IU5
 * @author mindfine
 */
public class AdressConverter {
	public static void main(String[] args) {
		String rca1	= "R5C255";	//rca: row column address
		int [] rcs	= getRC(rca1);
		String [] cr = convertRowColumnToLetterColumnRow(rcs);
		System.out.println(cr[0] + cr[1]);
		System.out.println("十进制的168转成二进制=" + decimalToBinary(168));
		
	}
	
	/**
	 * 十进制转26进制
	 * 26进制的ABG = A*26^2 + B*26^1 + G*26^0 = 735
	 * ABG:
	 * 	G:个位
	 *  B:常数顶位(个位到顶进一产生这一位)
	 *  A:平方顶位（十位到顶进一产生这一位）
	 * @param rc
	 * @return
	 */
	public static String[] convertRowColumnToLetterColumnRow(int [] rc){
		String []cr = {"",""};
		int tc = rc[1];//to convert
		int n = 0;
		while(true){
			n = tc % 26;
			if(tc == 0)	break;
			tc = tc / 26;
			cr[0] = (char)(n + 64) + cr[0];
		}
		cr[1] = ""+rc[0];
		return cr;
	}
	
	/**
	 * get Row&Column data from Row Column address; 
	 * @param rca Row Column address
	 * @return Array of Integer, represents Row&Column
	 */
	public static int[]	getRC(String rca){
		String[] rcs = rca.split("C");
		String rc1 = rcs[0].substring(1, rcs[0].length());
		String rc2 = rcs[1];
		int []rc = new int[2];
		rc[0] = Integer.parseInt(rc1);
		rc[1] = Integer.parseInt(rc2);
		return rc;
	}
	
	public static String decimalToBinary(int d){
		int m = 0;//余数
		String bin = "";
		
		while(true){
			m = d % 2;
			if(d==0)	break;
			d = d / 2;
			bin = m + bin;
		}
		
		return bin;
	}
}
