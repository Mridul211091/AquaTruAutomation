package agi.qa.aquatru.tests;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import agi.qa.aquatru.base.BaseTest;
import agi.qa.aquatru.constants.AppConstants;
import agi.qa.aquatru.utils.ExcelUtil;

public class AquaTruAffiliateTest extends BaseTest {

	@BeforeClass()
	public void affilatePageSetup() throws InterruptedException {

		// affiliatePage = loginPage.clickShopNow();
		// aquatruPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		// softAssert.assertEquals(loginPage.getLoginSuccessText(),AppConstants.LOGIN_SUCCESS_TEXT);
		// pdpPage= loginPage.clickBuyNow();

	}

	@DataProvider
	public Object[][] getDataFromExcel() {
		return ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
	}

	/*
	 * @DataProvider public Object[][] getAffiliateLinkFromExcel() { return
	 * ExcelUtil.getTestData(AppConstants.AFFILIATE_LINK); }
	 */
	/*
	 * @Test public void ProductDispalyPageTitleTest() { String pdpTitle =
	 * pdpPage.getProductDisplayPageTitle(); Assert.assertEquals(pdpTitle,
	 * AppConstants.PRODUCT_PAGE_TITLE); }
	 */

	/*
	 * @Test public void PurifierPageTitleTest() { String purifierTitle =
	 * affiliatePage.getPurifierPageTitle(); Assert.assertEquals(purifierTitle,
	 * AppConstants.AD_AFFILIATE_PURIFIER_PAGE_TITLE); }
	 */

	
	  @Test 
	  public void LandingPageBannerTest() { 
	  String bannerText = aquatruPage.getBannerText(); 
	  Assert.assertEquals(bannerText, AppConstants.AQT_AFFILIATE_PAGE_BANNER_TEXT); 
	  }
	  
	  @Test
	  public void CarafeDetailsTest() throws InterruptedException { 
		  Thread.sleep(5000);
		  aquatruPage.accepCookies();
		  Thread.sleep(5000);
		  String carafeName = aquatruPage.getModelText();
		  String regularPrice = aquatruPage.getRegularPrice();
		  String emailOnlyPrice = aquatruPage.getEmailOnlyPrice();
		  softAssert.assertEquals(carafeName, AppConstants.AQT_AFFILIATE_PAGE_CARAFE_TEXT); 
		  softAssert.assertEquals(regularPrice, AppConstants.AQT_AFFILIATE_PAGE_REGULAR_PRICE); 
		  softAssert.assertEquals(emailOnlyPrice, AppConstants.AQT_AFFILIATE_PAGE_EMAIL_ONLY_PRICE); 
		  softAssert.assertAll();
		  }
	  
	  @Test
	  public void AlkalineCarafeDetailsTest() throws InterruptedException { 
		  Thread.sleep(5000);
		  aquatruPage.accepCookies();
		  Thread.sleep(5000);
		  aquatruPage.clickAlkalineRadiobtn();
		  Thread.sleep(5000);
		  String carafeName = aquatruPage.getModelText();
		  String regularPrice = aquatruPage.getRegularPrice();
		  String emailOnlyPrice = aquatruPage.getEmailOnlyPrice();
		  softAssert.assertEquals(carafeName, AppConstants.AQT_AFFILIATE_PAGE_ALKALINE_CARAFE_TEXT); 
		  softAssert.assertEquals(regularPrice, AppConstants.AQT_AFFILIATE_PAGE_ALKALINE_REGULAR_PRICE); 
		  softAssert.assertEquals(emailOnlyPrice, AppConstants.AQT_AFFILIATE_PAGE_ALKALINE_EMAIL_ONLY_PRICE); 
		  softAssert.assertAll();
		  }
	  
	@Test(dataProvider = "getDataFromExcel")
	public void placeOrder(ITestContext testContext, String ModelName, String ThreeYearWarranty, String SlidingTray,
			String CarafeCombo, String BlueBottle, String BlackBottle, String WhiteBottle, String email,
			String firstname, String lastname, String addone, String addtwo, String cty, String state, String zipcode,
			String phonenumber, String upsellOne, String upsellQuantity, String subtotal, String flatrate, String tax,
			String finaltotal) throws InterruptedException, Exception {
		try {
			aquatruPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
			softAssert = new SoftAssert();
			int currenttest = aquatruPage.testMe(testContext);			
			Thread.sleep(5000);
			aquatruPage.clickShopNow();
			Thread.sleep(15000);
			aquatruPage.selectModel(ModelName, ThreeYearWarranty, SlidingTray, CarafeCombo, BlueBottle, BlackBottle,
					WhiteBottle);
			Thread.sleep(5000);
			aquatruPage.checkout(email, firstname, lastname, addone, addtwo, cty, state, zipcode, phonenumber);
			Thread.sleep(15000);
			aquatruPage.getThankYoPageURL();
			Map<String, String> productActDetailsMap = aquatruPage.getorderdetails();
			softAssert.assertEquals(productActDetailsMap.get("subtotal"), subtotal);
			System.out.println(
					"Expected Subtotal: " + subtotal + " Actual Subtotal: " + productActDetailsMap.get("subtotal"));
			softAssert.assertEquals(productActDetailsMap.get("Shipping"), flatrate);
			System.out.println(
					"Expected Shipping: " + flatrate + " Actual Shipping: " + productActDetailsMap.get("Shipping"));
			softAssert.assertEquals(productActDetailsMap.get("tax"), tax);
			System.out.println("Expected tax: " + tax + " Actual tax: " + productActDetailsMap.get("tax"));
			softAssert.assertEquals(productActDetailsMap.get("total"), finaltotal);
			System.out.println("Expected total: " + finaltotal + " Actual total: " + productActDetailsMap.get("total"));
			aquatruPage.writeexcel(productActDetailsMap.get("subtotal"), productActDetailsMap.get("Shipping"),
					productActDetailsMap.get("tax"), productActDetailsMap.get("total"), currenttest);
			//loginPage = aquatruPage.logoutfromthankyoupage();
			softAssert.assertAll();
		} finally {
			// Navigate back to the starting page of the website
			//loginPage.loginAgain(prop.getProperty("username"), prop.getProperty("password"));
			tearDown();
			setup();
	        
		}
		

	}
}
