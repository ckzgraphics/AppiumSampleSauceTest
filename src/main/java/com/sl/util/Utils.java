package com.sl.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import com.sl.global.GlobalVariable;

public class Utils {

	/**
	 * @param fileName with extension
	 * @return file extension
	 */
	public static String getFileExtension(String fileName){
		String returnString = null;
		String[] stringArray = null;

		if(fileName != null){
			stringArray = fileName.split("\\.");
			returnString = stringArray[stringArray.length-1];
		}
		return returnString;
	} // FUNC getFileExtension END
	
	/**
	 * @param inputStream
	 * @return String content of inputStream
	 */
	public static String convertInputStreamToString(InputStream inputStream){
		String stringValue = "";
		ByteArrayOutputStream result = null;
		try {
			result = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = inputStream.read(buffer)) != -1) {
				result.write(buffer, 0, length);
			}
			// StandardCharsets.UTF_8.name() > JDK 7
			stringValue = result.toString("UTF-8");;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stringValue;
	} // FUNC convertInputStreamToString END

	/**
	 * @param environment contains a configuration file name with JSON extension 
	 * @return JSON with environment details
	 */
	public static JSONObject getConfigurationFile(String environment){
		File fileObj = null;
		InputStream inputStreamReader = null;
		String jsonString = null;
		JSONObject completeConfigJson = null;
		try {
			fileObj = new File(GlobalVariable.getConfigPath().concat(environment));
			inputStreamReader = new FileInputStream(fileObj);
			jsonString = Utils.convertInputStreamToString(inputStreamReader);
			completeConfigJson = new JSONObject(jsonString);
			if(inputStreamReader != null){
				inputStreamReader.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return completeConfigJson;
	} // FUNC getConfigurationFile END
	
	public static String getTimeStamp(){
		return new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date());
	}


} // CLASS END
