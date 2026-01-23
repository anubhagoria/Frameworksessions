package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tlDriver= new ThreadLocal<WebDriver>(); 
	
	public static final Logger log = LogManager.getLogger(DriverFactory.class);
	//warnin, info, error, fatalerror

	/**
	 * This method initializing driver on the basis of browser name
	 * 
	 * @param browserName
	 */
	public WebDriver initDriver(Properties prop) {
		
		log.info("Properties: "+prop);

		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");

		String browserName = prop.getProperty("browser");
		log.info("Browser name: "+browserName);

		//System.out.println("Browser name is " + browserName);

		switch (browserName.trim().toLowerCase()) {

		case "chrome":
			
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				try {
					tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getChromeOptions()));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
			break;
		case "firefox":
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				try {
					tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getChromeOptions()));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxptions()));
			}
			break;
		case "edge":
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				try {
					tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getChromeOptions()));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeptions()));
			}
			break;

		default:
			log.error("Please supply correct Browser "+browserName);
			//System.out.println("Please supply correct Browser");
			throw new BrowserException("====INVALID BROWSER====");
		}
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	// mvn clean install -Denv="qa"
	public Properties initProp() {

		FileInputStream ip = null;
		prop = new Properties();
		String envName = System.getProperty("env");

		try {
			if (envName == null) {
				log.warn("Env is null, running the tests on qa environment");
				//System.out.println("Env is null, running the tests on qa environment");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} else {
				System.out.println("Running the tests on: " + envName);
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				default:
					log.error("===Invalid environment name=== "+envName);	
					throw new FrameworkException("===Invalid environment===");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	
	public static File getScreenShotFile() {
		//TakesScreenshot ts = (TakesScreenshot)driver;  instead of these 2 line below line does the same thing
		//File file = ts.getScreenshotAs(OutputType.FILE);
		
		File scrFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		return scrFile;
	}
	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir

	}

	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir

	}
}
