package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
//import cucumber.api.testng.AbstractTestNGCucumberTests
@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/features"}
		,glue={"stepDefinition","appHooks"}
		,plugin= { "pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"json:Reports/report.json"}
		,monochrome = true
		,stepNotifications = true		
			
		)

 public class Test{
	
	
	
}

