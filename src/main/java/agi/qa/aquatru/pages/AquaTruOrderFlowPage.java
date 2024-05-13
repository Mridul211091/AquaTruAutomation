package agi.qa.aquatru.pages;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;

import agi.qa.aquatru.constants.AppConstants;
import agi.qa.aquatru.utils.ElementUtil;
import agi.qa.aquatru.utils.ExcelUtil;
import agi.qa.aquatru.utils.JavaScriptUtil;
import agi.qa.aquatru.utils.TimeUtil;

public class AquaTruOrderFlowPage {

	// Page class/Page Library/Page Object
	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil JsUtil;
	private Map<String, String> productMap = new HashMap<String, String>();

	// 1. Private By Locators

	private By logoutLink = By.linkText("Logout");
	// private By shopNow =By.xpath("//span[normalize-space()='SHOP NOW']");

	private By shopNow = By.xpath(
			"//a[@href='https://aquatrustaging.wpengine.com/product-category/water-purifiers/']//span[contains(text(),'Shop Now')]");
	private By bannerText = By.xpath("//strong[contains(text(),'NEW! AirDoctor 2500 Wall-Mounted Purifier Sale-on-')]");
	// private By bannerText =By.xpath("//ul[contains(@class,'nav header-bottom-nav
	// nav-center mobile-nav')]/li/p");
	// private By Model2500Text =By.xpath("//h5[normalize-space()='\"WALL-MOUNTED OR
	// FLOOR STANDING AIR PURIFIER\"']");
	// private By Model2500Text = By.xpath("//div[@class='product_text-inner-col
	// mob-hide-heading']/h3[span[@class='orange_new' and text()='New!
	// ']][text()='AirDoctor 2500']");
	private By Model2500Text = By.xpath(
			"//div[@class='product_text-inner-col mob-hide-heading' and not(contains(@style, 'display: none'))]/h3[1][contains(., 'AirDoctor 2500')]");
	// section[@class='section product-listing-section
	// product_section_130445']//div[2]//div[1]//h3[1]
	private By counterTopCarafeBtn = By.xpath(
			"//span[a[contains(text(), 'Carafe Countertop Reverse Osmosis Water Purifier')]]/ancestor::div[contains(@class, 'home-product-col')]/descendant::input[@id='121318']");
	private By counterTopCarafeAlkalineBtn = By.xpath(
			"//span[a[contains(text(), 'Carafe Countertop Reverse Osmosis Water Purifier')]]/ancestor::div[contains(@class, 'home-product-col')]/descendant::input[@id='121319']");
	private By countertopCarafePrice = By.xpath(
			"//input[@id='121318']/ancestor::div[contains(@class, 'home-product-col-inner')]//span[@class='main-price prc_prd_121317']/span[@class='woocommerce-Price-amount amount']/bdi");
	private By coutertopCarafeAddToCartBtn = By.xpath(
			"//span[contains(a/text(), 'Carafe Countertop Reverse Osmosis Water Purifier')]/ancestor::div[contains(@class, 'home-product-col-inner')]//a[contains(@class, 'cart_prd')]");
	private By preCheckoutSlidingTrayUpsellAddToCartBtn = By.xpath(
			"//div[contains(@class, 'prod-slider-name') and text()='AquaTru Carafe Countertop Sliding Tray']/following-sibling::div[@class='add-to-cart-btn']//a[contains(@class, 'product_add_cart')]");
	private By preCheckoutWarrantyUpsellAddToCartBtn = By.xpath(
			"//div[contains(@class, 'prod-slider-name') and text()='AquaTru Carafe 3-Yr Warranty']/following-sibling::div[@class='add-to-cart-btn']//a[contains(@class, 'product_add_cart')]");
	private By preCheckoutClassicSlidingTrayUpsellAddToCartBtn = By.xpath(
			"//div[contains(@class, 'prod-slider-name') and text()='AquaTru Countertop Sliding Tray']/following-sibling::div[@class='add-to-cart-btn']//a[contains(@class, 'product_add_cart')]");
	private By preCheckoutOneYearComboUpsellAddToCartBtn = By.xpath(
			"//div[contains(@class, 'prod-slider-name') and text()='Carafe ONLY One-Year Filter Combo Pack']/following-sibling::div[@class='add-to-cart-btn']//a[contains(@class, 'product_add_cart')]");
	private By checkoutBtn = By.xpath("//a[normalize-space()='Checkout']");
	private By classicCountertopRadioBtn = By.xpath("//label[.='Classic']/preceding-sibling::input[@type='radio']");
	private By classicAlkalineCountertopRadioBtn = By
			.xpath("/label[.='Classic, Alkaline']/preceding-sibling::input[@type='radio']");
	private By connectCountertopRadioBtn = By.xpath("//label[.='Connect']/preceding-sibling::input[@type='radio']");
	private By connectAlkalineCountertopRadioBtn = By
			.xpath("//label[.='Connect, Alkaline']/preceding-sibling::input[@type='radio']");
	private By classicCoutertopAddToCartBtn = By
			.xpath("//a[@data-product_id='14958' and normalize-space(.)='Add to cart']");
	private By preCheckoutClassicWarrantyUpsellAddToCartBtn = By.xpath(
			"//div[contains(@class, 'prod-slider-name') and text()='3 Year Extended Warranty - Classic']/following-sibling::div[@class='add-to-cart-btn']//a[contains(@class, 'product_add_cart')]");
	private By preCheckoutClassicOneYearComboUpsellAddToCartBtn = By.xpath(
			"//div[contains(@class, 'prod-slider-name') and text()='Classic One-Year Filter Combo Pack']/following-sibling::div[@class='add-to-cart-btn']//a[contains(@class, 'product_add_cart')]");
	private By preCheckoutClassicAlkalineOneYearComboUpsellAddToCartBtn = By.xpath(
			"//div[contains(@class, 'prod-slider-name') and text()='Classic One-Year Mineral Boost Alkaline Combo Filter Pack']/following-sibling::div[@class='add-to-cart-btn']//a[contains(@class, 'product_add_cart')]");

