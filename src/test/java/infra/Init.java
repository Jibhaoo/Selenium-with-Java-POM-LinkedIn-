package infra;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.StaticTestData;

import java.util.concurrent.TimeUnit;

public class Init {
	public static WebDriver driver;

	// Initialize the driver when the class is loaded
	static {
		initDriver();
	}

	static void initDriver() {
		if (driver == null) {
			// Setup ChromeDriver using WebDriverManager
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			// Retrieve URL from environment variable
			String url = StaticTestData.URL;

			if (url != null) {
				// Open the URL in the browser
				driver.get(url);

				// Optional: Print the page title for verification
				System.out.println("Page Title: " + driver.getTitle());
			} else {
				System.out.println("Please set the URL environment variable.");
			}
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}

}