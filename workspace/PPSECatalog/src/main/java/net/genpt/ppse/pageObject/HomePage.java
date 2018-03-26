package net.genpt.ppse.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.genpt.ppse.helper.logger.LoggerHelper;
import net.genpt.ppse.helper.wait.WaitHelper;
import net.genpt.ppse.testBase.Config;
import net.genpt.ppse.testBase.TestBase;

public class HomePage extends TestBase{
	
	static public WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(HomePage.class);
	WaitHelper waitHelper;
	
	@FindBy(id="_hypVehicleApplication")
	WebElement vehicleApplicationTab;
	
	@FindBy(id="ui-id-10")
	WebElement yearList;
	
	@FindBy(id="ui-id-11")
	WebElement makeList;
	
	@FindBy(id="ui-id-12")
	WebElement modelList;
	
	@FindBy(xpath="//input[@name='enterYear-txt']")
	WebElement year;
	
	@FindBy(xpath="//input[@name='enterMake-txt']")
	WebElement make;
	
	@FindBy(xpath="//input[@name='enterModel-txt']")
	WebElement model;
	
	@FindBy(id="_txtKeyword")
	WebElement searchTextBox;
	
	@FindBy(id="_cphBody__ucTabbedSearchBox__btnKeywordSearch")
	WebElement searchBtn;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(driver, vehicleApplicationTab, new Config(TestBase.OR).getExplicitWait());
	}
	
	public void clickOnVehicleApplicationTab(){
		log.info("clicking on VehicleApplicationTab");
		vehicleApplicationTab.click();
	}
	
	public void selectYMM(String year, String make, String model) throws InterruptedException{
		System.out.println("i am into YMM");
		waitHelper.waitForElement(driver, yearList, new Config(TestBase.OR).getExplicitWait());
		this.year.sendKeys(year);
		this.year.sendKeys(Keys.TAB);
		waitHelper.waitForElement(driver, makeList, new Config(TestBase.OR).getExplicitWait());
		this.make.sendKeys(make);
		this.make.sendKeys(Keys.TAB);
		waitHelper.waitForElement(driver, modelList, new Config(TestBase.OR).getExplicitWait());
		this.model.sendKeys(model);
		this.model.sendKeys(Keys.TAB);
		Thread.sleep(3000);
		searchTextBox.sendKeys("Brake");
		searchBtn.click();
	}
	
	

}
