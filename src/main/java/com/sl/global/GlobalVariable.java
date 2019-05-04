package com.sl.global;

import com.sl.global.GlobalVariable;
import com.sl.util.PropertyHandler;

public class GlobalVariable {

	private static String OS_NAME = null;
	private static String PATH_SEPERATOR = "/";
	
	private static final String BASE_PATH = System.getProperty("user.dir").concat(PATH_SEPERATOR); 
	private static final String CONFIG_PATH = BASE_PATH.concat("config").concat(PATH_SEPERATOR); 
	private static final String DATA_PATH = BASE_PATH.concat("data").concat(PATH_SEPERATOR);
	private static final String LOG_PATH = BASE_PATH.concat("log").concat(PATH_SEPERATOR); 
	private static final String RESULT_PATH = BASE_PATH.concat("report").concat(PATH_SEPERATOR); 
	private static final String TESTNGFILE_PATH = BASE_PATH.concat("testng").concat(PATH_SEPERATOR); 
	
	private static String  REAL_DEVICE_HUB_URL= "";
	private static String  SAUCE_USER= "";
	private static String  SAUCE_KEY= "";
	
	private static String  WAIT_IMPLICIT= "";
	private static String  WAIT_EXPLICIT= "";
	
	private static PropertyHandler propertyHandler = null;
	
	static {
		
		OS_NAME = System.getProperty("os.name");
		String temp = OS_NAME.trim().replaceAll("\\s+", "").toLowerCase();
		
		if(temp.contains("mac")){
			PATH_SEPERATOR = "/";
		} else if(temp.contains("win")){
			PATH_SEPERATOR = "\\";
		}
		
		propertyHandler = new PropertyHandler();
		
		SAUCE_USER = System.getenv("SAUCE_USER");
		if(SAUCE_USER == null || SAUCE_USER == ""){
			SAUCE_USER = propertyHandler.getProperty("SAUCE_USER");
		}
		SAUCE_KEY = System.getenv("SAUCE_KEY");
		if(SAUCE_KEY == null || SAUCE_KEY == ""){
			SAUCE_KEY = propertyHandler.getProperty("SAUCE_KEY");
		}
		
		REAL_DEVICE_HUB_URL = propertyHandler.getProperty("REAL_DEVICE_HUB_URL");
		WAIT_IMPLICIT = propertyHandler.getProperty("WAIT_IMPLICIT");
		WAIT_EXPLICIT = propertyHandler.getProperty("WAIT_EXPLICIT");
		
	} // STATIC BLOCK END
	
	public static String getWAIT_IMPLICIT() {
		return WAIT_IMPLICIT;
	}
	public static String getWAIT_EXPLICIT() {
		return WAIT_EXPLICIT;
	}
	public static String getSAUCE_USER() {
		return SAUCE_USER;
	}
	public static String getSAUCE_KEY() {
		return SAUCE_KEY;
	}
	public static String getOS_NAME() {
		return OS_NAME;
	}
	public static String getBasePath() {
		return BASE_PATH;
	}
	public static String getConfigPath() {
		return CONFIG_PATH;
	}
	public static String getDataPath() {
		return DATA_PATH;
	}
	public static String getPATH_SEPERATOR() {
		return PATH_SEPERATOR;
	}
	public static String getLOG_PATH() {
		return LOG_PATH;
	}
	public static String getRESULT_PATH() {
		return RESULT_PATH;
	}
	
	public static String getTESTNGFILE_PATH() {
		return TESTNGFILE_PATH;
	}
	
	public static String getREAL_DEVICE_HUB_URL() {
		return REAL_DEVICE_HUB_URL;
	}
	
} // CLASS END
