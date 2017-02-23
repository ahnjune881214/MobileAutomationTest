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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.regression.setup.Setup;

/**
* <pre>
* com.regression.buy
*   |_ Card.java
* </pre>
* 
* Desc : 결제 Objects 
* @Company : Wemakeprice. Co.
* @Author  : June, Ahn
* @Date    : 2017. 2. 9. 오후 4:03:50
* @Version : 
*/

public class CardUtil extends CardObjects{
	WebDriverWait wait = new WebDriverWait(driver, 30);
	WebElement inputField;
	
	/**
	 * Desc : 신한카드
	 * @Method Name : shinhanCard
	 */
	public void shinhanCard() throws Exception {
		expectedOutput = "shinhan-sr-ansimclick://pay?srCode=";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIALink[@name='" + "결제하기" + "']")));
		
		// 신한카드 앱 > 가입하기
		Setup.driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIALink/UIAImage[@name='" + "결제하기" + "']")).click();
	}
	
	/**
	 * Desc : BC카드
	 * @Method Name : bcCard
	 */
	public void bcCard() throws Exception {
		expectedOutput = "bc____string";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView")));
		
		if(driver.findElements(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAButton[@name='사용안함']")).size() > 0){
			driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAButton[@name='사용안함']")).click();
			
		} else if (driver.findElements(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIALink[@name='사용안함']")).size() > 0){
			// 해당 결제 모듈 클릭 시점 못 찾음
			validation = expectedOutput;
			
		} else if (driver.findElements(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAStaticText[@name='사용안함']")).size() > 0){
			driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAStaticText[@name='사용안함']")).click();
		}
	}
	
	/**
	 * Desc : KB국민카드
	 * @Method Name : kbCard
	 */
	public void kbCard() throws Exception{
		expectedOutput = "CONNECTED";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIALink/UIAStaticText[@name='" + "결제하기" + "']")));
		
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIALink/UIAStaticText[@name='" + "결제하기" + "']")).click();
	}
	
	/**
	 * Desc : 삼성카드
	 * @Method Name : samsungCard
	 */
	public void samsungCard() {
		expectedOutput = "https://vbv.samsungcard.co.kr/VbV/mobile/MBITFX201.htm";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView")));
		
		if(driver.findElements(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAButton[@name='사용안함']")).size() > 0){
			driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAButton[@name='사용안함']")).click();
			
		} else if (driver.findElements(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIALink/UIALink/UIAStaticText[@name='사용안함']")).size() > 0){
			// 해당 결제 모듈 클릭 시점 못 찾음
			validation = expectedOutput;
			
		} else if (driver.findElements(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAStaticText[@name='사용안함']")).size() > 0){
			driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAStaticText[@name='사용안함']")).click();
		}
	}

	/**
	 * Desc : 현대카드
	 * @Method Name : hyundaiCard
	 */
	public void hyundaiCard() {
		expectedOutput = "https://ansimclick.hyundaicard.com/droidx/droidx_core.html";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView")));
		
		if(driver.findElements(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAButton[@name='사용안함']")).size() > 0){
			driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAButton[@name='사용안함']")).click();
			
		} else if (driver.findElements(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIALink/UIALink/UIAStaticText[@name='사용안함']")).size() > 0){
			// 해당 결제 모듈 클릭 시점 못 찾음
			validation = expectedOutput;
			
		} else if (driver.findElements(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAStaticText[@name='사용안함']")).size() > 0){
			driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAStaticText[@name='사용안함']")).click();
		}		
	}

	/**
	 * Desc : 롯데카드 
	 * @Method Name : lotteCard
	 */
	public void lotteCard() throws Exception{
		// 결제 모듈 이상으로 Expected output 못봄
		
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[1]/UIAImage[1]")).click();
	}

	/**
	 * Desc : 하나카드 
	 * @Method Name : hanaCard
	 */
	public void hanaCard() {
		expectedOutput = "Hana1QPay";
		
		//결제하기 버튼 선택
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIALink/UIAImage[@name='결제하기']")));
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIALink/UIAImage[@name='결제하기']")).click();
	}

	/**
	 * Desc : 외환카드
	 * @Method Name : yesCard
	 */
	public void yesCard() {
		expectedOutput = "Hana1QPay";
		
		//결제하기 버튼 선택
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIALink/UIAImage[@name='결제하기']")));
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIALink/UIAImage[@name='결제하기']")).click();
	}

	/**
	 * Desc : NH채움카드
	 * @Method Name : nhCard
	 */
	public void nhCard() {
		expectedOutput = "NHCardPay";
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAButton[1]")));
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAButton[1]")).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAButton[2]")));
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAButton[2]")).click();		
	}

	/**
	 * Desc : 씨티카드
	 * @Method Name : cityCard
	 */
	public void citiCard() {
		expectedOutput = "citimobile";
		
		// 결제하기 버튼 선택 
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[5]/UIAStaticText[1]")));
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[5]/UIAStaticText[1]")).click();
		
		// 앱실행/결제 인증 버튼 선택
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText[4]")));
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText[4]")).click();
		
//		// 씨티카드 모바일앱 열기 버튼 선택
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication[1]/UIAWindow[7]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAButton[1]")));
//		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[7]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAButton[1]")).click();
	}

	/**
	 * Desc : 우리카드
	 * @Method Name : wooriCard
	 */
	public void wooriCard() {
		expectedOutput = "MobileISP";
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView")));
		
		if(driver.findElements(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")).size() > 0){
			// 모바일 ISP 실행 버튼 선택
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")));
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")).click();
			
		} else if (driver.findElements(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAStaticText[@name='결제 진행하기']")).size() > 0){
			// 해당 결제 모듈 클릭 시점 못 찾음
			validation = expectedOutput;
			
		} 
	}

	/**
	 * Desc : 광주카드
	 * @Method Name : kjcard
	 */
	public void kjcard() {
		expectedOutput = "MobileISP";
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView")));
		
		if(driver.findElements(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")).size() > 0){
			// 모바일 ISP 실행 버튼 선택
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")));
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")).click();
			
		} else if (driver.findElements(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAStaticText[@name='결제 진행하기']")).size() > 0){
			// 해당 결제 모듈 클릭 시점 못 찾음
			validation = expectedOutput;
			
		} 
	}

	/**
	 * Desc : 수협카드
	 * @Method Name : suhyupCard
	 */
	public void suhyupCard() {
		expectedOutput = "MobileISP";
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView")));
		
		if(driver.findElements(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")).size() > 0){
			// 모바일 ISP 실행 버튼 선택
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")));
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")).click();
			
		} else if (driver.findElements(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAStaticText[@name='결제 진행하기']")).size() > 0){
			// 해당 결제 모듈 클릭 시점 못 찾음
			validation = expectedOutput;
			
		} 
	}

	/**
	 * Desc : 
	 * @Method Name : epostcard
	 */
	public void epostcard() throws Exception {
		expectedOutput = "MobileISP";
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView")));
		
		if(driver.findElements(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")).size() > 0){
			// 모바일 ISP 실행 버튼 선택
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")));
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")).click();
			
		} else if (driver.findElements(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAStaticText[@name='결제 진행하기']")).size() > 0){
			// 해당 결제 모듈 클릭 시점 못 찾음
			validation = expectedOutput;
			
		} 
	}

	/**
	 * Desc : 저축은행 
	 * @Method Name : savingCard
	 */
	public void savingCard() {
		expectedOutput = "MobileISP";
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView")));
		
		if(driver.findElements(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")).size() > 0){
			// 모바일 ISP 실행 버튼 선택
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")));
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")).click();
			
		} else if (driver.findElements(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAStaticText[@name='결제 진행하기']")).size() > 0){
			// 해당 결제 모듈 클릭 시점 못 찾음
			validation = expectedOutput;
		} 
	}

	/**
	 * Desc : 전북카드
	 * @Method Name : jbCard
	 */
	public void jbCard() {
		expectedOutput = "MobileISP";
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView")));
		
		if(driver.findElements(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")).size() > 0){
			// 모바일 ISP 실행 버튼 선택
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")));
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")).click();
			
		} else if (driver.findElements(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAStaticText[@name='결제 진행하기']")).size() > 0){
			// 해당 결제 모듈 클릭 시점 못 찾음
			validation = expectedOutput;
		}
	}

	/**
	 * Desc : 제주카드
	 * @Method Name : jejooCard
	 */
	public void jejooCard() {
		expectedOutput = "MobileISP";
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView")));
		
		if(driver.findElements(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")).size() > 0){
			// 모바일 ISP 실행 버튼 선택
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")));
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")).click();
			
		} else if (driver.findElements(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAStaticText[@name='결제 진행하기']")).size() > 0){
			// 해당 결제 모듈 클릭 시점 못 찾음
			validation = expectedOutput;
		}
	}

	/**
	 * Desc : KDB산업카드
	 * @Method Name : kdbCard
	 */
	public void kdbCard() {
		expectedOutput = "MobileISP";
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView")));
		
		if(driver.findElements(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")).size() > 0){
			// 모바일 ISP 실행 버튼 선택
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")));
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")).click();
			
		} else if (driver.findElements(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAStaticText[@name='결제 진행하기']")).size() > 0){
			// 해당 결제 모듈 클릭 시점 못 찾음
			validation = expectedOutput;
		}
	}

	/**
	 * Desc : MG새마을카드
	 * @Method Name : mgCard
	 */
	public void mgCard() {
		expectedOutput = "MobileISP";
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView")));
		
		if(driver.findElements(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")).size() > 0){
			// 모바일 ISP 실행 버튼 선택
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")));
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[2]/UIAStaticText[1]")).click();
			
		} else if (driver.findElements(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAStaticText[@name='결제 진행하기']")).size() > 0){
			// 해당 결제 모듈 클릭 시점 못 찾음
			validation = expectedOutput;
		}
	}
}
