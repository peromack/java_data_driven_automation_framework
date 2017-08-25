package testSuites;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.TestBase;

public class TestLogin extends TestBase {

	@BeforeMethod
	public void testSetUp() {
		driver.get("https://pubhub-rails-app.herokuapp.com/sign_in");
		log.debug("Navigated to Sign In Page");
	}
	
	@Test(dataProvider="getData")
	public void loginTest(String fullName, String password) throws InterruptedException {

		driver.findElement(By.cssSelector("#full_name")).sendKeys(fullName);
		log.debug("Entered name: " + fullName);
		driver.findElement(By.cssSelector("#password")).sendKeys(password);
		log.debug("Entered password: " + password);
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		log.debug("Clicked submit button");
		
		Assert.assertTrue(driver.findElement(By.cssSelector("#flash_success")).isDisplayed(), "Login Success Flash message was not displayed");
	}
	
	
	@Test(dataProvider="getData")
	public void invalidLoginTest(String fullName, String password) {
		
		driver.findElement(By.cssSelector("#full_name")).sendKeys(fullName);
		log.debug("Entered name: " + fullName);
		driver.findElement(By.cssSelector("#password")).sendKeys(password);
		log.debug("Entered password: " + password);
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		log.debug("Clicked submit button");
		
		Assert.assertTrue(driver.findElement(By.cssSelector("#flash_error")).isDisplayed(), "Login Failure Flash message was not displayed");
	}
	
}
