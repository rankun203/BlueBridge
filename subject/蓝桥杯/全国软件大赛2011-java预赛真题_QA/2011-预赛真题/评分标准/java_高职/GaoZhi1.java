
/*
  参考答案：
  x += price[i] * weight[i]
  
  x = x + price[i] * weight[i] 也正确
  
  注意加法、乘法交换率
  不能确定则代入试验
*/

public class GaoZhi1
{
	public static double getTotal(double[] price, double[] weight)
	{
		double x = 0;
		for(int i=0; i<price.length; i++)
		{
			x += price[i] * weight[i];  // 填空
		}
		return x;
	}
		
	public static void main(String[] args)
	{
		double[] p = {1.5, 2.4, 1.2, 3.3};
		double[] w = {2,2,3,1.5};
		
		System.out.println(getTotal(p, w));	
	}
}