/**
 * 
 */
package com.regression.buy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.regression.Util.DBConnector;
import com.regression.setup.Setup;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

/**
* <pre>
* com.regression.buy
*   |_ BankPageObjects.java
* </pre>
* 
* Desc : 
* @Company : Wemakeprice. Co.
* @Author  : June, Ahn
* @Date    : 2017. 2. 14. 오후 12:45:40
* @Version : 
*/
public class BankObjects extends Setup {
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
	public void buy(String bankCom) throws InterruptedException {
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
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAElement[@name='" + "무통장입금" + "']")).click();
				
		// 은행선택 
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAElement[@value='" + "은행을 선택하세요" + "']")).click();
		
		
		System.out.println("[STEP 4] 은행 선택 (" + bankCom + ")");
		
		// 은행 선택 (기업은행, 외환은행, 국민은행, 농협중앙회, 우리은행, 신한은행, SC제일은행, 씨티은행, 대구은행, 부산은행, 광주은행, 경남은행, 우체국, KEB 하나은행, 수협중앙회) 
		inputField = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAElement[10]"));
		inputField.click();
		driver.findElementByClassName("UIAPickerWheel").sendKeys(bankCom);
		
		// 은행 선택 완료
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[@name='" + "완료" + "']")).click();

		
		//현금영수증 신청안함
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAElement[@name='신청안함']")).click();
		
		// 동의 체크 _ 모두 동의 합니다. 
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIASwitch[@name='" + "모두 동의 합니다." + "']")).click();
		
		// 구매하기 버튼 선택
		driver.findElement(By.xpath("//UIAApplication/UIAWindow/UIAScrollView/UIAWebView/UIAButton[@name='" + "구매하기" + "']")).click();
	}

	/**
	 * Desc : Expected output 확인
	 * @Method Name : shinhanCard
	 */
	public void validateion(String bankCom) throws Exception{
		Thread.sleep(10000);
		
		expectedOutput = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText[5]")).getText(); 
		
		 if(driver.manage().logs().get("syslog").getAll().indexOf(expectedOutput) != 0){
			 validation = expectedOutput;
				
			 // 검증 완료
			 Assert.assertEquals("200원", validation);
			 System.out.println("[STEP 5] " + bankCom + " : PASS");
			 System.out.println("[RESULT] Expected output : " + "200원" + " | Validation : " + validation + "\n");
			
			 DBConnector.dbPassUpdate(testName, detailTest);
			
		 } else if(driver.getPageSource().indexOf("실패") > 0){
			System.out.println("[STEP 5] 포인트 : Fail");
			
			DBConnector.dbFailUpdate(testName, detailTest);
		 }
		
		// validation 초기화 
		validation = null;
	}
}
