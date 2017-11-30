﻿import java.io.*;
import java.sql.*;
import java.util.*;

public class User {
	private String name;
	private String password;
	private String role;
	public void showMenu() throws IllegalStateException, SQLException {}
	public boolean changeUserInfo(String userName,String userPassword,String userRole) throws IllegalStateException, SQLException{return false;}
	public boolean uploadFile(String ID,String description,String file) throws IllegalStateException, SQLException {return false;}
	public boolean deUser(String userName) throws IllegalStateException, SQLException {	return false;}
	public Doc[] showFilelist() throws IllegalStateException, SQLException {
		return DataProcessing.getAllDocs();
	}
	public boolean downloadFile(String num,String downloadpath) throws IllegalStateException, SQLException {
		Doc doc = DataProcessing.searchDoc(num);
		if(doc != null) {
			String sourcepath = "C:\\sql\\"+doc.getOwner();
			File sourcefile = new File(sourcepath+"\\"+doc.getPath());
			System.out.println(sourcefile.getAbsolutePath());
			File fin = new File(sourcepath+"\\"+doc.getPath());
			String nameed = fin.getName().substring(fin.getName().lastIndexOf(".")+1);
			File fout = new File(downloadpath+"\\"+doc.getPath());
			if(fout.exists()){
				return false;
			}
			FileInputStream is;
			FileOutputStream os;
			double len = (double)(fin.length())/50;
			System.out.println(len);
			System.out.println();
			try {
				is = new FileInputStream(fin);
				os = new FileOutputStream(fout);
				byte buf[] = new byte[1024];
				int len_t = 0;
				while((len_t=is.read(buf))!=-1) {  
			           os.write(buf,0,len_t);  
			           os.flush();  
			    }  
				os.close();
				is.close();
				System.out.println(doc.getPath()+"下载成功!\n");
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("文件操作错误!\n");
				return false;
			}
		}
		else
			System.out.println("下载失败!");
		return false;
	}
	public boolean changeSelfInfo(String userPassword,String userPassword_1) throws IllegalStateException, SQLException {
		String userName;
		userName = getName();
		if(!userPassword.equals(getPassword())) {
			System.out.println("密码错误!");
			return false;
		}
		if(DataProcessing.updateUser(userName, userPassword_1, getRole())) {
			System.out.println(userName +" 信息更新成功 !");
			return true;
		}
		else {
			System.out.println(userName +"信息更新失败!");
			return false;
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
	public User[] listUser() throws IllegalStateException, SQLException {
		return null;
	}
	public boolean addUser(String name2, String pass, String role2) throws IllegalStateException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
}