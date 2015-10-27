import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class Database {

	public static final String DB_FILE = "SE300ALH.xlsx";

	public static String readFile() throws IOException{
		BufferedReader reader = null;
		reader = new BufferedReader( new FileReader (DB_FILE));
		String         line = null;
		StringBuilder  stringBuilder = new StringBuilder();
		String         ls = System.getProperty("line.separator");


		while( ( line = reader.readLine() ) != null ) {
			stringBuilder.append( line );
			stringBuilder.append( ls );
		}
		reader.close();

		return stringBuilder.toString();
	}

	public static void writeToFile(String s) throws IOException{
		File locFile = new File(DB_FILE);
		locFile.createNewFile();
		BufferedWriter buffWriter = null;
		buffWriter = new BufferedWriter(new FileWriter(locFile, true));
		buffWriter.write(s);
		buffWriter.close();
	}

	/**m
	 * 
	 * @exception Throwable
	 */
	public void finalize()
			throws Throwable{

	}

	public void storeData(){

	}
}
//end Database
