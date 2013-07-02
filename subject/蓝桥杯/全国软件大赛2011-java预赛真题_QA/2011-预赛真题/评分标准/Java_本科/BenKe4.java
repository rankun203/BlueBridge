
/*
	答案：
	空1： r = d   (2分)
	空2： getMinDistance(lst)   (5分)
    
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
		if(lst==null || lst.size()<2) return Double.MAX_VALUE;
		
		double r = Double.MAX_VALUE;
		MyPoint p0 = lst.remove(0);
		for(int i=0; i<lst.size(); i++)
		{
			MyPoint p = lst.get(i);
			double d = MyPoint.distance(p0,p);
			if(d<r) r = d;  // 填空1
		}
		
		double d2 = getMinDistance(lst); // 填空2
		return d2 < r ? d2 : r;
	}
}


public class BenKe4
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