
//d
/**
 * @author lopezy2
 * @version 1.0
 * @created 09-Oct-2015 3:16:18 PM
 */

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author lopezy2
 *
 */
public class MapGUI extends LoginGUI {

	int zoom=15;
	public static int lostCount=0;
	String gpsHome="29.210815,-81.022833";
	String gpsUser="29.19072,-81.048074";
	String newPath="&path=color:red|weight:5";
	String lostL1="29.184838,-81.070304";
	String lostL2="29.179255,-81.056056";
	String lostL3="29.221924,-81.005716";
	public InfoGUI m_InfoGUI;
	
	JFrame frame1= new JFrame();
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;
    BufferedImage image;
    
	String ECName;
	String ECNum;
	String ECMail;

    
        
    /** This does stuff
     * @param gpsUser I have no idea what this does
     * @param gpsHome Also no idea...
     * @param zoomPer wat?
     */
    public void show(boolean startQ) {
           try {
        	     if(startQ==true&&lostCount==0){
      	   		 	//starting image
      	   		 	image = ImageIO.read(new URL("http://maps.google.com/maps/api/staticmap?center="+gpsUser+"&path=color:0x0000ff|weight:5|"+gpsUser+"|"+gpsHome+"&zoom="+zoom+"&markers=size:mid%7Ccolor:blue%7Clabel:L%7C"+lostL1+"|"+lostL2+"|"+lostL3+"&markers=size:mid%7Ccolor:green%7Clabel:U%7C"+gpsUser+"&markers=size:mid%7Ccolor:red%7Clabel:H%7C"+gpsHome+"&size=800x600&sensor=TRUE_OR_FALSE")); 
      	   	 	 }else if(startQ==true&&lostCount>0){
      	   	 		//display lost path URL
      	   	 		image = ImageIO.read(new URL("http://maps.google.com/maps/api/staticmap?center="+gpsUser+newPath+"&path=color:0x0000ff|weight:5|"+gpsUser+"|"+gpsHome+"&zoom="+zoom+"&markers=size:mid%7Ccolor:blue%7Clabel:L%7C"+lostL1+"|"+lostL2+"|"+lostL3+"&markers=size:mid%7Ccolor:green%7Clabel:U%7C"+gpsUser+"&markers=size:mid%7Ccolor:red%7Clabel:H%7C"+gpsHome+"&size=800x600&sensor=TRUE_OR_FALSE")); 
      	   	 	 }else{
      	   	 		//display new location map update
      	   	 		image = ImageIO.read(new URL("http://maps.google.com/maps/api/staticmap?center="+gpsUser+"&path=color:0x0000ff|weight:5|"+gpsUser+"|"+gpsHome+"&zoom="+zoom+"&markers=size:mid%7Ccolor:blue%7Clabel:L%7C"+lostL1+"|"+lostL2+"|"+lostL3+"&markers=size:mid%7Ccolor:green%7Clabel:U%7C"+gpsUser+"&markers=size:mid%7Ccolor:red%7Clabel:H%7C"+gpsHome+"&size=800x600&sensor=TRUE_OR_FALSE"));
      	   	 	 }
        	     frame1.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
         	     panel1=new JPanel();
        	  	 panel2=new JPanel();
        	  	 panel3=new JPanel();
       	  	 	 panel4=new JPanel();
         	     JLabel label = new JLabel(new ImageIcon(image));
         	     panel1.add(label);
         	     panel2.setLayout(new GridLayout(3,1));
        	     panel3.setLayout(new GridLayout(7,1));
        	     panel4.setLayout(new GridLayout(2,1));
        	     Button start=new Button("Start");
                 Button stop=new Button("Stop");
                 Button zIn=new Button("Zoom: (+)");
                 Button zOut=new Button("Zoom: (-)");
                 Button info=new Button("Information");
                 Button lost=new Button("Lost");
                 Button move=new Button("Simulate Movement (5 mins)");
                 Button change=new Button("Change Address");
                 Button ECstuff=new Button("Change EC info");
                 panel2.add(start);
                 panel2.add(change);
                 panel2.add(ECstuff);
             	 panel3.add(stop);
             	 panel3.add(move);
                 panel3.add(lost);
                 panel3.add(info);
                 panel3.add(zIn);
                 panel3.add(zOut);
                 if(startQ==true){
                	panel4.add(panel2);
                	panel4.remove(panel3);
                 }else{
                 	panel4.remove(panel2);
                 	panel4.add(panel3);
                 }
                 frame1.add(panel1);
                 frame1.add(panel4);
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
                	 try {
						info();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                 });
                 start.addActionListener((e)->{
                	 start();
                 });
                 stop.addActionListener((e)->{
                	 stop();
                 });
                 move.addActionListener((e)->{
                	 move();
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
   
    
    private void move() {
    	parseLocation();
    	frame1.remove(panel1);
		frame1.remove(panel4);
		show(false);
	}

    private void stop(){
    	frame1.remove(panel1);
		frame1.remove(panel4);
		show(true);		
	}

	private BufferedImage parseLocation(){
    	//String gpsUser="29.21,-81.03";
		
		//get new image for new GPSUser location
		//String waste;
		//FileReader read = new FileReader(username + ".txt");
		//BufferedReader buff = new BufferedReader(read);
		//waste = buff.readLine();
		//waste = buff.readLine();      x7
		//waste = buff.readLine();
		//waste = buff.readLine();
		//home = buff.readLine();
		//home = home.replaceAll(" ","+"); 
		//buff.close();
		
		
    	//get new image for new GPSUser location
		//add new path coordinate to path string
		//for(0 to 329)
		//	intnum+"/"
		//newPath=newPath+"|"+pathCoordinate;
		
		try{
			image = ImageIO.read(new URL("http://maps.google.com/maps/api/staticmap?center="+gpsUser+"&path=color:0x0000ff|weight:5|"+gpsUser+"|"+gpsHome+"&zoom="+zoom+"&markers=size:mid%7Ccolor:blue%7Clabel:L%7C"+lostL1+"|"+lostL2+"|"+lostL3+"&markers=size:mid%7Ccolor:green%7Clabel:U%7C"+gpsUser+"&markers=size:mid%7Ccolor:red%7Clabel:H%7C"+gpsHome+"&size=800x600&sensor=TRUE_OR_FALSE"));    
	    }catch (MalformedURLException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		return image;
	}

	private void start() {
		 frame1.remove(panel1);
		 frame1.remove(panel4);
   	     show(false);
	}

    
	public MapGUI(){
    	 show(true);
    }
	
	/**
	 * @throws IOException 
	 * 
	 */
	private void info() throws IOException{
		BufferedReader read = new BufferedReader(new FileReader(username + ".txt"));
		String name;
		String number;
		String email;
		String waste;
		//CALCULATING DISTANCE BETWEEN USER AND HOME:
		String homeLatLon[] = gpsHome.split(",");
		String userLatLon[] = gpsUser.split(",");
		double hLat=Double.parseDouble(homeLatLon[0]);
		double hLon=Double.parseDouble(homeLatLon[1]);
		double uLat=Double.parseDouble(userLatLon[0]);
		double uLon=Double.parseDouble(userLatLon[1]);	
		double hLatRDiff = (hLat-uLat)*Math.PI/180;
		double hLonRDiff = (hLon-uLon)*Math.PI/180;
		double uLatR = uLat*Math.PI/180;
		double hLatR = hLat*Math.PI/180;
		double a = Math.pow(Math.sin(hLatRDiff/2.0),2)+Math.cos(uLatR)*Math.cos(hLatR)*Math.pow(Math.sin(hLonRDiff/2.0),2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double distance = 6378000 * c;
		double distanceKM = distance - distance%1;
		double distanceM = (distanceKM*0.621371)/1000;
		distanceM = distanceM - ((distanceM*100)%1)/100;
		// new DatabaseManipulation().readData().get(1).toString()+
		waste = read.readLine();
		name = read.readLine();
		number = read.readLine();
		email = read.readLine();
		read.close();
		try {
			JOptionPane.showMessageDialog(null,"User: "+ username +"\nLocation: "+gpsUser+"\nEmergency Contact: "+name+"\nEmail: "+email+"\nSMS#: "+number+"\nDistance from Home: "+distanceM+" (m.)", "Information", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e){
			e.printStackTrace();
		}
		frame1.remove(panel1);
		frame1.remove(panel4);
		show(false);
	}
 
	private void pressIfLost(){
		lostCount++;
		JOptionPane.showMessageDialog(null, "Please remain at your location, An alert has been sent to your Emergency Contacts\nYou have been lost "+lostCount+" times.", "Emergency: Lost", JOptionPane.WARNING_MESSAGE);
		
		try {
			Email email = new Email("alhse300@gmail.com", "kfqggjpjqvtyesep");
			email.setBody(username+" is lost.");
			email.setSubject("Alzheimer Little Helper.");
			email.setFrom("alhse300@gmail.com");
			email.setTo(new String[]{"y3ssgl0@gmail.com"});
			if(email.send()){
				System.out.println("Email sent!");
			}
			else{
				System.out.println("Email not sent.");
			}
		}
		 catch (Exception e) {
			System.out.println("Email failed to send!");
		 }
		
		frame1.remove(panel1);
		frame1.remove(panel4);
		show(true);
	}

	private void zoomIn(){
		zoom=zoom+1;
		frame1.remove(panel1);
		frame1.remove(panel4);
		show(false);
	}

    private void zoomOut(){
    	zoom=zoom-1;
    	frame1.remove(panel1);
		frame1.remove(panel4);
		show(false);		
	}
	
    public void changeAddress(String stuff) throws IOException
    {
    	File f = new File(username + ".txt");
    	File trash = new File("temp.txt");
    	String waste;
    	trash.createNewFile();
		PrintWriter write = new PrintWriter("temp.txt");
		BufferedReader read = new BufferedReader(new FileReader(username + ".txt"));
		BufferedReader read1 = new BufferedReader(new FileReader(username + ".txt"));
		
		write.println(read.readLine());
		waste = read1.readLine();
		write.println(read.readLine());
		waste = read1.readLine();
		write.println(read.readLine());
		waste = read1.readLine();
		write.println(read.readLine());
		waste = read1.readLine();
		write.println(stuff);
		waste = read.readLine();
		waste = read1.readLine();
		
		while(read1.readLine() != null)
		{
			write.println(read.readLine());
		}
		
		write.close();
		read.close();
		read1.close();
		gpsHome = stuff.replaceAll(" ","+");
		f.delete();
		trash.renameTo(f);
		JOptionPane.showMessageDialog(null, "Address successfully changed");
		frame1.remove(panel1);
		frame1.remove(panel2);
		show(false);
		
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
		BufferedReader read1 = new BufferedReader(new FileReader(username + ".txt"));
		
		write.println(read.readLine());
		waste = read1.readLine();
		write.println(name);
		write.println(number);
		write.println(email);
		waste = read.readLine();
		waste = read.readLine();
		waste = read.readLine();
		
		waste = read1.readLine();
		waste = read1.readLine();
		waste = read1.readLine();
		
		while(read1.readLine() != null)
		{
			write.println(read.readLine());
		}
		
		write.close();
		read.close();
		f.delete();
		trash.renameTo(f);
		JOptionPane.showMessageDialog(null, "Emergency Contact info successfully changed");
	}
	
}//end MapGUI
