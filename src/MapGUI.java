


//d
/**
 * @author lopezy2
 * @version 1.0
 * @created 09-Oct-2015 3:16:18 PM
 */

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * @author lopezy2
 *
 */
public class MapGUI extends LoginGUI {

	private double commonLocation;
	private double Coordinates;
	
	protected double HomeLocation;
	private char lostinfo;
	int zoom=15;
	String gpsUser="29.210815,-81.022833";
	String gpsHome=home;
	String lostL1="29.184838,-81.070304";
	String lostL2="29.179255,-81.056056";
	
	String lostL3="29.221924,-81.005716";
	public InfoGUI m_InfoGUI;

	JFrame frame1= new JFrame();
    JPanel panel1;
    JPanel panel2;
    BufferedImage image;
    
        
    /** This does stuff
     * @param gpsUser I have no idea what this does
     * @param gpsHome Also no idea...
     * @param zoomPer wat?
     */
    public void show() {
           try {
        	     image = ImageIO.read(new URL("http://maps.google.com/maps/api/staticmap?center="+gpsUser+"&path=color:0x0000ff|weight:5|"+gpsUser+"|"+gpsHome+"&zoom="+zoom+"&markers=size:mid%7Ccolor:blue%7Clabel:L%7C"+lostL1+"|"+lostL2+"|"+lostL3+"&markers=size:mid%7Ccolor:green%7Clabel:U%7C"+gpsUser+"&markers=size:mid%7Ccolor:red%7Clabel:H%7C"+gpsHome+"&size=800x600&sensor=TRUE_OR_FALSE"));
        	  	 frame1.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
         	     panel1=new JPanel();
        	  	 panel2=new JPanel();
         	     JLabel label = new JLabel(new ImageIcon(image));
         	     panel1.add(label);
         	     panel2.setLayout(new GridLayout(6,1));
                 Button zIn=new Button("Zoom: (+)");
                 Button zOut=new Button("Zoom: (-)");
                 Button info=new Button("Information");
                 Button lost=new Button("Press if LOST");
                 Button change=new Button("Change Address");
                 Button ECstuff=new Button("Change EC info");
                 panel2.add(lost);
                 panel2.add(info);
                 panel2.add(zIn);
                 panel2.add(zOut); 
                 panel2.add(change);
                 panel2.add(ECstuff);
                 frame1.add(panel1);
                 frame1.add(panel2);
                 zIn.addActionListener((e)->{
                  	zoomIn();
                 });
                 zOut.addActionListener((e)->{
               	  	zoomOut();
               	 });
                 lost.addActionListener((e)->{
                	 pressIfLost();
                 });
                 info.addActionListener((e)->{
                	 info();
                 });
                 change.addActionListener((e)->{
                	 try {
						getNewAddress();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                 });
                 change.addActionListener((e)->{
                	 try {
						getECstuff();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                 });
                 
                 frame1.setTitle("Alzheimer Little Helper Application -> MAP");
                 frame1.pack();
                 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 frame1.setLocationRelativeTo(null);
                 frame1.setVisible(true);
           	   	       
           }
           catch (MalformedURLException e){
                  e.printStackTrace();
           }
           catch (Exception e){
                 e.printStackTrace();
           }
    }
   
	public MapGUI(){
    	 show();
       	
    }
	
	/**
	 * 
	 */
	private void info(){
		//Get gpsUser in double:
		//Get gpsHome in double:
		//Get distance between coordinates:
		String homeLatLon[] = gpsHome.split(",");
		String homeLat=homeLatLon[0];
		String homeLon=homeLatLon[1];
		String userLatLon[] = gpsUser.split(",");
		String userLat=homeLatLon[0];
		String userLon=homeLatLon[1];
		
		/*int earthRadius = 6371000; // Meters.
		double dLat = (homeLatLon[0] - userLatLon[0]).toRad();
		double dLon = (lon2-lon1).toRad();
		double lat1 = lat1.toRad();
		double lat2 = lat2.toRad();

		var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		        Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2); 
		var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		var d = R * c;*/
		
		
		JOptionPane.showMessageDialog(null, "User: BLANK \nLocation: "+gpsUser+"\nEmergency Contact: BLANK\nEmail: BLANK\nSMS#: BLANK\n", "Information", JOptionPane.INFORMATION_MESSAGE);
/*
		try {
			JOptionPane.showMessageDialog(null, new DatabaseManipulation().readData().get(1).toString(), "Information", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
*/
		
	}
 
	private void pressIfLost(){
		JOptionPane.showMessageDialog(null, "Please remain at your location, An alert has been sent to your Emergency Contacts", "Emergency: Lost", JOptionPane.WARNING_MESSAGE);
		
	}

	private void zoomIn(){
		zoom=zoom+1;
		frame1.remove(panel1);
		frame1.remove(panel2);
		show();
	}

    private void zoomOut(){
    	zoom=zoom-1;
    	frame1.remove(panel1);
		frame1.remove(panel2);
		show();		
	}
	
    public void changeAddress(String stuff) throws IOException
    {
    	File f = new File(username + ".txt");
    	File trash = new File("temp.txt");
    	String waste;
    	trash.createNewFile();
		PrintWriter write = new PrintWriter("temp.txt");
		BufferedReader read = new BufferedReader(new FileReader(username + ".txt"));
		
		write.println(read.readLine());
		write.println(read.readLine());
		write.println(read.readLine());
		write.println(read.readLine());
		write.println(stuff);
		waste = read.readLine();
		
		while(write.println(read.readLine() != null))
		{
		}
		
		write.close();
		read.close();
		gpsHome = stuff.replaceAll(" ","+");
		f.delete();
		trash.renameTo(f);
		JOptionPane.showMessageDialog(null, "Address successfully changed");
		frame1.remove(panel1);
		frame1.remove(panel2);
		show();
		
    }
	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable{

	}

	public boolean displayLost(){
		return false;
	}

	public double homeLine(){
		return 0;
	}
	
	public void getNewAddress() throws IOException
	{
		String info = JOptionPane.showInputDialog("Ener new Address");
		changeAddress(info);
	}
	String ECName;
	String ECNum;
	String ECMail;
	
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
	
	public void getECstuff() throws IOException
	{
		String name = JOptionPane.showInputDialog("Ener new Emergency Contact name");
		String number = JOptionPane.showInputDialog("Ener new Emergency Contact number");
		String email = JOptionPane.showInputDialog("Ener new Emergency Contact email");
		
		File f = new File(username + ".txt");
    	File trash = new File("temp.txt");
    	String waste;
    	trash.createNewFile();
		PrintWriter write = new PrintWriter("temp.txt");
		BufferedReader read = new BufferedReader(new FileReader(username + ".txt"));
		
		write.println(read.readLine());
		write.println(name);
		write.println(number);
		write.println(email);
		waste = read.readLine();
		waste = read.readLine();
		waste = read.readLine();
		
		while(write.println(read.readLine() != null))
		{
		}
		
		write.close();
		read.close();
		f.delete();
		trash.renameTo(f);
		JOptionPane.showMessageDialog(null, "Emergency Contact info successfully changed");
	}
	
}//end MapGUI
