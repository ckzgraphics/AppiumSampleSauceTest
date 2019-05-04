package com.sl.structure;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.TestRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.sl.global.Driver;
import com.sl.global.GlobalVariable;
import com.sl.util.Utils;

public abstract class RootTest {

	public Driver d = null;
	public Reporter testNGReporter = new Reporter();

	@BeforeClass(alwaysRun = true)
	@Parameters(value={"environment"})
	public void initDriver(String environment, ITestContext context){

		TestRunner runner = null;
		JSONObject completeConfigJson = null;
	
		try {
			// SET RESULT PATH
			runner = (TestRunner) context;
			runner.setOutputDirectory(GlobalVariable.getRESULT_PATH());
			
			// READ CONFIGURATION FILE
			completeConfigJson = Utils.getConfigurationFile(environment);
			
			// CREATE BASE DRIVER OBJECT
			d = new Driver();
			
			// CREATE WEB DRIVER INSTANCE BASED ON THE CONFIGURATION
			d.createDriver(completeConfigJson);
			
			if(context != null){
				context.setAttribute("driver", d.getDriverInstance());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // FUN initDriver END

	@AfterClass(alwaysRun = true)
	public void tearDown(){
		if(d != null){
			d.tearDownDriver();
		}
	} // FUNC tearDown END

} // CLASS BaseDriver END
