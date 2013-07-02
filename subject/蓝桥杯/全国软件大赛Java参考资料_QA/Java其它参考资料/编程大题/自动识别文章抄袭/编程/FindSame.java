
import java.io.*;
import java.util.*;

public class FindSame
{
	public static boolean toFind(String s1, String s2)
	{
		return false;
	}
	
	public static void main(String[] args) throws Exception
	{	
		String s1 = "";
		String s2 = "";
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("a.txt")));
		for(;;)
		{
			String s = br.readLine();
			if(s==null) break;
			s1 += s;
		}
		br.close();
		
		
		if(!toFind(s1, s2))
			System.out.println("找不到貌似抄袭部分");
	}
}