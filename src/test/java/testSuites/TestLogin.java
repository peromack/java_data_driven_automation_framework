package testSuites;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.TestBase;

public class TestLogin extends TestBase {

	@Test
	public void loginTest() throws InterruptedException {
		driver.findElement(By.cssSelector("a[href='/sign_in']")).click();
		log.debug("Clicked on Sign In link on Landing Page");
		
		driver.findElement(By.cssSelector("#full_name")).sendKeys("Tester 1");
		log.debug("Entered name: Tester 1");
		driver.findElement(By.cssSelector("#password")).sendKeys("password1");
		log.debug("Entered password: password1");
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		log.debug("Clicked submit button");
		
		Assert.assertTrue(driver.findElement(By.cssSelector("#flash_success")).isDisplayed(), "Login Success Flash message was not displayed");
		log.debug("Test Passed - User signed in successfully!");
	}
}
