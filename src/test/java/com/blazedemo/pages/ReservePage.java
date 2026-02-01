package com.blazedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ReservePage {
	
	private WebDriver driver;
	private WebDriverWait wait;

	String flightButtonxpath = "//tr[td[text()='%s']]//input[@type='submit']";
	
	public ReservePage(WebDriver driver,WebDriverWait wait)
	{
		this.driver=driver;
		this.wait=wait;
		PageFactory.initElements(driver, this);
	}
	
	public void chooseFlight(String flightNumber)
	{
		String dynamicXPath = String.format(flightButtonxpath, flightNumber);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXPath))).click();
	}
	
}
