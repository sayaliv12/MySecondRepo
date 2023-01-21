package package1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Utility;

public class PraticePageTestClass {
	private WebDriver driver;
	private PracticePage practicepage;
	int testId;
	private ArrayList<String> listOfAddr;
	SoftAssert soft=new SoftAssert();
	@BeforeClass
	public void launchBrowser()
	{
		System.setProperty("webdriver.chrome.driver","D:\\Eclipse Workspace\\TestNgJava1\\src\\test\\resources\\browsers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
	}
	@BeforeMethod
	public void openPracticePage()
	{
		practicepage=new PracticePage(driver);
		listOfAddr=new ArrayList<String>(driver.getWindowHandles());
	}
	@Test(priority=1)
	public void verifyRow1example() throws IOException
	{
		practicepage.clickOnradioButton();
		String countryName=Utility.fetchDataFromExcelSheet("sheet1",0,0);
		System.out.println("Expected : "+countryName );
		practicepage.enterCountryName(countryName);
		practicepage.selectOneOptionFromDropDown();
		practicepage.selectOptionFormCheckbox();
		String url=driver.getCurrentUrl();
		System.out.println("url is:" +url);		
		soft.assertEquals(url,"https://rahulshettyacademy.com/AutomationPractice/");
		soft.assertAll();
	}
	@Test(priority=2)
	public void verifyRow3Exmaple() 
	{
		practicepage.clickOnHideAndShowButton();
		practicepage.mouseHoverAction();
	}
	//@Test(priority=3,enabled=false)
	@Test(priority=3)
	public void verifyRow2Example()
	{
		practicepage.clickOnAlertButton();
		practicepage.clickOnOpenWindowButton();
		String mainPage=driver.getWindowHandle();
		System.out.println(mainPage);
		listOfAddr=new ArrayList<String>(driver.getWindowHandles());
		System.out.println(listOfAddr.get(0));
		System.out.println(listOfAddr.get(1));
		driver.switchTo().window(listOfAddr.get(1));
		driver.manage().window().maximize();
		driver.switchTo().window(listOfAddr.get(0));
		practicepage.clickOnOpenTabButton();
	}
	@AfterMethod
	public void logoutFromApplication(ITestResult output) throws IOException
	{
		if(ITestResult.FAILURE==output.getStatus())
		{
			System.out.println("=========");
			System.out.println("Pass Test Cases");
			Utility.captureScreenshot(driver, testId);
			System.out.println("=========");	
		}
		System.out.println("After Method");
	}
	@AfterClass
	public void closedBrowser()
	{
		driver.quit();
	}


}
