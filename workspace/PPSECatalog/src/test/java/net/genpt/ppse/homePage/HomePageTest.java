package net.genpt.ppse.homePage;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.sikuli.script.FindFailed;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import net.genpt.ppse.helper.logger.LoggerHelper;
import net.genpt.ppse.pageObject.HomePage;
import net.genpt.ppse.pageObject.InbentaPTListingPage;
import net.genpt.ppse.pageObject.SearchResultsPage;
import net.genpt.ppse.testBase.TestBase;

public class HomePageTest extends TestBase{
	
	private final Logger log = LoggerHelper.getLogger(HomePageTest.class);
	HomePage homePage;
	InbentaPTListingPage inbentaPTListingPage;
	SearchResultsPage searchResultsPage;
//	LoginToTAMSII lt;
	
	@DataProvider(name="testData")
	public Object[][] dataSource(){
		return getData("TestData.xlsx", "VAK");
	}
	
	@Test(dataProvider="testData", priority = 1)
	public void performVAKSearch(String year, String make, String model, String category, String subcategory, String parttype) throws InterruptedException, FindFailed, UnsupportedFlavorException, IOException {
		
		log.info(HomePageTest.class.getName()+"*************Started");		
		driver.get(url);
		homePage = new HomePage(driver);
		homePage.clickOnVehicleApplicationTab();
		homePage.selectYMM(year, make, model);
		log.info(HomePageTest.class.getName()+"*************Ended");
		
	}	

}
