package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import locators.LandingPageLocators;

public class LandingPageStepDefinition {
	
	LandingPageLocators landingPage = new LandingPageLocators();
	
	@Given("^I am on the landing page$")
	public void verifyLandingPage() {
		landingPage.verifyLandingPage();
	}
	
	@Then("^I see the features called (.*)$")
	public void verifyFeaturesOnLandingPage(String featureName) {
		landingPage.verifyFeatures(featureName);
	}

}
