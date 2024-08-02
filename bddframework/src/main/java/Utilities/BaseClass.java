package Utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class BaseClass {

	AppProperties appProperties = new AppProperties();

	WebDriverFactory webDriverFactory = new WebDriverFactory();
	
	
	@Before
	public void launchSession() {
		//start when test case starts
		
		DriverFactory.getInstance().setWebDriver(webDriverFactory.getWebDriverSession());
		
		DriverFactory.getInstance().getWebDriver().manage().window().maximize();
		
		DriverFactory.getInstance().getWebDriver().get(appProperties.getProperty("siteUrl", "src/test/resources/test.properties"));
		
		Logs.getLog().getLogger().info("launchSession is success");
	}
	
	
	@After
	public void clearSession(Scenario scenario) {
		//start when test case ends
		
		WebDriver currentDriver = DriverFactory.getInstance().getWebDriver();
		
		if(scenario.isFailed()) {
			
			byte [] screenshot = ((TakesScreenshot) currentDriver).getScreenshotAs(OutputType.BYTES);
			
			scenario.attach(screenshot, "image/png", scenario.getName());
			
			Logs.getLog().getLogger().info("screenshot is captured");
		}
		
		DriverFactory.getInstance().closeBrowser();
		
		Logs.getLog().getLogger().info("clearSession is success");
	}

}
