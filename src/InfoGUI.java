package src;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 * @author lopezy2
 * @version 1.0
 * @created 09-Oct-2015 3:16:18 PM
 */
public class InfoGUI extends MapGUI{

	private String ECName;
	private String ECNum;
	protected static String ECMail;
	

	/**
	 * @throws IOException throw exception for when the file is not found
	 * 
	 * Initializes the information screen
	 */
	public InfoGUI() throws IOException{
		getInformation();
		displayInfoGUI();
	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable{

	}

	/**
	 * Displays all of the information about the user as well as buttons that allow user to update info or return to map
	 */
	public void displayInfoGUI(){
		frame1.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
	    panel1=new JPanel();
	 	panel2=new JPanel();
	    panel1.setLayout(new GridLayout(7,1));
	    JLabel lab1 = new JLabel("Emergency contact name : "+ ECName);
	    JLabel lab2 = new JLabel("Emergency contact number : "+ ECNum);
	    JLabel lab3 = new JLabel("Emergency contact email : "+ ECMail);
	    JLabel lab4 = new JLabel("Home location : "+ HomeLocation);
	    JLabel lab5 = new JLabel("Most common lost locations :");
	    JLabel lab6 = new JLabel("\t - 29.210815,-81.022833");
	    JLabel lab7 = new JLabel("\t - 29.210815,-81.022833");
	    panel1.add(lab1);
	    panel1.add(lab2);
	    panel1.add(lab3);
	    panel1.add(lab4);
	    panel1.add(lab5);
	    panel1.add(lab6);
	    panel1.add(lab7);
	    panel2.setLayout(new GridLayout(2,1));
        Button a=new Button("Update");
        Button b=new Button("Map");
        panel2.add(a);
        panel2.add(b);                           
        frame1.add(panel1);
        frame1.add(panel2);
        
        frame1.setTitle("Alzheimer Little Helper Application -> Information");
        frame1.pack();
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
        
        b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.remove(panel1);
				frame1.remove(panel2);
				returnMap();
			}
		});
	}

	/**
	 * Has the user enter updated emergency contact information
	 */
	public void  inputEmergencyContact(){

	}

	/**
	 * Returns user to the map screen
	 * @throws IOException 
	 */
	public void returnMap() throws IOException{
		MapGUI map = new MapGUI();
	}

	/**
	 * @throws IOException throw exception for when the file is not found
	 * 
	 * reads the users file to obtain the relevant information it needs to display
	 */
	public void getInformation() throws IOException
	{
		String waste;
		FileReader read = new FileReader(username + ".txt");
		BufferedReader buff = new BufferedReader(read);
		waste = buff.readLine();
		ECName = buff.readLine();
		ECNum = buff.readLine();
		ECMail = buff.readLine();
		buff.close();
	}
}//end InfoGUI
