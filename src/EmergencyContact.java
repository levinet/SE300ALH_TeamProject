import java.io.IOException;
import java.rmi.AccessException;
import java.util.Properties;
import sun.rmi.transport.tcp.TCPTransport;
import com.sun.media.jfxmedia.MediaException;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * @author lopezy2, based on code from <a href="http://www.tutorialspoint.com/java/java_sending_email.htm">tutorialspoint.com</a>
 * @version 1.0
 * @created 09-Oct-2015 3:16:18 PM
 */
public class EmergencyContact extends InfoGUI{
	
public EmergencyContact() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

public static void main (String [] args){
	
	// Assuming you are sending email from localhost
    String host = "localhost";

	
	// Recipient's email ID needs to be mentioned.
    String to = (ECMail+".txt");

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
}
}


// email validation \b[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}\b.



// send SMS message





//end EmergencyContact
