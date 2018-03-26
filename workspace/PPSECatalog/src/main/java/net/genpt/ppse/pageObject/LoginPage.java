package net.genpt.ppse.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.genpt.ppse.helper.dropdown.DropDownHelper;
import net.genpt.ppse.helper.genericHelper.GenericHelper;
import net.genpt.ppse.helper.logger.LoggerHelper;
import net.genpt.ppse.helper.wait.WaitHelper;
import net.genpt.ppse.testBase.Config;
import net.genpt.ppse.testBase.TestBase;

public class LoginPage extends TestBase{
	
	static public WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);
	WaitHelper waitHelper;
	
	
	@FindBy(id="napaStoreNumber")
	WebElement storeNumber;
	
	@FindBy(id="servingDCCode")
	WebElement servingDCCode;
	
	@FindBy(id="customerNumber")
	WebElement customerNumber;
	
	@FindBy(id="tamsVersion")
	WebElement tamsVersion;
	
	@FindBy(id="SubmitForm")
	WebElement submitLoginBtn;
	
	@FindBy(id="_hypVehicleApplication")
	WebElement vehicleApplicationTab;
	
	
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(driver, submitLoginBtn, new Config(TestBase.OR).getExplicitWait());
	}
	
	public void enterStoreNumber(String storeNumber) {
		log.info("entering storeNumber: "+storeNumber);
		this.storeNumber.clear();
		this.storeNumber.sendKeys(storeNumber);
	}
	
	public void selectDCCode(String servingDCCode) {
		log.info("Selecting servingDCCode: "+servingDCCode);
		DropDownHelper dd = new DropDownHelper(driver);
		dd.selectVisibleValue(this.servingDCCode, servingDCCode);
	}
	
	public void enterCustomerNumber(String customerNumber) {
		log.info("Entering customerNumber: "+customerNumber);
		this.customerNumber.clear();
		this.customerNumber.sendKeys(customerNumber);
	}
	
	public void selectTamsVersion(String tamsVersion) {
		log.info("Selecting tamsVersion: "+tamsVersion);
		DropDownHelper dd = new DropDownHelper(driver);
		dd.selectVisibleValue(this.tamsVersion, tamsVersion);
	}
	
	public HomePage clickOnSubmitLoginBtn() {
		log.info("Clicking on submitLoginBtn");
		submitLoginBtn.click();
		return new HomePage(driver);
	}
	
	public void loginToApplication(String storeNumber, String servingDCCode, String customerNumber, String tamsVersion) {
		enterStoreNumber(storeNumber);
		selectDCCode(servingDCCode);
		enterCustomerNumber(customerNumber);
		selectTamsVersion(tamsVersion);
		clickOnSubmitLoginBtn();
	}
	
	public boolean verifyLogin(){
		return new GenericHelper().isDisplayed(vehicleApplicationTab);
	}
	
	
}
