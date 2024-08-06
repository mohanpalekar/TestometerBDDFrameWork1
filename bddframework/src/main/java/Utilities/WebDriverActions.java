package Utilities;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverActions {
	
	private WebDriver driver = DriverFactory.getInstance().getWebDriver();
	
	private JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver; 
	
	long timeOut = Long.parseLong(AppProperties.getProperty("src/test/resources/test.properties", "timeOut"));
	
	long timeOutInMillis = Long.parseLong(AppProperties.getProperty("src/test/resources/test.properties", "timeOutInMillis"));

	
	private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	
	public WebElement waitUntilElementToBeClickable(By locator) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		executionOrder(element);
		return element;
	}
	
	public WebElement waitUntilVisibilityOfElementLocated(By locator) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		executionOrder(element);
		return element;
	}
	
	public void scrollToElement(WebElement element) {
		javascriptExecutor.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	public void hightLightElement(WebElement element) {
		javascriptExecutor.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}
	
	public void removeHightLight(WebElement element) {
		javascriptExecutor.executeScript("arguments[0].setAttribute('style', 'background: white; border: 2px solid green;');", element);
	}
	
	public void pause() {
		try {
			TimeUnit.MILLISECONDS.sleep(timeOutInMillis);
		} catch (InterruptedException e) {
			Logs.getLog().getLogger("WebDriverActions").error("ERROR --> failed to wait : "+e.getMessage());
		}
	}
	
	public void executionOrder(WebElement element) {
		scrollToElement(element);
		hightLightElement(element);
		pause();
		removeHightLight(element);
		pause();
	}

}
