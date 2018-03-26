package net.genpt.ppse.helper.alert;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import net.genpt.ppse.helper.logger.LoggerHelper;

public class AlertHelper {
	
	private WebDriver driver;
	private Logger oLog = LoggerHelper.getLogger(AlertHelper.class);
	
	public AlertHelper(WebDriver driver){
		this.driver = driver;
		oLog.debug("Alert Helper : "+this.driver.hashCode());
	}
	
	public Alert getAlert(){
		oLog.debug("");
		return driver.switchTo().alert();
	}
	
	public void acceptAlert(){
		oLog.info("");
		getAlert().accept();
	}
	
	public void dismissAlert(){
		oLog.info("");
		getAlert().dismiss();
	}
	
	public String getAlertText(){
		String text = getAlert().getText();
		oLog.info("text");
		return text;
	}
	
	public boolean isAlertPresent(){
		try{
			driver.switchTo().alert();
			oLog.info(true);
			return true;
		}catch(NoAlertPresentException e){
			oLog.info(false);
			return false;
		}
	}
	
	public void acceptAlertIfPresent(){
		if(!isAlertPresent())
			return;
		acceptAlert();
		oLog.info("");
	}
	
	public void dismissAlertIfPresent(){
		if(!isAlertPresent())
			return;
		dismissAlert();
		oLog.info("");
	}
	
	public void acceptPrompt(String text){
		if(!isAlertPresent())
			return;
		Alert alert = getAlert();
		alert.sendKeys(text);
		alert.accept();
		oLog.info(text);
	}
	
	
	

	
	
	
	
	
	
	
	
}

