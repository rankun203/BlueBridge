package com.mindfine.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;

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

		System.out.println("---------------------------------------------------------------------------------\r\n" +ftd);
		Stack<String> quoteStack = new Stack<String>();
		boolean inQuote = false;
		for (int i = 0; i < ftd.length(); i++) {
//TODO 实现忽略字符串（双引号之间的内容）里面的//符号
/*
			if (quotPos == i && ftd.charAt(i - 1) != '\\') {
				quoteStack.pop();
				inQuote = false;
			}
			if(!inQuote){//如果不在括号里面
				ftd = ftd.substring(0, firstIn) + "<font color=green>"
						+ ftd.substring(firstIn, ftd.length());
				int firstEnd = ftd.indexOf("\r\n", firstIn);
				ftd = ftd.substring(0, firstEnd) + "</font>"
						+ ftd.substring(firstEnd, ftd.length());
				i = firstEnd;
				
			}
*/
			int firstIn = ftd.indexOf("//");
			
			if(ftd.charAt(i) == '\"'){//如果当前符号是引号
				inQuote = true;
				int quotPos = ftd.indexOf("&quot;", i);
				firstIn = ftd.indexOf("//", quotPos);//在引号之后找注释符号
				if (quotPos != -1) {
					quoteStack.push("\"");
					continue;
				}
				
			}

			if (quoteStack.size() == 0) {
				if (firstIn < 0) {
					break;
				}
			}
		}

		return ftd;
	}

	public static void main(String[] args) throws Exception {
		SourceConverter sc = new SourceConverter();
		String src = sc.readFile("a.txt");
		String ftd = sc.converter(src);
		System.out.println(ftd);
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
