package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;

	/**
	 * This method initializing driver on the basis of browser name
	 * 
	 * @param browserName
	 */
	public WebDriver initDriver(Properties prop) {

		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");

		String browserName = prop.getProperty("browser");

		System.out.println("Browser name is " + browserName);

		switch (browserName.trim().toLowerCase()) {

		case "chrome":
			driver = new ChromeDriver(optionsManager.getChromeOptions());
			break;
		case "firefox":
			driver = new FirefoxDriver(optionsManager.getFirefoxptions());
			break;
		case "edge":
			driver = new EdgeDriver(optionsManager.getEdgeptions());
			break;

		default:
			System.out.println("Please supply correct password");
			throw new BrowserException("====INVALID BROWSER====");
		}
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return driver;
	}

	// mvn clean install -Denv="qa"
	public Properties initProp() {

		FileInputStream ip = null;
		prop = new Properties();
		String envName = System.getProperty("env");

		try {
			if (envName == null) {
				System.out.println("Env is null, running the tests on qa environment");
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
}
