package com.sl.listner;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.sl.global.GlobalVariable;
import com.sl.structure.RootTest;
import com.sl.util.ScreenshotHandler;

public class TestNGListner extends RootTest implements ITestListener {

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {
		
		String[] strArray = result.getTestClass().getName().split("\\.");
		String testClassName = strArray[strArray.length-1];
		
		String methodName = "Test_Passed_".concat(testClassName).concat(".png");
		ITestContext context = result.getTestContext();
		WebDriver driver = (WebDriver)context.getAttribute("driver");
		ScreenshotHandler.take_screenshot(driver, GlobalVariable.getRESULT_PATH(), methodName);
		
	}

	public void onTestFailure(ITestResult result) {
		
		String[] strArray = result.getTestClass().getName().split("\\.");
		String testClassName = strArray[strArray.length-1];
		
		String methodName = "Test_Failed_".concat(testClassName).concat(".png");
		ITestContext context = result.getTestContext();
		WebDriver driver = (WebDriver)context.getAttribute("driver");
		ScreenshotHandler.take_screenshot(driver, GlobalVariable.getRESULT_PATH(), methodName);
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
