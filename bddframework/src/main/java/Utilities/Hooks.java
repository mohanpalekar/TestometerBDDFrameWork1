package Utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.epam.healenium.SelfHealingDriver;

import Interfaces.HealeniumRepositoryInterface;
import Interfaces.WebDriverFactoryInterface;
import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;

public class Hooks {

	private final static Set<String> failedSteps = new HashSet<String>();
	private static long totalScenarios = 0;
	private static long totalFailedScenarios = 0;
	private static long startTime = 0;
	private static long finishTime = 0;
	private static boolean isAlreadyFailedTestStep = false;
	private static long [] healeniumServerData;

	private WebDriver driver;

	private WebDriverFactoryInterface driverFactory = new SelfHealingWebDriverFactory(driver);

	private static HealeniumRepositoryInterface Hooks = new HealeniumRepository();

	private String browserName = System.getProperty("browser").toLowerCase();

	@BeforeAll
	public static void cleanOldFiles() throws IOException, InterruptedException, FailedToStartHealeniumServer, InvalidPortException {
		
		startTime = System.currentTimeMillis();

		File [] files1 = new File[2];

		File healeniumScript = new File("D:\\healenium\\shell-installation\\web\\start_healenium.sh");
		File healeniumLogFile = new File("D:\\healenium\\shell-installation\\web\\logs\\healenium-backend.log");

		files1[0] = healeniumScript;
		files1[1] = healeniumLogFile;

		String command = "D: && cd D:\\healenium\\shell-installation\\web && \"D:\\healenium\\shell-installation\\web\\start_healenium.sh\""; 

		healeniumServerData = Hooks.startHealeniumServer(files1, command);

		if(healeniumServerData[0] != -1) {
			File healenumPropertiesFile = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\healenium.properties");
			updatePortInHealeniumProperties(healenumPropertiesFile);
		}

		final File folder = new File(System.getProperty("user.dir") + "/target/PDFReport");
		final File[] files = folder.listFiles();
		for (final File file : files) {
			if (file.isFile() && file.getName().contains(".pdf")) {
				Files.deleteIfExists(Paths.get(file.getAbsolutePath()));
				Logs.getLog().getLogger().info("{BaseClass} Deleted ---> " + file);
			}

		}

	}


	// start when test case starts
	@Before
	public void launchSession(Scenario scenario) throws NoSuchFieldException, SecurityException,
	IllegalArgumentException, IllegalAccessException, AlreadyFailedTestStepException, DriverFailedToCreateException {
		
		Logs.getLog().getLogger().info("================================================================================");
		
		Logs.getLog().getLogger().info("{BaseClass} launching session for scenario "+scenario.getName());

		excludeScenarioWithFailedStep(scenario);

		DriverFactory.getInstance().setWebDriver(driverFactory.getDriver(browserName));

		driver = DriverFactory.getInstance().getWebDriver();

		driver.manage().window().maximize();

		// DriverFactory.getInstance().getWebDriver().get(AppProperties.getProperty("src/test/resources/test.properties",
		// "siteUrl"));

		driver.get(System.getProperty("siteUrl"));

		Logs.getLog().getLogger().info("{BaseClass} launchSession is SUCCESS for scenario "+scenario.getName());

	}

