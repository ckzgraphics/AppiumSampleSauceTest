package com.sl.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sl.structure.RootTest;

public class TC_Search extends RootTest {
	
	@Test
	public void Test() throws Exception{
		
		testNGReporter.log("Click on the search box");
		d.click("//android.widget.TextView[contains(@index, '1')]");
		
		testNGReporter.log("Type the search string");
		d.sendKeys("//android.widget.EditText[contains(@index, '0')]", "SauceLabs");
		
		testNGReporter.log("Click on the first search result");
		d.click("//android.widget.ListView/android.widget.LinearLayout[1]/descendant::android.widget.TextView[1]");
		
		testNGReporter.log("Verify the lable value");
		String value = d.getText("//android.widget.TextView[contains(@resource-id, 'org.wikipedia.alpha:id/view_page_title_text')]");
		
		testNGReporter.log("Lable value is ".concat(value));
		Assert.assertEquals(value, "Sauce Labs");
		
	} // TEST END
	
} // CLASS END
