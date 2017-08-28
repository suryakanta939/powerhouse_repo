package com.powerhouse.commonLib;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectFunctions {
	
 public static void selectElementByValue(WebElement element,String value){
	 Select sel=new Select(element);
	 sel.selectByValue(value);
 }
 public static void selectElementByText(WebElement element,String text){
	 Select sel=new Select(element);
	 sel.selectByVisibleText(text);
 }
 public static void selectElementByIndex(WebElement element,int index){
	 Select sel=new Select(element);
	 sel.selectByIndex(index);
 }
 public static void showAlltheElements(WebElement element){
	 Select sel=new Select(element);
	 List<WebElement> allAvailabeleOptions=sel.getOptions();
	 System.out.println("The Total No Of Elements present is "+allAvailabeleOptions);
	 for(int i=0;i<allAvailabeleOptions.size();i++){
		 String texts=allAvailabeleOptions.get(i).getText();
		 System.out.println(texts);
	 }
 }
 
}
