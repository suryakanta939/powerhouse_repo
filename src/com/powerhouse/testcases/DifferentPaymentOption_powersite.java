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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import com.powerhouse.commonLib.ActionFunctions;
import com.powerhouse.commonLib.ExplictyWait;
import com.powerhouse.commonLib.ExtentFactory;
import com.powerhouse.commonLib.ScreenShot;
import com.powerhouse.pageClasses.HomePage;
import com.powerhouse.pageClasses.PaymentOptions;
import com.powerhouse.pageClasses.PowersiteSubscription;
import com.powerhouse.pageClasses.RegistrationWithActivation;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DifferentPaymentOption_powersite {
	WebDriver driver; 
	String BaseUrl="https://pohostaging.com/";
	boolean flag=true;
	ExtentReports report;
	ExtentTest test;
	HomePage hp;
	PowersiteSubscription ps;
	PaymentOptions po;
	RegistrationWithActivation ra;
	@BeforeClass
	  public void beforeClass() throws InterruptedException {
		report=ExtentFactory.getInstancce();
		test=report.startTest("powerhouse registration");
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true); 
		profile.setAssumeUntrustedCertificateIssuer(false);
		/*open the browser and hit the url*/
	  driver=new FirefoxDriver(profile);
	  test.log(LogStatus.INFO, "browser is opened");
	  /*
	   * declaring the classes
	   * */
		hp=new HomePage(driver, test);
		ps=new PowersiteSubscription(driver, test);
		po=new PaymentOptions(driver, test);
		ra=new RegistrationWithActivation(driver, test);
		/*---------------------------------------------*/
		/*---------------------------------------------*/
		driver.get(BaseUrl);
		
		test.log(LogStatus.INFO, "entered the powerhouse URL");
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Browser is maximized");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//hp.loginToPowerHouse("arya1", "reset123");
		ra.use10MinMailToLogin();
		
	  }
  @BeforeMethod
  public void beforeMethod() throws InterruptedException {
	  ps.mousehoverOnMarketPlace();
		 test.log(LogStatus.INFO, "mouse over on the marketplace");
		 Thread.sleep(500);
		 ps.upgradeSubscription().click();
		 test.log(LogStatus.INFO, "clicked on the upgradesubscription");
		 
		 ps.bronzeAddToCart().click();
		 test.log(LogStatus.INFO, "clicked on the add to cart of bronze");
		 
		 ExplictyWait.waitForTheVisiilty(driver, 10,driver.findElement(By.xpath("//a[text()='View cart']")));
		 driver.findElement(By.xpath("//a[text()='View cart']")).click();
		 
		 Thread.sleep(1500);
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ps.proceedToCheckOut());
		 
		 ps.proceedToCheckOut().click();
		 po.fillingBillingDetails();
  }
  
  @Test(priority=1,enabled=false)
  public void directBankOption() throws InterruptedException {
	  po.direcBankTransfer();
	  Thread.sleep(3000);
	  
  }
  @Test(dependsOnMethods="directBankOption",enabled=false)
  public void directPayPalOption() throws InterruptedException {
	  po.payPalPayment();
	  Thread.sleep(3000);
	  
  }
  @Test(priority=1,enabled=false)
  public void creditcardSavedOption() throws InterruptedException {
	 // po.creditCardSavedAccount();
	  po.payThroughcreditCardSavedAccount();
	  Thread.sleep(3000);
	  
  }
  @Test(priority=1)
  public void creditcardNewOption() throws InterruptedException {
	//  po.creditCardNewAccount();
	  po.payThroughcreditCardNewAccount();
	  Thread.sleep(3000);  
  }
  
  @AfterMethod
  public void afterMethod(ITestResult result) throws IOException, InterruptedException {
	  if(result.FAILURE==result.getStatus()){
		  ScreenShot.takeScreenShot(driver, result.getName());
	  }else{
		  while(flag=true){
			  try{
				  ExplictyWait.waitForTheVisiilty(driver, 10, driver.findElement(By.xpath("//h1[text()='Checkout']")));
				  WebElement text=driver.findElement(By.xpath("//h1[text()='Checkout']"));
				  System.out.println("sucessfully "+text);
				  break;
			  }catch(Throwable t){
				  Thread.sleep(1000);
			  }
		  }
	  }
  }

  

  @AfterClass
  public void afterClass() {
	 test.assignAuthor("Suryakanta Sahoo");
	 report.endTest(test);
	 report.flush();
	  
  }

}
