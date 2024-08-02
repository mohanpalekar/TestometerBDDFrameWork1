package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {

	public WebDriver getWebDriverSession() {

		WebDriver driver = null;

		AppProperties appProperties = new AppProperties();

		String browserName = appProperties.getProperty("browser", "src/test/resources/test.properties");

		try {

			if(browserName != null) {
				if(browserName.equalsIgnoreCase("chrome")) {

					WebDriverManager.chromedriver().setup();

					driver = new ChromeDriver();

				}else if(browserName.equalsIgnoreCase("firefox")) {
					WebDriverManager.firefoxdriver().setup();

					driver = new FirefoxDriver();

				}else if(browserName.equalsIgnoreCase("safari")) {
					WebDriverManager.safaridriver().setup();

					driver = new SafariDriver();

				}else {
					Logs.getLog().getLogger().error("Invalid browserName : "+browserName);
				}	
			}

		}catch(Exception ex) {
			Logs.getLog().getLogger().error("Invalid browserName : "+browserName);
		}

		Logs.getLog().getLogger().error("getWebDriverSession is success : ");
		return driver;
	}

}
