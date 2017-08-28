package com.powerhouse;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.powerhouse.commonLib.ScreenShot;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SingleRegistration {
	 public static final String USERNAME = "davidding2";
	  public static final String AUTOMATE_KEY = "jT7ix3GsnYAnApd1RdNy";
  public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
		WebDriver driver; 
	String BaseUrl="https://pohostaging.com/";
	ExtentReports report;
	ExtentTest test;
	
	@BeforeClass
	public void setUp() throws InterruptedException, MalformedURLException{
		  DesiredCapabilities caps = DesiredCapabilities.firefox();
	    caps.setCapability("browser_version", "54.0");
	    caps.setCapability("os", "Windows");
	    caps.setCapability("os_version", "10");
	    caps.setCapability("browserstack.debug", "true");
	    driver = new RemoteWebDriver(new URL(URL), caps);
		/*
		 * setting the extent reports
		 * */
		report=new ExtentReports("E:\\SELENIUM_PROGRAM\\PowerHouse\\Report\\powerhouse.html");
		test=report.startTest("powerhouse registration");
		/*open the browser and hit the url*/
		//driver=new FirefoxDriver();
		test.log(LogStatus.INFO, "browser is opened");
		driver.get(BaseUrl);
		test.log(LogStatus.INFO, "entered the powerhouse URL");
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Browser is maximized");
		Thread .sleep(1000);
		
		
	} 
	
  @Test()
  public void registration() {
 SoftAssert sa=new SoftAssert();
	  
	  /*click on the registration */
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		test.log(LogStatus.INFO, "clicked on the registration");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		/*fill the registration page*/
		driver.findElement(By.id("reg-name")).sendKeys("davidtestk1");
		test.log(LogStatus.INFO, "entered the username");
		
		driver.findElement(By.id("reg-email")).sendKeys("davidtestk1@abac.com");
		test.log(LogStatus.INFO, "entered the email id");
		
		driver.findElement(By.id("reg-pass")).sendKeys("reset123");
		test.log(LogStatus.INFO, "entered the password");
		
		driver.findElement(By.id("reg-pass-confirmation")).sendKeys("reset123");
		test.log(LogStatus.INFO, "confirmed the password");
		
		driver.findElement(By.id("reg-fname")).sendKeys("davidtestk1");
		test.log(LogStatus.INFO, "entered the first name");
		
		driver.findElement(By.id("reg-lname")).sendKeys("sahoo");
		test.log(LogStatus.INFO, "entered the last name");
		
		driver.findElement(By.xpath("//input[@name='reg_submit']")).click();
		test.log(LogStatus.INFO, "clicked on the registration button");
		/*wait for some time*/
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		/*read the sucessfull message*/
//		String message=driver.findElement(By.xpath("//strong[contains(text(),'Registration complete. You can now')]")).getText();
//		sa.assertEquals("message", "Registration complete. You can now");
//		test.log(LogStatus.INFO, "read the sucessfulmessage after registration");
		
		/*login to the registered user*/
		driver.findElement(By.id("user")).sendKeys("davidtestk1");
		test.log(LogStatus.INFO, "entered the user name");
		
		driver.findElement(By.id("pass")).sendKeys("reset123");
		test.log(LogStatus.INFO, "entered the pass word");
		
		driver.findElement(By.xpath("//input[@value='Log In']")).click();
		test.log(LogStatus.INFO, "clciked on the login button");
		
		sa.assertAll();
		/*check for the user name is logged in or not*/
		String yourName=driver.findElement(By.xpath("//h1[text()='davidtestk1 sahoo']")).getText();
		System.out.println("the logged in user  is "+yourName);
		sa.assertEquals(yourName,"davidtestk1 sahoo");
		test.log(LogStatus.INFO , "the user "+yourName+" is logged in sucessfulyy");
		
	  }
	 
		
	
  
  @AfterMethod
  public void keepTrack(ITestResult result) throws IOException{
	  if(result.getStatus()==result.FAILURE){
		  ScreenShot.takeScreenShot(driver, result.getName());
	  }
	  else{
		  System.out.println("Test case got pass");
	  }
	  
  } 
  
  
  @AfterClass
  public void tearDown(){
	  driver.quit();
	  test.log(LogStatus.INFO,"browser cosled");
	  test.assignAuthor("suryakanta sahoo");
	  report.endTest(test);
	 report.flush();
	  
  }
  
 }

