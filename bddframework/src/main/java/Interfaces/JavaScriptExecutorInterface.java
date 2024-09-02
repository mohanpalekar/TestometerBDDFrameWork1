package Interfaces;

import org.openqa.selenium.WebElement;

public interface JavaScriptExecutorInterface {

	void scrollToElement(WebElement element);

	void hightLightElement(WebElement element);

	void removeHightLight(WebElement element);

	void executionOrder(WebElement element);

}
