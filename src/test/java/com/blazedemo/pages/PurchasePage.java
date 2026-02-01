package com.blazedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PurchasePage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public PurchasePage(WebDriver driver,WebDriverWait wait)
	{
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="inputName") private WebElement cust_Name;
	@FindBy(id="address") private WebElement cust_Address;
	@FindBy(id="city") private WebElement cust_City;
	@FindBy(id="state") private WebElement cust_State;
	@FindBy(id="zipCode") private WebElement cust_ZipCode;
	@FindBy(xpath="//*[@id=\"cardType\"]") private WebElement cust_CardType;
	@FindBy(id="creditCardNumber") private WebElement cust_CardNumber;
	@FindBy(id="creditCardMonth") private WebElement cust_CardMonth;
	@FindBy(id="creditCardYear") private WebElement cust_CardYear;
	@FindBy(id="nameOnCard") private WebElement cust_NameOnCard;
	@FindBy(id="rememberMe") private WebElement rememberMeCheckBox;
	@FindBy(xpath="//input[@type='submit' and contains(@value,'Purchase Flight')]") private WebElement purchaseFlight;
	@FindBy(xpath="/html/body/div[2]/div/h1") private WebElement successMsg;
	
	public void fillPassengersDetails(String name,String address,String city,String state,String zipcode)
	{
		//System.out.println("Current URL = " + driver.getCurrentUrl());
		cust_Name.sendKeys(name);
		cust_Address.sendKeys(address);
		cust_City.sendKeys(city);
		cust_State.sendKeys(state);
		cust_ZipCode.sendKeys(zipcode);
		
	}
	
	public void fillPassenderCardDetails( String cardTypeName,String cardnumber,String cardmonth,String cardyear,String nameoncard)
	{
		Select cardType = new Select(wait.until(ExpectedConditions.visibilityOf(cust_CardType)));
		cardType.selectByContainsVisibleText(cardTypeName);
		cust_CardNumber.sendKeys(cardnumber);
		cust_CardMonth.sendKeys(cardmonth);
		cust_CardYear.sendKeys(cardyear);
		cust_NameOnCard.sendKeys(nameoncard);
	}
	
	public void rememberMeCheckBox()
	{
		rememberMeCheckBox.click();
	}
	
	public void purchaseFlightTicket() 
	{
		purchaseFlight.click();
		//Assert.assertTrue(successMsg.getText().contains("Thank you for your purchase today!"));;
	}
	
	public void purchaseTicketBlankCardDetails()
	{
		purchaseFlight.click();
		String smsg=successMsg.getText();
		boolean successmsg=smsg.contains("Thank you for your purchase today!");
		Assert.assertTrue(successmsg,"BUG: Confirmation is not appeared when Card details are Blank ! " +
	            "but success is shown.");
	}
	
	public void Invalidcreditcardcharacters()
	{
		purchaseFlight.click();
		String smsg=successMsg.getText();
		boolean successmsg=smsg.contains("Thank you for your purchase today!");
		Assert.assertTrue(successmsg,"BUG: Confirmation is not appeared when Card Number is AlphaNumeric ! " +
	            "but success is shown.");	
	}
	
	public void LeaveRequiredFieldsblank()
	{
		purchaseFlight.click();
		String smsg=successMsg.getText();
		boolean successmsg=smsg.contains("Thank you for your purchase today!");
		Assert.assertTrue(successmsg,"BUG: Confirmation is not appeared when Required filed missing ! " +
	            "but success is shown.");	
	}
	
	
	
}
