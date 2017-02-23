/**
 * Copyright (c) 2017 by QA Team of Wemakeprice. All Rights Reserved.
 * 
 * Permission to use, copy, modify, and distribute this software and its documentation for
 * educational, research, and not-for-profit purposes, without fee and without a signed licensing agreement,
 * is hereby granted, provided that the above copyright notice appears in all copies, modifications, and distributions.
 */

package com.regression.membership;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.regression.Util.DBConnector;
import com.regression.setup.Setup;

import io.appium.java_client.MobileElement;
import junit.framework.Assert;

public class MembershipTemplate extends Setup {
	String expectedOutput = null;
	
	WebElement inputField;
	MobileElement body_message;
	MobileElement btn;
	
	public void membershipButtonClick() throws InterruptedException {
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIALink/UIAStaticText[@name='회원가입']")).click(); // 로그인 > 회원가입 버튼
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAButton[1]")).click(); // 로그인 > 회원가입 버튼 > 개인 구매회원 버튼
	}
	
	public void membershipTemplate() throws InterruptedException {
//		long time = System.currentTimeMillis(); 
//		Date date = new Date();
		SimpleDateFormat dayTime = new SimpleDateFormat("MMddmmsss");
		String date = dayTime.format(new Date());
		
		// 이메일 
		inputField = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[1]"));
		inputField.click();
		inputField.sendKeys("wmpqa" + date + "@wmp.com");
//		System.out.println("Email Name : " + "wmp" + date + "@wmp.com");
		
		// 비밀번호 
		inputField = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIASecureTextField[1]"));
		inputField.click();
		inputField.sendKeys("enlwlffi@1");
		
		// 비밀번호 확인 
		inputField = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIASecureTextField[2]"));
		inputField.click();
		inputField.sendKeys("enlwlffi@1");
		
		// 이름 
		inputField = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[2]"));
		inputField.click();
		inputField.sendKeys("안준");
		
		// 생년 
		inputField = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAElement[2]"));
		inputField.click();
		driver.findElementByClassName("UIAPickerWheel").sendKeys("1988");
		
		// 월
		inputField = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAElement[3]"));
		inputField.click();
		driver.findElementByClassName("UIAPickerWheel").sendKeys("12");
		
		// 일
		inputField = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAElement[4]"));
		inputField.click();
		driver.findElementByClassName("UIAPickerWheel").sendKeys("14");
		
		// 휴대폰 번호
		inputField = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[3]"));
		inputField.click();
		inputField.sendKeys("01034298850");
		
		//키 패드닫기
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[3]")).click();
        
		// 인증번호 체크 로직 
		body_message = (MobileElement) driver.findElementByXPath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAStaticText[@name='이미 가입된 휴대폰 번호입니다.']");
		Assert.assertEquals("이미 가입된 휴대폰 번호입니다.", body_message.getAttribute("name"));
        
		if(String.valueOf(body_message.getAttribute("name")).equals("이미 가입된 휴대폰 번호입니다.")){
        	driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAButton[2]")).click();

        	// [팝업] 인증 번호 체크 로직
        	WebDriverWait wait = new WebDriverWait(driver, 3000);
    		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("UIAAlert")));
    		MobileElement body_message = (MobileElement)driver.findElementsByClassName("UIAStaticText").get(2);
    		String checkNum = body_message.getAttribute("name").replaceAll("[^0-9]", "");
    		
    		// [팝업] 인증번호 전송 팝업
    		MobileElement close_alert = (MobileElement)driver.findElementsByClassName("UIACollectionCell").get(0);
    		close_alert.click();
    		
    		// [팝업] 해당번호로 인증번호가 전송되었습니다.
    		close_alert = (MobileElement)driver.findElementsByClassName("UIAWebView").get(0);
    		close_alert.click();

    		// 인증 번호 수신 
    		inputField = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText[3]"));
    		inputField.click();
    		Thread.sleep(2000);
    		inputField = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[4]"));
    		inputField.sendKeys(checkNum);
    		Thread.sleep(2000);
    		
    		// 키 패드닫기
    		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[3]")).click();

    		// 인증 [확인] 버튼 선택 
    		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAButton[3]")).click();
    		
    		// 휴대폰 인증이 완료되었습니다. 
    		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAButton[5]")).click();
        }
		
		// 성별 선택 
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText[4]")).click();
		
		// 전체 동의     
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText[7]")).click();
		
		// 가입하기 버튼 선택
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAButton[4]")).click();
	}
	
	public void membershipVelidation() throws Exception{
		body_message = (MobileElement) driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText[1]");
		Assert.assertEquals("위메프 가입을 감사드립니다.", body_message.getAttribute("name"));
		
		// [쇼핑하기]버튼 클릭
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]")).click();
	}
	
	public void reStart(){
		// 재시작
		driver.resetApp();
		
		// 남성 선택 
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAButton[2]")).click();

		//출생선택
		inputField = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAPicker[1]/UIAPickerWheel[1]"));
		inputField.click();
		driver.findElementByClassName("UIAPickerWheel").sendKeys("1988");
		
		//UIAApplication[1]/UIAWindow[1]/UIAButton[1] // 선택 완료 
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")).click();
	}
	
	public void logout() throws Exception {
		Thread.sleep(3000);
		
		// 마이페이지 선택
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAButton[@name='bottom menu mypage nor']")).click(); 
		
		// 환경설정 선택
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[16]")).click(); 
		
		// 로그아웃
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAButton[1]")).click(); 
		
		// [팝업] 예
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[2]/UIAButton[1]")).click(); 
		
		// 마이페이지 > 뒤로가기  
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")).click();
		
		// 홈으로 
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")).click();
	}	
}
