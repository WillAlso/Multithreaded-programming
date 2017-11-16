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
			if(con != null) {
				name = con.readLine("�������û�����:");
				password = new String(con.readPassword("�������û�����:"));
			}
			else {
				System.out.print("�������û�����:");
				name = in.next();
				System.out.print("\n�������û�����:");
				password = in.next();
			}
			User user;
			try {
				user = DataProcessing.search(name, password);
			} catch (IllegalStateException e) {
				System.out.println("Not Connected to Database");
				return;
			} catch (SQLException e) {
				System.out.println("Error in excecuting Query");
				return;
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
