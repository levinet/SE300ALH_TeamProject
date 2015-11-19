//
//import java.awt.Button;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.PasswordAuthentication;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.SwingConstants;
//import javax.swing.border.EmptyBorder;
//
//public class LoginGUI {
//
//	public static int choice;
//	public static String username;
//	public static String password;
//	private static int num;
//	public static int count;
//	public static String info;
//	public static String home;
//	
//	JFrame frame = new JFrame();
//	JPanel panel;
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		
//		LoginGUI start = new LoginGUI();
//	}
//	
//	/**
//	 * Creates the window and asks if the user is new or returning
//	 */
//	public void Login() {
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setBounds(600, 300, 450, 300);
//		panel = new JPanel();
//		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
//		frame.add(panel);
//		panel.setLayout(null);
//		
//		Button btnNew = new Button("New User");
//		btnNew.setBounds(150, 81, 120, 30);
//		panel.add(btnNew);
//		
//		Button btnReturn = new Button("Returning User");
//		btnReturn.setBounds(150, 115, 120, 30);
//		panel.add(btnReturn);
//		
//		btnNew.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				frame.remove(panel);
//				num = 1;
//				getInfo("Enter your desired username");
//			}
//		});
//		btnReturn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				frame.remove(panel);
//				num = 2;
//				getInfo("Enter your username");
//			}
//		});
//		frame.setVisible(true);
//	}
//	
//	
//	/**
//	 * Creates the class that initializes the code
//	 */
//	public LoginGUI()
//	{
//		Login();
//	}
//	
//	
//	/**
//	 * @param text shows what the user must enter into the text field
//	 * 
//	 * This is the place where the user enters their username
//	 */
//	public void getInfo(String text)
//	{
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setBounds(600, 300, 450, 300);
//		panel = new JPanel();
//		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
//		panel.setLayout(null);
//		JLabel instruction = new JLabel(text);
//		instruction.setBounds(0, 81, 450, 30);
//		instruction.setHorizontalAlignment(SwingConstants.CENTER);
//		final JTextField user = new JTextField(15);
//		user.setBounds(120, 111, 210, 30);
//		JButton accept = new JButton("Enter");
//		accept.setBounds(120, 141, 210, 30);
//		panel.add(instruction);
//		panel.add(user);
//		panel.add(accept);
//		frame.add(panel);
//		
//		accept.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				username = new String(user.getText());
//				File f = new File(username + ".txt");
//				if(!f.exists()  && num == 1)
//				{
//					try {
//						f.createNewFile();
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					frame.remove(panel);
//					getPassword("Enter your password");
//				} 
//				else if (num == 1)
//				{
//					frame.remove(panel);
//					getInfo("Username taken. Please Enter another");
//				}
//				try {
//					if(f.createNewFile() && num ==2)
//					{
//						f.delete();
//						frame.remove(panel);
//						getInfo("Username does not exist. Enter again");
//					}
//					else if(num == 2)
//					{
//						count = 3;
//						frame.remove(panel);
//						getPassword("Enter your password");
//					}
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		});
//		frame.setVisible(true);
//	}
//	
//	
//	/**
//	 * @param text is tells the user to do, in this portion lets the user know how many more attempts to enter a correct password
//	 * 
//	 * This is where the user can enter their password
//	 */
//	public void getPassword(String text)
//	{
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setBounds(600, 300, 450, 300);
//		panel = new JPanel();
//		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
//		panel.setLayout(null);
//		JLabel instruction = new JLabel(text);
//		instruction.setBounds(0, 81, 450, 30);
//		instruction.setHorizontalAlignment(SwingConstants.CENTER);
//		final JTextField pass = new JTextField(15);
//		pass.setBounds(120, 111, 210, 30);
//		JButton accept = new JButton("Enter");
//		accept.setBounds(120, 141, 210, 30);
//		panel.add(instruction);
//		panel.add(pass);
//		panel.add(accept);
//		frame.add(panel);
//		
//		accept.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				password = new String(pass.getText());
//				if(num == 1)
//				{
//					try {
//						frame.remove(panel);
//						createPassword();
//					} catch (FileNotFoundException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
//				if(num == 2)
//				{
//					try {
//						frame.remove(panel);
//						checkPassword();
//					} catch (FileNotFoundException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
//			}
//		});
//		frame.setVisible(true);
//	}
//	
//	
//	/**
//	 * @throws FileNotFoundException This is a throw if the file is not found
//	 * 
//	 * creates a password in the new users file
//	 */
//	public void createPassword() throws FileNotFoundException
//	{
//		PrintWriter write = new PrintWriter(username + ".txt");
//		write.println(password);
//		write.close();
//		getContact("Please enter Emergency Contact name");
//	}
//	
//	
//	/**
//	 * @throws IOException This is a throw if the file is not found
//	 * 
//	 * This checks to see if the password the returning user entered was correct
//	 */
//	public void checkPassword() throws IOException
//	{
//		String check;
//		FileReader read = new FileReader(username + ".txt");
//		BufferedReader buff = new BufferedReader(read);
//		check = buff.readLine();
//		buff.close();
//		if (count > 0 && check.equals(password)!= true)
//		{
//			count = count - 1;
//			getPassword("Password incorrect. "+ (count+1) +" tries remaining");
//		}
//		else
//		{
//			getHome();
//			MapGUI map = new MapGUI();
//		}
//		
//	}
//	
//	
//	
//	/**
//	 * @param text Tells the user what info the user must enter
//	 * 
//	 * This method tells the user which information about either their emergency contact or home location they need to enter
//	 */
//	public void getContact(String text)
//	{
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setBounds(600, 300, 450, 300);
//		panel = new JPanel();
//		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
//		panel.setLayout(null);
//		JLabel instruction = new JLabel(text);
//		instruction.setBounds(0, 81, 450, 30);
//		instruction.setHorizontalAlignment(SwingConstants.CENTER);
//		final JTextField pass = new JTextField(15);
//		pass.setBounds(120, 111, 210, 30);
//		JButton accept = new JButton("Enter");
//		accept.setBounds(120, 141, 210, 30);
//		panel.add(instruction);
//		panel.add(pass);
//		panel.add(accept);
//		frame.add(panel);
//		accept.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				info = new String(pass.getText());
//				try {
//					frame.remove(panel);
//					writeInfo();
//				} catch (FileNotFoundException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		});
//		frame.setVisible(true);
//	}
//	
//	
//	/**
//	 * @throws IOException Throw exception for when the file does not exist
//	 * 
//	 * This writes the new information to the file
//	 */
//	public void writeInfo() throws IOException
//	{
//		FileWriter write = new FileWriter(username + ".txt",true);
//		BufferedWriter buff = new BufferedWriter(write);
//		buff.write(info);
//		buff.newLine();
//		buff.close();
//		if(num == 1)
//		{
//			
//			num++;
//			getContact("Please enter Emergency Contact number");
//		}
//		else if (num == 2)
//		{
//			num++;
//			getContact("Please enter Emergency Contact email");
//			
//		}
//		else if (num == 3)
//		{
//			num++;
//			getContact("Please enter home address");
//		}
//		else
//		{
//			home = info.replaceAll(" ","+");
//			frame.setVisible(false);
//			MapGUI map = new MapGUI();
//		}
//	}
//	
//	
//	/**
//	 * @throws IOException throw exception for when the file is not found
//	 * 
//	 * Gets the home location of the returning user so the map can display properly
//	 */
//	public void getHome() throws IOException
//	{
//		String waste;
//		int i;
//		FileReader read = new FileReader(username + ".txt");
//		BufferedReader buff = new BufferedReader(read);
//		waste = buff.readLine();
//		waste = buff.readLine();
//		waste = buff.readLine();
//		waste = buff.readLine();
//		home = buff.readLine();
//		home = home.replaceAll(" ","+"); 
//		buff.close();
//	}
//
//=======

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LoginGUI {

	public static int choice;
	public static String username;
	public static String password;
	private static int num;
	public static int count;
	public static String info;
	public static String home;
	
	JFrame frame = new JFrame();
	JPanel panel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		LoginGUI start = new LoginGUI();
	}
	
	/**
	 * Creates the window and asks if the user is new or returning
	 */
	public void Login() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(600, 300, 450, 300);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.add(panel);
		panel.setLayout(null);
		
		Button btnNew = new Button("New User");
		btnNew.setBounds(150, 81, 120, 30);
		panel.add(btnNew);
		
		Button btnReturn = new Button("Returning User");
		btnReturn.setBounds(150, 115, 120, 30);
		panel.add(btnReturn);
		
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.remove(panel);
				num = 1;
				getInfo("Enter your desired username");
			}
		});
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.remove(panel);
				num = 2;
				getInfo("Enter your username");
			}
		});
		frame.setVisible(true);
	}
	
	
	/**
	 * Creates the class that initializes the code
	 */
	public LoginGUI()
	{
		Login();
	}
	
	
	/**
	 * @param text shows what the user must enter into the text field
	 * 
	 * This is the place where the user enters their username
	 */
	public void getInfo(String text)
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(600, 300, 450, 300);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		JLabel instruction = new JLabel(text);
		instruction.setBounds(0, 81, 450, 30);
		instruction.setHorizontalAlignment(SwingConstants.CENTER);
		final JTextField user = new JTextField(15);
		user.setBounds(120, 111, 210, 30);
		JButton accept = new JButton("Enter");
		accept.setBounds(120, 141, 210, 30);
		panel.add(instruction);
		panel.add(user);
		panel.add(accept);
		frame.add(panel);
		
		accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = new String(user.getText());
				File f = new File(username + ".txt");
				if(!f.exists()  && num == 1)
				{
					try {
						f.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					frame.remove(panel);
					getPassword("Enter your password");
				} 
				else if (num == 1)
				{
					frame.remove(panel);
					getInfo("Username taken. Please Enter another");
				}
				try {
					if(f.createNewFile() && num ==2)
					{
						f.delete();
						frame.remove(panel);
						getInfo("Username does not exist. Enter again");
					}
					else if(num == 2)
					{
						count = 3;
						frame.remove(panel);
						getPassword("Enter your password");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame.setVisible(true);
	}
	
	
	/**
	 * @param text is tells the user to do, in this portion lets the user know how many more attempts to enter a correct password
	 * 
	 * This is where the user can enter their password
	 */
	public void getPassword(String text)
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(600, 300, 450, 300);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		JLabel instruction = new JLabel(text);
		instruction.setBounds(0, 81, 450, 30);
		instruction.setHorizontalAlignment(SwingConstants.CENTER);
		final JTextField pass = new JTextField(15);
		pass.setBounds(120, 111, 210, 30);
		JButton accept = new JButton("Enter");
		accept.setBounds(120, 141, 210, 30);
		panel.add(instruction);
		panel.add(pass);
		panel.add(accept);
		frame.add(panel);
		
		accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				password = new String(pass.getText());
				if(num == 1)
				{
					try {
						frame.remove(panel);
						createPassword();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(num == 2)
				{
					try {
						frame.remove(panel);
						checkPassword();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		frame.setVisible(true);
	}
	
	
	/**
	 * @throws FileNotFoundException This is a throw if the file is not found
	 * 
	 * creates a password in the new users file
	 */
	public void createPassword() throws FileNotFoundException
	{
		PrintWriter write = new PrintWriter(username + ".txt");
		write.println(password);
		write.close();
		getContact("Please enter Emergency Contact name");
	}
	
	
	/**
	 * @throws IOException This is a throw if the file is not found
	 * 
	 * This checks to see if the password the returning user entered was correct
	 */
	public void checkPassword() throws IOException
	{
		String check;
		FileReader read = new FileReader(username + ".txt");
		BufferedReader buff = new BufferedReader(read);
		check = buff.readLine();
		buff.close();
		if (count > 0 && check.equals(password)!= true)
		{
			count = count - 1;
			getPassword("Password incorrect. "+ (count+1) +" tries remaining");
		}
		else
		{
			getHome();
			MapGUI map = new MapGUI();
		}
		
	}
	
	
	
	/**
	 * @param text Tells the user what info the user must enter
	 * 
	 * This method tells the user which information about either their emergency contact or home location they need to enter
	 */
	public void getContact(String text)
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(600, 300, 450, 300);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		JLabel instruction = new JLabel(text);
		instruction.setBounds(0, 81, 450, 30);
		instruction.setHorizontalAlignment(SwingConstants.CENTER);
		final JTextField pass = new JTextField(15);
		pass.setBounds(120, 111, 210, 30);
		JButton accept = new JButton("Enter");
		accept.setBounds(120, 141, 210, 30);
		panel.add(instruction);
		panel.add(pass);
		panel.add(accept);
		frame.add(panel);
		accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				info = new String(pass.getText());
				try {
					frame.remove(panel);
					writeInfo();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame.setVisible(true);
	}
	
	
	/**
	 * @throws IOException Throw exception for when the file does not exist
	 * 
	 * This writes the new information to the file
	 */
	public void writeInfo() throws IOException
	{
		FileWriter write = new FileWriter(username + ".txt",true);
		BufferedWriter buff = new BufferedWriter(write);
		buff.write(info);
		buff.newLine();
		buff.close();
		if(num == 1)
		{
			
			num++;
			getContact("Please enter Emergency Contact number");
		}
		else if (num == 2)
		{
			num++;
			getContact("Please enter Emergency Contact email");
			
		}
		else if (num == 3)
		{
			num++;
			getContact("Please enter home address");
		}
		else
		{
			home = info.replaceAll(" ","+");
			frame.setVisible(false);
			MapGUI map = new MapGUI();
		}
	}
	
	
	/**
	 * @throws IOException throw exception for when the file is not found
	 * 
	 * Gets the home location of the returning user so the map can display properly
	 */
	public void getHome() throws IOException
	{
		String waste;
		int i;
		FileReader read = new FileReader(username + ".txt");
		BufferedReader buff = new BufferedReader(read);
		waste = buff.readLine();
		waste = buff.readLine();
		waste = buff.readLine();
		waste = buff.readLine();
		home = buff.readLine();
		home = home.replaceAll(" ","+"); 
		buff.close();
	}

}
