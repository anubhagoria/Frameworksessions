package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.Utils.CSVUtil;
import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void ProductInfoSetup() {
		accPage =loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductTestData(){
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"macbook","MacBook Air"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"}
		};	
	}

	@Test(dataProvider="getProductTestData")
	public void productHeaderTest(String productKey, String productName) {
		searchPage = accPage.doSearch(productKey);
		proInfoPage=searchPage.selectProduct(productName);
		String actheader = proInfoPage.getProductHeaderInfo();
		Assert.assertEquals(actheader, productName);
	}
	
	@DataProvider
	public Object[][] getImageCountTestData(){
		return new Object[][] {
			{"macbook","MacBook Pro",4},
			{"macbook","MacBook Air",4},
			{"imac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"samsung","Samsung Galaxy Tab 10.1",7}
		};
	}
	
	@DataProvider
	public Object[][] getProductCSVData() {
		return CSVUtil.csvData("product");
	}
	
	@Test(dataProvider="getImageCountTestData")
	public void productImageCountTest(String searchKey, String productName,int expImgCount) {
		searchPage = accPage.doSearch(searchKey);
		proInfoPage = searchPage.selectProduct(productName);
		int actimgCount=proInfoPage.getProductImagesCount();
		Assert.assertEquals(actimgCount, expImgCount);
	}
	
	@Test
	public void productInfoTest() {
		searchPage = accPage.doSearch("macbook");
		proInfoPage = searchPage.selectProduct("MacBook Pro");
		Map<String,String> actProMap = proInfoPage.getAllProductInfo();
		
		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals(actProMap.get("Brand"), "Apple");
		softassert.assertEquals(actProMap.get("Product Code"), "Product 18");
		softassert.assertEquals(actProMap.get("Reward Points"), "800");
		softassert.assertEquals(actProMap.get("Availability"), "Out Of Stock");
		softassert.assertEquals(actProMap.get("productPrice"), "$2,000.00");
		softassert.assertEquals(actProMap.get("exTaxPrice"), "$2,000.00");
		
		softassert.assertAll();
		
	}
}
