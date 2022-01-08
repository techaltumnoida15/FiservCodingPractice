package org.basePage.com;

import org.fiserv.enums.KeyboardEvents;
import org.fiserv.enums.WaitStrategy;
import org.fiserv.factories.ExplicitWaitFactory;
import org.fiserv.report.Logger;
import org.fiservDriver.com.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {   //common methods

	protected void type(By locator, String value, String elementName, WaitStrategy waitstrategy) {
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy, locator);
		element.sendKeys(value);
		Logger.info("'" + value + "' is type in '" + elementName + "'");
	}
	
	protected void clickOn(By locator, String elementName, WaitStrategy waitstrategy) {
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy, locator);
		element.click();
		Logger.info("Click on '" + elementName + "'");
	}
	
	protected void containsTextInAttribute(By locator, String attribute, String value) {
		new WebDriverWait(DriverManager.getDriver(), 10)
		.until(ExpectedConditions.attributeContains(locator, attribute, value));
	}
	
	protected void waitForPageLoad() {
		new WebDriverWait(DriverManager.getDriver(), 10)
		.until(d -> ((JavascriptExecutor)DriverManager.getDriver()).executeScript("return document.readyState").equals("complete"));
	}
	
	protected String pageCurrentURL() {
		return DriverManager.getDriver().getCurrentUrl();
	}
	
	protected String getPageTitle() {
		return DriverManager.getDriver().getTitle();
	}
	
	protected void useKeyboardKeys(By locator, String elementName, KeyboardEvents keyboardEvents) {
		WebElement element = DriverManager.getDriver().findElement(locator);
		
		if(keyboardEvents == KeyboardEvents.ENTER) {
			element.sendKeys(Keys.ENTER);
			Logger.info("Press ENTER on '" + elementName + "'");
		}
		else if(keyboardEvents == KeyboardEvents.TAB) {
			element.sendKeys(Keys.TAB);
			Logger.info("Press TAB on '" + elementName + "'");
		}
		else if(keyboardEvents == KeyboardEvents.ESC) {
			element.sendKeys(Keys.ESCAPE);
			Logger.info("Press ESCAPE on '" + elementName + "'");
		}
	}
	
	protected String getElementText(By locator, WaitStrategy waitstrategy) {
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy, locator);
		Logger.info("Element text is => " + element.getText());
		return element.getText();
	}
}
