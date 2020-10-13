package winBuilder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel labelMenu = new JLabel("Menu");
		labelMenu.setFont(labelMenu.getFont().deriveFont(labelMenu.getFont().getStyle() | Font.BOLD));
		panel.add(labelMenu);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login.main(null);
			}
		});
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnExit.setFont(btnExit.getFont().deriveFont(btnExit.getFont().getStyle() | Font.BOLD));
		panel_1.add(btnExit);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		JButton btnEnc = new JButton("Encrypt");
		btnEnc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Encrypt encrypt = new Encrypt();
				encrypt.setVisible(true);
			}
		});
		btnEnc.setFont(btnEnc.getFont().deriveFont(btnEnc.getFont().getStyle() | Font.BOLD));
		btnEnc.setBounds(38, 37, 89, 23);
		panel_2.add(btnEnc);
		
		JButton btnDec = new JButton("Decrypt");
		btnDec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Decrypt decrypt = new Decrypt();
				decrypt.setVisible(true);
			}
		});
		btnDec.setFont(btnDec.getFont().deriveFont(btnDec.getFont().getStyle() | Font.BOLD));
		btnDec.setBounds(300, 37, 89, 23);
		panel_2.add(btnDec);

		JButton btnConsult = new JButton("Consult");
		btnConsult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Consult consult = new Consult();
				consult.setVisible(true);
			}
		});
		btnConsult.setFont(btnConsult.getFont().deriveFont(btnConsult.getFont().getStyle() | Font.BOLD));
		btnConsult.setBounds(300, 133, 89, 23);
		panel_2.add(btnConsult);
		
		JButton btnImage = new JButton("Image");
		btnImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				SaveImage image = new SaveImage();
				image.setVisible(true);
			}
		});
		btnImage.setFont(btnImage.getFont().deriveFont(btnImage.getFont().getStyle() | Font.BOLD));
		btnImage.setBounds(38, 133, 89, 23);
		panel_2.add(btnImage);
	}

}
