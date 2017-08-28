package com.powerhouse.pageClasses;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.powerhouse.commonLib.ExplictyWait;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class PaymentOptions 
{
	boolean flag=true;
	static WebElement element=null;
	static List<WebElement> elements=null;
    static WebDriver driver=null;
    ExtentTest test;
    String URL="https://www.sandbox.paypal.com/cgi-bin/webscr?test_ipn=1&cmd=_xclick-subscriptions&business=prabrisha1990-facilitator%40gmail.com&no_note=1&currency_code=USD&charset=utf-8&rm=2&upload=1&return=https%3A%2F%2Fpohostaging.com%2Fcheckout%2Forder-received%2F7819%3Fkey%3Dwc_order_59943191bfe42%26utm_nooverride%3D1&cancel_return=https%3A%2F%2Fpohostaging.com%2Fmembers%2Flalu%2Fshop%2Fcart%2F%3Fcancel_order%3Dtrue%26order%3Dwc_order_59943191bfe42%26order_id%3D7819%26redirect%26_wpnonce%3Dcad7fe8bff&page_style=&image_url=&paymentaction=sale&bn=WooThemes_Cart&invoice=WC-FREE-65&custom={%22order_id%22%3A7819%2C%22order_key%22%3A%22wc_order_59943191bfe42%22%2C%22subscription_id%22%3A7820%2C%22subscription_key%22%3A%22wc_order_59943192ac187%22}&notify_url=https%3A%2F%2Fpohostaging.com%2Fwc-api%2FWC_Gateway_Paypal%2F&first_name=LaLu&last_name=Sahoo&address1=addrss1&address2=addrss2&city=Bengaluru&state=Karnataka&zip=560008&country=IN&email=suryakanta%40abacies.com1&night_phone_b=67978978978&no_shipping=1&item_name_1=PowerSite%3A+Bronze+%28FREE+TRIAL%29+x+1&quantity_1=1&amount_1=0&item_number_1=FREE-65&item_name=Subscription+7820+%28Order+FREE-65%29+-+PowerSite%3A+Bronze+%28FREE+TRIAL%29&a1=0&p1=30&t1=D&a3=5.00&p3=1&t3=M&src=1&sra=0";
    public PaymentOptions(WebDriver driver,ExtentTest test){
    	this.driver=driver;
    	this.test=test;
    }
    
    public static WebElement directPayment(){
    	element=driver.findElement(By.xpath("//input[@id='payment_method_bacs']"));
    	return element;
    }
    
    public static WebElement payPal(){
    	element=driver.findElement(By.id("payment_method_paypal"));
    	return element;
    }
  
    
    public static WebElement creditCardStripe(){
    	element=driver.findElement(By.xpath("//input[@id='payment_method_stripe']"));
    	return element;
    }
    
  
    public static WebElement creditCardsaved(){
    	element=driver.findElement(By.xpath("//li[label[contains(text(),'Visa ending in')]]//input"));
    	return element;
    }
    
  
    public static WebElement creditCardnew(){
    	element=driver.findElement(By.xpath("//input[@id='wc-stripe-payment-token-new']"));
    	return element;
    }
    
    public static void clickPOnplaceOrder(){
    	driver.findElement(By.id("place_order")).click();
    }
    
    public static List<WebElement> crditCardOptions(){
    	
    	 elements=driver.findElements(By.xpath("//input[contains(@id,'wc-stripe-payment-token')]"));
		return elements;
    	
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
      public void fillingBillingDetails() throws InterruptedException{
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
    	driver.findElement(By.xpath("//li[text()='Bihar']")).click();
    	
      	driver.findElement(By.id("billing_postcode")).clear();
    	driver.findElement(By.id("billing_postcode")).sendKeys("560037");
    	
    	driver.findElement(By.id("billing_phone")).clear();
    	driver.findElement(By.id("billing_phone")).sendKeys("1212346");
      }
      /*
       * this function will hep in filling the pay paldetails
       * */
      public void fillingPaypalDetails() throws InterruptedException{
    	 
    	  try{
    		  while(true){
    			  try{
    			  ExplictyWait.waitForTheVisiilty(driver, 30, driver.findElement(By.xpath("//input[@id='payment_type_paypal']")));
    			  break;
    			  }catch(Throwable t){
    				  Thread.sleep(1000);
    			  }
    		  }
    		
    		 if(driver.findElement(By.xpath("//input[@id='payment_type_paypal']")).isDisplayed()){
    			 driver.findElement(By.xpath("//input[@id='payment_type_paypal']")).click();
    			 ExplictyWait.waitForTheVisiilty(driver, 10, driver.findElement(By.xpath("//input[@name='unified_login.x']")));
    			 driver.findElement(By.xpath("//input[@name='unified_login.x']")).click();
    			 driver.findElement(By.xpath("//input[@id='email']")).clear();
    			 driver.findElement(By.xpath("//input[@id='email']")).sendKeys("prabrisha1990@gmail.com");
    	    	  driver.findElement(By.xpath("//input[@id='password']")).clear();
    	    	  driver.findElement(By.xpath("//input[@id='password']")).sendKeys("prabrisha@123");
    	    	  driver.findElement(By.id("btnLogin")).click();
    	    	  while(true){
    	    		  try{
    	    			  ExplictyWait.waitForTheVisiilty(driver, 10, driver.findElement(By.xpath("//input[@name='submit.x']")));
    	    			  driver.findElement(By.xpath("//input[@name='submit.x']")).click();
    	    			  break;
    	    		  }catch(Throwable t){
    	    			  Thread.sleep(500);
    	    			  System.out.println(t.getMessage());
    	    		  }
    	    	  }
    	    	  driver.findElement(By.id("merchantRet")).click();
    		 }
    		 
    	  }
    	  catch(Throwable t){
    		  System.out.println(t.getMessage());
    		  Thread.sleep(1000);
    	  }
    	 
    	  
      }
      
      
      
   /*
    * this function is to pay direct through bank transfer
    * */   
      public void direcBankTransfer() throws InterruptedException{
    	  while(true){
    		try{
    			ExplictyWait.waitForTheVisiilty(driver, 10, directPayment());
    				 if(directPayment().isSelected()){
    		    		 clickPOnplaceOrder();
    		    		 Thread.sleep(3000);
    		    		 test.log(LogStatus.INFO, "placed the order");
    		    		 break;
    		    	 }else{
    		    		 directPayment().click();
    		    		 test.log(LogStatus.INFO, "clicked on the direct payment");
    		    		 clickPOnplaceOrder();
    		    		 Thread.sleep(3000);
    		    		 test.log(LogStatus.INFO, "placed the order");
    		    		 break;
    		    	 }
    			}
    		catch(Throwable t){
    			System.out.println(t.getMessage());
    			Thread.sleep(1000);
    		}
    			
    		}
    	
      
    }
      
      /*
       * this function is to pay  through paypal
       * */ 
      public void payPalPayment() throws InterruptedException{
    	  while(true){
    		try{
    			ExplictyWait.waitForTheVisiilty(driver, 10, payPal());
    				 if(payPal().isSelected()){
    		    		 clickPOnplaceOrder();
    		    		 Thread.sleep(3000);
    		    		 test.log(LogStatus.INFO, "placed the order");
    		    		 fillingPaypalDetails();
    		    		 test.log(LogStatus.INFO, "filled up the pay pal details");
    		    		 break;
    		    	 }else{
    		    		 payPal().click();
    		    		 test.log(LogStatus.INFO, "clicked on the direct payment");
    		    		 clickPOnplaceOrder();
    		    		 Thread.sleep(3000);
    		    		 test.log(LogStatus.INFO, "placed the order");
    		    		fillingPaypalDetails();
    		    		 test.log(LogStatus.INFO, "filled up the pay pal details");
    		    		 break;
    		    	 }
    		}
    		catch(Throwable t){
    			System.out.println(t.getMessage());
    			Thread.sleep(1000);
    		}
    			
    		}
    	
      
    }
      /*
       * this function is to pay  through crdeitcard saved account
       * */ 

      public void creditCardSavedAccount() throws InterruptedException{
    	  
      				 if(creditCardStripe().isSelected()){
      					 if(crditCardOptions().size()>1){
      						 creditCardsaved().click();
          		    		 clickPOnplaceOrder();
          		    		 Thread.sleep(3000);
          		    		 test.log(LogStatus.INFO, "placed the order");
          		    		
      					 }else{
      						 fillingBillingDetails();
      						clickPOnplaceOrder();
      					 }
      					
      		    	 }else{
      		    		creditCardStripe().click();
      		    		 if(crditCardOptions().size()>1){
      						 creditCardsaved().click();
          		    		 clickPOnplaceOrder();
          		    		 Thread.sleep(3000);
          		    		 test.log(LogStatus.INFO, "placed the order");
          		    		
      					 }else{
      						 fillingBillingDetails();
      						clickPOnplaceOrder();
      						
      					 }
      		    	 }
      			}
     
      /*
       * this function is to pay  through credit card new account
       * */ 

      public void creditCardNewAccount() throws InterruptedException{
    	  while(true){
      		try{
      			ExplictyWait.waitForTheVisiilty(driver, 10, creditCardStripe());
      			
      				 if(creditCardStripe().isSelected()){
      					creditCardnew().click();
      				   fillingUpVisaDetails();
      		    		 clickPOnplaceOrder();
      		    		 Thread.sleep(3000);
      		    		 test.log(LogStatus.INFO, "placed the order");
      		    		 break;
      		    	 }else{
      		    		creditCardStripe().click();
      		    		creditCardnew().click();
       				   fillingUpVisaDetails();
      		    		 test.log(LogStatus.INFO, "clicked on the direct payment");
      		    		creditCardsaved().click();
      		    		 clickPOnplaceOrder();
      		    		 Thread.sleep(3000);
      		    		 test.log(LogStatus.INFO, "placed the order");
      		    		 break;
      		    	 }
      			}
      		catch(Throwable t){
      			System.out.println(t.getMessage());
      			Thread.sleep(1000);
      		}
      			
      		}
      }
      /*
       * this function is to pay  through credit card for the first time
       * */ 
      public void payThroughcreditCardNewAccount() throws InterruptedException{
    	  while(true){
      		try{
      			ExplictyWait.waitForTheVisiilty(driver, 10, creditCardStripe());
      				 if(creditCardStripe().isSelected()){
      
                          if(crditCardOptions().size()>1){
                        	  creditCardnew().click();
                        	  fillingUpVisaDetails();
            		    		 clickPOnplaceOrder();
            		    		 Thread.sleep(3000);
            		    		 test.log(LogStatus.INFO, "placed the order");
            		    		 break;
                        	  
                          }
//                          else{
//                        	  fillingUpVisaDetails();
//           		    		 clickPOnplaceOrder();
//           		    		 Thread.sleep(3000);
//           		    		 test.log(LogStatus.INFO, "placed the order");
//           		    		 break; 
//                          }
      				 
      		    	 }else{
      		    		creditCardStripe().click();
      		    		if(crditCardOptions().size()>1){
      		    			creditCardnew().click();
                      	  fillingUpVisaDetails();
          		    		 clickPOnplaceOrder();
          		    		 Thread.sleep(3000);
          		    		 test.log(LogStatus.INFO, "placed the order");
          		    		 break;
                        }
      		    		else{
                        	
                        	fillingUpVisaDetails();
         		    		 test.log(LogStatus.INFO, "clicked on the direct payment");
         		    		 clickPOnplaceOrder();
         		    		 Thread.sleep(3000);
         		    		 test.log(LogStatus.INFO, "placed the order");
         		    		 break;
         		    	 }
                        }  
      			}
      		catch(Throwable t){
      			System.out.println(t.getMessage());
      			Thread.sleep(1000);
      		}
      			
      		}
      }
      
      /*
       * this function is to pay  through credit card for the first time
       * */ 
      public void payThroughcreditCardSavedAccount() throws InterruptedException{
    	  while(true){
      		try{
      			ExplictyWait.waitForTheVisiilty(driver, 10, creditCardStripe());
      				 if(creditCardStripe().isSelected()){
      					 
             System.out.println(crditCardOptions().size());
             
                          if(crditCardOptions().size()>1){
                        	  creditCardsaved().click();
           		    		 clickPOnplaceOrder();
           		    		 Thread.sleep(3000);
           		    		 test.log(LogStatus.INFO, "placed the order");
                        	  break;
                          }
                          else{
                        	  fillingUpVisaDetails();
           		    		 clickPOnplaceOrder();
           		    		 Thread.sleep(3000);
           		    		 test.log(LogStatus.INFO, "placed the order");
           		    		 break; 
                          }
      				 
      		    	 }else{
      		    		creditCardStripe().click();
      		    		if(crditCardOptions().size()>1){
      		    			creditCardsaved().click();
          		    		 clickPOnplaceOrder();
          		    		 Thread.sleep(3000);
          		    		 test.log(LogStatus.INFO, "placed the order");
                       	  break;
                        }
      		    		else{
                        	fillingUpVisaDetails();
         		    		 test.log(LogStatus.INFO, "Filling up visa details");
         		    		 clickPOnplaceOrder();
         		    		 Thread.sleep(3000);
         		    		 test.log(LogStatus.INFO, "placed the order");
         		    		 break;
         		    	 }
                        }  
      			}
      		catch(Throwable t){
      			System.out.println(t.getMessage());
      			Thread.sleep(1000);
      		}
      			
      		}
}

     }