import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class ALHtester {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * This is the test to make sure that each time someone logs in as a returning user, it is not deleting the previous file and making a new one
	 */
	@Test
	public void testTim() throws IOException, InterruptedException {
		int mer;
		//Run the LoginGUI and wait for 20 seconds
		LoginGUI login = new LoginGUI();
		Thread.sleep(20000);
		//Check to see if the a new file is being creted because it shouldn't
		File f = new File("Tim.txt");
		if(f.createNewFile())
		{
			mer = 0;
		}
		else
		{
			mer = 1;
		}
		
		assertEquals(1,mer,0);
	}
	
	public void testTyler() throws Exception{
		
		
		
	}
	
	public void testYessenia() throws Exception {
		int mer;
		//run the MapGUI and wait 20 seconds
		MapGUI map = new MapGUI();
		Thread.sleep(20000);
		// Check to see if email was sent when lost button is pressed
		
		
		
		
	}


}
