package com.sl.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sl.structure.RootTest;

public class TC_Language_Settings extends RootTest {
	
	@Test
	public void Test() throws Exception{
		
		testNGReporter.log("Click on kabab menu");
		d.click("//android.view.ViewGroup/android.support.v7.widget.LinearLayoutCompat/android.widget.TextView[1]");
		
		testNGReporter.log("Click on the settings option");
		d.click("//android.widget.TextView[contains(@text, 'Settings')]");
		
		testNGReporter.log("Verify the language value");
		String value = d.getText("//android.widget.TextView[contains(@text, 'Wikipedia language')]/following-sibling::android.widget.TextView");
		
		testNGReporter.log("Languge value is ".concat(value));
		Assert.assertEquals(value, "English");
		
	} // TEST END
	
} // CLASS END
