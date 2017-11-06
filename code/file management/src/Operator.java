import java.util.Scanner;

public class Operator extends User{
	Operator(String name,String password,String role){
		setName(name);
		setPassword(password);
		setRole(role);
	}
	public void uploadFile() {
		String file;
		System.out.print("Please the file's name:");
		Scanner input = new Scanner(System.in);
		file = input.next();
		System.out.println(file+"has been upload!");
	}
	public void showMenu() {
		System.out.println("The Operator's Menu:");
		System.out.println("1,showFilelist\n2,downloadFile\n3,uploadFile\n4,showMenu\n5,exitSystem");
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
			uploadFile();break;
		case "4":
			showMenu();break;
		case "5":
			exitSystem();break;
		}
		}
	}

}
