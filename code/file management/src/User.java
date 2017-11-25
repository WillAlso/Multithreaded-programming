import java.io.*;
import java.sql.*;
import java.util.*;

public class User {
	private String name;
	private String password;
	private String role;
	public void showMenu() throws IllegalStateException, SQLException {}
	public boolean changeUserInfo(String userName,String userPassword,String userRole) throws IllegalStateException, SQLException{return false;}
	public static boolean addUser(String userName,String userPassword,String userRole) throws IllegalStateException, SQLException {return false;};
	public static String[] listUser() throws IllegalStateException, SQLException {return null;}
	public static boolean deUser(String userName) throws IllegalStateException, SQLException {return false;}
	public static String[] showFilelist() throws IllegalStateException, SQLException {
		Enumeration<Doc> e = DataProcessing.getAllDocs();
		System.out.println("编号\t所有者\t时间\t\t\t描述\t\t文件名");
		int cnt = 0;
		while(e.hasMoreElements()){
			Doc user = e.nextElement();
			cnt++;
	     }
		String[] temp = new String[cnt];
		Enumeration<Doc> d = DataProcessing.getAllDocs();
		int c = 0;
		while(d.hasMoreElements()){
			Doc user = d.nextElement();
			temp[c++] = new String(user.getNumber()+"    "+user.getPath()+"    "+user.getOwner()+"      "+user.getTimestamp());
	     }
		return temp;
	}
	public boolean downloadFile(String num,String downloadpath) throws IllegalStateException, SQLException {
		
		System.out.println(num);
		Doc doc = DataProcessing.searchDoc(num);
		if(doc != null) {
			String sourcepath = "C:\\sql\\"+doc.getOwner();
			File sourcefile = new File(sourcepath+"\\"+doc.getPath());
			System.out.println(sourcefile.getAbsolutePath());
			File fin = new File(sourcepath+"\\"+doc.getPath());
			String nameed = fin.getName().substring(fin.getName().lastIndexOf(".")+1);
			File fout = new File(downloadpath+"\\"+doc.getPath());
			if(fout.exists()){
				return false;
			}
			FileInputStream is;
			FileOutputStream os;
			double len = (double)(fin.length())/50;
			System.out.println(len);
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
				System.out.println(doc.getPath()+"下载成功!\n");
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("文件操作错误!\n");
				return false;
			}
		}
		else
			System.out.println("下载失败!");
		return false;
	}
	public void changeSelfInfo() throws IllegalStateException, SQLException {
		String userName;
		String userPassword;
		userName = getName();
		Scanner input = new Scanner(System.in);
		System.out.print("\n请输入用户旧密码:");
		userPassword = input.next();
		if(!userPassword.equals(getPassword())) {
			System.out.println("密码错误!");
			return;
		}
		System.out.print("\n请输入用户新密码:");
		userPassword = input.next();
		if(DataProcessing.updateUser(userName, userPassword, getRole())) {
			System.out.println(userName +" 信息更新成功 !");
		}
		else {
			System.out.println(userName +"信息更新失败!");
			return;
		}
	}
	public void exitSystem() {
		System.out.println(getName() + "已退出系统!");
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
	public String chooseFolder(String file) {
		File f = new File(file);
		Scanner in = new Scanner(System.in);
		if(f.isFile()) {
			System.out.println("选择路径是文件，返回父目录!");
			return file;
		}
		Map map = new HashMap();
		File[] ft = (new File(file).listFiles());
		int cnt = 1;
		System.out.println("编号\t文件(或文件夹)");
		for(File t:ft) {
			System.out.println(cnt+ "\t"+t.getName());
			map.put(cnt++,t.getName());
		}
		System.out.println(cnt+"\t[结束]");
		int chice = in.nextInt();
		if(chice >= cnt) {
			return file;
		}
		else{
			String filetemp = map.get(chice).toString();
			return chooseFolder(new String(file+"\\"+filetemp));
		}
	}
}