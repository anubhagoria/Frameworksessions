package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Utils.ElementUtils;
import com.qa.opencart.constants.AppConstants;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtils eleUtil;
	Map<String,String> hp;
	
	private final By productHeader = By.tagName("h1");
	private final By productImgs = By.xpath("//a[@class='thumbnail']");
	
	private final By proMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private final By proPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtils(driver);
	}
	
	public String getProductHeaderInfo() {
		String actProductHeader = eleUtil.waitForElementvisibility(productHeader, AppConstants.DEFAULT_TIMEOUT).getText();
		System.out.println("Product Header: "+actProductHeader);
		return actProductHeader;
	}
	
	public int getProductImagesCount() {
		int imgCount=eleUtil.waitForElementsVisible(productImgs, AppConstants.DEFAULT_TIMEOUT).size();
		System.out.println("Total number of images for the products: "+imgCount);
		return imgCount;
	}
	
	public Map<String,String> getAllProductInfo() {
		//hp= new HashMap<String,String>();//unordered
		//hp= new LinkedHashMap<String,String>();//ordered
		hp= new TreeMap<String,String>();//sorted with keys
		hp.put("productHeader", getProductHeaderInfo());
		hp.put("productImageCount", String.valueOf(getProductImagesCount()));
		getProMetaDataInfo();
		getPriceDataInfo();
		System.out.println(hp);
		return hp;
	}
	
	private Map<String,String> getProMetaDataInfo() {
		//hp = new HashMap<String,String>();
		List<WebElement> MetaData=eleUtil.waitForElementsVisible(proMetaData, AppConstants.DEFAULT_TIMEOUT);
		for(WebElement ele: MetaData) {
			String metaData = ele.getText();
			String mData[]= metaData.split(":");
			String metaKey = mData[0].trim();
			String metaValue = mData[1].trim();
			hp.put(metaKey, metaValue);
		}
		//System.out.println(hp);
		return hp;
	}
	//$2,000.00
	//Ex Tax: $2,000.00
	private	 Map<String,String> getPriceDataInfo() {
		List<WebElement> PriceData = eleUtil.waitForElementsVisible(proPriceData, AppConstants.DEFAULT_TIMEOUT);
		String productPrice =PriceData.get(0).getText().trim();
		String exTaxPrice = PriceData.get(1).getText().split(":")[1].trim();
		hp.put("productPrice", productPrice);
		hp.put("exTaxPrice", exTaxPrice);
		//System.out.println(hp);
		return hp;
	}

}
