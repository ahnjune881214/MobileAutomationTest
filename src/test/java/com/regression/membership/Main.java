/*******************************************************************************
 * Copyright (c) 2017 by QA Team of Wemakeprice. All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software and its documentation for
 * educational, research, and not-for-profit purposes, without fee and without a signed licensing agreement,
 * is hereby granted, provided that the above copyright notice appears in all copies, modifications, and distributions.
 *******************************************************************************/

package com.regression.membership;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.Test;

import com.regression.Util.DBConnector;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

/**
* <pre>
* com.regression.membership
*   |_ Main.java
* </pre>
* 
* Desc : 
* @Company : Wemakeprice. Co.
* @Author  : June, Ahn
* @Date    : 2017. 2. 6. 오후 3:53:13
* @Version : 
*/
public class Main extends MembershipTemplate{
	WebElement inputField;

	@Test
	public void main() throws Exception{
		String membership[] = {"즉시구매", "구매후기", "찜하기", "장바구니"};
		
		System.out.println("[STAR TEST] 회원가입");
		
		for (int i = 0; i < membership.length; i++){
			login(); // 로그인 유무 확인
			
			if (membership[i].equals("즉시구매")){
				testName = "회원가입";
				detailTest = "즉시구매_" + membership[i].toString();
				
				detailDeal();
				buyDeal();
			} else if (membership[i].equals("구매후기")){
				testName = "회원가입";
				detailTest = "구매후기_" + membership[i].toString();
				
				detailDeal();
				buyPostscript();
			} else if (membership[i].equals("찜하기")){
				testName = "회원가입";
				detailTest = "찜하기_" + membership[i].toString();
				
				detailDeal();
				selectDeal();
			} else if (membership[i].equals("장바구니")){
				testName = "회원가입";
				detailTest = "회원가입_" + membership[i].toString();
				
				cart();
			}
			
			mixMembership();
			
			expectedOutput = "성공";
			if(driver.manage().logs().get("syslog").getAll().indexOf(expectedOutput) != 0){
				System.out.println("[RESULT] PASS");
				DBConnector.dbPassUpdate(testName, detailTest);
			} else {
				System.out.println("[RESULT] FAIL");
				DBConnector.dbFailUpdate(testName, detailTest);
			}
			
			System.out.println("[RESULT] 회원가입 완료\n");
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
		// 로그인 상태 유무 확인
		for (LogEntry logEntry : driver.manage().logs().get("syslog").getAll()){
			if (logEntry.getMessage().contains("email =")){
				login = 1;
			}
		}
		
		popup(); // 팝업 확인
		
		if(login == 0){
			System.out.println("[STEP 1] 로그아웃 상태");

		} else {
			System.out.println("[STEP 1] 로그인 상태 > 로그아웃");
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[5]")).click(); // 마이페이지 선택

			driver.scrollTo("환경설정");
			Thread.sleep(1000);
			
			driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIATableView/UIATableCell/UIAStaticText[@name='환경설정']")).click(); // 환경설정 선택
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAButton[1]")).click(); // 로그아웃 버튼 선택
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[2]/UIAButton[1]")).click(); //로그아웃 팝업 예 버튼 선택
			driver.resetApp();
			
			popup(); // 팝업 확인
		}
	}
	
	/**
	 * Desc : 홈 > 딜선택 > 딜상세 입장 
	 * @Method Name : dealSearch
	 */
	@Test
	public void detailDeal() {
		// 최근 본 상품 선택
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[6]")).click();
		
		// 최근 본 상품 > 딜 상세로 이동 
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIACollectionCell[1]")).click();
	}
	
	/**
	 * Desc : 즉시 구매 선택 
	 * @throws InterruptedException 
	 * @Method Name : buyDeal
	 */
	public void buyDeal() throws InterruptedException {
		System.out.println("[STEP 2] 회원가입 방법 : 즉시구매");
		Thread.sleep(3000);
		
		// 딜상세 > 즉시구매 선택 
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[10]")).click();
		
		// 즉시구매 선택
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[8]")).click();
	}
	
	/**
	 * Desc : 딜상세 > 구매후기 작성하기 선택 
	 * @Method Name : buyPostscript
	 */
	public void buyPostscript() {
		System.out.println("[STEP 2] 회원가입 방법 : 구매후기");
		//구매후기 작성하기 선택
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIAButton[5]")).click();
	}
	/**
	 * Desc : 딜상세 > 찜하기 
	 * @Method Name : buyPostscript
	 */
	// 딜상세 > 찜하기 선택
	public void selectDeal() {
		System.out.println("[STEP 2] 회원가입 방법 : 찜하기");
		
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAButton[@name='btn bottom like nor']")).click();
	}

	/**
	 * Desc : 장바구니 선택 
	 * @Method Name : cart
	 * @throws InterruptedException
	 */
	public void cart() throws InterruptedException {
		System.out.println("[STEP 2] 회원가입 방법 : 장바구니");
		
		// 장바구니 (개별 플로우) 
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAButton[@name='bottom menu basket nor']")).click(); // 장바구니 버튼 선택

	}
	
	/**
	 * Desc : 공통 플로우 (로그인 부터 회원가입, 로그아웃)  
	 * @throws Exception 
	 * @Method Name : mixMembership
	 */
	public void mixMembership() throws Exception {
		System.out.println("[STEP 3] 회원가입 양식 작성");
		//공통 플로우
		membershipButtonClick(); // 로그db인 페이지 > 회원가입 버튼 선택 
		membershipTemplate(); // 회원가입 신청서 작성
		membershipVelidation(); // 회원가입 완료 > 쇼핑하기 버튼 클릭
		reStart(); // Restart 후 초기 셋팅 (성별, 생년월일 선택)
		System.out.println("[STEP 4] 회원가입 완료");
		logout(); // 로그아웃
		System.out.println("[STEP 5] 로그아웃");
	}
}