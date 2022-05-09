package appHooks;

import java.io.IOException;
import utilities.Utilities;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks{
	
	@Before(order=1)
	public void launchBrowser() {
	
		Utilities.initialization();
		
	}
	
	@After(order=0)
	public void QuitBrowser(){
	
		Utilities.close();
		
	}
	
	@AfterStep
	public void attachScreenshotToExtentReport(Scenario scenario) throws IOException {
	    //String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		
		Utilities.screenshot(scenario);
	}
	/*@After(order=1)
	public void ClearScreenshotFolder() throws IOException {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		String filePath =System.getProperty("user.dir")+"/test-output";
		File file = new File(filePath);
		if(file.exists()) {
			FileUtils.cleanDirectory(file);
		}
		else {
			System.out.println("directory not found");
		}*/
		
	}
	



