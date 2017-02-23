/**
 * Copyright (c) 2017 by QA Team of Wemakeprice. All Rights Reserved.
 * 
 * Permission to use, copy, modify, and distribute this software and its documentation for
 * educational, research, and not-for-profit purposes, without fee and without a signed licensing agreement,
 * is hereby granted, provided that the above copyright notice appears in all copies, modifications, and distributions.
 */

package com.regression.setup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.collections.Maps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public class PageObject {

	long startTime;
	String resultTime, captureName, categoryName, elementName;
	DateFormat dateFormat;
	Date date = new Date();
	DateFormat cvsDirectoryName = new SimpleDateFormat("yyyy_MM_dd");
	String cvsFileName = cvsDirectoryName.format(date).toString();
	File cvsHomeDirectory = new File("./Result/" + cvsFileName + "/" + cvsFileName);

	protected static AppiumDriver driver;

	public void RestartSpeedCkeck() throws InterruptedException, IOException {
		System.out.println("[START] Application ReStart");
		driver.resetApp();
		
		startTime = System.currentTimeMillis();
		wait("UIAButton");
		System.out.printf("[DONE] Application ReStart Time.(%s sec)\n", getElapsedTimeSting(startTime));
		resultTime = getElapsedTimeSting(startTime);
		
		captureName = new Object(){}.getClass().getEnclosingMethod().getName();
		
		String source_main = driver.getPageSource();
		if(source_main.contains("다시 보지 않기")) {
			btn_click(1);
		}
	}
	
	public void appRestart(){
		driver.resetApp();
	}
	
	@SuppressWarnings("all")
	public void csvWriter(String getText) throws IOException {
		
		DateFormat directoryName = new SimpleDateFormat("yyyy_MM_dd");
		String fileName = directoryName.format(date).toString();
		
		File homeDirectory = new File("./Result/");
		File newDirectory = new File(homeDirectory, fileName);
		
		if (!newDirectory.exists()) {
			boolean result = newDirectory.mkdir();
			if (result) {
//				System.out.println("The directory is created !");
			}
		} else {
//			System.out.println("The directory already exist");
		}
		
		FileWriter writer = new FileWriter(cvsHomeDirectory + ".csv", true);
				
		writer.append(getText + ",");

		writer.flush();
		writer.close();
	}
	
	@SuppressWarnings("all")
	public static void screenCapture(String captureName) throws IOException {
		TimeZone tz;
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		DateFormat directoryName = new SimpleDateFormat("yyyy_MM_dd");
		tz = TimeZone.getTimeZone("Asia/Seoul");
		df.setTimeZone(tz);

		try{
			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String fileName = directoryName.format(date).toString();
			createDirectory(fileName); // create directory
			FileUtils.copyFile(file,
					new File("./Result/" + fileName + "/" + captureName + "_" + df.format(date).toString() + ".png"));
			
		} catch (IOException e1){
			e1.printStackTrace();
		}
	}
	
	@SuppressWarnings("all")
	private static void createDirectory(String directoryName) throws IOException {
		final File homeDirectory = new File("./Result/");
		final File newDirectory = new File(homeDirectory, directoryName);
		if (!newDirectory.exists()) {
			boolean result = newDirectory.mkdir();
			if (result) {
//				System.out.println("The directory is created !");
			}
		} else {
//			System.out.println("The directory already exist");
		}
	}

	public static String getElapsedTimeSting(long startTime) {
		long elapsedTime = System.currentTimeMillis() - startTime;
		String elpsedTimeString = (elapsedTime / 1000) + "." + (elapsedTime % 1000);
		return elpsedTimeString;
	}

	public static void wait(String classname) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className(classname)));
	}
	
	public static void waitXpath(String xpath) throws InterruptedException {
//		WebDriverWait wait = new WebDriverWait(driver, 30);
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		Thread.sleep(3000);
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

	}

	public void category_click(String element1, int index1, String element2, int index2) throws InterruptedException {
		MobileElement table = (MobileElement) driver.findElementsByClassName(element1).get(index1);
		String category_name = table.getAttribute("name");
		driver.scrollToExact(category_name);
		MobileElement row = table.findElementsByClassName(element2).get(index2);

		row.click();
		// row.getAttribute("name").toString();
	}

	public void btn_click(String name) {
		MobileElement btn = (MobileElement) driver.findElement(By.xpath("//UIAButton[@name='" + name + "']"));
		btn.click();
	}

	public static void btn_click(int x) {
		final MobileElement btn = (MobileElement) driver.findElementsByClassName("UIAButton").get(x);
		btn.click();
	}

	public void menu_click(int x) {
		MobileElement table2 = (MobileElement) driver.findElementsByClassName("UIAWindow");
		MobileElement row = table2.findElementsByClassName("UIAButton").get(x);
		row.click();
	}
	
	public void btClick(String name){
		MobileElement btn = (MobileElement) driver.findElement(By.xpath("'"+name+"'"));
		btn.click();
	}

	private final void sorting_menu() {
		MobileElement sorting_menu = (MobileElement) driver.findElement(By.xpath(
				"//UIAApplication[1]/UIAWindow[1]/UIAScrollView[2]/UIATableView[1]/UIATableCell[1]/UIAButton[2]"));
		sorting_menu.click();
	}
	
	private final void sorting_item(int k) {
		MobileElement sorting_item = (MobileElement) driver
				.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[2]/UIAButton[" + k + "]"));
		sorting_item.click();
	}

	private void alert(String depth) {
		final String alert = driver.getPageSource();
		if (alert.contains("네트워크 오류가 발생했습니다.")) {
			System.out.println(depth + " :: Alert - 네트워크 오류가 발생했습니다");
			MobileElement confirm = (MobileElement) driver.findElementsByClassName("UIACollectionCell").get(0);
			confirm.click();
		}
	}

	public void appIndicator(){
		MobileElement getAtturibute = (MobileElement) driver.findElementByClassName("UIAActivityIndicator");
		String Attribute = getAtturibute.getAttribute("name");
		MobileElement ActivityIndicator = (MobileElement)driver.findElementByClassName("UIAActivityIndicator");
		
		while ( ! ActivityIndicator.getAttribute("name").equals(Attribute));
	}
}
