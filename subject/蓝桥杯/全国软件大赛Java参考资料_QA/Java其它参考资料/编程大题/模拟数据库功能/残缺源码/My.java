

import java.io.*;
import java.util.*;


// 代表每行数据
class MyRow
{
	private String name;    // 名字
	private double length;  // 长度
	private double weight;  // 重量
	private double power;   // 威力
	private double price;   // 价格
	
	public MyRow(String x)
	{
		String[] ss = x.split("\t");
		name = ss[0].trim();
		length = Double.parseDouble(ss[1]);
		weight = Double.parseDouble(ss[2]);
		power = Double.parseDouble(ss[3]);
		price = Double.parseDouble(ss[4]);
	}
	
	public String toString()
	{
		return name + "\t" + length + "\t" + weight + "\t" + power + "\t" + price;
	}
	
	public String getName() { return name; }
	public double getLength() { return length; }
	public double getWeight() { return weight; }
	public double getPower() { return power; }
	public double getPrice() { return price; }
}

// 代表所有数据
class MyData
{
	// 内部类，“裁判”类，用于裁决Vector中的对象的比较大小问题	
	class CaiPan implements Comparator
	{
		private int type;
		
		public CaiPan(int type)
		{
			this.type = type;
		}
		
		public int compare(Object o1, Object o2)	
		{
			if(!(o1 instanceof MyRow)) return 0;
			if(!(o2 instanceof MyRow)) return 0;
			
			MyRow r1 = (MyRow)o1;
			MyRow r2 = (MyRow)o2;
			
			switch(type){
			case 1:
				return Double.compare(r1.getLength(),r2.getLength());
			case 2:
				return Double.compare(r1.getWeight(),r2.getWeight());
			case 3:
				return Double.compare(r1.getPower(),r2.getPower());
			case 4:
				return Double.compare(r1.getPrice(),r2.getPrice());
			default:
				return 0;
			}
		}
	}
		
		
	private Vector _v = new Vector();
	
	public void show()
	{
		System.out.println("................................");
		System.out.println("名称\t长度\t重量\t威力\t价格");
		for(int i=0; i<_v.size(); i++){
			System.out.println(_v.get(i));
		}
		System.out.println("................................");
	}
	
