package utilities;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


/**
 * MultiThreaded Model having synchronized method to support parallel Execution.
 * 
 * 
 *
 */

public class ExtentTestManager {
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	private static ExtentReports extent = ExtentManager.getReporter();

	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized void endTest() {
		extent.removeTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
	}

	public static synchronized ExtentTest startTest(String device) {
		
		//this testName is reflected in the Extent report as
		// eg. checkIntroSlides: emulator-5554
		String testName = Thread.currentThread().getStackTrace()[3].getMethodName() + ": " + device;
		return startTest(testName, ""); 
		//returning startTest(String testName, String desc) method present
		//below the current method
	}

	public static synchronized ExtentTest startTest(String testName, String desc) {

		ExtentTest test = extent.createTest(testName, desc);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);

		return test;

	}

}
