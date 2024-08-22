package stepDefinitions;

import io.cucumber.java.en.Then;
import locators.PropertyTypeLocators;

public class PropertyTypeStepDefinition {
	
	PropertyTypeLocators propertyTypeLocators = new PropertyTypeLocators();
	
	@Then("^I see Browse By Property Types (.*)$")
	public void verifyPropertyType(String propertyName) {
		propertyTypeLocators.verifyPropertyType(propertyName);
	}

}
