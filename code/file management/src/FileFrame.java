import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;

public class FileFrame {
	public static void main(String args[]){
		Login l = new Login();
	}
}

class Login implements ActionListener{
	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JPasswordField passwordField_1;
	private JTextField textField_2;
	private JPasswordField passwordField_2;
	private JPasswordField passwordField_3;
	private JPasswordField passwordField_4;
	private JButton button;
	private JButton button_1;
	private JDesktopPane desktopPane;
	private JDesktopPane mainPane;
	private JTextField tf_01;
	private JTextField tf_02;
	private File file;
	private JMenuBar mBar;
	private JMenu menu1;
	private JMenu menu2;
	private JMenuItem mil1;
	private JMenuItem mil2;
	private JMenuItem mil3;
	private JMenuItem mil4;
	private JMenuItem mil5;
	private JMenuItem mil6;
	private JButton btn;	//�ļ��б�
	private JButton btn_1;	//�����ļ�
	private JButton btn_2;	//�ı��û���Ϣ
	private JButton btn_3;	//ɾ���û�
	private JButton btn_4;	//����û�
	private JButton btn_5;	//�û��б�
	private JButton btn_6;	//��ʾ�˵�
	private JButton btn_7;	//�˳���¼
	private JButton btn_8;	//�ϴ��ļ�
	private JButton btn_9;	//������Ϣ
	private JTable list_ad;	//
	private JList li;		//
	private JList li_1;		//
	private JTable li_2;		//
	private int flag;		//
	private User user;
	public Login(){
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		textField = new JTextField();
		passwordField = new JPasswordField();
		button = new JButton("��¼");
		button_1 = new JButton("�˳�");
		JLabel label = new JLabel("�û�");
		JLabel lblNewLabel = new JLabel("����");
		textField.setBounds(160, 66, 151, 21);
		textField.setColumns(10);
		passwordField.setBounds(160, 108, 151, 21);
		lblNewLabel.setBounds(120, 110, 30, 20);
		button.setBounds(115, 160, 90, 20);
		button_1.setBounds(245, 160, 90, 20);
		label.setBounds(120, 70, 30, 20);
		desktopPane.add(textField);
		desktopPane.add(label);
		desktopPane.add(passwordField);
		desktopPane.add(lblNewLabel);
		desktopPane.add(button);		
		button.addActionListener(this);
		button_1.addActionListener(this);
		desktopPane.add(button_1);
		if(flag == 2){
			res_ad();
		}else if(flag == 1){
			res_op();
		}else if(flag == 0){
			res_br();
		}
		frame.setVisible(true);
	}
	public void res_ad(){
		mainPane = new JDesktopPane();
		mainPane.setBackground(new Color(255, 255, 255));
		mBar = new JMenuBar();
		mBar.setBounds(0,0,100,30);
		menu1 = new JMenu(" �˵� ");
		menu2 = new JMenu("ϵͳ");
		mil1 = new JMenuItem("������ҳ");
		mil2 = new JMenuItem("�˻���Ϣ");
		mil3 = new JMenuItem("�ر�");
		mil4 = new JMenuItem("����");
		mil5 = new JMenuItem("����");
		mil6 = new JMenuItem("����");
		btn = new JButton("�ļ��б�");
		btn_1 = new JButton("�����ļ�");
		btn_2 = new JButton("�ı��û�");
		btn_3 = new JButton("ɾ���û�");
		btn_4 = new JButton("����û�");
		btn_5 = new JButton("�û��б�");
		btn_6 = new JButton("��ʾ�˵�");
		btn_7 = new JButton("�˳���¼");
		btn.setBounds(20, 50, 90, 20);
		btn_1.setBounds(20, 80, 90, 20);
		btn_2.setBounds(20, 110, 90, 20);
		btn_3.setBounds(20, 140, 90, 20);
		btn_4.setBounds(20, 170, 90, 20);
		btn_5.setBounds(20, 200, 90, 20);
		btn_6.setBounds(20, 230, 90, 20);
		btn_7.setBounds(20, 260, 90, 20);
		mil1.setSize(50, 15);
		menu1.setSize(50, 25);
		menu1.add(mil1);
		menu1.add(mil2);
		menu2.add(mil3);
		menu2.add(mil4);
		menu2.add(mil5);
		menu2.add(mil6);
		mBar.add(menu1);
		mBar.add(menu2);
		mainPane.add(mBar);
		mainPane.add(btn);
		mainPane.add(btn_1);
		mainPane.add(btn_2);
		mainPane.add(btn_3);
		mainPane.add(btn_4);
		mainPane.add(btn_5);
		mainPane.add(btn_6);
		mainPane.add(btn_7);	
		mil1.addActionListener(this);
		mil2.addActionListener(this);
		mil3.addActionListener(this);
		mil4.addActionListener(this);
		mil5.addActionListener(this);
		mil6.addActionListener(this);
		btn.addActionListener(this);
		btn_1.addActionListener(this);
		btn_2.addActionListener(this);
		btn_3.addActionListener(this);
		btn_4.addActionListener(this);
		btn_5.addActionListener(this);
		btn_6.addActionListener(this);
		btn_7.addActionListener(this);
	}
	public void res_op(){
		mainPane = new JDesktopPane();
		mainPane.setBackground(new Color(255, 255, 255));
		mBar = new JMenuBar();
		mBar.setBounds(0,0,80,20);
		menu1 = new JMenu("�˵�");
		menu2 = new JMenu("ϵͳ");
		mil1 = new JMenuItem("������ҳ");
		mil2 = new JMenuItem("�˻���Ϣ");
		mil3 = new JMenuItem("�ر�");
		mil4 = new JMenuItem("����");
		mil5 = new JMenuItem("����");
		mil6 = new JMenuItem("����");
		btn = new JButton("�ļ��б�");
		btn_1 = new JButton("�����ļ�");
		btn_8 = new JButton("�ϴ��ļ�");
		btn_6 = new JButton("��ʾ�˵�");
		btn_9 = new JButton("������Ϣ");
		btn_7 = new JButton("�˳���¼");
		btn.setBounds(20, 50, 90, 20);
		btn_1.setBounds(20, 80, 90, 20);
		btn_8.setBounds(20, 110, 90, 20);
		btn_6.setBounds(20, 140, 90, 20);
		btn_9.setBounds(20, 170, 90, 20);
		btn_7.setBounds(20, 200, 90, 20);
		menu1.add(mil1);
		menu1.add(mil2);
		menu2.add(mil3);
		menu2.add(mil4);
		menu2.add(mil5);
		menu2.add(mil6);
		mBar.add(menu1);
		mBar.add(menu2);
		mainPane.add(mBar);
		mainPane.add(btn);
		mainPane.add(btn_1);
		mainPane.add(btn_8);
		mainPane.add(btn_6);
		mainPane.add(btn_9);
		mainPane.add(btn_7);	
		mil1.addActionListener(this);
		mil6.addActionListener(this);
		btn.addActionListener(this);
		btn_1.addActionListener(this);
		btn_8.addActionListener(this);
		btn_6.addActionListener(this);
		btn_9.addActionListener(this);
		btn_7.addActionListener(this);
	}
	public void res_br(){
		mainPane = new JDesktopPane();
		mainPane.setBackground(new Color(255, 255, 255));
		mBar = new JMenuBar();
		mBar.setBounds(0,0,80,20);
		menu1 = new JMenu("�˵�");
		menu2 = new JMenu("ϵͳ");
		mil1 = new JMenuItem("������ҳ");
		mil2 = new JMenuItem("�˻���Ϣ");
		mil3 = new JMenuItem("�ر�");
		mil4 = new JMenuItem("����");
		mil5 = new JMenuItem("����");
		mil6 = new JMenuItem("����");
		btn = new JButton("�ļ��б�");
		btn_1 = new JButton("�����ļ�");
		btn_6 = new JButton("��ʾ�˵�");
		btn_9 = new JButton("������Ϣ");
		btn_7 = new JButton("�˳���¼");
		btn.setBounds(20, 50, 90, 20);
		btn_1.setBounds(20, 80, 90, 20);
		btn_6.setBounds(20, 110, 90, 20);
		btn_9.setBounds(20, 140, 90, 20);
		btn_7.setBounds(20, 170, 90, 20);
		menu1.add(mil1);
		menu1.add(mil2);
		menu2.add(mil3);
		menu2.add(mil4);
		menu2.add(mil5);
		menu2.add(mil6);
		mBar.add(menu1);
		mBar.add(menu2);
		mainPane.add(mBar);
		mainPane.add(btn);
		mainPane.add(btn_1);
		mainPane.add(btn_6);
		mainPane.add(btn_9);
		mainPane.add(btn_7);	
		mil1.addActionListener(this);
		mil2.addActionListener(this);
		mil3.addActionListener(this);
		mil4.addActionListener(this);
		mil5.addActionListener(this);
		mil6.addActionListener(this);
		btn.addActionListener(this);
		btn_1.addActionListener(this);
		btn_6.addActionListener(this);
		btn_9.addActionListener(this);
		btn_7.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {	//ˢ�²˵�
		if(e.getSource() == mil1){
			frame.remove(mainPane);
			if(flag == 2){
				res_ad();
			}else if(flag == 1){
				res_op();
			}else if(flag == 0){
				res_br();
			}
			frame.add(mainPane);
			frame.validate();
		}else if(e.getSource() == mil2){
			
		}else if(e.getSource() == mil3){			//�رճ���
			System.exit(0);
		}else if(e.getSource() == mil4){
			
		}else if(e.getSource() == mil5){
			
		}else if(e.getSource() == mil6){
			
		}else if(e.getSource() == btn){				//�ļ��б�
			frame.remove(mainPane);
			if(flag == 2){
				res_ad();
			}else if(flag == 1){
				res_op();
			}else if(flag == 0){
				res_br();
			}
			try {
				Doc[] m = user.showFilelist();
				String[] column = {"���","������","ʱ��","����","�ļ���"};
				Object[][] data = new Object[m.length][5];
				for(int i = 0;i < m.length;i++){
						data[i][0] = m[i].getNumber();
						data[i][1] = m[i].getOwner();
						data[i][2] = m[i].getTimestamp();
						data[i][3] = m[i].getDescription();
						data[i][4] = m[i].getPath();
				}
				list_ad = new JTable(data,column);
			} catch (IllegalStateException | SQLException e1) {
				e1.printStackTrace();
			}
			 list_ad.setBounds(200, 60, 800,1000);
			 mainPane.add(list_ad);
			 frame.add(mainPane);
			 frame.validate();
		}else if(e.getSource() == btn_1){			//�����ļ�
			frame.remove(mainPane);
			if(flag == 2){
				res_ad();
			}else if(flag == 1){
				res_op();
			}else if(flag == 0){
				res_br();
			}
			try {
				Doc[] m = user.showFilelist();
				String[] column = {"���","������","ʱ��","����","�ļ���"};
				Object[][] data = new Object[m.length][5];
				for(int i = 0;i < m.length;i++){
						data[i][0] = m[i].getNumber();
						data[i][1] = m[i].getOwner();
						data[i][2] = m[i].getTimestamp();
						data[i][3] = m[i].getDescription();
						data[i][4] = m[i].getPath();
				}
				list_ad = new JTable(data,column);
			} catch (IllegalStateException | SQLException e1) {
				e1.printStackTrace();
			}
			 list_ad.setBounds(200, 60, 800,1000);
			 mainPane.add(list_ad);
			 JButton bt = new JButton("ȷ��");
			 bt.setBounds(20, 400, 93,23);
			 mainPane.add(bt);
			 bt.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent arg0){
					 if(list_ad.getSelectedRow() != -1){
						 String str= (String) list_ad.getValueAt(list_ad.getSelectedRow(), 4);
						 String downFile = str;
						 JFrame fe = new JFrame();
						 fe.setBounds(100, 100, 450, 300);
						fe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						JFileChooser dlg = new JFileChooser();
						dlg.setApproveButtonText("ȷ��");
						dlg.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						dlg.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
									File file = dlg.getSelectedFile();
									if(file != null){
										try {
											if(user.downloadFile(downFile, file.getAbsolutePath())){
												 JLabel tl = new JLabel("���سɹ�");
												 tl.setBounds(133, 176, 54, 15);
												 mainPane.add(tl);
											}
											else{
												JLabel tl = new JLabel("����ʧ��");
											 tl.setBounds(133, 176, 54, 15);
											 mainPane.add(tl);
											}
										} catch (IllegalStateException | SQLException e1) {
											e1.printStackTrace();
										}
									}
									fe.setVisible(false);
							}
						});
						File file = dlg.getSelectedFile();
						if(file != null)
							System.out.println(file.getAbsolutePath());
						dlg.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
						fe.add(dlg);
						fe.setVisible(true);
					 }
			 }});
			frame.add(mainPane);
			frame.validate();
		}else if(e.getSource() == btn_2){			//�����û�
			frame.remove(mainPane);
			if(flag == 2){
				res_ad();
			}else if(flag == 1){
				res_op();
			}else if(flag == 0){
				res_br();
			}
			JLabel lblNewLabel_2 = new JLabel("�û�");
			lblNewLabel_2.setBounds(270, 80, 40, 20);
			mainPane.add(lblNewLabel_2);
			JLabel lblNewLabel_3 = new JLabel("����");
			lblNewLabel_3.setBounds(270, 115, 40, 20);
			mainPane.add(lblNewLabel_3);
			textField_1 = new JTextField();
			textField_1.setBounds(350, 80, 105, 20);
			mainPane.add(textField_1);
			passwordField_1 = new JPasswordField();
			passwordField_1.setBounds(350, 115, 105, 20);
			mainPane.add(passwordField_1);
			String m[] = {"administrator","operator","browser"};
			li = new JList<String>(m);
			li.setBounds(270,150,600,100);
			mainPane.add(li);
			JButton bt = new JButton("ȷ��");
			 bt.setBounds(20, 400, 90,20);
			 mainPane.add(bt);
			 bt.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent g){
					 String name = textField_1.getText();
					 String pass = new String(passwordField_1.getPassword());
					 String role = null;
					 if(li.getSelectedValue()!=null)
						 role = li.getSelectedValue().toString();
					 if(name != null && pass != null && role != null){
						 try {
							if(user.changeUserInfo(name, pass, role)){
								JLabel tl = new JLabel("���³ɹ�");
								 tl.setBounds(133, 176, 54, 15);
								 mainPane.add(tl);
								 textField_1.setText("");
								 passwordField_1.setText("");
								 li.clearSelection();
							}
							else{
								JLabel tl = new JLabel("����ʧ��");
								 tl.setBounds(133, 176, 54, 15);
								 mainPane.add(tl);
								 textField_1.setText("");
								 passwordField_1.setText("");
								 li.clearSelection();
							}
						} catch (IllegalStateException | SQLException e) {
							e.printStackTrace();
						}
					 }
				 }
			 });
			frame.add(mainPane);
			frame.validate();
		}else if(e.getSource() == btn_3){			//ɾ���û�
			frame.remove(mainPane);
			if(flag == 2){
				res_ad();
			}else if(flag == 1){
				res_op();
			}else if(flag == 0){
				res_br();
			}
			try {
				User[] m = user.listUser();
				String[] column = {"�û�","����"};
				Object[][] data = new Object[m.length][2];
				for(int i = 0;i < m.length;i++){
					data[i][0] = m[i].getName();
					data[i][1] = m[i].getRole();
				}
				li_2 = new JTable(data,column);
			} catch (IllegalStateException | SQLException e1) {
				e1.printStackTrace();
			}
			 li_2.setBounds(200, 60, 800,1000);
			 mainPane.add(li_2);
			 JButton bt = new JButton("ȷ��");
			 bt.setBounds(20, 400, 90,20);
			 mainPane.add(bt);
			 bt.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent g){
					 String role = null;
					 role = (String) li_2.getValueAt(li_2.getSelectedRow(), 1);
					 String n = (String) li_2.getValueAt(li_2.getSelectedRow(), 0);
					 System.out.println(role);
						 try {
							if(user.deUser(n)){
								JLabel tl = new JLabel("ɾ���ɹ�");
								li_2.remove(li_2.getSelectedRow());
								 tl.setBounds(133, 176, 54, 15);
								 mainPane.add(tl);
								 li_2.clearSelection();
								 frame.validate();
							}
							else{
								JLabel tl = new JLabel("ɾ��ʧ��");
								 tl.setBounds(133, 176, 54, 15);
								 mainPane.add(tl);
								 li_2.clearSelection();
							}
						} catch (IllegalStateException | SQLException e) {
							e.printStackTrace();
						}
					 
				 }
			 });
			frame.add(mainPane);
			frame.validate();
		}else if(e.getSource() == btn_4){		//����û�
			frame.remove(mainPane);
			if(flag == 2){
				res_ad();
			}else if(flag == 1){
				res_op();
			}else if(flag == 0){
				res_br();
			}
			JLabel lblNewLabel_2 = new JLabel("�û�");
			lblNewLabel_2.setBounds(270, 80, 40, 20);
			mainPane.add(lblNewLabel_2);
			JLabel lblNewLabel_3 = new JLabel("����");
			lblNewLabel_3.setBounds(270, 115, 40, 20);
			mainPane.add(lblNewLabel_3);
			textField_2 = new JTextField();
			textField_2.setBounds(350, 80, 105, 20);
			mainPane.add(textField_2);
			passwordField_2 = new JPasswordField();
			passwordField_2.setBounds(350, 115, 105, 20);
			mainPane.add(passwordField_2);
			String m[] = {"administrator","operator","browser"};
			li_1 = new JList<String>(m);
			li_1.setBounds(270,150,600,100);
			mainPane.add(li_1);
			JButton bt = new JButton("ȷ��");
			 bt.setBounds(20, 400, 93,23);
			 mainPane.add(bt);
			 bt.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent g){
					 String name = textField_2.getText();
					 String pass = new String(passwordField_2.getPassword());
					 String role = null;
					 role = li_1.getSelectedValue().toString();
					 System.out.println(name+pass+role);
					// if(name != null && pass != null && role != null){
						 try {
							if(user.addUser(name, pass, role)){
								JLabel tl = new JLabel("��ӳɹ�");
								 System.out.println(name+pass+role);
								 tl.setBounds(133, 176, 54, 15);
								 mainPane.add(tl);
								 textField_2.setText("");
								 passwordField_2.setText("");
								 li_1.clearSelection();
							}
							else{
								JLabel tl = new JLabel("���ʧ��");
								tl.setBounds(133, 176, 54, 15);
								mainPane.add(tl);
								textField_2.setText("");
								passwordField_2.setText("");
								li_1.clearSelection();
							}
						} catch (IllegalStateException | SQLException e) {
							e.printStackTrace();
						}
					 }
			 });
			frame.add(mainPane);
			frame.validate();
			
		}else if(e.getSource() == btn_5){		//�û��б�
			frame.remove(mainPane);
			if(flag == 2){
				res_ad();
			}else if(flag == 1){
				res_op();
			}else if(flag == 0){
				res_br();
			}
			try {
				User[] m = user.listUser();
				String[] column = {"�û�","����"};
				Object[][] data = new Object[m.length][2];
				for(int i = 0;i < m.length;i++){
					data[i][0] = m[i].getName();
					data[i][1] = m[i].getRole();
				}
				li_2 = new JTable(data,column);
			} catch (IllegalStateException | SQLException e1) {
				e1.printStackTrace();
			}
			 JLabel lt = new JLabel("����       ����");
			 lt.setBounds(200, 40, 200,20);
			 mainPane.add(lt);
			 li_2.setBounds(200, 60, 800,1000);
			 mainPane.add(li_2);
			frame.add(mainPane);
			frame.validate();
		}else if(e.getSource() == btn_6){
			frame.remove(mainPane);
			if(flag == 2){
				res_ad();
			}else if(flag == 1){
				res_op();
			}else if(flag == 0){
				res_br();
			}
			frame.add(mainPane);
			frame.validate();
		}else if(e.getSource() == btn_7){
			frame.remove(mainPane);
			frame.setBounds(100, 100, 450, 300);
			if(flag == 2){
				res_ad();
			}else if(flag == 1){
				res_op();
			}else if(flag == 0){
				res_br();
			}
			user = null;
			frame.add(desktopPane);
			frame.validate();
		}else if(e.getSource() == btn_8){		//�ϴ��ļ�
			frame.remove(mainPane);
			if(flag == 2){
				res_ad();
			}else if(flag == 1){
				res_op();
			}else if(flag == 0){
				res_br();
			}
			JButton btnNewButton_11 = new JButton("ѡ���ļ�");
			btnNewButton_11.setBounds(250, 65, 90, 20);
			mainPane.add(btnNewButton_11);
			btnNewButton_11.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent g){
					JFrame fe = new JFrame();
					fe.setBounds(100, 100, 450, 300);
					fe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					JFileChooser dlg = new JFileChooser();
					dlg.setApproveButtonText("ȷ��");
					dlg.setFileSelectionMode(JFileChooser.FILES_ONLY);
					dlg.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							file = dlg.getSelectedFile();
							if(file != null){
								JLabel ll = new JLabel(file.getAbsolutePath());
								ll.setBounds(120,360,1000,20);
								mainPane.add(ll);
								fe.setVisible(false);
							}
						}
					});
					file = dlg.getSelectedFile();
					JLabel ll1 = new JLabel("���");
					ll1.setBounds(270, 90, 50, 15);
					mainPane.add(ll1);
					
					JLabel ll2 = new JLabel("����");
					ll2.setBounds(268, 120, 50, 15);
					mainPane.add(ll2);
					tf_01 = new JTextField();
					tf_01.setBounds(355, 90, 100, 21);
					mainPane.add(tf_01);
					tf_02 = new JTextField();
					tf_02.setBounds(355, 120, 100, 21);
					mainPane.add(tf_02);
					JButton btn01 = new JButton("�ϴ�");
					btn01.setBounds(250, 150, 90, 20);
					mainPane.add(btn01);
					btn01.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							if(file != null ){
								String ID = tf_01.getText();
								String description = tf_02.getText();
								try {
									if(user != null)
									if(user.uploadFile(ID, description, file.getAbsolutePath())){
										JLabel tl = new JLabel("�ϴ��ɹ�");
										tf_01.setText("");
										tf_02.setText("");
										file = null;
										tl.setBounds(133, 176, 54, 15);
										mainPane.add(tl); 
									}
								} catch (IllegalStateException | SQLException e1) {
									e1.printStackTrace();
								}
							}
						}
					});
					fe.add(dlg);
					fe.setVisible(true);
				 }
			});
			frame.add(mainPane);
			frame.validate();
		}else if(e.getSource() == btn_9){		//������Ϣ
			frame.remove(mainPane);
			if(flag == 2)	{res_ad();}
			else if(flag == 1)	{res_op();}
			else if(flag == 0)	{res_br();}
			JLabel lb = new JLabel("������");
			JLabel lb_1 = new JLabel("������");
			lb.setBounds(270, 80, 40, 20);
			mainPane.add(lb);
			lb_1.setBounds(270, 115, 40, 20);
			mainPane.add(lb_1);
			passwordField_3 = new JPasswordField();
			passwordField_3.setBounds(350, 80, 105, 20);
			mainPane.add(passwordField_3);
			passwordField_4 = new JPasswordField();
			passwordField_4.setBounds(350, 115, 105, 20);
			mainPane.add(passwordField_4);
			JButton bt = new JButton("ȷ��");
			 bt.setBounds(20, 400, 93,23);
			 mainPane.add(bt);
			 bt.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent g){
					 String oldPass = new String(passwordField_3.getPassword());
					 String newPass = new String(passwordField_4.getPassword());
					 try {
						if(user.changeSelfInfo(oldPass, newPass)){
							JLabel tl = new JLabel("���ĳɹ�");
							 tl.setBounds(133, 176, 54, 15);
							 mainPane.add(tl);
							 passwordField_3.setText("");
							 passwordField_4.setText("");
						}
						else{
							JLabel tl = new JLabel("����ʧ��");
							 tl.setBounds(133, 176, 54, 15);
							 mainPane.add(tl);
							 passwordField_3.setText("");
							 passwordField_4.setText("");
						}
					}  catch (IllegalStateException g1) {
						System.out.println("Error in excecuting Query");
						System.exit(0);
					} catch (SQLException g1) {
						System.out.println("Not Connected to Database");
						System.exit(0);
					}
				}
			 });
			frame.add(mainPane);
			frame.validate();
		} else if(e.getSource() == button){

			String name = textField.getText();
			String password = new String(passwordField.getPassword());
			try {
				user = DataProcessing.searchUser(name, password);
				if(user != null){
					System.out.println(user.getRole());
				switch(user.getRole()){
					case "administrator":
						user = new Administrator(name,password,user.getRole());flag = 2;break;
					case "operator":
						user = new Operator(name,password,user.getRole());flag = 1;break;
					case "browser":
						user = new Browser(name,password,user.getRole());flag = 0;break;
					}
					frame.remove(desktopPane);
					frame.setBounds(100, 100, 707, 479);
					textField.setText("");
					passwordField.setText("");
					if(flag == 2){
						res_ad();
					}else if(flag == 1){
						res_op();
					}else if(flag == 0){
						res_br();
					}
					frame.add(mainPane);
					frame.validate();
				}
				else{
					JLabel label = new JLabel("�û��������������!");
					label.setForeground(Color.red);
					label.setBounds(150, 85,120,120);
					desktopPane.add(label);
					frame.validate();
				}
			} catch (IllegalStateException g) {
				System.out.println("Error in excecuting Query");
			//	System.exit(0);
			} catch (SQLException g) {
				System.out.println("Not Connected to Database");
			//	System.exit(0);
			}
		}else if(e.getSource() == button_1){
			System.exit(0);
		}
	}
}
