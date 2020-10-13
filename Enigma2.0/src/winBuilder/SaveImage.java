package winBuilder;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class SaveImage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtName;
	private JLabel labelIcon;
	private String s;
	private JButton btnMenu;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaveImage frame = new SaveImage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SaveImage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtAreaDesc = new JTextArea("Description", 100, 100);
		JScrollPane pane = new JScrollPane(txtAreaDesc);
		pane.setBounds(288, 159, 125, 75);
		contentPane.add(pane);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filechooser = new JFileChooser();
				filechooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "gif", "png");
				filechooser.addChoosableFileFilter(filter);
				int result = filechooser.showSaveDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = filechooser.getSelectedFile();
					String path = selectedFile.getAbsolutePath();
					Image image = resizeImage(path, 600, 300);
					ImageIcon icon = new ImageIcon(image);
					labelIcon.setIcon(icon);	
					s = path;
				}
			}
		});
		btnBrowse.setFont(btnBrowse.getFont().deriveFont(btnBrowse.getFont().getStyle() | Font.BOLD));
		btnBrowse.setBounds(30, 160, 89, 23);
		contentPane.add(btnBrowse);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(btnSave.getFont().deriveFont(btnSave.getFont().getStyle() | Font.BOLD));
		btnSave.setBounds(30, 194, 89, 23);
		contentPane.add(btnSave);
		
		txtId = new JTextField();
		txtId.setText("Id");
		txtId.setToolTipText("");
		txtId.setBounds(160, 161, 86, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtName = new JTextField();
		txtName.setText("Name");
		txtName.setBounds(160, 195, 86, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		labelIcon = new JLabel("");
		labelIcon.setBounds(0, 0, 434, 148);
		contentPane.add(labelIcon);
		
		btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Menu menu = new Menu();
				menu.setVisible(true);
			}
		});
		btnMenu.setFont(btnMenu.getFont().deriveFont(btnMenu.getFont().getStyle() | Font.BOLD));
		btnMenu.setBounds(30, 228, 89, 23);
		contentPane.add(btnMenu);
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String url = "jdbc:mysql://localhost:3306/Enigma?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GB";
					String user = "root";
					String password = "Clamart77";
					Connection con = DriverManager.getConnection(url, user, password);
					
					PreparedStatement ps = con.prepareStatement("INSERT INTO images(id, name, description, image) VALUES(?, ?, ?, ?)");
					InputStream is = new FileInputStream(new File(s));
					ps.setString(1,  txtId.getText());
					ps.setString(2, txtName.getText());
					ps.setString(3, txtAreaDesc.getText());
					ps.setBlob(4, is);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data inserted");
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	public Image resizeImage(String path, int newWidth, int newHeight) {
		Image oldImage = Toolkit.getDefaultToolkit().getImage(path);
		Image newImage = oldImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
		return newImage;
	}
}
