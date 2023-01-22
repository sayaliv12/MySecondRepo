package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {
	private WebDriver driver;
	public static String fetchDataFromExcelSheet(String sheet,int rowNo,int CellNo) throws IOException 
	{
		String path="C:\\Users\\DELL\\Desktop\\Project2\\TestNgJava1\\src\\test\\resources\\Testdata\\TestData1.xlsx";
		FileInputStream file=new FileInputStream(path);
		Cell cell=WorkbookFactory.create(file).getSheet("sheet1").getRow(rowNo).getCell(CellNo);
		try
		{
			String value=cell.getStringCellValue();
			return value;
		}
		catch(IllegalStateException e)
		{
			long value1=(long)cell.getNumericCellValue();
			String str=String.valueOf(value1);
			System.out.println(str);
			return str;
		}
	}
	public static void captureScreenshot(WebDriver driver,int testId) throws IOException
	{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_DD hh_mm_ss");
		String value=sdf.format(date);
		String fileName=value.toString().replace("_", "-");
		System.out.println(fileName);
		TakesScreenshot take=(TakesScreenshot)driver;
		File src=take.getScreenshotAs(OutputType.FILE);
		File dest=new File("C:\\Users\\DELL\\Desktop\\Project2\\TestNgJava1\\test-output\\Failed Test Screenshots\\test" +fileName+ ".jpeg");
		FileHandler.copy(src,dest);
	}
}
