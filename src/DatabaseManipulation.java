package src;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bradlet7
 *
 */
public class DatabaseManipulation {

	public static String username;

	/**
	 * the readData() method reads the given txt file with the user's
	 * information
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String> readData() throws Exception {

		String DB_FILE = username + ".txt";

		String line = null;

		FileReader fileRead = null;

		BufferedReader buffRead = null;

		List<String> database = new ArrayList<String>();

		try {
			fileRead = new FileReader(DB_FILE);

			buffRead = new BufferedReader(fileRead);

			while ((line = buffRead.readLine()) != null) {
				database.add(line);
				;
			}

			buffRead.close();
			fileRead.close();

		} catch (FileNotFoundException fnfe) {
			System.out.println("Unable to open file '" + DB_FILE + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + DB_FILE + "'");

		}

		return database;

	}

	/**
	 * @throws Exception
	 */
	public void writeData() throws Exception{
		
		String DB_FILE = username+".txt";
		
		try{
		
		FileReader fileRead = new FileReader(DB_FILE);
		BufferedReader buffread = new BufferedReader(fileRead);
		FileWriter fileWrite = new FileWriter(DB_FILE);
		BufferedWriter buffWrite = new BufferedWriter(fileWrite);
		String s = "";
		while((s = buffread.readLine()) != null){
			buffWrite.write(s);
			buffWrite.newLine();
		}
		
		buffread.close();
		buffWrite.close();
		fileRead.close();
		fileWrite.close();		
	
		}		
		
		catch (FileNotFoundException fnfe) {
			System.out.println("Unable to open file '" + DB_FILE + "'");
		} 
		catch (IOException e) {
			System.out.println("Error reading file '" + DB_FILE + "'");
		}
	}


	/**
	 * 
	 */
	private Database database;

	public Database getDatabase() {
		return database;
	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize() throws Throwable {

	}

	public double calculations() {
		return 0;
	}

	public boolean checkIfLost() {
		return false;
	}
}// end DatabaseManipulation
