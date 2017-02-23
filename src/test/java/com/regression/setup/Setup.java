/**
 * Copyright (c) 2017 by QA Team of Wemakeprice. All Rights Reserved.
 * 
 * Permission to use, copy, modify, and distribute this software and its documentation for
 * educational, research, and not-for-profit purposes, without fee and without a signed licensing agreement,
 * is hereby granted, provided that the above copyright notice appears in all copies, modifications, and distributions.
 */

package com.regression.setup;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import com.regression.Util.DBConnector;
import com.wemakeprice.driver.CapabilitiesFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public class Setup extends PageObject {
	public static AppiumDriver driver;
	public static int login = 0;
	MobileElement btn;
	public static WebDriverWait wait;
	
	public static String testName = null;
	public static String detailTest = null;
	
	WebElement inputField;
	
    @BeforeMethod
    public void setUp() throws Exception {
    	driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),CapabilitiesFactory.getCapabilities());
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

    	// 로그인 상태 유무 확인
    	for (LogEntry logEntry : driver.manage().logs().get("syslog").getAll()){
			if (logEntry.getMessage().contains("email =")){
				login = 1;
			}
		}
    	
    	// 팝업 닫기
    	MobileElement body_message = (MobileElement)driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]");
		if (String.valueOf(body_message.getAttribute("name")).equals("다시 보지 않기")) {
			driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").click();
		} else if (String.valueOf(body_message.getAttribute("name")).equals("선택완료")) {
			// 회원가입 후 최초 로그인 시 노출되는 팝업
			// 남성 선택 
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAButton[2]")).click();

			//출생선택
			inputField = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAPicker[1]/UIAPickerWheel[1]"));
			inputField.click();
			driver.findElementByClassName("UIAPickerWheel").sendKeys("1988");
			
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")).click();
			
			if (String.valueOf(body_message.getAttribute("name")).equals("다시 보지 않기")) {
				driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").click();	
			}
		}
    }
    
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(ITestResult.FAILURE==result.getStatus())
		{
			try 
			{
				TakesScreenshot ts=(TakesScreenshot)driver;
				File source=ts.getScreenshotAs(OutputType.FILE);
				SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
				Calendar calendar = Calendar.getInstance();
				FileUtils.copyFile(source, new File("/Users/we/Screenshots/"+ testName + "_" + detailTest + formater.format(calendar.getTime())+".png"));
				System.out.println("Screenshot taken");
				
				DBConnector.dbFailUpdate(testName, detailTest);
				System.out.println("실패한테스트 : " + testName + "_" + detailTest);
			} 
			catch (Exception e)
			{
				System.out.println("Exception while taking screenshot "+e.getMessage()); 
			} 
		}
	} 

    public void skip_intro() throws InterruptedException {
			driver.navigate().back();
			Thread.sleep(1000);
			driver.navigate().back();
    }
 
    public static void env(String[] args) {
    	String s = System.getenv("test");
    	System.out.println(s);
	}
}
