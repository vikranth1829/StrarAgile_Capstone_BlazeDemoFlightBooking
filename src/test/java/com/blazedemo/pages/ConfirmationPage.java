package com.blazedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ConfirmationPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(xpath="/html/body/div[2]/div/h1") private WebElement successMsg;
	
	public ConfirmationPage(WebDriver driver,WebDriverWait wait)
	{
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}
	
	public void validateSuccessMsg()
	{
		String successmsg = successMsg.getText();
		Assert.assertEquals(successmsg,"Thank you for your purchase today!","Success message mismatch or Ticket Booking Failed");
	}
	
	

}
