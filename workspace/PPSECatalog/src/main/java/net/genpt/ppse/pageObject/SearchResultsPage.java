package net.genpt.ppse.pageObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.google.common.collect.Ordering;

import net.genpt.ppse.helper.javascript.JavaScriptHelper;
import net.genpt.ppse.helper.logger.LoggerHelper;
import net.genpt.ppse.helper.wait.WaitHelper;
import net.genpt.ppse.testBase.Config;
import net.genpt.ppse.testBase.TestBase;

public class SearchResultsPage extends TestBase {
	
	static public WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(SearchResultsPage.class);
	WaitHelper waitHelper;
	
	@FindBy(xpath="//*[contains(text(),'Search Results:')]")
	WebElement searchResultsLabel;
	
	@FindBy(xpath="//div[@id='breadBox']/div")
	List<WebElement> You_Searched_For;
	
	@FindBy(xpath="//div[@id='divVehiclebreadcrumbsInfoBody']/ul/li")
	List<WebElement> selected_vehicle;
	
	@FindBy(xpath="//span[@class='partInfo']")
	List<WebElement> Part_Numbers;
	
	@FindBy(id="_cphBody_searchWithinBox")
	WebElement searchWithInTextBox;
	
	@FindBy(id="_cphBody__btnKeywordSearchWithin")
	WebElement searchWithinBtn;
	
	@FindBy(name="itemsPerPage_Top-txt")
	WebElement viewPerPage;
	
	@FindBy(id="sortList")
	WebElement sortList;
	
	@FindBy(xpath="//a[contains(@id, '_cphBody_resultList_ListingDetails1_ResultsListRepeater__anchorDescription')]")
	List<WebElement> Part_Descriptions;
	
