package locators;

import org.openqa.selenium.By;

import Utilities.WebDriverActions;

public class PropertyTypeLocators extends WebDriverActions{
	
	//private By propertyType_Hotel = By.xpath("//div[@data-testid='webcore-carousel-title' and text()='Apartments']");
	
	private By getPropertyType(String propertyName) {
		return By.xpath("//div[@data-testid='webcore-carousel-title' and text()='"+propertyName+"']");
	}
	
	public void verifyPropertyType(String propertyName) {
		waitUntilVisibilityOfElementLocated(getPropertyType(propertyName));
	}

}
