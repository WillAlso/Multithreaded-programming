import java.io.*;
import java.sql.*;
import java.util.*;

public class Operator extends User{
	Operator(String name,String password,String role){
		setName(name);
		setPassword(password);
		setRole(role);
	}
	public boolean uploadFile(String ID,String description,String file) throws IllegalStateException, SQLException {
		File folder = new File("C:\\sql\\"+getName());
		if(!folder.exists())
			folder.mkdirs();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
		File fin = new File(file);
		File fout = new File("C:\\sql\\"+getName()+"\\"+fin.getName());
		if(fout.exists()) {
			System.out.println("文件存在，是否继续？");
			Scanner input2 = new Scanner(System.in);
			String c = input2.nextLine();
			if(!(c.equals("y")||c.equals("Y")))
				return false;
		}
		System.out.println(ID+" "+getName()+description+ fin.getAbsolutePath());
		System.out.println(ID+" "+getName()+description+ fout.getAbsolutePath());
		if(DataProcessing.insertDoc(ID, getName(), timestamp, description, fin.getName())) {
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
			}
		}
		else {
			System.out.println("上传失败!");
			return false;
		}
	}
	public void showMenu() throws IllegalStateException, SQLException {
		System.out.println("The Operator's Menu:");
		System.out.println("1,文件列表\n2,下载文件\n3,上传文件\n4,显示菜单\n5,更改信息\n6,退出登录");
		Scanner input = new Scanner(System.in);
		String c;
		while(true) {
			c = input.next();
		switch(c) {
		case "1":
			showFilelist();break;
		case "2":
			downloadFile();break;
		case "3":
			uploadFile();break;
		case "4":
			showMenu();break;
		case "5":
			changeSelfInfo();break;
		case "6":
			return;
		}
		}
	}
	public String chooseFile(String file) {
		File f = new File(file);
		Scanner in = new Scanner(System.in);
		if(f.isFile()) {
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
		String filetemp = map.get(chice).toString();
		return chooseFile(new String(file+"\\"+filetemp));
	}
}