	public SearchResultsPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(driver, searchResultsLabel, new Config(TestBase.OR).getExplicitWait());
	}
	
	public boolean yousearchedfor_VAK(String category, String subCategory, String partType) throws InterruptedException{
		boolean flag = false;
		ArrayList<String> yousearchforlist = new ArrayList<String>();
		
		for(int i=1; i<You_Searched_For.size(); i++){
			System.out.println(You_Searched_For.get(i).getText());
			yousearchforlist.add(You_Searched_For.get(i).getText().split(":")[1].trim());
		}
		System.out.println("yousearchforlist: "+yousearchforlist);
		if(yousearchforlist.contains(category) && yousearchforlist.contains(subCategory) && yousearchforlist.contains(partType))
			flag = true;
		You_Searched_For.get(1).click();
//		Thread.sleep(15000);
		return flag;
	}
	
	public boolean selected_vehicle_VAK(String year, String make, String model){
		boolean flag = false;
		ArrayList<String> selectedvehiclelist = new ArrayList<String>();
		
		for(int i=0; i<selected_vehicle.size(); i++){
			System.out.println(selected_vehicle.get(i).getText());
			selectedvehiclelist.add(selected_vehicle.get(i).getText().split(":")[1].trim());
		}
		System.out.println("selectedvehiclelist: "+selectedvehiclelist);
		if(selectedvehiclelist.contains(year) && selectedvehiclelist.contains(make) && selectedvehiclelist.contains(model))
			flag = true;
		return flag;
	}
	
	public boolean searchWithinResults() throws InterruptedException {
		boolean flag = false;
		String part_number = null;
		System.out.println(((Part_Numbers.get(0).getText()).split(":"))[1]);
		part_number = ((Part_Numbers.get(0).getText()).split(":"))[1];
		searchWithInTextBox.sendKeys(part_number);
		searchWithinBtn.click();
		Thread.sleep(3000);
		if(part_number.trim().equals((((Part_Numbers.get(0).getText()).split(":"))[1]).trim())){
			System.out.println("search within results shown successfully");
			flag = true;
		}
		return flag;
	}
	
	public boolean viewPerPage() throws InterruptedException {
		boolean flag = false;
		System.out.println(" I am in view per page");
		Thread.sleep(5000);
		viewPerPage.click();
		Thread.sleep(5000);
		driver.findElement(By.id("ui-id-14")).click();
		if(Part_Numbers.size()==10)
			flag = true;
		else
			flag = false;
		viewPerPage.click();
		Thread.sleep(5000);
		driver.findElement(By.id("ui-id-15")).click();
		if(Part_Numbers.size()==25)
			flag = true;
		else
			flag = false;
		viewPerPage.click();
		Thread.sleep(5000);
		driver.findElement(By.id("ui-id-16")).click();
		if(Part_Numbers.size()==50)
			flag = true;
		else
			flag = false;
		return flag;
    }
	
	public boolean verifySortByPartNumber() throws InterruptedException{
		
		boolean flag = false;
		driver.findElement(By.xpath("//input[@name='sortList-txt']")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("ui-id-15")).click();
		
		for(int i=0; i<=2; i++){
			List<WebElement> sortedAppPartnumbers = driver.findElements(By.xpath("//span[@class='partInfo']"));
			ArrayList<String> sortedPartnumbers = new ArrayList<String>();
			 try {
				 for(int j=0; j<sortedAppPartnumbers.size(); j++){
					 System.out.println(sortedAppPartnumbers.get(j).getText().split(":")[1].trim().split("  ")[1]);
					 sortedPartnumbers.add(sortedAppPartnumbers.get(j).getText().split(":")[1].trim().split("  ")[1]);
				  }
				 flag = Ordering.natural().isOrdered(sortedPartnumbers);
				 break;
			 }catch(Exception e){
				 System.out.println(e.getMessage());
			 }
		}
		return flag;
	}
	
	public boolean verifySortByLineAbbreviation() throws InterruptedException{
		
		boolean flag = false;
		driver.findElement(By.xpath("//input[@name='sortList-txt']")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("ui-id-16")).click();
		
		for(int i=0; i<=2; i++){
			List<WebElement> sortedAppLineAbbreviation = driver.findElements(By.xpath("//span[@class='partInfo']"));
			ArrayList<String> sortedLineAbbreviation = new ArrayList<String>();
			 try {
				 
				 for(int j=0; j<sortedAppLineAbbreviation.size(); j++){
					 System.out.println(sortedAppLineAbbreviation.get(j).getText().split(":")[1].trim().split("  ")[0]);
					 sortedLineAbbreviation.add(sortedAppLineAbbreviation.get(j).getText().split(":")[1].trim().split("  ")[0]);
				  }
				 flag = Ordering.natural().isOrdered(sortedLineAbbreviation);
				 break;
			 }catch(Exception e){
				 System.out.println(e.getMessage());
			 }
		}
		return flag;
	}
	
	public boolean verifySortByPartDescription() throws InterruptedException{
		
		boolean flag = false;
//		Thread.sleep(2000);
//		new JavaScriptHelper(driver).scrollDownVertically();
//		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@name='sortList-txt']")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("ui-id-17")).click();
		
		for(int i=0; i<=2; i++){
			List<WebElement> sortedAppPartDescription = driver.findElements(By.xpath("//a[contains(@id, '_cphBody_resultList_ListingDetails1_ResultsListRepeater__anchorDescription')]"));
			ArrayList<String> sortedPartDescription = new ArrayList<String>();
			 try {
				 
				 for(int j=0; j<sortedAppPartDescription.size(); j++){
					 System.out.println(sortedAppPartDescription.get(j).getText());
					 sortedPartDescription.add(sortedAppPartDescription.get(j).getText());
				  }
				 flag = Ordering.natural().isOrdered(sortedPartDescription);
				 break;
			 }catch(Exception e){
				 System.out.println(e.getMessage());
			 }
		}
		return flag;
	}
	
	public ProductDetailsPage clickOnProductDescription() throws InterruptedException {
		log.info("Clicking on Product Description");
//		Part_Descriptions.get(0).click();
		List<WebElement> partDescriptions = driver.findElements(By.xpath("//a[contains(@id, '_cphBody_resultList_ListingDetails1_ResultsListRepeater__anchorDescription')]"));
		System.out.println("###################Product Description: "+partDescriptions.get(0).getText());
		Thread.sleep(5000);
		partDescriptions.get(0).click();
		return new ProductDetailsPage(driver);
	}

}
