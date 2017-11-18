﻿import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;

public class Administrator extends User{
	Administrator(String name,String password,String role){
		setName(name);
		setPassword(password);
		setRole(role);
	}
	public void changeUserInfo() throws IllegalStateException, SQLException {
		String userName;
		String userPassword;
		String userRole;
		System.out.print("请输入用户姓名:");
		Scanner input = new Scanner(System.in);
		userName = input.next();
		System.out.print("\n请输入用户新密码:");
		userPassword = input.next();
		System.out.println("\n请选择用户类型:\n1,Administrator\n2,Operator\n3,Browser");
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
		if(DataProcessing.updateUser(userName, userPassword, userRole)) {
			System.out.println(userName +" 信息更新成功 !");
		}
		else {
			System.out.println(userName +"信息更新失败!");
			return;
		}
	}
	public void deUser() throws IllegalStateException, SQLException {
			String userName;
			System.out.print("请输入用户密码:");
			Scanner input = new Scanner(System.in);
			userName = input.next();
			if(getName().equals(userName)) {
				System.out.println("不能删除自己");
				return;
			}
			if(DataProcessing.deleteUser(userName)) {
				System.out.println(userName +"删除成功");
			}
			else {
				System.out.println(userName + "删除失败!");
				return;
			}
	}
	public void addUser() throws IllegalStateException, SQLException {
		String userName;
		String userPassword;
		String userRole;
		System.out.print("请输入用户姓名:");
		Scanner input = new Scanner(System.in);
		userName = input.next();
		System.out.print("\n请输入用户密码:");
		userPassword = input.next();
		System.out.print("\n请选择用户类型:");
		userRole = input.next();
		if(DataProcessing.insertUser(userName, userPassword, userRole)) {
			System.out.println(userName + "添加成功!");
		}
		else {
			System.out.println(userName + "添加失败!");
		}
	}
	public void listUser() throws IllegalStateException, SQLException {
		Enumeration<User> e = DataProcessing.getAllUser();
		while(e.hasMoreElements()){
			User user = e.nextElement();
	        System.out.println(user.getName()+"\t"+user.getRole());
	     }
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
