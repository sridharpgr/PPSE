package net.genpt.ppse.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import net.genpt.ppse.excelReader.Excel_Reader;
import net.genpt.ppse.helper.wait.WaitHelper;
import net.genpt.ppse.logintoTAMS.LoginToTAMSII;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//import net.bytebuddy.jar.asm.commons.Method;

public class TestBase {
	
	public static final Logger logger = Logger.getLogger(TestBase.class.getName());
	
	public static WebDriver driver;
	public static Properties OR;
	public File file;
	public FileInputStream fis;
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public ITestResult result;
	public Excel_Reader excelReader;
	public String url;
	public LoginToTAMSII loginToTAMSII;
	
	static{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir") 
				+ "\\src\\main\\java\\net\\genpt\\ppse\\report\\test"
				+ formater.format(calendar.getTime())+".html",false);
	}
	
	@BeforeTest
	public void launchBrowser() throws Exception{
		try {
			loadPropertiesFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Config config = new Config(TestBase.OR);
		loginToTAMSII = new LoginToTAMSII();
		url = loginToTAMSII.loginToTAMS();
		getBrowser(config.getBrowser());
		WaitHelper waitHelper = new WaitHelper(driver);
		waitHelper.setImplicitWait(config.getImplicitWait(), TimeUnit.SECONDS);
		waitHelper.setPageLoadTimeout(config.getPageLoadTimeOut(), TimeUnit.SECONDS);
	}
	
	
	public void getBrowser(String browser){
		if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);
		}
	}
	
	public void loadPropertiesFile() throws IOException{
		String log4jConfPath = "C:\\Users\\dellclient\\workspace\\PPSECatalog\\log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		OR = new Properties();
		file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\net\\genpt\\ppse\\config\\config.properties");
		fis = new FileInputStream(file);
		OR.load(fis);
		logger.info("loading loginPage.properties");
		System.out.println("hai");
	}
	
	public void getPropertiesData(){
		
	}
	
	public String getScreenShot(String imageName) throws IOException{
		if(imageName.equals("")){
			imageName="\\blank";
		}
		File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String imageLocation = System.getProperty("user.dir") + "\\src\\main\\java\\net\\genpt\\ppse\\screenshot";
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String actualImageName = imageLocation+imageName+"_"+formater.format(calendar.getTime())+".png";
		System.out.println("actualImageName***********************************"+actualImageName);
		File destFile = new File(actualImageName);
		FileUtils.copyFile(image, destFile);
		return actualImageName;
	}
	
	public WebElement waitForElement(WebDriver driver, long time, WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public WebElement waitForElementWithPollingInterval(WebDriver driver, long time, WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.pollingEvery(5, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void implicitWait(long time){
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	
	public void getResult(ITestResult result) throws IOException{
		if(result.getStatus() == ITestResult.SUCCESS){
			test.log(LogStatus.PASS, result.getName()+" test is pass");
		}else if(result.getStatus() == ITestResult.SKIP){
			test.log(LogStatus.SKIP, result.getName()+" test is skipped and skip reason is: "+result.getThrowable());
		}else if(result.getStatus() == ITestResult.FAILURE){
			test.log(LogStatus.FAIL, result.getName()+" test is failed and failed reason is: "+result.getThrowable());
			String screen = getScreenShot("");
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
		}else if(result.getStatus() == ITestResult.STARTED){
			test.log(LogStatus.INFO, result.getName()+" test is started");
		}
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException{
		getResult(result);
	}
	
	@BeforeMethod
	public void beforeMethod(Method result){
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO,result.getName()+ " test started");
	}
	
	@AfterClass(alwaysRun=true)
	public void endTest(){
		//driver.quit();
		extent.endTest(test);
		extent.flush();
	}
	
	public WebElement getLocator(String locator) throws Exception{
		
		String split[] = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];
		System.out.println(locatorType);
		System.out.println(locatorValue);
		
		if(locatorType.toLowerCase().equals("id"))
			return driver.findElement(By.id(locatorValue));
		else if(locatorType.toLowerCase().equals("name"))
			return driver.findElement(By.name(locatorValue));
		else if((locatorType.toLowerCase().equals("classname")) ||(locatorType.toLowerCase().equals("class")))
			return driver.findElement(By.className(locatorValue));
		else if((locatorType.toLowerCase().equals("tagname")) ||(locatorType.toLowerCase().equals("tag")))
			return driver.findElement(By.tagName(locatorValue));
		else if((locatorType.toLowerCase().equals("linktext")) ||(locatorType.toLowerCase().equals("link")))
			return driver.findElement(By.linkText(locatorValue));
		else if(locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElement(By.partialLinkText(locatorValue));
		else if((locatorType.toLowerCase().equals("cssselector")) ||(locatorType.toLowerCase().equals("css")))
			return driver.findElement(By.cssSelector(locatorValue));
		else if(locatorType.toLowerCase().equals("xpath"))
			return driver.findElement(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type: "+locatorType);
	}
	
	public List<WebElement> getLocators(String locator) throws Exception{
		
		String split[] = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];
		System.out.println(locatorType);
		System.out.println(locatorValue);
		
		if(locatorType.toLowerCase().equals("id"))
			return driver.findElements(By.id(locatorValue));
		else if(locatorType.toLowerCase().equals("name"))
			return driver.findElements(By.name(locatorValue));
		else if((locatorType.toLowerCase().equals("classname")) ||(locatorType.toLowerCase().equals("class")))
			return driver.findElements(By.className(locatorValue));
		else if((locatorType.toLowerCase().equals("tagname")) ||(locatorType.toLowerCase().equals("tag")))
			return driver.findElements(By.tagName(locatorValue));
		else if((locatorType.toLowerCase().equals("linktext")) ||(locatorType.toLowerCase().equals("link")))
			return driver.findElements(By.linkText(locatorValue));
		else if(locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElements(By.partialLinkText(locatorValue));
		else if((locatorType.toLowerCase().equals("cssselector")) ||(locatorType.toLowerCase().equals("css")))
			return driver.findElements(By.cssSelector(locatorValue));
		else if(locatorType.toLowerCase().equals("xpath"))
			return driver.findElements(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type: "+locatorType);
	}
	
	public WebElement getWebElement(String locator) throws Exception{
		return getLocator(OR.getProperty(locator));
	}
	
	public List<WebElement> getWebElements(String locator) throws Exception{
		return getLocators(OR.getProperty(locator));
	}
	
	public String[][] getData(String excelName, String sheetName){
		String excelLocation = System.getProperty("user.dir") + "\\src\\main\\java\\net\\genpt\\ppse\\data\\"+excelName;
		System.out.println("Excel Path: "+excelLocation);
		excelReader = new Excel_Reader();
		return excelReader.getExcelData(excelLocation, sheetName);
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}

}

