package com.powerhouse.commonLib;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionFunctions {

	public static void mouserOverOperation(WebDriver driver,WebElement element){
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	public static void dragAndDrop(WebDriver driver,WebElement srcelement,WebElement dstelement){
		Actions act=new Actions(driver);
		act.clickAndHold(srcelement).perform();
		act.moveToElement(dstelement).perform();
		act.release().perform();
	}
	
	public static void openNewTab(WebDriver driver){
		Actions act=new Actions(driver);
		act.sendKeys(Keys.chord(Keys.CONTROL,"t")).perform();
	}
	public static void shiftToTab(WebDriver driver){
		Actions act=new Actions(driver);
		act.sendKeys(Keys.chord(Keys.CONTROL,Keys.TAB)).perform();
	}
	
}
