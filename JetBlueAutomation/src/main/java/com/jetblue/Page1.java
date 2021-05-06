package com.jetblue;

import java.awt.AWTException;
import java.awt.Desktop.Action;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

public class Page1 {
	WebDriver driver = null;
	FileInputStream fis=null;
	Properties prop=null;
	
	@BeforeSuite
	public void file() {
		File fine = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator
				+ "resources" + File.separator + "JetBlueFile" + File.separator + "jetBlue.properties");  
//				   prop.getProperty("toolsQA_ALERT");          
				try {
				fis = new FileInputStream(fine);
				} catch (FileNotFoundException e) {
				e.printStackTrace();
				}
				prop =new Properties();
				try {
				prop.load(fis);
				} catch (IOException e) {
				e.printStackTrace();
				}
				
	}

	@BeforeClass
	public void testClass() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\hari.subramani\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(prop.getProperty("JETBLUE_URL"));
		driver.manage().window().maximize();

	}

	@Test
	public void test1() throws InterruptedException {
		WebElement iframeElement = driver.findElement(By.xpath(prop.getProperty("COOKIE_PATH")));
		driver.switchTo().frame(iframeElement);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("ACCEPT_COOKIE"))).click();
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		// this is for One Way trip
		 driver.findElement(By.xpath(prop.getProperty("TRIP_dROPDOWN"))).click();
		 driver.findElement(By.xpath(prop.getProperty("ONE_WAY_TRIP"))).click();
	//	 Assert.assertFalse( driver.findElement(By.xpath(prop.getProperty("ONE_WAY_TRIP"))).isSelected());
		// Selecting Adults
		 driver.findElement(By.xpath(prop.getProperty("PASSENGER_SELECTION"))).click();
		int noofadults = 2;
		for (int i = 1; i < noofadults; i++) {
			driver.findElement(By.xpath(prop.getProperty("INCREASE_ONE_ADULT"))).click();
		// From Region
			WebElement input = driver.findElement(By.xpath(prop.getProperty("FROM_ADDRESS")));
		input.clear();
		input.sendKeys(prop.getProperty("SELECTED_FROM_ADDRESS"));
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		// To Region
		WebElement input2 = driver.findElement(By.cssSelector(prop.getProperty("TO_ADDRESS")));
		input2.clear();
		input2.sendKeys(prop.getProperty("SELECTED_TO_ADDRESS"));
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		// Departure date
		WebElement date = driver.findElement(By.xpath(prop.getProperty("DATE_PICKER")));
		date.sendKeys(prop.getProperty("INPUT_DATE"));
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		/*	    Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Search flights')]"))).build().perform();
        System.out.println("DONE!!!!!"); */
		Thread.sleep(4000);
		// Search Flight
		driver.findElement(By.xpath(prop.getProperty("SEARCH_BUTTION"))).click();
	//	Assert.assertEquals(true, false);
		
		
		Thread.sleep(1000);
		 driver.findElement(By.xpath(prop.getProperty("PRICE_BUTTON"))).click();

	}
	}

	@AfterClass
	public void quit() throws InterruptedException {
		//Thread.sleep(5000);
		//driver.quit();

	}

}
