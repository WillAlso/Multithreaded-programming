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
								String maker = dis.readUTF();
								System.out.println(maker);
								Timestamp timestamp = new Timestamp(System.currentTimeMillis());
								ois = new ObjectInputStream(client.getInputStream());
								Doc doc = (Doc) ois.readObject();
								String made = doc.getPath();
								String event = "上传文件";
								String sql = "INSERT INTO doc(number,owner,timestamp,description,filename) VALUES ('"
										+ doc.getNumber() + "','" + doc.getOwner() + "'," + "?" + ",'" + doc.getDescription()
										+ "','" + doc.getPath() + "')";
								String sql1 = "INSERT INTO log(maker,made,event,time) VALUES('"+maker+"','"+made+"','"+event+"',"+"?)";
								PreparedStatement pstmt = con.prepareStatement(sql);
								pstmt.setTimestamp(1, doc.getTimestamp());
								PreparedStatement pstmt1 = con.prepareStatement(sql1);
								pstmt1.setTimestamp(1, timestamp);
								dos = new DataOutputStream(client.getOutputStream());
								try {
									pstmt.execute();
									pstmt1.execute();
									dos.writeBoolean(true);
								} catch (Exception e) {
									dos.writeBoolean(false);
								}
								client.close();
							} else if (chioce == 6) {// searchUser
								String name = dis.readUTF();
								String password1 = dis.readUTF();
								String maker = new String(name);
								String made = new String(name);
								String event = "登录";
								Timestamp timestamp = new Timestamp(System.currentTimeMillis());
								String sql1 = "INSERT INTO log(maker,made,event,time) VALUES('"+maker+"','"+made+"','"+event+"',"+"?)";
								System.out.println(name + password1);
								if (!connectToDB)
									throw new IllegalStateException("Not Connected to Database");
								String sql = "SELECT * FROM user WHERE username = '" + name + "'";
								
								PreparedStatement pstmt1 = con.prepareStatement(sql1);
								pstmt1.setTimestamp(1, timestamp);
								pstmt1.execute();
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
								String maker = dis.readUTF();
								ois = new ObjectInputStream(client.getInputStream());
								dos = new DataOutputStream(client.getOutputStream());
								User user = (User) ois.readObject();
								String made = user.getName();
								String event = "更改文件信息";
								Timestamp timestamp = new Timestamp(System.currentTimeMillis());
								if (!connectToDB)
									throw new IllegalStateException("Not Connected to Database");
								String sql = "UPDATE user SET username = '" + user.getName() + "',password = '"
										+ user.getPassword() + "',role = '" + user.getRole() + "' WHERE username = '"
										+ user.getName() + "'";
								String sql1 = "INSERT INTO log(maker,made,event,time) VALUES('"+maker+"','"+made+"','"+event+"',"+"?)";
								PreparedStatement pstmt1 = con.prepareStatement(sql1);
								pstmt1.setTimestamp(1, timestamp);
								try {
									st.execute(sql);
									pstmt1.execute();
									dos.writeBoolean(true);
								} catch (Exception s) {
									dos.writeBoolean(false);
								}
								client.close();
							} else if (chioce == 9) {// insertUser
								String maker = dis.readUTF();
								ois = new ObjectInputStream(client.getInputStream());
								Timestamp timestamp = new Timestamp(System.currentTimeMillis());
								User user = (User) ois.readObject();
								String made = user.getName();
								String event = "增加用户";
								if (!connectToDB)
									throw new IllegalStateException("Not Connected to Database");
								String sql = "INSERT INTO user (username,password,role) VALUES ('" + user.getName() + "','"
										+ user.getPassword() + "','" + user.getRole() + "')";
								String sql1 = "INSERT INTO log(maker,made,event,time) VALUES('"+maker+"','"+made+"','"+event+"',"+"?)";
								PreparedStatement pstmt1 = con.prepareStatement(sql1);
								pstmt1.setTimestamp(1, timestamp);
								dos = new DataOutputStream(client.getOutputStream());
								try {
									st.execute(sql);
									pstmt1.execute();
									dos.writeBoolean(true);
								} catch (Exception e) {
									dos.writeBoolean(false);
								}
								client.close();
							} else if (chioce == 10) {// deleteUser
								String maker = dis.readUTF();
								if (!connectToDB)
									throw new IllegalStateException("Not Connected to Database");
								String name = dis.readUTF();
								Timestamp timestamp = new Timestamp(System.currentTimeMillis());
								String made = new String(name);
								String event = "删除用户";
								dos = new DataOutputStream(client.getOutputStream());
								String sql = "DELETE FROM user WHERE username = '" + name + "'";
								String sql1 = "INSERT INTO log(maker,made,event,time) VALUES('"+maker+"','"+made+"','"+event+"',"+"?)";
								PreparedStatement pstmt1 = con.prepareStatement(sql1);
								pstmt1.setTimestamp(1, timestamp);
								try {
									st.execute(sql);
									pstmt1.execute();
									dos.writeBoolean(true);
								} catch (Exception e) {
									dos.writeBoolean(false);
								}
								client.close();
							}else if(chioce == 11){//sendMessage
								if (!connectToDB)
									throw new IllegalStateException("Not Connected to Database");
								String url = dis.readUTF();
								Timestamp timestamp = new Timestamp(System.currentTimeMillis());
								int m = (int)(10000*Math.random());
								String con = String.valueOf(m);
								Sender.sendMail(url,"89",con);
								dos = new DataOutputStream(client.getOutputStream());
								dos.writeUTF(con);
								dos.flush();
								client.close();
							}else if(chioce == 12){//deleteDoc
								String maker = dis.readUTF();
								String event = "删除文件";
								Timestamp timestamp = new Timestamp(System.currentTimeMillis());
								if (!connectToDB)
									throw new IllegalStateException("Not Connected to Database");
								String number = dis.readUTF();
								String owner = dis.readUTF();
								String filename = null;
								System.out.println(number+owner);
								String sql1 = "SELECT * FROM doc WHERE number = '" + number + "'";
								ResultSet result = st.executeQuery(sql1);
								result.next();
								filename = result.getString("filename");
								System.out.println(filename);
								String made = new String(filename);
								File file = new File("C:\\sql\\" + owner+"\\"+filename);
								file.delete();
								dos = new DataOutputStream(client.getOutputStream());
								String sql = "DELETE FROM doc WHERE number = '" + number + "'";
								String sql2 = "INSERT INTO log(maker,made,event,time) VALUES('"+maker+"','"+made+"','"+event+"',"+"?)";
								PreparedStatement pstmt1 = con.prepareStatement(sql2);
								pstmt1.setTimestamp(1, timestamp);
								try {
									st.execute(sql);
									pstmt1.execute();
									dos.writeBoolean(true);
								} catch (Exception e) {
									dos.writeBoolean(false);
								}
								client.close();
							}else if (chioce == 13) {// getLog
								if (!connectToDB)
									throw new IllegalStateException("Not Connected to Database");
								String sql = "SELECT * FROM log";
								ResultSet result = st.executeQuery(sql);
								result.last();
								int number = result.getRow();
								dos = new DataOutputStream(client.getOutputStream());
								dos.writeInt(number);
								result.first();
								oos = new ObjectOutputStream(client.getOutputStream());
								do {
									String maker = result.getString("maker");
									String made = result.getString("made");
									String event = result.getString("event");
									Timestamp time = result.getTimestamp("time");
									oos.writeObject(new Log(maker, made, event, time));
									oos.flush();
								} while (result.next());
								client.close();
							}else if (chioce == 14) {// Exit
								String maker = dis.readUTF();
								String made = new String(maker);
								String event = "退出";
								Timestamp timestamp = new Timestamp(System.currentTimeMillis());
								String sql1 = "INSERT INTO log(maker,made,event,time) VALUES('"+maker+"','"+made+"','"+event+"',"+"?)";
								PreparedStatement pstmt1 = con.prepareStatement(sql1);
								pstmt1.setTimestamp(1, timestamp);
								try {
									pstmt1.execute();
								} catch (Exception e) {
									System.exit(0);
								}
							}
							
						}catch(Exception e){
							e.printStackTrace();
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

