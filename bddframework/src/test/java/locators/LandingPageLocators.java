package locators;

import org.openqa.selenium.By;

import Utilities.WebDriverActions;

public class LandingPageLocators extends WebDriverActions{

	private By logInButton = By.xpath("//a[@aria-label='Sign in']");
	
	public void clickLoginButton() {
		waitUntilElementToBeClickable(logInButton).click();
	}

	
}
