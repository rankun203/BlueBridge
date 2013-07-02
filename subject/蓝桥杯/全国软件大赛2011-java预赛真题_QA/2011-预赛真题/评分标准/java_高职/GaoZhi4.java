
/*
  参考答案：
  空1： Double.MAX_VALUE  （2分）
  空2： r = d             （4分）
  
  对空1， 若填 999999, 1E20 等较大的数字者，可以给1分
  
*/

import java.util.*;

class MyPoint
{
	private double x;  // 横坐标
	private double y;  // 纵坐标
		
	public MyPoint(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public static double distance(MyPoint p1, MyPoint p2)
	{
		double dx = p1.x - p2.x;
		double dy = p1.y - p2.y;		
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	/*
	  lst中含有若干个点的坐标
	  返回其中距离最小的点的距离
	*/
	public static double getMinDistance(List<MyPoint> lst)
	{
		double r = Double.MAX_VALUE;  // 填空1
		int n = lst.size();
		for(int i=0; i<n; i++)
		{
			for(int j=i+1; j<n; j++)
			{
				MyPoint p1 = lst.get(i);
				MyPoint p2 = lst.get(j);
				
				double d = MyPoint.distance(p1,p2);
				if( d < r ) r = d; // 填空2
			}
		}
		return r;		
	}
}


public class GaoZhi4
{

	public static void main(String[] args)
	{
		List<MyPoint> t = new Vector<MyPoint>();
		t.add(new MyPoint(1,1));
		t.add(new MyPoint(2,2));
		t.add(new MyPoint(3,5.1));
		t.add(new MyPoint(2.3,1.5));
		
		System.out.println(MyPoint.getMinDistance(t));		
	}
}