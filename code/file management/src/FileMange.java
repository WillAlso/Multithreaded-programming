import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class FileMange {
	public static void main(String[] args){
		String name;
		String password;
		Console con = System.console();
		Scanner in = new Scanner(System.in);
		try {
			do {
				if(con != null) {
					name = con.readLine("请输入用户姓名:");
					password = new String(con.readPassword("请输入用户密码:"));
				}
				else {
					System.out.print("请输入用户姓名:");
					name = in.next();
					System.out.print("\n请输入用户密码:");
					password = in.next();
				}
				User user = DataProcessing.searchUser(name, password);
				if(user == null) {
					System.out.println("用户不存在！");
					continue;
				}
				switch(user.getRole()) {
				case "administrator":
					user = new Administrator(name,password,user.getRole());
					user.showMenu();
					break;
				case "operator":
					user = new Operator(name,password,user.getRole());
					user.showMenu();
					break;
				case "browser":
					user = new Browser(name,password,user.getRole());
					user.showMenu();
					break;
				}
			}while(true);
		}catch(SQLException b) {
			System.out.println("Not Connected to Database");
		}catch(IllegalStateException e) {
			System.out.println("Error in excecuting Query");
		}
	}
	
}
