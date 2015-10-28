
//d
/**
 * @author lopezy2
 * @version 1.0
 * @created 09-Oct-2015 3:16:18 PM
 */

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	String gpsHome="29.19072,-81.048074";
	String lostL1="29.184838,-81.070304";
	String lostL2="29.179255,-81.056056";
	
	String lostL3="29.221924,-81.005716";
	public InfoGUI m_InfoGUI;

	JFrame frame1= new JFrame();
    JPanel panel1;
    JPanel panel2;
    BufferedImage image;
    
    public static void main(String[] args) {
		new MapGUI();
	}
    
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
         	     panel2.setLayout(new GridLayout(4,1));
                 Button zIn=new Button("Zoom: (+)");
                 Button zOut=new Button("Zoom: (-)");
                 Button info=new Button("Information");
                 Button lost=new Button("Press if LOST");
                 panel2.add(lost);
                 panel2.add(info);
                 panel2.add(zIn);
                 panel2.add(zOut);                           
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
		
		
//		JOptionPane.showMessageDialog(null, "User: BLANK \nLocation: "+gpsUser+"\nEmergency Contact: BLANK\nEmail: BLANK\nSMS#: BLANK\n", "Information", JOptionPane.INFORMATION_MESSAGE);
		
		try {
			JOptionPane.showMessageDialog(null, new DatabaseManipulation().readData().get(1).toString(), "Information", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
}//end MapGUI
