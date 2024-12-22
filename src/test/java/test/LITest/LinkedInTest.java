package test.LITest;

import infra.Init;
import infra.LoginBasePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.LISendWishes;
import utilities.AppActions;
import utilities.ExtentManager;
import utilities.WebAppActions;

import java.io.File;
import java.io.IOException;

public class LinkedInTest extends LoginBasePage {
    private AppActions utility;
    private WebDriver driver;
    private WebAppActions utils;
    LISendWishes pp;
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("*****This is Login Test BeforeSuite*****");
        extent = ExtentManager.getReporter(
                new File("").getAbsolutePath() + File.separator + "Reports" + File.separator + "ExtentReport.html");
    }

    @Parameters({ "deviceName", "os", "platform" })
    @BeforeTest
    public void setUp(String deviceName, String os, String platform) throws InterruptedException, IOException {
        System.out.println("*****This is Login Test BeforeTest*****");
        Init init = new Init();
        driver = init.getDriver();
        utility = new AppActions(driver);
        utils = new WebAppActions(driver);
        pp = new LISendWishes(driver,utils);

        LinkedInLogin(driver);
    }

    @Test(priority = 1)
    public void VerifyTheSendWishes() throws InterruptedException {
        testStart("Verify the Send Wishes Functionality", "Should send wishes to all ");
        pp.sendingWishes();
        endTest();
    }

}