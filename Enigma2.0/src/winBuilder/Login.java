package winBuilder;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login {

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
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(61, 33, 305, 183);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(168, 53, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(lblNewLabel.getFont().getStyle() | Font.BOLD));
		lblNewLabel.setBounds(44, 56, 71, 14);
		panel.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(lblPassword.getFont().deriveFont(lblPassword.getFont().getStyle() | Font.BOLD));
		lblPassword.setBounds(44, 99, 71, 14);
		panel.add(lblPassword);
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if("Enter".equals(e.getActionCommand())) {
					String inputLogin = textField.getText();
					String inputPassword = new String(passwordField.getPassword());
					try {
						String url = "jdbc:mysql://localhost:3306/Enigma?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GB";
						String user = "root";
						String passwordRoot = "Clamart77";
						Connection con = DriverManager.getConnection(url, user, passwordRoot);
			//			System.out.println("Success");
						
						Statement stmt = con.createStatement();
						
						String sql = "SELECT login, password FROM user WHERE login = '"+ inputLogin + "' and password = '"+ inputPassword +"';";
						ResultSet rs = stmt.executeQuery(sql);
						if(rs.next()) {
							JOptionPane.showMessageDialog(null, "Success");
							Menu menu = new Menu();
							frame.setVisible(false);
							menu.setVisible(true);
							
						}else {
							JOptionPane.showMessageDialog(null, "Incorect username or password");
						}	
					}catch(Exception c){
						c.printStackTrace();
					}
				}	
			}
		});
		btnNewButton.setFont(btnNewButton.getFont().deriveFont(btnNewButton.getFont().getStyle() | Font.BOLD));
		btnNewButton.setBounds(107, 140, 89, 23);
		panel.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(168, 96, 86, 20);
		panel.add(passwordField);
	}
}
