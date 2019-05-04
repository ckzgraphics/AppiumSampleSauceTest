package com.sl.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.sl.global.GlobalVariable;

public class ScreenshotHandler {

	public static void take_screenshot(WebDriver webDriver){
		try {
			TakesScreenshot scrShot = ((TakesScreenshot)webDriver);
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile=new File(GlobalVariable.getRESULT_PATH().concat("screenshot/test.png"));
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (Exception e) {
			 e.printStackTrace();
		}
	} // FUNC END
	
	public static void take_screenshot(WebDriver webDriver, String fileName){
		try {
			TakesScreenshot scrShot = ((TakesScreenshot) webDriver);
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile=new File(GlobalVariable.getRESULT_PATH().concat("screenshot/").concat(fileName));
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (Exception e) {
			 e.printStackTrace();
		}
	} // FUNC END
	
	
	public static void take_screenshot(WebDriver webDriver, String completeDirectoryPath, String fileName){
		try {
			TakesScreenshot scrShot = ((TakesScreenshot) webDriver);
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile=new File(completeDirectoryPath.concat(fileName));
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (Exception e) {
			 e.printStackTrace();
		}
	} // FUNC END
}