import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;

public class LoginGUI {

	public static int choice;
	public String username;
	JFrame frame = new JFrame();
	JPanel panel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		LoginGUI start = new LoginGUI();
	}

	/**
	 * Create the frame.
	 */
	public void Login() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.add(contentPane);
		contentPane.setLayout(null);
		
		Button btnNew = new Button("New User");
		btnNew.setBounds(150, 81, 120, 30);
		contentPane.add(btnNew);
		
		Button btnReturn = new Button("Returning User");
		btnReturn.setBounds(150, 115, 120, 30);
		contentPane.add(btnReturn);
		
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.remove(contentPane);
				createNewUser();
			}
		});
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.remove(contentPane);
			}
		});
		frame.setVisible(true);
	}
	public LoginGUI()
	{
		Login();
		MapGUI map=new MapGUI();
	}
	public void createNewUser()
	{
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.add(panel);
		panel.setLayout(null);
		
	}
}
