package com.powerhouse.pageClasses;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.powerhouse.commonLib.ExplictyWait;
import com.relevantcodes.extentreports.ExtentTest;

public class checkOut {
	boolean flag=true;
	static WebElement element=null;
    static WebDriver driver=null;
    ExtentTest test;
    public checkOut(WebDriver driver,ExtentTest test){
    	this.driver=driver;
    	this.test=test;
    }
    /*
     * This function is to filling up the visa details
     * */
  public void fillingUpVisaDetails() throws InterruptedException{
	  for(int i=0;i<4;i++){
	  driver.findElement(By.id("stripe-card-number")).sendKeys("4242");
	  }
	  for(int i=0;i<2;i++){
		  if(i==0){
	  driver.findElement(By.id("stripe-card-expiry")).sendKeys("11");
		  }else{
			  driver.findElement(By.id("stripe-card-expiry")).sendKeys("27");
		  }
	  }
	  driver.findElement(By.id("stripe-card-cvc")).sendKeys("111");
  }
  
  /*
   * check for the availability
   * 
   * */
    public void billingDetails() throws InterruptedException{
    	driver.findElement(By.id("billing_first_name")).clear();
    	driver.findElement(By.id("billing_first_name")).sendKeys("raman");
    	
    	driver.findElement(By.id("billing_last_name")).clear();
    	driver.findElement(By.id("billing_last_name")).sendKeys("sahoo");
    	
    	driver.findElement(By.id("billing_company")).clear();
    	driver.findElement(By.id("billing_company")).sendKeys("Abacies");
    	
    	driver.findElement(By.id("billing_address_1")).clear();
    	driver.findElement(By.id("billing_address_1")).sendKeys("HAL");
    	
    	driver.findElement(By.id("billing_city")).clear();
    	driver.findElement(By.id("billing_city")).sendKeys("Banglore");
    	
    	driver.findElement(By.xpath("//span[@aria-labelledby='select2-billing_state-container']")).click();
    	ExplictyWait.waitForTheVisiilty(driver, 10, driver.findElement(By.xpath("//ul[@id='select2-billing_state-results']")));
    	WebElement results=driver.findElement(By.xpath("//ul[@id='select2-billing_state-results']"));
    	List<WebElement> state=results.findElements(By.xpath("//ul[@id='select2-billing_state-results']//li"));
    	for(int i=0;i<state.size();i++){
    		String stateName=state.get(i).getText();
    		System.out.println(stateName);
    	}
    	driver.findElement(By.xpath("//li[text()='Bihar']")).click();
    	driver.findElement(By.id("billing_postcode")).clear();
    	driver.findElement(By.id("billing_postcode")).sendKeys("560037");
    	driver.findElement(By.id("billing_phone")).clear();
    	driver.findElement(By.id("billing_phone")).sendKeys("1212346");
    	while(flag){
    		try{
    		ExplictyWait.waitForTheVisiilty(driver,60, driver.findElement(By.xpath("//input[@id='payment_method_stripe']")));	
    		if(driver.findElement(By.xpath("//input[@id='payment_method_stripe']")).isSelected()){
    			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    			fillingUpVisaDetails();
				driver.findElement(By.id("place_order")).click();
				break;
			}else{
    		
    			driver.findElement(By.xpath("//input[@id='payment_method_stripe']")).click();
    			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    			fillingUpVisaDetails();
    			driver.findElement(By.id("place_order")).click();
	    	      break;
    	}
    
    	}catch(Throwable t){
    		Thread.sleep(1000);
    	}
    }
    	
   }
}
