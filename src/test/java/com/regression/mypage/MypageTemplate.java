/**
 * Copyright (c) 2017 by QA Team of Wemakeprice. All Rights Reserved.
 * 
 * Permission to use, copy, modify, and distribute this software and its documentation for
 * educational, research, and not-for-profit purposes, without fee and without a signed licensing agreement,
 * is hereby granted, provided that the above copyright notice appears in all copies, modifications, and distributions.
 */

package com.regression.mypage;

import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.seleniumhq.jetty9.http.HttpTester.Message;

import com.regression.Util.DBConnector;
import com.regression.setup.Setup;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

/**
* <pre>
* com.regression.mypage
*   |_ MypageTemplate.java
* </pre>
* 
* Desc : 
* @Company : Wemakeprice. Co.
* @Author  : June, Ahn
* @Date    : 2017. 2. 20. 오후 6:12:18
* @Version : 
*/
public class MypageTemplate extends Setup {
	WebElement inputField;
	
	public void btn_click(String name) {
			MobileElement btn = null;
		
		try {
			boolean case1 = driver.findElement(By.xpath("//UIAButton[@name='" + name + "']")).isEnabled();
			System.out.println(case1);
			if(case1 == true){
				btn = (MobileElement) driver.findElement(By.xpath("//UIAButton[@name='" + name + "']"));
				btn.click();
			}
			
		} catch (NoSuchElementException nse){
			System.out.println("not found");
		}
		
		try {
			boolean case2 = driver.findElement(By.xpath("//UIAStaticText[@name='" + name + "']")).isEnabled();
			System.out.println(case2);
			if(case2 == true){
				btn = (MobileElement) driver.findElement(By.xpath("//UIAButton[@name='" + name + "']"));
				btn.click();
			}
			
		} catch (NoSuchElementException nse){
			System.out.println("not found");
		}
		
		try {
			boolean case3 = driver.findElement(By.xpath("//UIAImage[@name='" + name + "']")).isEnabled();
			System.out.println(case3);
			if(case3 == true){
				btn = (MobileElement) driver.findElement(By.xpath("//UIAButton[@name='" + name + "']"));
				btn.click();
			}
			
		} catch (NoSuchElementException nse){
			System.out.println("not found");
		}
	}
	
