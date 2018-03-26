package net.genpt.ppse.helper.browser;


import java.util.LinkedList;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import net.genpt.ppse.helper.logger.LoggerHelper;

public class BrowserHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(BrowserHelper.class);
	
	public BrowserHelper(WebDriver driver){
		this.driver = driver;
		log.debug("Browser Helper : "+this.driver.hashCode());
	}
	
	public void goBack(){
		driver.navigate().back();
		log.info("");
	}
	
	public void goForward(){
		driver.navigate().forward();
		log.info("");
	}
	
	public void refresh(){
		driver.navigate().refresh();
		log.info("");
	}
	
	public Set<String> getWindowHandles(){
		log.info("");
		return driver.getWindowHandles();
	}
	
	public void switchToWindow(int index){
		
		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandles());
		if(index < 0 || index > windowsId.size())
			throw new IllegalArgumentException("Invalid Index: "+index);
		driver.switchTo().window(windowsId.get(index));
		log.info(index);
		
	}
	
	public void switchToParentWindow(){
		
		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandles());
		driver.switchTo().window(windowsId.get(0));
		log.info("");
		
	}
	
	public void switchToParentWithChildClose(){
		
		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandles());
		
		for(int i=0; i<windowsId.size(); i++){
			log.info(windowsId.get(i));
			driver.switchTo().window(windowsId.get(i));
			driver.close();
		}
		
		switchToParentWindow();
	}
	
	public void switchToFrame(String nameOrId){
		
		driver.switchTo().frame(nameOrId);
		log.info(nameOrId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

