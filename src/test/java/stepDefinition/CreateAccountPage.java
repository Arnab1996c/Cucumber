package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Utilities;

public class CreateAccountPage {
	
	@Given("User is on CreateAccountPage")
	public static void User_is_on_CreateAccountPage() {
	
		Utilities.Navigate("CreateAccountPage");
	}
	@Then("User fills all mandatory field")
	public static void User_fills_all_mandatory_field() {
		
		Utilities.Set("YourName", 2);
		Utilities.Set("EmailId", 2);
		Utilities.Set("Password>", 2);
		Utilities.Set("PasswordAgain", 2);
		
	}
	
	@When("User clicks on ContinueButton")
	public static void User_clicks_on_ContinueButton() {
		
		Utilities.Click("ContinueButton");
	}
}
