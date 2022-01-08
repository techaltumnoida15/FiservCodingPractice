package org.fiserv.report;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import org.fiserv.constants.FrameworkConstants;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public final class ExtentReport {
	private ExtentReport() {}

	private static ExtentReports extent;
	private static ExtentTest test;
	private static String reportPath = "";

	public static void initReport() throws Exception {
		reportPath = FrameworkConstants.getExtentreportpath();
		if(Objects.isNull(extent)) {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

			extent.attachReporter(spark);

			//Config
			spark.config().setTheme(Theme.DARK);
			spark.config().setDocumentTitle("Fiserv Code Assessment");
			spark.config().setReportName("Google Search Tests");
		}
	}

	public static void tearDownReport() throws Exception {
		if(Objects.nonNull(extent)) {
			extent.flush();
		}
		ExtentManager.unload();

		Desktop.getDesktop().browse(new File(reportPath).toURI());
	}

	public static void createTests(String testName) throws IOException {
		test = extent.createTest(testName);
		ExtentManager.setExtentReport(test);
		
	}

}
