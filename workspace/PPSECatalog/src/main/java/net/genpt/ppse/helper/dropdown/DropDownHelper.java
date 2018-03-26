package net.genpt.ppse.helper.dropdown;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import net.genpt.ppse.helper.logger.LoggerHelper;

public class DropDownHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(DropDownHelper.class);
	
	public DropDownHelper(WebDriver driver){
		this.driver = driver;
		log.debug("Drop down Helper : "+this.driver.hashCode());
	}
	
	public void selectVisibleValue(WebElement element, String visibleValue){
		Select select = new Select(element);
		System.out.println("visibleValue********** "+visibleValue);
		select.selectByVisibleText(visibleValue);
		log.info("Locator: "+element+" Value: "+visibleValue);
	}
	
	public String getSelectedValue(WebElement element){
		String value = new Select(element).getFirstSelectedOption().getText();
		log.info("Locator: "+element+" Value: "+value);
		return value;
	}
	
	public void selectUsingIndex(WebElement element, int index){
		Select select = new Select(element);
		select.selectByIndex(index);
		log.info("Locator: "+element+" Value: "+index);
	}
	
	public List<String> getAllDropDownValues(WebElement locator){
		Select select = new Select(locator);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		
		for(WebElement element : elementList){
			log.info(element.getText());
			valueList.add(element.getText());
		}
		
		return valueList;		
	}
}

