package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.EditAccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;


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
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browserName) {
		df = new DriverFactory();
		prop = df.initProp();
		
		//read browser from testng.xml file
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = df.initDriver(prop);
		loginPage= new LoginPage(driver);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	

}
