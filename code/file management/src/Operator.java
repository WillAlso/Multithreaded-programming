import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Operator extends User{
	Operator(String name,String password,String role){
		setName(name);
		setPassword(password);
		setRole(role);
	}
	public void uploadFile() {}
	public void showMenu() {
		System.out.println("The Operator's Menu:");
		System.out.println("1,文件列表\n2,下载文件\n3,上传文件\n4,显示菜单\n5,更改信息\n6,退出登录");
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
		default:
			System.out.println("输入选项有误!请重新输入:");
			showMenu();
			continue;
		}
		}
	}

}
