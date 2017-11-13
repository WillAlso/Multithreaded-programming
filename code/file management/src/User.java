import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class User {
	private String name;
	private String password;
	private String role;
	public void showMenu() {}
	public void showFilelist() {
		System.out.println("�ļ��б�:");
		String path = "C:\\filedata";
		File filefolder = new File(path);
		if(!filefolder.exists()) {
			System.out.println("�ļ��в�����!");
			return;
		}
		System.out.println("���\t�ļ�\t�ļ���\t��С\tʱ��");
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
		File file[] = filefolder.listFiles();
		int cnt = 1;
		for(File f:file) {
			if(f.isDirectory()) {
				System.out.print((cnt++) +"\t[Floder]" + f.getName()+ '\t' +f.length()+"bytes\t");
				Date dt = new Date(f.lastModified());
				String sDateTime = sdf.format(dt);
				System.out.println(sDateTime);
			}
			else {
				System.out.print((cnt++) + "\t[File]\t" + f.getName() + '\t' + f.length()+"bytes\t");
				Date dt = new Date(f.lastModified());
				String sDateTime = sdf.format(dt);
				System.out.println(sDateTime);
			}
		}
	}
	public void downloadFile() {
		showFilelist();
		Map map = new HashMap();
		String path = "C:\\filedata";
		String downloadpath = "C:\\Users\\Administrator\\Downloads";
		System.out.print("�������ļ����:");
		Scanner input = new Scanner(System.in);
		File[] file = (new File(path)).listFiles();
		int cnt = 1;
		for(File f:file) {
			map.put(cnt++, f.getName());
		}
		int chice = input.nextInt();
		String filename = map.get(chice).toString();
		File downfile = new File(path + "\\"+filename);
		try {
			new File(downloadpath +"\\" +filename).createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File savefile = new File(downloadpath +"\\" +filename);
		
		System.out.println(downfile.getAbsolutePath());
		System.out.println(savefile.getAbsolutePath());
		try {
			BufferedInputStream is = new BufferedInputStream(new FileInputStream(downfile));
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(savefile));
			int b;
			while((b = is.read()) != -1)
				os.write(b);
			is.close();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * int length=2097152;
		try {
			FileInputStream in=new FileInputStream(downfile);
			FileOutputStream out=new FileOutputStream(savefile);
			byte[] buffer=new byte[length];
			while(true){
				int ins=in.read(buffer);
				if(ins==-1){
					in.close();
					out.flush();
					out.close();
				}else
					out.write(buffer,0,ins);
				}
		}catch (Exception e) {
			//in.close();
			//out.close();
			e.printStackTrace();
			System.out.println(filename+"����ʧ��!");
			return;
		}
		*/
		//System.out.println(filename+"���سɹ�!");
	}
	public void changeSelfInfo() {
		String userName;
		String userPassword;
		userName = getName();
		Scanner input = new Scanner(System.in);
		System.out.print("\n�������û�������:");
		userPassword = input.next();
		if(!userPassword.equals(getPassword())) {
			System.out.println("�������!");
			return;
		}
		System.out.print("\n�������û�������:");
		userPassword = input.next();
		boolean flag;
		try {
			flag = DataProcessing.update(userName, userPassword, getRole());
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		if(flag) {
			System.out.println(userName +" ��Ϣ���³ɹ� !");
		}
		else {
			System.out.println(userName +"��Ϣ����ʧ��!");
			return;
		}
	}
	public void exitSystem() {
		System.out.println(getName() + "���˳�ϵͳ!");
		System.exit(0);
	}
	public void setName(String name){
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
	

}