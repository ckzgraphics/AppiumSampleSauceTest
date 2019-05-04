package com.sl.global;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sl.global.GlobalVariable;
import com.sl.util.PropertyHandler;
import com.sl.util.Utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Driver {
	
	@SuppressWarnings("rawtypes")
	AppiumDriver driver = null;
	WebDriverWait webDriverWait = null;
	WebElement element = null;
	MobileElement mobElement = null;
	By by = null;
	MobileBy mobBy = null;
	
	JavascriptExecutor js = null;
	String sessionId = "";
	
	PropertyHandler propertyHandler = null;
	private static final Logger driverLogger = Logger.getLogger(Driver.class.getSimpleName());
	
	/**
	 * DEFAULT CTOR
	 */
	public Driver(){
		propertyHandler = new PropertyHandler();
		
		try {
			String timestamp = Utils.getTimeStamp();
			FileHandler fileHandler = new FileHandler(GlobalVariable.getLOG_PATH().concat(Driver.class.getSimpleName()).concat("_").concat(timestamp).concat(".log"));
			driverLogger.addHandler(fileHandler);
			
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);
		} catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
	} // DEFAULT CTOR
	
	/**
	 * This method handles web driver setup
	 * @param completeConfigJson JSON OBJECT CONTAINING ENVIRONMENT CONFIG
	 */
	public void createDriver(JSONObject completeConfigJson){
		try {
			
			driverLogger.log(Level.INFO, "Creating driver instance");
			String environment = "sauce";
			String execution = "";
			JSONObject capabilities = null;
		
			execution = completeConfigJson.getString("execution");
			driverLogger.log(Level.INFO, "Execution environment is ".concat(execution));
			if(execution != null && execution != ""){
				environment =  execution.toLowerCase();
			}
			
			if(environment.equals("sauce")){
				capabilities = completeConfigJson.getJSONObject("capabilities");
				createSauceDriverWithCaps(capabilities);
			} else if(environment.equals("browserstack")){
				// CREATE DRIVER WITH BROWSERSTACK SETUP
			} else if(environment.equals("remote")){
				// CREATE DRIVER WITH LOCAL GRID SETUP | LOCAL SELENIUM GRID SETUP
			} else if(environment.equals("local")){
				// CREATE DRIVER WITH LOCAL SETUP
			}
			
			driver.manage().timeouts().implicitlyWait(Long.parseLong(GlobalVariable.getWAIT_IMPLICIT()), TimeUnit.SECONDS);
			webDriverWait = new WebDriverWait(driver, Long.parseLong(GlobalVariable.getWAIT_EXPLICIT()));
			
			js = (JavascriptExecutor) driver;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // FUNC createDriver END
	
	
	/**
	 * This method creates web driver instance for sauce lab config
	 * @param capabilities CAPABILITIES
	 */
	private void createSauceDriverWithCaps(JSONObject capabilities){
		
		try {
			driverLogger.log(Level.INFO, "Creating driver using sauce caps..");
			DesiredCapabilities caps = new DesiredCapabilities();
			String value = "";
			
			if(capabilities != null){
				
				value = capabilities.getString("testobject_suite_name");
				if(value != null){
					caps.setCapability("testobject_suite_name", value.toLowerCase());
				}
				
				value = capabilities.getString("testobject_test_name");
				if(value != null){
					caps.setCapability("testobject_test_name", value.toLowerCase());
				}
				
				value = capabilities.getString("testobject_app_id");
				if(value != null){
					caps.setCapability("testobject_app_id", value.toLowerCase());
				}
				
				value = capabilities.getString("platformName");
				if(value != null){
					caps.setCapability("platformName", value.toLowerCase());
				}
				
				value = capabilities.getString("platformVersion");
				if(value != null){
					caps.setCapability("platformVersion", value);
				}
				
				caps.setCapability("testobjectApiKey", GlobalVariable.getSAUCE_KEY());
				
			}
			
			driver = new AndroidDriver(new URL(GlobalVariable.getREAL_DEVICE_HUB_URL()), caps);
			sessionId = ((AppiumDriver) driver).getSessionId().toString();
			driverLogger.log(Level.INFO, "Driver created with session id ".concat(sessionId));
			
		} catch (Exception e) {
			e.printStackTrace();
			driverLogger.log(Level.INFO, e.getMessage());
		}
	} // FUNC createLocalDriverWithCaps END
	
	public AppiumDriver getDriverInstance(){
		return driver;
	}
	
	public void tearDownDriver(){
		if(driver != null){
			driver.quit();
			driverLogger.log(Level.INFO, "Driver instance closed");
		}
	} // FUNC tearDownDriver END
	
	public String getSessionId(){
		return sessionId;
	} // FUNC END
	
	public String getTitle(){
		return driver.getTitle();
	} // FUNC END
	
	public String getPageSource(){
		return driver.getPageSource();
	} // FUNC END
	
	public String getCurrentUrl(){
		return driver.getCurrentUrl();
	} // FUNC END
	
	public void openURL(String url) {
		try {
			driver.get(url);
		} catch (TimeoutException t) {
			t.printStackTrace();
		} catch (WebDriverException w){
			w.printStackTrace();
		}
	} // FUNC END
	
	public boolean click(String locator){
		boolean executed = false;
		try {
			mobElement = (MobileElement) webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath(locator)));
			mobElement.click();
			executed = true;
		} catch (Exception e) {
			executed = false;
			e.printStackTrace();
		}
		return executed;
	} // FUNC END
	
	public boolean sendKeys(String locator, String inputText){
		boolean executed = false;
		try {
			mobElement = (MobileElement) webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath(locator)));
			mobElement.sendKeys(inputText);
			executed = true;
		} catch (NoSuchElementException nse) {
			executed = false;
			nse.printStackTrace();
		} catch (WebDriverException e) {
			executed = false;
			e.printStackTrace();
		}
		return executed;
	} // FUNC END

	
	public void scrollUp(int scrollPixelValue){
		js.executeScript("javascript:window.scrollBy(0,"+ scrollPixelValue+")");
	} // FUNC END
	
	public void scrollDown(int scrollPixelValue){
		js.executeScript("javascript:window.scrollBy(0,"+ (scrollPixelValue*-1)+")");
	} // FUNC END
	
	public String getText(String locator){
		mobElement = (MobileElement) webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath(locator)));
		return mobElement.getText();
	} // FUNC END
	
} // CLASS Driver END
