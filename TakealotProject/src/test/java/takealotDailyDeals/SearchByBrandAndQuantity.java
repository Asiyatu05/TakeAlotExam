package takealotDailyDeals;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import frameWorkClasses.BasePage;

public class SearchByBrandAndQuantity extends BasePage{
	
	public void clickOnCookie() {
		clickElement(By.cssSelector("button[class='button cookies-banner-module_dismiss-button_24Z98']"));
	}
	
	
	//Clicking on Search tab
	public void clickSearch() {

		clickElement(By.name("search"));
	}
	//Searching for Brand & Quantity
	public void enterBrandName(String BrandName) {
 		
		 EnterText(By.name("search"),BrandName);
		 
	}
	//Clicking on search button
	public void clickOnSearch() {
 		
		
		 
		 clickElement(By.xpath("/html/body/div[1]/header/div/div/div[2]/form/div/div[3]/button"));
	}
	
	//Checking number of items
	public String numberOfItems() {

		return getElementText(By.xpath("//div[@class='search-count toolbar-module_search-count_P0ViI search-count-module_search-count_1oyVQ']"));

	}
//Selecting the first item
	public void selectOneItem() {

		clickElement(By.xpath("//div[1]/div[4]/div/div[2]/div[2]/div/div[3]/div/div/a"));

		
	}
//Fetching Item price
	public String itemPrice() {

		driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			driver.switchTo().window(handle);
		}

		return getElementText(By.xpath("//div[4]/div[1]/div[2]/aside/div[1]/div[1]/div[1]/span[@class='currency plus currency-module_currency_29IIm']"));


	}
   //Adding items to cart
	public void addToCart() {

		driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			driver.switchTo().window(handle);

		}
		clickElement(By.xpath("//div/div/a[@class='button  expanded add-to-cart-button add-to-cart-button-module_add-to-cart-button_1a9gT']"));
		driver.switchTo().activeElement();
		waitForElement(100,By.xpath("//div/div/div/div/div[@class='grid-x drawer-screen-module_drawer-header_3xmzS']"));


	}
	//Asserting that item was added to cart
	public String addedToCart() {

		return getElementText(By.xpath("//div/div/div/div/div[@class='grid-x drawer-screen-module_drawer-header_3xmzS']"));

	}
	//Clicking on goToCart
	public void goToCart() {


		waitForElement(100,By.xpath("//div/div/div/div/div[@class='grid-x drawer-screen-module_drawer-header_3xmzS']"));
		clickElement(By.xpath("//div/button[@class='button checkout-now dark']"));


	}
	//Selecting a quantity
	public String selectQuantity(String quantity) {

		selectDropDown(By.id("cart-item_undefined"), quantity);
		return quantity;
        
	
	}
	
	//Asserting that correct cart value is displayed
	public String assertCartValue() {
		
		waitForElement(100, By.xpath("//div[4]/div[2]/section/div[2]/div[2]/div/div[1]/div[1]/div/div[1]/div/div[2]/p/span[@class='currency plus currency-module_currency_29IIm']"));
		
		

		return getElementText(By.xpath("//div[4]/div[2]/section/div[2]/div[2]/div/div[1]/div[1]/div/div[1]/div/div[2]/p/span[@class='currency plus currency-module_currency_29IIm']"));

	}
	
	public void goHome() {

		clickElement(By.xpath("//a/img[@class='top-nav-module_logo_R1oac']"));
	}
	
	public void clearSearch() {

		WebElement clearText = getElement(By.name("search"));
		clearText.clear();
		
	}

	

}
