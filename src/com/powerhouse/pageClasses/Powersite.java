package com.powerhouse.pageClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;

public class Powersite {
	static WebElement element=null;
	static WebDriver driver=null;
	ExtentTest test;
	public Powersite(WebDriver driver,ExtentTest test){
		this.driver=driver;
		this.test=test;
	}
	public static WebElement dashBoardPowersite(){
		element=driver.findElement(By.xpath("//a[@id='user-powersites']"));
		return element;
	}
/*click on the powersite*/
	public static void clickOnPowersite(){
		dashBoardPowersite().click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public static String readPowersiteMessage(){
		String message=driver.findElement(By.xpath("//p[contains(text(),'create 0 PowerSite/s.')]")).getText();
		return message;
		
	}
	
	public static WebElement createPowersiteButton(){
         element=driver.findElement(By.xpath("//span[text()='Create PowerSite']"));
		return element;
	}
	
	/*click on powersite button*/
	
	public static void clickOnCreatePowerSite(){
		createPowersiteButton().click();
	}
}
