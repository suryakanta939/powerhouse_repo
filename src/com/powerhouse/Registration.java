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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.powerhouse.commonLib.ScreenShot;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Registration {
//	 public static final String USERNAME = "davidding2";
//	  public static final String AUTOMATE_KEY = "jT7ix3GsnYAnApd1RdNy";
//  public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
		WebDriver driver; 
	String BaseUrl="https://pohostaging.com/";
	ExtentReports report;
	ExtentTest test;
	
	@BeforeClass
	public void setUp() throws InterruptedException {
//		  DesiredCapabilities caps = DesiredCapabilities.firefox();
//	    caps.setCapability("browser_version", "54.0");
//	    caps.setCapability("os", "Windows");
//	    caps.setCapability("os_version", "10");
//	    caps.setCapability("browserstack.debug", "true");
//	    driver = new RemoteWebDriver(new URL(URL), caps);
		/*
		 * setting the extent reports
		 * */
		report=new ExtentReports("E:\\SELENIUM_PROGRAM\\PowerHouse\\Report\\powerhouse.html");
		test=report.startTest("powerhouse registration");
		/*open the browser and hit the url*/
		driver=new FirefoxDriver();
		test.log(LogStatus.INFO, "browser is opened");
		driver.get(BaseUrl);
		test.log(LogStatus.INFO, "entered the powerhouse URL");
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Browser is maximized");
		Thread .sleep(1000);
		 /*click on the registration */
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		test.log(LogStatus.INFO, "clicked on the registration");
		
		
		
	} 
//	@BeforeMethod
//	public void setup() throws InterruptedException{
//		driver=new FirefoxDriver();
//		test.log(LogStatus.INFO, "browser is opened");
//		driver.get(BaseUrl);
//		test.log(LogStatus.INFO, "entered the powerhouse URL");
//		driver.manage().window().maximize();
//		test.log(LogStatus.INFO, "Browser is maximized");
//		Thread .sleep(1000);
//	}
	
  @Test(dataProvider="getdata")
  public void registration(String Un,String email,String Pwd,String Cpwd,String Fn,String Ln) throws InterruptedException {
	  if(Un==null||email==null||Pwd==null||Cpwd==null||Fn==null||Ln==null){
		  System.out.println("trying to get the null data so not sending to the registration from");
	  }
	  else{
	  System.out.println("user name "+Un);
	  System.out.println("user email "+email);
	  System.out.println("user pwd "+Pwd);
	  System.out.println("user Cpwd "+Cpwd);
	  System.out.println("user Fn "+Fn);
	  System.out.println("user Ln "+Ln);
// SoftAssert sa=new SoftAssert();
	  
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		/*fill the registration page*/
		driver.findElement(By.id("reg-name")).sendKeys(Un);
		test.log(LogStatus.INFO, "entered the username");
		
		driver.findElement(By.id("reg-email")).sendKeys(email);
		test.log(LogStatus.INFO, "entered the email id");
		
		driver.findElement(By.id("reg-pass")).sendKeys(Pwd);
		test.log(LogStatus.INFO, "entered the password");
		
		driver.findElement(By.id("reg-pass-confirmation")).sendKeys(Cpwd);
		test.log(LogStatus.INFO, "confirmed the password");
		
		driver.findElement(By.id("reg-fname")).sendKeys(Fn);
		test.log(LogStatus.INFO, "entered the first name");
		
		driver.findElement(By.id("reg-lname")).sendKeys(Ln);
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
		driver.findElement(By.id("user")).sendKeys(Un);
		test.log(LogStatus.INFO, "entered the user name");
		
		driver.findElement(By.id("pass")).sendKeys(Pwd);
		test.log(LogStatus.INFO, "entered the pass word");
		
		driver.findElement(By.xpath("//input[@value='Log In']")).click();
		test.log(LogStatus.INFO, "clciked on the login button");
		Thread.sleep(1000);
	//	sa.assertAll();
		/*check for the user name is logged in or not*/
//		String yourName=driver.findElement(By.xpath("//h1[text()='"+Fn+ Ln+"']")).getText();
//		System.out.println("the logged in user  is "+yourName);
//		sa.assertEquals(yourName, Fn+ Ln);
//		test.log(LogStatus.INFO , "the user "+yourName+" is logged in sucessfulyy");
		
//		  driver.findElement(By.xpath("//i[@class='fa fa-sign-out']")).click();
//		  driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//		  driver.findElement(By.xpath("//a[@id='register-trigger']")).click();
//		 // driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//		  Thread.sleep(1000);
	  }
	 
		
		
	
  }
  @AfterMethod
  public void keepTrack(ITestResult result) throws IOException, InterruptedException{
	  try{
	if( driver.findElement(By.xpath("//i[@class='fa fa-sign-out']")).isDisplayed())
	{
		 driver.findElement(By.xpath("//i[@class='fa fa-sign-out']")).click();
		  driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		  driver.findElement(By.xpath("//a[@id='register-trigger']")).click();
		 // driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		  Thread.sleep(1000);
	}
	  }
	  catch(Throwable t){
	
	  if(result.getStatus()==result.FAILURE){
		  ScreenShot.takeScreenShot(driver, result.getName());
	  }
	  else{
		  System.out.println("Test case got pass");
	  }
	  
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
  
  @DataProvider
  public Object[][]  getdata()throws InvalidFormatException, IOException 
{
	
	  FileInputStream fis=new FileInputStream("C:\\Users\\ad\\Desktop\\powerhouse.xlsx");
	 Workbook wb= WorkbookFactory.create(fis);
	Sheet sh= wb.getSheet("RegistrationData");
	int rn=sh.getLastRowNum();
	System.out.println(rn);
	Object[][] obj=new Object[rn+1][6];
	for(int i=1;i<rn+1;i++){
		Row rw=sh.getRow(i);
		for(int j=0;j<6;j++){
			Cell cell=rw.getCell(j);
			cell.setCellType(Cell.CELL_TYPE_STRING);   //This is for any type value from excel

				 String data=rw.getCell(j).getStringCellValue();
				  obj[i][j]=data;
			}
	}
	return obj;
  }
}

