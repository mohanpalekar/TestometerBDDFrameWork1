package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {

	public WebDriver getWebDriverSession() {

		WebDriver driver = null;

		//String browserName = AppProperties.getProperty("src/test/resources/test.properties", "browser");

		Logs.getLog().getLogger().info("{WebDriverFactory} " +System.getProperty("browser"));

		String browserName = System.getProperty("browser").toLowerCase();

		try {

			switch(browserName) {

			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;

			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;

			case "edge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;

			case "safari":
				WebDriverManager.safaridriver().setup();
				driver = new SafariDriver();
				break;

			default:
				Logs.getLog().getLogger().error("{WebDriverFactory} ERROR --> Invalid browserName : "+browserName);


			}

		}catch(Exception ex) {
			Logs.getLog().getLogger().error("{WebDriverFactory} ERROR --> Invalid browserName : "+browserName);
		}
		if(driver != null) {
			Logs.getLog().getLogger().info("{WebDriverFactory} INFO --> getWebDriverSession is success : "+browserName);
		}else {
			Logs.getLog().getLogger().error("{WebDriverFactory} ERROR --> getWebDriverSession is failure : "+browserName);

		}
		return driver;
	}

}
