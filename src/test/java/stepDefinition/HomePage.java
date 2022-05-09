package stepDefinition;

import driverFile.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Utilities;

public class HomePage extends BaseClass {
	
	@Given("User is on Homepage")
	public static void User_is_on_Homepage() {
		Utilities.Navigate("HomePage");
	}
	@Then("User hovers on SignInOption")
	public static void User_hovers_on_SignInOption() {
		Utilities.MouseHover("SignInOption");
	}
	@And("User clicks on SignInButton")
	public static void User_clicks_on_SignInButton() {
		
		Utilities.Click("SignInButton");
	}
	@When("User clicks on CreateNewAccountButton")
	public static void User_clicks_on_CreateNewAccountButton() {
		
		Utilities.Click("CreateNewAccountButton");
	}
	
}
