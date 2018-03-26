package net.genpt.ppse.helper.assertionHelper;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import net.genpt.ppse.helper.logger.LoggerHelper;

public class VerificationHelper {
	
	private static final Logger log = LoggerHelper.getLogger(VerificationHelper.class);
	
	public static synchronized boolean verifyElementPresent(WebElement element){
		
		boolean isDisplayed = false;
		try{
			isDisplayed = element.isDisplayed();
			log.info(element.getText()+" is displayed");
		}catch(Exception e){
			log.error("Element is not found "+e);
		}
		return isDisplayed;
	}
	
	public static synchronized boolean verifyElementNotPresent(WebElement element){
		
		boolean isDisplayed = false;
		try{
			isDisplayed = element.isDisplayed();
			log.info(element.getText()+" is displayed");
		}catch(Exception e){
			log.error("Element is not found "+e);
			isDisplayed = true;
		}
		return isDisplayed;
	}
	
	public static synchronized boolean verifyTextEquals(WebElement element, String expectedText){
		boolean flag = false;
		String actualText = element.getText();
		try{
			if(actualText.equals(expectedText)){
				log.info("actual text is: "+actualText+" expected text is: "+expectedText);
				return flag=true;
			}
			else{
				log.error("actual text is: "+actualText+" expected text is: "+expectedText);
				return flag;
			}
		}catch(Exception e){
			log.error("actual text is: "+actualText+" expected text is: "+expectedText);
			log.info("text not matching "+e);
			return flag;
		}
	}
}
