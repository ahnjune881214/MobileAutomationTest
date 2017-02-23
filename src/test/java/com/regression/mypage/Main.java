/**
 * Copyright (c) 2017 by QA Team of Wemakeprice. All Rights Reserved.
 * 
 * Permission to use, copy, modify, and distribute this software and its documentation for
 * educational, research, and not-for-profit purposes, without fee and without a signed licensing agreement,
 * is hereby granted, provided that the above copyright notice appears in all copies, modifications, and distributions.
 */

package com.regression.mypage;

import java.net.URL;

import org.apache.bcel.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import com.regression.Util.DBConnector;

import io.appium.java_client.ios.IOSDriver;

/**
* <pre>
* com.regression.mypage
*   |_ Main.java
* </pre>
* 
* Desc : 
* @Company : Wemakeprice. Co.
* @Author  : June, Ahn
* @Date    : 2017. 2. 20. 오후 6:09:51
* @Version : 
*/
public class Main extends MypageTemplate{
	
	@Test
	public void main() throws Exception {
		// 로그인 유무 확인
		login();
		
		// 마이페이지 선택
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[5]")).click();
		
		// Message 노출 확인
		message();
		
		// 검색 
		search();
		
		// 정보수정
		information();
		
		// 알림 
		notice();
		
		// 포인트
		point();
		
		// 할인쿠폰
		coupon();
		
		// 혜택 (쿠폰 받기)
		benefit();
		
		// 배송중인 상품
		shipping();
		
		// 사용가능 티켓 
		ticket();
		
		// 구매목록 
		buylist();
		
		// 취소/교환반품 내역
		buyback();
		
		// 미사용 티켓 환불
		notusing();
		
		// 항공권
		airport();
		
		// 찜한상품
		selcectDeal();
		
		// 최근 본 상품 
		recentlyDeal();
		
		// 상품 Q&A
		dealQA();
		
		// 고객센터
		serviceCenter();
		
		// 구매후기
		purchasePostscript();
		
		// 환경설정
		setting();
		
	}
	
}
