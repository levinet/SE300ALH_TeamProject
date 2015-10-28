import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bradlet7
 *
 */


public class DatabaseManipulation {
	
	public void readData() throws Exception{

	String DB_FILE = "SE300ALH.csv_";

	BufferedReader buffRead = null;
	
	String currentLine = "";
	
	String csvSplit = ",";
	
	String[] data;
	
	try{
		buffRead = new BufferedReader(new FileReader(DB_FILE));
		System.out.println("Success!");
	}
	catch(FileNotFoundException fnfe){
		System.out.println("File not found...");
		System.exit(404);
	}
	
	List<Database> databases = new ArrayList<Database>(); 
	
	while((currentLine = buffRead.readLine()) != null){
		
		data = currentLine.split(csvSplit);
		databases.add(new Database(data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9],data[10]));
	}
	
	buffRead.close();
	
	}
	
	/*
	public static String readFile() throws IOException {
		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader(DB_FILE));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		reader.close();

		return stringBuilder.toString();
	}

	public static void writeToFile(String s) throws IOException {
		File locFile = new File(DB_FILE);
		locFile.createNewFile();
		BufferedWriter buffWriter = null;
		buffWriter = new BufferedWriter(new FileWriter(locFile, true));
		buffWriter.write(s);
		buffWriter.close();
	}
	*/

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable{

	}

	public double calculations(){
		return 0;
	}

	public boolean checkIfLost(){
		return false;
	}
}//end DatabaseManipulation
