package frameWorkClasses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	    // Declare Webdriver
		public static WebDriver driver;
		
		// Constructor of the base class
		//Constructor is a special method, used to initialize objects, a constructor is called when an object of a class is created
		public BasePage() {
			
			if (driver == null)
			{
				//Get parameter values
				
				String browser = getDataConfigProperties("browser");
				String systemUnderTest = getDataConfigProperties("systemUnderTest");
				String pdriverDir = getDataConfigProperties("driverdir");
				//String pdriverDirFireFox = getDataConfigProperties("driverdirfirefox");
				
				//Check if parameter passed as "chrome"
				if(browser.equalsIgnoreCase("chrome"))
				{
					//set path to chromedriver.exe
					System.setProperty("webdriver.chrome.driver", pdriverDir+"chromedriver");
							//create an instance of chrome
					driver = new ChromeDriver();
					driver.get(systemUnderTest);
					driver.manage().window().maximize();;
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				}
				//Check if parameter passed as "firefox"
				else if(browser.equalsIgnoreCase("firefox"))
				{
					//set path to firefoxdriver.exe
					System.setProperty("webdriver.gecko.driver", pdriverDir+"geckodriver");
							//create an instance of firefox
					driver = new FirefoxDriver();
					driver.get(systemUnderTest);
					driver.manage().window().maximize();;
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				}
				
				else if(browser.equalsIgnoreCase("Edge"))
				{
					//set path to edgeriver.exe
					System.setProperty("webdriver.edge.driver", pdriverDir+"msedgedriver");
							//create an instance of edge
					driver = new EdgeDriver();
					driver.get(systemUnderTest);
					driver.manage().window().maximize();;
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				}
				
			}
			
		}	
		
		//Create a method to read the config
		public String getDataConfigProperties (String propertyName) {
			//Properties setup
			Properties p = new Properties();
			InputStream is  = null;
			
			try {
				is = new FileInputStream("config.properties");
			} catch (FileNotFoundException e) {
			  e.printStackTrace();
			
			} 
			try {
				p.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return p.getProperty(propertyName);
		}
		
		
		    
		    //create a method to Wait for Element
		public void waitForElement (int elementWait, By pLocator) {
			WebDriverWait wait = new WebDriverWait(driver, elementWait);
			wait.until(ExpectedConditions.visibilityOfElementLocated(pLocator));
		}
		    
		    //create a method to wait for the URL
		public void waitForUrl (int elementWait, String pLocator) {
			WebDriverWait wait = new WebDriverWait(driver, elementWait);
			wait.until(ExpectedConditions.urlContains(pLocator));
		}
		    
		    //create a method to Wait for Click
		public void waitForClick(int elementWait, By pLocator) {
			WebDriverWait wait = new WebDriverWait(driver, elementWait);
			wait.until(ExpectedConditions.elementToBeClickable(pLocator));
		}
		
	   
		    //create a method to get Element Text
		public String getElementText (By pLocator) {
			waitForClick(1000,pLocator);
			String h1 = getElement(pLocator).getText();
			return h1;
		}
		    
		    //create a method to click Element
		public void clickElement(By pLocator) {
			waitForClick(1000,pLocator);
			getElement(pLocator).click();
		}
		 
		    
		    //create a method to get Element
		public WebElement getElement (By pLocator) {
			waitForClick (1000,pLocator);
			return driver.findElement(pLocator);
		}
		    
		    //clean up (close the browser)
		    public void cleanUp() {
		    	driver.close();
		    }
		    //create a method to EnterText
		    public void EnterText (By pLocator, String pText) {
		    	waitForClick (10,pLocator);
				driver.findElement(pLocator).sendKeys(pText);
		    }
		    
		    //create a method to select the drop down
		    public void selectDropDown (By pLocator, String pValue) {
		    	//Creates an instance of the dropdown object
		    	Select sDrpDown = new Select (getElement(pLocator));
		    	//Populates the dropdown
		    	sDrpDown.selectByVisibleText(pValue);}
		    	
		    	public void closeWindow () {
		    		
		    		 
		    		// This will return the number of windows opened by Webdriver and will return Set of St//rings
		    		Set<String> handles = driver.getWindowHandles();
		    		 
		    		// Now we will iterate using Iterator
		    	    java.util.Iterator<String> I1 =  handles.iterator();
		    		 
		   
		    		   String child_window=I1.next();
		    		   String parent_window=I1.next();
		   
		    		driver.switchTo().window(child_window);
		    		driver.close();
		    		driver.switchTo().window(parent_window);
		    		
		    	
		    		
		    	}

}
