﻿import java.sql.*;
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
	public boolean deUser(String userName) throws IllegalStateException, SQLException {
			if(DataProcessing.deleteUser(userName)) {
				System.out.println(userName +"删除成功");
				return true;
			}
			else {
				System.out.println(userName + "删除失败!");
				return false;
			}
	}
	public boolean addUser(String userName,String userPassword,String userRole) throws IllegalStateException, SQLException {
		
		if(DataProcessing.insertUser(userName, userPassword, userRole)) {
			System.out.println(userName + "添加成功!");
			return true;
		}
		else {
			System.out.println(userName + "添加失败!");
			return false;
		}
	}
	public User[] listUser() throws IllegalStateException, SQLException {
		return DataProcessing.getAllUser();
	}
}
