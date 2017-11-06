import java.util.Enumeration;
import java.util.Scanner;

public class Administrator extends User{
	Administrator(String name,String password,String role){
		setName(name);
		setPassword(password);
		setRole(role);
	}
	public void changeUserInfo() {
		String userName;
		String userPassword;
		String userRole;
		System.out.print("Please input the name:");
		Scanner input = new Scanner(System.in);
		userName = input.next();
		userPassword = input.next();
		userRole = input.next();
		if(DataProcessing.update(userName, userPassword, userRole)) {
			System.out.println(userName +" has been update");
		}
		else {
			System.out.println("Update is unsuccessful!");
			return;
		}
	}
	public void deUser() {
			String userName;
			System.out.print("Please input the name:");
			Scanner input = new Scanner(System.in);
			userName = input.next();
			if(DataProcessing.delete(userName)) {
				System.out.println(userName +" has been deleted");
			}
			else {
				System.out.println("Delete is unsuccessful!");
				return;
			}
	}
	public void addUser() {
		String userName;
		String userPassword;
		String userRole;
		System.out.print("Please input the name:");
		Scanner input = new Scanner(System.in);
		userName = input.next();
		System.out.print("\nPlease input the password:");
		userPassword = input.next();
		System.out.print("\nPlease input the role:");
		userRole = input.next();
		if(DataProcessing.insert(userName, userPassword, userRole)) {
			System.out.println(userName + " has been added!");
		}
		else {
			System.out.println(userName + " hasn't been added!");
		}
	}
	public void listUser() {
		Enumeration<User> e = DataProcessing.getAllUser();
		while(e.hasMoreElements()){
			User user = e.nextElement();
	         System.out.println(user.getName()+"\t"+user.getRole());
	     }
	}
	public void showMenu() {
		System.out.println("The Administrator's Menu:");
		System.out.println("1,showFilelist\n2,downloadFile\n3,changeUserInfo\n4,deUser\n5,addUser\n6,listUser\n7,showMenu\n8,exitSystem");
		String c;
		Scanner input = new Scanner(System.in);
		while(true) {
		c = input.next();
		switch(c) {
		case "1":
			showFilelist();break;
		case "2":
			downloadFile();break;
		case "3":
			changeUserInfo();break;
		case "4":
			deUser();break;
		case "5":
			addUser();break;
		case "6":
			listUser();break;
		case "7":
			showMenu();break;
		case "8":
			exitSystem();break;
		}		
		}
	}

}
