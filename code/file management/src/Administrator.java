import java.sql.SQLException;
import java.util.*;

public class Administrator extends User{
	Administrator(String name,String password,String role){
		setName(name);
		setPassword(password);
		setRole(role);
	}
	public boolean changeUserInfo(String userName,String userPassword,String userRole) throws IllegalStateException, SQLException {
		if(DataProcessing.updateUser(userName, userPassword, userRole)) {
			System.out.println(userName +" 信息更新成功 !");
			return true;
		}
		else {
			System.out.println(userName +"信息更新失败!");
			return false;
		}
	}
	public static boolean deUser(String userName) throws IllegalStateException, SQLException {
			if(DataProcessing.deleteUser(userName)) {
				System.out.println(userName +"删除成功");
				return true;
			}
			else {
				System.out.println(userName + "删除失败!");
				return false;
			}
	}
	public static boolean addUser(String userName,String userPassword,String userRole) throws IllegalStateException, SQLException {
		
		if(DataProcessing.insertUser(userName, userPassword, userRole)) {
			System.out.println(userName + "添加成功!");
			return true;
		}
		else {
			System.out.println(userName + "添加失败!");
			return false;
		}
	}
	public static String[] listUser() throws IllegalStateException, SQLException {
		Enumeration<User> e = DataProcessing.getAllUser();
		int cnt = 0;
		while(e.hasMoreElements()){
			User user = e.nextElement();
	        cnt++;
	     }
		String[] temp = new String[cnt];
		Enumeration<User> d = DataProcessing.getAllUser();
		int c = 0;
		while(d.hasMoreElements()){
			User user = d.nextElement();
			temp[c++] = new String(user.getName()+"    "+user.getRole());
		}
		return temp;
	}
	public void showMenu() throws IllegalStateException, SQLException {
		System.out.println("The Administrator's Menu:");
		System.out.println("1,文件列表\n2,下载文件\n3,改变用户信息\n"
				+ "4,删除用户\n5,添加用户\n6,用户列表\n7,显示菜单\n8,退出登录");
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
		}		
		}
	}

}
