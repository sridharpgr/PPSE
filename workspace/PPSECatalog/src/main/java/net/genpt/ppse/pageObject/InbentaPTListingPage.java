package net.genpt.ppse.pageObject;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.genpt.ppse.helper.logger.LoggerHelper;
import net.genpt.ppse.helper.wait.WaitHelper;
import net.genpt.ppse.testBase.Config;
import net.genpt.ppse.testBase.TestBase;

public class InbentaPTListingPage extends TestBase {
	
	static public WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(InbentaPTListingPage.class);
	WaitHelper waitHelper;
	
	@FindBy(xpath="//span[@class='UnivPTFilter']")
	List<WebElement> inbenta_selected_vehicle;
	
	@FindBy(id="btnUnivNotlooking")
	WebElement lookupoldway;
	
	public InbentaPTListingPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(driver, lookupoldway, new Config(TestBase.OR).getExplicitWait());
	}
	
	public boolean verifySelectedVehicle(String year, String make, String model){
		System.out.println("I am in verifySelectedVehicle in Inbenta%%%%%%%%%%%%%%%%%%%%");
		boolean flag = false;
		 ArrayList<String> ymm = new ArrayList<String>();
		for(int i=0; i<inbenta_selected_vehicle.size(); i++){
			System.out.println(inbenta_selected_vehicle.get(i).getText());
			ymm.add(inbenta_selected_vehicle.get(i).getText().split(":")[1].trim());
		}
		if(ymm.contains(year) && ymm.contains(make) && ymm.contains(model))
			flag = true;
		return flag;
	}
	
	public void clickOnPartType(String partType) throws InterruptedException{
		System.out.println("I am in clickOnPartType: "+partType);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='UnivPTdiv UnivPTNotDisable']//*[contains(text(),'"+partType+"')]")).click();
	}

}
