package com.powerhouse.testcases;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.powerhouse.commonLib.ActionFunctions;
import com.powerhouse.commonLib.ExplictyWait;
import com.powerhouse.commonLib.ExtentFactory;
import com.powerhouse.pageClasses.BodyPressPage;
import com.powerhouse.pageClasses.HomePage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ImageUpload {
	WebDriver driver; 
	String BaseUrl="https://pohostaging.com/";
	boolean flag=true;
	ExtentReports report;
	ExtentTest test;
	HomePage hp;
	BodyPressPage bp;
	@BeforeClass
	public void setUp() throws InterruptedException{
		report=ExtentFactory.getInstancce();
		test=report.startTest("image Upload");
		/*open the browser and hit the url*/
	  driver=new FirefoxDriver();
	  test.log(LogStatus.INFO, "browser is opened");
	  /*
	   * declaring the classes
	   * */
		hp=new HomePage(driver, test);
		bp=new BodyPressPage(driver, test);
          driver.get(BaseUrl);
		
		test.log(LogStatus.INFO, "entered the powerhouse URL");
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Browser is maximized");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		hp.loginToPowerHouse("lalu", "reset123");
		
	}
	 @Test
	  public void clearingIMage() throws InterruptedException, IOException
	  {
		  bp.clearImage();
		
	  }
  @Test(dependsOnMethods="clearingIMage")
  public void addingCoverImage() throws InterruptedException, IOException
  {
	  bp.addCoverImage();
	
  }
}
