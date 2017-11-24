import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Operator extends User{
	Operator(String name,String password,String role){
		setName(name);
		setPassword(password);
		setRole(role);
	}
	public void uploadFile() throws IllegalStateException, SQLException {
		File folder = new File("C:\\sql\\"+getName());
		if(!folder.exists())
			folder.mkdirs();
		String ID;
		String file;
		String description;
		Scanner input = new Scanner(System.in);
		System.out.print("请输入上传文件名:");		
		file = chooseFile("C:\\");
		System.out.println(file);
		System.out.print("请输入文件编号:");
		ID = input.next();
		System.out.print("请输入文件描述:");
		Scanner input1 = new Scanner(System.in);
		description = input1.nextLine();	
		Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
		File fin = new File(file);
		/*Scanner input3 = new Scanner(System.in);
		System.out.print("请输入保存文件名:");
		String nametemp = input3.nextLine();
		String nameed = fin.getName().substring(fin.getName().lastIndexOf(".")+1);
		File fout = new File("C:\\sql\\"+getName()+"\\"+nametemp+"."+nameed);*/
		File fout = new File("C:\\sql\\"+getName()+"\\"+fin.getName());
		if(fout.exists()) {
			System.out.println("文件存在，是否继续？");
			Scanner input2 = new Scanner(System.in);
			String c = input2.nextLine();
			if(!(c.equals("y")||c.equals("Y")))
				return;
		}
		if(DataProcessing.insertDoc(ID, getName(), timestamp, description, fin.getName())) {
			double len = (double)(fin.length())/50;
			double cnt = 0;
			FileInputStream is;
			FileOutputStream os;
			/*System.out.println("下载进度:");
			for(int m =0;m < 50;m++) {
				System.out.print("-");
			}*/
			System.out.println();
			try {
				is = new FileInputStream(fin);
				os = new FileOutputStream(fout);
				/*int b;
				while((b=is.read()) != -1){
					os.write(b);
					cnt++;
					while(cnt >= len) {
						cnt -= len;
						System.out.print("-");
					}
				}				
				is.close();
				os.close();*/
				byte buf[] = new byte[1024];
				int len_t = 0;
				while((len_t=is.read(buf))!=-1) {  
			           os.write(buf,0,len_t);
			           os.flush();
			    }
				os.close();
				is.close();
				System.out.println("\n上传成功!\n");
			} catch (Exception e) {
				System.out.println("文件操作错误!\n");
				return;
			}
		}
		else {
			System.out.println("上传失败!");
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
			//exitSystem();break;
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
