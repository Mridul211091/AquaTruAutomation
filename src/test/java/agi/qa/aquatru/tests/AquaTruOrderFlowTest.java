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

public class AquaTruOrderFlowTest extends BaseTest {

	@BeforeClass()
	public void affilatePageSetup() throws InterruptedException {

		// affiliatePage = loginPage.clickShopNow();
		//aquatruPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
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

	/*
	 * @Test public void LandingPageBannerTest() { String bannerText =
	 * affiliatePage.getBannerText(); Assert.assertEquals(bannerText,
	 * AppConstants.AD_AFFILIATE_PAGE_BANNER_TEXT); }
	 */

	/*
	 * @Test(dataProvider="getAffiliateLinkFromExcel") public void
	 * AffiliateLinkTest(String url) throws Exception { //setup(); affiliatePage =
	 * affiliatePage.getaffiliateURL(url); Thread.sleep(3000);
	 * affiliatePage.clickShopNow(); Thread.sleep(3000); //String bannerText =
	 * affiliatePage.getBannerText(); //softAssert.assertEquals(bannerText,
	 * AppConstants.AD_AFFILIATE_PAGE_BANNER_TEXT); String purifierTitle =
	 * affiliatePage.getPurifierPageTitle(); softAssert.assertEquals(purifierTitle,
	 * AppConstants.AD_AFFILIATE_PURIFIER_PAGE_TITLE); Thread.sleep(10000);
	 * //.selectModel("AirDoctor Wall-Mounted 2500","1"); String modelTitle =
	 * affiliatePage.getModelText(); Assert.assertEquals(modelTitle,
	 * AppConstants.AD_AFFILIATE_MODEL_TEXT); softAssert.assertAll(); //tearDown();
	 * }
	 */

	@Test(dataProvider = "getDataFromExcel")
	public void placeOrder(ITestContext testContext, String ModelName, String ThreeYearWarranty, String SlidingTray,
			String CarafeCombo, String BlueBottle, String BlackBottle, String WhiteBottle, String email,
			String firstname, String lastname, String addone, String addtwo, String cty, String state, String zipcode,
			String phonenumber, String upsellOne, String upsellQuantity, String subtotal, String flatrate, String tax,
			String finaltotal) throws InterruptedException, Exception {
		try {
			aquatruPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
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
