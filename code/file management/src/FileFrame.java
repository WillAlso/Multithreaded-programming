import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;

import com.sun.awt.AWTUtilities;

public class FileFrame {
	public static void main(String args[]) {
		Login login = new Login();
	}
}

class Login implements ActionListener, KeyListener {
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
	private TextArea tf_02;
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
	private JButton btn; // 文件列表
	private JButton btn_1; // 下载文件
	private JButton btn_2; // 改变用户信息
	private JButton btn_3; // 删除用户
	private JButton btn_4; // 添加用户
	private JButton btn_5; // 用户列表
	private JButton btn_8; // 上传文件
	private JTable list_ad; //
	private JList li; //
	private JList li_1; //
	private JTable li_2; //
	private int flag; //
	private User user;
	private JLabel labtemp1;
	static Point origin = new Point();
	public Login() {
		ImageIcon icon = new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\2.gif");
		JLabel lab = new JLabel(icon);
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBackground(new Color(0, 0, 0, 0));
		frame.setBounds(100, 100, 400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(0, 0, 0, 0));
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		lab.setBounds(0, 0, 120, 80);
		desktopPane.add(lab);
		textField = new JTextField();
		passwordField = new JPasswordField();
		button = new JButton("登录");
		button_1 = new JButton("退出");
		JLabel label = new JLabel("用户");
		JLabel lblNewLabel = new JLabel("密码");
		textField.setBounds(160, 66, 150, 20);
		textField.setColumns(10);
		passwordField.setBounds(160, 108, 150, 20);
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
		passwordField.addKeyListener(this);
		desktopPane.add(button_1);
		ImageIcon icon_close = new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\col.gif");
		ImageIcon icon_min = new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\min.gif");
		JButton button_close = new JButton(icon_close);
		JButton button_min = new JButton(icon_min);
		button_close.setBounds(350, 0, 30, 30);
		button_min.setBounds(320, 0, 30, 30);
		button_close.setBackground(new Color(0, 0, 0, 0));
		button_min.setBackground(new Color(0, 0, 0, 0));
		button_close.setBorderPainted(false);
		button_min.setBorderPainted(false);
		button_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_min.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setExtendedState(JFrame.ICONIFIED);
			}
		});
		desktopPane.add(button_close);
		desktopPane.add(button_min);
		if (flag == 3) {
			res_ad();
		} else if (flag == 2) {
			res_op();
		} else if (flag == 1) {
			res_br();
		}
		frame.setVisible(true);
	}
	public void res_ad() {
		mainPane = new JDesktopPane();
		mainPane.setBackground(new Color(255, 255, 255));
		ImageIcon icon = new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\back.png");
		JLabel labtemp = new JLabel(icon);
		labtemp1 = new JLabel();
		labtemp1.setOpaque(true);
		labtemp1.setBackground(new Color(236, 242, 247));
		labtemp1.setBounds(0, 90, frame.getWidth(), 40);
		mBar = new JMenuBar();
		mBar.setBorderPainted(false);
		mBar.setBackground(new Color(6, 130, 230));
		mBar.setBounds(frame.getWidth() - 110, 0, 35, 20);
		menu1 = new JMenu("菜单");
		mil1 = new JMenuItem("账户");
		mil2 = new JMenuItem("注销");
		mil3 = new JMenuItem("帮助");
		mil4 = new JMenuItem("反馈");
		mil5 = new JMenuItem("关于");
		mil6 = new JMenuItem("退出");
		btn_3 = new JButton("删除用户");
		JButton btn_min = new JButton(new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\winmin.png"));
		btn_min.setBounds(frame.getWidth() - 80, 0, 30, 20);
		JButton btn_win = new JButton(new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\win.png"));
		btn_win.setBounds(frame.getWidth() - 55, 0, 30, 20);
		JButton btn_close = new JButton(new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\winclo.png"));
		btn_close.setBounds(frame.getWidth() - 30, 0, 30, 20);
		btn_min.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setExtendedState(JFrame.ICONIFIED);
			}
		});
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btn_win.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (frame.getWidth() <= 900) {
					frame.setLocation(0, 0);
					frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
					// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} else {
					frame.setLocation(100, 100);
					frame.setSize(900, 650);
				}
				frame.remove(mainPane);
				res_ad();
				frame.add(mainPane);
				frame.validate();
			}
		});

		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		frame.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point p = frame.getLocation();
				frame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
			}
		});
		mainPane.add(btn_min);
		mainPane.add(btn_win);
		mainPane.add(btn_close);
		ImageIcon icon1 = new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\1.png");
		btn = new JButton(icon1);
		btn.setBounds(300, 0, 90, 90);
		ImageIcon icon2 = new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\3.gif");
		JLabel lab2 = new JLabel(icon2);
		lab2.setBounds(5, 0, 30, 30);
		ImageIcon icon3 = new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\default.jpg");
		JLabel lab3 = new JLabel(icon3);
		lab3.setBounds(5, 30, 60, 60);
		ImageIcon icon4 = new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\3.png");
		btn_1 = new JButton(icon4);
		btn_1.setBounds(10, 95, 80, 30);
		btn_8 = new JButton(new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\4.png"));
		btn_8.setBounds(100, 95, 80, 30);
		ImageIcon icon5 = new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\7.png");
		btn_5 = new JButton(icon5);
		btn_5.setBounds(390, 0, 90, 90);
		JLabel lab4 = new JLabel(user.getName());
		lab4.setForeground(Color.WHITE);
		lab4.setBounds(70, 35, 60, 30);
		JLabel lab5 = new JLabel(user.getRole());
		lab5.setForeground(Color.WHITE);
		lab5.setBounds(70, 55, 90, 30);
		mainPane.add(lab4);
		mainPane.add(lab5);
		mainPane.add(lab3);
		mainPane.add(lab2);
		mainPane.add(btn);
		labtemp.setBounds(0, 0, frame.getWidth(), 90);
		menu1.add(mil1);
		menu1.add(mil2);
		menu1.add(mil3);
		menu1.add(mil4);
		menu1.add(mil5);
		menu1.add(mil6);
		mBar.add(menu1);
		mainPane.add(mBar);
		mainPane.add(btn_1);
		mainPane.add(btn_8);
		mainPane.add(btn_5);
		mainPane.add(labtemp);
		mainPane.add(labtemp1);
		mil1.addActionListener(this);
		mil2.addActionListener(this);
		mil3.addActionListener(this);
		mil4.addActionListener(this);
		mil5.addActionListener(this);
		mil6.addActionListener(this);
		btn.addActionListener(this);
		btn_1.addActionListener(this);
		btn_5.addActionListener(this);
		btn_8.addActionListener(this);
	}

	public void res_op() {
		mainPane = new JDesktopPane();
		mainPane.setBackground(new Color(255, 255, 255));
		ImageIcon icon = new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\back.png");
		JLabel labtemp = new JLabel(icon);
		labtemp1 = new JLabel();
		labtemp1.setOpaque(true);
		labtemp1.setBackground(new Color(236, 242, 247));
		labtemp1.setBounds(0, 90, frame.getWidth(), 40);
		mBar = new JMenuBar();
		mBar.setBorderPainted(false);
		mBar.setBackground(new Color(6, 130, 230));
		mBar.setBounds(frame.getWidth() - 110, 0, 35, 20);
		menu1 = new JMenu("菜单");
		mil1 = new JMenuItem("账户");
		mil2 = new JMenuItem("注销");
		mil3 = new JMenuItem("帮助");
		mil4 = new JMenuItem("反馈");
		mil5 = new JMenuItem("关于");
		mil6 = new JMenuItem("退出");
		btn_3 = new JButton("删除用户");
		JButton btn_min = new JButton(new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\winmin.png"));
		btn_min.setBounds(frame.getWidth() - 80, 0, 30, 20);
		JButton btn_win = new JButton(new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\win.png"));
		btn_win.setBounds(frame.getWidth() - 55, 0, 30, 20);
		JButton btn_close = new JButton(new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\winclo.png"));
		btn_close.setBounds(frame.getWidth() - 30, 0, 30, 20);
		btn_min.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setExtendedState(JFrame.ICONIFIED);
			}
		});
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btn_win.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (frame.getWidth() <= 900) {
					frame.setLocation(0, 0);
					frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
					// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} else {
					frame.setLocation(100, 100);
					frame.setSize(900, 650);
				}
				frame.remove(mainPane);
				res_ad();
				frame.add(mainPane);
				frame.validate();
			}
		});

		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		frame.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point p = frame.getLocation();
				frame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
			}
		});
		mainPane.add(btn_min);
		mainPane.add(btn_win);
		mainPane.add(btn_close);
		ImageIcon icon1 = new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\1.png");
		btn = new JButton(icon1);
		btn.setBounds(300, 0, 90, 90);
		ImageIcon icon2 = new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\3.gif");
		JLabel lab2 = new JLabel(icon2);
		lab2.setBounds(5, 0, 30, 30);
		ImageIcon icon3 = new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\default.jpg");
		JLabel lab3 = new JLabel(icon3);
		lab3.setBounds(5, 30, 60, 60);
		ImageIcon icon4 = new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\3.png");
		btn_1 = new JButton(icon4);
		btn_1.setBounds(10, 95, 80, 30);
		btn_8 = new JButton(new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\4.png"));
		btn_8.setBounds(100, 95, 80, 30);
		JLabel lab4 = new JLabel(user.getName());
		lab4.setForeground(Color.WHITE);
		lab4.setBounds(70, 35, 60, 30);
		JLabel lab5 = new JLabel(user.getRole());
		lab5.setForeground(Color.WHITE);
		lab5.setBounds(70, 55, 90, 30);
		mainPane.add(lab4);
		mainPane.add(lab5);
		mainPane.add(lab3);
		mainPane.add(lab2);
		mainPane.add(btn);
		labtemp.setBounds(0, 0, frame.getWidth(), 90);
		menu1.add(mil1);
		menu1.add(mil2);
		menu1.add(mil3);
		menu1.add(mil4);
		menu1.add(mil5);
		menu1.add(mil6);
		mBar.add(menu1);
		mainPane.add(mBar);
		mainPane.add(btn_1);
		mainPane.add(btn_8);
		mainPane.add(labtemp);
		mainPane.add(labtemp1);
		mil1.addActionListener(this);
		mil2.addActionListener(this);
		mil3.addActionListener(this);
		mil4.addActionListener(this);
		mil6.addActionListener(this);
		btn.addActionListener(this);
		btn_1.addActionListener(this);
		btn_8.addActionListener(this);
	}
	public void res_br() {
		mainPane = new JDesktopPane();
		mainPane.setBackground(new Color(255, 255, 255));
		ImageIcon icon = new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\back.png");
		JLabel labtemp = new JLabel(icon);
		labtemp1 = new JLabel();
		labtemp1.setOpaque(true);
		labtemp1.setBackground(new Color(236, 242, 247));
		labtemp1.setBounds(0, 90, frame.getWidth(), 40);
		mBar = new JMenuBar();
		mBar.setBorderPainted(false);
		mBar.setBackground(new Color(6, 130, 230));
		mBar.setBounds(frame.getWidth() - 110, 0, 35, 20);
		menu1 = new JMenu("菜单");
		mil1 = new JMenuItem("账户");
		mil2 = new JMenuItem("注销");
		mil3 = new JMenuItem("帮助");
		mil4 = new JMenuItem("反馈");
		mil5 = new JMenuItem("关于");
		mil6 = new JMenuItem("退出");
		btn_3 = new JButton("删除用户");
		JButton btn_min = new JButton(new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\winmin.png"));
		btn_min.setBounds(frame.getWidth() - 80, 0, 30, 20);
		JButton btn_win = new JButton(new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\win.png"));
		btn_win.setBounds(frame.getWidth() - 55, 0, 30, 20);
		JButton btn_close = new JButton(new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\winclo.png"));
		btn_close.setBounds(frame.getWidth() - 30, 0, 30, 20);
		btn_min.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setExtendedState(JFrame.ICONIFIED);
			}
		});
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btn_win.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (frame.getWidth() <= 900) {
					frame.setLocation(0, 0);
					frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
					// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} else {
					frame.setLocation(100, 100);
					frame.setSize(900, 650);
				}
				frame.remove(mainPane);
				res_ad();
				frame.add(mainPane);
				frame.validate();
			}
		});

		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		frame.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point p = frame.getLocation();
				frame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
			}
		});
		mainPane.add(btn_min);
		mainPane.add(btn_win);
		mainPane.add(btn_close);
		ImageIcon icon1 = new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\1.png");
		btn = new JButton(icon1);
		btn.setBounds(300, 0, 90, 90);
		ImageIcon icon2 = new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\3.gif");
		JLabel lab2 = new JLabel(icon2);
		lab2.setBounds(5, 0, 30, 30);
		ImageIcon icon3 = new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\default.jpg");
		JLabel lab3 = new JLabel(icon3);
		lab3.setBounds(5, 30, 60, 60);
		ImageIcon icon4 = new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\3.png");
		btn_1 = new JButton(icon4);
		btn_1.setBounds(10, 95, 80, 30);
		JLabel lab4 = new JLabel(user.getName());
		lab4.setForeground(Color.WHITE);
		lab4.setBounds(70, 35, 60, 30);
		JLabel lab5 = new JLabel(user.getRole());
		lab5.setForeground(Color.WHITE);
		lab5.setBounds(70, 55, 90, 30);
		mainPane.add(lab4);
		mainPane.add(lab5);
		mainPane.add(lab3);
		mainPane.add(lab2);
		mainPane.add(btn);
		labtemp.setBounds(0, 0, frame.getWidth(), 90);
		menu1.add(mil1);
		menu1.add(mil2);
		menu1.add(mil3);
		menu1.add(mil4);
		menu1.add(mil5);
		menu1.add(mil6);
		mBar.add(menu1);
		mainPane.add(mBar);
		mainPane.add(btn_1);
		mainPane.add(labtemp);
		mainPane.add(labtemp1);
		mil1.addActionListener(this);
		mil2.addActionListener(this);
		mil3.addActionListener(this);
		mil4.addActionListener(this);
		mil5.addActionListener(this);
		mil6.addActionListener(this);
		btn.addActionListener(this);
		btn_1.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) { // 刷新菜单
		if (e.getSource() == mil1) {// 更改信息
			JFrame userframe = new JFrame();
			userframe.setBounds(100, 100, 400, 300);
			JDesktopPane userPane = new JDesktopPane();
			JLabel lb = new JLabel("旧密码");
			JLabel lb_1 = new JLabel("新密码");
			lb.setBounds(20, 40, 40, 20);
			userPane.add(lb);
			lb_1.setBounds(20, 70, 40, 20);
			userPane.add(lb_1);
			passwordField_3 = new JPasswordField();
			passwordField_3.setBounds(70, 40, 120, 20);
			userPane.add(passwordField_3);
			passwordField_4 = new JPasswordField();
			passwordField_4.setBounds(70, 70, 120, 20);
			userPane.add(passwordField_4);
			JButton bt = new JButton("确定");
			bt.setBounds(20, 200, 80, 20);
			userPane.add(bt);
			JButton bt_c = new JButton("取消");
			bt_c.setBounds(240, 200, 80, 20);
			userPane.add(bt_c);
			bt_c.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent g) {
					userframe.remove(userPane);
					userframe.dispose();
				}
			});
			bt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent g) {
					String oldPass = new String(passwordField_3.getPassword());
					String newPass = new String(passwordField_4.getPassword());
					try {
						if (user.changeSelfInfo(oldPass, newPass)) {
							JLabel tl = new JLabel("更改成功");
							tl.setBounds(133, 176, 54, 15);
							mainPane.add(tl);
							passwordField_3.setText("");
							passwordField_4.setText("");
						} else {
							JLabel tl = new JLabel("更改失败");
							tl.setBounds(133, 176, 54, 15);
							mainPane.add(tl);
							passwordField_3.setText("");
							passwordField_4.setText("");
						}
					} catch (IllegalStateException g1) {
						System.out.println("Error in excecuting Query");
						System.exit(0);
					} catch (SQLException g1) {
						System.out.println("Not Connected to Database");
						g1.printStackTrace();
						System.exit(0);
					}
				}
			});
			userframe.add(userPane);
			userframe.setVisible(true);
		} else if (e.getSource() == mil2) {
			frame.remove(mainPane);
			frame.setBounds(100, 100, 450, 300);
			if (flag == 2) {
				res_ad();
			} else if (flag == 1) {
				res_op();
			} else if (flag == 0) {
				res_br();
			}
			user = null;
			frame.add(desktopPane);
			frame.validate();
		} else if (e.getSource() == mil3) { 
		} else if (e.getSource() == mil4) {

		} else if (e.getSource() == mil5) {

		} else if (e.getSource() == mil6) {// 关闭程序
			System.exit(0);
		} else if (e.getSource() == btn) { // 文件列表
			frame.remove(mainPane);
			if (flag == 3) {
				res_ad();
			} else if (flag == 2) {
				res_op();
			} else if (flag == 1) {
				res_br();
			}
			Doc[] m = null;
			try {
				m = user.showFilelist();
				String[] column = { "编号", "所有者", "时间", "描述", "文件名" };
				Object[][] data = new Object[m.length][5];
				for (int i = 0; i < m.length; i++) {
					data[i][0] = m[i].getNumber();
					data[i][1] = m[i].getOwner();
					data[i][2] = m[i].getTimestamp();
					data[i][3] = m[i].getDescription();
					data[i][4] = m[i].getPath();
				}
				list_ad = new JTable(data, column);
			} catch (IllegalStateException | SQLException e1) {
				e1.printStackTrace();
			}
			list_ad.setBounds(150, 150, frame.getWidth() - 200 - 20, m.length * 20);
			JScrollPane scrollPane = new JScrollPane(list_ad);
			scrollPane.getViewport().setBackground(Color.WHITE);
			scrollPane.setBounds(0, 130, frame.getWidth(), frame.getHeight() - 130);
			mainPane.add(scrollPane);
			frame.add(mainPane);
			frame.validate();
		} else if (e.getSource() == btn_1) { // 下载文件
			frame.remove(mainPane);
			if (flag == 2) {
				res_ad();
			} else if (flag == 1) {
				res_op();
			} else if (flag == 0) {
				res_br();
			}
			if (list_ad.getSelectedRow() != -1) {
				String str = (String) list_ad.getValueAt(list_ad.getSelectedRow(), 0);
				String downFile = str;
				JFrame fe = new JFrame();
				fe.setBounds(100, 100, 450, 300);
				fe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JFileChooser dlg = new JFileChooser();
				dlg.setApproveButtonText("确定");
				dlg.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				dlg.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						File file = dlg.getSelectedFile();
						if (file != null) {
							try {
								if (user.downloadFile(downFile, file.getAbsolutePath())) {
								} else {
								}
							} catch (IllegalStateException | SQLException e1) {
								e1.printStackTrace();
							}
						}
						fe.setVisible(false);
					}
				});
				File file = dlg.getSelectedFile();
				if (file != null)
					System.out.println(file.getAbsolutePath());
				dlg.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fe.add(dlg);
				fe.setVisible(true);
			}
			frame.add(mainPane);
			btn.doClick();
			frame.validate();
		} else if (e.getSource() == btn_2) { // 更改用户
			String role1 = null;
			String name = null;
			if (li_2.getSelectedRow() != -1) {
				role1 = (String) li_2.getValueAt(li_2.getSelectedRow(), 1);
				name = (String) li_2.getValueAt(li_2.getSelectedRow(), 0);
			}
			JFrame frame_user = new JFrame();
			frame_user.setBounds(100, 100, 400, 300);
			JDesktopPane userPane = new JDesktopPane();
			JLabel lblNewLabel_2 = new JLabel("用户");
			lblNewLabel_2.setBounds(20, 20, 40, 20);
			userPane.add(lblNewLabel_2);
			JLabel lblNewLabel_3 = new JLabel("密码");
			lblNewLabel_3.setBounds(20, 50, 40, 20);
			userPane.add(lblNewLabel_3);
			textField_2 = new JTextField();
			textField_2.setBounds(70, 20, 105, 20);
			if(name != null){
				textField_2.setText(name);
			}
			userPane.add(textField_2);
			passwordField_2 = new JPasswordField();
			passwordField_2.setBounds(70, 50, 105, 20);
			userPane.add(passwordField_2);
			String m[] = { "administrator", "operator", "browser" };
			li_1 = new JList<String>(m);
			li_1.setBounds(20, 80, 350, 60);
			userPane.add(li_1);
			if(role1 != null){
				switch(role1){
				case "administrator":li_1.setSelectedIndex(0);break;
				case "operator":li_1.setSelectedIndex(1);break;
				case "browser":li_1.setSelectedIndex(2);break;
				default:
					li_1.clearSelection();
				}
			}
			JButton bt = new JButton("确定");
			bt.setBounds(20, 210, 80, 20);
			userPane.add(bt);
			JButton bt_c = new JButton("取消");
			bt_c.setBounds(250, 210, 80, 20);
			userPane.add(bt_c);
			bt_c.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent g) {
					frame_user.remove(userPane);
					frame_user.dispose();
				}
			});
			bt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent g) {
					String name = textField_2.getText();
					String pass = new String(passwordField_2.getPassword());
					String role = null;
					role = li_1.getSelectedValue().toString();
					System.out.println(name + pass + role);
					// if(name != null && pass != null && role != null){
					try {
						if (user.changeUserInfo(name, pass, role)) {
							btn_5.doClick();
							JLabel tl = new JLabel("更改成功");
							tl.setBounds(frame.getWidth() - 60, frame.getHeight() - 25, 55, 20);
							mainPane.add(tl);
							textField_2.setText("");
							passwordField_2.setText("");
							li_1.clearSelection();
							frame_user.dispose();
						} else {
							JLabel tl = new JLabel("更改失败");
							tl.setBounds(frame.getWidth() - 60, frame.getHeight() - 25, 55, 20);
							mainPane.add(tl);
							textField_2.setText("");
							passwordField_2.setText("");
							li_1.clearSelection();
							frame_user.dispose();
						}
					} catch (IllegalStateException | SQLException e) {
						e.printStackTrace();
					}
				}
			});
			frame_user.add(userPane);
			frame_user.setVisible(true);
		} else if (e.getSource() == btn_3) { // 删除用户
			frame.remove(mainPane);
			if (flag == 2) {
				res_ad();
			} else if (flag == 1) {
				res_op();
			} else if (flag == 0) {
				res_br();
			}
			String role1 = null;
			if (li_2.getSelectedRow() != -1) {
				role1 = (String) li_2.getValueAt(li_2.getSelectedRow(), 1);
				String n = (String) li_2.getValueAt(li_2.getSelectedRow(), 0);
				if (n.equals(user.getName())) {
					return;
				}
				System.out.println(role1);
				try {
					if (user.deUser(n)) {
						li_2.clearSelection();
						btn_5.doClick();
					} else {
						li_2.clearSelection();
					}
				} catch (IllegalStateException | SQLException s) {
					s.printStackTrace();
				}
			}
			frame.add(mainPane);
			frame.validate();
		} else if (e.getSource() == btn_4) { // 添加用户
			JFrame frame_user = new JFrame();
			frame_user.setBounds(100, 100, 400, 300);
			JDesktopPane userPane = new JDesktopPane();
			JLabel lblNewLabel_2 = new JLabel("用户");
			lblNewLabel_2.setBounds(20, 20, 40, 20);
			userPane.add(lblNewLabel_2);
			JLabel lblNewLabel_3 = new JLabel("密码");
			lblNewLabel_3.setBounds(20, 50, 40, 20);
			userPane.add(lblNewLabel_3);
			textField_2 = new JTextField();
			textField_2.setBounds(70, 20, 105, 20);
			userPane.add(textField_2);
			passwordField_2 = new JPasswordField();
			passwordField_2.setBounds(70, 50, 105, 20);
			userPane.add(passwordField_2);
			String m[] = { "administrator", "operator", "browser" };
			li_1 = new JList<String>(m);
			li_1.setBounds(20, 80, 350, 60);
			userPane.add(li_1);
			JButton bt = new JButton("确定");
			bt.setBounds(20, 210, 80, 20);
			userPane.add(bt);
			JButton bt_c = new JButton("取消");
			bt_c.setBounds(250, 210, 80, 20);
			userPane.add(bt_c);
			bt_c.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent g) {
					frame_user.remove(userPane);
					frame_user.dispose();
				}
			});
			bt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent g) {
					String name = textField_2.getText();
					String pass = new String(passwordField_2.getPassword());
					String role = null;
					role = li_1.getSelectedValue().toString();
					System.out.println(name + pass + role);
					// if(name != null && pass != null && role != null){
					try {
						if (user.addUser(name, pass, role)) {
							JLabel tl = new JLabel("添加成功");
							tl.setBounds(frame.getWidth() - 60, frame.getHeight() - 25, 55, 20);
							mainPane.add(tl);
							textField_2.setText("");
							passwordField_2.setText("");
							li_1.clearSelection();
							frame_user.dispose();
							btn_5.doClick();
						} else {
							JLabel tl = new JLabel("添加失败");
							tl.setBounds(frame.getWidth() - 60, frame.getHeight() - 25, 55, 20);
							mainPane.add(tl);
							textField_2.setText("");
							passwordField_2.setText("");
							li_1.clearSelection();
							frame_user.dispose();
						}
					} catch (IllegalStateException | SQLException e) {
						e.printStackTrace();
					}
				}
			});
			frame_user.add(userPane);
			frame_user.setVisible(true);
		} else if (e.getSource() == btn_5) { // 用户列表
			frame.remove(mainPane);
			if (flag == 3) {
				res_ad();
			} else if (flag == 2) {
				res_op();
			} else if (flag == 1) {
				res_br();
			}
			btn_3 = new JButton(new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\5.png"));
			btn_3.setBounds(190, 5, 80, 30);
			labtemp1.add(btn_3);
			btn_3.addActionListener(this);
			btn_4 = new JButton(new ImageIcon("E:\\资料\\java\\Multithreaded-programming\\icon\\10.png"));
			btn_4.setBounds(280, 5, 80, 30);
			labtemp1.add(btn_4);
			btn_4.addActionListener(this);
			btn_2 = new JButton("更改");
			btn_2.setBounds(370, 5, 80, 30);
			btn_2.addActionListener(this);
			btn_2.setBackground(new Color(236, 242, 247));
			labtemp1.add(btn_2);
			User[] m = null;
			try {
				m = user.listUser();
				String[] column = { "用户", "类型" };
				Object[][] data = new Object[m.length][2];
				for (int i = 0; i < m.length; i++) {
					data[i][0] = m[i].getName();
					data[i][1] = m[i].getRole();
				}
				li_2 = new JTable(data, column);
			} catch (IllegalStateException | SQLException e1) {
				e1.printStackTrace();
			}
			li_2.setBounds(150, 150, frame.getWidth() - 200 - 20, m.length * 20);
			JScrollPane scrollPane = new JScrollPane(li_2);
			scrollPane.getViewport().setBackground(Color.WHITE);
			scrollPane.setBounds(0, 130, frame.getWidth(), frame.getHeight() - 130);
			mainPane.add(scrollPane);
			frame.add(mainPane);
			frame.validate();
		} else if (e.getSource() == btn_8) { // 上传文件
			JFrame frame_up = new JFrame();
			frame_up.setBounds(200, 200, 450, 300);
			frame_up.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			JDesktopPane upPane = new JDesktopPane();
			JButton btnNewButton_11 = new JButton("选择文件");
			btnNewButton_11.setBounds(20, 20, 90, 20);
			upPane.add(btnNewButton_11);
			btnNewButton_11.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent g) {
					JFrame fe = new JFrame();
					fe.setBounds(100, 100, 450, 300);
					JFileChooser dlg = new JFileChooser();
					dlg.setFileSelectionMode(JFileChooser.FILES_ONLY);
					dlg.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							file = dlg.getSelectedFile();
							if (file != null) {
								JLabel ll = new JLabel(file.getAbsolutePath());
								ll.setBounds(100, 20, frame_up.getWidth() - 100, 20);
								upPane.add(ll);
								fe.setVisible(false);
							}
						}
					});
					file = dlg.getSelectedFile();
					JLabel ll1 = new JLabel("编号");
					ll1.setBounds(20, 60, 50, 20);
					upPane.add(ll1);
					JLabel ll2 = new JLabel("描述");
					ll2.setBounds(20, 100, 50, 20);
					upPane.add(ll2);
					tf_01 = new JTextField();
					tf_01.setBounds(80, 60, 200, 20);
					upPane.add(tf_01);
					tf_02 = new TextArea();
					tf_02.setBounds(80, 100, 200, 80);
					upPane.add(tf_02);
					JButton btn01 = new JButton("上传");
					JButton btn02 = new JButton("取消");
					btn01.setBounds(160, 200, 60, 20);
					btn02.setBounds(230, 200, 60, 20);
					upPane.add(btn01);
					upPane.add(btn02);
					btn02.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame_up.dispose();
						}
					});
					btn01.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (file != null) {
								String ID = tf_01.getText();
								String description = tf_02.getText();
								System.out.println(file.getAbsolutePath());
								System.out.println(ID + description);
								try {
									// if(user != null)
									if (user.uploadFile(ID, description, file.getAbsolutePath())) {
										tf_01.setText("");
										tf_02.setText("");
										file = null;
										frame_up.dispose();
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
			frame_up.add(upPane);
			frame_up.setVisible(true);
		} else if (e.getSource() == button) {
			String name = textField.getText();
			String password = new String(passwordField.getPassword());
			try {
				user = DataProcessing.searchUser(name, password);
				if (user != null) {
					System.out.println(user.getRole());
					switch (user.getRole()) {
					case "administrator":
						user = new Administrator(name, password, user.getRole());
						flag = 3;
						break;
					case "operator":
						user = new Operator(name, password, user.getRole());
						flag = 2;
						break;
					case "browser":
						user = new Browser(name, password, user.getRole());
						flag = 1;
						break;
					}
					frame.remove(desktopPane);
					frame.setBounds(100, 100, 900, 650);
					textField.setText("");
					passwordField.setText("");
					if (flag == 3) {
						res_ad();
					} else if (flag == 2) {
						res_op();
					} else if (flag == 1) {
						res_br();
					}
					frame.setResizable(true);
					frame.add(mainPane);
					frame.validate();
				} else {
					JLabel label = new JLabel("用户名或者密码错误!");
					label.setForeground(Color.red);
					label.setBounds(150, 85, 120, 120);
					desktopPane.add(label);
					frame.validate();
				}
			} catch (IllegalStateException g) {
				System.out.println("Error in excecuting Query");
				System.exit(0);
			} catch (SQLException g) {
				System.out.println("Not Connected to Database");
				g.printStackTrace();
				System.exit(0);
			}
		} else if (e.getSource() == button_1) {
			System.exit(0);
		}
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
		if (e.getSource() == passwordField) {
			if (e.getKeyChar() == '\n') {
				button.doClick();
			}
		}
	}
}
