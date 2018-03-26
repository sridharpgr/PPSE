package net.genpt.ppse.testBase;

import java.util.Properties;

public class Config extends TestBase{
	
	private Properties OR;
	
	public Config(Properties OR){
		this.OR = OR;
	}
	public String getUserName() {
		return OR.getProperty("UserName");
	}

	public String getPassword() {
		return OR.getProperty("Password");
	}

	public String getURL() {
		return OR.getProperty("URL");
	}

	public int getPageLoadTimeOut() {
		return Integer.parseInt(OR.getProperty("PageLoadTimeOut"));
	}

	public int getImplicitWait() {
		return Integer.parseInt(OR.getProperty("ImplicitWait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(OR.getProperty("ExplicitWait"));
	}

	public String getDbType() {
		return OR.getProperty("DataBase.Type");
	}

	public String getDbConnStr() {
		return OR.getProperty("DtaBase.ConnectionStr");
	}
	public String getBrowser() {
		return OR.getProperty("Browser");
	}
	
	public String getStoreNumber() {
		return OR.getProperty("StoreNumber");
	}
	
	public String getDCCode() {
		return OR.getProperty("DCCode");
	}
	
	public String getCustomerNumber() {
		return OR.getProperty("CustomerNumber");
	}
	
	public String getTamsVersion() {
		return OR.getProperty("TamsVersion");
	}
}

