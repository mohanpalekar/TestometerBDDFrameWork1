package locators;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import Utilities.Logs;
import Utilities.WebDriverActions;

public class LandingPageLocators{

	private By logInButton = By.xpath("//a[@aria-label='Sign in']");

	private By staysOption = By.id("accommodations");

	private By flightsOption = By.id("flights");

	private By packagesOption = By.id("packages");

	private By carsOption = By.id("cars");

	private By attractionOption = By.id("attractions");

	private By taxisOption = By.id("airport_taxis");

	//private By heroBanner = By.xpath("//span[@data-testid='herobanner-title1']");

	//private By heroBanner = By.xpath("//div[@class='Header_main']");

	private By popUp = By.xpath("//button[starts-with(@aria-label, 'Dismiss')]");
	
	private WebDriverActions webDriverActions;
	
	public LandingPageLocators(WebDriverActions webDriverActions) {
		this.webDriverActions = webDriverActions;
	}

	public void clickLoginButton() {
		webDriverActions.waitUntilElementToBeClickable(logInButton).click();
	}

	public void verifyFeatures(String featureName) {
		Assert.assertTrue(webDriverActions.waitUntilVisibilityOfElementLocated(getFeatureByName(featureName)).isDisplayed());
	}

	public void verifyLandingPage() {
		Assert.assertEquals(webDriverActions.getCurrentUrl(), "https://www.booking.com/");
	}

	public void closePopUpIfDisplayed() {

		try {
			WebElement crossPopUp = webDriverActions.waitUntilElementToBeClickable(popUp);

			if(crossPopUp.isDisplayed()) {
				crossPopUp.click();
			}

		}catch(TimeoutException ex) {
			Logs.getLog().getLogger().error("{LandingPageLocators} PopUp is not displayed");	
		}
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
