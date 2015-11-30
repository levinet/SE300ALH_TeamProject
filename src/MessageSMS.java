import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

// TODO: Auto-generated Javadoc
/**
 * The Class MessageSMS.
 *
 * @author yessenia
 */
public class MessageSMS {

	/**
	 * Instantiates a new message sms.
	 *
	 * @param string the string
	 */
	public MessageSMS(String string) {
		
	}

	/**
	 * Send sms.
	 *
	 * @param strAccountId the str account id
	 * @param strEmail the str email
	 * @param strPassword the str password
	 * @param strMSISDN the str msisdn
	 * @param strMessage the str message
	 * @param strResponse the str response
	 * @return the int
	 */
	

	public static int  SendSMS(String strAccountId,String strEmail,String strPassword,String strMSISDN,String strMessage,StringBuffer strResponse)
	{
		String  sRequestURL;
		String  sData;
		int nResult = -1;

		sRequestURL = "http://www.redoxygen.net/sms.dll?Action=SendSMS";

		try
		{

			sData  = ("AccountId="  + URLEncoder.encode(strAccountId,"UTF-8"));
			sData += ("&Email="     + URLEncoder.encode(strEmail,"UTF-8"));
			sData += ("&Password="  + URLEncoder.encode(strPassword,"UTF-8"));
			sData += ("&Recipient=" + URLEncoder.encode(strMSISDN,"UTF-8"));
			sData += ("&Message="   + URLEncoder.encode(strMessage,"UTF-8"));

			
			URL urlObject = new URL(sRequestURL);
			HttpURLConnection con = (HttpURLConnection) urlObject.openConnection();
			con.setRequestMethod("POST");
			con.setDoInput (true);
                        con.setDoOutput (true);


			DataOutputStream out;
    		        out = new DataOutputStream(con.getOutputStream());
    		        out.writeBytes (sData);
			out.flush();
			out.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String inputLine;
			StringBuffer responseBuffer = new StringBuffer();

			while ((inputLine = in.readLine()) != null)
			{
                              responseBuffer = responseBuffer.append(inputLine);
			      responseBuffer = responseBuffer.append("\n\n\n");
			}

			strResponse.replace(0,0,responseBuffer.toString());

			String sResultCode = strResponse.substring(0,4);
			nResult = Integer.parseInt(sResultCode);

			in.close();
		}

		catch (Exception e)
		{
			System.out.println("Exception caught sending SMS\n");
			nResult = -2;
		}
		return nResult;
	}

	/** The str account id. */
	public String strAccountId;
	
	/** The str email. */
	public String strEmail;
	
	/** The str password. */
	public String strPassword;
	
	/** The str msisdn. */
	public String strMSISDN;
	
	/** The str message. */
	public String strMessage;
	
}

// Account Name: Software engineering practices
// Account ID: CI00168303
// Email Address: y3ssgl0@gmail.com
// Password: rVhnm5B6

