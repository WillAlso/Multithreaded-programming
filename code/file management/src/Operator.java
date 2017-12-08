import java.io.*;
import java.sql.*;
import java.util.*;
import java.net.*;


public class Operator extends User{
	public Operator(String name,String password,String role){
		setName(name);
		setPassword(password);
		setRole(role);
	}
	public boolean uploadFile(String ID,String description,String file) throws IllegalStateException, SQLException {
		final String SERVER_IP ="127.0.0.1";
	    final int SERVER_PORT =2017;
	    Socket client = null;
	    FileInputStream fis = null;
	    DataOutputStream dos = null;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
		File fin = new File(file);
		if(DataProcessing.insertDoc(ID, getName(), timestamp, description, fin.getName())) {
			/*
			double len = (double)(fin.length())/50;
			double cnt = 0;
			FileInputStream is;
			FileOutputStream os;
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
				System.out.println("\n上传成功!\n");
				return true;
			} catch (Exception e) {
				System.out.println("文件操作错误!\n");
				return false;
			}*/
			 try {
		            try {
		                client =new Socket(SERVER_IP, SERVER_PORT);
		                fis =new FileInputStream(fin);
		                dos =new DataOutputStream(client.getOutputStream());
		                dos.writeInt(1);
		                dos.flush();
		                dos.writeUTF(fin.getName());
		                dos.flush();
		                dos.writeLong(fin.length());
		                dos.flush();
		                dos.writeUTF(getName());
		                dos.flush();
		                
		                byte[] sendBytes =new byte[1024];
		                int length =0;
		                while((length = fis.read(sendBytes,0, sendBytes.length)) >0){
		                    dos.write(sendBytes,0, length);
		                    dos.flush();
		                }
		            }catch (Exception e) {
		                e.printStackTrace();
		                return false;
		            }finally{
		                if(fis !=null)
		                    fis.close();
		                if(dos !=null)
		                    dos.close();
		                client.close();
		            }
		        }catch (Exception e) {
		            e.printStackTrace();
		        }
		}
		else {
			return false;
		}
        return true;
	}
}
