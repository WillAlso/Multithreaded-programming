import java.util.Scanner;

public class User {
	private String name;
	private String password;
	private String role;
	public void showMenu() {}
	public void showFilelist() {
		System.out.println("�ļ��б�:");
	}
	public void downloadFile() {
		String file;
		System.out.print("�������ļ�����:");
		Scanner input = new Scanner(System.in);
		file = input.next();
		System.out.println(file+"���سɹ�!");
	}
	public void changeSelfInfo() {
		String userName;
		String userPassword;
		userName = getName();
		Scanner input = new Scanner(System.in);
		System.out.print("\n�������û�������:");
		userPassword = input.next();
		if(!userPassword.equals(getPassword())) {
			System.out.println("�������!");
			return;
		}
		System.out.print("\n�������û�������:");
		userPassword = input.next();
		if(DataProcessing.update(userName, userPassword, getRole())) {
			System.out.println(userName +" ��Ϣ���³ɹ� !");
		}
		else {
			System.out.println(userName +"��Ϣ����ʧ��!");
			return;
		}
	}
	public void exitSystem() {
		System.out.println(getName() + "���˳�ϵͳ!");
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