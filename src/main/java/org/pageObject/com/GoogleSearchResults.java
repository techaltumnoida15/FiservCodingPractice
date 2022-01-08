package org.pageObject.com;

import org.basePage.com.BasePage;
import org.fiserv.enums.WaitStrategy;
import org.openqa.selenium.By;

public class GoogleSearchResults extends BasePage{

	private By results = By.cssSelector("#search > div > div > div");
	
	public GoogleSearchResults waitForGoogleSearchResultPageLoad() {
		waitForPageLoad();
		return this;
	}
	
	public String getPageElementText() {
		return getElementText(results, WaitStrategy.NONE);
	}
}
