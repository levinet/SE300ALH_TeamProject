import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.*;



/**
 * @author lopezy2, based on code from <a href="http://www.tutorialspoint.com/java/java_sending_email.htm">tutorialspoint.com</a>
 * @version 1.0
 * @created 09-Oct-2015 3:16:18 PM
 * 
 */
public class EmergencyContact{ //extends InfoGUI{
	
public EmergencyContact() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

public static void main (String [] args){
	
	System.out.println("Starting EmergencyContact thing");
	
	// Assuming you are sending email from localhost
    String host = "localhost";

	
	// Recipient's email ID needs to be mentioned.
    String to = "seanhold3n+test@gmail.com";

    // Sender's email ID needs to be mentioned
    String from = "y3ssgl0@gmail.com"; // create email for the project

    // Subject for the Email
    String subject = "ALH message";
    
    // Actual message being sent out to the emergency contact
    String mailText = ("Help me i am lost and cannot find my way home this is my current location: ");
    
    Properties properties = new Properties();
	properties.put("mail.smtp.host", host);
	Session emailSession = Session.getDefaultInstance(properties); 
    
//    sendEmail ( to, from, subject, mailText, mailText);
//    
//}
//
//public static void sendEmail(String to, String from, String subject, String mailText, String host){
	try{
		

		Message emailMessage = new MimeMessage(emailSession);
		emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		emailMessage.setFrom(new InternetAddress(from));
		emailMessage.setSubject(subject);
		emailMessage.setText(mailText);

		
		emailSession.setDebug(true);

		Transport.send(emailMessage);
	} catch (MessagingException  e) {
		e.printStackTrace();
	}



// email validation \b[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}\b.



// send SMS message

	try {
		String recipient = "+6825598090";
		String message = " Greetings! Have a nice day!";
		String username = "admin";
		String password = "pasword";
		String originator = "+6825598090";
		String requestUrl  = "http://127.0.0.1:9501/api?action=sendmessage&" +
		 "username=" + URLEncoder.encode(username, "UTF-8") +
		 "&password=" + URLEncoder.encode(password, "UTF-8") +
		 "&recipient=" + URLEncoder.encode(recipient, "UTF-8") +
		 "&messagetype=SMS:TEXT" +
		 "&messagedata=" + URLEncoder.encode(message, "UTF-8") +
		 "&originator=" + URLEncoder.encode(originator, "UTF-8") +
		 "&serviceprovider=GSMModem1" +
		 "&responseformat=html";
		URL url = new URL(requestUrl);
		HttpURLConnection uc = (HttpURLConnection)url.openConnection();
		System.out.println(uc.getResponseMessage());
		uc.disconnect();
		} catch(Exception ex) {
		System.out.println(ex.getMessage());
		}
	}
}

//end EmergencyContact
