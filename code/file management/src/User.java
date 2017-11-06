import java.util.Scanner;

public class User {
	private String name;
	private String password;
	private String role;
	public void showMenu() {}
	public void showFilelist() {
		System.out.println("The file list:");
	}
	public void downloadFile() {
		String file;
		System.out.print("Please the file's name:");
		Scanner input = new Scanner(System.in);
		file = input.next();
		System.out.println(file+" has been downloaded!");
	}
	public void changeSelfInfo() {}
	public void exitSystem() {
		System.out.println("You has exit system!");
		System.exit(0);
	}
	public void setName(String name){
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public String getRole() {
		return role;
	}
	

}