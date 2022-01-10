package takealotDailyDeals;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import frameWorkClasses.BasePage;

public class DailyDeals extends BasePage {

	//Handling the cookie
	public void clickOnCookie() {
		clickElement(By.cssSelector("button[class='button cookies-banner-module_dismiss-button_24Z98']"));
	}
//Clicking on the daily deals button
	public void clickDailyDealsButton() {

		clickElement(By.cssSelector("img[alt='Daily Deals Flyout x2.png']"));
	}
//Selecting a brand
	public void selectOneBrand() {

		clickElement(By.xpath("//div/div/ul/div/div/li[2]/label"));

	}
//This is to get the number of items displayed
	public String numberOfItems() {

		return getElementText(By.xpath("//div[@class='search-count toolbar-module_search-count_P0ViI search-count-module_search-count_1oyVQ']"));

	}
//This is to select one item
	public void selectOneItem() {

		clickElement(By.xpath("//div[1]/div[6]/div/div[2]/div[2]/div/div[1]/div/div/a"));

	}
//Window handling and getting the item price.
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
		
		driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			driver.switchTo().window(handle);
		}
		

		return getElementText(By.xpath("//div[4]/div[2]/section/div[2]/div[2]/div/div[1]/div[1]/div/div[1]/div/div[2]/p/span[@class='currency plus currency-module_currency_29IIm']"));

	}

	

}