	private By preCheckoutConnectWarrantyUpsellAddToCartBtn = By.xpath(
			"//div[contains(@class, 'prod-slider-name') and text()='3 Year Extended Warranty - Connect']/following-sibling::div[@class='add-to-cart-btn']//a[contains(@class, 'product_add_cart')]");
	private By Model2000plusQuantityBtn = By.xpath(
			"//h3[contains(text(), 'AirDoctor 2000')]/following::input[@class='ux-quantity__button ux-quantity__button--plus button plus is-form'][1]");
	private By myAccountLink = By.linkText("My Account");
	private By headers = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By select7in1Model = By.xpath("//a[normalize-space()='7-in-1-saw model']");
	private By onePay = By.xpath("//*[@id=\'wc-option-pay-full\']");
	private By onePay7in1 = By.xpath("//*[@id='product-2155']/div/div[1]/div/div[2]/form/div/div[2]/ul[1]/li[2]/label");
	private By threePay7in1 = By
			.xpath("//*[@id='product-2155']/div/div[1]/div/div[2]/form/div/div[2]/ul[1]/li[1]/label");
	private By select3000Model = By.xpath("(//a[normalize-space()='platinum 3000 model'])[1]");
	private By noThanks = By.xpath("//*[@id=\"no_thanks\"]");
	private By firstName = By.id("billing_first_name");
	private By emailField = By.id("billing_email");
	private By lastName = By.id("billing_last_name");
	private By addressone = By.id("billing_address_1");
	private By addresstwo = By.id("billing_address_2");
	private By city = By.id("billing_city");
	private By zipcode = By.id("billing_postcode");
	private By phone = By.id("billing_phone");
	private By dropdownBtn = By.xpath("//*[@id=\"billing_state_field\"]/span/span/span[1]/span/span[2]");
	private By stateDropdown = By.id("select2-billing_state-container");
	private By statetextfield = By.cssSelector("input[role='combobox']");
	private By stateoption = By.cssSelector("#select2-billing_state-results:first-child li");
	private By termscheckbox = By.xpath("//input[@id='terms']");
	private By placeorderbtn = By.name("woocommerce_checkout_place_order");
	private By placeorderadbtn = By.id("place_order");
	private By popupdonebtn = By.id("wdc_popup");
	private By credicardradiobtn = By.id("payment_method_cybersource_credit_card");
	private By cardnumberfield = By.id("wc-cybersource-credit-card-account-number-hosted");
	private By worldpaycardnumberfield = By.id("WC_Gateway_Worldpay-card-number");
	private By cardexpiryfield = By.id("wc-cybersource-credit-card-expiry");
	private By worldpaycardexpiryfield = By.id("WC_Gateway_Worldpay-card-expiry");
	private By cardseccodefield = By.id("wc-cybersource-credit-card-csc-hosted");
	private By worldpaycardseccodefield = By.id("WC_Gateway_Worldpay-card-cvc");
	private By subtotalvalue = By.xpath("//th[text()='Subtotal:']/following-sibling::td");
	private By taxvalue = By.xpath("//th[text()='Tax:']/following-sibling::td");
	private By flatrate = By.xpath("//th[text()='Shipping:']/following-sibling::td");
	private By finaltotal = By.xpath("//th[text()='Total:']/following-sibling::td");
	private By backtohomepage = By.xpath("//*[@id='logo']/a");
	private By logout = By.xpath("//*[@id='main']/div[2]/div/div/div[2]/div/div/p[1]/a");
	// *[@id="main"]/div[2]/div/div/div[2]/div/div/p[1]/a
	private By loginIcon = By.xpath("//a[@class='nav-top-link'][normalize-space()='Login']");
	private By usenewcard = By.id("wc-cybersource-credit-card-use-new-payment-method");
	private By paymentmethodtab = By.xpath("//*[@id=\"my-account-nav\"]/li[5]/a");
	private By logouttab = By.xpath("//*[@id='my-account-nav']/li[8]/a");
	private By deletebtn = By.xpath("//*[@id=\"main\"]/div[2]/div/div/div[2]/div/div/table/tbody/tr/td[6]/a[3]");

