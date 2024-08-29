package Utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;

public class BaseClass {

	private static long totalScenarios = 0;
	private static long totalFailedScenarios = 0;

	WebDriverFactory webDriverFactory = new WebDriverFactory();
	

	//start when test case starts
	@Before
	public void launchSession() {

		DriverFactory.getInstance().setWebDriver(webDriverFactory.getWebDriverSession());

		DriverFactory.getInstance().getWebDriver().manage().window().maximize();

		//DriverFactory.getInstance().getWebDriver().get(AppProperties.getProperty("src/test/resources/test.properties", "siteUrl"));

		DriverFactory.getInstance().getWebDriver().get(System.getProperty("siteUrl"));

		Logs.getLog().getLogger().info("{BaseClass} launchSession is success");
	}
	

	//start when test case ends
	@After 
	public void clearSession(Scenario scenario) {

		totalScenarios++;

		WebDriver currentDriver = DriverFactory.getInstance().getWebDriver();

		if(scenario.isFailed()) {

			totalFailedScenarios++;

			byte [] screenshot = ((TakesScreenshot) currentDriver).getScreenshotAs(OutputType.BYTES);

			scenario.attach(screenshot, "image/png", scenario.getName());

			Logs.getLog().getLogger().info("{BaseClass} screenshot is captured");

		}

		DriverFactory.getInstance().closeBrowser();

		Logs.getLog().getLogger().info("{BaseClass} clearSession is success");
	}
	
	

	@BeforeAll
	public static void cleanOldFiles() throws IOException {
		final File folder = new File(System.getProperty("user.dir")+"/target/PDFReport");
		final File[] files = folder.listFiles();
		for (final File file : files)
		{
			if(file.isFile() && file.getName().contains(".pdf")) {
				Files.deleteIfExists(Paths.get(file.getAbsolutePath()));
				Logs.getLog().getLogger().info("{BaseClass} Deleted ---> "+file);
			}

		}
	}
	
	


	@AfterAll
	public static void setTestResults() throws AddressException, MessagingException, IOException {

		try (FileOutputStream out = new FileOutputStream("testResults.properties")){

			Properties props = new Properties();

			props.setProperty("totalScenarios", String.valueOf(totalScenarios));
			props.setProperty("totalFailedScenarios", String.valueOf(totalFailedScenarios));
			props.store(out, "Added test results counts");

		}


	}

}
