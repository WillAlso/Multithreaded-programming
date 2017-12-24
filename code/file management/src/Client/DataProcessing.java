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
			dis = new DataInputStream(client.getInputStream());
			dos.writeInt(4);
			dos.flush();
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

	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename)
			throws SQLException, IllegalStateException, IOException {

		try {
			client = new Socket(SERVER_IP, SERVER_PORT);
			dos = new DataOutputStream(client.getOutputStream());
			dis = new DataInputStream(client.getInputStream());
			oos = new ObjectOutputStream(client.getOutputStream());
			dos.writeInt(5);
			dos.flush();
			oos.writeObject(new Doc(ID, creator, timestamp, description, filename));
			oos.flush();
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
			dis = new DataInputStream(client.getInputStream());
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
			dis.close();
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
			dis = new DataInputStream(client.getInputStream());
			dos.writeInt(7);
			dos.flush();
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

	public static boolean updateUser(String name, String password, String role)
			throws SQLException, IllegalStateException, IOException {
		try {
			client = new Socket(SERVER_IP, SERVER_PORT);
			dos = new DataOutputStream(client.getOutputStream());
			dis = new DataInputStream(client.getInputStream());
			oos = new ObjectOutputStream(client.getOutputStream());
			dos.writeInt(8);
			dos.flush();
			User user;
			if (role.equals("administrator")) {
				user = new Administrator(name, password, role);
			} else if (role.equals("operator")) {
				user = new Operator(name, password, role);
			} else {
				user = new Browser(name, password, role);
			}
			oos.writeObject(user);
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

	public static boolean insertUser(String name, String password, String role)
			throws SQLException, IllegalStateException, UnknownHostException, IOException {

		try {
			client = new Socket(SERVER_IP, SERVER_PORT);
			dos = new DataOutputStream(client.getOutputStream());
			dis = new DataInputStream(client.getInputStream());
			oos = new ObjectOutputStream(client.getOutputStream());
			dos.writeInt(9);
			dos.flush();
			User user;
			if (role.equals("administrator")) {
				user = new Administrator(name, password, role);
			} else if (role.equals("operator")) {
				user = new Operator(name, password, role);
			} else {
				user = new Browser(name, password, role);
			}
			oos.writeObject(user);
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

	public static boolean deleteUser(String name) throws SQLException, IllegalStateException, IOException {

		try {
			client = new Socket(SERVER_IP, SERVER_PORT);
			dos = new DataOutputStream(client.getOutputStream());
			dos.writeInt(10);
			dos.flush();
			dos.writeUTF(name);
			dos.flush();
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
}
