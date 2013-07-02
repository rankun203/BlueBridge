
/*
  答案：
  空1： null             (4.5分)
  空2： cow.afterYear()  (4.5分)
  
        cow.age++  也是正确的
        
  如不能确定，代入程序试验一下。
*/

import java.util.*;

public class BenKe5
{
public static class Cow 
{
	private int age;
	public Cow afterYear()
	{
		age++;
		return age > 2 ? new Cow() : null;  // 填空1
	}	
	
	public static void showTotalCowNum(int n)
	{
		List<Cow> list = new ArrayList<Cow>();
		list.add(new Cow());

		for (int i = 0; i < n; i++)
		{
			int cowCount = list.size();
			for (int j = 0; j < cowCount; j++)
			{
				Cow cow = list.get(j).afterYear();
				if (cow != null)
				{
					cow.afterYear();  // 填空2
					//cow.age++;
					list.add(cow);
				}
			}
		}
		System.out.println(n + "年后，共有：" + list.size());		
	}
}	
	
	
	public static void main(String[] args)
	{
		Cow.showTotalCowNum(7);  // 13
	}
}