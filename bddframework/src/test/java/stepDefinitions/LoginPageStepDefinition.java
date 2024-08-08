package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import locators.LandingPageLocators;
import locators.RegisterPageLocators;

public class LoginPageStepDefinition {
	
	RegisterPageLocators register = new RegisterPageLocators();
	
	LandingPageLocators landingpage = new LandingPageLocators();
	
	@Given("^I click My Account$")
	public void clickMyAccount() {
		landingpage.clickMyAccount();
	}
	
	@And("^I enter username as (.*)$")
	public void enteruserName(String username) {
		register.enterUsername(username);
	}
	
	@And("^I enter password as (.*)$")
	public void enterPassword(String password) {
		register.enterPassword(password);
	}
	
	@When("^I click on login button$")
	public void clickLoginButton() {
		register.clickLoginButton();
	}
	
	@Then("^I see dashboard page$")
	public void verifyDashboardPage() {
		register.clickLoginButton();
	}
	

}
