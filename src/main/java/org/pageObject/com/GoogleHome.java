package org.pageObject.com;

import org.basePage.com.BasePage;
import org.fiserv.enums.KeyboardEvents;
import org.fiserv.enums.WaitStrategy;
import org.openqa.selenium.By;

public class GoogleHome extends BasePage{
	
	private By googleTextBox = By.name("q");

	public GoogleHome enterSearchString(String searchString) {
		type(googleTextBox, searchString, "Google Search Text Box", WaitStrategy.NONE);
		return this;
	}
	
	public GoogleSearchResults pressEnter() {
		useKeyboardKeys(googleTextBox, "Google Search Text Box", KeyboardEvents.ENTER);
		return new GoogleSearchResults();
	}
	
	public GoogleHome waitForGoogleHomePageLoad() {
		 waitForPageLoad();
		 return this;
	}
	
	
}
