package com.powerhouse.commonLib;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class WindowHandel {
	
 public static void multipleWindow(WebDriver driver,String title){
	 String parentId=driver.getWindowHandle();
	 Set<String> allIds=driver.getWindowHandles();
	 for(String ids:allIds){
		 if(!ids.equals(parentId)){
			 driver.switchTo().window(ids);
			 String actualTitle=driver.getTitle();
			 if(actualTitle.equals(title)){
				 driver.switchTo().window(ids);
			 }
		 }
	 }
	 
 }
	
}
