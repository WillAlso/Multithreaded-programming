import java.util.Scanner;

public class Browser extends User{
	Browser(String name,String password,String role){
		setName(name);
		setPassword(password);
		setRole(role);
	}
	public void showMenu() {
		System.out.println("The Browser's Menu:");
		System.out.println("1,showFilelist\n2,downloadFile\n3,showMenu\n4,exitSystem");
		Scanner input = new Scanner(System.in);
		String c;
		while(true) {
		c = input.next();
		switch(c) {
		case "1":
			showFilelist();break;
		case "2":
			downloadFile();break;
		case "3":
			showMenu();break;
		case "4":
			exitSystem();break;
		}	
		}
	}

}
