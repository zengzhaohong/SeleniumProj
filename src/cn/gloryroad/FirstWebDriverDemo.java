package cn.gloryroad;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirstWebDriverDemo {
	public static void main(String args[]) {
		WebDriver webDriver;
		String baseUrl;
		
		System.setProperty("webdriver.firefox.bin","E:\\Mozilla Firefox\\firefox.exe");
		System.setProperty("webdriver.gecko.driver","E:\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		//���ػ���������������
		webDriver = new FirefoxDriver();
		baseUrl = "http://www.sogou.com/";
		//�򿪰ٶ���ҳ
		webDriver.get(baseUrl+"/");
		webDriver.findElement(By.id("query")).sendKeys("����֮·�Զ�������");
		//����������ť
		webDriver.findElement(By.id("stb")).click();
		
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		webDriver.close();
	}
}
