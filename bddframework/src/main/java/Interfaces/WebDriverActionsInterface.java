package Interfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface WebDriverActionsInterface {
	
	WebElement waitUntilElementToBeClickable(By locator);
	
	WebElement waitUntilVisibilityOfElementLocated(By locator);
	
	String getCurrentUrl();

}
