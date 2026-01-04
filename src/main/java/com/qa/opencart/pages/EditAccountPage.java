package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.DEFAULT_TIMEOUT;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Utils.ElementUtils;

public class EditAccountPage {
	
	private WebDriver driver;
	private ElementUtils eleUtil;
	
	private By tel = By.id("input-telephone");
	private By continueButton = By.xpath("//input[@type='submit']");
	
	
	
	
	public EditAccountPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtils(driver);
	}
	
	public AccountsPage updateTelNo() {
		eleUtil.doSendKeys(tel, "9999864532");
		eleUtil.doClick(continueButton);
		return new AccountsPage(driver);
		
	}
	

}
