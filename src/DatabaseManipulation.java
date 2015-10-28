import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bradlet7
 *
 */

public class DatabaseManipulation extends MapGUI{
	
	/**
	 * the readData() method reads the given CSV file with the user's information
	 * @return
	 * @throws Exception
	 */
	public List<Database> readData() throws Exception{

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
	
	List<Database> database = new ArrayList<Database>(); 
	
	while((currentLine = buffRead.readLine()) != null){
		
		data = currentLine.split(csvSplit);
		database.add(new Database(data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9],data[10]));
	}
	
	buffRead.close();
	
	return database;
	
	}
	
	
	
	/**
	 * @throws Exception
	 */
	public void writeData() throws Exception{
		
		String DB_FILE =  username+".csv";
		int i;
		BufferedReader buffread = new BufferedReader(new FileReader(username+".txt"));
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(DB_FILE,true));
		buffWrite.write(username);
		while(buffread.readLine() != null)
		{
			buffWrite.write(buffread.readLine());
		}
		buffread.close();
		buffWrite.close();
	
	}
	
	
	/**
	 * 
	 */
	private Database database;
	
	public Database getDatabase(){
		return database;
	}
	
	
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
