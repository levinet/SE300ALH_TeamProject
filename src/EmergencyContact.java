import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author lopezy2, based on code from <a href="http://www.tutorialspoint.com/java/java_sending_email.htm">tutorialspoint.com</a>
 * @version 1.0
 * @created 09-Oct-2015 3:16:18 PM
 * 
 */
public class EmergencyContact extends Authenticator { //extends InfoGUI{
	private String User;
	private String Pass;
	private String[] ECMail;
	private String From;
	private String Port;
	private String SoPort;
	private String Host;
	private String Subject;
	private String Body;
	private boolean Auth;
	private boolean Debuggable;
	private Multipart Multipart;

	/**
	 * sets email formating and parameters for sending the email
	 * @return
	 */

	public EmergencyContact() throws IOException{
		Host = "smtp.gmail.com"; // default smtp server

		Port = "465"; // default smtp port

		SoPort = "465"; // default socketfactory port

		User = "alhse300"; // username

		Pass = "timtomyessitylerSE300"; // password

		From = "alhse300@gmail.com"; // email sent from

		Subject = "Alzheimer's little helper notification"; // email subject

		Body = "TEST"; // email body

		Debuggable = false; // debug mode on or off - default off

		Auth = true; // smtp authentication - default on

		Multipart = new MimeMultipart();
		// There is something wrong with MailCap, javamail can not find a handler for the multipart/mixed part, so this needs to be added.

		MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();

		mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");

		mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");

		mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");

		mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");

		mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");

		CommandMap.setDefaultCommandMap(mc);

	}
	/**

	 * Initialization of username and password.

	 * @param user The username of the email account

	 * @param pass The password of the email account

	 */


	public EmergencyContact (String user, String pass) throws IOException { //changed to constructor
		this();
		User = user;
		Pass = pass;
	}

	/**	
	 * Sends the email.

	 * @return

	 * @throws Exception

	 */	
	// will obtain email adress from the text file in use.
	public boolean send(String toAddress) throws Exception {

		ECMail = new String[]{toAddress};
		
		Properties props = setProperties();
		if(!User.equals("") && !Pass.equals("") && ECMail.length > 0 && !From.equals("") && !Subject.equals("") ) {

			Session session = Session.getInstance(props, this);
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(From));
			InternetAddress[] addressTo = new InternetAddress[ECMail.length];

			for (int i = 0; i < ECMail.length; i++) {

				addressTo[i] = new InternetAddress(ECMail[i]);

			}
			msg.setRecipients(MimeMessage.RecipientType.TO, toAddress);
			msg.setSubject(Subject);

			msg.setSentDate(new Date());
			// setup message body

			BodyPart messageBodyPart = new MimeBodyPart();

			messageBodyPart.setText(Body);

			Multipart.addBodyPart(messageBodyPart);
			// Put parts in message

			msg.setContent(Multipart);
			// send email

			Transport.send(msg);
			return true;

		} else {    
			return false;
		}
	}
	/**
	 * pulls the email from the text file in use 
	 * @param file name attached
	 * @ throws Exception
	 */

	public void addAttachment(String filename) throws Exception {

		BodyPart messageBodyPart = new MimeBodyPart();

		DataSource source = new FileDataSource(filename);

		messageBodyPart.setDataHandler(new DataHandler(source));
		Calendar c = Calendar.getInstance();

		String[] dateTime = c.getTime().toString().split(" ");

		String name = "";

		for(String s : dateTime){

			name = name + s;
		}
		dateTime = name.split(":");

		String nextString = "";

		for(String s : dateTime){

			nextString = nextString + s;

		}
		messageBodyPart.setFileName(nextString + ".txt");
		Multipart.addBodyPart(messageBodyPart);

	}

	/**

	 * Authenticates the email account sending the message.

	 * @return

	 */

	@Override

	public PasswordAuthentication getPasswordAuthentication() {

		return new PasswordAuthentication(User, Pass);

	}
	private Properties setProperties() {

		Properties props = new Properties();
		props.put("mail.smtp.Host", Host);
		if(Debuggable) {

			props.put("mail.debug", "true");

		}
		if(Auth) {

			props.put("mail.smtp.auth", "true");

		}
		props.put("mail.smtp.Port", Port);

		props.put("mail.smtp.socketFactory.Port", SoPort);

		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		props.put("mail.smtp.socketFactory.fallback", "false");
		return props;

	}

	/**

	 * Gets the message body.

	 * @return

	 */

	// the getters and setters

	public String getBody() {

		return Body;

	}

	/**

	 * Sets the message body.

	 * @param _body Body text of the message.
	 * @param body 

	 */

	public void setBody(String Body) {

		this.Body = Body;

	}

	/**

	 * Set the subject line.

	 * @param _subject The subject line of the message.

	 */

	public void setSubject(String Subject) {

		this.Subject = Subject;

	}

	/**

	 * Sets the address the email will be sent to.

	 * @param to Array of message addresses.

	 */

	public void setTo(String[] ECMail) {

		ECMail = this.ECMail;

	}

	/**

	 * Sets the email sender.

	 * @param _from Email address of sender to be seen when email is recieved.

	 */

	public void setFrom(String from) {

		From = from;

	}
}


// more of the getters and setters …..


// email validation \b[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}\b.



// send SMS message

//try {
//		String recipient = "+6825598090";
//		String message = " Greetings! Have a nice day!";
//		String username = "admin";
//		String password = "pasword";
//		String originator = "+6825598090";
//		String requestUrl  = "http://127.0.0.1:9501/api?action=sendmessage&" +
//		 "username=" + URLEncoder.encode(username, "UTF-8") +
//		 "&password=" + URLEncoder.encode(password, "UTF-8") +
//		 "&recipient=" + URLEncoder.encode(recipient, "UTF-8") +
//		 "&messagetype=SMS:TEXT" +
//		 "&messagedata=" + URLEncoder.encode(message, "UTF-8") +
//		 "&originator=" + URLEncoder.encode(originator, "UTF-8") +
//		 "&serviceprovider=GSMModem1" +
//		 "&responseformat=html";
//		URL url = new URL(requestUrl);
//		HttpURLConnection uc = (HttpURLConnection)url.openConnection();
//		System.out.println(uc.getResponseMessage());
//		uc.disconnect();
//		} catch(Exception ex) {
//		System.out.println(ex.getMessage());
//		}
//	}
//}

//end EmergencyContact