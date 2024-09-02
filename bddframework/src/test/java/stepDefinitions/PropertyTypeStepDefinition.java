package stepDefinitions;

import Utilities.WebDriverActions;
import io.cucumber.java.en.Then;
import locators.PropertyTypeLocators;

public class PropertyTypeStepDefinition {

	private WebDriverActions webDriverActions = new WebDriverActions();

	private PropertyTypeLocators propertyTypeLocators = new PropertyTypeLocators(webDriverActions);

	@Then("^I see Browse By Property Types (.*)$")
	public void verifyPropertyType(String propertyName) {
		propertyTypeLocators.verifyPropertyType(propertyName);
	}

}
