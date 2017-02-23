/*******************************************************************************
 * Copyright (c) 2017 by QA Team of Wemakeprice. All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software and its documentation for
 * educational, research, and not-for-profit purposes, without fee and without a signed licensing agreement,
 * is hereby granted, provided that the above copyright notice appears in all copies, modifications, and distributions.
 *******************************************************************************/

package com.regression.buy;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.Test;

import com.regression.setup.Setup;

/**
* <pre>
* com.regression.buy
*   |_ Main.java
* </pre>
* 
* Desc : 결제 자동화 모듈
* @Company : Wemakeprice. Co.
* @Author  : June, Ahn
* @Date    : 2017. 2. 8. 오전 11:39:02
* @Version : 
*/
public class Main extends Setup {
	
	/**
	 * Desc : 결제 테스트 모듈 (신용카드, 무통장입금, 실시간계좌이체, 휴대폰결제, 간편결제, 포인트결제)
	 * @Method Name : card
	 */
	@Test
	public void main() throws Exception {
		card();
		mutongjang();
		realTime();
		cellPhone();
		simple();
		point();
	}
	
	/**
	 * Desc : 신용카드
	 * @Method Name : card
	 */
	public void card() throws Exception {
		CardObjects card = new CardObjects();

		// 19개 카드사
		String buyCom[] = {"신한카드", "BC카드", "KB국민카드", "삼성카드", "현대카드", "롯데카드", "하나카드", "외환카드", "NH채움카드", "씨티카드", "우리카드", "광주카드", 
						   "수협카드", "우체국카드", "저축은행", "전북카드", "제주카드", "KDB산업카드", "MG새마을카드"};
		
		System.out.println("[STAR PAYMENT TEST] 카드 결제");
		
		for (int i = 0; i<buyCom.length; i++){
			System.out.println("[CARD COMPANY] " + buyCom[i]);
			
			testName = "결제테스트";
			detailTest = "신용카드_" + buyCom[i];
			
			card.login();
			card.buy(buyCom[i]);
			card.validateion(buyCom[i]);

			driver.resetApp();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
	}
	
	/**
	 * Desc : 무통장입금
	 * @Method Name : mutongjang
	 */
	public void mutongjang() throws Exception {
		BankObjects bank = new BankObjects();
		
		// 15개 은행
		String bankCom[] = {"기업은행", "외환은행", "국민은행", "농협중앙회", "우리은행", "신한은행", "SC제일은행", "씨티은행", "대구은행", 
							"부산은행", "광주은행", "경남은행", "우체국", "KEB 하나은행", "수협중앙회"};
		
		System.out.println("[STAR PAYMENT TEST] 무통장입금 결제");
		
		for (int i = 0; i<bankCom.length; i++){
			System.out.println("[BANK NAME] " + bankCom[i]);
			
			testName = "결제테스트";
			detailTest = "무통장입금_" + bankCom[i];
			
			bank.login();
			bank.buy(bankCom[i]);
			bank.validateion(bankCom[i]);

			driver.resetApp();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
	}
	
	/**
	 * Desc : 실시간계좌이체
	 * @Method Name : realTime
	 */
	public void realTime() throws Exception {
		RealTimeObjects real = new RealTimeObjects();
		
		System.out.println("[STAR PAYMENT TEST] 실시간계좌이체 결제");
		
		testName = "결제테스트";
		detailTest = "실시간계좌이체";
		
		real.login();
		real.buy();
		real.validateion();
		
		driver.resetApp();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	/**
	 * Desc : 휴대폰 결제
	 * @Method Name : cellPhone
	 */	
	public void cellPhone() throws Exception {
		CellPhoneObjects phone = new CellPhoneObjects();
		
		// 5개 통신사
		String cell[] = {"SKT", "KT", "LG U+", "KCT", "CJH"};
		
		System.out.println("[STAR PAYMENT TEST] 휴대폰 결제");
		
		for (int i = 0; i<cell.length; i++){
			System.out.println("[AGENCY NAME] " + cell[i]);
			
			testName = "결제테스트";
			detailTest = "휴대폰결제_" + cell[i];
			
			phone.login();
			phone.buy(cell[i]);
			phone.validateion(cell[i]);

			driver.resetApp();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
	}
	
	/**
	 * Desc : 간편결제 
	 * @Method Name : simple
	 */	
	public void simple() throws Exception {
		SimpleObjects pay = new SimpleObjects();
		// 간편 결제 결제 수단
		String simple[] = {"페이나우", "페이코", "케이페이"};
		
		System.out.println("[STAR PAYMENT TEST] 간편 결제");
		
		for (int i = 0; i<simple.length; i++){
			System.out.println("[AGENCY NAME] " + simple[i]);
			
			testName = "결제테스트";
			detailTest = "간편결제_" + simple[i];
			
			pay.login();
			pay.buy(simple[i]);
			pay.validateion(simple[i]);

			driver.resetApp();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
	}
	
	/**
	 * Desc : 포인트 결제 
	 * @Method Name : point
	 */	
	public void point() throws Exception {
		PointObjects point = new PointObjects();
		
		// 포인트
		System.out.println("[STAR PAYMENT TEST] 포인트 결제");
		
		testName = "결제테스트";
		detailTest = "포인트";
		
		point.login();
		point.buy();
		point.validateion();
	}
	
}