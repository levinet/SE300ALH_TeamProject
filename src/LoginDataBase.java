import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginDataBase { 
		   public Boolean Authenticate(String user, String pass) { 
			   
		      List<String> userDetails = ReadFile();
		      //String[] userDetailsString = (String[]) userDetails.toArray();
		      String check = "user"+"\t"+"pass";
		      boolean valid = false;
		      
		      if (userDetails.contains(check))
		      {
		    	  valid = true;
		      }
		      else
		      {
		    	  valid = false;
		      }
		      
		      return valid;
		   }
		   
		   public List<String> ReadFile(){
			   
			// The name of the file to open.
		        String fileName = "./userAccounts.txt";

		        // Reference one line at a time
		        String line = null;
		        
		        // Store all users in text file
		        List<String> userdetails = new ArrayList<String>();

		        try {
		            // FileReader reads text files in the default encoding.
		            FileReader fileReader = 
		                new FileReader(fileName);

		            // Always wrap FileReader in BufferedReader.
		            BufferedReader bufferedReader = 
		                new BufferedReader(fileReader);

		            while((line = bufferedReader.readLine()) != null) {
		            	userdetails.add(line);
		            }   
		            
		            // Always close files.
		            bufferedReader.close();         
		        }
		        catch(FileNotFoundException ex) {
		            System.out.println(
		                "Unable to open file '" + 
		                fileName + "'");                
		        }
		        catch(IOException ex) {
		            System.out.println(
		                "Error reading file '" 
		                + fileName + "'");                  
		            
		        }
			   
			   return userdetails;
		   }
}
