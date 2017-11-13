import java.io.*;
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
			if(DataProcessing.search(name, password) == null) {
				System.out.println("�û������ڣ�");
				continue;
			}
			User user = DataProcessing.search(name, password);
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
