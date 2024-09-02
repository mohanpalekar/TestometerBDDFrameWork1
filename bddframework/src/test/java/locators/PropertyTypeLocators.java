package locators;

import org.openqa.selenium.By;

import Utilities.WebDriverActions;

public class PropertyTypeLocators{
	
	private WebDriverActions webDriverActions;
	
	public PropertyTypeLocators(WebDriverActions webDriverActions) {
		this.webDriverActions = webDriverActions;
	}
	
	private By getPropertyType(String propertyName) {
		return By.xpath("//div[@data-testid='webcore-carousel-title' and text()='"+propertyName+"']");
	}
	
	public void verifyPropertyType(String propertyName) {
		webDriverActions.waitUntilVisibilityOfElementLocated(getPropertyType(propertyName));
	}

}
