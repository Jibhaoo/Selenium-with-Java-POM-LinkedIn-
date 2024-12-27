package pageObjects;

import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utilities.WebAppActions;

import java.util.List;


public class LISendWishes<WebElements> {

	public WebDriver driver;
	private WebAppActions utils;
	public static ExtentReports extent;

	@FindBy(xpath = "//button[@type='submit']")
    public WebElement sendbutton;

	@FindBy(xpath = "//*[name()='use' and contains(@href,'#close-sma')]")
    public WebElement closebutton;

	@FindBy(xpath = "//button[contains(@id,'ember')]//span[@class='props-s-message-button text-body-small']")
	public List <WebElement> wishes;

	public LISendWishes(WebDriver driver, WebAppActions utils) {
		this.driver = driver;
		this.utils = utils;
		PageFactory.initElements(driver, this);

	}

	public LISendWishes sendingWishes() throws InterruptedException {
		utils.logINFO("Navigating to My Network Page");
		driver.navigate().to("https://www.linkedin.com/mynetwork/grow/");
		utils.logINFO("Navigating to Catch Up");
		driver.navigate().to("https://www.linkedin.com/mynetwork/catch-up/all/");


		int wishessize = wishes.size();
		if(wishessize<=0)
		{
			utils.logINFO(" Wishes size is " +wishessize);
			utils.logINFO("Scrolling down to page");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)");
		}
		try {
			Thread.sleep(5000);
		}
		catch (Exception e)
		{

		}
		int displayedwishessize = wishes.size();
		utils.logINFO(" Wishes size after scrolling down is " +displayedwishessize);
		if(displayedwishessize>0) {


			int i=0;
			for (WebElement wsh : wishes)
			{

				//utils.scrollIntoView(wishes.get(i));
				if (wsh.isDisplayed()) {

						utils.logINFO("Clicking on Wishes");
						utils.isElementPresentAndClick(wsh);
						utils.logINFO("Clicking on Send button");
						utils.isElementPresentAndClick(sendbutton);
						utils.logINFO("Clicking on Close button");
						utils.isElementPresentAndClick(closebutton);


				}

				i++;
			}
		}
		else {

			utils.logINFO("Wish element is not displayed, skipping...");
			Assert.fail();

		}
		utils.logINFO("SUCCESS!! Wishes Send successfully");

		return this;

	}

}
