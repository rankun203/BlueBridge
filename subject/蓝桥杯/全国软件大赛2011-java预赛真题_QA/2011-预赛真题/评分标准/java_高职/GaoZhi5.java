
/*
  参考答案：
  空1： 0.5
  空2： value>1000
  
  注意逻辑等价，无法确定则代入程序执行之
*/

public class GaoZhi5
{
	public static void main(String[] args)
	{
		int N = 10000;
		int n = 0;
		
		for(int i=0; i<N; i++)
		{
			double value = 1000.0;	
			for(int k=0; k<100; k++)
			{
				if(Math.random() > 0.5)  // 填空1
					value = value * 1.1;
				else
					value = value * 0.9;
			}
			if(value>1000) n++; // 填空2
		}
		
		System.out.println(1.0*n/N);
	}
}