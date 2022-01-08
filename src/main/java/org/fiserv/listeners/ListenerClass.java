package org.fiserv.listeners;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.fiserv.constants.FrameworkConstants;
import org.fiserv.report.ExtentManager;
import org.fiserv.report.ExtentReport;
import org.fiserv.report.Logger;
import org.fiserv.utilities.PropertyFile;
import org.fiserv.utilities.ScreenshotUtils;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClass implements ITestListener, ISuiteListener{
	private String currentTimeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"));

	@Override
	public void onStart(ISuite suite) {
		try {
			ExtentReport.initReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onFinish(ISuite suite) {
		try {
			ExtentReport.tearDownReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		try {
			ExtentReport.createTests(result.getMethod().getMethodName());
		} catch (IOException e) {}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		String screenshotPath = FrameworkConstants.getPassedtestscreenshotpath() + testName + "_" + currentTimeStamp + ".jpeg";

		Logger.pass(result.getMethod().getMethodName() + " is passed.");

		try {
			if(PropertyFile.get("passedTestScreenshot").equalsIgnoreCase("yes")) {
				FileUtils.moveFile(ScreenshotUtils.takeScreenshot(), new File(screenshotPath));
				ExtentManager.getExtentTest().addScreenCaptureFromBase64String(ScreenshotUtils.takeScreenshotBase64(), testName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		String screenshotPath = FrameworkConstants.getFailedtestscreenshotpath() + testName + "_" + currentTimeStamp + ".jpeg";

		Logger.fail(result.getMethod().getMethodName() + " is failed.");
		Logger.fail(result.getThrowable().toString());

		try {
			if(PropertyFile.get("failedTestScreenshot").equalsIgnoreCase("yes")) {
				FileUtils.moveFile(ScreenshotUtils.takeScreenshot(), new File(screenshotPath));
				ExtentManager.getExtentTest().addScreenCaptureFromBase64String(ScreenshotUtils.takeScreenshotBase64(), testName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Logger.skip(result.getMethod().getMethodName() + " is skipped.");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		ITestListener.super.onFinish(context);
	}

}
