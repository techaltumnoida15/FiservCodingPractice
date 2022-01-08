package org.fiserv.factories;

import org.fiserv.constants.FrameworkConstants;
import org.fiserv.enums.WaitStrategy;
import org.fiservDriver.com.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class ExplicitWaitFactory {

	private ExplicitWaitFactory() {}

	public static WebElement performExplicitWait(WaitStrategy waitstrategy, By by) {
		WebElement element = null;
		if(waitstrategy == WaitStrategy.CLICKABLE) {
			element = 	new WebDriverWait(DriverManager.getDriver(), FrameworkConstants.getExplicitwaittime())
					.until(ExpectedConditions.elementToBeClickable(by));
		}
		else if(waitstrategy == WaitStrategy.PRESENCE) {
			element =	new WebDriverWait(DriverManager.getDriver(), FrameworkConstants.getExplicitwaittime())
					.until(ExpectedConditions.presenceOfElementLocated(by));
		}
		else if(waitstrategy == WaitStrategy.VISIBLE) {
			element =new WebDriverWait(DriverManager.getDriver(), FrameworkConstants.getExplicitwaittime())
					.until(ExpectedConditions.visibilityOfElementLocated(by));
		}
		else if(waitstrategy == WaitStrategy.NONE) {
			element = DriverManager.getDriver().findElement(by);
		}
		return element;
	}
}
