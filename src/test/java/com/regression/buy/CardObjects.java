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
*   |_ PageObject.java
* </pre>
* 
* Desc : 
* @Company : Wemakeprice. Co.
* @Author  : June, Ahn
* @Date    : 2017. 2. 8. 오전 11:43:19
* @Version : 
*/
public class CardObjects extends Setup {
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
	public void buy(String cardCom) throws InterruptedException  {
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
		
		// 주문 / 결제하기 - 결제 방법 > 신용카드 선택
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAElement[@name='" + "신용카드" + "']")).click();
				
		// 카드선택 
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAElement[@value='" + "선택하세요" + "']")).click();
		
		
		System.out.println("[STEP 4] 카드사 선택 (" + cardCom + ")");
		
		// 신한카드 선택 (신한카드, BC카드, KB국민카드, 삼성카드, 현대카드, 롯데카드, 하나카드, 외환카드, NH채움카드, 씨티카드, 우리카드, 광주카드, 수협카드, 우체국카드, 저축은행, 전북카드, 제주카드, KDB산업카드, MG새마을카드) 
		inputField = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[4]/UIAPicker[1]/UIAPickerWheel[1]"));
		inputField.click();
		driver.findElementByClassName("UIAPickerWheel").sendKeys(cardCom);
		
		// 카드 선택 완료
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[@name='" + "완료" + "']")).click();
		
		// 동의 체크 _ 모두 동의 합니다. 
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIASwitch[@name='" + "모두 동의 합니다." + "']")).click();
		
		// 구매하기 버튼 선택
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAButton[@name='" + "구매하기" + "']")).click();
	}
	
	/**
	 * Desc : Expected output 확인
	 * @Method Name : shinhanCard
	 */
	public void validateion(String cardCom) throws Exception{
		CardUtil card = new CardUtil();
		
		Thread.sleep(3000);
		
		if (cardCom == "신한카드") {
			card.shinhanCard();
			
		} else if (cardCom == "BC카드") {
			card.bcCard();
			
		} else if (cardCom == "KB국민카드") {
			card.kbCard();
			
		} else if (cardCom == "삼성카드") {
			card.samsungCard();
			
		} else if (cardCom == "현대카드"){
			card.hyundaiCard();
			
		} else if (cardCom == "롯데카드"){
			card.lotteCard();
			
		} else if (cardCom == "하나카드"){
			card.hanaCard();
			
		} else if (cardCom == "외환카드"){
			card.yesCard();
			
		} else if (cardCom == "NH채움카드"){
			card.nhCard();
			
		} else if (cardCom == "씨티카드"){
			card.citiCard();
			
		} else if (cardCom == "우리카드"){
			card.wooriCard();
			
		} else if (cardCom == "광주카드"){
			card.kjcard();
			
		} else if (cardCom == "수협카드"){
			card.suhyupCard();
			
		} else if (cardCom == "우체국카드"){
			card.epostcard();
			
		} else if (cardCom == "저축은행"){
			card.savingCard();
			
		} else if (cardCom == "전북카드"){
			card.jbCard();
			
		} else if (cardCom == "제주카드"){
			card.jejooCard();
			
		} else if (cardCom == "KDB산업카드"){
			card.kdbCard();
			
		} else if (cardCom == "MG새마을카드"){
			card.mgCard();
		} 		
		
		Thread.sleep(5000);
		
		 if(driver.manage().logs().get("syslog").getAll().indexOf(expectedOutput) != 0){
			 validation = expectedOutput;
				
			// 검증 완료
			Assert.assertEquals(expectedOutput, validation);
			
			System.out.println("[STEP 5] " + cardCom + " : PASS");
			System.out.println("[RESULT] Expected output : " + expectedOutput + " | Validation : " + validation + "\n");
			
			DBConnector.dbPassUpdate(testName, detailTest);
		 } else if(driver.getPageSource().indexOf("실패") > 0){
		 } else {
			System.out.println("[STEP 5] 포인트 : Fail");
			
			DBConnector.dbFailUpdate(testName, detailTest);
		 }
		
		// validation 초기화 
		validation = null;
	}
}
