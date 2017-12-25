package Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.*;

public class User implements Serializable {
	private String name;
	private String password;
	private String role;

	public void showMenu() throws IllegalStateException, SQLException {
	}

	public boolean changeUserInfo(String userName, String userPassword, String userRole)
			throws IllegalStateException, SQLException, IOException {
		return false;
	}

	public boolean uploadFile(String ID, String description, String file)
			throws IllegalStateException, SQLException, IOException {
		if ((new Operator(name, password, role)).uploadFile(ID, description, file)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deUser(String userName) throws IllegalStateException, SQLException, IOException {
		return false;
	}
	public boolean deDoc(String filename,String owner) throws IllegalStateException, SQLException, IOException {
		if(DataProcessing.deleteDoc(filename,getName(),owner)) {
			System.out.println(filename +"删除成功");
			return true;
		}
		else {
			System.out.println(filename + "删除失败!");
			return false;
		}
	}
	public Doc[] showFilelist() throws IllegalStateException, SQLException, IOException {
		return DataProcessing.getAllDocs();
	}

	public boolean downloadFile(String num, String downloadpath)
			throws IllegalStateException, SQLException, IOException {
		Doc doc = DataProcessing.searchDoc(num);
		final String SERVER_IP = "127.0.0.1";
		final int SERVER_PORT = 2017;
		Socket client = null;
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		try {
			try {
				client = new Socket(SERVER_IP, SERVER_PORT);
				dos = new DataOutputStream(client.getOutputStream());
				dis = new DataInputStream(client.getInputStream());
				dos.writeInt(2);
				dos.flush();
				dos.writeUTF(doc.getPath());
				dos.flush();
				dos.writeUTF(doc.getOwner());
				dos.flush();
				fos = new FileOutputStream(new File(downloadpath + "\\" + doc.getPath()));
				byte[] sendBytes = new byte[1024];
				while (true) {
					int read = 0;
					read = dis.read(sendBytes);
					if (read == -1)
						break;
					fos.write(sendBytes, 0, read);
					fos.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				if (dos != null)
					dos.close();
				if (dis != null)
					dis.close();
				if (fos != null)
					fos.close();
				if (client != null)
					client.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean changeSelfInfo(String userPassword, String userPassword_1)
			throws IllegalStateException, SQLException, IOException {
		String userName;
		userName = getName();
		if (!userPassword.equals(getPassword())) {
			System.out.println("密码错误!");
			return false;
		}
		if (DataProcessing.updateUser(userName, userPassword_1, getRole(),getName())) {
			System.out.println(userName + " 信息更新成功 !");
			return true;
		} else {
			System.out.println(userName + "信息更新失败!");
			return false;
		}
	}

	public void exitSystem() {
		System.exit(0);
	}

	public void setName(String name) {
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

	public User[] listUser() throws IllegalStateException, SQLException, UnknownHostException, IOException {
		return null;
	}
	public Log[] listLog() throws IllegalStateException, SQLException, UnknownHostException, IOException {
		return DataProcessing.getLog();
	}
	public boolean addUser(String name2, String pass, String role2)
			throws IllegalStateException, SQLException, UnknownHostException, IOException {
		return false;
	}
	public void Exit() throws IOException{
		DataProcessing.userExit(getName());
	}
}