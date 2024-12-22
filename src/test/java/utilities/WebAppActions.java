package utilities;

import infra.LoginBasePage;
import logger.Log;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class WebAppActions extends LoginBasePage {
	private SoftAssert softAssertion;
	public WebAppActions(WebDriver driver) {
		this.driver = driver;
	}

	public static WebDriver driver;
	/*
	 * Added by Shiv
	 */
	Logger LogNew = Logger.getLogger(Log.class.getName());

	/*
	 * this method will print messages in Console logs and as well as will log to
	 * EXTENT REPORTS using logTestMessage() method
	 */
	public void logINFO(String Message) {
		/*DOMConfigurator.configure("log4j.xml");
		LogNew.info(Message);
		logTestMessage(Message);*/
		URL u = Log.class.getClassLoader().getResource("log4j2.xml");
		DOMConfigurator.configure(u);
		LogNew.info(Message);
		logTestMessage(Message);
	}

	public void logERROR(String Message) {
		/*DOMConfigurator.configure("log4j.xml");
		LogNew.error(Message);*/

		URL u = Log.class.getClassLoader().getResource("log4j2.xml");
		DOMConfigurator.configure(u);
		LogNew.error(Message);
	}

	/*
	 * Get current date and time in MM/dd/yyyy,hh:mm:ss aa format
	 */
	public String getCurrentDateAndTime() {
		SimpleDateFormat gmtDateFormat = new SimpleDateFormat("MM/dd/yyyy,hh:mm:ss aa");
		gmtDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		// Current Date Time in GMT
		return gmtDateFormat.format(new Date());

	}



	public SoftAssert softAssert() {
		if (softAssertion == null) {
			return softAssertion = new SoftAssert();
		}
		return softAssertion;
	}



	public void isElementPresentAndClick(WebElement element)
	{

		WebDriverWait wait = new WebDriverWait(driver, Constant.shortTimeout);
		wait.until(ExpectedConditions.visibilityOf(element)).click();

	}
	public boolean scrollIntoView(WebElement element) {
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
			return true;
		} catch (Exception e) {
			System.err.println("Exception while scrolling into view: " + e.getMessage());
			return false;
		}
	}
	public void isElementPresentAndSendkeys(WebElement element, String sendkeys)
	{
		WebDriverWait wait = new WebDriverWait(driver,Constant.shortTimeout);
		wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(sendkeys);

	}
}
