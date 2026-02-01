package com.blazedemo.tests;

import org.testng.annotations.DataProvider;

import com.blazedemo.utils.BaseTest;

public class TestData extends BaseTest{

	@DataProvider(name="FromToCity")
	public Object[][] fromToCityTestData()
	{
		return new Object[][] {{"Boston","Buenos Aires"}};
	}

	@DataProvider(name="completeFlowTestData")
	public Object[][] completeTestData()
	{
		return new Object[][] {{"Paris",		   // from City
								"Buenos Aires",    // to City
								"4346",
								"Vikranth Mano",    // passengerName
					            "123 Main St",     // address
					            "New York",        // city
					            "NY",              // state
					            "10001",           // zipCode
					            "American Express", //card type
					            "1234 1234 1234",   //card number
					            "10",              //card month
					            "2035",            //Expiry Year
					            "Vikranth M"     //Name on Card
					           }};
	}
	
	@DataProvider(name="MultipleBookingDate")
	public Object[][] MultipleBookingDate()
	{
		return new Object[][] {{ "Portland",		   // from City
								 "Dublin",    // to City
								 "4346",
								 "Sachin",    // passengerName
								 "2547 Seconds Stree",     // address
								 "Trichy",        // city
								 "TN",              // state
								 "621216",           // zipCode
								 "Visa", //card type
								 "1234 4567 8910",   //card number
								 "06",              //card month
								 "2040",            //Expiry Year
								 "Sachin Tendulkar"      //Name on Card
								},
								{ "Mexico City","London","9696","Rahul Dravit","No 10 lard Stree","Bangalore",       
									 "AP","987216","Diner's Club","9999 8888 7777","01","2038","Rahul Dravit" },
								{ "Philadelphia","Cairo","234","Brain Lara","125 West Stree","West Indies",       
									 "WI","001587","American Express","2227 6547 7878","04","2045","Brain Lara" }
		};
	}
	
	@DataProvider(name="BlankCCdetailsNegativeTestData")
	public Object[][] BlankCreditCard()
	{
		return new Object[][] {{"São Paolo","Rome","9696","Rahul Dravit","No 10 lard Stree","Bangalore",       
								"AP","987216","","","","","" }}; 
	
	}
	
	@DataProvider(name="NonNumericCreditCardDetails")
	public Object[][] NonNumericCreditCardDetails()
	{
		return new Object[][] {{"Mexico City","Berlin","234","Wirendar Shewag","No 10 lard Stree","Delhi",       
								"MP","787216","Visa","qwer werr 1234 6577","12","2039","Shewag" }};
	}
	
	@DataProvider(name="sameDepartureandDestination")
	public Object[][] SameDepartureandDestination()
	{
		return new Object[][] {{"Mexico City","Mexico City"}};
	}
	
}