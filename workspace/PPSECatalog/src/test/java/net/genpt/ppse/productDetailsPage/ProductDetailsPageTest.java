package net.genpt.ppse.productDetailsPage;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import net.genpt.ppse.helper.logger.LoggerHelper;
import net.genpt.ppse.pageObject.ProductDetailsPage;
import net.genpt.ppse.testBase.TestBase;

public class ProductDetailsPageTest extends TestBase{
	
	private final Logger log = LoggerHelper.getLogger(ProductDetailsPageTest.class);
	ProductDetailsPage productDetailsPage;
	
	@Test(priority = 9)
	public void testProductDetailsPage() throws InterruptedException {
		System.out.println("I am in testProductDetailsPage&&&&&&&&&&&&&&&&&&&&&&&");
		log.info(ProductDetailsPageTest.class.getName()+"*************Started");
		productDetailsPage = new ProductDetailsPage(driver);
		boolean status = productDetailsPage.verifyProductDetailsPage();
		status = false;
		if(status){
			log.info("Product Details page is shown successfully");	
		}
		else{
			Assert.assertTrue(false, "You Searched for section is not shown");
		}
		log.info(ProductDetailsPageTest.class.getName()+"*************Ended");
	}

}
