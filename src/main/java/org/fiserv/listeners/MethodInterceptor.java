package org.fiserv.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fiserv.utilities.ExcelFileData;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

public class MethodInterceptor implements IMethodInterceptor{

	@Override
	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
		List<Map<String, String>> testDetails;
		List<IMethodInstance> testToRun = new ArrayList<IMethodInstance>();
		
		try {
			testDetails = ExcelFileData.readData(2);
			
			for(int i = 0; i<methods.size(); i++ ) {
				
				for(int j = 0; j<testDetails.size(); j++) {
					
					if(methods.get(i).getMethod().getMethodName().equalsIgnoreCase(testDetails.get(j).get("TestName"))
							&& testDetails.get(j).get("ToRun").equalsIgnoreCase("Y")) {
						
						methods.get(i).getMethod().setDescription(testDetails.get(j).get("Desc"));
						methods.get(i).getMethod().setRetryAnalyzerClass(org.fiserv.listeners.ReTryTests.class);
						
						testToRun.add(methods.get(i));
					}
				}
			}
			
		} catch (Exception e) {}
		
		return testToRun;  
	}

}
