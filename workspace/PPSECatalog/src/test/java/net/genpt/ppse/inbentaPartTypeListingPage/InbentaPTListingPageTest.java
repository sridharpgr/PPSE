package net.genpt.ppse.inbentaPartTypeListingPage;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import net.genpt.ppse.helper.logger.LoggerHelper;
import net.genpt.ppse.pageObject.InbentaPTListingPage;
import net.genpt.ppse.testBase.Config;
import net.genpt.ppse.testBase.TestBase;

public class InbentaPTListingPageTest extends TestBase{
	
	private final Logger log = LoggerHelper.getLogger(InbentaPTListingPageTest.class);
	InbentaPTListingPage inbentaPTListingPage;
	
	@DataProvider(name="testData")
	public Object[][] dataSource(){
		return getData("TestData.xlsx", "VAK");
	}
	
	@Test(dataProvider="testData", priority = 2)
	public void verifyInbentaPTListingPage(String year, String make, String model, String category, String subcategory, String parttype) throws InterruptedException {
		log.info(InbentaPTListingPageTest.class.getName()+"*************Started");
		Config config = new Config(TestBase.OR);
		inbentaPTListingPage = new InbentaPTListingPage(driver);
		boolean status = inbentaPTListingPage.verifySelectedVehicle(year, make, model);
		if(status){
			log.info("Selected Vehicle is shown successfully");	
		}
		else{
			Assert.assertTrue(false, "Selected Vehicle is not shown");
		}
		inbentaPTListingPage.clickOnPartType(parttype);
		
		log.info(InbentaPTListingPageTest.class.getName()+"*************Ended");
	}
}
