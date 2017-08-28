package com.powerhouse.pageClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.powerhouse.commonLib.ActionFunctions;
import com.powerhouse.commonLib.ExplictyWait;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class PowersiteSubscription {

	static WebElement element=null;
	static WebDriver driver=null;
	ExtentTest test;
	public PowersiteSubscription(WebDriver driver,ExtentTest test){
		this.driver=driver;
		this.test=test;
		
	}
	
	public static WebElement marketplace(){
		element=driver.findElement(By.xpath("//a[text()='Marketplace']"));
		return element;
	}
	
	public static WebElement upgradeSubscription(){
		element=driver.findElement(By.xpath("//a[text()='Upgrade Subscription']"));
		return element;
	}
	
	public static WebElement powersiteSubscription(){
		element=driver.findElement(By.xpath("//li[text()='PowerSite Subscriptions']"));
		return element;
	}
	
	public static WebElement bronzeAddToCart(){
		element=driver.findElement(By.xpath("//li[a[h2[contains(text(),'Bronze (FREE TRIAL)')]]]//a[text()='Add To Cart']"));
		return element;
	}
	public static WebElement goldAddToCart(){
		element=driver.findElement(By.xpath("//li[a[h2[contains(text(),'Upgrade: Gold')]]]//a[text()='Add To Cart']"));
		return element;
	}
	public static WebElement platinumAddToCart(){
		element=driver.findElement(By.xpath("//li[a[h2[contains(text(),'Upgrade: Platinum')]]]//a[text()='Add To Cart']"));
		return element;
	}
	
	public static WebElement proceedToCheckOut(){
		element=driver.findElement(By.xpath("//a[contains(text(),'Proceed to checkout')]"));
		return element;
	}
	
	/*mouse hover marketplace*/
	public static void mousehoverOnMarketPlace(){
		ExplictyWait.waitForTheVisiilty(driver, 10, marketplace());
		ActionFunctions.mouserOverOperation(driver, marketplace());
	
	}
	 public void purchaseBronze() throws InterruptedException{
		 mousehoverOnMarketPlace();
		 test.log(LogStatus.INFO, "mouse over on the marketplace");
		 Thread.sleep(500);
		 upgradeSubscription().click();
		 test.log(LogStatus.INFO, "clicked on the upgradesubscription");
		 
		 bronzeAddToCart().click();
		 test.log(LogStatus.INFO, "clicked on the add to cart of bronze");
		 
		 ExplictyWait.waitForTheVisiilty(driver, 10,driver.findElement(By.xpath("//a[text()='View cart']")));
		 driver.findElement(By.xpath("//a[text()='View cart']")).click();
		 
		 Thread.sleep(1500);
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", proceedToCheckOut());
		 
		 proceedToCheckOut().click();
		 
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		checkOut ch=new checkOut(driver, test);
//		ch.billingDetails();
		 
		 /*checking for extra tings*/
		 CopyOfcheckOut ch=new CopyOfcheckOut(driver, test);
		 ch.billingDetails();
		 
		 
	 }
	
}
