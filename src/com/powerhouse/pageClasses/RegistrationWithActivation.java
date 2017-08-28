package com.powerhouse.pageClasses;

import java.util.concurrent.TimeUnit;

import jdk.nashorn.internal.runtime.Context.ThrowErrorManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.powerhouse.commonLib.ActionFunctions;
import com.powerhouse.commonLib.ExplictyWait;
import com.powerhouse.commonLib.RandomNames;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class RegistrationWithActivation {
	static WebElement element=null;
    static WebDriver driver=null;
    static ExtentTest test;
    static String mail=null;
   // String username="ipman1";
    String Password="reset123";
    String baseUrl="https://pohostaging.com/";
    public RegistrationWithActivation(WebDriver driver,ExtentTest test){
    	this.driver=driver;
    	this.test=test;
    }
    
    public static WebElement errorMessage(){
    	element=driver.findElement(By.xpath("//p[text()='Invalid username and/or password.']"));
    	return element;
    }
    public static String errorMessageText(){
    	 String error=errorMessage().getText();
    	return error;
    }
    /*
     * This function is to get email from 10 minutes
     * */
    public static String getEmailFrom10minutes(){
    	String email=driver.findElement(By.id("fe_text")).getAttribute("value");
		return email;
    }
    /*
     * This function is to get email from 10 minutes.uk
     * */
    public static String getEmailFrom10minutesUk(){
    	String mail=driver.findElement(By.id("email")).getText();
		return mail;
    }
    
    public static WebElement email10Minutes(){
    	element=driver.findElement(By.id("fe_text"));
    	return element;
    }
    
    public static WebElement activationMessage(){
    	element=driver.findElement(By.xpath("//a[contains(text(),'Activate your account')]"));
    	return element;
    }
    
    public static WebElement activationLink(){
    	element=driver.findElement(By.xpath("//p[contains(text(),'Thanks for registering! To complete')]//a"));
    	return element;
    }
    /*
     * This function is to get the email_id
     * */
    public static String getemail(){
    	try{
    		driver.navigate().to("https://10minutemail.net/");
    		ExplictyWait.waitForTheVisiilty(driver, 10, email10Minutes());
    		 mail=getEmailFrom10minutes();
    		
    	}
    	catch(Throwable t){
    		driver.navigate().to("https://www.10minutemail.co.uk/");
    		 mail=getEmailFrom10minutesUk();
    	}
    	return mail;
    }
    /*
     * This functuion is actiavate account
     * */
    public static void activateAccount() throws InterruptedException{
    	while(true){
    	try{
    		ExplictyWait.waitForTheVisiilty(driver, 10,activationMessage() );
    		activationMessage().click();
    		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    		/*click on the activation link*/
    		activationLink().click();
    		break;
    		
    		
    	}
    	catch(Throwable t){
    		Thread.sleep(1000);
    	}
    }
   }
    
    public void use10MinMailToLogin() throws InterruptedException{
    	String emailid= getemail();
    	test.log(LogStatus.INFO, "read the email");
    	ActionFunctions.openNewTab(driver);
    	test.log(LogStatus.INFO, "opened a new tab");
    	driver.get(baseUrl);
    	HomePage hp=new HomePage(driver, test);
    	hp.clickOnRegister();
    	test.log(LogStatus.INFO, "clicked on the register");
   	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
   	 
   	 String username=RandomNames.name();// getting some random name
   	 
   	hp.registrationfromusername().sendKeys(username);
   	test.log(LogStatus.INFO, "username is entered");
   	hp.email().sendKeys(emailid);
   	test.log(LogStatus.INFO, "email is entered");
   	hp.registrationPassWord().sendKeys(Password);
   	test.log(LogStatus.INFO, "password is entered");
   	hp.confirmPassword().sendKeys(Password);
   	test.log(LogStatus.INFO, "confirmpassword is entered");
   	hp.registerButton().click();
   	test.log(LogStatus.INFO, "clicked on the registerbutton");
   	/*try to login with the user name & password*/
    hp.fillLoginForm(username, Password);
   
    while(true){
    	try{
    		ExplictyWait.waitForTheVisiilty(driver, 10, errorMessage());
    		ActionFunctions.shiftToTab(driver);
    		test.log(LogStatus.INFO, "shited to the 10minutes mail");
    		String wid=driver.getWindowHandle();
    		driver.switchTo().window(wid);
    		activateAccount();
    		test.log(LogStatus.INFO, "account is activated");
    		Thread.sleep(1000);
    		ActionFunctions.shiftToTab(driver);
    		test.log(LogStatus.INFO, "shifted to powerhouse loin page");
    		String wid1=driver.getWindowHandle();
    		driver.switchTo().window(wid1);
    		hp.fillLoginForm(username, Password);
    		test.log(LogStatus.INFO, "logged in sucessfully");
    		break;
    	}
    	catch(Throwable t){
    		
    		Thread.sleep(1000);
    	}
    }
   	
   	
    }
}
