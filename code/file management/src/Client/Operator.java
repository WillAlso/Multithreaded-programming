package Client;

import java.io.*;
import java.sql.*;
import java.net.*;

public class Operator extends User {
	public Operator(String name, String password, String role) {
		setName(name);
		setPassword(password);
		setRole(role);
	}

	public boolean uploadFile(String ID, String description, String file)
			throws IllegalStateException, SQLException, IOException {
		String SERVER_IP = "127.0.0.1";
		int SERVER_PORT = 2017;
		Socket client = null;
		FileInputStream fis = null;
		DataOutputStream dos = null;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		File fin = new File(file);
		if (DataProcessing.insertDoc(ID, getName(), timestamp, description, fin.getName(),getName())) {
			try {
				try {
					client = new Socket(SERVER_IP, SERVER_PORT);
					fis = new FileInputStream(fin);
					dos = new DataOutputStream(client.getOutputStream());
					dos.writeInt(1);
					dos.flush();
					dos.writeUTF(fin.getName());
					dos.flush();
					dos.writeUTF(getName());
					dos.flush();
					byte[] sendBytes = new byte[1024];
					int length = 0;
					while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0) {
						dos.write(sendBytes, 0, length);
						dos.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				} finally {
					if (fis != null)
						fis.close();
					if (dos != null)
						dos.close();
					client.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return false;
		}
		return true;
	}
}
