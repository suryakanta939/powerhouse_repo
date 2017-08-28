package com.powerhouse.commonLib;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplictyWait {
	
	public static void presenceofElement(WebDriver driver,int timeInSec,String xpathOftheElement){
		WebDriverWait wait=new WebDriverWait(driver, timeInSec);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathOftheElement)));
	}
   
	public static void waitForTheVisiilty(WebDriver driver,int timeINSec,WebElement element){
		WebDriverWait wait=new WebDriverWait(driver, timeINSec);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitForTheVisibiltyOfAllElements(WebDriver driver,int timeinSec,List<WebElement> elements){
		WebDriverWait wait=new WebDriverWait(driver,timeinSec);
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		
	}
	public static void waitForTheElementToClick(WebDriver driver,int timeInsec,WebElement element){
		WebDriverWait wait=new WebDriverWait(driver, timeInsec);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
}
