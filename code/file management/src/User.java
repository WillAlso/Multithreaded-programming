import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class User {
	private String name;
	private String password;
	private String role;
	public void showMenu() {}
	public void showFilelist() {}
	public void downloadFile() {}
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
		boolean flag;
		try {
			flag = DataProcessing.update(userName, userPassword, getRole());
		} catch (IllegalStateException e) {
			System.out.println("Not Connected to Database");
			return;
		} catch (SQLException e) {
			System.out.println("Error in excecuting Query");
			return;
		}
		if(flag) {
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