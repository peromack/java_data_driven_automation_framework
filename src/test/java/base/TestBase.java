package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
	/*
	 * Serves as the superclass for all test suites
	 * Initializes/Configures the following:
	 * Selenium WebDriver
	 * Properties
	 * Logs
	 * ExtentReports
	 * DB
	 * Excel 
	 * Mail
	 * Jenkins
	 */
	
	public static WebDriver driver;
	public static Properties config;
	public static FileInputStream fis;
	
	@BeforeSuite
	public void setUp() throws IOException {
		if (driver==null) {
			
			// load the Config.properties configuration file which contains sets of key-value pairs
			fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/Config.properties");
			config = new Properties();
			config.load(fis); 
			
			// set the driver to the browser defined in the config file
			if (config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/executables/chromedriver");
				driver = new ChromeDriver();
			}
			
			// instantiate the driver, maximize window, set implicit wait time
			driver.get(config.getProperty("test_site_url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	}
	
	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}
