package org.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BaseClass {
	public static WebDriver driver;

	public WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Dell\\eclipse-workspace\\Facebook\\Driver\\chromedriver.exe");
		driver=new ChromeDriver();
		return driver;
	}

	public void launchurl(String url) {
		driver.get(url);
	}

	public void maximize() {
		driver.manage().window().maximize();
	}

	public String title() {
		String title = driver.getTitle();
		return title;
	}

	public void quit() {
		driver.quit();
	}

	public void close() {
		driver.close();

	}

	public void sendkeys(WebElement element, String s) {
		element.sendKeys(s);
	}

	public void click(WebElement element) {
		element.click();

	}

	public WebElement id(String s) {
		WebElement element1 = driver.findElement(By.id(s));
		return element1;

	}

	public WebElement name(String s) {
		WebElement element1 = driver.findElement(By.name(s));
		return element1;
	}

	public WebElement className(String s) {
		WebElement element1 = driver.findElement(By.className(s));
		return element1;
	}

	public WebElement xpath(String s) {
		WebElement element1 = driver.findElement(By.xpath(s));
		return element1;
	}

	public String getText(WebElement element) {
		String s1 = element.getText();
		return s1;

	}

	public String getAttribute(WebElement element, String s) {
		String s2 = element.getAttribute("value");
		return s2;

	}

	public void selectValueByIndex(WebElement element, int index) {
		new Select(element).selectByIndex(index);

	}

	public void selectByText(WebElement element, String data) {
		new Select(element).selectByVisibleText(data);

	}

	public void debug(long l) throws InterruptedException {
		Thread.sleep(l);

	}

	public void moveToElement(WebElement element) {
		Actions a = new Actions(driver);
		a.moveToElement(element).perform();

	}

	public void dragAndDrop(WebElement source, WebElement designation) {
		Actions a = new Actions(driver);
		a.dragAndDrop(source, designation).perform();

	}

	public void rightclick(WebElement element) {
		Actions a = new Actions(driver);
		a.contextClick(element).perform();

	}

	public void doubleclick(WebElement element) {
		Actions a = new Actions(driver);
		a.doubleClick(element).perform();

	}

	public void switchToAlert(WebElement element) {
		Alert al = driver.switchTo().alert();
		al.accept();
	}
	public void screenshot(String filepath) throws IOException {
		TakesScreenshot screen=(TakesScreenshot)driver;
		File loc=screen.getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("user.dir")+"\\Screenshots\\"+filepath+".png");
		FileUtils.copyFile(loc, dest);

	}
	public String getDataFromExcel(String pathname,String sheetName,int rowNo,int cellNo) throws IOException {
		
		File f=new File(pathname);
		FileInputStream stream=new FileInputStream(f);
		Workbook w=new XSSFWorkbook(stream);
		Sheet s = w.getSheet(sheetName);
		Row r = s.getRow(rowNo);
		Cell c = r.getCell(cellNo);
		int Type = c.getCellType();
		
		String value=null;
		if (Type==1) {
		 value = c.getStringCellValue();
		 }else if (DateUtil.isCellDateFormatted(c)) {
			 Date dateCellValue = c.getDateCellValue();
			 SimpleDateFormat date=new SimpleDateFormat("dd-MMM-yy");
			 value=date.format(dateCellValue);
			
		}else {
			double d = c.getNumericCellValue();
			long l=(long)d;
			value=String.valueOf(l);
		}
		return value;

	}
	public void sendDataFromExcel(String pathname,String sheetName,int rowNo,int cellNo,String cellData) throws IOException {
		
		File f=new File(pathname);
		FileInputStream stream=new FileInputStream(f);
		Workbook w=new XSSFWorkbook(stream);
		Sheet s = w.getSheet(sheetName);
		Row r = s.getRow(rowNo);
		Cell c = r.createCell(cellNo);
		c.setCellValue(cellData);
		FileOutputStream o=new FileOutputStream(f);
		w.write(o);
		System.out.println("done");
		
		
	}

	
	
	
	
	
	
	
	

}
