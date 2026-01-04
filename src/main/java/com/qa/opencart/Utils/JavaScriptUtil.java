package com.qa.opencart.Utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	private WebDriver driver;
	private JavascriptExecutor js;

	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor)driver;
	}
	
	public String getTitleByJavascript() {
		return js.executeScript("return document.title").toString();
	}
	
	public String getUrlByJavascript() {
		return js.executeScript("return document.URL").toString();
	}	
	public void refreshBrowserByJs() {
		js.executeScript("history.go(0)");
	}
	public void navigateForwardByJs() {
		js.executeScript("history.go(1)");
	}
	public void navigateBackByJs() {
		js.executeScript("history.go(-1)");
	}
	//used when giving some demo or say driver on login page
	//alert('Login Page')
	public void generatAlertByJs(String message) {
		js.executeScript("alert('"+message+"')");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.switchTo().alert().dismiss();
	}
	public String getPageInnerText() {
		return js.executeScript("return document.documentElement.innerText;").toString();
	}
	
	public void scrollPageDownByJs() {
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public void scrollPageUpByJs() {
		js.executeScript("window.scrollTo(document.body.scrollheight,0)");
	}
	
	public void scrollPageDownHeightByJs(String height) {
		js.executeScript("window.scrollTo(0,"+height+")");
	}
	
	public void scrollToElement(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true)",element);
	}
	public void drawBorder(WebElement element) {
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}
	public void flash(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor");//blue
		for (int i = 0; i < 5; i++) {
			changeColor("rgb(0,200,0)", element);// green
			changeColor(bgcolor, element);// blue
		}
	}

	private void changeColor(String color, WebElement element) {
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);//GBGBGBGBGBGB

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}
	public void clickEleByJs(WebElement element) {
	js.executeScript("arguments[0].click()",element);
	}
	public void sendKeysUsingWithIdByJS(String id, String value) {
		js.executeScript("document.getElementById('"+id+"').value='"+value+"'");
		//js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
						  //document.getElementById('input-email').value ='tom@gmail.com'
	}
	public void zoomChromeEdgeFirefox(String zoomPercentage) {
		js.executeScript("document.body.style.zoom="+"'"+zoomPercentage+"'");
	}
	//sometimes above method doesn't work in fitrefox so use below
	public void zoomFirefox(String zoomPercentage) {
		String zoom = "document.body.style.MozTransform = 'scale("+zoomPercentage+")'";
		js.executeScript(zoom);

	}
}
