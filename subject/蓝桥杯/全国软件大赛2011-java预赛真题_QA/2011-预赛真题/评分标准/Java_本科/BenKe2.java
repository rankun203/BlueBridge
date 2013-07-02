
/*
  答案：
  空1： return false
  空2： isPrime(i+2) && i+2<n
  
  空1： 1分
  空2： 3分
  
  注意：
  如空2 回答：isPrime(i+2)  可给2分
  
  注意所有的逻辑等价格式：
  例如： 
  isPrime(i+2) && i<n-2
  isPrime(2+i) && i<n-2
  
  注意另外一个偏僻的思路：
  (n-2>1 && isPrime(i-2))
  
  注意：仅回答 isPrime(i-2) 不给分
*/


public class BenKe2
{
	public static boolean isPrime(int x)
	{
		for(int i=2; i<=x/2; i++)
		{
			if(x%i==0) return false; //填空1
		}
		return true;
	}
	
	public static int twinPrimeNum(int n)
	{
		int sum = 0;
		for(int i=2; i<n; i++)
		{
			if(isPrime(i) && isPrime(i+2) && i+2<n) sum++; //填空2
		}
		
		return sum;
	}
	
	public static void main(String[] args)
	{
		System.out.println(twinPrimeNum(10000));  //205
	}
}