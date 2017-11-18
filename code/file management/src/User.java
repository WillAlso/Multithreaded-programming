import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class User {
	private String name;
	private String password;
	private String role;
	public void showMenu() throws IllegalStateException, SQLException {}
	public void showFilelist() throws IllegalStateException, SQLException {
		Enumeration<Doc> e = DataProcessing.getAllDocs();
		System.out.println("编号\t所有者\t时间\t\t\t描述\t\t文件名");
		while(e.hasMoreElements()){
			Doc user = e.nextElement();
	        System.out.println(user.getNumber()+"\t"+user.getOwner()+"\t"+user.getTimestamp()+"\t"+user.getDescription()+"\t"+user.getPath());
	     }
	}
	public void downloadFile() throws IllegalStateException, SQLException {
		String num;
		String sourcepath = "C:\\sql";
		String downloadpath;
		System.out.print("请输入下载文件编号:");
		Scanner input = new Scanner(System.in);
		num = input.next();
		Doc doc = DataProcessing.searchDoc(num);
		if(doc != null) {
			File sourcefile = new File(sourcepath+doc.getPath());
			System.out.println("请选择下载目录");
			downloadpath = chooseFolder("C:\\");
			File fin = new File(sourcepath+"\\"+doc.getPath());
			File fout = new File(downloadpath+"\\"+doc.getPath());
			FileInputStream is;
			FileOutputStream os;
			double len = (double)(fin.length())/50;
			System.out.println(len);
			double cnt = 0;
			for(int m = 0;m < 50;m++) {
				System.out.print("-");
			}
			System.out.println();
			try {
				is = new FileInputStream(fin);
				os = new FileOutputStream(fout);
				int b;
				while((b=is.read()) != -1){
					os.write(b);
					cnt++;
					while(cnt>=len) {
						cnt -= len;
						System.out.print("-");
					}
				}
				is.close();
				os.close();		
				System.out.println(doc.getPath()+"下载成功!\n");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("文件操作错误!\n");
				return;
			}
		}
		else
			System.out.println(doc.getPath()+"下载失败!");
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