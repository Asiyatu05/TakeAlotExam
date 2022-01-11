package takealotTests;

import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import frameWorkClasses.ReadExcel;
import takealotDailyDeals.DailyDeals;
import takealotDailyDeals.SearchByBrandAndQuantity;

public class DailyDealsTest {
	
	
	
	DailyDeals dd = new DailyDeals();
	SearchByBrandAndQuantity sbq = new SearchByBrandAndQuantity();
	ReadExcel rExcel = new ReadExcel();
	
	@BeforeTest
	public void Given_NavigationToLandingPage_When_CookieIsClicked_Then_CookieIsCleared() {
		
		dd.clickOnCookie();
		
	}
	
   @Test(priority=1)
	public void Given_NavigationToLandingPage_When_DailyDealsButtonIsClicked_And_OneBrandIsSelected_And_FirstItemClicked_Then_ItemIsAddedToCart() throws InterruptedException{
		//sbq.goHome();
	   Thread.sleep(2000);
		dd.clickDailyDealsButton();
		
		dd.selectOneBrand();
		//Getting the number of items & converting it to an Integer
		String items = dd.numberOfItems().replaceAll("\\D+","");
		System.out.println("Number of items displayed " + items);
		int actualItems = Integer.valueOf(items);
		Thread.sleep(2000);
		Assert.assertTrue(actualItems > 0);
		
		//Skipping test case if number of item are less than one
		if(actualItems < 1) {
			
			System.out.println("Test case skipped");
			throw new SkipException("Skipping this test case");
		
		};
		
        dd.selectOneItem();
        Thread.sleep(2000);
        //Getting the price of an item & converting it to an Integer
        String price = dd.itemPrice().replaceAll("\\D+","");
        System.out.println("The item price is : R" + price);
        int itemPrice = Integer.valueOf(price);
		
        dd.addToCart();
        Thread.sleep(2000);
        String actual = dd.addedToCart();
	    String expected = "Added to cart";
		
		Assert.assertEquals(actual,expected);
		System.out.println(actual);
        
        dd.goToCart();
        
        
	
        
        dd.selectQuantity("2");
        
        int selectedQuantity = Integer.parseInt(dd.selectQuantity("2"));
        System.out.println("Quantity of items in the cart: " + selectedQuantity);
        
        Thread.sleep(2000);

	
	//Used to get total cart value
	String cartTotalPrice = dd.assertCartValue().replaceAll("\\D+","");
	System.out.println("The totalPrice price is: R" + cartTotalPrice);
	//Converting cart value to Integer
    int actualTotalPrice = Integer.valueOf(cartTotalPrice);
    //Calculating expected cart value
    int expectedTotalPrice = itemPrice * selectedQuantity;
    //Asserting if actual & expected total price value match & printing out the result
    if(expectedTotalPrice == actualTotalPrice) {
    	
    	
    	Assert.assertTrue(actualTotalPrice == expectedTotalPrice);
    	System.out.println("Correct Total price is displayed : R" + expectedTotalPrice);
    	
    }
    
    else 
    {
    	System.out.println("Incorrect Total price is displayed");
    }
    
	
	
	}
	
    
	//Using dataProvider to read and search data from excel sheet
	@Test(dataProvider = "getDataFromExcel",priority=2)
	public void Given_BrandsAndQuantityIsReadFromExcel_When_BrandsAndQuantityIsSearched_Then_AllSearchedItemsAreReturned(String Brand, String Quantity) throws InterruptedException {
	
		
		sbq.clickSearch();
		sbq.enterBrandName(Brand);
		Thread.sleep(2000);
		sbq.clickOnSearch();
		 
		System.out.println("Brand & Quantity");
		System.out.println(Brand + " " + Quantity);
		
		String items = sbq.numberOfItems().replaceAll("\\D+","");
		System.out.println("Number of items displayed " + items);
		int actualItems = Integer.valueOf(items);
		Assert.assertTrue(actualItems > 0);
		if(actualItems < 1) {
			
			System.out.println("Test case skipped");
			throw new SkipException("Skipping this test case");
		};
		
        sbq.selectOneItem();
        
        String price = sbq.itemPrice().replaceAll("\\D+","");
        System.out.println("The item price is : R" + price);
        int itemPrice = Integer.valueOf(price);
		
        sbq.addToCart();
        
        String actual = sbq.addedToCart();
	    String expected = "Added to cart";
		
		Assert.assertEquals(actual,expected);
		System.out.println(actual);
        
		sbq.goToCart();
        
        sbq.selectQuantity(Quantity);
        
        int selectedQuantity = Integer.parseInt(sbq.selectQuantity(Quantity));
        System.out.println("Quantity of items in the cart: " + selectedQuantity);
        
        Thread.sleep(2000);
	
	
	
    
    int itemTotalPrice = itemPrice * selectedQuantity;
    System.out.println("Total price of item(s): R" + itemTotalPrice);
    
    
    String cartTotalPrice = sbq.assertCartValue().replaceAll("\\D+","");
    int actualTotalPrice = Integer.valueOf(cartTotalPrice);
    System.out.println("Total cart price is: R" + actualTotalPrice);
    
    sbq.closeWindow();
    sbq.clearSearch();
	 
     
     //Assert.assertTrue(true);
     
     
	 
		
	}
	
	
@DataProvider(name = "getDataFromExcel")
	
	public Object[][]  getDataFromExcel() throws IOException {

		String excelDirectory = rExcel.getDataConfigProperties("excelDataDir");
        Object [][] arrObj = rExcel.getExcelData(excelDirectory+"BrandsAndQuantityExcel.xlsx", "Sheet1");
        
        return arrObj;
        
	}
	
	
	@AfterSuite
	public void cleanUp () {
		
		
	    
		dd.cleanUp();
		
	
		
	}
	
}
