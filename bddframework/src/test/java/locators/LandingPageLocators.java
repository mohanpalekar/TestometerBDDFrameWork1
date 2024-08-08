package locators;

import org.openqa.selenium.By;

import Utilities.WebDriverActions;

public class LandingPageLocators extends WebDriverActions{

	private By myAccount = By.xpath("//a[@title='My account1']");

	private By searchIcon = By.xpath("//a[@aria-label='Search']");

	public void clickMyAccount() {
		waitUntilElementToBeClickable(myAccount).click();
	}

	public void clickSearchicon() {
		waitUntilElementToBeClickable(searchIcon).click();;
	}

}
