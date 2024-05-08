package agi.qa.aquatru.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import agi.qa.aquatru.base.BaseTest;
import agi.qa.aquatru.constants.AppConstants;
import agi.qa.aquatru.errors.AppError;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}

	@Test(priority = 2)
	public void loginIconLinkExistTest() {
		Assert.assertTrue(loginPage.isLoginIconExist());
	}

	@Test(priority = 3)
	public void loginTest() throws InterruptedException {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		//Assert.assertEquals(loginPage.getLoginSuccessText(), AppConstants.LOGIN_SUCCESS_TEXT);
	}
	
	@Test(priority = 4)
	public void viewProductTest() throws InterruptedException {
		pdpPage= loginPage.clickBuyNow();
		Assert.assertEquals(pdpPage.getProductPageURL(), AppConstants.PRODUCT_PAGE_URL_FRACTION);
	}


}
