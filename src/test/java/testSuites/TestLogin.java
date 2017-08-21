package testSuites;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.TestBase;

public class TestLogin extends TestBase {

	@Test
	public void loginTest() throws InterruptedException {
		driver.findElement(By.cssSelector("a[href='/sign_in']")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("input[type='submit']")).isDisplayed(), "User not redirected to Log In page");
	}
}
