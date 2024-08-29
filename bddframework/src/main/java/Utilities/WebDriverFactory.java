package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.epam.healenium.SelfHealingDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {

	public WebDriver getWebDriverSession() {

		SelfHealingDriver driver = null;

		//String browserName = AppProperties.getProperty("src/test/resources/test.properties", "browser");

		Logs.getLog().getLogger().info("{WebDriverFactory} " +System.getProperty("browser"));

		String browserName = System.getProperty("browser").toLowerCase();

		try {

			WebDriver delegate = null;

			switch(browserName) {

			case "chrome":
				WebDriverManager.chromedriver().setup();
				delegate = new ChromeDriver();
				break;

			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				delegate = new FirefoxDriver();
				break;

			case "edge":
				WebDriverManager.edgedriver().setup();
				delegate = new EdgeDriver();
				break;

			case "safari":
				WebDriverManager.safaridriver().setup();
				delegate = new SafariDriver();
				break;

			default:
				Logs.getLog().getLogger().error("{WebDriverFactory} ERROR --> Invalid browserName : "+browserName);


			}

			//create Self-healing driver
			driver = SelfHealingDriver.create(delegate);

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
