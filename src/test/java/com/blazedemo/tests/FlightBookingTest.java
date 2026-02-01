package com.blazedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.blazedemo.pages.ConfirmationPage;
import com.blazedemo.pages.HomePage;
import com.blazedemo.pages.PurchasePage;
import com.blazedemo.pages.ReservePage;
import com.blazedemo.utils.BaseTest;

public class FlightBookingTest extends BaseTest{
	
	
	@Test(groups="Smoke")
	public void TC01_Verify_homepage_loads_and_dropdowns_visible()
	{
		HomePage homepage_obj = new HomePage(driver,wait);
		//System.out.println(driver.getTitle());
		//System.out.println(driver.getCurrentUrl());
		Assert.assertTrue(homepage_obj.verifyTitle(),"Home Page Title is Mismatch");   //Verify Title
		Assert.assertTrue(homepage_obj.verifyURL(),"Home Page URL is Mismatch");   //Verify URl
		Assert.assertTrue(homepage_obj.areDropdownDisplayed(),"Dropdown is Not Displayed");
		Assert.assertTrue(homepage_obj.areDropdownEnable(),"Dropdown is Not Enabled");  // Verify drop down is Enabled	
	}

	@Test(groups="Functional",dataProvider="FromToCity",dataProviderClass=TestData.class)
	public void TC02_Search_flights_with_valid_cities(String fromCity,String toCity)
	{
		HomePage homepage_obj = new HomePage(driver,wait);
		homepage_obj.searchFlight(fromCity,toCity);
		Assert.assertTrue(driver.getCurrentUrl().contains("reserve"),"Flight Result not displayed from "+fromCity + " to "+toCity);
	}
	
	@Test(groups="Functional",dataProvider="completeFlowTestData",dataProviderClass=TestData.class,dependsOnMethods="TC01_Verify_homepage_loads_and_dropdowns_visible")
	public void TC03_Complete_a_flight_booking(String fromCity,String toCity,String flightNumber,
												String name,String address,String city,String state,String zipcode,
												String cardTypeName,String cardnumber,String cardmonth,String cardyear,String nameoncard)
	{
		HomePage homepage_obj = new HomePage(driver,wait);
		homepage_obj.searchFlight(fromCity,toCity);
		ReservePage reservePage_obj = new ReservePage(driver,wait);
		reservePage_obj.chooseFlight(flightNumber);
		PurchasePage purchasePage_obj = new PurchasePage(driver,wait);
		purchasePage_obj.fillPassengersDetails(name, address, city, state, zipcode);
		purchasePage_obj.fillPassenderCardDetails( cardTypeName,cardnumber, cardmonth, cardyear, nameoncard);
		purchasePage_obj.rememberMeCheckBox();
		purchasePage_obj.purchaseFlightTicket();
		ConfirmationPage confirmationPage_obj = new ConfirmationPage(driver,wait);
		confirmationPage_obj.validateSuccessMsg();
	}
	
	@Test(groups="Data Driven",dataProvider="MultipleBookingDate",dataProviderClass=TestData.class)
	public void TC04_Multiple_bookings_with_different_data_sets(String fromCity,String toCity,String flightNumber,
																String name,String address,String city,String state,String zipcode,
																String cardTypeName,String cardnumber,String cardmonth,String cardyear,String nameoncard)
	{	
		HomePage homepage_obj = new HomePage(driver,wait);
		homepage_obj.searchFlight(fromCity,toCity);
		ReservePage reservePage_obj = new ReservePage(driver,wait);
		reservePage_obj.chooseFlight(flightNumber);
		PurchasePage purchasePage_obj = new PurchasePage(driver,wait);
		purchasePage_obj.fillPassengersDetails(name, address, city, state, zipcode);
		purchasePage_obj.fillPassenderCardDetails( cardTypeName,cardnumber, cardmonth, cardyear, nameoncard);
		purchasePage_obj.rememberMeCheckBox();
		purchasePage_obj.purchaseFlightTicket();
		ConfirmationPage confirmationPage_obj = new ConfirmationPage(driver,wait);
		confirmationPage_obj.validateSuccessMsg();
	}
	
	@Test(groups="Negative",dataProvider="BlankCCdetailsNegativeTestData",dataProviderClass=TestData.class)
	public void TC05_Blank_credit_card(String fromCity, String toCity,String flightNumber,
										String name,String address,String city,String state,String zipcode,
										String cardTypeName,String cardnumber,String cardmonth,String cardyear,String nameoncard)
	{
		HomePage homepage_obj = new HomePage(driver,wait);
		homepage_obj.searchFlight(fromCity, toCity);
		ReservePage reservePage_obj = new ReservePage(driver,wait);
		reservePage_obj.chooseFlight(flightNumber);
		PurchasePage purchasePage_obj = new PurchasePage(driver,wait);
		purchasePage_obj.fillPassengersDetails(name,address,city,state,zipcode);
		purchasePage_obj.fillPassenderCardDetails(cardTypeName, cardnumber, cardmonth, cardyear, nameoncard);
		purchasePage_obj.purchaseTicketBlankCardDetails();
	}
	
	
	@Test(groups="Negative",dataProvider="NonNumericCreditCardDetails",dataProviderClass=TestData.class)
	public void TC06_Invalid_credit_card_characters(String fromCity,String toCity,String flightNumber,
													String name,String address,String city,String state,String zipcode,
													String cardTypeName,String cardnumber,String cardmonth,String cardyear,String nameoncard)
	{
		HomePage homepage_obj = new HomePage(driver,wait);
		homepage_obj.searchFlight(fromCity,toCity);
		ReservePage reservePage_obj = new ReservePage(driver,wait);
		reservePage_obj.chooseFlight(flightNumber);
		PurchasePage purchasePage_obj = new PurchasePage(driver,wait);
		purchasePage_obj.fillPassengersDetails(name,address,city,state,zipcode);
		purchasePage_obj.fillPassenderCardDetails(cardTypeName, cardnumber, cardmonth, cardyear, nameoncard);
		
	}
	
	@Test(groups="Negative",dataProvider="sameDepartureandDestination",dataProviderClass=TestData.class,
											expectedExceptions = org.openqa.selenium.NoSuchElementException.class)
	public void TC07_Same_departure_and_destination_city(String fromCity,String toCity)
	{
		HomePage homepage_obj = new HomePage(driver,wait);
		homepage_obj.sameDepartureandDestination(fromCity,toCity);
		
		
	}
}
