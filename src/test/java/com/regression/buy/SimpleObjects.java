/*******************************************************************************
 * Copyright (c) 2017 by QA Team of Wemakeprice. All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software and its documentation for
 * educational, research, and not-for-profit purposes, without fee and without a signed licensing agreement,
 * is hereby granted, provided that the above copyright notice appears in all copies, modifications, and distributions.
 *******************************************************************************/

package com.regression.buy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.regression.Util.DBConnector;
import com.regression.setup.Setup;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

/**
* <pre>
* com.regression.buy
*   |_ SimpleObjects.java
* </pre>
* 
* Desc : 
* @Company : Wemakeprice. Co.
* @Author  : June, Ahn
* @Date    : 2017. 2. 14. 오후 5:53:07
* @Version : 
*/
public class SimpleObjects extends Setup {
	public static String expectedOutput = null;
	public static String validation = null; 
	
	WebElement inputField;
	
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
			
			inputField.sendKeys("qawmp_pc61@wemakeprice.com");
			
			// 비밀번호 입력 
			inputField = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIASecureTextField[1]"));
			inputField.click();
			inputField.sendKeys("890iop");
			
			// 로그인 버튼 선택 
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAButton[1]")).click();
		} else {
			System.out.println("[STEP 1] 로그인 상태");
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[4]")).click(); // 장바구니 버튼 선택
		}
	}
	
	/**
	 * Desc : 결제 로직
	 * @throws InterruptedException 
	 * @Method Name : buy
	 */
	public void buy(String simple) throws InterruptedException  {
		System.out.println("[STEP 2] 상품 구매 시작");
		
		Thread.sleep(5000);
		
		if(driver.getPageSource().indexOf("장바구니에 담은 상품이 없습니다.") > 0){
			System.out.println("[STEP 3] 장바구니 딜 확인 > 딜 없음 > 200원 딜 가져오기");
			
			// 장바구니 > 뒤로가기 선택
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")).click();
			
			// 마이페이지 버튼 선택
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[5]")).click();
			
			// 마이페이지 > 찜한상품
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[10]")).click();
			
			// 마이페이지 > 찜한상품 > 200원 딜 선택 
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[1]")).click();
			
			// 200원 딜상세 > 구매하기
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[10]")).click();
			
			// 200언 딜상세 > 장바구니 담기
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[7]")).click();
			
			// 장바구니로 이동 
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[3]")).click();
		} else {
			System.out.println("[STEP 3] 장바구니 딜 확인 > 딜 존제");
		}
		
		// 장바구니 > 테스트 딜선택
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[1]")).click();
		
		// 즉시구매 버튼 선택 
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[@name='" + "즉시구매" + "']")).click();
		
		// 주문 / 결제하기 - 결제 방법 > 간편결제 선택
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAElement[9]")).click();
				
		
		System.out.println("[STEP 4] 간편결제 선택 (" + simple + ")");
		
		// 간편결제 선택 (페이나우, 페이코, 케이페이) 
		inputField = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[4]/UIAPicker[1]/UIAPickerWheel[1]"));
		inputField.click();
		driver.findElementByClassName("UIAPickerWheel").sendKeys(simple);
		
		// 카드 선택 완료
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[@name='" + "완료" + "']")).click();
		
		// 동의 체크 _ 모두 동의 합니다. 
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIASwitch[@name='" + "모두 동의 합니다." + "']")).click();
		
		// 구매하기 버튼 선택
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAButton[@name='" + "구매하기" + "']")).click();
		
		Thread.sleep(5000);
		
	}
	
	/**
	 * Desc : validation check
	 * @Method Name : validateion
	 */
	public void validateion(String simple) throws Exception{
		if (simple == "페이나우") {
			// 페이나우 결제 > 다음
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[3]")).click();
			expectedOutput = "Application";
		} else if (simple == "페이코") {
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAButton[2]")).click();
			expectedOutput = "paycoapplogin";
		} else if (simple == "케이페이") {
			System.out.println("test");
			// PG사에 테스트 딜 요청 해야함 1676150
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText[15]")).click();
			expectedOutput = "paynow";
			
		} 	
		
		Thread.sleep(5000);
		
//		System.out.println(driver.manage().logs().get("syslog").getAll());
		
		 if(driver.manage().logs().get("syslog").getAll().indexOf(expectedOutput) != 0){
			 validation = expectedOutput;
				
			// 검증 완료
			Assert.assertEquals(expectedOutput, validation);
			System.out.println("[STEP 5] " + simple + " : PASS");
			System.out.println("[RESULT] Expected output : " + expectedOutput + " | Validation : " + validation + "\n");
			
			DBConnector.dbPassUpdate(testName, detailTest);
		 } else if(driver.getPageSource().indexOf("실패") > 0){
			System.out.println("[STEP 5] 포인트 : Fail");
			
			DBConnector.dbFailUpdate(testName, detailTest);
		 }

		 // validation 초기화 
		validation = null;
	}
}
