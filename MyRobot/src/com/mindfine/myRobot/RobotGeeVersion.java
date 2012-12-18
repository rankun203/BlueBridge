public class RobotGeeVersion {
  public static void main(String[] args) {
		Robot r = new Robot();
		///////////////////////////////////////////////////////
		System.out.printf("%.2f", 
				r.turn(movType.T_LEFT).mov(100).turn(movType.T_RIGHT)
				.mov(50).turn(movType.T_RIGHT).mov(10).distance());
		///////////////////////////////////////////////////////
		////由于输入输出流和正则的内容还没掌握 这部分没有实现//////
		///////////////////////////////////////////////////////
	}
}
//转向 向左 向右
enum movType { T_LEFT, T_RIGHT }
//面朝 前 后 左 右
enum Toward { AHEAD, REAR, LEFT, RIGHT }

class Robot {
	//当前坐标
	private int x = 0, y = 0;
	//当前面朝前/后/左/右
	private Toward faceTo = Toward.AHEAD;
	//移动
	public Robot mov(int displacement) {
		switch (faceTo) {
		case AHEAD: 
		case REAR:
			y += (faceTo == Toward.AHEAD ? displacement : -displacement); 
			break;
		case LEFT: 
		case RIGHT:
			x += (faceTo == Toward.RIGHT ? displacement : -displacement); 
			break;
		}
		return this;
	}
	//转向
	public Robot turn(movType m) {
		if(m == movType.T_LEFT) {
			switch(faceTo) {
			case AHEAD: faceTo = Toward.LEFT; break;
			case REAR:  faceTo = Toward.RIGHT; break;
			case LEFT:  faceTo = Toward.REAR; break;
			case RIGHT: faceTo = Toward.AHEAD; break;
			}}
		else {
			switch(faceTo) {
			case AHEAD: faceTo = Toward.RIGHT; break;
			case REAR:  faceTo = Toward.LEFT; break;
			case LEFT:  faceTo = Toward.AHEAD; break;
			case RIGHT: faceTo = Toward.REAR; break;
			}}
		return this;
	}
	//距离
	public double distance() { return Math.sqrt(x * x + y * y);	}
}

