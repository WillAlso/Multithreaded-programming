package Server;

import java.io.*;
import java.net.*;

public class Server extends ServerSocket {

	private static final int PORT = 2017;

	private ServerSocket server;
	private Socket client;
	private DataInputStream dis;
	private FileOutputStream fos;
	private FileInputStream fis;
	private DataOutputStream dos;
	public Server() throws Exception {
		try {
			try {
				server = new ServerSocket(PORT);
				while (true) {
					client = server.accept();
					dis = new DataInputStream(client.getInputStream());
					if (dis.readInt() == 1) {
						String fileName = dis.readUTF();
						long fileLength = dis.readLong();
						String folderutf = dis.readUTF();
						File folder = new File("C:\\sql\\" + folderutf);
						if (!folder.exists())
							folder.mkdirs();

						fos = new FileOutputStream(new File("C:\\sql\\" + folderutf + "\\" + fileName));
						byte[] sendBytes = new byte[1024];
						int transLen = 0;
						System.out.println("----开始接收文件<" + fileName + ">,文件大小为<" + fileLength + ">----");
						while (true) {
							int read = 0;
							read = dis.read(sendBytes);
							if (read == -1)
								break;
							transLen += read;
							System.out.println("接收文件进度" + 100 * transLen / fileLength + "%...");
							fos.write(sendBytes, 0, read);
							fos.flush();
						}
						System.out.println("----接收文件<" + fileName + ">成功-------");
						client.close();
					}else{
						dos =new DataOutputStream(client.getOutputStream());
						String fileName = dis.readUTF();
						String folderutf = dis.readUTF();
						File temp = new File("C:\\sql\\" + folderutf + "\\" + fileName);
						dos.writeLong(temp.length());
						dos.flush();
						fis = new FileInputStream(temp);
						System.out.println(new File("C:\\sql\\" + folderutf + "\\" + fileName).getAbsolutePath());
		                byte[] sendBytes =new byte[1024];
		                int length =0;
		                while((length = fis.read(sendBytes,0, sendBytes.length)) >0){
		                    dos.write(sendBytes,0, length);
		                    dos.flush();
		                }
		                System.out.println("发送层高");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (dis != null)
					dis.close();
				if (fos != null)
					fos.close();
				if(server != null)
					server.close();
				if(fis != null)
					fis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			new Server();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
