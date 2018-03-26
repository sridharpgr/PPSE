package net.genpt.ppse.loginPage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.google.common.collect.Ordering;

public class TestPPSE {
	
	public WebDriver driver;
	
	@Test
	public void ppseAutomation() throws InterruptedException{
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.get("http://ppse.genpt.net/(S(mioxw1rdtv2opyn24msoae1q))/SearchResult.aspx?N=+10201700+5000100+200010310&PartTypeSection=+2017+Acura+MDX+Brake&isPTListClk=false&itemsPerPage=10&PostBack=1");
//		List<WebElement> perpagecount = driver.findElements(By.xpath("//select[@id='itemsPerPage_Top']/option"));
//		Iterator<WebElement> itr = perpagecount.iterator();
//	    while (itr.hasNext()) {
//	    	WebElement c = itr.next();
//	        String text = c.getText().trim().toString();
//	        System.out.println(text);
//	    }    
//		Thread.sleep(5000);
//		driver.findElement(By.xpath("//input[@name='itemsPerPage_Top-txt']")).click();
//		Thread.sleep(5000);
//		driver.findElement(By.id("ui-id-14")).click();
//		Thread.sleep(5000);
//		driver.findElement(By.xpath("//input[@name='itemsPerPage_Top-txt']")).click();
//		Thread.sleep(5000);
//		driver.findElement(By.id("ui-id-15")).click();
//		Thread.sleep(5000);
//		driver.findElement(By.xpath("//input[@name='itemsPerPage_Top-txt']")).click();
//		Thread.sleep(5000);
//		driver.findElement(By.id("ui-id-16")).click();
//		Thread.sleep(5000);
//		Thread.sleep(2000);
//		List<WebElement> partnumbers = driver.findElements(By.xpath("//span[@class='partInfo']"));
//		System.out.println(partnumbers.get(0).getText());
		
//		
//		List<WebElement> beforeSortAppPartnumbers = driver.findElements(By.xpath("//span[@class='partInfo']"));
//		for(int j=0; j<beforeSortAppPartnumbers.size(); j++){
//			System.out.println(beforeSortAppPartnumbers.get(j).getText().split(":")[1].trim().split("  ")[1]);					
//	    }
		
		/*sort by number***************************
		driver.findElement(By.xpath("//input[@name='sortList-txt']")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("ui-id-15")).click();
		for(int i=0;i<=2;i++)	 
		{
			List<WebElement> sortedAppPartnumbers = driver.findElements(By.xpath("//span[@class='partInfo']"));
			ArrayList<String> sortedPartnumbers = new ArrayList<String>();
		  try{  
				
				for(int j=0; j<sortedAppPartnumbers.size(); j++){
					System.out.println(sortedAppPartnumbers.get(j).getText().split(":")[1].trim().split("  ")[1]);
					sortedPartnumbers.add(sortedAppPartnumbers.get(j).getText().split(":")[1].trim().split("  ")[1]);
			    }
				
				boolean isSorted = Ordering.natural().isOrdered(sortedPartnumbers);
				System.out.println(isSorted);
				
				for(int j=0; j<sortedPartnumbers.size(); j++){
					System.out.println(sortedPartnumbers.get(j));
			    }
		    break;
		}
		 
		catch(Exception e)
		{
		 
		System.out.println(e.getMessage());
		 
		}
		 
		}*sort by number***************************/
		
		
		/*sort by line abreau***************************
		driver.findElement(By.xpath("//input[@name='sortList-txt']")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("ui-id-16")).click();
		for(int i=0;i<=2;i++)	 
		{
			List<WebElement> sortedAppPartnumbers = driver.findElements(By.xpath("//span[@class='partInfo']"));
			ArrayList<String> sortedPartnumbers = new ArrayList<String>();
		  try{  
				
				for(int j=0; j<sortedAppPartnumbers.size(); j++){
					System.out.println(sortedAppPartnumbers.get(j).getText().split(":")[1].trim().split("  ")[0]);
					sortedPartnumbers.add(sortedAppPartnumbers.get(j).getText().split(":")[1].trim().split("  ")[0]);
			    }
				
				boolean isSorted = Ordering.natural().isOrdered(sortedPartnumbers);
				System.out.println(isSorted);
				
				for(int j=0; j<sortedPartnumbers.size(); j++){
					System.out.println(sortedPartnumbers.get(j));
			    }
				break;
		}
		 
		catch(Exception e)
		{
		 
		System.out.println(e.getMessage());
		 
		}
		 
		}*sort by line abreau***************************/
	
		
		driver.findElement(By.xpath("//input[@name='sortList-txt']")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("ui-id-17")).click();
		for(int i=0;i<=2;i++)	 
		{
			List<WebElement> sortedAppPartDescriptions = driver.findElements(By.xpath("//a[contains(@id, '_cphBody_resultList_ListingDetails1_ResultsListRepeater__anchorDescription')]"));
			ArrayList<String> sortedPartDescriptions = new ArrayList<String>();
		  try{  
				
				for(int j=0; j<sortedAppPartDescriptions.size(); j++){
					System.out.println(sortedAppPartDescriptions.get(j).getText());
					sortedPartDescriptions.add(sortedAppPartDescriptions.get(j).getText());
			    }
				
				boolean isSorted = Ordering.natural().isOrdered(sortedPartDescriptions);
				System.out.println(isSorted);
				
				for(int j=0; j<sortedPartDescriptions.size(); j++){
					System.out.println(sortedPartDescriptions.get(j));
			    }
				break;
		}
		 
		catch(Exception e)
		{
		 
		System.out.println(e.getMessage());
		 
		}
		 
		}
		Thread.sleep(5000);
		driver.quit();
		

		
	}

}
