package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Utils.ElementUtils;
import com.qa.opencart.Utils.StringUtils;
import com.qa.opencart.constants.AppConstants;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtils eleUtil;
	
	private final By firstname = By.id("input-firstname");
	private final By lastname = By.id("input-lastname");
	private final By emailId = By.name("email");
	private final By tel = By.id("input-telephone");
	private final By password = By.id("input-password");
	private final By confirmPwd =By.id("input-confirm");
	
	private final By agreecheckBox = By.name("agree");
	private final By continueButton = By.xpath("//*[@id=\"content\"]/form/div/div/input[2]");
	
	private final By successMsg = By.xpath("//*[@id=\"content\"]/h1");
	
	private final By subscribeYes = By.xpath("(//input[@name='newsletter'])[1]");
	private final By subscribeNo = By.xpath("(//input[@name='newsletter'])[2]");
	
	private final By logoutLink = By.linkText("Logout");
	private final By registerLink = By.linkText("Register");

	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtils(driver);
	}
	
	public boolean registerUser(String firstname,String lastname,String tel, String password, String subscribe) {
		eleUtil.waitForElementvisibility(this.firstname,AppConstants.LONG_DEFAULT_TIMEOUT).sendKeys(firstname);
		eleUtil.doSendKeys(this.lastname, lastname);
		eleUtil.doSendKeys(this.emailId,StringUtils.getRandomEmailId());
		eleUtil.doSendKeys(this.tel, tel);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPwd, password);
		
		
		if (subscribe.equalsIgnoreCase("Yes")) {
			eleUtil.doClick(subscribeYes);
		}else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(agreecheckBox);
		eleUtil.doClick(continueButton);
		
		if(eleUtil.waitForElementvisibility(successMsg, AppConstants.LONG_DEFAULT_TIMEOUT).getText().contains(AppConstants.REGISTER_SUCCESS_MESSG)) {
			eleUtil.waitForElementvisibility(logoutLink, AppConstants.LONG_DEFAULT_TIMEOUT).click();
			//eleUtil.doClick(logoutLink);
			eleUtil.waitForElementvisibility(registerLink, AppConstants.LONG_DEFAULT_TIMEOUT).click();
			//eleUtil.doClick(registerLink);
			return true;
		}	
		return false;
			
	}
	

}