	@AfterStep
	public void trackStep(Scenario scenario)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		if (scenario.isFailed()) {
			failedSteps.add(GetTestStepName.currentStepName);
			Logs.getLog().getLogger().error("{BaseClass} FAILED STEP : "+GetTestStepName.currentStepName+" { Scenario: "+scenario.getName()+" }");

		}

	}

	// start when test case ends
	@After
	public void clearSession(Scenario scenario) {

		if(driver != null) {

			totalScenarios++;

			// WebDriver currentDriver = (DriverFactory.getInstance().getWebDriver());

			if(!isAlreadyFailedTestStep) {

				if (scenario.isFailed()) {

					totalFailedScenarios++;

					final WebDriver delegatedDriver = ((SelfHealingDriver) driver).getDelegate();

					byte[] screenshot = ((TakesScreenshot) delegatedDriver).getScreenshotAs(OutputType.BYTES);

					scenario.attach(screenshot, "image/png", scenario.getName());

					Logs.getLog().getLogger().info("{BaseClass} screenshot is captured");
				}

				DriverFactory.getInstance().closeBrowser();

				Logs.getLog().getLogger().info("{BaseClass} clearSession is SUCCESS for scenario "+scenario.getName());

			}else {
				totalFailedScenarios++;
			}
		}else {
			Logs.getLog().getLogger().error("{BaseClass} clearSession is FAIL for scenario "+scenario.getName());

		}
		
		Logs.getLog().getLogger().info("================================================================================");

	}




	@AfterAll
	public static void setTestResults() throws AddressException, MessagingException, IOException, InterruptedException {

		try (FileOutputStream out = new FileOutputStream("testResults.properties")) {
			
			finishTime = System.currentTimeMillis();

			Properties props = new Properties();

			double timeTaken = (finishTime - startTime) / 60000;

			props.setProperty("totalScenarios", String.valueOf(totalScenarios));
			props.setProperty("totalFailedScenarios", String.valueOf(totalFailedScenarios));
			props.setProperty("timeTaken", String.valueOf(timeTaken)+" (minutes)");
			props.store(out, "Added test results counts");

		}
		Logs.getLog().getLogger().info("{Hooks} trying to stop healenium server");
		String command = "taskkill /F /PID "+healeniumServerData[0];
		File healeniumLogFile = new File("D:\\healenium\\shell-installation\\web\\logs\\healenium-backend.log");
		Hooks.stopHealeniumServer(healeniumLogFile, command);

		Logs.getLog().getLogger().info("{BaseClass} SUMMARY (All Failed Steps) : "+failedSteps);

	}

	private void excludeScenarioWithFailedStep(Scenario scenario) throws NoSuchFieldException, SecurityException,
	IllegalArgumentException, IllegalAccessException, AlreadyFailedTestStepException {

		Set<String> tempScenarioSteps = new HashSet<String>();

		Field f = scenario.getClass().getDeclaredField("delegate");
		f.setAccessible(true);
		TestCaseState tcs = (TestCaseState) f.get(scenario);

		Field f2 = tcs.getClass().getDeclaredField("testCase");
		f2.setAccessible(true);
		TestCase r = (TestCase) f2.get(tcs);

		r.getTestSteps()
		.stream()
		.filter(x -> x instanceof PickleStepTestStep)
		.map(x -> (PickleStepTestStep) x)
		.forEach(x -> tempScenarioSteps.add(x.getStep().getText()));

		long count = failedSteps.stream().filter(tempstring -> {
			return tempScenarioSteps.stream().anyMatch(tempstring2 -> {
				return tempstring.equals(tempstring2);
			});
		}).count();

		String stepsFailed = failedSteps.stream().filter(tempstring -> {
			return tempScenarioSteps.stream().anyMatch(tempstring2 -> {
				return tempstring.equals(tempstring2);
			});}).reduce("", (a,b)->(a + "\n" + b));

		if (count > 0) {
			Logs.getLog().getLogger().error("{BaseClass} excludeScenarioWithFailedStep : "+scenario.getName());
			isAlreadyFailedTestStep = true;
			throw new AlreadyFailedTestStepException("This scenario contains these already failed step(s).\n"+stepsFailed);

		}else {
			isAlreadyFailedTestStep = false;
		}
	}

	private static void updatePortInHealeniumProperties(File f1) throws IOException {

		List<String> lines = new ArrayList<String>();
		String line = null;

		try(FileReader fr = new FileReader(f1)){

			try(BufferedReader br = new BufferedReader(fr)){

				while ((line = br.readLine()) != null) {
					if (line.contains("hlm.server.url = http://localhost:")) {
						Logs.getLog().getLogger().info("{Hooks} updating healenium.properties with port : "+healeniumServerData[1]);
						line = line.replace(line, "hlm.server.url = http://localhost:"+healeniumServerData[1]);

						Logs.getLog().getLogger().info("{Hooks} updated line --> "+line);
					}
					lines.add(line);
				}
			}

			try(FileWriter fw = new FileWriter(f1)){
				try(BufferedWriter pw = new BufferedWriter(fw)){

					for(String s : lines) {
						pw.write(s);
						pw.newLine();
					}
				}
			}
		}
	}

}
