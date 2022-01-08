package org.fiserv.constants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.fiserv.utilities.PropertyFile;


public final class FrameworkConstants {
	private FrameworkConstants() {}

	private static final String USERDIR = System.getProperty("user.dir");
	private static final String PROPFILEPATH = USERDIR + "\\resources\\config.properties";
	private static final String EXTENTREPORTPATH = USERDIR + "/extent-output/";
	private static final String PASSEDTESTSCREENSHOTPATH = USERDIR + "\\screenshots\\passedTests\\";
	private static final String FAILEDTESTSCREENSHOTPATH = USERDIR + "\\screenshots\\failedTests\\";
	private static final String EXCELPATH = USERDIR + "\\resources\\testData.xlsx";
	private  static final int MAXRETRYCOUNT = 0;
	private static final int EXPLICITWAITTIME = 10;
	
	public static int getExplicitwaittime() {
		return EXPLICITWAITTIME;
	}
	
	public static int getMaxretryCount() {
		return MAXRETRYCOUNT;
	}

	public static String getPassedtestscreenshotpath() {
		return PASSEDTESTSCREENSHOTPATH;
	}

	public static String getFailedtestscreenshotpath() {
		return FAILEDTESTSCREENSHOTPATH;
	}

	public static String getExtentreportpath() throws Exception {
		if(PropertyFile.get("overrideReport").equalsIgnoreCase("no"))
			return EXTENTREPORTPATH + "TestExecutionReport_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss")) + ".html";
		else
			return EXTENTREPORTPATH + "index.html";
	}

	public static String getPropfilepath() {
		return PROPFILEPATH;
	}

	public static String getExcelpath() {
		return EXCELPATH;
	}
}
