package com.chenjian.test;
/*
 * 趣味算式
 */
public class InterestedFormula {
	public static void main(String[] args) {
		final String[] opt = { ",ADD,", ",SUB,", "" };
		String str = "";
		int count = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					for (int l = 0; l < 3; l++) {
						for (int m = 0; m < 3; m++) {
							for (int n = 0; n < 3; n++) {
								for (int o = 0; o < 3; o++) {
									for (int p = 0; p < 3; p++) {
										str = 1 + opt[i] + 2 + opt[j] + 3
												+ opt[k] + 4 + opt[l] + 5
												+ opt[m] + 6 + opt[n] + 7
												+ opt[o] + 8 + opt[p] + 9;
										String[] s = str.split(",");
										int flag = 1;
										int index = 0;
										int val = 0;
										for (String tmp : s) {
											if (0 == index) {
												val = Integer.parseInt(tmp);
												index = 1;
												continue;
											}
											if ("ADD".equals(tmp)) {
												flag = 1; // 表示加
											} else if ("SUB".equals(tmp)) {
												flag = 2; // 表示减
											} else {
												switch (flag) {
													case 1: {
														val += Integer.parseInt(tmp);
													}break;
													case 2: {
														val -= Integer.parseInt(tmp);
													}break;
													default:{
														System.out.println("好像出错了！看看吧。");
													}break;
												}
											}
										}
										if (110 == val) {
											String stmp = str;
											stmp = stmp
													.replaceAll(",ADD,", "+");
											stmp = stmp
													.replaceAll(",SUB,", "-");
											System.out.println(stmp + " = "
													+ val);
											count++;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		System.out.println("总共有" + count + "条满足。");
	}
}
