import java.util.Scanner;

public class Operator extends User{
	Operator(String name,String password,String role){
		setName(name);
		setPassword(password);
		setRole(role);
	}
	public void uploadFile() {
		String file;
		System.out.print("�������ϴ��ļ�:");
		Scanner input = new Scanner(System.in);
		file = input.next();
		System.out.println(file+"�ϴ��ɹ�!");
	}
	public void showMenu() {
		System.out.println("The Operator's Menu:");
		System.out.println("1,�ļ��б�\n2,�����ļ�\n3,�ϴ��ļ�\n4,��ʾ�˵�\n5,������Ϣ\n6,�˳���¼");
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
			changeSelfInfo();break;
		case "6":
			//exitSystem();break;
			return;
		}
		}
	}

}
