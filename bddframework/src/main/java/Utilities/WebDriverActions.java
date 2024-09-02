package Utilities;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Interfaces.JavaScriptExecutorInterface;
import Interfaces.WebDriverActionsInterface;

public class WebDriverActions implements WebDriverActionsInterface, JavaScriptExecutorInterface{

	private WebDriver driver;
	private JavascriptExecutor javascriptExecutor;	
	private long timeOut = Long.parseLong(System.getProperty("timeOut"));	
	private WebDriverWait wait;

	public WebDriverActions(){
		this.driver = DriverFactory.getInstance().getWebDriver();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		this.javascriptExecutor = (JavascriptExecutor) driver;

	}

	@Override
	public WebElement waitUntilElementToBeClickable(By locator) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		executionOrder(element);
		return element;
	}

	@Override
	public WebElement waitUntilVisibilityOfElementLocated(By locator) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		executionOrder(element);
		return element;
	}

	@Override
	public void scrollToElement(WebElement element) {
		javascriptExecutor.executeScript("arguments[0].scrollIntoView();", element);
	}

	@Override
	public void hightLightElement(WebElement element) {
		javascriptExecutor.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}

	@Override
	public void removeHightLight(WebElement element) {
		javascriptExecutor.executeScript("arguments[0].setAttribute('style', 'background: white; border: 2px solid green;');", element);
	}

	public void pause() {
		try {
			long timeOutInMillis = Long.parseLong(System.getProperty("timeOutInMillis"));
			TimeUnit.MILLISECONDS.sleep(timeOutInMillis);
		} catch (InterruptedException e) {
			Logs.getLog().getLogger().error("{WebDriverActions} ERROR --> failed to wait : "+e.getMessage());
		}
	}

	@Override
	public void executionOrder(WebElement element) {
		scrollToElement(element);
		hightLightElement(element);
		pause();
		removeHightLight(element);
		pause();
	}

	@Override
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

}
