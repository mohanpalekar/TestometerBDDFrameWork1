package locators;

import org.junit.Assert;
import org.openqa.selenium.By;

import Utilities.WebDriverActions;

public class LandingPageLocators extends WebDriverActions{

	private By logInButton = By.xpath("//a[@aria-label='Sign in']");
	
	private By staysOption = By.id("accommodations");
	
	private By flightsOption = By.id("flights");
	
	private By packagesOption = By.id("packages");
	
	private By carsOption = By.id("cars");
	
	private By attractionOption = By.id("attractions");
	
	private By taxisOption = By.id("airport_taxis");
	
	private By heroBanner = By.xpath("//span[@data-testid='herobanner-title1']");
	
	public void clickLoginButton() {
		waitUntilElementToBeClickable(logInButton).click();
	}
	
	public void verifyFeatures(String featureName) {
		Assert.assertTrue(waitUntilVisibilityOfElementLocated(getFeatureByName(featureName)).isDisplayed());
	}
	
	public void verifyLandingPage() {
		waitUntilVisibilityOfElementLocated(heroBanner).isDisplayed();
	}
	
	
	public By getFeatureByName(String featureName) {
		
		switch(featureName) {
		
		case "Stays":
			return staysOption;
			
		case "Flights":
			return flightsOption;
			
		case "Flights+Hotel":
			return packagesOption;
			
		case "Car rentals":
			return carsOption;
			
		case "Attractions":
			return attractionOption;
			
		case "Airport taxis":
			return taxisOption;
			
		default: return null;
		}
		
	}
	
}
