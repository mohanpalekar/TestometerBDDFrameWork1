package Interfaces;

import org.openqa.selenium.WebDriver;

public interface WebDriverFactoryInterface {
	
	WebDriver getDriver(String browserName);

}
