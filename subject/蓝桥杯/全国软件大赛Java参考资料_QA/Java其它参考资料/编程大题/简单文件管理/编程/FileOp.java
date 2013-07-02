import java.io.*;

class FileOp {
	public static void main(String args[]) 
	{
		try	{
			while(run()!=0);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static int run() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int c = -1;
		while (c != 0) {
			System.out.println("-----------------");
			System.out.println("1. 创建目录");
			System.out.println("2. 删除目录");
			System.out.println("3. 创建文件");
			System.out.println("4. 删除文件");
			System.out.println("0. 退出");
			System.out.println("-----------------");			
			System.out.print("请输入你的选择：");
			try {
				c = Integer.parseInt(br.readLine().trim());
			} catch (Exception e) {
				System.out.println("无效的选择");
				continue;
			}
			switch (c) 
			{
				case 0:
					System.out.println("再见！");
					return 0;
				case 1:
					System.out.print("请输入要创建的目录名：");
					createDirectory(br.readLine());
					break;					
				case 2:
					System.out.print("请输入要删除的目录名：");
					delDirectory(br.readLine());
					break;
				case 3:
					System.out.print("请输入要创建的文件名：");
					createFile(br.readLine());
					break;					
				case 4:
					System.out.print("请输入要删除的文件名：");
					delFile(br.readLine());
					break;	
				default:
					System.err.println("无效的选择");
					continue;
			}	
			System.out.println();			
		} 		
		
		return 1;
	}
		
	// 删除文件夹
	public static void delDirectory(String folderPath) 
	{
		System.out.println("等待考生完成！");
	}
	
	// 删除指定的文件
	public static void delFile(String name)
	{
		System.out.println("等待考生完成！");
	}
		
	//创建文件
	public static void createFile(String name) throws IOException
	{
		System.out.println("等待考生完成！");
	}
	
	//创建目录	
	public static void createDirectory(String name)
	{
		System.out.println("等待考生完成！");
	}
}

