import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class FileMange {
	public static void main(String[] args) {
		String name;
		String password;
		Console con = System.console();
		Scanner in = new Scanner(System.in);
		do {
			System.out.print("�������û�����:");
			name = in.next();
			//name = con.readLine();
			System.out.print("\n�������û�����:");
			password = in.next();
			//password = String.valueOf(con.readPassword());
			User user;
			try {
				user = DataProcessing.search(name, password);
			} catch (IllegalStateException e) {
				e.printStackTrace();
				continue;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
			if(user == null) {
				System.out.println("�û������ڣ�");
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
	}
	
}
