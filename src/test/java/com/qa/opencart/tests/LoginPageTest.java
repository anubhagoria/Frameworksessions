package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;

import static com.qa.opencart.constants.AppConstants.*;

public class LoginPageTest extends BaseTest{

	@Test(description="checking login page title..")
	public void loginPageTitleTest()
	{
		String actTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("Checking title of login page: "+actTitle);
		Assert.assertEquals(actTitle,LOGIN_PAGE_TITLE);
	}
	@Test
	public void loginPageURLTest()
	{
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(LOGIN_PAGE_FRACTION_URL));
	}
	
	@Test(description="check forgot link is available")
	public void fogotLinkExistsTest()
	{
		boolean forgotLink = loginPage.isForgotPwdLinkExists();
		Assert.assertTrue(forgotLink);
	}
	
	@Test(priority=Short.MAX_VALUE, description="login with valid credentials")
	public void doLoginTest()
	{
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccPageTitle(),HOME_PAGE_TITLE);
	}
	
	@Test(enabled=true)
	public void forgorPass() {
		System.out.println("test enabled as false");
	}
}
