package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class EditAccountPageTest extends BaseTest{
	
	@BeforeClass
	public void EditAccountPageTestSetup() {
		accPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		editAccPage=accPage.navigateToEditAccount();
	}
	
	@Test
	public void updateTelTest() {
		accPage= editAccPage.updateTelNo();
		String succMessage =accPage.getAccUpdateSuccessMessage();
		Assert.assertEquals(succMessage, "Success: Your account has been successfully updated.");
	}

}
