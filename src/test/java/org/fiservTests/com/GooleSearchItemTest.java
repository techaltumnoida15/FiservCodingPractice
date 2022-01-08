package org.fiservTests.com;

import org.fiservDriver.com.BaseTest;
import org.pageObject.com.GoogleHome;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GooleSearchItemTest extends BaseTest{

	@Test
	public void googleSearchItemTest() {
		String searchString = "Java Tutorial";
		GoogleHome googleHome = new GoogleHome();

		String pageElementText = googleHome
								.waitForGoogleHomePageLoad()
								.enterSearchString(searchString)
								.pressEnter()
								.waitForGoogleSearchResultPageLoad()
								.getPageElementText();
		
		Assert.assertTrue(pageElementText.contains(searchString));
	}
}
