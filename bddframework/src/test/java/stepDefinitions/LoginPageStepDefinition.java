package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import locators.LandingPageLocators;
import locators.LoginPageLocators;

public class LoginPageStepDefinition {

	LoginPageLocators register = new LoginPageLocators();

	LandingPageLocators landingpage = new LandingPageLocators();

	@Given("^I click Login$")
	public void clickMyAccount() {
		landingpage.clickLoginButton();
	}

	@And("^I enter username as (.*)$")
	public void enteruserName(String username) {
		register.enterUsername(username);
		register.clickLoginButton();
	}

	@And("^I enter password as (.*)$")
	public void enterPassword(String password) {
		register.enterPassword(password);
	}

	@When("^I click on login button$")
	public void clickLoginButton() {
		register.clickLoginButton();
	}
	
	@And("^I click Press and Hold when asked if I am Robot$")
	public void verifyIfRobot() {
		register.clickPressAndHold();
	}

	@Then("^I see (.*) is displayed$")
	public void verifyDashboardPage(String userName) {
		register.verifyUserIsLoggedIn(userName);
	}
	
	@Then("^I verify that invalid email address error message is shown to the user$")
	public void validateInvalidEmailErrorMessage() {
		register.verifyInvalidEmailMessage();
	}
	
	@Then("^I verify that invalid password error message is shown to the user$")
	public void validateInvalidPasswordErrorMessage() {
		register.verifyInvalidPasswordMessage();
	}


}
