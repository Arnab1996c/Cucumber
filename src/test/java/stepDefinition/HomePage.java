package stepDefinition;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

import driverFile.BaseClass;
import io.cucumber.java.en.Given;
import utilities.Utilities;

public class HomePage extends BaseClass {
	
	@Given("User is on Homepage")
	public static void User_is_on_Homepage() {
		Utilities.Navigate("HomePage");
	}
}
