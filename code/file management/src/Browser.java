import java.util.Scanner;

public class Browser extends User{
	Browser(String name,String password,String role){
		setName(name);
		setPassword(password);
		setRole(role);
	}
	public void showMenu() {
		System.out.println("The Browser's Menu:");
		System.out.println("1,文件列表\n2,下载文件\n3,显示菜单\n4,更改信息\n5,退出登录");
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
			changeSelfInfo();break;
		case "5":
			//exitSystem();break;
			return;
		}	
		}
	}

}
