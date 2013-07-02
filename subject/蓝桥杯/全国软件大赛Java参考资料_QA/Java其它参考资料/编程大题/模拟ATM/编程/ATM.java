import java.io.*;

public class ATM {

	private Account acc;
 
	private File dataFile;
	private FileWriter fw;
	private BufferedWriter bw;
 
	private String filePath = "./data.txt";
 
	public ATM() {
		this.acc = new Account();
		try {
			this.dataFile = new File(this.filePath);
			if (!this.dataFile.exists()) {
				this.dataFile.createNewFile(); 
			}
			this.fw = new FileWriter(this.filePath);
			this.bw = new BufferedWriter(this.fw);
		} catch (IOException io) {
			System.err.println("无法打开文件");
			io.printStackTrace(); 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}

	public static void main(String[] args) {
		new ATM().interact();
	}
 
	public void interact() {
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("帐号: ");
			String temp = br.readLine();
			System.out.println("密码: ");
			String temp2;
			boolean flag = true;
			int i = 0;
			

		}catch(Exception e){
			System.out.println(e);
		}

	}  
}


class Account {
 
	private long accNo = 123456;
	private String pass = "666666";
	private long balance = 10000;
 
	public Account() {
	}
 
	public boolean isValid(long accNo, String pass) {
		return (this.accNo == accNo) && (pass.equals(this.pass));
	}
 
	public void changePassword(String oldPass, String password) {
		if (!oldPass.equals(this.pass)) {
			System.err.println("密码错误！");
			return; 
		}
		if (password.length() < 6) {
			System.err.println("请输入6位密码");
			return; 
		}
		if (password.equals(this.pass)) {
			System.err.println("新密码不能与原始密码相同");
			return; 
		}
		this.pass = password;  
	}
	
	public long balanceInquery() {
		return this.balance; 
	}
	
	public void withdraw(long amount) {
		if (amount > 5000 || amount < 0) {
		System.err.println("取款范围: $0-$5000");
		return; 
	}
	if ((amount % 100) != 0) {
		System.err.println("取款金额必须是100的倍数！");
		return; 
	}
	long newBalance = this.balance - amount;
	if (newBalance < 0) {
		System.err.println("账户余额不足！");
		return; 
	}
	this.balance = newBalance;
	}
	
	public void deposit(long amount) {
		if (amount < 0) {
			System.err.println("存款不能是负数！");
			return; 
		}
		this.balance += amount;
	}
	
	public String toString1() {
		return ("帐号: " + this.accNo + "\n" + "密码: " + this.pass + "\n" + "余额: " + this.balance);
	}
} 