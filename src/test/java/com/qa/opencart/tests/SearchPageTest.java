package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchPageTest extends BaseTest{
	
	@BeforeClass
	public void searchSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void searchTest() {
		searchPage=accPage.doSearch("airtel");
		int productsCount = searchPage.getResultProductCount();
		Assert.assertEquals(productsCount, 0);
		
	}
	
}
