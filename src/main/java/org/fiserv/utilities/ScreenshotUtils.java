package org.fiserv.utilities;

import java.io.File;

import org.fiservDriver.com.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public final class ScreenshotUtils {
	
	private ScreenshotUtils() {}
	
	public static File takeScreenshot() {
		return ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
	}
	
	public static String takeScreenshotBase64() {
		return ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
	}

}
