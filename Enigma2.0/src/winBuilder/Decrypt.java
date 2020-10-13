package winBuilder;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Decrypt extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField inputField;
	private static JTextField resultField;
	private static int key = -6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Decrypt frame = new Decrypt();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Decrypt() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label2 = new JLabel("Result :");
		label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD));
		label2.setBounds(10, 150, 94, 14);
		contentPane.add(label2);
		
		JLabel label = new JLabel("Enter value :");
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD));
		label.setBounds(10, 60, 78, 14);
		contentPane.add(label);
		
		inputField = new JTextField();
		inputField.setColumns(10);
		inputField.setBounds(10, 172, 414, 20);
		contentPane.add(inputField);
		
		resultField = new JTextField();
		resultField.setColumns(10);
		resultField.setBounds(10, 85, 414, 20);
		contentPane.add(resultField);
		
		JButton btnDec = new JButton("Decrypt");
		btnDec.setFont(btnDec.getFont().deriveFont(btnDec.getFont().getStyle() | Font.BOLD));
		btnDec.setBounds(10, 116, 94, 23);
		contentPane.add(btnDec);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Menu menu = new Menu();
				menu.setVisible(true);
			}
		});
		btnMenu.setFont(btnMenu.getFont().deriveFont(btnMenu.getFont().getStyle() | Font.BOLD));
		btnMenu.setBounds(316, 11, 89, 23);
		contentPane.add(btnMenu);
		
		JButton btnSave = new JButton("Save value");
		btnSave.setFont(btnSave.getFont().deriveFont(btnSave.getFont().getStyle() | Font.BOLD));
		btnSave.setBounds(10, 203, 94, 23);
		contentPane.add(btnSave);
	}
	
	public static void decryptString(String e) {
		char[] chars = e.toCharArray();
		String result = "";
		for(char c: chars) {
			c -= key;
			result = result + String.valueOf(c);
		}
		System.out.println(result);
		resultField.setText(result);
	}
	
	public static void saveString(String e) {
		if(e != null) {
			try {
				String url = "jdbc:mysql://localhost:3306/Enigma?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GB";
				String user = "root";
				String password = "Clamart77";
				Connection con = DriverManager.getConnection(url, user, password);
				Statement stmt = con.createStatement();
				
				int stringId ;
				String sql = "SELECT MAX(string_id) FROM savedStrings";
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()) {
					stringId = rs.getInt(1)+1;
					String result = resultField.getText();
					Statement stmt2 = con.createStatement();
					String sql2 = "INSERT INTO savedStrings VALUES('"+ stringId + "', '"+ result+ "', '" + key + "');";
					stmt2.executeUpdate(sql2);
					JOptionPane.showMessageDialog(null, "Saved");
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "Enter value");
		}
	}

}
