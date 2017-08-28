package com.powerhouse.commonLib;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ScreenShot {
	
	public static void takeScreenShot(WebDriver driver,String fileName) throws IOException{
		EventFiringWebDriver eDriver=new EventFiringWebDriver(driver);
		File srcFile=eDriver.getScreenshotAs(OutputType.FILE);
		File destFile=new File("E:\\SELENIUM_PROGRAM\\PowerHouse\\ScreenShot\\"+fileName+".png");
		FileUtils.copyFile(srcFile, destFile);
	}

}
