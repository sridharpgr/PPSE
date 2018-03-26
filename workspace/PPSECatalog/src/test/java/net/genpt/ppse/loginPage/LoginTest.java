package net.genpt.ppse.loginPage;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import net.genpt.ppse.helper.logger.LoggerHelper;
import net.genpt.ppse.pageObject.LoginPage;
import net.genpt.ppse.testBase.Config;
import net.genpt.ppse.testBase.TestBase;

public class LoginTest extends TestBase{
	
	private final Logger log = LoggerHelper.getLogger(LoginTest.class);
	
	@Test(description = "Login Application Verification")
	public void testLoginToApplication() {
		log.info(LoginTest.class.getName()+" ************Started");
		Config config = new Config(TestBase.OR);
		driver.get(config.getURL());
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApplication(config.getStoreNumber(), config.getDCCode(), config.getCustomerNumber(), config.getTamsVersion());
		boolean status = loginPage.verifyLogin();
		if(status){
			log.info("login is sucessful");	
		}
		else{
			Assert.assertTrue(false, "login is not sucessful");
		}
		log.info(LoginTest.class.getName()+" ************Ended");
	}

}
