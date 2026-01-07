package com.qa.opencart.base;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.EditAccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

//@Listeners(ChainTestListener.class)
public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	protected LoginPage loginPage;
	protected Properties prop;
	protected AccountsPage accPage;
	protected SearchResultsPage searchPage;
	protected ProductInfoPage proInfoPage;
	protected RegisterPage registerPage;
	protected EditAccountPage editAccPage;
	
	public static final Logger log = LogManager.getLogger(BaseTest.class);
	
	@Parameters({"browser"})//comming from testng_grefression.xml
	@BeforeTest
	public void setUp(String browserName) {//holding parameter for browser
		df = new DriverFactory();
		prop = df.initProp();
		
		//read browser from testng.xml file
		if(browserName!=null) {
			prop.setProperty("browser", browserName);//updating the previous prorperty from 
			                                         //properties file to the testng property
		}
		
		driver = df.initDriver(prop);
		loginPage= new LoginPage(driver);
	}
	
	
	
	@AfterMethod
	public void attachScreenshot(ITestResult result) {
		
		 if(!result.isSuccess()) {
		 ChainTestListener.embed(DriverFactory.getScreenshotBase64(),"img/png"); 
		 }
		//ChainTestListener.embed(DriverFactory.getScreenShotFile(),"img/png");
		
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
		log.info("closing the browser");
	}
	
	

}