	// UnderSink Locators:
	private By undersinkAlkalineRadioBtn = By
			.xpath("//label[.='Alkaline ']/preceding-sibling::input[@type='radio' and @id='6135']");
	private By undersinkClassicRadioBtn = By
			.xpath("//label[.='Classic ']/preceding-sibling::input[@type='radio' and @id='6136']");
	private By undersinkAddToCartBtn = By.xpath("//a[@data-product_id='1714' and normalize-space(.)='Add to cart']");

	private By preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn = By.xpath(
			"//div[contains(@class, 'prod-slider-name') and text()='3 Year Extended Warranty - Under Sink']/following-sibling::div[@class='add-to-cart-btn']//a[contains(@class, 'product_add_cart')]");
	private By preCheckoutUnderSinkClassicOneYearComboUpsellAddToCartBtn = By.xpath(
			"//div[contains(@class, 'prod-slider-name') and text()='Classic One-Year Filter Combo Pack']/following-sibling::div[@class='add-to-cart-btn']//a[contains(@class, 'product_add_cart')]");
	private By preCheckoutDeepBlueBottleUpsellAddToCartBtn = By.xpath(
			"//div[contains(@class, 'prod-slider-name') and text()='AquaTru Stainless Steel Water Bottle - Deep Blue']/following-sibling::div[@class='add-to-cart-btn']//a[contains(@class, 'product_add_cart')]");
	private By preCheckoutUrchinBlackBottleUpsellAddToCartBtn = By.xpath(
			"//div[contains(@class, 'prod-slider-name') and text()='AquaTru Stainless Steel Water Bottle - Urchin Black']/following-sibling::div[@class='add-to-cart-btn']//a[contains(@class, 'product_add_cart')]");
	private By preCheckoutWhiteSandBottleUpsellAddToCartBtn = By.xpath(
			"//div[contains(@class, 'prod-slider-name') and text()='AquaTru Stainless Steel Water Bottle - White Sand']/following-sibling::div[@class='add-to-cart-btn']//a[contains(@class, 'product_add_cart')]");

