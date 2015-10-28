
/**
 * @author bradlet7
 *
 */

public class Database {
	
	private String user;
	private String password;
	private String latitude;
	private String longitude;
	private String homeLatitude;
	private String homeLongitude;
	private String time;
	private String date;
	private String emergencyContact;
	private String phone;
	private String email;
	
	
	public Database(String u, String pw, String lat, String longi, String homeLat, String homeLongi, String t, String d, String em, String ph, String e){
		user=u;
		password=pw;
		latitude=lat;
		longitude=longi;
		homeLatitude=homeLat;
		homeLongitude=homeLongi;
		time=t;
		date=d;
		emergencyContact=em;
		phone=ph;
		email=e;

	}
	
	public String toString(){
		return "User: "+user+"\nPassword: *******"+""+"\nCoordinates: "+latitude+","+longitude+
				"\nHomeLocation: "+homeLatitude+","+homeLongitude+"\nTime: "+time+"\nDate: "+date+
				"\nEmergencyContact: "+emergencyContact+"\nPhone: "+phone+"\nEmail: "+email;
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
