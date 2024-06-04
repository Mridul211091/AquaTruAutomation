package agi.qa.aquatru.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import agi.qa.aquatru.constants.AppConstants;
import agi.qa.aquatru.factory.DriverFactory;
import agi.qa.aquatru.pages.AquaTruAffiliatePage;
import agi.qa.aquatru.pages.AquaTruOrderFlowPage;
import agi.qa.aquatru.pages.LoginPage;
import agi.qa.aquatru.pages.ProductDisplayPage;
import agi.qa.aquatru.utils.ExcelUtil;




public class BaseTest {
	
	WebDriver driver;
	protected Properties prop;
	DriverFactory df;
	
	protected LoginPage loginPage;
	protected ProductDisplayPage pdpPage;
	protected AquaTruOrderFlowPage aquatruPage;
	protected AquaTruAffiliatePage affiliatePage;
	protected SoftAssert softAssert;
	
	//@Parameters({"browser"})
	/*
	 * @BeforeTest public void setup(String browserName) { df = new DriverFactory();
	 * prop = df.initProp();
	 * 
	 * 
	 * if(browserName!=null) { prop.setProperty("browser", browserName); }
	 * 
	 * 
	 * driver = df.initDriver(prop); loginPage = new LoginPage(driver); softAssert =
	 * new SoftAssert(); }
	 */
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		//loginPage = new LoginPage(driver);
		//aquatruPage = new AquaTruOrderFlowPage(driver);
		affiliatePage = new AquaTruAffiliatePage(driver);
		softAssert = new SoftAssert();
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	

}
