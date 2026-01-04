package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import com.qa.opencart.base.BaseTest;
import static com.qa.opencart.constants.AppConstants.*;

public class AccountsPageTest extends BaseTest{

	@BeforeClass
	public void accPageSetup() {
		accPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void accTitlePageTest() {
		String accAccPageTitle =accPage.getAccPageTitle();
		Assert.assertEquals(accAccPageTitle, HOME_PAGE_TITLE);
	}
	
	@Test
	public void accPageURL() {
		String accURL = accPage.getAccPageURL();
		Assert.assertTrue(accURL.contains(HOME_PAGE_FRACTION_URL));
				
	}
	@Test
	public void accPageHeaderTest() {
		List<String> actAccPageHeaderLst=accPage.getAccPageHeaderList();
		Assert.assertEquals(actAccPageHeaderLst, expAccPageHeaderList);
	}
}
