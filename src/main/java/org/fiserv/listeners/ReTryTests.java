package org.fiserv.listeners;

import org.fiserv.constants.FrameworkConstants;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class ReTryTests implements IRetryAnalyzer{

	int count = 0;
	int maxRetryCount = FrameworkConstants.getMaxretryCount();
	
	@Override
	public boolean retry(ITestResult result) {
		if(count < maxRetryCount) {
			count++;
			return true;
		}
		else {
			return false;
		}
	}
}
