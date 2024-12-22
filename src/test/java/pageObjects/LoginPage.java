package pageObjects;

import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WebAppActions;

import java.util.List;


public class LoginPage {

	public WebDriver driver;
	private WebAppActions utils;
	public static ExtentReports extent;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement signInButton;

	@FindBy(id = "username")
	public WebElement email_TextField;

	@FindBy(id = "password")
	public WebElement pass_TextField;



	public LoginPage(WebDriver driver, WebAppActions utils) {
		this.driver = driver;
		this.utils = utils;
		PageFactory.initElements(driver, this);

	}

	public LoginPage signIn(String email, String pass)
	{

		utils.logINFO("Entering Email id");
		utils.isElementPresentAndSendkeys(email_TextField,email);
		utils.logINFO("Entering password");
		utils.isElementPresentAndSendkeys(pass_TextField,pass);
		utils.logINFO("Clicking on Sign In button");
		utils.isElementPresentAndClick(signInButton);
		try {
			Thread.sleep(40000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;

	}

}
