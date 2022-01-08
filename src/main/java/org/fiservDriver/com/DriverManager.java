package org.fiservDriver.com;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

	private DriverManager() {}
	
	private static ThreadLocal<WebDriver> dr = new ThreadLocal<>();
	                //List<String> myL = new ArrayList<>
	
	public static WebDriver getDriver() {
		return dr.get();
	}
	
	public static void setWebDriver(WebDriver drRef) {
		dr.set(drRef);
	}
	
	public static void unload() {
		dr.remove();
	}
}
