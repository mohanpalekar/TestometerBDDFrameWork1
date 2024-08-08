package Utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class BaseClass {

	WebDriverFactory webDriverFactory = new WebDriverFactory();

	//start when test case starts
	@Before
	public void launchSession() {

		DriverFactory.getInstance().setWebDriver(webDriverFactory.getWebDriverSession());

		DriverFactory.getInstance().getWebDriver().manage().window().maximize();

		DriverFactory.getInstance().getWebDriver().get(AppProperties.getProperty("src/test/resources/test.properties", "siteUrl"));

		DriverFactory.getInstance().getWebDriver().get(AppProperties.getProperty("src/test/resources/test.properties", "siteUrl"));
		
		Logs.getLog().getLogger("BaseClass").info("launchSession is success");
	}

	//start when test case ends
	@After 
	public void clearSession(Scenario scenario) {

		WebDriver currentDriver = DriverFactory.getInstance().getWebDriver();

		if(scenario.isFailed()) {

			byte [] screenshot = ((TakesScreenshot) currentDriver).getScreenshotAs(OutputType.BYTES);

			scenario.attach(screenshot, "image/png", scenario.getName());

			Logs.getLog().getLogger("BaseClass").info("screenshot is captured");
		}

		DriverFactory.getInstance().closeBrowser();

		Logs.getLog().getLogger("BaseClass").info("clearSession is success");
	}

}
