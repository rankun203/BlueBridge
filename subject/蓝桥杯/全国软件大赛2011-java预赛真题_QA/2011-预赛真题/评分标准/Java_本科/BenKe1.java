
/*
  答案：
  y * a / b
  当然,下列写法也是对的：
  a / b * y
  a * y / b
*/


public class BenKe1
{
	public static void main(String[] args)
	{
		double x = 1;
		double y = 1;
		int a = 1;
		int b = 3;
		
		while(y>1e-15)
		{
			y =  y * a / b;  // 填空位置
			x += y;
			a++;
			b += 2;
		}
		System.out.println(x*2);
		
	}
}