
/*
  参考答案：
  /d/1000
  
  注意逻辑等价：
  /(d*1000)
  /d*0.001
  ...
  
  不好确定则代入程序中看运行结果
  
*/

public class GaoZhi2
{
	public static double getDistance(int begin, int end, double d)
	{
		return (end-begin) * Math.PI * d * d/ d / 1000;   // 填空
	}
				
	public static void main(String[] args)
	{
		System.out.println(getDistance(100,200,1));
	}
}