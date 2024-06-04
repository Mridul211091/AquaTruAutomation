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



public class AquaTruAffiliatePageTest extends BaseTest {

	@BeforeClass()
	public void affilatePageSetup() throws InterruptedException {

		// affiliatePage = loginPage.clickShopNow();
		//affiliatePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		// softAssert.assertEquals(loginPage.getLoginSuccessText(),AppConstants.LOGIN_SUCCESS_TEXT);
		// pdpPage= loginPage.clickBuyNow();

	}

	/*
	 * @DataProvider public Object[][] getDataFromExcel() { return
	 * ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME); }
	 */

	@DataProvider
	public Object[][] getAffiliateLinkFromExcel() {
		return ExcelUtil.getTestData(AppConstants.AFFILIATE_LINK);
	}

	@Test
	public void LandingPageBannerTest() {
		String bannerText = affiliatePage.getBannerText();
		Assert.assertEquals(bannerText, AppConstants.AQT_GENERIC_AFFILIATE_PAGE_BANNER_TEXT);
	}

	 @Test(dataProvider="getAffiliateLinkFromExcel") 
	  public void AffiliateLinkTest(ITestContext testContext,String url) throws Exception { 
	  //setup(); 
	try {
	  softAssert = new SoftAssert();
	  affiliatePage = affiliatePage.getaffiliateURL(url); 
	  String bannerText = affiliatePage.getBannerText(); 
	  softAssert.assertEquals(bannerText,AppConstants.AQT_GENERIC_AFFILIATE_PAGE_BANNER_TEXT);  
	  softAssert.assertAll(); 
	}
	finally {
	tearDown();
	setup();
	}
	 }
	
}


