package com.powerhouse.pageClasses;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.powerhouse.commonLib.ExplictyWait;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BodyPressPage {

	static WebElement element=null;
	static WebDriver driver=null;
	ExtentTest test;
	boolean flag=true;
	
  public BodyPressPage(WebDriver driver,ExtentTest test){
	this.driver=driver;
	this.test=test;
   }
  
  public static WebElement coverImagedelete(){
	  element=driver.findElement(By.xpath("//button[@id='woffice_cover_delete']"));
	  return element;
  }
  
  public static void clickOnDeleteButton(){
	  coverImagedelete().click();
  }
  
  public static WebElement addImageIcon(){
	   element = driver.findElement(By.xpath("//i[@class='fa fa-camera']"));
	  return element;
  }
  
  public void clickOnAddImageIcon() throws IOException{
	  int xCord=addImageIcon().getLocation().x;
	  int yCord=addImageIcon().getLocation().y;
	  System.out.println(xCord);
	  System.out.println(yCord);
	  Actions act=new Actions(driver);
		act.moveByOffset(xCord, yCord).click().build().perform();
  }
  
  public void uploadImage() throws IOException{
	  Runtime.getRuntime().exec("C:\\Users\\ad\\Desktop\\powerhouseimage.exe");
  }
  /*
   * this is the function to upload cover image
   * */
  public void addCoverImage() throws IOException 
  {
		ExplictyWait.waitForTheVisiilty(driver, 20, addImageIcon());
		  if(addImageIcon().isDisplayed()){
			  clickOnAddImageIcon();
			  uploadImage(); 
    }
		  }
  
  
  
  public void clearImage() throws InterruptedException{
	  if(coverImagedelete().isDisplayed()){
		  clickOnDeleteButton();
		  Thread.sleep(2000);
		  driver.navigate().refresh();
        }
	  else{
	  System.out.println("image is already cleared");
      }
	    
    }
  
}

