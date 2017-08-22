package testSuites;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.TestBase;

public class TestLogin extends TestBase {

	@BeforeMethod
	public void testSetUp() {
		driver.get("https://pubhub-rails-app.herokuapp.com/sign_in");
		log.debug("Navigated to Sign In Page");
	}
	
	@Test
	public void loginTest() throws InterruptedException {

		driver.findElement(By.cssSelector("#full_name")).sendKeys("Tester 1");
		log.debug("Entered name: Tester 1");
		driver.findElement(By.cssSelector("#password")).sendKeys("password1");
		log.debug("Entered password: password1");
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
	
	@DataProvider
	public Object[][] getData() {
		
		String sheetName = "TestLogin";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		
		Object[][] data = new Object[rows-1][cols];
		
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			for (int colNum = 0; colNum < cols; colNum++) {
				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		
		return data;
		
	}
}
