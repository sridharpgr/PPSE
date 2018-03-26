package net.genpt.ppse.searchResultsPage;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import net.genpt.ppse.helper.logger.LoggerHelper;
import net.genpt.ppse.pageObject.SearchResultsPage;
import net.genpt.ppse.testBase.TestBase;

public class SearchResultsPageTest extends TestBase{
	
	private final Logger log = LoggerHelper.getLogger(SearchResultsPageTest.class);
	SearchResultsPage searchResultsPage;
	
	@DataProvider(name="testData")
	public Object[][] dataSource(){
		return getData("TestData.xlsx", "VAK");
	}
	
	@Test(dataProvider="testData", priority = 3)
	public void verifySearchResultsPage(String year, String make, String model, String category, String subcategory, String parttype) throws InterruptedException {
		System.out.println("I am in verifySearchResultsPage");
		
		log.info(SearchResultsPageTest.class.getName()+"*************Started");
		searchResultsPage = new SearchResultsPage(driver);
		boolean status1 = searchResultsPage.yousearchedfor_VAK(category, subcategory, parttype);
		System.out.println(status1);
		if(status1){
			log.info("You Searched for section is shown successfully");	
		}
		else{
			Assert.assertTrue(false, "You Searched for section is not shown");
		}
		boolean status2 = searchResultsPage.selected_vehicle_VAK(year, make, model);
		if(status2){
			log.info("selected vehicle is shown successfully");	
		}
		else{
			Assert.assertTrue(false, "selected vehicle is not shown");
		}
		
		log.info(SearchResultsPageTest.class.getName()+"*************Ended");
	}
	
	@Test(priority = 4)
	public void testSortByPartNumber() throws InterruptedException {
		System.out.println("I am in testSortByPartNumber");
		searchResultsPage = new SearchResultsPage(driver);
		boolean status = searchResultsPage.verifySortByPartNumber();
		System.out.println(status);
		if(status){
			log.info("part numbers are sorted successfully");	
		}
		else{
			Assert.assertTrue(false, "part numbers are not sorted");
		}
	}
	
	@Test(priority = 5)
	public void testSortByLineAbbreviation() throws InterruptedException {
		System.out.println("I am in testSortByLineAbbreviation");
		searchResultsPage = new SearchResultsPage(driver);
		boolean status = searchResultsPage.verifySortByLineAbbreviation();
		System.out.println(status);
		if(status){
			log.info("LineAbbreviations are sorted successfully");	
		}
		else{
			Assert.assertTrue(false, "LineAbbreviation are not sorted");
		}
	}
	
	@Test(priority = 6)
	public void testSortByPartDescription() throws InterruptedException {
		System.out.println("I am in testSortByPartDescription");
		searchResultsPage = new SearchResultsPage(driver);
		boolean status = searchResultsPage.verifySortByPartDescription();
		System.out.println(status);
		if(status){
			log.info("PartDescriptions are sorted successfully");	
		}
		else{
			Assert.assertTrue(false, "PartDescriptions are not sorted");
		}
	}
	
	@Test(priority = 7)
	public void verifyViewPerPage() throws InterruptedException{
		System.out.println("I am in verifyViewPerPage");
		searchResultsPage = new SearchResultsPage(driver);
		boolean status = searchResultsPage.viewPerPage();
		System.out.println(status);
		if(status){
			log.info("verifyViewPerPage is shown successfully");	
		}
		else{
			Assert.assertTrue(false, "verifyViewPerPage is not shown");
		}
	}
	
	@Test(priority = 8)
	public void verify_Search_Within() throws InterruptedException{
		searchResultsPage = new SearchResultsPage(driver);
		boolean status = searchResultsPage.searchWithinResults();
		System.out.println(status);
		if(status){
			log.info("Search within these result is shown successfully");	
		}
		else{
			Assert.assertTrue(false, "Search within these result is not shown");
		}
		searchResultsPage.clickOnProductDescription();
	}

}
