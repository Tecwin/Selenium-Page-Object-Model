package com.automation.SeleniumFramework.Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	public static ExtentReports getReportObject() {
		String path=System.getProperty("user.dir")+"/reports/index.html";
		ExtentSparkReporter extentSparkReporter=new ExtentSparkReporter(path);
		extentSparkReporter.config().setReportName("web automation results");
		extentSparkReporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extentReports=new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("Tester","Tecwin Rodrigues");
		return extentReports;
	}

}
