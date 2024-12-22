package infra;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import logger.Log;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import pageObjects.LoginPage;
import utilities.ExtentManager;
import utilities.ExtentTestManager;
import utilities.ReadExcelData;
import utilities.WebAppActions;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.UUID;



public class LoginBasePage extends Init {

	public static ExtentReports extent;
	public ExtentTest test;
	private static ThreadLocal<ExtentTest> localtest = new ThreadLocal<ExtentTest>();
	ReadExcelData red;
	private WebAppActions utils;
	LoginPage lp;

	public ExtentTest getTest() {
		return localtest.get();
	}

	/**
	 * TreadLocal getTest and setTest method are used to maintain thread safety .
	 *
	 */

	public void setTest(ExtentTest test) {
		localtest.set(test);

	}
	public void logTestMessage(String message) {
		try {
			getTest().log(Status.INFO, message );
		} catch (Exception ex) {
			Log.INFO(ex.getMessage());
		}
	}
	/**
	 * Start the test logging for the particular driver instance.
	 */

	public void testStart(String testName, String description) {
		test = (ExtentTestManager.startTest(testName, description).assignCategory().assignAuthor("Jibhaoo"));
		setTest(test);
	}



	/**
	 * Tear down the test logging for particular driver instance.
	 */
	public void endTest() {
		Log.INFO("Ending the test");
		//ExtentTestManager.endTest();
	}

	public void LinkedInLogin(WebDriver driver) throws IOException {
		Init.initDriver();
		red = new ReadExcelData();
		utils = new WebAppActions(driver);
		lp = new LoginPage(driver,utils);
		utils.logINFO(" Inside login ");

		String currentURl= driver.getCurrentUrl().trim();
		/*
		 *  Login to Production Env
		 */
		utils.logINFO(currentURl);
		if (currentURl.equals("https://www.linkedin.com/login"))
		{
			utils.logINFO("Logged in Successfully with User 1");

			lp.signIn(red.readDataFromExcelEnv("Login", "email","Dev"), red.readDataFromExcelEnv("Login", "password","Dev"));


		}

		else
		{
			//lp.signIn(red.readDataFromExcelEnv("Login", "email","Dev"), red.readDataFromExcelEnv("Login", "password","Dev"));

		}
		utils.logINFO("Successfully Login page");
	}

	public void logScreenshot(String screenName) {
			// Capture screenshot
			WebDriver driver = getDriver();

			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			byte[] encoded=null;
			try {
				encoded=Base64.encodeBase64(FileUtils.readFileToByteArray(screenshot));
			}catch(IOException e1)
			{
				e1.printStackTrace();
			}

			try {
				getTest().log(Status.INFO, "Screenshot:",
						MediaEntityBuilder.createScreenCaptureFromBase64String(new String(encoded, StandardCharsets.US_ASCII)).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}


	/**
	 * Generic function used to create snapshot directory if not exist.
	 */

	@BeforeMethod
	public void beforeMethod(Method m) {
		Log.INFO("/n" + "*******Starting " + m.getName() + " test******" + "/n");
	}

	@AfterMethod
	public void afterTest(ITestResult result) {
		try {

			if (result.getStatus() == ITestResult.FAILURE) {
				getTest().log(Status.FAIL, result.getThrowable());
				UUID uuid = UUID.randomUUID();
				logScreenshot(uuid.toString());
				utils.logERROR("TEST FAILED!!!");
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				getTest().log(Status.PASS, "TEST PASSED!!!");
				//utils.logINFO("TEST PASSED!!!");
			} else {
				getTest().log(Status.SKIP, "Test Skipped");
				Log.INFO("TEST SKIPPED!!!");
			}

			ExtentManager.getReporter().flush();

		}

		catch (Exception ex) {
			Log.INFO(" " + ex.getMessage());
		}
	}

	@AfterSuite
	public void AfterSuite() {

				driver.quit();


	}

	}