	// 2. Public Page Class Const...
	public AquaTruOrderFlowPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		JsUtil = new JavaScriptUtil(driver);
	}

	public String getProductDisplayPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.AD_AFFILIATE_PAGE_TITLE, 5);
		System.out.println("Landing page title : " + title);
		return title;
	}

	public String getPurifierPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.AD_AFFILIATE_PURIFIER_PAGE_TITLE, 5);
		System.out.println("Prifier page title : " + title);
		return title;
	}

	public String getProductPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.PRODUCT_PAGE_URL_FRACTION, TimeUtil.DEFAULT_MEDIUM_TIME);
		System.out.println("product page url : " + url);
		return url;
	}

	public AquaTruOrderFlowPage getaffiliateURL(String url) {
		driver.get(url);
		return new AquaTruOrderFlowPage(driver);
	}

	public String getBannerText() {
		String title = eleUtil.doGetElementText(bannerText);
		System.out.println("Landing page banner text : " + title);
		return title;
	}

	public void clickShopNow() {
		eleUtil.waitForElementPresence(shopNow, TimeUtil.DEFAULT_LONG_TIME);
		eleUtil.doActionsClick(shopNow);
	}

	public String getModelText() {
		String title = eleUtil.doGetElementText(Model2500Text);
		System.out.println("Model Text is : " + title);
		return title;
	}
	
	public void getThankYoPageURL() throws URISyntaxException {
		String originalUrl = driver.getCurrentUrl();
        String username = "aquatrustaging";
        String password = "aquatru2024";

        try {
            URI uri = new URI(originalUrl);
            String newUrl = uri.getScheme() + "://" + username + ":" + password + "@" + uri.getHost();
            if (uri.getPort() != -1) {
                newUrl += ":" + uri.getPort();
            }
            newUrl += uri.getPath();
            if (uri.getQuery() != null) {
                newUrl += "?" + uri.getQuery();
            }
            System.out.println("New URL: " + newUrl);
            driver.get(newUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
		//return url;
	}

	public Map<String, String> getorderdetails() throws InvalidFormatException, IOException {
		productMap.put("subtotal", eleUtil.getElement(subtotalvalue).getText());
		productMap.put("tax", eleUtil.getElement(taxvalue).getText());
		productMap.put("Shipping", eleUtil.getElement(flatrate).getText());
		productMap.put("total", eleUtil.getElement(finaltotal).getText());
		System.out.println("product Details: \n" + productMap);
		return productMap;
	}

	public void selectupsells(String Upsell_1, String Upsell_1_Option_Quantity) throws InterruptedException {

		if (Upsell_1.equals("YES")) {
			if (Upsell_1_Option_Quantity.equals("2")) {
				WebElement staticDropdown = driver.findElement(By.id("wps_upsell_quantity_field"));
				Select dropdown = new Select(staticDropdown);
				dropdown.selectByVisibleText("2");
				System.out.println("Upsell_1_Option_Quantity");
				Thread.sleep(1000);
			} else if (Upsell_1_Option_Quantity.equals("3")) {
				WebElement staticDropdown = driver.findElement(By.id("wps_upsell_quantity_field"));
				Select dropdown = new Select(staticDropdown);
				dropdown.selectByVisibleText("3");
				System.out.println("Upsell_1_Option_Quantity");
				Thread.sleep(1000);
			}

			driver.findElement(By.xpath("//a[contains(text(),'ADD TO CART')]")).click();

		} else if (Upsell_1.equals("NO")) {
			driver.findElement(By.xpath("(//a[normalize-space()='No, thank you'])[1]")).click();
		} else {
			System.out.println("Incorrect Upsell-1 Options");
			// Fail - incorrect Upsell-1 option
		}

	}

	public void checkout(String email, String firstname, String lastname, String addone, String addtwo, String cty,
			String state, String zip, String phonenumber) throws InterruptedException, Exception {
		eleUtil.doSendKeys(emailField, email);
		eleUtil.doSendKeys(firstName, firstname);
		eleUtil.doSendKeys(firstName, firstname);
		eleUtil.doSendKeys(lastName, lastname);
		eleUtil.doSendKeys(addressone, addone);
		eleUtil.doSendKeys(addresstwo, addtwo);
		eleUtil.doSendKeys(city, cty);
		JsUtil.scrollIntoView(eleUtil.getElement(dropdownBtn));
		eleUtil.clickWhenReady(dropdownBtn, TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.doSendKeys(statetextfield, state);
		eleUtil.clickWhenReady(stateoption, TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.SendKeys(zipcode, zip);
		eleUtil.doSendKeys(phone, phonenumber);
		JsUtil.scrollIntoView(eleUtil.getElement(cardnumberfield));
		Thread.sleep(3000);
		eleUtil.doActionsSendKeys(cardnumberfield, AppConstants.CARD_NUMBER);
		eleUtil.doActionsSendKeys(cardexpiryfield, AppConstants.CARD_EXPIRY);
		//JsUtil.scrollIntoView(eleUtil.getElement(placeorderbtn));
		Thread.sleep(3000);
		eleUtil.doActionsSendKeys(cardseccodefield, AppConstants.SECURITY_CODE);
		// JsUtil.scrollIntoView(eleUtil.getElement(placeorderbtn));
		Thread.sleep(2000);
		eleUtil.doActionsClick(termscheckbox);
		// eleUtil.doClickcheckbox(termscheckbox,TimeUtil.DEFAULT_MEDIUM_TIME);
		Thread.sleep(2000);
		eleUtil.doActionsClick(placeorderadbtn);
		eleUtil.clickWhenReady(popupdonebtn, TimeUtil.DEFAULT_MEDIUM_TIME);
	}

	public void selectModel(String ModelName, String ThreeYearWarranty, String SlidingTray, String CarafeCombo,
			String BlueBottle, String BlackBottle, String WhiteBottle) throws InterruptedException, Exception {
		try {
			if (ModelName.equals("Carafe Countertop") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& SlidingTray.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				Thread.sleep(5000);
				eleUtil.doActionsClick(counterTopCarafeBtn);
				eleUtil.doActionsClick(coutertopCarafeAddToCartBtn);
				eleUtil.waitForElementPresence(preCheckoutWarrantyUpsellAddToCartBtn, TimeUtil.DEFAULT_LONG_TIME);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutSlidingTrayUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(5000);
			}

			else if (ModelName.equals("Carafe Countertop") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& SlidingTray.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(counterTopCarafeBtn);
				eleUtil.doActionsClick(coutertopCarafeAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.waitForElementPresence(preCheckoutWarrantyUpsellAddToCartBtn, TimeUtil.DEFAULT_LONG_TIME);
				eleUtil.doActionsClick(preCheckoutWarrantyUpsellAddToCartBtn);
				System.out.println("Clicked on Warranty Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Carafe Countertop") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& SlidingTray.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(counterTopCarafeBtn);
				eleUtil.doActionsClick(coutertopCarafeAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.waitForElementPresence(preCheckoutWarrantyUpsellAddToCartBtn, TimeUtil.DEFAULT_LONG_TIME);
				eleUtil.doActionsClick(preCheckoutWarrantyUpsellAddToCartBtn);
				System.out.println("Clicked on Warranty Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutSlidingTrayUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Carafe Countertop") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& SlidingTray.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(counterTopCarafeBtn);
				eleUtil.doActionsClick(coutertopCarafeAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.waitForElementPresence(preCheckoutWarrantyUpsellAddToCartBtn, TimeUtil.DEFAULT_LONG_TIME);
				eleUtil.doActionsClick(preCheckoutWarrantyUpsellAddToCartBtn);
				System.out.println("Clicked on Warranty Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			}

			else if (ModelName.equals("Carafe Countertop") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& SlidingTray.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(counterTopCarafeBtn);
				eleUtil.doActionsClick(coutertopCarafeAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutSlidingTrayUpsellAddToCartBtn);
				System.out.println("Clicked on Sliding Tray Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Carafe Countertop") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& SlidingTray.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(counterTopCarafeBtn);
				eleUtil.doActionsClick(coutertopCarafeAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutSlidingTrayUpsellAddToCartBtn);
				System.out.println("Clicked on Sliding Tray Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Carafe Countertop") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& SlidingTray.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(counterTopCarafeBtn);
				eleUtil.doActionsClick(coutertopCarafeAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutOneYearComboUpsellAddToCartBtn);
				System.out.println("Clicked on One Year Combo Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Carafe Countertop") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& SlidingTray.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(counterTopCarafeBtn);
				eleUtil.doActionsClick(coutertopCarafeAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			}

			else if (ModelName.equals("Carafe Alkaline Countertop") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& SlidingTray.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(counterTopCarafeAlkalineBtn);
				eleUtil.doActionsClick(coutertopCarafeAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutSlidingTrayUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			else if (ModelName.equals("Carafe Alkaline Countertop") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& SlidingTray.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(counterTopCarafeAlkalineBtn);
				eleUtil.doActionsClick(coutertopCarafeAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWarrantyUpsellAddToCartBtn);
				System.out.println("Clicked on Warranty Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Carafe Alkaline Countertop") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& SlidingTray.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(counterTopCarafeAlkalineBtn);
				eleUtil.doActionsClick(coutertopCarafeAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWarrantyUpsellAddToCartBtn);
				System.out.println("Clicked on Warranty Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutSlidingTrayUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Carafe Alkaline Countertop") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& SlidingTray.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(counterTopCarafeAlkalineBtn);
				eleUtil.doActionsClick(coutertopCarafeAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWarrantyUpsellAddToCartBtn);
				System.out.println("Clicked on Warranty Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			}

			else if (ModelName.equals("Carafe Alkaline Countertop") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& SlidingTray.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(counterTopCarafeAlkalineBtn);
				eleUtil.doActionsClick(coutertopCarafeAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutSlidingTrayUpsellAddToCartBtn);
				System.out.println("Clicked on Sliding Tray Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Carafe Alkaline Countertop") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& SlidingTray.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(counterTopCarafeAlkalineBtn);
				eleUtil.doActionsClick(coutertopCarafeAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutSlidingTrayUpsellAddToCartBtn);
				System.out.println("Clicked on Sliding Tray Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Carafe Alkaline Countertop") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& SlidingTray.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(counterTopCarafeAlkalineBtn);
				eleUtil.doActionsClick(coutertopCarafeAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutOneYearComboUpsellAddToCartBtn);
				System.out.println("Clicked on One Year Combo Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Carafe Alkaline Countertop") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& SlidingTray.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(counterTopCarafeAlkalineBtn);
				eleUtil.doActionsClick(coutertopCarafeAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Classic Countertop") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& SlidingTray.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(classicCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicSlidingTrayUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			else if (ModelName.equals("Classic Countertop") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& SlidingTray.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(classicCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicWarrantyUpsellAddToCartBtn);
				System.out.println("Clicked on Warranty Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Classic Countertop") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& SlidingTray.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(classicCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicWarrantyUpsellAddToCartBtn);
				System.out.println("Clicked on Warranty Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicSlidingTrayUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Classic Countertop") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& SlidingTray.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(classicCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicWarrantyUpsellAddToCartBtn);
				System.out.println("Clicked on Warranty Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			}

			else if (ModelName.equals("Classic Countertop") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& SlidingTray.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(classicCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicSlidingTrayUpsellAddToCartBtn);
				System.out.println("Clicked on Sliding Tray Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Classic Countertop") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& SlidingTray.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(classicCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicSlidingTrayUpsellAddToCartBtn);
				System.out.println("Clicked on Sliding Tray Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Classic Countertop") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& SlidingTray.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(classicCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicOneYearComboUpsellAddToCartBtn);
				System.out.println("Clicked on One Year Combo Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Classic Countertop") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& SlidingTray.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(classicCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			}

			else if (ModelName.equals("Connect Countertop") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& SlidingTray.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(connectCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutConnectWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicSlidingTrayUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			else if (ModelName.equals("Connect Countertop") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& SlidingTray.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(connectCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutConnectWarrantyUpsellAddToCartBtn);
				System.out.println("Clicked on Warranty Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Connect Countertop") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& SlidingTray.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(connectCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutConnectWarrantyUpsellAddToCartBtn);
				System.out.println("Clicked on Warranty Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicSlidingTrayUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Connect Countertop") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& SlidingTray.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(connectCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutConnectWarrantyUpsellAddToCartBtn);
				System.out.println("Clicked on Warranty Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			}

			else if (ModelName.equals("Connect Countertop") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& SlidingTray.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(connectCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicSlidingTrayUpsellAddToCartBtn);
				System.out.println("Clicked on Sliding Tray Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Connect Countertop") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& SlidingTray.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(connectCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicSlidingTrayUpsellAddToCartBtn);
				System.out.println("Clicked on Sliding Tray Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Connect Countertop") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& SlidingTray.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(connectCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicOneYearComboUpsellAddToCartBtn);
				System.out.println("Clicked on One Year Combo Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Connect Countertop") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& SlidingTray.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(connectCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			}

			else if (ModelName.equals("Connect Alkaline Countertop") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& SlidingTray.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(connectAlkalineCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutConnectWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicSlidingTrayUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicAlkalineOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			else if (ModelName.equals("Connect Alkaline Countertop") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& SlidingTray.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(connectAlkalineCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutConnectWarrantyUpsellAddToCartBtn);
				System.out.println("Clicked on Warranty Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Connect Alkaline Countertop") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& SlidingTray.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(connectAlkalineCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutConnectWarrantyUpsellAddToCartBtn);
				System.out.println("Clicked on Warranty Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicSlidingTrayUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Connect Alkaline Countertop") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& SlidingTray.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(connectAlkalineCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutConnectWarrantyUpsellAddToCartBtn);
				System.out.println("Clicked on Warranty Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicAlkalineOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			}

			else if (ModelName.equals("Connect Alkaline Countertop") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& SlidingTray.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(connectAlkalineCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicSlidingTrayUpsellAddToCartBtn);
				System.out.println("Clicked on Sliding Tray Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicAlkalineOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Connect Alkaline Countertop") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& SlidingTray.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(connectAlkalineCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicSlidingTrayUpsellAddToCartBtn);
				System.out.println("Clicked on Sliding Tray Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Connect Alkaline Countertop") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& SlidingTray.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(connectAlkalineCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicAlkalineOneYearComboUpsellAddToCartBtn);
				System.out.println("Clicked on One Year Combo Upsell");
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			} else if (ModelName.equals("Connect Alkaline Countertop") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& SlidingTray.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(connectAlkalineCountertopRadioBtn);
				eleUtil.doActionsClick(classicCoutertopAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			}

			// Combination 1: All Yes
			else if (ModelName.equals("Classic Undersink") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& BlueBottle.equalsIgnoreCase("Yes") && BlackBottle.equalsIgnoreCase("Yes")
					&& WhiteBottle.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(undersinkClassicRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutDeepBlueBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUrchinBlackBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWhiteSandBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 2: ThreeYearWarranty is No, others are Yes
			else if (ModelName.equals("Classic Undersink") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& BlueBottle.equalsIgnoreCase("Yes") && BlackBottle.equalsIgnoreCase("Yes")
					&& WhiteBottle.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(undersinkClassicRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutDeepBlueBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUrchinBlackBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWhiteSandBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 3: BlueBottle is No, others are Yes
			else if (ModelName.equals("Classic Undersink") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& BlueBottle.equalsIgnoreCase("No") && BlackBottle.equalsIgnoreCase("Yes")
					&& WhiteBottle.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(undersinkClassicRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUrchinBlackBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWhiteSandBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 4: BlackBottle is No, others are Yes
			else if (ModelName.equals("Classic Undersink") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& BlueBottle.equalsIgnoreCase("Yes") && BlackBottle.equalsIgnoreCase("No")
					&& WhiteBottle.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(undersinkClassicRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutDeepBlueBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWhiteSandBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 5: WhiteBottle is No, others are Yes
			else if (ModelName.equals("Classic Undersink") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& BlueBottle.equalsIgnoreCase("Yes") && BlackBottle.equalsIgnoreCase("Yes")
					&& WhiteBottle.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(undersinkClassicRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutDeepBlueBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUrchinBlackBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 6: CarafeCombo is No, others are Yes
			else if (ModelName.equals("Classic Undersink") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& BlueBottle.equalsIgnoreCase("Yes") && BlackBottle.equalsIgnoreCase("Yes")
					&& WhiteBottle.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(undersinkClassicRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutDeepBlueBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUrchinBlackBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWhiteSandBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 7: ThreeYearWarranty and BlueBottle are No, others are Yes
			else if (ModelName.equals("Classic Undersink") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& BlueBottle.equalsIgnoreCase("No") && BlackBottle.equalsIgnoreCase("Yes")
					&& WhiteBottle.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(undersinkClassicRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUrchinBlackBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWhiteSandBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 8: ThreeYearWarranty and BlackBottle are No, others are Yes
			else if (ModelName.equals("Classic Undersink") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& BlueBottle.equalsIgnoreCase("Yes") && BlackBottle.equalsIgnoreCase("No")
					&& WhiteBottle.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(undersinkClassicRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutDeepBlueBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWhiteSandBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 9: ThreeYearWarranty and WhiteBottle are No, others are Yes
			else if (ModelName.equals("Classic Undersink") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& BlueBottle.equalsIgnoreCase("Yes") && BlackBottle.equalsIgnoreCase("Yes")
					&& WhiteBottle.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(undersinkClassicRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutDeepBlueBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUrchinBlackBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 10: ThreeYearWarranty and CarafeCombo are No, others are Yes
			else if (ModelName.equals("Classic Undersink") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& BlueBottle.equalsIgnoreCase("Yes") && BlackBottle.equalsIgnoreCase("Yes")
					&& WhiteBottle.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(undersinkClassicRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutDeepBlueBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUrchinBlackBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWhiteSandBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 11: BlueBottle and BlackBottle are No, others are Yes
			else if (ModelName.equals("Classic Undersink") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& BlueBottle.equalsIgnoreCase("No") && BlackBottle.equalsIgnoreCase("No")
					&& WhiteBottle.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(undersinkClassicRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWhiteSandBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 12: BlueBottle and WhiteBottle are No, others are Yes
			else if (ModelName.equals("Classic Undersink") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& BlueBottle.equalsIgnoreCase("No") && BlackBottle.equalsIgnoreCase("Yes")
					&& WhiteBottle.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(undersinkClassicRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUrchinBlackBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 13: BlueBottle and CarafeCombo are No, others are Yes
			else if (ModelName.equals("Classic Undersink") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& BlueBottle.equalsIgnoreCase("No") && BlackBottle.equalsIgnoreCase("Yes")
					&& WhiteBottle.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(undersinkClassicRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUrchinBlackBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWhiteSandBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 14: BlackBottle and WhiteBottle are No, others are Yes
			else if (ModelName.equals("Classic Undersink") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& BlueBottle.equalsIgnoreCase("Yes") && BlackBottle.equalsIgnoreCase("No")
					&& WhiteBottle.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(undersinkClassicRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutDeepBlueBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 15: BlackBottle and CarafeCombo are No, others are Yes
			else if (ModelName.equals("Classic Undersink") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& BlueBottle.equalsIgnoreCase("Yes") && BlackBottle.equalsIgnoreCase("No")
					&& WhiteBottle.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(undersinkClassicRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutDeepBlueBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWhiteSandBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 16: WhiteBottle and CarafeCombo are No, others are Yes
			else if (ModelName.equals("Classic Undersink") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& BlueBottle.equalsIgnoreCase("Yes") && BlackBottle.equalsIgnoreCase("Yes")
					&& WhiteBottle.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(undersinkClassicRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutDeepBlueBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUrchinBlackBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 1: All Yes
			else if (ModelName.equals("Classic Alkaline Undersink") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& BlueBottle.equalsIgnoreCase("Yes") && BlackBottle.equalsIgnoreCase("Yes")
					&& WhiteBottle.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(undersinkAlkalineRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutDeepBlueBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUrchinBlackBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWhiteSandBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicAlkalineOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 2: ThreeYearWarranty is No, others are Yes
			else if (ModelName.equals("Classic Alkaline Undersink") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& BlueBottle.equalsIgnoreCase("Yes") && BlackBottle.equalsIgnoreCase("Yes")
					&& WhiteBottle.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(undersinkAlkalineRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutDeepBlueBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUrchinBlackBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWhiteSandBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicAlkalineOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 3: BlueBottle is No, others are Yes
			else if (ModelName.equals("Classic Alkaline Undersink") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& BlueBottle.equalsIgnoreCase("No") && BlackBottle.equalsIgnoreCase("Yes")
					&& WhiteBottle.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(undersinkAlkalineRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUrchinBlackBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWhiteSandBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicAlkalineOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 4: BlackBottle is No, others are Yes
			else if (ModelName.equals("Classic Alkaline Undersink") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& BlueBottle.equalsIgnoreCase("Yes") && BlackBottle.equalsIgnoreCase("No")
					&& WhiteBottle.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(undersinkAlkalineRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutDeepBlueBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWhiteSandBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicAlkalineOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 5: WhiteBottle is No, others are Yes
			else if (ModelName.equals("Classic Alkaline Undersink") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& BlueBottle.equalsIgnoreCase("Yes") && BlackBottle.equalsIgnoreCase("Yes")
					&& WhiteBottle.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(undersinkAlkalineRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutDeepBlueBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUrchinBlackBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicAlkalineOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 6: CarafeCombo is No, others are Yes
			else if (ModelName.equals("Classic Alkaline Undersink") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& BlueBottle.equalsIgnoreCase("Yes") && BlackBottle.equalsIgnoreCase("Yes")
					&& WhiteBottle.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(undersinkAlkalineRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutDeepBlueBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUrchinBlackBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWhiteSandBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 7: ThreeYearWarranty and BlueBottle are No, others are Yes
			else if (ModelName.equals("Classic Alkaline Undersink") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& BlueBottle.equalsIgnoreCase("No") && BlackBottle.equalsIgnoreCase("Yes")
					&& WhiteBottle.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(undersinkAlkalineRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUrchinBlackBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWhiteSandBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicAlkalineOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 8: ThreeYearWarranty and BlackBottle are No, others are Yes
			else if (ModelName.equals("Classic Alkaline Undersink") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& BlueBottle.equalsIgnoreCase("Yes") && BlackBottle.equalsIgnoreCase("No")
					&& WhiteBottle.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(undersinkAlkalineRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutDeepBlueBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWhiteSandBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicAlkalineOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 9: ThreeYearWarranty and WhiteBottle are No, others are Yes
			else if (ModelName.equals("Classic Alkaline Undersink") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& BlueBottle.equalsIgnoreCase("Yes") && BlackBottle.equalsIgnoreCase("Yes")
					&& WhiteBottle.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(undersinkAlkalineRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutDeepBlueBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUrchinBlackBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicAlkalineOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 10: ThreeYearWarranty and CarafeCombo are No, others are Yes
			else if (ModelName.equals("Classic Alkaline Undersink") && ThreeYearWarranty.equalsIgnoreCase("No")
					&& BlueBottle.equalsIgnoreCase("Yes") && BlackBottle.equalsIgnoreCase("Yes")
					&& WhiteBottle.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(undersinkAlkalineRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutDeepBlueBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUrchinBlackBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWhiteSandBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 11: BlueBottle and BlackBottle are No, others are Yes
			else if (ModelName.equals("Classic Alkaline Undersink") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& BlueBottle.equalsIgnoreCase("No") && BlackBottle.equalsIgnoreCase("No")
					&& WhiteBottle.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(undersinkAlkalineRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWhiteSandBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicAlkalineOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 12: BlueBottle and WhiteBottle are No, others are Yes
			else if (ModelName.equals("Classic Alkaline Undersink") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& BlueBottle.equalsIgnoreCase("No") && BlackBottle.equalsIgnoreCase("Yes")
					&& WhiteBottle.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(undersinkAlkalineRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUrchinBlackBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicAlkalineOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 13: BlueBottle and CarafeCombo are No, others are Yes
			else if (ModelName.equals("Classic Alkaline Undersink") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& BlueBottle.equalsIgnoreCase("No") && BlackBottle.equalsIgnoreCase("Yes")
					&& WhiteBottle.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(undersinkAlkalineRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUrchinBlackBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWhiteSandBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 14: BlackBottle and WhiteBottle are No, others are Yes
			else if (ModelName.equals("Classic Alkaline Undersink") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& BlueBottle.equalsIgnoreCase("Yes") && BlackBottle.equalsIgnoreCase("No")
					&& WhiteBottle.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("Yes")) {
				eleUtil.doActionsClick(undersinkAlkalineRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutDeepBlueBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutClassicAlkalineOneYearComboUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 15: BlackBottle and CarafeCombo are No, others are Yes
			else if (ModelName.equals("Classic Alkaline Undersink") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& BlueBottle.equalsIgnoreCase("Yes") && BlackBottle.equalsIgnoreCase("No")
					&& WhiteBottle.equalsIgnoreCase("Yes") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(undersinkAlkalineRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutDeepBlueBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutWhiteSandBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			// Combination 16: WhiteBottle and CarafeCombo are No, others are Yes
			else if (ModelName.equals("Classic Alkaline Undersink") && ThreeYearWarranty.equalsIgnoreCase("Yes")
					&& BlueBottle.equalsIgnoreCase("Yes") && BlackBottle.equalsIgnoreCase("Yes")
					&& WhiteBottle.equalsIgnoreCase("No") && CarafeCombo.equalsIgnoreCase("No")) {
				eleUtil.doActionsClick(undersinkAlkalineRadioBtn);
				eleUtil.doActionsClick(undersinkAddToCartBtn);
				System.out.println("Clicked Add to Cart");
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUnderSinkClassicWarrantyUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutDeepBlueBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(preCheckoutUrchinBlackBottleUpsellAddToCartBtn);
				Thread.sleep(8000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(3000);
			}

			else {
				System.out.println("Incorrect Model");
				// Fail - Incorrect payment option
			}
		}

		catch (Exception ex) {
			System.out.println("Check Model Name");
			throw ex;

		}

	}

	public void writeexcel(String subtotal, String flatrate, String tax, String total, int count)
			throws InvalidFormatException, IOException {
		ExcelUtil.setdata(AppConstants.PRODUCT_SHEET_NAME, subtotal, flatrate, tax, total, count);
	}

	public int testMe(ITestContext testContext) {
		int currentCount = testContext.getAllTestMethods()[0].getCurrentInvocationCount();
		System.out.println("Executing count: " + currentCount);
		return currentCount;
	}

	public LoginPage logoutfromthankyoupage() throws InterruptedException {
		eleUtil.clickWhenReady(backtohomepage, TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.clickWhenReady(loginIcon, TimeUtil.DEFAULT_MEDIUM_TIME);
		//JsUtil.scrollIntoView(eleUtil.getElement(logouttab));
		eleUtil.clickWhenReady(logout, TimeUtil.DEFAULT_MEDIUM_TIME);
		return new LoginPage(driver);
	}

}
