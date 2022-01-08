package org.fiservDriver.com;

import org.fiserv.enums.Browsers;
import org.fiserv.utilities.PropertyFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public final class Driver {

	private Driver() {}

	public static void initDriver() throws Exception {
		WebDriver driver = null;
		String browserName = PropertyFile.get("browserName");

		if(DriverManager.getDriver() == null) {

			if(browserName.equalsIgnoreCase(Browsers.CHROME.name())) {
				//Open Chrome
				WebDriverManager.chromedriver().setup();

				if(PropertyFile.get("headless").equalsIgnoreCase("yes")) {
					ChromeOptions chromeOptions = new ChromeOptions();
					chromeOptions.setHeadless(true);

					driver = new ChromeDriver(chromeOptions);
				}
				else {
					driver = new ChromeDriver();
				}
			}
			else if(browserName.equalsIgnoreCase(Browsers.FIREFOX.name())) {
				//Open FF
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
			else if(browserName.equalsIgnoreCase(Browsers.IE.name())) {
				//Open IE
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			}
			else {
				throw new Exception("This browser is not mention.");
			}
		}

		DriverManager.setWebDriver(driver);
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().get(PropertyFile.get("url"));

	}

	public static void quitDriver() {
		if(DriverManager.getDriver() != null) {
			DriverManager.getDriver().quit();
			DriverManager.unload();
		}
	}
}
