import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class maindemo {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					maindemo window = new maindemo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public maindemo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 707, 479);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(20, 20, 222, 21);
		desktopPane.add(menuBar);
		
		JMenu mnCaidan = new JMenu("caidan");
		menuBar.add(mnCaidan);
		
		JMenu menu = new JMenu("New menu");
		menuBar.add(menu);
		
		JMenuItem mil1 = new JMenuItem("asd");
		mnCaidan.add(mil1);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(30, 51, 93, 23);
		desktopPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(30, 89, 93, 23);
		desktopPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(30, 132, 93, 23);
		desktopPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(30, 176, 93, 23);
		desktopPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setBounds(30, 220, 93, 23);
		desktopPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("New button");
		btnNewButton_5.setBounds(30, 272, 93, 23);
		desktopPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("New button");
		btnNewButton_6.setBounds(30, 316, 93, 23);
		desktopPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("New button");
		btnNewButton_7.setBounds(30, 359, 93, 23);
		desktopPane.add(btnNewButton_7);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(30, 26, 54, 15);
		desktopPane.add(lblNewLabel);
		
		JList list = new JList();
		list.setBounds(354, 72, 1, 1);
		desktopPane.add(list);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(133, 176, 54, 15);
		desktopPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(268, 93, 54, 15);
		desktopPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(268, 118, 54, 15);
		desktopPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(354, 90, 105, 21);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(354, 115, 105, 21);
		desktopPane.add(passwordField);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		chckbxNewCheckBox.setBounds(252, 157, 103, 23);
		desktopPane.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("New check box");
		chckbxNewCheckBox_1.setBounds(369, 157, 103, 23);
		desktopPane.add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("New check box");
		chckbxNewCheckBox_2.setBounds(496, 157, 103, 23);
		desktopPane.add(chckbxNewCheckBox_2);
		
		 
	}
}
