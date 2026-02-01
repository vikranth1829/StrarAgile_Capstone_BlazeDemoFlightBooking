package com.blazedemo.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(ExtentReportListeners.class)
public class BaseTest {
	
	public WebDriver driver;
	protected WebDriverWait wait;
	
	@BeforeMethod
	@Parameters("browser")
	public void baseSetup()
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get("https://blazedemo.com/");
	}

	@AfterMethod
	public void tearDown()
	{
		if(driver!=null)
		{
		driver.quit();
		}
	}
}
