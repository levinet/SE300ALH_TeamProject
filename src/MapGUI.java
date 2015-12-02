package src;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * The Class MapGUI.
 *
 * @author lopezy2
 */

/**
 * @author yessenia
 *
 */
public class MapGUI extends LoginGUI {
	
	int zoom=15;	
	public static int lostCount=0;	
	public String gpsHome=home;	
	public String gpsUser;	
	String nPath="&path=color:red|weight:5";	
	String newPath;	
	public String lostL1;	
	public String lostL2;	
	public String lostL3;	
	int walter=0;	
	boolean startQ=true;	
	JFrame frame1= new JFrame();
    JPanel panel1;   
    JPanel panel2;    
    JPanel panel3;  
    JPanel panel4;    
    JPanel panel5;  
    BufferedImage image;   
    BufferedImage image2;
	String ECName;
	String ECNum;
	String ECMail;
       
    /**
     * @param startQ the start q
     * @param gpsUser the gps user
     */
		
    /**
     * @param startQ
     * @param gpsUser
     */
    public void show(boolean startQ, String gpsUser) {
           try {
        	     if(startQ==true&&lostCount==0){
      	   		 	//starting image
      	   		 	image = ImageIO.read(new URL("http://maps.google.com/maps/api/staticmap?center="+gpsHome+"&zoom="+zoom+"&markers=size:mid%7Ccolor:red%7Clabel:H%7C"+gpsHome+"&size=800x600&sensor=TRUE_OR_FALSE&maptype=hybrid")); 
      	   		 	
      	   	 	 }else if(startQ==true&&lostCount>0){
      	   	 		//display lost path image
      	   	 		image = ImageIO.read(new URL("http://maps.google.com/maps/api/staticmap?center="+gpsUser+newPath+"&path=color:0x0000ff|weight:5|"+gpsUser+"|"+gpsHome+"&zoom="+zoom+"&markers=size:mid%7Ccolor:blue%7Clabel:L%7C"+lostL1+"|"+lostL2+"|"+lostL3+"&markers=size:mid%7Ccolor:green%7Clabel:U%7C"+gpsUser+"&markers=size:mid%7Ccolor:red%7Clabel:H%7C"+gpsHome+"&size=800x600&sensor=TRUE_OR_FALSE&maptype=hybrid")); 
      	   	 	 }else{
      	   	 		//display map
      	   	 		image = ImageIO.read(new URL("http://maps.google.com/maps/api/staticmap?center="+gpsUser+"&path=color:0x0000ff|weight:5|"+gpsUser+"|"+gpsHome+"&zoom="+zoom+"&markers=size:mid%7Ccolor:blue%7Clabel:L%7C"+lostL1+"|"+lostL2+"|"+lostL3+"&markers=size:mid%7Ccolor:green%7Clabel:U%7C"+gpsUser+"&markers=size:mid%7Ccolor:red%7Clabel:H%7C"+gpsHome+"&size=800x600&sensor=TRUE_OR_FALSE&maptype=hybrid"));
      	   	 	 }
        	     image2 = ImageIO.read(new File("Capture.PNG")) ;
        	     frame1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
         	     panel1=new JPanel();
        	  	 panel2=new JPanel();
        	  	 panel3=new JPanel();
       	  	 	 panel4=new JPanel();
       	  	 	 panel5 =new JPanel();
       	  	 	 JLabel label2 = new JLabel(new ImageIcon(image2));
         	     JLabel label = new JLabel(new ImageIcon(image));
         	     panel1.add(label);
         	     panel2.setLayout(new GridLayout(3,1));
        	     panel3.setLayout(new GridLayout(6,1));
        	     panel4.setLayout(new GridLayout(2,1));
        	     Button start=new Button("Start");
                 Button stop=new Button("Stop");
                 Button zIn=new Button("Zoom: (+)");
                 Button zOut=new Button("Zoom: (-)");
                 Button info=new Button("Information");
                 Button lost=new Button("Lost");
                 Button change=new Button("Change Home");
                 Button ECstuff=new Button("Change EC info");
                 panel2.add(start);
                 panel2.add(change);
                 panel2.add(ECstuff);
             	 panel3.add(stop);
                 panel3.add(lost);
                 panel3.add(info);
                 panel3.add(zIn);
                 panel3.add(zOut);
                 panel5.add(label2);
                 if(startQ==true){
                	panel4.add(panel2);
                	panel4.remove(panel3);
                 }else{
                 	panel4.remove(panel2);
                 	panel4.add(panel3);
                 }
                 frame1.add(panel5);
                 frame1.add(panel4);
                 frame1.add(panel1);
                 zIn.addActionListener((e)->{
                  	zoomIn();
                 });
                 zOut.addActionListener((e)->{
               	  	zoomOut();
               	 });
                 lost.addActionListener((e)->{
                	 try {
						pressIfLost();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
                	 try {
						start();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                 });
                 stop.addActionListener((e)->{
                	 stop();
                 });
                 change.addActionListener((e)->{
                	 try {
						getNewAddress();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                 });
                 ECstuff.addActionListener((e)->{
                	 try {
						getECstuff();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                 });
                 panel4.setBackground(new Color(173,216,230));
                 panel3.setBackground(new Color(173,216,230));
                 frame1.getContentPane().setBackground(new Color(173,216,230));
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
   
    /**
     * Stop.
     */
    private void stop(){
    	frame1.remove(panel1);
		frame1.remove(panel4);
		frame1.remove(panel5);
		show(true, gpsUser);		
	}

	/**
	 * Parses the location.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void parseLocation() throws IOException{
    	 FileReader read9 = new FileReader(username + ".txt");
		 BufferedReader buff9 = new BufferedReader(read9);
		 int countgpsPath=0;
		 int countcomparePath=0;
		 while(buff9.readLine()!=null){
			 countgpsPath++;
		 }
		 buff9.close();
		 FileReader read10 = new FileReader(username + ".txt");
		 BufferedReader buff10 = new BufferedReader(read10);
		 String waster="";
		 while(countcomparePath<=countgpsPath-61){
			 waster=waster+buff10.readLine();
			 countcomparePath++;
		 }
		 int arrayindex=0;
		 String ArrayLocation[]=new String[60];
		 while(countcomparePath<=countgpsPath-1){
			 String gpspathinc[]= buff10.readLine().split("/");
			 String gpspathcor[]=gpspathinc[1].split(",");
			 String pathgps = gpspathcor[1]+","+gpspathcor[0];
			 ArrayLocation[arrayindex]=pathgps;
			 countcomparePath++;
			 arrayindex++;
		 }
		 String path=null;
		 buff10.close();
		 for(int p=0;p<60;p++){
			 path=path+"|"+ArrayLocation[p];
		 }
		 newPath= nPath + path;

		//gpsUser=ArrayLocation[walter];
		//walter++;
		
	}

	/**
	 * Start.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void start() throws IOException {
		 FileReader read3 = new FileReader(username + ".txt");
		 BufferedReader buff3 = new BufferedReader(read3);
		 String wasteq;
		 int countgpsUser=7;
		 int countcompare=0;
		 wasteq=buff3.readLine();
		 wasteq=buff3.readLine();
		 wasteq=buff3.readLine();
		 wasteq=buff3.readLine();
		 wasteq=buff3.readLine();
 		 lostL1=buff3.readLine();
		 lostL2=buff3.readLine();
		 lostL3=buff3.readLine();
		 while(buff3.readLine()!=null){
			 countgpsUser++;
		 }
		 buff3.close();
		 String wastew="";
		 FileReader read7 = new FileReader(username + ".txt");
		 BufferedReader buff7 = new BufferedReader(read7);
		 while(countcompare<countgpsUser-1){
			 wastew=wastew+buff7.readLine();
			 countcompare++;
		 }
		 String falseUser[]=buff7.readLine().split("/");
         String user[]=falseUser[1].split(",");
         gpsUser=user[1]+","+user[0];
		 buff7.close();
		 frame1.remove(panel1);
		 frame1.remove(panel4);
		 frame1.remove(panel5);
   	     show(false, gpsUser);
	}

    
	/**
	 * Instantiates a new map gui.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public MapGUI() throws IOException{
    	show(startQ,gpsUser);
    }
	
	/**
	 * Info.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	/**
	 * @throws IOException
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
		frame1.remove(panel5);
		show(false, gpsUser);
	}
 
	/**
	 * Press if lost.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void pressIfLost() throws IOException{
		lostCount++;
		parseLocation();
		frame1.remove(panel1);
		frame1.remove(panel4);
		frame1.remove(panel5);
		show(true, gpsUser);
		
		BufferedReader read = new BufferedReader(new FileReader(username + ".txt"));
		String name;
		String number;
		String email;
		String waste;
		waste = read.readLine();
		name = read.readLine();
		number = read.readLine();
		email = read.readLine();
		read.close();
		
		JOptionPane.showMessageDialog(null, "Please remain at your location.\nAn alert has been sent to your Emergency Contacts\nYou have been lost "+lostCount+" times in the last "+lostCount+" minute(s).\nAn Email has been sent to "+email+".\nA message has been sent to "+number+".", "Emergency: Lost", JOptionPane.WARNING_MESSAGE);
		
		//send email
		final String user = "alhse300@gmail.com";
        final String password = "gkqnlvvjemiilpna";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
          });

        try {
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
    		//initiate email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("alhse300@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("y3ssgl0@gmail.com"));
            message.setSubject("ALH ALERT!!!!");
            message.setText("Dear "+name+","
                + "\n "+ username +" has been lost at Location: "+gpsUser+"\n They are "+distanceM+" (m.) from home:");

            Transport.send(message);

           System.out.println("Done");

        } 
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }
		
		//Send SMS message
        String homeLatLon[] = gpsHome.split(",");
		String userLatLon[] = gpsUser.split(",");
		double hLat=Double.parseDouble(homeLatLon[0]);
		double hLon=Double.parseDouble(homeLatLon[1]);
		double uLat=Double.parseDouble(userLatLon[0]);
		double uLon=Double.parseDouble(userLatLon[1]);
		double distanceM = findDistance(hLat, hLon,uLat,uLon);
		
		
		String strAccountId = "CI00168303"; // Put your AccountId here
		String strEmail = "y3ssgl0@gmail.com"; // Put your Email address here
												// (Used for authentication and replies)
		String strPassword = "rVhnm5B6"; // Put your Password here
		String strMSISDN = number; // Put a recipient mobile number here
		String strMessage = "Dear "+name+","
                + "\n "+ username +" has been lost at Location: "+gpsUser+"\n They are "+distanceM+" (m.) from home:";
 // Put your SMSmessage text here
															
		int nResult;
		StringBuffer strResponse = new StringBuffer();

		nResult = MessageSMS.SendSMS(strAccountId, strEmail, strPassword, strMSISDN, strMessage, strResponse);

		System.out.println("SMS sent = " + nResult + "\n");
		System.out.println("Response Text = " + strResponse + "\n");
		
	}

	/**
	 * Zoom in.
	 */
	private void zoomIn(){
		zoom=zoom+1;
		frame1.remove(panel1);
		frame1.remove(panel4);
		frame1.remove(panel5);
		show(false,gpsUser);
	}

    /**
     * Zoom out.
     */
    private void zoomOut(){
    	zoom=zoom-1;
    	frame1.remove(panel1);
		frame1.remove(panel4);
		 frame1.remove(panel5);
		show(false,gpsUser);		
	}
	
    /**
     * Change address.
     *
     * @param stuff the stuff
     * @throws IOException Signals that an I/O exception has occurred.
     */
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
		if(f.delete())
		{
			trash.renameTo(new File(username + ".txt"));
		}
		JOptionPane.showMessageDialog(null, "Address successfully changed.");
		frame1.remove(panel5);
		frame1.remove(panel1);
		frame1.remove(panel4);
		show(false,gpsUser);
		
    }
	
	/**
	 * Finalize.
	 *
	 * @exception Throwable the throwable
	 */
	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	public void finalize()
	  throws Throwable{

	}

	/**
	 * Display lost.
	 *
	 * @return true, if successful
	 */
	public boolean displayLost(){
		return false;
	}

	/**
	 * Home line.
	 *
	 * @return the double
	 */
	public double homeLine(){
		return 0;
	}
	
	/**
	 * Gets the new address.
	 *
	 * @return the new address
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void getNewAddress() throws IOException
	{
		String info = JOptionPane.showInputDialog("Enter new Address");
		changeAddress(info);
	}
	
	/**
	 * Gets the information.
	 *
	 * @return the information
	 * @throws IOException Signals that an I/O exception has occurred.
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
	
	/**
	 * Gets the e cstuff.
	 *
	 * @return the e cstuff
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void getECstuff() throws IOException
	{
		String name = JOptionPane.showInputDialog("Enter new Emergency Contact name");
		String number = JOptionPane.showInputDialog("Enter new Emergency Contact number");
		String email = JOptionPane.showInputDialog("Enter new Emergency Contact email");
		
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
		read1.close();
		if(f.delete())
		{
			trash.renameTo(new File(username + ".txt"));
		}
		JOptionPane.showMessageDialog(null, "Emergency Contact info successfully changed");
	}
	public double findDistance(double hLat,double hLon,double uLat,double uLon)
	{
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
		return distanceM;
	}
	
}//end MapGUI
