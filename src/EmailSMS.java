package src;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * The Class EmailSMS.
 *
 * @author yessenia
 */

 class EmailSMS{

	/**
	 * The main method.
	 * This method obtains the password and email asociated with the gmail account.
	 *the email is also composed 
	 * @param args the arguments
	 */
	public static void main(String[] args) {

        final String username = "alhse300@gmail.com";
        final String password = "gkqnlvvjemiilpna";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("alhse300@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("y3ssgl0@gmail.com"));
            message.setSubject("");
            message.setText("");

            Transport.send(message);

           System.out.println("Done");

        } 
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

//gkqnlvvjemiilpna
//must be logged in to the gmail account
//email: alhse300@gmail.com
//password: timtomyessitylerSE300
