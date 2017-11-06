import java.io.*;
import java.util.*;

public class FileMange {
	public static void main(String[] args) {
		String name;
		String password;
		Console con = System.console();
		Scanner in = new Scanner(System.in);
		do {
			System.out.println("Wecome!\nPlease input your name:");
			name = in.next();
			//name = con.readLine();
			System.out.println("\nPlease input your password:");
			password = in.next();
			//password = String.valueOf(con.readPassword());
		}while(DataProcessing.search(name, password) == null);
		User user = DataProcessing.search(name, password);
		switch(user.getRole()) {
		case "Administrator":
			user = new Administrator(name,password,user.getRole());
			user.showMenu();
			break;
		case "Operator":
			user = new Operator(name,password,user.getRole());
			user.showMenu();
			break;
		case "Browser":
			user = new Browser(name,password,user.getRole());
			user.showMenu();
			break;
		}
	}
	
}
