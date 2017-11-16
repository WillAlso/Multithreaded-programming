import java.sql.SQLException;
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
		System.out.print("�������û�����:");
		Scanner input = new Scanner(System.in);
		userName = input.next();
		System.out.print("\n�������û�������:");
		userPassword = input.next();
		System.out.println("\n��ѡ���û�����:\n1,Administrator\n2,Operator\n3,Browser");
		int c = input.nextInt();
		switch(c) {
		case 1:
			userRole = "administrator";break;
		case 2:
			userRole = "operator";break;
		case 3:
			userRole = "browser";break;
			default:
				System.out.println("Error!");
				return;
		}
		boolean flag;
		try {
			flag = DataProcessing.update(userName, userPassword, userRole);
		} catch (IllegalStateException e) {
			System.out.println("Not Connected to Database");
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		if(flag) {
			System.out.println(userName +" ��Ϣ���³ɹ� !");
		}
		else {
			System.out.println(userName +"��Ϣ����ʧ��!");
			return;
		}
	}
	public void deUser() {
			String userName;
			System.out.print("�������û�����:");
			Scanner input = new Scanner(System.in);
			userName = input.next();
			boolean flag;
			try {
				flag = DataProcessing.delete(userName);
			} catch (IllegalStateException e) {
				e.printStackTrace();
				return;
			} catch (SQLException e) {
				e.printStackTrace();
				return;
			}
			if(flag) {
				System.out.println(userName +"ɾ���ɹ�");
			}
			else {
				System.out.println(userName + "ɾ��ʧ��!");
				return;
			}
	}
	public void addUser() {
		String userName;
		String userPassword;
		String userRole;
		System.out.print("�������û�����:");
		Scanner input = new Scanner(System.in);
		userName = input.next();
		System.out.print("\n�������û�����:");
		userPassword = input.next();
		System.out.print("\n��ѡ���û�����:");
		userRole = input.next();
		boolean flag;
		try {
			flag = DataProcessing.insert(userName, userPassword, userRole);
		} catch (IllegalStateException e) {
			System.out.println("Not Connected to Database");
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		if(flag) {
			System.out.println(userName + "��ӳɹ�!");
		}
		else {
			System.out.println(userName + "���ʧ��!");
		}
	}
	public void listUser() {
		Enumeration<User> e;
		try {
			e = DataProcessing.getAllUser();
		} catch (IllegalStateException e1) {
			System.out.println("Not Connected to Database");
			return;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		while(e.hasMoreElements()){
			User user = e.nextElement();
	        System.out.println(user.getName()+"\t"+user.getRole());
	     }
	}
	public void showMenu() {
		System.out.println("The Administrator's Menu:");
		System.out.println("1,�ļ��б�\n2,�����ļ�\n3,�ı��û���Ϣ\n"
				+ "4,ɾ���û�\n5,����û�\n6,�û��б�\n7,��ʾ�˵�\n8,�˳���¼");
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
			//exitSystem();break;
			return;
		default:
			System.out.println("����ѡ������!����������:");
			showMenu();
			continue;
		}		
		}
	}

}
