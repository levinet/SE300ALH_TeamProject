
/**
 * @author bradlet7
 *
 */


/**
 * class Database holds all the information for a single Database
 * @param user: stores the name of the suer
 * @param password: stores the password of the user
 * @param latitude: stores the current latitude
 * @param longitude: stores the current longitude
 * @param homeLatitude: stores the user's home latitude
 * @param homeLongitude: stores the user's home longitude
 * @param time: stores the current time
 * @param date: stores the current date
 * @param emergencyContact: stores the user's emergency contact
 * @param phone: stores the emergency contact's phone number
 * @param email: stores the emergency contact's email address
 */
public class Database {
	
	
	private String username;
	private String password;
	private String latitude;
	private String longitude;
	private String homeLatitude;
	private String homeLongitude;
	private String time;
	private String date;
	private String ECname;
	private String ECnum;
	private String ECmail;
	
	
	/**
	 * create a Database type
	 * @param u :saving user information under u
	 * @param pw :saving password information under pw
	 * @param lat :saving latitude of the lost location under lat
	 * @param longi :saving longitude of the lost location under longi
	 * @param homeLat :saving home latitude  information under homeLat
	 * @param homeLongi :saving home longitude information under homeLongi
	 * @param t :saving times of lost information under t
	 * @param d :saving  date information under d
	 * @param em :saving emergency contact name information under em
	 * @param ph :saving emergency contact phone number under ph
	 * @param e :saving emergency contact email under e
	 */
	public Database(String u, String pw, String lat, String longi, String homeLat, String homeLongi, String t, String d, String em, String ph, String e){
		username=u;
		password=pw;
		latitude=lat;
		longitude=longi;
		homeLatitude=homeLat;
		homeLongitude=homeLongi;
		time=t;
		date=d;
		ECname=em;
		ECnum=ph;
		ECmail=e;

	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString() Class Object is the root of the class hierarchy. Every class has Object as a superclass. All objects, including arrays, implement the methods of this class.
	 */
	public String toString(){
		return "User: "+username+"\nPassword: *******"+""+"\nCoordinates: "+latitude+","+longitude+
				"\nHomeLocation: "+homeLatitude+","+homeLongitude+"\nTime: "+time+"\nDate: "+date+
				"\nEmergencyContact: "+ECname+"\nPhone: "+ECnum+"\nEmail: "+ECmail;
	}

	/**
	 * m
	 * 
	 * @exception Throwable
	 */
	
	public void finalize() throws Throwable {

	}

}
// end Database
