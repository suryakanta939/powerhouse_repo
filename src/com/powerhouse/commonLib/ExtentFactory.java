package com.powerhouse.commonLib;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentFactory {

	static ExtentReports extent;
	public static ExtentReports getInstancce(){
		 extent=new ExtentReports("E:\\SELENIUM_PROGRAM\\PowerHouse\\Report\\powerhouse.html",false);
		 return extent;
	}
}
