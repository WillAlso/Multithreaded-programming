package Client;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.*;

public class Administrator extends User{
	public Administrator(String name,String password,String role){
		setName(name);
		setPassword(password);
		setRole(role);
	}
	public boolean changeUserInfo(String userName,String userPassword,String userRole) throws IllegalStateException, SQLException, IOException {
		if(DataProcessing.updateUser(userName, userPassword, userRole,getName())) {
			System.out.println(userName +" 信息更新成功 !");
			return true;
		}
		else {
			System.out.println(userName +"信息更新失败!");
			return false;
		}
	}
	public boolean deUser(String filename) throws IllegalStateException, SQLException, IOException {
			if(DataProcessing.deleteUser(filename,getName())) {
				System.out.println(filename +"删除成功");
				return true;
			}
			else {
				System.out.println(filename + "删除失败!");
				return false;
			}
	}
	public boolean addUser(String userName,String userPassword,String userRole) throws IllegalStateException, SQLException, UnknownHostException, IOException {
		
		if(DataProcessing.insertUser(userName, userPassword, userRole,getName())) {
			System.out.println(userName + "添加成功!");
			return true;
		}
		else {
			System.out.println(userName + "添加失败!");
			return false;
		}
	}
	public User[] listUser() throws IllegalStateException, SQLException, UnknownHostException, IOException {
		return DataProcessing.getAllUser();
	}
}
