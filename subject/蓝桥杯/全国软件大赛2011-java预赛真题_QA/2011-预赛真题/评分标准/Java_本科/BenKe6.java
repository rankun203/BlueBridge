
/*
  答案：
  空1： new Cell(a.row, a.col+1, a)     (2分)
  空2： dest.add(c[i])                  (8分)
  空3： a = a.from                      (9分)
  
  如不能确定，代入试验
*/

import java.util.*;

public class BenKe6
{
	class Cell
	{
		private int row;
		private int col;
		private Cell from;
		
		public Cell(int row, int col, Cell from)
		{
			this.row = row;
			this.col = col;
			this.from = from;
		}
	}
		
	char[][] maze = 
	{{'#','#','#','#','B','#','#','#','#','#','#','#'},
	{'#','#','#','#','.','.','.','.','#','#','#','#'},
	{'#','#','#','#','.','#','#','#','#','.','.','#'},
	{'#','.','.','.','.','#','#','#','#','#','.','#'},
	{'#','.','#','#','#','#','#','.','#','#','.','#'},
	{'#','.','#','#','#','#','#','.','#','#','.','#'},
	{'#','.','#','#','.','.','.','.','.','.','.','#'},
	{'#','.','#','#','.','#','#','#','.','#','.','#'},
	{'#','.','.','.','.','#','#','#','.','#','.','#'},
	{'#','#','.','#','.','#','#','#','.','#','.','A'},
	{'#','#','.','#','#','#','.','.','.','#','#','#'},
	{'#','#','#','#','#','#','#','#','#','#','#','#'}};
	
	public void show()
	{
		for(int i=0; i<maze.length; i++)
		{
			for(int j=0; j<maze[i].length; j++) 
				System.out.print(" " + maze[i][j]);
			System.out.println();
		}
	}
	
	//把与from集合中相邻的可染色节点染色，被染色节点记入 dest
	//一旦发现出口将被染色，则返回当前的"传播源"节点
	public Cell colorCell(Set<Cell> from, Set<Cell> dest)
	{
		Iterator<Cell> it = from.iterator();
		while(it.hasNext())
		{
			Cell a = it.next();
			Cell[] c = new Cell[4];
			c[0] = new Cell(a.row-1, a.col, a);
			c[1] = new Cell(a.row, a.col-1, a);
			c[2] = new Cell(a.row+1, a.col, a);
			c[3] = new Cell(a.row, a.col+1, a);  // 填空1
						
			for(int i=0; i<4; i++)
			{
				if(c[i].row < 0 || c[i].row >= maze.length) continue;
				if(c[i].col < 0 || c[i].col >= maze[0].length) continue;
				
				char x = maze[c[i].row][c[i].col];
				if(x=='B') return a;
				if(x=='.') 
				{
					maze[c[i].row][c[i].col] = '?';
					dest.add(c[i]);  // 填空2
				}
			}
		}
		return null;
	}
	
	public void resolve()
	{
		Set<Cell> set = new HashSet<Cell>();
		set.add(new Cell(9,11,null));
		
		for(;;)
		{
			Set<Cell> set1 = new HashSet<Cell>();
			Cell a = colorCell(set, set1);
			if(a!=null)
			{
				System.out.println("找到解！");
				while(a!=null)
				{
					maze[a.row][a.col] = '+';
					a = a.from;  // 填空3
				}
				break;
			}
			if(set1.isEmpty())
			{
				System.out.println("无解！");
				break;
			}
			set = set1;
		}		
	}
	
	public static void main(String[] args)
	{
		BenKe6 m = new BenKe6();
		m.show();
		m.resolve();
		m.show();	
	}
}
