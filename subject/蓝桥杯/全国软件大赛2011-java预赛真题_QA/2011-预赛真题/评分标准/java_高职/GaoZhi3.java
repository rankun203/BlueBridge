
/*
  参考答案：
  s2
  
  发现新答案能运行良好及时上报
*/

public class GaoZhi3
{		
	public static void main(String[] args)
	{
		String s = "1234567";		
		String s2 = "";
		for(int i=0; i<s.length(); i++)
		{
			s2 = s.charAt(i) + s2; // 填空
		}
		System.out.println(s2);		
	}
}