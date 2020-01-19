package cn.gloryroad;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JQueryTest {

	public WebDriver webDriver;
	JavascriptExecutor js;
	String baseUrl = "http:www.sogou.com/";
	
	@SuppressWarnings("unchecked")
	@Test
	public void jQueryTest() throws InterruptedException{
		System.setProperty("webdriver.firefox.bin","E:\\Mozilla Firefox\\firefox.exe");
		System.setProperty("webdriver.gecko.driver","E:\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		webDriver = new FirefoxDriver();
		webDriver.get(baseUrl+"/");
		//将webDriver转换为JavascriptExecutor的对象
		js = (JavascriptExecutor) webDriver;
		injectjQueryIfNeeded();
		
		/*调用JavascriptExecutor对象的executeScript函数，执行javascript语句return jQuery.find('a')
		 * return jQuery.find('a')表示调用jQuery的find方法查找页面中所有a标签的页面元素
		 */
		List<WebElement> elements = (List<WebElement>) js.executeScript("return jQuery.find('a')");
		assertEquals(128,elements.size());
		for (int i = 0; i < elements.size(); i++) {
			System.out.print(elements.get(i).getText() + "、");
		}
	}
	/*
	 * 由于网页页面可能不会默认包含jQuery库，所以先要判断是否包含
	 */
	private void injectjQueryIfNeeded() {
		if(!jQueryLoaded()) {
			injectjQuery();
		}
	}
	
	//判断是否已加载jQuery
	public Boolean jQueryLoaded() {
		Boolean loaded;
		try {
			loaded = (Boolean) js.executeScript("return"+"jQuery()!=null");
		} catch (WebDriverException e) {
			loaded = false;
		}
		return loaded;
	}
	
	//通过注入JQuery
	public void injectjQuery() {
		js.executeScript(" var headID ="
				+ "document.getElementsByTagName(\"head\")[0];"
				+ "var newScript = document.createElement('script');"
				+ "newScript.type = 'text/javascript';" + "newScript.src = "
				+ "'http://ajax.googleapis.com/ajax/"
				+ "libs/jquery/1.7.2/jquery.min.js';"
				+ "headID.appendChild(newScript);");
	}
}
