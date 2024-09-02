package stepDefinitions;

import Utilities.WebDriverActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import locators.LandingPageLocators;

public class LandingPageStepDefinition {

	private WebDriverActions webDriverActions = new WebDriverActions();

	private LandingPageLocators landingPage = new LandingPageLocators(webDriverActions);

	@Given("^I am on the landing page$")
	public void verifyLandingPage() {
		landingPage.verifyLandingPage();
	}

	@Then("^I see the features called (.*)$")
	public void verifyFeaturesOnLandingPage(String featureName) {
		landingPage.verifyFeatures(featureName);
	}

	@And("^I close the popup when I see it$")
	public void closePopUp() {
		landingPage.closePopUpIfDisplayed();
	}



}
