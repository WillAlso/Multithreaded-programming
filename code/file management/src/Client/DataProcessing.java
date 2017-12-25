package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.*;

public class DataProcessing {
	static String SERVER_IP = "127.0.0.1";
	static int SERVER_PORT = 2017;
	static Socket client;
	static DataOutputStream dos;
	static DataInputStream dis;
	static ObjectInputStream ois;
	static ObjectOutputStream oos;

	public static Doc searchDoc(String ID) throws SQLException, IllegalStateException, IOException {
		Doc doc = null;
		try {
			client = new Socket(SERVER_IP, SERVER_PORT);
			dos = new DataOutputStream(client.getOutputStream());
			dos.writeInt(3);
			dos.flush();
			dos.writeUTF(ID);
			dos.flush();
			ois = new ObjectInputStream(client.getInputStream());
			doc = (Doc) ois.readObject();
			return doc;
		} catch (ClassNotFoundException e) {
			return null;
		} finally {
			dos.close();
			ois.close();
			client.close();
		}
	}

	public static Doc[] getAllDocs() throws SQLException, IllegalStateException, IOException {
		int number;
		Doc[] doc;
		try {
			client = new Socket(SERVER_IP, SERVER_PORT);
			dos = new DataOutputStream(client.getOutputStream());
			dos.writeInt(4);
			dos.flush();
			dis = new DataInputStream(client.getInputStream());
			number = dis.readInt();
			doc = new Doc[number];
			ois = new ObjectInputStream(client.getInputStream());
			for (int i = 0; i < number; i++) {
				doc[i] = (Doc) ois.readObject();
			}
			return doc;
		} catch (ClassNotFoundException e) {
			return null;
		} finally {
			dos.close();
			dis.close();
			ois.close();
			client.close();
		}
	}

	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename,String maker)
			throws SQLException, IllegalStateException, IOException {

		try {
			client = new Socket(SERVER_IP, SERVER_PORT);
			dos = new DataOutputStream(client.getOutputStream());
			dos.writeInt(5);
			dos.flush();
			dos.writeUTF(maker);
			dos.flush();
			oos = new ObjectOutputStream(client.getOutputStream());
			oos.writeObject(new Doc(ID, creator, timestamp, description, filename));
			oos.flush();
			dis = new DataInputStream(client.getInputStream());
			if (dis.readBoolean()) {
				return true;
			} else {
				return false;
			}
		} finally {
			dos.close();
			dis.close();
			ois.close();
			client.close();
		}
	}

	public static User searchUser(String name, String password)
			throws SQLException, IllegalStateException, IOException {
		User user = null;
		try {
			client = new Socket("127.0.0.1", 2017);
			dos = new DataOutputStream(client.getOutputStream());
			dos.writeInt(6);
			dos.flush();
			dos.writeUTF(name);
			dos.flush();
			dos.writeUTF(password);
			dos.flush();
			ois = new ObjectInputStream(client.getInputStream());
			user = (User) ois.readObject();
			return user;
		} catch (ClassNotFoundException e) {
			return null;
		} finally {
			dos.close();
			ois.close();
			client.close();
		}
	}

	public static User[] getAllUser() throws SQLException, IllegalStateException, UnknownHostException, IOException {
		int number;
		User[] user;
		try {
			client = new Socket(SERVER_IP, SERVER_PORT);
			dos = new DataOutputStream(client.getOutputStream());
			dos.writeInt(7);
			dos.flush();
			dis = new DataInputStream(client.getInputStream());
			number = dis.readInt();
			user = new User[number];
			ois = new ObjectInputStream(client.getInputStream());
			for (int i = 0; i < number; i++) {
				user[i] = (User) ois.readObject();
			}
			return user;
		} catch (ClassNotFoundException e) {
			return null;
		} finally {
			dos.close();
			dis.close();
			ois.close();
			client.close();
		}
	}

	public static boolean updateUser(String name, String password, String role,String maker)
			throws SQLException, IllegalStateException, IOException {
		try {
			client = new Socket(SERVER_IP, SERVER_PORT);
			dos = new DataOutputStream(client.getOutputStream());
			dos.writeInt(8);
			dos.flush();
			dos.writeUTF(maker);
			dos.flush();
			User user;
			if (role.equals("administrator")) {
				user = new Administrator(name, password, role);
			} else if (role.equals("operator")) {
				user = new Operator(name, password, role);
			} else {
				user = new Browser(name, password, role);
			}
			oos = new ObjectOutputStream(client.getOutputStream());
			oos.writeObject(user);
			oos.flush();
			dis = new DataInputStream(client.getInputStream());
			if (dis.readBoolean()) {
				return true;
			} else {
				return false;
			}
		} finally {
			dos.close();
			dis.close();
			ois.close();
			client.close();
		}

	}

	public static boolean insertUser(String name, String password, String role,String maker)
			throws SQLException, IllegalStateException, UnknownHostException, IOException {

		try {
			client = new Socket(SERVER_IP, SERVER_PORT);
			dos = new DataOutputStream(client.getOutputStream());
			dos.writeInt(9);
			dos.flush();
			dos.writeUTF(maker);
			dos.flush();
			User user;
			if (role.equals("administrator")) {
				user = new Administrator(name, password, role);
			} else if (role.equals("operator")) {
				user = new Operator(name, password, role);
			} else {
				user = new Browser(name, password, role);
			}
			oos = new ObjectOutputStream(client.getOutputStream());
			oos.writeObject(user);
			oos.flush();
			dis = new DataInputStream(client.getInputStream());
			if (dis.readBoolean()) {
				return true;
			} else {
				return false;
			}
		} finally {
			dos.close();
			dis.close();
			client.close();
		}
	}

	public static boolean deleteUser(String name,String maker) throws SQLException, IllegalStateException, IOException {
		try {
			client = new Socket(SERVER_IP, SERVER_PORT);
			dos = new DataOutputStream(client.getOutputStream());
			dos.writeInt(10);
			dos.flush();
			dos.writeUTF(maker);
			dos.flush();
			dos.writeUTF(name);
			dos.flush();
			dis = new DataInputStream(client.getInputStream());
			if (dis.readBoolean()) {
				return true;
			} else {
				return false;
			}
		} finally {
			dos.close();
			dis.close();
			ois.close();
			client.close();
		}
	}
	public static boolean deleteDoc(String name,String owner,String maker) throws SQLException, IllegalStateException, IOException {
		try {
			client = new Socket(SERVER_IP, SERVER_PORT);
			dos = new DataOutputStream(client.getOutputStream());
			dos.writeInt(12);
			dos.flush();
			dos.writeUTF(maker);
			dos.flush();
			dos.writeUTF(name);
			dos.flush();
			dos.writeUTF(owner);
			dos.flush();
			dis = new DataInputStream(client.getInputStream());
			if (dis.readBoolean()) {
				return true;
			} else {
				return false;
			}
		} finally {
			dos.close();
			dis.close();
			client.close();
		}
	}
	public static String sendMessage(String url) throws IOException{
		String t = null;
		try {
			client = new Socket(SERVER_IP, SERVER_PORT);
			dos = new DataOutputStream(client.getOutputStream());
			dos.writeInt(11);
			dos.flush();
			dos.writeUTF(url);
			dos.flush();
			dis = new DataInputStream(client.getInputStream());
			t = dis.readUTF();
		}finally {
			dos.close();
			dis.close();
			client.close();
		}
		return t;
	}
	
	public static Log[] getLog() throws SQLException, IllegalStateException, UnknownHostException, IOException {
		int number;
		Log[] log;
		try {
			client = new Socket(SERVER_IP, SERVER_PORT);
			dos = new DataOutputStream(client.getOutputStream());
			dos.writeInt(13);
			dos.flush();
			dis = new DataInputStream(client.getInputStream());
			number = dis.readInt();
			log = new Log[number];
			ois = new ObjectInputStream(client.getInputStream());
			for (int i = 0; i < number; i++) {
				log[i] = (Log) ois.readObject();
			}
			return log;
		} catch (ClassNotFoundException e) {
			return null;
		} finally {
			dos.close();
			dis.close();
			ois.close();
			client.close();
		}
	}
	public static void userExit(String name) throws IOException{
		try {
			client = new Socket(SERVER_IP, SERVER_PORT);
			dos = new DataOutputStream(client.getOutputStream());
			dos.writeInt(14);
			dos.flush();
			dos.writeUTF(name);
			dos.flush();
		}catch(Exception e){
			System.exit(0);
		}finally {
			dos.close();
			dis.close();
			client.close();
		}
	}
	
}
