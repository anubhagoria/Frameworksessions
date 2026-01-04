package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Utils.ElementUtils;
import com.qa.opencart.constants.AppConstants;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtils eleUtil;

	private By resultProducts = By.xpath("//div[contains(@class ,'product-layout')]");
	
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtils(driver);
	}
	
	public int getResultProductCount() {
	 int count = eleUtil.waitForElementsVisible(resultProducts, AppConstants.DEFAULT_TIMEOUT).size();
	 System.out.println("Total number of products: "+count);
	 return count;
	}
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("Product name: "+productName);
		eleUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}
	
	
}
