package com.powerhouse.pageClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.powerhouse.commonLib.ExplictyWait;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage {
	static WebElement element=null;
    static WebDriver driver=null;
    ExtentTest test;
    public HomePage(WebDriver driver,ExtentTest test){
    	this.driver=driver;
    	this.test=test;
    }
    
    public static WebElement dashBoard(){
    	element=driver.findElement(By.xpath("//a[text()='Dashboard']"));
    	return element;
    }
    
    public static WebElement Shop(){
    	element=driver.findElement(By.xpath("//a[text()='Shop']"));
    	return element;
    }
    public static WebElement Courses(){
    	element=driver.findElement(By.xpath("//a[text()='Courses']"));
    	return element;
    }
    
    public static WebElement Register(){
    	element=driver.findElement(By.xpath("//a[text()='Register']"));
    	return element;
    }
    /*elements for register form*/
    public void clickOnRegister(){
    	Register().click();
    	test.log(LogStatus.INFO,"clicked on the register");
    }
    /*elements for 
     * 
     * filling the registration form*/
    public static WebElement registrationfromusername(){
      element=driver.findElement(By.id("reg-name"));
    	return element;
    }
    public static WebElement email(){
        element=driver.findElement(By.id("reg-email"));
      	return element;
      }
    public static WebElement registrationPassWord(){
        element=driver.findElement(By.id("reg-pass"));
      	return element;
      }
    public static WebElement confirmPassword(){
        element=driver.findElement(By.id("reg-pass-confirmation"));
      	return element;
      }
    public static WebElement fisrstName(){
        element=driver.findElement(By.id("reg-fname"));
      	return element;
      }
    public static WebElement lastName(){
        element=driver.findElement(By.id("reg-lname"));
      	return element;
      }
    public static WebElement registerButton(){
        element=driver.findElement(By.xpath("//input[@name='reg_submit']"));
      	return element;
      }
    
    /*elements 
     * 
     * for log in*/
    public static WebElement Login(){
    	element=driver.findElement(By.xpath("//a[text()='Login']"));
    	return element;
    }
    
    public static WebElement userName(){
    	element=driver.findElement(By.id("user"));
    	return element;
    }
    public static WebElement password(){
    	element=driver.findElement(By.id("pass"));
    	return element;
    }

    public static WebElement logInBtn(){
    	element=driver.findElement(By.xpath("//input[@value='Log In']"));
    	return element;
    }
    /*
     * elements for sign out
     * */
    
    public static WebElement signoutBtn(){
    	 element=driver.findElement(By.xpath("//i[@class='fa fa-sign-out']"));
    	return element;
    }
    
    public static void clickOnSignOut(){
    	signoutBtn().click();
    	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }
    
    public static WebElement signUp(){
    	element=driver.findElement(By.xpath("//a[@id='register-trigger']"));
    	return element;
    }
    /*
     * elements for sign up
     * */
    public static void clickonSignUp(){
    	signUp().click();
    	
    }
    public void fillLoginForm(String UN,String PWD){
    	 userName().sendKeys(UN);
    	 test.log(LogStatus.INFO, "username is entered");
    	 password().sendKeys(PWD);
    	 test.log(LogStatus.INFO, "password is entered");
    	 logInBtn().click();
    	 test.log(LogStatus.INFO, "clicked on the login button");
    	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    public void loginToPowerHouse(String UN,String PWD) throws InterruptedException{
    	 Login().click();
    	 test.log(LogStatus.INFO, "clickedOn the login");
    	 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    	 userName().sendKeys(UN);
    	 test.log(LogStatus.INFO, "username is entered");
    	 password().sendKeys(PWD);
    	 test.log(LogStatus.INFO, "password is entered");
    	 logInBtn().click();
    	 test.log(LogStatus.INFO, "clicked on the login button");
    	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	 
    }
    
    public void fillTheregisterForm(String Un,String email,String Pwd,String Cpwd,String Fn,String Ln) throws InterruptedException{
    	registrationfromusername().sendKeys(Un);
    	test.log(LogStatus.INFO, "entered the username");
    	
    	email().sendKeys(email);
    	test.log(LogStatus.INFO, "entered the email id");
    	
    	registrationPassWord().sendKeys(Pwd);
    	test.log(LogStatus.INFO, "entered the password");
    	
    	confirmPassword().sendKeys(Cpwd);
    	test.log(LogStatus.INFO, "confirmed the password");
    	
    	fisrstName().sendKeys(Fn);
    	test.log(LogStatus.INFO, "entered the first name");
    	
    	lastName().sendKeys(Ln);
    	test.log(LogStatus.INFO, "entered the last name");
    	
    	registerButton().click();
    	test.log(LogStatus.INFO, "clicked on the registration button");
    	Thread.sleep(500);
    	try{
    	ExplictyWait.waitForTheVisiilty(driver, 10, driver.findElement(By.xpath("//strong[text()='Email Already in use']")));
    	if(driver.findElement(By.xpath("//strong[text()='Email Already in use']")).isDisplayed()){
    		registrationfromusername().clear();
        	test.log(LogStatus.INFO, "removed the username");
        	
        	email().clear();
        	test.log(LogStatus.INFO, "removed the email id");
        	
        	registrationPassWord().clear();
        	test.log(LogStatus.INFO, "removed the password");
        	
        	confirmPassword().clear();
        	test.log(LogStatus.INFO, "removed the password");
        	
        	fisrstName().clear();
        	test.log(LogStatus.INFO, "removed the name");
        	
        	lastName().clear();
        	test.log(LogStatus.INFO, "removed the name");
    	}
    	
   } 	
    catch(Throwable t)		{
    	ExplictyWait.waitForTheVisiilty(driver, 10, userName());
    		userName().sendKeys(Un);
       	 test.log(LogStatus.INFO, "username is entered");
       	ExplictyWait.waitForTheVisiilty(driver, 10, password());
       	 password().sendKeys(Pwd);
       	 test.log(LogStatus.INFO, "password is entered");
       	 logInBtn().click();
       	 test.log(LogStatus.INFO, "clicked on the login button");
       	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	}
    }
    
    
}
