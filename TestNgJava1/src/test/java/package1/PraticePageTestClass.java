package package1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import setup.Base;
import utils.Utility;

public class PraticePageTestClass extends Base{
	private WebDriver driver;
	private PracticePage practicepage;
	int testId;
	private ArrayList<String> listOfAddr;
	SoftAssert soft=new SoftAssert();

	@BeforeTest
	@Parameters("browser")
	public void launchBrowser(String browser)
	{
		if(browser.equals("chrome"))
		{
			//System.setProperty("webdriver.chrome.driver","C:\\Users\\DELL\\Desktop\\Project2\\TestNgJava1\\src\\test\\resources\\browsers\\chromedriver.exe");
			//driver=new ChromeDriver();
			driver=openChromeBrowser();
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		if(browser.equals("edge"))
		{
			System.setProperty("webdriver.edge.driver","C:\\Users\\DELL\\Desktop\\Project2\\TestNgJava1\\src\\test\\resources\\browsers\\msedgedriver.exe");
			driver=new EdgeDriver();
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
	}
	@BeforeClass
	public void createPOMObjects()
	{
		practicepage=new PracticePage(driver);
		listOfAddr=new ArrayList<String>(driver.getWindowHandles());
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
	}
	@BeforeMethod
	public void openPracticePage()
	{
		System.out.println("Before Method");
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
	public void cleanPOMObjects()
	{
		practicepage=null;
	}
	@AfterTest
	public void closedBrowser()
	{
		driver.quit();
	}
}


