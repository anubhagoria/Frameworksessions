package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.qa.opencart.constants.AppConstants.*;

@Epic("Epic 100: designs of login page")
@Feature("Feature 101: login page design")
@Story("Story 102: related to the user credentials")
public class LoginPageTest extends BaseTest{

	@Description("checking the login page title is correct")
	@Owner("Anu")
	@Issue("Related to Web-9876")
	@Severity(SeverityLevel.MINOR)
	@Test(description="checking login page title..")
	public void loginPageTitleTest()
	{
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("Actual title: "+actTitle);
		ChainTestListener.log("Checking title of login page: "+actTitle);
		Assert.assertEquals(actTitle,LOGIN_PAGE_TITLE);
	}
	
	@Description("checking the login page url is correct")
	@Owner("Anu")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void loginPageURLTest()
	{
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(LOGIN_PAGE_FRACTION_URL));
	}
	
	@Description("checking the lforget password link")
	@Owner("Anu")
	@Severity(SeverityLevel.TRIVIAL)
	@Test(description="check forgot link is available")
	public void fogotLinkExistsTest()
	{
		boolean forgotLink = loginPage.isForgotPwdLinkExists();
		Assert.assertTrue(forgotLink);
	}
	
	@Description("Checking the login credentials")
	@Owner("Anuradha")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=Short.MAX_VALUE, description="login with valid credentials")
	public void doLoginTest()
	{
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccPageTitle(),HOME_PAGE_TITLE);
	}
	
	@Test(enabled=false)
	public void forgorPass() {
		System.out.println("test enabled as false");
	}
}
