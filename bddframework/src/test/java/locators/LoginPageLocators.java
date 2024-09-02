package locators;

import org.junit.Assert;
import org.openqa.selenium.By;

import Utilities.WebDriverActions;


public class LoginPageLocators{
	
	private By userName = By.name("username");
	
	private By passWord = By.name("password");
	
	private By loginButton =By.xpath("//button[@type='submit']");
	
	private By loginuserName = By.xpath("//div[text()='Your account']");
	
	private By verifyIfRobot = By.xpath("//h3[text()='Are you a robot?']/following-sibling::div[2]");
	
	private By invalidEmailErrorMessage = By.id("username-note");
	
	private By invalidPasswordErrorMessage = By.id("password-note");
	
	private WebDriverActions webDriverActions;
	
	public LoginPageLocators(WebDriverActions webDriverActions) {
		this.webDriverActions = webDriverActions;
	}
	
	public void enterUsername(String username) {	
		webDriverActions.waitUntilVisibilityOfElementLocated(userName).sendKeys(username);
	}
	
	public void enterPassword(String password) {
		webDriverActions.waitUntilVisibilityOfElementLocated(passWord).sendKeys(password);
	}
	
	public void clickLoginButton() {
		webDriverActions.waitUntilElementToBeClickable(loginButton).click();
	}
	
	public void verifyUserIsLoggedIn(String userName) {
		String loggedInUserName = webDriverActions.waitUntilVisibilityOfElementLocated(loginuserName).getText();
		Assert.assertEquals(loggedInUserName, userName);
	}
	
	public void clickPressAndHold() {
		webDriverActions.waitUntilElementToBeClickable(verifyIfRobot).click();
	}
	
	public void verifyInvalidEmailMessage() {
		Assert.assertTrue(webDriverActions.waitUntilVisibilityOfElementLocated(invalidEmailErrorMessage).isDisplayed());
	}
	
	public void verifyInvalidPasswordMessage() {
		Assert.assertTrue(webDriverActions.waitUntilVisibilityOfElementLocated(invalidPasswordErrorMessage).isDisplayed());
	}

}
