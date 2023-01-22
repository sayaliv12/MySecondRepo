package package1;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PracticePage {
	private WebDriver driver;
	private Actions act;
	//private WebDriverWait wait;
	@FindBy(xpath="(//input[@class='radioButton'])[1]")
	private WebElement radio1;
	@FindBy(xpath="//input[@id='autocomplete']")
	private WebElement countryName;
	@FindBy(xpath="	//select[@id='dropdown-class-example']")
	private WebElement dropDown;
	@FindBy(xpath="//div[@id='checkbox-example']//input")
	private WebElement option1;
	@FindBy(xpath="//input[@id='hide-textbox']")
	private WebElement hide;
	@FindBy(xpath="	//input[@id='displayed-text']")
	private WebElement inputField;
	@FindBy(xpath="	//input[@id='show-textbox']")
	private WebElement show;
	@FindBy(xpath="//button[@id='mousehover']")
	private WebElement mouseHoverbutton;
	@FindBy(xpath="//div[@class='mouse-hover-content']//a")
	private WebElement top;
	@FindBy(xpath="(//div[@class='mouse-hover-content']//a)[2]")
	private WebElement reload;
	@FindBy(xpath="//input[@id='alertbtn']")
	private WebElement alert;
	@FindBy(xpath="//input[@id='confirmbtn']")
	private WebElement confirm;
	@FindBy(xpath="//button[@id='openwindow']")
	private WebElement openWindow;
	@FindBy(xpath="//a[@id='opentab']")
	private WebElement openTab;
	@FindBy(xpath="//iframe[@id='courses-iframe']")
	private WebElement iframe;
	@FindBy(xpath="//a[@class='dropdown-toggle']")
	private WebElement more;
	@FindBy(xpath="//ul[@class='dropdown-menu']//li")
	private WebElement aboutUs;
	@FindBy(xpath="//a[text()='REST API']")
	private WebElement restAPI;
	
	public PracticePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
		this.driver=driver;
		act=new Actions(driver);
		//wait=new WebDriverWait(driver,20);
	}
	
	//1.Radio Button Example
	public void clickOnradioButton()
	{
		//Validation
		boolean value=radio1.isSelected();
		System.out.println("Result before selecting radio box is :" +value);
		radio1.click();
		value=radio1.isSelected();
		System.out.println("Result after selecting  radio box is :" +value);
	}
	//2.Suggestiong Class Example
	public void enterCountryName(String country)
	{
		countryName.sendKeys(country);
	}
	//3.Dropdown Example
	public void selectOneOptionFromDropDown()
	{
		Select s=new Select(dropDown);
		s.selectByIndex(1);
		s.selectByValue("option1");
		s.selectByVisibleText("Option1");
	}
	//4.ChckBox Example
	public void selectOptionFormCheckbox()
	{
		//Validation
		boolean value=option1.isSelected();
		System.out.println("Result before selecting checkbox is :" +value);
		option1.click();
		value=option1.isSelected();
		System.out.println("Result after selecting checkbox is :" +value);
	}
	//5.Element isDisplayed Example
	public void clickOnHideAndShowButton() 
	{
		//Validation
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("argument[0].scrollIntoView(true);",inputField);
		inputField.click();
		boolean value=inputField.isDisplayed();
		System.out.println("Is input field displayed? :"+value);
		hide.click();
		value=inputField.isDisplayed();
		System.out.println("Is input field displayed? :"+value);
		//WebElement show=wait.until(ExpectedConditions.visibilityOfElementLocated(null));
		show.click();
		value=inputField.isDisplayed();
		System.out.println("Is input field displayed? :"+value);
	}
	//6.Mouse Hover Example
	public void mouseHoverAction()
	{
		act.moveToElement(mouseHoverbutton).moveToElement(top).click().build().perform();
		act.moveToElement(mouseHoverbutton).moveToElement(reload).click().build().perform();
	}
	//7.Switch To Alert Example
	public void clickOnAlertButton()
	{
		alert.click();
		Alert alt=driver.switchTo().alert();
		alt.accept();
	}
	//8.Switch Window Example
	public void clickOnOpenWindowButton()
	{
		openWindow.click();
	}
	//9.Switch Tab Example
	public void clickOnOpenTabButton()
	{
		openTab.click();
	}
	//10.iframe Example
	public void clickOnmoretab() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.switchTo().frame(iframe);
		act.moveToElement(more).moveToElement(aboutUs).click().build().perform();
	}
	public void clickOnFooterlink()
	{
		restAPI.click();
	}
}









