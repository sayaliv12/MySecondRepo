package package1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
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
import com.beust.jcommander.Parameter;

import setup.Base;
import utils.Utility;

public class PracticeFrameTestClass extends Base {
	private WebDriver driver;
	private PracticePage practicepage;
	int testId;
	
	@BeforeTest
	@Parameters("browser")
	public void launchBrowser(String browser)
	{
		if(browser.equals("chrome"))
		{
			//System.setProperty("webdriver.chrome.driver","C:\\Users\\DELL\\Desktop\\Project2\\TestNgJava1\\src\\test\\resources\\browsers\\chromedriver.exe");
			//driver=new ChromeDriver();
			driver=Base.openChromeBrowser();
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
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
	}
	@BeforeMethod
	public void openPracticePage()
	{
		System.out.println("Before Method");
	}
	@Test
	public void verifyIframeexample() throws IOException, InterruptedException
	{
		practicepage.clickOnmoretab();
		driver.switchTo().defaultContent();
		practicepage.clickOnFooterlink();
		String url=driver.getCurrentUrl();
		System.out.println("url is : " +url);
		Assert.assertEquals(url , "https://www.restapitutorial.com/");
		String title=driver.getTitle();
		System.out.println("title is :"+title);
		Assert.assertEquals(title, "REST API Tutorial");
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
