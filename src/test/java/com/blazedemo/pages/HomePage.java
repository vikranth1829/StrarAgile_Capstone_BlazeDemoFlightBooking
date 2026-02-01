package com.blazedemo.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(name="fromPort") private WebElement departureCity;
	@FindBy(name="toPort") private WebElement destinationCity;
	@FindBy(css="input[value='Find Flights']") private WebElement findFlightButton;
	
	public HomePage(WebDriver driver,WebDriverWait wait)
	{
		this.driver = driver;
		this .wait = wait;
		PageFactory.initElements(driver,this);
	}
	
	public void searchFlight(String fromCity, String toCity)
	{
		Select selectFromCity = new Select(wait.until(ExpectedConditions.visibilityOf(departureCity)));
		selectFromCity.selectByContainsVisibleText(fromCity);
		
		Select selectToCity = new Select(wait.until(ExpectedConditions.visibilityOf(destinationCity)));
		selectToCity.selectByContainsVisibleText(toCity);
		
		wait.until(ExpectedConditions.elementToBeClickable(findFlightButton)).click();
	}
	
	public boolean verifyTitle()
	{
		return driver.getTitle().equals("BlazeDemo");
	}
	
	public boolean verifyURL()
	{
		return driver.getCurrentUrl().equals("https://blazedemo.com/");
	}
	
	public boolean areDropdownDisplayed()
	{
		return wait.until(ExpectedConditions.visibilityOf(departureCity)).isDisplayed() &&
		wait.until(ExpectedConditions.visibilityOf(destinationCity)).isDisplayed();
	}
	
	public boolean areDropdownEnable()
	{
		return wait.until(ExpectedConditions.visibilityOf(departureCity)).isEnabled() &&
				wait.until(ExpectedConditions.visibilityOf(destinationCity)).isEnabled();
	}
	
	public void sameDepartureandDestination(String fromCity,String toCity)
	{
		Select selectFromCity = new Select(wait.until(ExpectedConditions.visibilityOf(departureCity)));
		selectFromCity.selectByContainsVisibleText(fromCity);
		boolean exceptionThrown =false;
		try {
			Select selectToCity = new Select(wait.until(ExpectedConditions.visibilityOf(destinationCity)));
			selectToCity.selectByContainsVisibleText(toCity);
		}catch(NoSuchElementException e) {
			exceptionThrown=true;
		}
		Assert.assertTrue(exceptionThrown,"BUG: Confirmation is not appeared when Departure and Destination is Same! " +
	            "but success is shown.");	
	}
}



