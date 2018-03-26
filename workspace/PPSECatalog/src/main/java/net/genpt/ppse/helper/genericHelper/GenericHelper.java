package net.genpt.ppse.helper.genericHelper;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import net.genpt.ppse.helper.logger.LoggerHelper;

public class GenericHelper {
	
	private static final Logger log = LoggerHelper.getLogger(GenericHelper.class);
	
	public String readValueFromElement(WebElement element){
		
		if(null == element){
			log.info("Webelement is null");
			return null;
		}
		
		boolean displayed = false;
		try{
			displayed = isDisplayed(element);
		}catch(Exception e){
			log.error(e);
			Reporter.log(e.fillInStackTrace().toString());
			return null;
		}
		
		if(!displayed){
			return null;
		}
		
		String text = element.getText();
		log.info("webelement value is: "+text);
		return text;
		
	}
	
	public boolean isDisplayed(WebElement element){
		
		try{
			element.isDisplayed();
			log.info("element is displayed: "+element);
			return true;
		}catch(Exception e){
			log.info(e);
			Reporter.log(e.fillInStackTrace().toString());
			return false;
		}		
	}
	
	protected boolean isNotDisplayed(WebElement element) {
		
		try{
			element.isDisplayed();
			log.info("element is displayed: "+element);
			return false;
		}catch(Exception e){
			log.error(e);
			Reporter.log(e.fillInStackTrace().toString());
			return true;
		}	
		
	}
	
	protected String getDisplayText(WebElement element) {
		
		if(null == element)
			return null;
		if(!isDisplayed(element))
			return null;
		return element.getText();
		
	}
	
	public static synchronized String getElementText(WebElement element){
		
		if(null == element){
			log.info("web element is null");
			return null;
		}
		String elementText = null;
		try{
			elementText = element.getText();
		}catch(Exception e){
			log.info("Element is not found "+e);
			Reporter.log(e.fillInStackTrace().toString());
		}
		return elementText;	
	}
	
	public String readValueFromInput(WebElement element) {
		
		if(null == element)
			return null;
		if(!isDisplayed(element))
			return null;
		
		String value = element.getAttribute("value");
		log.info("Element value is.. "+value);
		
		return value;
	}
}

