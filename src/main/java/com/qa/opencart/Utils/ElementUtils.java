package com.qa.opencart.Utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtils {

	WebDriver driver;
	private JavaScriptUtil javaUtil;

	private void nullCheck(CharSequence... value) {
		if (value == null) {
			throw new RuntimeException("===Null value===");
		}
	}

	public ElementUtils(WebDriver driver) {
		this.driver = driver;
		javaUtil= new JavaScriptUtil(driver);
	}

	public void doSendKeys(By locator, String value) {
		nullCheck(value);
		getElement(locator).clear();
		getElement(locator).sendKeys(value);

	}

	public WebElement getElement(By locator) {
		WebElement ele = driver.findElement(locator);
		highlightElement(ele);
		return ele;
	}
	
	private void highlightElement(WebElement ele) {
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			javaUtil.flash(ele);
		}
	}

	public void doClick(By locator) {
		WebElement e1 = getElement(locator);
		e1.click();
	}

	public void doClickCheckbox(By locator) {
		WebElement e1 = getElement(locator);
		if (e1.isSelected()) {
		} else {
			e1.click();
		}
	}

	public String dogetEleDomattrname(By locator, String attname) {
		WebElement e1 = getElement(locator);
		String att = e1.getDomAttribute(attname);
		System.out.println("Element placeholder: " + att);
		return att;

	}

	public String dogetEleDomprop(By locator, String attname) {
		WebElement e1 = getElement(locator);
		String att = e1.getDomProperty(attname);
		// System.out.println(attname+" text: "+att);
		return att;

	}

	public String getTextelement(By locator) {
		WebElement e1 = getElement(locator);
		String eletext = e1.getText();
		//System.out.println("Element text: " + eletext);
		return eletext;

	}

	public void doSendKeys(By locator, CharSequence... value) {
		nullCheck(value);
		getElement(locator).sendKeys(value);

	}

	public boolean isElementDisplayed(By locator) {
		try {
			getElement(locator).isDisplayed();
			return true;
			
		} catch (NoSuchElementException e) {
			System.out.println("Element not found");
			return false;
		}
	}	
		// **************findElements utils************//
	public List<String> getElementTextList(By locator) {
		List<String> menuListTextList = new ArrayList<String>();
		List<WebElement> eleList= getElements(locator);
		int count=getSizeOfList(locator);
		for(int i=0;i<count;i++) {
			if (eleList.get(i).getText().length()!=0) {
				menuListTextList.add(eleList.get(i).getText());
			}
		}
		return menuListTextList;
	}
	
	public int getSizeOfList(By locator) {
		int elecount = getElements(locator).size();
		System.out.println(elecount);
		return elecount;
	}
	
	public List<WebElement> getElements(By locator) {
		List<WebElement> menuList = driver.findElements(locator);
		return menuList;
	}
	//**************Element displayed- with findelements*********//
	public boolean checkElementDisplyed(By locator) {
		List<WebElement> elelist = getElements(locator);
		if(elelist.size()==1) {
			return true;
		}
		return false;
	}
	
	public void checkElementDisplyed(By locator, int expcount) {
		List<WebElement> elelist = getElements(locator);
		int c= elelist.size();
		if (c == expcount) {
			System.out.println(locator+" displayed on page "+c+" times");
			
		}
	}
	
	//*******Click on any link(for eg footer)***********//
	public void clickOnLink(By locator,String element) {
		List<WebElement> elelist =getElements(locator);
		System.out.println(elelist.size());
		for (WebElement e:elelist) {
			System.out.println(e.getText());
			if (e.getText().equals(element)) {
				e.click();
				break;
			}
		}
	}
	
	//**************Drop-down with select*************//
	public WebElement getWebElement(By locator) {
		return driver.findElement(locator);
	}

	public boolean selByIndex(By locator, int idx) {
		Select select1 = new Select(getWebElement(locator));
		try {
			select1.selectByIndex(idx);
			return true;
		} catch (NoSuchElementException e) {
			System.out.println(idx + " not present");
			return false;
		}
	}

	public boolean selByVisibleText(By locator, String txt) {
		Select select2 = new Select(getWebElement(locator));
		try {
			select2.selectByVisibleText(txt);
			return true;
		} catch (NoSuchElementException e) {
			System.out.println(txt + " not present");
			return false;
		}
	}

	public boolean selByValue(By locator, String val) {
		Select select3 = new Select(getWebElement(locator));
		try {
			select3.selectByValue(val);
			return true;
		} catch (NoSuchElementException e) {
			System.out.println(val + " not present");
			return false;
		}

	}
	
	//**************Action utils***********************//
	public void handleParentSubMenu(By parentMenu, By subMenu) {

		Actions act = new Actions(driver);
		act.moveToElement(getWebElement(parentMenu)).perform();
		doClick(subMenu);
		
	}
 
	//************Non-select -Jquery drop down****************//
	public void selectChoices(By dropDown, By choiceList, String... choice) {
		doClick(dropDown);
		List<WebElement> choices = getElements(choiceList);
		if (choice[0].equalsIgnoreCase("all")) {
			for (WebElement wb : choices) {
				wb.click();
			}
		} else {

			for (WebElement wb : choices) {
				System.out.println(wb.getText());
				for (String val : choice) {
					if (wb.getText().equals(val)) {
						wb.click();
						break;
					}
				}
			}
		}

	}
	//wait utils*****************//
	/*
	 * An expectation for checking that an element is present on the DOM of a page. 
	 * This does notnecessarily mean that the element is visible.
       Parameters:locator used to find the element
       Returns:the WebElement once it is located
	 */
	
	public WebElement waitForElementPresence(By locator, int timeout) {
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(timeout));
		WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return ele;
	}
	/*An expectation for checking that an element is present on the DOM of a page 
	 * and visible.
	 * Visibility means that the element is not only displayed but also 
	 * has a height and width that is greater than 0.
		Parameters:locator used to find the element
		Returns:the WebElement once it is located and visible
	 * 
	 */
	public WebElement waitForElementvisibility(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		WebElement ele =wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		highlightElement(ele);
		return ele;
	}
	/**An expectation for checking that there is at least one element present on a web page.*/
	public List<WebElement> waitForElementsPresence(By locator, int timeout) {
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(timeout));
		List<WebElement> eleList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		return eleList;
	}
	/**
	 * An expectation for checking that all elements present on the web page that match the locatorare visible. Visibility means that the 
	 * elements are not only displayed but also have a heightand width that is greater than 0
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public List<WebElement> waitForElementsVisible(By locator, int timeout) {
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(timeout));
		try {
			List<WebElement> eleList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			return eleList;
		}catch(TimeoutException e) {
			return Collections.EMPTY_LIST;//[] size is zero
		}
	}
	
	
	public void clickWithWait(By locator) {
		waitForElementvisibility(locator,10).click();
	}
	public void sendKeysWithWait(By locator, CharSequence...value) {
		waitForElementvisibility(locator,10).sendKeys(value);
	}
	
	//waitfor alert- js pop up alerts
	public Alert waitForAlertPresent(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	public void acceptAlert(int timeOut) {
		waitForAlertPresent(timeOut).accept();
	}
	public void dismissAlert(int timeOut) {
		waitForAlertPresent(timeOut).dismiss();
	}
	public String getAlertText(int timeOut) {
		return waitForAlertPresent(timeOut).getText();
	}
	public void sendKeysAlert(int timeOut, String value) {
		waitForAlertPresent(timeOut).sendKeys(value);
	}
	//wait for title
	public String waitForTitleContains(String partialTitle, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		try {
		wait.until(ExpectedConditions.titleContains(partialTitle));
		return driver.getTitle();
		}catch(TimeoutException e) {
			return null;
		}
	}
	
	public String waitForTitleIs(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.titleIs(title));
			return driver.getTitle();
		}catch(TimeoutException e) {
			return null;
		}
	}
	
	//wait for url
	public String waitForURLContains(String partialurl, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		try {
		wait.until(ExpectedConditions.urlContains(partialurl));
		return driver.getCurrentUrl();
		}catch(TimeoutException e) {
			return null;
		}
	}
	
	public String waitForURLIs(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.urlToBe(url));
			return driver.getCurrentUrl();
		}catch(TimeoutException e) {
			return null;
		}
	}
	//wait for frame
	public void waitForFrameAndSwitchToIt(By frameBylocator,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameBylocator));

	}
	
	public void waitForFrameAndSwitchToIt(String frameNameOrId, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameOrId));
	}
	public void waitForFrameAndSwitchToIt(int frameIndex, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}
	
	public void waitForFrameAndSwitchToIt(WebElement frameElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
	//wait for browserwindow
	public boolean waitForBrowserWindow(int noOfExpectedWindows, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.numberOfWindowsToBe(noOfExpectedWindows));
			return true;
		} catch (Exception e) {
			System.out.println("Expected number of windows are not correct");
			return false;
		}

	}
	/**
	 * An expectation for checking an element is 
	 * visible and enabled such that you can click it.
	 * Parameters:locator used to find the elementReturns:the WebElement once it is located and clickable 
	 * (visible and enabled)
	 * @param locator
	 * @param timeOut
	 */
	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	public WebElement waitForElementVisibleWithFluentWait(By locator, int timeOut,int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
									.withTimeout(Duration.ofSeconds(timeOut))
									.pollingEvery(Duration.ofSeconds(pollingTime))
									.ignoring(NoSuchElementException.class)
									.ignoring(StaleElementReferenceException.class)
									.withMessage("===Element not found===");
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
								
	}
	public WebElement waitForElementpresenceWithFluentWait(By locator, int timeOut,int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
									.withTimeout(Duration.ofSeconds(timeOut))
									.pollingEvery(Duration.ofSeconds(pollingTime))
									.ignoring(NoSuchElementException.class)
									.ignoring(StaleElementReferenceException.class)
									.withMessage("===Element not found===");
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

}
