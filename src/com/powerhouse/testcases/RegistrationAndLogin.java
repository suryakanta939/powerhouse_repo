package com.powerhouse.testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.powerhouse.commonLib.ActionFunctions;
import com.powerhouse.commonLib.ExplictyWait;
import com.powerhouse.commonLib.ExtentFactory;
import com.powerhouse.commonLib.ScreenShot;
import com.powerhouse.commonLib.WindowHandel;
import com.powerhouse.pageClasses.HomePage;
import com.powerhouse.pageClasses.RegistrationWithActivation;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class RegistrationAndLogin {
	WebDriver driver;
	ExtentReports  report;
	ExtentTest test;
	String baseUrl="https://pohostaging.com/";
	String mailurl="https://10minutemail.net/";
	HomePage hp;
	RegistrationWithActivation ra;
 
  @BeforeClass
  public void beforeClass() {
	  report=ExtentFactory.getInstancce();
	  test=report.startTest("RegistrationAndLogin");
	  driver=new FirefoxDriver();
	  test.log(LogStatus.INFO, "browser opened");
	//  driver.get(mailurl);
	  test.log(LogStatus.INFO, "url is entered");
	  hp=new HomePage(driver, test);
	  ra=new RegistrationWithActivation(driver, test);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void loginPowerhouse() throws InterruptedException {
	  
   ra.use10MinMailToLogin();
   test.log(LogStatus.INFO, "logged in to powerhouser by using the 10 minutes mail");
	 
//	  hp.loginToPowerHouse("lalu", "reset123");
//	  test.log(LogStatus.INFO, "logged in to the user sucessfully");
	  
  }
  @AfterMethod
  public void tearDown(ITestResult result) throws IOException{
	  if(result.getStatus()==result.FAILURE){
		  ScreenShot.takeScreenShot(driver, result.getName());
		  test.log(LogStatus.INFO, "screen shot is taken");
	  }else{
		  System.out.println("test case got pass");
	  }
  }
  
  @AfterClass
  public void afterClass() {
//	  driver.quit();
	 test.assignAuthor("Suryakanta Sahoo");
	 report.endTest(test);
	 report.flush();
  }

}
