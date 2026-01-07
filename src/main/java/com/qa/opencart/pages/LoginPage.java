package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Utils.ElementUtils;

import io.qameta.allure.Step;

import static com.qa.opencart.constants.AppConstants.*;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtils eleUtil;
	
	//1. private By locators
	private final By emailId = By.xpath("//input[@id='input-email']");
	private final By password = By.id("input-password");
	private final By loginButton = By.xpath("//input[@value='Login']");
	private final By forgotPassLink = By.linkText("Forgotten Password");
	private final By registerLink = By.linkText("Register");
	
	
	//2. public page consructor
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(driver);
	}
	
	//3. public page actions,methods
	@Step("getting the login page title")
	public String getLoginPageTitle() {
		String pagetitle=eleUtil.waitForTitleIs(LOGIN_PAGE_TITLE,DEFAULT_TIMEOUT);
		System.out.println("Login page title: "+pagetitle);
		return pagetitle;
	}
	
	@Step("getting the login page url")
	public String getLoginPageURL() {
		String pageURL = eleUtil.waitForURLContains(LOGIN_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		System.out.println("Login page URL: "+pageURL);
		return pageURL;
	}
	
	@Step("checking the password link")
	public boolean isForgotPwdLinkExists() {
		return eleUtil.isElementDisplayed(forgotPassLink);
	
	}
	
	@Step("Login with name {0} and password {1}")
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("User credentials: "+username+" "+pwd);
		eleUtil.waitForElementvisibility(emailId, MEDIUM_DEFAULT_TIMEOUT).sendKeys(username);
		driver.findElement(password).sendKeys(pwd);
		eleUtil.doClick(loginButton);
		return new AccountsPage(driver);
	}
	
	@Step("Navigating to the registration page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.clickWhenReady(registerLink, DEFAULT_TIMEOUT);
		return new RegisterPage(driver);
	}

}
