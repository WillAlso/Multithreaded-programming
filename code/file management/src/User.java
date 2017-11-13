import java.util.Scanner;

public class User {
	private String name;
	private String password;
	private String role;
	public void showMenu() {}
	public void showFilelist() {
		System.out.println("文件列表:");
	}
	public void downloadFile() {
		String file;
		System.out.print("请输入文件名字:");
		Scanner input = new Scanner(System.in);
		file = input.next();
		System.out.println(file+"下载成功!");
	}
	public void changeSelfInfo() {
		String userName;
		String userPassword;
		userName = getName();
		Scanner input = new Scanner(System.in);
		System.out.print("\n请输入用户旧密码:");
		userPassword = input.next();
		if(!userPassword.equals(getPassword())) {
			System.out.println("密码错误!");
			return;
		}
		System.out.print("\n请输入用户新密码:");
		userPassword = input.next();
		if(DataProcessing.update(userName, userPassword, getRole())) {
			System.out.println(userName +" 信息更新成功 !");
		}
		else {
			System.out.println(userName +"信息更新失败!");
			return;
		}
	}
	public void exitSystem() {
		System.out.println(getName() + "已退出系统!");
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