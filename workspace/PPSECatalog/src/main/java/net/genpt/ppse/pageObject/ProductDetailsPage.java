package net.genpt.ppse.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.genpt.ppse.helper.genericHelper.GenericHelper;
import net.genpt.ppse.helper.logger.LoggerHelper;
import net.genpt.ppse.helper.wait.WaitHelper;
import net.genpt.ppse.testBase.Config;
import net.genpt.ppse.testBase.TestBase;

public class ProductDetailsPage extends TestBase {
	
	public static WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(ProductDetailsPage.class);
	WaitHelper waitHelper;
	
	@FindBy(id="ui-id-1")
	WebElement productDetailsTab;
	
	@FindBy(id="ui-id-2")
	WebElement buyersGuideTab;
	
	@FindBy(id="ui-id-3")
	WebElement techAssistanceTab;
	
	@FindBy(id="_cphBody_ctl00__btnBackResults")
	WebElement backToResults;
	
	public ProductDetailsPage(WebDriver driver){
		System.out.println("I am in ProductDetailsPage^^^^^^^^^^^^^^^^^^^^^^");
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(driver, productDetailsTab, new Config(TestBase.OR).getExplicitWait());
	}
	
	public boolean verifyProductDetailsPage() throws InterruptedException{
		boolean flag = false;
		flag = new GenericHelper().isDisplayed(productDetailsTab);
//		backToResults.click();
		Thread.sleep(4000);
		return flag;
	}

}
