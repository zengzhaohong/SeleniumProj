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
		//��webDriverת��ΪJavascriptExecutor�Ķ���
		js = (JavascriptExecutor) webDriver;
		injectjQueryIfNeeded();
		
		/*����JavascriptExecutor�����executeScript������ִ��javascript���return jQuery.find('a')
		 * return jQuery.find('a')��ʾ����jQuery��find��������ҳ��������a��ǩ��ҳ��Ԫ��
		 */
		List<WebElement> elements = (List<WebElement>) js.executeScript("return jQuery.find('a')");
		assertEquals(128,elements.size());
		for (int i = 0; i < elements.size(); i++) {
			System.out.print(elements.get(i).getText() + "��");
		}
	}
	/*
	 * ������ҳҳ����ܲ���Ĭ�ϰ���jQuery�⣬������Ҫ�ж��Ƿ����
	 */
	private void injectjQueryIfNeeded() {
		if(!jQueryLoaded()) {
			injectjQuery();
		}
	}
	
	//�ж��Ƿ��Ѽ���jQuery
	public Boolean jQueryLoaded() {
		Boolean loaded;
		try {
			loaded = (Boolean) js.executeScript("return"+"jQuery()!=null");
		} catch (WebDriverException e) {
			loaded = false;
		}
		return loaded;
	}
	
	//ͨ��ע��JQuery
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
