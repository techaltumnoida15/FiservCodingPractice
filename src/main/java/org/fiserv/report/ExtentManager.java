package org.fiserv.report;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentManager {
	private ExtentManager() {}

	private static ThreadLocal<ExtentTest> extText = new ThreadLocal<ExtentTest>();
	
	public static ExtentTest getExtentTest() {
		return extText.get();
	}
	
	static void setExtentReport(ExtentTest test) {
		extText.set(test);
	}
	
	static void unload() {
		extText.remove();
	}
}
