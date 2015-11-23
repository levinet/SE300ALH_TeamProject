import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
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
 * 
 * 
 */

public class EmailSMS extends javax.mail.Authenticator {

	private String _user;

	private String _pass;
	private String[] _to;

	private String _from;
	private String _port;

	private String _sport;
	private String _host;
	private String _subject;

	private String _body;
	private boolean _auth;
	private boolean _debuggable;
	private Multipart _multipart;

	/**
	 * 
	 * Sets the formatting and initializes the parameters for sending email.
	 * 
	 * @ return
	 */

	public EmailSMS() {

		_host = "smtp.gmail.com"; // default smtp server

		_port = "465"; // default smtp port

		_sport = "465"; // default socketfactory port
		
		_user = ""; // username

		_pass = ""; // password

		_from = ""; // email sent from

		_subject = ""; // email subject

		_body = ""; // email body
		_debuggable = false; // debug mode on or off - default off

		_auth = true; // smtp authentication - default on
		_multipart = new MimeMultipart();
		// There is something wrong with MailCap, javamail can not find a
		// handler for the multipart/mixed part, so this bit needs to be added.

		MailcapCommandMap mc = (MailcapCommandMap) CommandMap
				.getDefaultCommandMap();

		mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");

		mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");

		mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");

		mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");

		mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");

		CommandMap.setDefaultCommandMap(mc);

	}

	/**
	 * 
	 * Initialization of username and password.
	 * 
	 * @param user
	 *            The username of the email account
	 * 
	 * @param pass
	 *            The password of the email account
	 */

	public EmailSMS(String user, String pass) {

		this();
		_user = user;

		_pass = pass;

	}

	/**
	 * 
	 * Sends the email.
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */

	public boolean send() throws Exception {

		Properties props = _setProperties();
		if (!_user.equals("") && !_pass.equals("") && _to.length > 0
				&& !_from.equals("") && !_subject.equals("")) {

//			Session session = Session.getInstance(props, this);
			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(_user, _pass);
						}
					});

			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(_from));
			InternetAddress[] addressTo = new InternetAddress[_to.length];

			for (int i = 0; i < _to.length; i++) {

				addressTo[i] = new InternetAddress(_to[i]);

			}

			msg.setRecipients(MimeMessage.RecipientType.TO, addressTo);
			msg.setSubject(_subject);

			msg.setSentDate(new Date());
			// setup message body

			BodyPart messageBodyPart = new MimeBodyPart();

			messageBodyPart.setText(_body);

			_multipart.addBodyPart(messageBodyPart);
			// Put parts in message

			msg.setContent(_multipart);
			// send email

			Transport.send(msg);
			return true;

		} else {

			return false;

		}

	}

	/**
	 * 
	 * Pulls the email from the device storage and attach it to the email.
	 * 
	 * @param filename
	 *            Name of the file to be attached
	 * 
	 * @throws Exception
	 */

	public void addAttachment(String filename) throws Exception {

		BodyPart messageBodyPart = new MimeBodyPart();

		DataSource source = new FileDataSource(filename);

		messageBodyPart.setDataHandler(new DataHandler(source));
		Calendar c = Calendar.getInstance();

		String[] dateTime = c.getTime().toString().split(" ");

		String name = "";

		for (String s : dateTime) {

			name = name + s;

		}

		dateTime = name.split(":");

		String nextString = "";

		for (String s : dateTime) {

			nextString = nextString + s;

		}
		messageBodyPart.setFileName(nextString + ".jpg");
		_multipart.addBodyPart(messageBodyPart);

	}

	/**
	 * 
	 * Authenticates the email account sending the message.
	 * 
	 * @return
	 */

	@Override
	public PasswordAuthentication getPasswordAuthentication() {

		return new PasswordAuthentication(_user, _pass);

	}

	private Properties _setProperties() {

		Properties props = new Properties();
		props.put("mail.smtp.host", _host);
		if (_debuggable) {

			props.put("mail.debug", "true");

		}
		if (_auth) {

			props.put("mail.smtp.auth", "true");

		}
		props.put("mail.smtp.port", _port);

		props.put("mail.smtp.socketFactory.port", _sport);

		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");

		props.put("mail.smtp.socketFactory.fallback", "false");
		return props;

	}

	/**
	 * 
	 * Gets the message body.
	 * 
	 * @return
	 */

	// the getters and setters

	public String getBody() {

		return _body;

	}

	/**
	 * 
	 * Sets the message body.
	 * 
	 * @param _body
	 *            Body text of the message.
	 */

	public void setBody(String _body) {

		this._body = _body;

	}

	/**
	 * 
	 * Set the subject line.
	 * 
	 * @param _subject
	 *            The subject line of the message.
	 */

	public void setSubject(String _subject) {

		this._subject = _subject;

	}

	/**
	 * 
	 * Sets the address the email will be sent to.
	 * 
	 * @param to
	 *            Array of message addresses.
	 */

	public void setTo(String[] to) {

		_to = to;

	}

	/**
	 * 
	 * Sets the email sender.
	 * 
	 * @param _from
	 *            Email address of sender to be seen when email is recieved.
	 */

	public void setFrom(String _from) {

		this._from = _from;

	}

	// more of the getters and setters …..

}