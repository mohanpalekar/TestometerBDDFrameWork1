package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features={"src/test/resources/features"},
		glue={"stepDefinitions", "Utilities"},
		tags="@Regression",
		dryRun=false,
		monochrome = true,
		plugin= {"pretty", "Utilities.GetTestStepName", "html:target/HTMLReports.html", "json:target/JSON/cucumber.json",
				"junit:target/cucumber-reports/Cucumber.xml", "rerun:target/rerun.txt",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})


public class Runner {


}
