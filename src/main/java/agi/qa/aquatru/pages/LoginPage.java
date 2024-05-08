package agi.qa.aquatru.pages;

import java.sql.Time;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import agi.qa.aquatru.constants.AppConstants;
import agi.qa.aquatru.utils.ElementUtil;
import agi.qa.aquatru.utils.TimeUtil;

public class LoginPage {

	//Page class/Page Library/Page Object
	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. Private By Locators
	private By loginIcon = By.xpath("//a[@class='nav-top-link'][normalize-space()='Login']");
	private By usernameField = By.id("username");
	private By passwordField = By.id("password");
	private By loginButton = By.name("login");
	private By loginText= By.xpath("//div[text()='You are successfully logged in!']");
	private By buynowbtn= By.xpath("(//a[normalize-space()='Buy Now'])[1]");
	private By shopnowbtn= By.xpath("//*[@id=\"content\"]/div/div/div[2]/a");
	private By cookiebannerbtn= By.cssSelector("button[aria-label=\"OK, Agree to CCPA\"]");
	private By backtohomepage = By.xpath("//*[@id='logo']/a");
	private By cookie= By.cssSelector(".sc-dcJsrY.hNTnAz");
	private By shadowroot= By.id("usercentrics-root");

	// 2. Public Page Class Const...
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. Public Page Actions/Method
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_MEDIUM_TIME);
		System.out.println("login page title : " + title);
		return title;
	}
	
	public AquaTruOrderFlowPage getaffiliateURL(String url) {
		driver.get(url);
		return new AquaTruOrderFlowPage(driver);
	}
	
	public String getLoginSuccessText() {
		eleUtil.waitForElementVisible(loginText, TimeUtil.DEFAULT_LONG_TIME);
		String title = eleUtil.doGetElementText(loginText);
		System.out.println("login success text : " + title);
		return title;
	}

	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, TimeUtil.DEFAULT_MEDIUM_TIME);
		System.out.println("login page url : " + url);
		return url;
	}

	public boolean isLoginIconExist() {
		return eleUtil.isElementDisplayed(loginIcon);
	}

	public AquaTruOrderFlowPage doLogin(String username, String pwd) throws InterruptedException{
		Thread.sleep(2000);
		eleUtil.clickWhenReady(loginIcon, TimeUtil.DEFAULT_LONG_TIME);
		eleUtil.waitForElementVisible(usernameField, TimeUtil.DEFAULT_LONG_TIME);
		eleUtil.doSendKeys(usernameField, username);
		eleUtil.doSendKeys(passwordField, pwd);
		eleUtil.doClick(loginButton);
		eleUtil.doClick(backtohomepage);
		Thread.sleep(3000);
		return new AquaTruOrderFlowPage(driver);
	}
	
	public AquaTruOrderFlowPage loginAgain(String username, String pwd) throws InterruptedException{
		Thread.sleep(2000);
		eleUtil.clickWhenReady(loginIcon, TimeUtil.DEFAULT_LONG_TIME);
		eleUtil.waitForElementVisible(usernameField, TimeUtil.DEFAULT_LONG_TIME);
		eleUtil.doSendKeys(usernameField, username);
		eleUtil.doSendKeys(passwordField, pwd);
		eleUtil.doClick(loginButton);
		eleUtil.doClick(backtohomepage);
		return new AquaTruOrderFlowPage(driver);
	}
	
	public ProductDisplayPage clickBuyNow() throws InterruptedException{
		
		eleUtil.clickWhenReady(buynowbtn, TimeUtil.DEFAULT_LONG_TIME);
		return new ProductDisplayPage(driver);
		
	}
	
	public AquaTruOrderFlowPage clickShopNow() throws InterruptedException{
		Thread.sleep(2000);
		driver.findElement(shadowroot).getShadowRoot().findElement(cookiebannerbtn).click();
		Thread.sleep(2000);
		eleUtil.clickWhenReady(shopnowbtn, TimeUtil.DEFAULT_LONG_TIME);
		return new AquaTruOrderFlowPage(driver);
		
	}
	
	

}
