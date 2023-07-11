package commonFunctions;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FunctionLibrary {
	public static WebDriver driver;
	public static String Expecteddata ="";
	public static String ActualData ="";
	//method for launching browser
	public static WebDriver startBrowser() throws Throwable
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}
	//method for launch url
	public static void openUrl(WebDriver driver,String url)
	{
		driver.get(url);
	}
	//method for wait for element
	public static void waitForElement(WebDriver driver,String LocatorType,String LocatorValue,String wait)
	{
		WebDriverWait myWait = new WebDriverWait(driver, Integer.parseInt(wait));
		if(LocatorType.equalsIgnoreCase("name"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(LocatorValue)));
		}
		else if(LocatorType.equalsIgnoreCase("xpath"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));

		}
		else if(LocatorType.equalsIgnoreCase("id"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorValue)));
		}
	}
	//method for textboxes
	public static void typeAction(WebDriver driver,String LocatorType,String LocatorValue,String TestData)
	{
		if(LocatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(LocatorValue)).clear();
			driver.findElement(By.name(LocatorValue)).sendKeys(TestData);
		}
		else if(LocatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(LocatorValue)).clear();
			driver.findElement(By.xpath(LocatorValue)).sendKeys(TestData);
		}
		else if(LocatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(LocatorValue)).clear();
			driver.findElement(By.id(LocatorValue)).sendKeys(TestData);
		}
	}
	//method for buttons,checkbox,links,images,radiobutton
	public static void clickAction(WebDriver driver,String LocatorType,String LocatorValue)
	{
		if(LocatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(LocatorValue)).click();
		}
		else if(LocatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(LocatorValue)).click();
		}
		else if(LocatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(LocatorValue)).sendKeys(Keys.ENTER);
		}
	}
	
	//method for close browser
	public static void closeBrowser(WebDriver driver)
	{
		driver.quit();
	}
	//method for listboxes
	public static void selectDropDown(WebDriver driver,String LocatorType,String LocatorValue,String TestData)
	{
		if(LocatorType.equalsIgnoreCase("xpath"))
		{
			int value = Integer.parseInt(TestData);
			WebElement element = driver.findElement(By.xpath(LocatorValue));
			Select select = new Select(element);
			select.selectByIndex(value);
			
		}
		if(LocatorType.equalsIgnoreCase("id"))
		{
			int value = Integer.parseInt(TestData);
			WebElement element = driver.findElement(By.id(LocatorValue));
			Select select = new Select(element);
			select.selectByIndex(value);
			
		}
	}
	//method for capturestockitem number
	public static void capturestockitem(WebDriver driver,String LocatorType,String Locatorvalue)
	{
		Expecteddata =driver.findElement(By.name(Locatorvalue)).getAttribute("value");
	}
	
	
		//method capture supplier number
	public static void capureData(WebDriver driver,String Locator_Type,String Locator_Value)
	{
		Expecteddata= driver.findElement(By.name(Locator_Value)).getAttribute("value");
	}
	//method for supplier table
	public static void supplierTable(WebDriver driver) throws Throwable
	{
		if(!driver.findElement(By.xpath("//input[@id='psearch']")).isDisplayed())
			driver.findElement(By.xpath("//span[@data-caption='Search']")).click();
		driver.findElement(By.xpath("//input[@id='psearch']")).sendKeys(Expecteddata);
		driver.findElement(By.xpath("//button[@id='btnsubmit']")).click();
		Thread.sleep(4000);
		ActualData = driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr[1]/td[6]/div/span/span")).getText();
		System.out.println(Expecteddata+"     "+ActualData);
		Assert.assertEquals(Expecteddata, ActualData,"Supplier Number Not Matching");
		
	}
}
