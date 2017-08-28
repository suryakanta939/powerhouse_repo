package com.powerhouse.testcases;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import com.powerhouse.commonLib.ExtentFactory;
import com.powerhouse.commonLib.ScreenShot;
import com.powerhouse.pageClasses.HomePage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class MultipleRegistration {
//	public static final String USERNAME = "davidding2";
//	  public static final String AUTOMATE_KEY = "jT7ix3GsnYAnApd1RdNy";
//public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	WebDriver driver; 
	String BaseUrl="https://pohostaging.com/";
	ExtentReports report;
	ExtentTest test;
	HomePage hp;
  
  @BeforeClass
  public void beforeClass() throws InterruptedException, MalformedURLException
  {
//	  DesiredCapabilities caps = DesiredCapabilities.firefox();
//	    caps.setCapability("browser_version", "54.0");
//	    caps.setCapability("os", "Windows");
//	    caps.setCapability("os_version", "10");
//	    caps.setCapability("browserstack.debug", "true");
//	    driver = new RemoteWebDriver(new URL(URL), caps);
		/*
		 * setting the extent reports
		 * */
		report=ExtentFactory.getInstancce();
		test=report.startTest("powerhouse registration");
		/*open the browser and hit the url*/
		driver=new FirefoxDriver();
		test.log(LogStatus.INFO, "browser is opened");
		hp=new HomePage(driver, test);
		driver.get(BaseUrl);
		test.log(LogStatus.INFO, "entered the powerhouse URL");
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Browser is maximized");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		 /*click on the registration */
	  hp.clickOnRegister();
	  Thread .sleep(1000);
  }
  
  @Test(dataProvider="getdata")
  public void  registration(String Un,String email,String Pwd,String Cpwd,String Fn,String Ln) throws InterruptedException
  {
	  if(Un==null||email==null||Pwd==null||Cpwd==null||Fn==null||Ln==null){
		  System.out.println("getting the null data so not sending to the registration from");
	  }
	  else{
	  System.out.println("user name "+Un);
	  System.out.println("user email "+email);
	  System.out.println("user pwd "+Pwd);
	  System.out.println("user Cpwd "+Cpwd);
	  System.out.println("user Fn "+Fn);
	  System.out.println("user Ln "+Ln);
	  /*
	   * filling up the registration form
	   * */
	  hp.fillTheregisterForm(Un, email, Pwd, Cpwd, Fn, Ln);
	  test.log(LogStatus.INFO, "sucessfully register");
	  }
	  
  }
  @AfterMethod
  public void tearDown(ITestResult result) throws IOException{
	  try{
	  if(hp.signoutBtn().isDisplayed()){
		  hp.clickOnSignOut();
		  test.log(LogStatus.INFO, "sucessfully signout");
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  hp.clickonSignUp();
		  test.log(LogStatus.INFO, "clicked on the sign Up button");
	  }
	  }
	  catch(Throwable t){
		  if(result.getStatus()==result.FAILURE){
			  ScreenShot.takeScreenShot(driver, result.getName());
		  }else{
			  System.out.println("test case got pass");
		  }
	  }
  }

  @AfterClass
  public void afterClass() 
  {
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
