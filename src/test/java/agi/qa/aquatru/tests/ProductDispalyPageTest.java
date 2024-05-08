package agi.qa.aquatru.tests;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import agi.qa.aquatru.base.BaseTest;
import agi.qa.aquatru.constants.AppConstants;
import agi.qa.aquatru.utils.ExcelUtil;

public class ProductDispalyPageTest extends BaseTest {
	
	@BeforeClass
	public void pdpSetup() throws InterruptedException {
		
		  loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		  softAssert.assertEquals(loginPage.getLoginSuccessText(),
		  AppConstants.LOGIN_SUCCESS_TEXT); pdpPage= loginPage.clickBuyNow();
		 
	}
	
	@DataProvider
	public Object[][] getDataFromExcel() {
		return ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
	}

	@Test
	public void ProductDispalyPageTitleTest() {
		String pdpTitle = pdpPage.getProductDisplayPageTitle();
		Assert.assertEquals(pdpTitle, AppConstants.PRODUCT_PAGE_TITLE);
	}
	
	
	
	@Test(dataProvider = "getDataFromExcel")
	public void placeOrder(ITestContext testContext,String ModelName,String PaymentOption, String firstname,
			String lastname, String addone, String addtwo,
			String cty ,String state,String zipcode,String phonenumber,
			String upsellOne,String upsellQuantity, String subtotal, String flatrate,
			String tax, String finaltotal) throws InterruptedException, Exception {
		
		int currenttest=pdpPage.testMe(testContext);
		pdpPage.selectModel(ModelName, PaymentOption);
		Thread.sleep(5000);
		pdpPage.checkout (firstname, lastname, addone, addtwo, cty, state,zipcode,phonenumber); 
		Thread.sleep(15000);
		//pdpPage.selectupsells(upsellOne, upsellQuantity);
		Map<String, String> productActDetailsMap = pdpPage.getorderdetails();
		softAssert.assertEquals(productActDetailsMap.get("subtotal"), subtotal);
		softAssert.assertEquals(productActDetailsMap.get("flatrate"), flatrate);
		softAssert.assertEquals(productActDetailsMap.get("tax"), tax);
		softAssert.assertEquals(productActDetailsMap.get("total"), finaltotal);
		pdpPage.writeexcel(productActDetailsMap.get("subtotal"),productActDetailsMap.get("flatrate"),productActDetailsMap.get("tax"),productActDetailsMap.get("total"),currenttest);
		loginPage= pdpPage.logoutfromthankyoupage();
		softAssert.assertAll();
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		pdpSetup();
		
	}	
}

