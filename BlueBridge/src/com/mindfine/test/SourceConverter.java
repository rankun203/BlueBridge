package com.mindfine.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SourceConverter {
	public String converter(String src) {
		String ftd = src;

		ftd = ftd.replaceAll("&", "&amp;").replaceAll(" ", "&nbsp;")
				.replaceAll("<", "&lt;").replaceAll(">", "&gt;")
				.replaceAll("\"", "&quot;")
				.replaceAll("\\t", "&nbsp;&nbsp;&nbsp;&nbsp;")
				.replaceAll("\r\n", "\r\n<br>");

		String[] keyWord = { "public&nbsp;", "return&nbsp;", "throws&nbsp;", "import&nbsp;", "static&nbsp;",
				"int&nbsp;", "long&nbsp;", "float&nbsp;", "double&nbsp;", "new&nbsp;", "void&nbsp;", "class&nbsp;",
				"throw&nbsp;", "while&nbsp;", "for&nbsp;", "private&nbsp;", "protected&nbsp;", "final&nbsp;",
				"const&nbsp;" };
		//加粗关键字
		for (int j = 0; j < keyWord.length; j++) {
			String ptn = keyWord[j];
			ftd = ftd.replaceAll(ptn, "&nbsp;<b>" + ptn + "</b>&nbsp;");
		}

		
		//当前循环是否处于字符串内
		boolean inQuote = false;
		//开启的标签
		int open = 0;
		//循环每一个字符
		for (int i = 0; true; i++) {
			if(ftd.indexOf("&quot;", i) == i) {//如果发现了字符串符号，就toggle isQuote
				inQuote = inQuote ? false : true;
				i += 6;
			}
			if(!inQuote) {//如果没有在字符串内
				if(ftd.charAt(i) == '/' && ftd.charAt(i+1) == '/') {
					ftd = ftd.substring(0, i) + "<font color=green>" + ftd.substring(i, ftd.length());
					open++;
					i += 19;
				} else if (ftd.charAt(i) == '\r' && open != 0) {
					ftd = ftd.substring(0, i) + "</font>" + ftd.substring(i, ftd.length());
					open--;
					i += 8;
				}
			}
			if(i == ftd.length() - 1){
				break;
			}
		}

		return ftd;
	}

	public static void main(String[] args) throws Exception {
		SourceConverter sc = new SourceConverter();
		String src = sc.readFile("a.txt");
		String ftd = sc.converter(src);
		sc.saveFile("a.html", ftd);
		System.out.println("完成");
	}

	private String readFile(String fileName) throws Exception {
		String src = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(fileName), "gbk"));
		while (br.ready()) {
			src += br.readLine() + "\r\n";
		}
		br.close();
		return src;
	}

	private void saveFile(String file, String s) throws Exception {
		PrintWriter out = new PrintWriter(file);
		out.println(s);
		out.close();
	}

}
