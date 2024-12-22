package utilities;
import infra.Init;
import logger.Log;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import infra.LoginBasePage;
import java.io.File;
import java.net.URL;

/**
 * A Generic utility to control device actions. Such as Handling of
 * gestures,keyboard actions ,Wait conditions,snapshot.
 * 
 * @author Harshit Jhureley
 *
 */
public class AppActions extends LoginBasePage {
	WebDriver driver;
	public String deviceSerial = null;
	String filePath = null;
	Logger LogNew = Logger.getLogger(Log.class.getName());

	public AppActions(WebDriver driver) {
		super();
	}

	public String snapShot(String Screen) {

		Log.INFO("Taking Spanshot of " + deviceSerial);
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {

			filePath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + deviceSerial
					+ File.separator + Screen + ".png";
			Log.INFO(filePath);
			FileUtils.copyFile(srcFile, new File(filePath));
		} catch (Exception ex) {
			Log.INFO("InException");
			Log.INFO(ex.getMessage());
		}

		return filePath;
	}


	public void sleep(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (Exception ex) {
			Log.INFO(ex.getMessage());
		}
	}


}