	/**
	 * Desc : 팝업 처리
	 * @Method Name : popup
	 */
	public void popup() {
		MobileElement body_message = (MobileElement)driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]");
		if (String.valueOf(body_message.getAttribute("name")).equals("다시 보지 않기")) {
			driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").click();
		}
	}
	
	/**
	 * Desc : 로그인 유무 확인
	 * @Method Name : login
	 */
	public void login() throws InterruptedException {
		popup(); // 팝업 확인
		
		if(login == 0){
			System.out.println("[STEP 1] 로그아웃 상태 > 로그인 시작");
			
			// 장바구니 버튼 클릭
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[4]")).click(); // 장바구니 버튼 선택
			
			// 아이디 입력 
			inputField = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[1]"));
			inputField.click();
			
			if(driver.findElement(MobileBy.xpath("//UIAApplication[1]/UIAWindow[4]/UIAKeyboard[1]/UIAKey[1]")).getText().matches("ㅂ")){
				driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[4]/UIAKeyboard[1]/UIAButton[2]")).click(); // 영어로 전환
			} else if (driver.findElement(MobileBy.xpath("//UIAApplication[1]/UIAWindow[4]/UIAKeyboard[1]/UIAKey[1]")).getText().matches("1")){
				driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[4]/UIAKeyboard[1]/UIAButton[2]")).click(); // 영어로 전환
			}
			
			// Input Login ID
			inputField.sendKeys("INPUT LOGIN ID");
			
			// 비밀번호 입력 
			inputField = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIASecureTextField[1]"));
			inputField.click();
			// Input Login PW
			inputField.sendKeys("INPUT LOGIN PW");
			
			// 로그인 버튼 선택 
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAButton[1]")).click();
			
			// 이전 버튼 선택 (마이페이지로 이동)
			driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth back black']")).click();
		} else {
			System.out.println("[STEP 1] 로그인 상태");
		}
	}
	
	/**
	 * Desc : 마이페이지 메시지 확인  
	 * @Method Name : message
	 */	
	public void message() throws Exception {
		String message = null;
		System.out.println("[START TEST] 마이페이지 Text message 확인");
		
		testName = "마이페이지";
		detailTest = "Text Message 확인";
		
		// Message 노출 확인 (ex : 안준님 환영합니다.)
		message = driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIATableView/UIATableCell/UIAStaticText[@name='안준님 환영합니다.']")).getText();
		if (message.equals("안준님 환영합니다.")){
			System.out.println("환영 Message : pass");
			
			// 배송중인 상품 확인 1개 
			message = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[3]")).getText();
			if (message.equals("1")){
				System.out.println("배송중인 상품 : pass");
				
				// 배송중인 상품 확인 1개 
				message = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[4]")).getText();
				if (message.equals("1")){
					System.out.println("사용 가능 티켓 : pass");
					DBConnector.dbPassUpdate(testName, detailTest);
				} else {
					System.out.println("사용 가능 티켓 : fail");
					DBConnector.dbFailUpdate(testName, detailTest);
				}
			} else {
				System.out.println("배송중인 상품 : fail");
				DBConnector.dbFailUpdate(testName, detailTest);
			}
		} else {
			System.out.println("환영 Message : fail");
			DBConnector.dbFailUpdate(testName, detailTest);
		}
		
		
	}
	
	/**
	 * Desc : 마이페이지 > 검색   
	 * @Method Name : search
	 */	
	public void search() throws Exception {
		testName = "마이페이지";
		detailTest = "검색";
		
		// 검색 버튼 선택
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth magnifier black']")).click();
		
		if(driver.manage().logs().get("syslog").getAll().indexOf("search_top") != 0){
			System.out.println("검색 : pass");
			DBConnector.dbPassUpdate(testName, detailTest);
		} else {
			System.out.println("검색 : fail");
			DBConnector.dbFailUpdate(testName, detailTest);
		}
		
		// 이전 버튼 선택 (마이페이지로 이동)
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAButton[@name='btn search back']")).click();
	}
	
	/**
	 * Desc : 마이페이지 > 정보수정  
	 * @Method Name : information
	 */	
	public void information() throws Exception {
		testName = "마이페이지";
		detailTest = "회원정보 확인";
		
		// 정보수정 버튼 선택
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIATableView/UIATableCell/UIAButton[@name='정보수정']")).click();
		
		String message = driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAStaticText[@name='회원정보 확인']")).getText();
		if(message.equals("회원정보 확인")){
			System.out.println("Step 1 : pass");
		} else {
			System.out.println("Step 2 : fail");
		}
		
		// 비밀번호 입력 
		inputField = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIASecureTextField[1]"));
		inputField.click();
		inputField.sendKeys("890iop");

		// 키패드 닫기 
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAToolbar/UIAButton[@name='완료']")).click();
		
		// 확인 버튼 선택 
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAButton[@name='확인']")).click();
		
		message = driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIATextField[@value='안준']")).getText();
		if(message.equals("안준")){
			System.out.println("Step2 : pass");
			DBConnector.dbPassUpdate(testName, detailTest);
		} else {
			System.out.println("Step2 : fail");
			DBConnector.dbFailUpdate(testName, detailTest);
		}
		
		// 이전 버튼 선택 (마이페이지로 이동)
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth back black']")).click();
	}	
	
	/**
	 * Desc : 마이페이지 > 알림 
	 * @Method Name : notice
	 */		
	public void notice() throws Exception{
		testName = "마이페이지";
		detailTest = "알림";
		
		// 알림 버튼 선택 
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIATableView/UIATableCell/UIAButton[@name='mypageBtnPushlist']")).click();

		String message = driver.manage().logs().get("syslog").getAll().toString();
		
		String idx1 = "http://mapi.wemakeprice.com/app/api";
		String idx2 = "version=4.9.9&os=iphone";
		
		int firidx = message.indexOf(idx1);
		int lastidx = message.lastIndexOf(idx2);
		
		String path = (message.substring(firidx, lastidx) + idx2).toString();
		
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
 
        int code = connection.getResponseCode();
        if (code!=400) {
        	System.out.println("http Status : pass");
        	DBConnector.dbPassUpdate(testName, detailTest);
        } else {
        	System.out.println("http Status : fail");
        	DBConnector.dbFailUpdate(testName, detailTest);
        }
        
		// 이전 버튼 선택 (마이페이지로 이동)
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth back black']")).click();
	}
	
	/**
	 * Desc : 마이페이지 > 할인쿠폰 
	 * @Method Name : coupon
	 */		
	public void coupon() throws Exception{
		testName = "마이페이지";
		detailTest = "할인쿠폰";
		
		// 할인쿠폰 선택
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIATableView/UIATableCell/UIAStaticText[@name='할인쿠폰']")).click();
		
		String messgae = driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAStaticText[@name='사용 가능한 할인쿠폰이 없습니다.']")).getText();
		if (messgae.equals("사용 가능한 할인쿠폰이 없습니다.")){
			System.out.println("Step : pass");
			DBConnector.dbPassUpdate(testName, detailTest);
		} else {
			System.out.println("Step : fail");
			DBConnector.dbFailUpdate(testName, detailTest);
		}
		
		// 이전 버튼 선택 (마이페이지로 이동)
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth back black']")).click();
	}
	
	/**
	 * Desc : 마이페이지 > 혜택 
	 * @Method Name : benefit
	 */		
	public void benefit() throws Exception{
		testName = "마이페이지";
		detailTest = "혜택";
		
		// 혜텍 버튼 선택
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIATableView/UIATableCell/UIAStaticText[@name='혜택']")).click();
		
		System.out.println(driver.manage().logs().get("syslog").getAll());
		
		if(driver.manage().logs().get("syslog").getAll().indexOf("benefitzone") != 0){
			// 검증 완료
			System.out.println("Step : Pass");	
			DBConnector.dbPassUpdate(testName, detailTest);
		} else {
			System.out.println("Step : Fail");
			DBConnector.dbFailUpdate(testName, detailTest);
		}
		
		// 마이페이지로 이동
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[5]")).click();
	}
	
	/**
	 * Desc : 마이페이지 > 배송중인상품 
	 * @Method Name : shipping
	 */		
	public void shipping() throws Exception{
		testName = "마이페이지";
		detailTest = "배송중인 상품";
		
		String message = null;
		
		// 배송중인 상품 선택
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIATableView/UIATableCell/UIAStaticText[@name='배송중인 상품']")).click();
			
		// 구매상품 탭 //
			// Message 노출 확인 (ex : 배송중)
			message = driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAScrollView/UIAWebView/UIALink/UIALink/UIALink/UIAStaticText[@name='배송중']")).getText();
			if (message.equals("배송중")){
				System.out.println("배송중 메시지 : pass");
				DBConnector.dbPassUpdate(testName, detailTest);
			} else {
				System.out.println("배송중 메시지 : fail");
				DBConnector.dbFailUpdate(testName, detailTest);
			}
			
			// 구매목록 > 구매 상세로 진입
			driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAScrollView/UIAWebView/UIALink/UIALink/UIAImage[@name='qa오테스트0120']")).click();
			
			// 상세보기 페이지 > 딜상세로 이동
			driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIALink/UIAStaticText[@name='상세보기']")).click();
			
			// 딜상세 > 이전 버튼 선택 
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")).click();
			
			// 구매상세 > 이전 버튼 선택 
			driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth back black']")).click();
			
			// 1개월 버튼 선택 
			driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAScrollView/UIAWebView/UIALink/UIAStaticText[@name='1개월']")).click();
			
			// 3개월 버튼 선택
			driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAScrollView/UIAWebView/UIALink/UIAStaticText[@name='3개월']")).click();
			
			// 15일 버튼 선택
			driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAScrollView/UIAWebView/UIALink/UIAStaticText[@name='15일']")).click();
			
		// 구매티켓 탭 //
			// 구매티켓 탭 선택
			driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAButton[@name='구매티켓']")).click();
			
			// Message 노출 확인 (ex : 사용가능 1매)
			message = driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAScrollView/UIAWebView/UIALink/UIALink/UIALink/UIAStaticText[@name='사용가능 1매']")).getText();
			if (message.equals("사용가능 1매")){
				System.out.println("티켓 메시지 : pass");
				DBConnector.dbPassUpdate(testName, detailTest);
			} else {
				System.out.println("티켓 메시지 : fail");
				DBConnector.dbFailUpdate(testName, detailTest);
			}
			
			// 상세보기 페이지로 이동 
			driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAScrollView/UIAWebView/UIALink/UIALink/UIAImage[@name='test구매금지']")).click();
			
			// 상세보기 페이지 > 딜상세로 이동
			driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIALink/UIAStaticText[@name='상세보기']")).click();
			
			// 딜상세 > 이전 버튼 선택 
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")).click();
			
			// 구매상세 > 이전 버튼 선택 
			driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth back black']")).click();
			
			// 전체 티켓 버튼 선택
			driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAScrollView/UIAWebView/UIALink/UIAStaticText[@name='전체 티켓']")).click();
			
		// 취소/교환반품 탭 //
			// 취소/교환반품 탭
			driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAButton[@name='취소·교환·반품']")).click();
			
			// 취소목록 > 구매 상세로 진입
			driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAScrollView/UIAWebView/UIALink/UIALink/UIAImage[@name='qa오테스트0120']")).click();
			
			
			// Message 노출 확인 (ex : 취소완료)
			message = driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAStaticText[@name='취소완료']")).getText();
			if (message.equals("취소완료")){
				System.out.println("티켓 메시지 : pass");
				DBConnector.dbPassUpdate(testName, detailTest);
			} else {
				System.out.println("티켓 메시지 : fail");
				DBConnector.dbFailUpdate(testName, detailTest);
			}
			
			// 이전 버튼 선택 
			driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth back black']")).click();
			
			// 취소목록 > 이전 신천내역 선택 
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[2]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")).click();
		
		// 이전 버튼 선택 (마이페이지로 이동)
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth back black']")).click();
	}
	
	/**
	 * Desc : 마이페이지 > 사용가능 티켓  
	 * @Method Name : ticket
	 */		
	public void ticket() throws Exception{
		// 사용가능 티켓 선택
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIATableView/UIATableCell/UIAStaticText[@name='사용가능 티켓']")).click();
		
		// 이전 버튼 선택 (마이페이지로 이동)
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth back black']")).click();
	}
	
	/**
	 * Desc : 마이페이지 > 구매 목록   
	 * @Method Name : buylist
	 */		
	public void buylist() throws Exception{
		// 구매목록 선택
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIATableView/UIATableCell[@name='구매목록']")).click();
		
		// 이전 버튼 선택 (마이페이지로 이동)
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth back black']")).click();
	}

	/**
	 * Desc : 마이페이지 > 취소 교환 반품 내역    
	 * @Method Name : buyback
	 */		
	public void buyback() throws Exception{
		// 취소교환반품 선택
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIATableView/UIATableCell[@name='취소·교환·반품 내역']")).click();
		
		// 이전 버튼 선택 (마이페이지로 이동)
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth back black']")).click();
	}
	
	/**
	 * Desc : 마이페이지 > 미사용 티켓     
	 * @Method Name : notusing
	 */		
	public void notusing() throws Exception{
		testName = "마이페이지";
		detailTest = "미사용 티켓";
		
		// 미사용티켓  선택
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIATableView/UIATableCell[@name='미사용티켓 환불']")).click();
		
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAScrollView/UIAWebView/UIALink/UIAStaticText[@name='미사용티켓 환불안내']")).click();
		
		if(driver.manage().logs().get("syslog").getAll().indexOf("event/page") != 0){
			// 검증 완료
			System.out.println("[STEP 1] 포인트 : Pass");		
			DBConnector.dbPassUpdate(testName, detailTest);
		} else {
			System.out.println("[STEP 1] 포인트 : Fail");
			DBConnector.dbFailUpdate(testName, detailTest);
		}
		
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth close']")).click();
		
		// 미사용 티켓 환불 탭 선택
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAButton[@name='미사용티켓 환불내역']")).click();
		
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAScrollView/UIAWebView/UIALink/UIAStaticText[@name='미사용티켓 환불안내']")).click();
		
		if(driver.manage().logs().get("syslog").getAll().indexOf("event/page") != 0){
			// 검증 완료
			System.out.println("[STEP 1] 포인트 : Pass");		
			DBConnector.dbPassUpdate(testName, detailTest);
		} else {
			System.out.println("[STEP 1] 포인트 : Fail");
			DBConnector.dbFailUpdate(testName, detailTest);
		}
		
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth close']")).click();
		
		// 이전 버튼 선택 (마이페이지로 이동)
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth back black']")).click();
	}
	
	/**
	 * Desc : 마이페이지 > 항공권      
	 * @Method Name : airport
	 */		
	public void airport() throws Exception{
		testName = "마이페이지";
		detailTest = "항공권";
		
		// 항공권 선택
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIATableView/UIATableCell[@name='항공권']")).click();
		
		Thread.sleep(3000);
		System.out.println(driver.manage().logs().get("syslog").getAll());
		
		if(driver.manage().logs().get("syslog").getAll().indexOf("airticket") != 0){
			// 검증 완료
			System.out.println("[STEP 1] 항공권 : Pass");		
			DBConnector.dbPassUpdate(testName, detailTest);
		} else {
			System.out.println("[STEP 1] 항공권 : Fail");
			DBConnector.dbFailUpdate(testName, detailTest);
		}
		
		// 이전 버튼 선택 (마이페이지로 이동)
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth close']")).click();
	}
	
	/**
	 * Desc : 마이페이지 > 찜한상품      
	 * @Method Name : airport
	 */		
	public void selcectDeal() throws Exception{
		testName = "마이페이지";
		detailTest = "찜한상품";
		
		String message = null;
		// 찜한상품 선택
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIATableView/UIATableCell[@name='찜한상품']")).click();
		
		// Message 노출 확인 (ex : 사용가능 1매)
		message = driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAStaticText[@name='체크한 상품 목록입니다.']")).getText();
		if (message.equals("체크한 상품 목록입니다.ᅢ")){
			System.out.println("찜한상품 메시지 : pass");
			DBConnector.dbPassUpdate(testName, detailTest);
		} else {
			System.out.println("찜한상품 메시지 : fail");
			DBConnector.dbFailUpdate(testName, detailTest);
		}
		
		// 찜한딜 선택 
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[1]")).click();
		
		// 딜상세 > 이전 페이지로 이동 
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")).click();
		
		// 이전 버튼 선택 (마이페이지로 이동)
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth back black']")).click();
	}	
	
	/**
	 * Desc : 마이페이지 > 최근본상품       
	 * @Method Name : recentlyDeal
	 */		
	public void recentlyDeal() throws Exception{
		// 최근본상품 선택
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIATableView/UIATableCell[@name='최근 본 상품']")).click();
		
		// 이전 버튼 선택 (마이페이지로 이동)
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth back black']")).click();
	}	

	/**
	 * Desc : 마이페이지 > 상품 Q&A       
	 * @Method Name : dealQA
	 */		
	public void dealQA() throws Exception{
		// 최근본상품 선택
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIATableView/UIATableCell[@name='상품 Q&A']")).click();
		
		// 이전 버튼 선택 (마이페이지로 이동)
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth back black']")).click();
	}
	
	/**
	 * Desc : 마이페이지 > 고객센터        
	 * @Method Name : serviceCenter
	 */		
	public void serviceCenter() throws Exception{
		// 최근본상품 선택
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIATableView/UIATableCell[@name='고객센터']")).click();
		
		// 이전 버튼 선택 (마이페이지로 이동)
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth back black']")).click();
	}
	
	/**
	 * Desc : 마이페이지 > 구매후기        
	 * @Method Name : purchasePostscript
	 */		
	public void purchasePostscript() throws Exception{
		// 구매후기 선택
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIATableView/UIATableCell[@name='구매후기']")).click();
		
		// 이전 버튼 선택 (마이페이지로 이동)
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth back black']")).click();
	}
	
	/**
	 * Desc : 마이페이지 > 환경설정        
	 * @Method Name : setting
	 */		
	public void setting() throws Exception{
		// 구매후기 선택
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIATableView/UIATableCell[@name='환경설정']")).click();
		
		// 이전 버튼 선택 (마이페이지로 이동)
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth back black']")).click();
	}
	
	/**
	 * Desc : 마이페이지 > 포인트 확인 
	 * @Method Name : point
	 */	
	public void point() throws Exception {
		testName = "마이페이지";
		detailTest = "포인트";
		
		// 포인트
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIATableView/UIATableCell/UIAStaticText[@name='포인트']")).click();
		
		if(driver.manage().logs().get("syslog").getAll().indexOf("point_list") != 0){
			// 검증 완료
			System.out.println("[STEP 1] 포인트 : Pass");		
			DBConnector.dbPassUpdate(testName, detailTest);
		} else {
			System.out.println("[STEP 1] 포인트 : Fail");
			DBConnector.dbFailUpdate(testName, detailTest);
		}
		
		// 이전 버튼 선택 (마이페이지로 이동)
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIANavigationBar/UIAButton[@name='btn 1depth back black']")).click();
	}
}
