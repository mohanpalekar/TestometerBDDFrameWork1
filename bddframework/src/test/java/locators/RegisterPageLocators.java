package locators;

import org.openqa.selenium.By;

import Utilities.WebDriverActions;

public class RegisterPageLocators extends WebDriverActions{
	
	private By userName = By.id("username");
	
	private By passWord = By.id("password");
	
	private By loginButton =By.name("login");
	
	public void enterUsername(String username) {
		waitUntilVisibilityOfElementLocated(userName).sendKeys(username);
	}
	
	public void enterPassword(String password) {
		waitUntilVisibilityOfElementLocated(passWord).sendKeys(password);
	}
	
	public void clickLoginButton() {
		waitUntilElementToBeClickable(loginButton).click();
	}

}
