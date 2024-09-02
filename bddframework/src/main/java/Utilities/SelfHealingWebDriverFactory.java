package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.epam.healenium.SelfHealingDriver;

import Interfaces.WebDriverFactoryInterface;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SelfHealingWebDriverFactory implements WebDriverFactoryInterface{

	private SelfHealingDriver driver;

	public SelfHealingWebDriverFactory(WebDriver driver) {
		this.driver = (SelfHealingDriver) driver;
	}

	@Override
	public WebDriver getDriver(String browserName) {

		Logs.getLog().getLogger().info("{SelfHealingWebDriverFactory} " +System.getProperty("browser"));

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
				Logs.getLog().getLogger().error("{SelfHealingWebDriverFactory} ERROR --> Invalid browserName : "+browserName);
			}

			//create Self-healing driver
			driver = SelfHealingDriver.create(delegate);

		}catch(Exception ex) {
			Logs.getLog().getLogger().error("{SelfHealingWebDriverFactory} ERROR --> Invalid browserName : "+browserName);
		}
		if(driver != null) {
			Logs.getLog().getLogger().info("{SelfHealingWebDriverFactory} INFO --> getWebDriverSession is success : "+browserName);
		}else {
			Logs.getLog().getLogger().error("{SelfHealingWebDriverFactory} ERROR --> getWebDriverSession is failure : "+browserName);

		}
		return driver;
	}
}
