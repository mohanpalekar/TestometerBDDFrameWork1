package Utilities;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	private static DriverFactory instance = new DriverFactory();

	private DriverFactory() {
	}

	public WebDriver getWebDriver() {
		return driver.get();
	}

	public static DriverFactory getInstance() {
		return instance;
	}

	public void setWebDriver(WebDriver driverParam) {
		driver.set(driverParam);
	}

	public void closeBrowser() {
		driver.get().quit();
		driver.remove();
	}



}
