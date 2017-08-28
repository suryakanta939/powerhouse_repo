package com.powerhouse.pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.powerhouse.commonLib.ExplictyWait;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UpgradeGold {

	 static WebElement element=null;
	 static WebDriver driver=null;
	 static	ExtentTest test;
	public UpgradeGold(WebDriver driver,ExtentTest test){
		this.driver=driver;
		this.test=test;
	}
	public static WebElement bronzeFreeTrial(){
		element=driver.findElement(By.xpath("//a[text()='PowerSite: Bronze (FREE TRIAL)']"));
		return element;
	}
	
	public static void clickOnBronzefreetrial(){
		String text=bronzeFreeTrial().getText();
		System.out.println(text);
		bronzeFreeTrial().click();
	}
	
	public static void clickOnAddToCart(){
		driver.findElement(By.xpath("//button[contains(text(),'Add To Cart')]")).click();
	}
	/*
	 * this function is to click on the gold addto cart
	 * 
	 * */
	public void clickOnGoldCart() throws InterruptedException{
		PowersiteSubscription.mousehoverOnMarketPlace();
		test.log(LogStatus.INFO, "mouse hove on market place");
		
		 Thread.sleep(500);
		 PowersiteSubscription.upgradeSubscription().click();
		 test.log(LogStatus.INFO, "clicked on the upgradesubscription");
		 
		 PowersiteSubscription.goldAddToCart().click();
		 test.log(LogStatus.INFO, "clicked on the add to cart of bronze");
		 Thread.sleep(1500);
		 
	}
	
	
}
