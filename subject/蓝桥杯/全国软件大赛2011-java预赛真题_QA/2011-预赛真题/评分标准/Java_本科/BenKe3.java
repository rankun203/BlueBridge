
/*
  答案：
  reverseString(x.substring(1))
  
  注意：
  reverseString(x.substring(1,x.length())) 是对的
  
  这个绕弯的办法也是对的
  reverseString(new StringBuffer(x).deleteCharAt(0).toString())
  
  总之：如果实在确定不下来，填入这个程序来试验一下。
  
*/


public class BenKe3
{
	public static String reverseString(String x)
	{
		if(x==null || x.length()<2) return x;
		return reverseString(x.substring(1)) + x.charAt(0);  // 填空
		//return reverseString(new StringBuffer(x).deleteCharAt(0).toString()) + x.charAt(0);
	}
	
	public static void main(String[] args)
	{
		System.out.println(reverseString("abcd"));
	}
}