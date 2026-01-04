package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Utils.ElementUtils;
import com.qa.opencart.constants.AppConstants;

import static com.qa.opencart.constants.AppConstants.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtils eleUtil;
	
	private final By accPageHeaderList = By.cssSelector("div#content>h2");
	private final By searchIcon = By.cssSelector("div#search button");
	private final By search = By.cssSelector("div#search>input");
	
	private final By editAccLink = By.linkText("Edit Account");
	private By successMsg = By.xpath("//div[contains(@class,'alert-success')]");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(driver);
	}
	
	public String getAccPageTitle() {
		String accPageTitle =eleUtil.waitForTitleIs(HOME_PAGE_TITLE, DEFAULT_TIMEOUT);
		return accPageTitle;
	}
	
	public String getAccPageURL() {
		String accFracURL = eleUtil.waitForURLContains(HOME_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		return accFracURL;
	}
	
	public List<String> getAccPageHeaderList() {
		List<WebElement> headerList = new ArrayList<WebElement>();
		headerList = eleUtil.getElements(accPageHeaderList);
		List<String> lstText = new ArrayList<String>();
		for(WebElement e: headerList) {
			String headerText = e.getText();
			lstText.add(headerText);
		}
		System.out.println(lstText);
		return lstText;
	}
	public SearchResultsPage doSearch(String searchKey) {
		System.out.println("SearchKey: "+searchKey);
		eleUtil.doSendKeys(search, searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}
	
	
	public EditAccountPage navigateToEditAccount() {
		eleUtil.clickWhenReady(editAccLink, DEFAULT_TIMEOUT);
		return new EditAccountPage(driver);
	}
	public String getAccUpdateSuccessMessage() {
		return eleUtil.getTextelement(successMsg);
	}
}
