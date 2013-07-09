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

		String[] keyWord = { "public", "return", "throws", "import", "static",
				"int", "long", "float", "double", "new", "void", "class",
				"throw", "while", "for", "private", "protected", "final",
				"const" };
		for (int i = 0; i < keyWord.length; i++) {
			String ptn = keyWord[i];
			ftd = ftd.replaceAll("&nbsp;" + ptn + "&nbsp;", "&nbsp;<b>" + ptn
					+ "</b>&nbsp;");
		}

		
		//当前循环是否处于字符串内
		boolean inQuote = false;
		//开启的标签
		int open = 0;
		//每个引号一个位置
		int lastQuot = 0;
		//循环每一个字符
		for (int i = 0; true; i++) {
System.out.println(i);
			if(ftd.indexOf("&quot;", i) == i) {//如果发现了字符串符号，就toggle isQuote
				lastQuot = i;
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

			if(i==886){
				System.out.println("--"+ftd.length());
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
