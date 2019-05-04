package com.sl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.sl.global.GlobalVariable;

public class PropertyHandler {

	Properties properties = null;
	private String propertyFileName = "config.properties";
	private final String propertyFilePath = GlobalVariable.getConfigPath();

	public PropertyHandler(){
		setPropertyFile(propertyFileName, propertyFilePath);
	} // CTOR END

	public PropertyHandler(String propertyFileName, String propertyFilePath){
		setPropertyFile(propertyFileName, propertyFilePath);
	} // CTOR END
	

	public void setPropertyFile(String propertyFileName, String propertyFilePath){

		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(propertyFilePath.concat(propertyFileName)));
			properties = new Properties();
			properties.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // FUNC setPropertyFile END


	public String getProperty(String key){
		String propertyValue = "";
		if(key != null && key != ""){
			propertyValue = properties.getProperty(key);
		}
		return propertyValue;
	} // FUNC getProperty END


} // PropertyHandler END
