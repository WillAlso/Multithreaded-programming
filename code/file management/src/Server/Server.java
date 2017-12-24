package Server;

import java.io.*;
import java.net.*;
import java.sql.*;
import Client.*;

public class Server extends ServerSocket{

	private static Connection con;
	private static Statement st;
	private static boolean connectToDB = false;

	private static final int PORT = 2017;
	private ServerSocket server;
	private Socket client;
	private DataInputStream dis;
	private FileOutputStream fos;
	private FileInputStream fis;
	private DataOutputStream dos;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	String url = "jdbc:mysql://localhost:3306/foruser?characterEncoding=utf-8";
	String username = "root";
	String password = "12345678";
	public Server() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			st = con.createStatement();
			connectToDB = true;
		} catch (SQLException e) {
			connectToDB = false;
			System.out.println("Not Connected to Database");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			try {
				server = new ServerSocket(PORT);
				while (true) {
					client = server.accept();
					Thread t = new Thread(){
						public void run(){
							try{
							dis = new DataInputStream(client.getInputStream());
							int chioce = dis.readInt();
							System.out.println(chioce);
							if (chioce == 1) {// 服务器接收文件
								String fileName = dis.readUTF();
								String folderutf = dis.readUTF();
								File folder = new File("C:\\sql\\" + folderutf);
								if (!folder.exists())
									folder.mkdirs();
								fos = new FileOutputStream(new File("C:\\sql\\" + folderutf + "\\" + fileName));
								byte[] sendBytes = new byte[1024];
								while (true) {
									int read = 0;
									read = dis.read(sendBytes);
									if (read == -1)
										break;
									fos.write(sendBytes, 0, read);
									fos.flush();
								}
								System.out.println("接收文件<" + fileName + ">成功");
								client.close();
							} else if (chioce == 2) {// 服务器发送文件
								dos = new DataOutputStream(client.getOutputStream());
								String fileName = dis.readUTF();
								String folderutf = dis.readUTF();
								File temp = new File("C:\\sql\\" + folderutf + "\\" + fileName);
								fis = new FileInputStream(temp);
								System.out.println(new File("C:\\sql\\" + folderutf + "\\" + fileName).getAbsolutePath());
								byte[] sendBytes = new byte[1024];
								int length = 0;
								while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0) {
									dos.write(sendBytes, 0, length);
									dos.flush();
								}
								System.out.println("发送文件<" + fileName + ">成功");
								client.close();
							} else if (chioce == 3) {// searchDoc
								String ID = dis.readUTF();
								if (!connectToDB)
									throw new IllegalStateException("Not Connected to Database");
								String sql = "SELECT * FROM doc WHERE number = '" + ID + "'";
								ResultSet result = st.executeQuery(sql);
								oos = new ObjectOutputStream(client.getOutputStream());
								if (result.next()) {
									oos.writeObject(new Doc(result.getString("number"), result.getString("owner"),
											result.getTimestamp("timestamp"), result.getString("description"),
											result.getString("filename")));
									oos.flush();
								} else {
									oos.writeObject(null);

								}
								client.close();
							} else if (chioce == 4) {// getAllDocs
								if (!connectToDB)
									throw new IllegalStateException("Not Connected to Database");
								String sql = "SELECT * FROM doc";
								ResultSet result = st.executeQuery(sql);
								result.last();
								int number = result.getRow();
								dos = new DataOutputStream(client.getOutputStream());
								dos.writeInt(number);
								result.first();
								String ID;
								oos = new ObjectOutputStream(client.getOutputStream());
								do {
									ID = result.getString(2);
									String owner = result.getString("owner");
									Timestamp time = result.getTimestamp("timestamp");
									String des = result.getString("description");
									String name = result.getString("filename");

									oos.writeObject(new Doc(ID, owner, time, des, name));
									oos.flush();
								} while (result.next());
								client.close();
							} else if (chioce == 5) {// insertDoc
								ois = new ObjectInputStream(client.getInputStream());
								Doc doc = (Doc) ois.readObject();
								String sql = "INSERT INTO doc(number,owner,timestamp,description,filename) VALUES ('"
										+ doc.getNumber() + "','" + doc.getOwner() + "'," + "?" + ",'" + doc.getDescription()
										+ "','" + doc.getPath() + "')";
								PreparedStatement pstmt = con.prepareStatement(sql);
								pstmt.setTimestamp(1, doc.getTimestamp());
								dos = new DataOutputStream(client.getOutputStream());
								try {
									pstmt.execute();
									dos.writeBoolean(true);
								} catch (Exception e) {
									dos.writeBoolean(false);
								}
								client.close();
							} else if (chioce == 6) {// searchUser
								System.out.println("cahf");
								String name = dis.readUTF();
								String password1 = dis.readUTF();
								System.out.println(name + password1);
								if (!connectToDB)
									throw new IllegalStateException("Not Connected to Database");
								String sql = "SELECT * FROM user WHERE username = '" + name + "'";
								ResultSet result = st.executeQuery(sql);
								User user = null;
								if (result.next()) {
									if (password1.equals(result.getString("password"))) {
										String role = result.getString("role");
										if (role.equals("administrator")) {
											user = new Administrator(name, password1, role);
										} else if (role.equals("operator")) {
											user = new Operator(name, password1, role);
										} else {
											user = new Browser(name, password1, role);
										}
									}
								}
								oos = new ObjectOutputStream(client.getOutputStream());
								oos.writeObject(user);
								oos.flush();
								client.close();
							} else if (chioce == 7) {// getAllUser
								if (!connectToDB)
									throw new IllegalStateException("Not Connected to Database");
								String sql = "SELECT * FROM user";
								ResultSet result = st.executeQuery(sql);
								result.last();
								int number = result.getRow();
								dos = new DataOutputStream(client.getOutputStream());
								dos.writeInt(number);
								result.first();
								oos = new ObjectOutputStream(client.getOutputStream());
								do {
									if (result.getString("role").equals("administrator")) {
										oos.writeObject(new Administrator(result.getString("username"),
												result.getString("password"), result.getString("role")));
										oos.flush();
									} else if (result.getString("role").equals("operator")) {
										oos.writeObject(new Operator(result.getString("username"), result.getString("password"),
												result.getString("role")));
										oos.flush();
									} else {
										oos.writeObject(new Browser(result.getString("username"), result.getString("password"),
												result.getString("role")));
										oos.flush();
									}
								} while (result.next());
								client.close();
							} else if (chioce == 8) {// updateUser
								ois = new ObjectInputStream(client.getInputStream());
								dos = new DataOutputStream(client.getOutputStream());
								User user = (User) ois.readObject();
								if (!connectToDB)
									throw new IllegalStateException("Not Connected to Database");
								String sql = "UPDATE user SET username = '" + user.getName() + "',password = '"
										+ user.getPassword() + "',role = '" + user.getRole() + "' WHERE username = '"
										+ user.getName() + "'";
								try {
									st.execute(sql);
									dos.writeBoolean(true);
								} catch (Exception s) {
									dos.writeBoolean(false);
								}
								client.close();
							} else if (chioce == 9) {// insertUser
								ois = new ObjectInputStream(client.getInputStream());
								dos = new DataOutputStream(client.getOutputStream());
								User user = (User) ois.readObject();
								if (!connectToDB)
									throw new IllegalStateException("Not Connected to Database");
								String sql = "INSERT INTO user (username,password,role) VALUES ('" + user.getName() + "','"
										+ user.getPassword() + "','" + user.getRole() + "')";
								try {
									st.execute(sql);
									dos.writeBoolean(true);
								} catch (Exception e) {
									dos.writeBoolean(false);
								}
								client.close();
							} else if (chioce == 10) {// deleteUser
								if (!connectToDB)
									throw new IllegalStateException("Not Connected to Database");
								String name = dis.readUTF();
								dos = new DataOutputStream(client.getOutputStream());
								String sql = "DELETE FROM user WHERE username = '" + name + "'";
								try {
									st.execute(sql);
									dos.writeBoolean(true);
								} catch (Exception e) {
									dos.writeBoolean(false);
								}
								client.close();
							}
						
						}catch(Exception e){
							System.exit(0);
						}
						}
					};
					t.start();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (dis != null)
					dis.close();
				if (fos != null)
					fos.close();
				if (server != null)
					server.close();
				if (fis != null)
					fis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		try {
			Server server = new Server();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

