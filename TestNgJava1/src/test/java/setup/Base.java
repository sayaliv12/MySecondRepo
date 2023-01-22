package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
	
	public static WebDriver openChromeBrowser()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\DELL\\Desktop\\Project2\\TestNgJava1\\src\\test\\resources\\browsers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		return driver;
	}
}
