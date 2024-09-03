package Interfaces;

import org.openqa.selenium.WebDriver;

import Utilities.DriverFailedToCreateException;

public interface WebDriverFactoryInterface {
	
	WebDriver getDriver(String browserName) throws DriverFailedToCreateException;

}