	public boolean load(String x)
	{
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(x)));
			_v.clear();
			br.readLine();  // 第一行不要
			for(;;){
				String s = br.readLine();
				if(s==null) break;
				MyRow row = new MyRow(s);
				_v.add(row);
			}		
			
			show();
			
			return true;	
		}
		catch(Exception e){
			//e.printStackTrace();
			return false;
		}
	}
	
	public boolean sort(String x)
	{
		if(x.equals("length")){
			Collections.sort(_v, new CaiPan(1));
			show();
			return true;
		}
		if(x.equals("weight")){
			Collections.sort(_v, new CaiPan(2));
			show();
			return true;
		}
		if(x.equals("power")){
			Collections.sort(_v, new CaiPan(3));
			show();
			return true;
		}
		if(x.equals("price")){
			Collections.sort(_v, new CaiPan(4));
			show();
			return true;
		}
		
		return false;
	}
	
	// 初步解析命令
	public boolean select(String x)
	{
		Vector sort = new Vector();  // 显示的字段
		Vector where = new Vector();  // 过滤的条件语句
		
		String[] ss = x.split(" ");
		
		Vector t = sort;
		for(int i=0; i<ss.length; i++){
			if(ss[i].length()==0) continue;  // 防止多个空格
			if(ss[i].equals("where")){
				t = where;
				continue;
			}
			t.add(ss[i]);
		}
		
		if(sort.size()==0) return false;  // 字段必须指定
		if(sort.size()>5) return false;  // 字段太多
		
		return select(sort, where); 
	}	
	
	// 执行select任务
	public boolean select(List sort, List where)
	{
	try{
		System.out.println("................................");
		//输出标题
		for(int k=0; k<sort.size(); k++){
			if(sort.get(k).equals("name"))
				System.out.print("姓名\t");
			else if(sort.get(k).equals("length"))
				System.out.print("长度\t");
			else if(sort.get(k).equals("weight"))
				System.out.print("重量\t");
			else if(sort.get(k).equals("power"))
				System.out.print("威力\t");
			else if(sort.get(k).equals("price"))
				System.out.print("价格\t");
			else if(sort.get(k).equals("*"))
				System.out.print("名称\t长度\t重量\t威力\t价格\t");
			else
				return false;
		}// 枚举sort
		System.out.println("");
		
		
		//输出内容
		for(int i=0; i<_v.size(); i++){
			MyRow row = (MyRow)_v.get(i);
			if(checkWhere(row, where)){
				for(int k=0; k<sort.size(); k++){
					if(sort.get(k).equals("name"))
						System.out.print(row.getName() + "\t");
					else if(sort.get(k).equals("length"))
						System.out.print(row.getLength() + "\t");
					else if(sort.get(k).equals("weight"))
						System.out.print(row.getLength() + "\t");
					else if(sort.get(k).equals("power"))
						System.out.print(row.getLength() + "\t");
					else if(sort.get(k).equals("price"))
						System.out.print(row.getLength() + "\t");
					else if(sort.get(k).equals("*"))
						System.out.print(row + "\t");
					else
						return false;
				}// 枚举sort
				System.out.println("");
			}//检查过滤条件	
		}//对每个行处理
		
		System.out.println("................................");
		return true;
	}
	catch(Exception e){
		//e.printStackTrace();
		return false;
	}
	}
	
	
	// 返回true 则该行记录显示，返回false,则不显示
	public boolean checkWhere(MyRow row, List where) throws Exception
	{
		if(where.size()==0) return true;
		System.out.println("检查过滤条件的功能等待考生完成！");
		return false;
	}
}

// 负责解释用户输入的命令
class MyCommand
{
	private MyData data;
	
	public MyCommand(MyData x)
	{
		data = x;
	}
	
	public boolean execute(String x)
	{
		int d = x.indexOf(" ");  // 找第一个空格的位置
		if(d<0) return false;
		
		String x1 = x.substring(0,d);  
		String x2 = x.substring(d+1);  
		
		if(x1.equals("load")){
			if(!data.load(x2.trim()))
				System.out.println("装入文件出错！");
			return true;
		}
			
		if(x1.equals("sort"))
			return data.sort(x2.trim());
		
		if(x1.equals("select"))
			return data.select(x2);
		
		return false;
	}
}


public class My
{
	private static BufferedReader br_keyboard;  
	
	static
	{
		br_keyboard = new BufferedReader(new InputStreamReader(System.in));  // 将它用于从键盘读入
	}
	
	public static void main(String[] args) throws Exception
	{
		MyData data = new MyData();
		MyCommand cmd = new MyCommand(data);  // cmd 服务于 data
		
		for(;;){
			System.out.print("请输入命令(输入help显示帮助信息)：");
			String s = br_keyboard.readLine();
			
			if(s.equals("exit")) break;
			
			if(s.equals("help")){
				System.out.println("----------------------------");
				System.out.println("load data.txt");
				System.out.println("从当前目录装入文件data.txt，并显示");
				System.out.println("sort weight");
				System.out.println("按“重量”排序，并显示");
				System.out.println("类似地，还可以是 sort length, sort price，sort power等");
				System.out.println("select weight length");
				System.out.println("只显示 重量，长度两列");
				System.out.println("select weight length where price > 50");
				System.out.println("只显示 重量，长度两列, 只包含价格 > 50 的行");
				System.out.println("select * where price>50 length<30");
				System.out.println("显示所有列, 只包含价格>50 且 长度<30 的行");
				System.out.println("其它的组合，从上边类推");
				System.out.println("exit");
				System.out.println("退出程序");
				System.out.println("----------------------------");
				continue;
			}
			
			if(!cmd.execute(s)){
				System.out.println("无效的命令");
			}
		}
		
	}
}