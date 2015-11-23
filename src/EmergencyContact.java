public class EmergencyContact {

	public static void main(String[] args) throws Exception{
		EmailSMS email = new EmailSMS("alhse300@gmail.com", "kfqggjpjqvtyesep");
		email.setBody("Your person is lost.");
		email.setSubject("Alzheimer Little Helper.");
		email.setFrom("alhse300@gmail.com");
		email.setTo(new String[]{"y3ssgl0@gmail.com"});
		if(email.send()){
			System.out.println("Sent!");
		}
		else{
			System.out.println("Message not sent");
		}
	}
	
}



//end EmergencyContact