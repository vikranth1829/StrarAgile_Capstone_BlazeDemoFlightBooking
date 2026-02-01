package com.blazedemo.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportListeners implements ITestListener{
	
	private static ExtentSparkReporter htmlReport;
	private static ExtentReports report;
	private static ThreadLocal<ExtentTest> test =new ThreadLocal<>();
	
	@Override
	public void onStart(ITestContext context)
	{
		htmlReport = new ExtentSparkReporter("BlazeDemoFlightBooking.html");
		report = new ExtentReports();
		report.attachReporter(htmlReport);
		
		report.setSystemInfo("ProjectName", "BlazeDemo FlightBooking");
		report.setSystemInfo("Machine", "Windows");
		report.setSystemInfo("Company", "Star Agile");
		report.setSystemInfo("User", "Automation user");
		report.setSystemInfo("Browser", "Chrome");
		report.setSystemInfo("Tool", "Selenium Webdriver");

		// configuration to report
		htmlReport.config().setDocumentTitle("My simple Extent Report");
		htmlReport.config().setReportName("Selenium Report");
		htmlReport.config().setTheme(Theme.STANDARD);
		htmlReport.config().setTimeStampFormat("dd-MM-YYYY");
	}

	@Override
	public void onTestStart(ITestResult result)
	{
		ExtentTest node = report.createTest(result.getMethod().getMethodName());
		test.set(node);
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		test.get().pass("Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		
		test.get().fail("Test Failed"+result.getThrowable());	
	}

	@Override
	public void onFinish(ITestContext context)
	{
		report.flush();
	}
	
}


