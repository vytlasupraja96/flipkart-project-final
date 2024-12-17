package project4.project4flipkart;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.poi.ss.usermodel.Row;

import jxl.read.biff.BiffException;
public class Flipkart {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		WebDriver driver = new ChromeDriver();
		//WebDriverManager.chromedriver().setup();
		
		
		System.out.println("loading chrome browser...");
		driver.get("https://www.flipkart.com/");
	//	driver.manage().window().maximize();
		Thread.sleep(3000);
	WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']"));
	search.sendKeys("mobile phones");
	search.sendKeys(Keys.ENTER);
	Thread.sleep(2000);
	
	

    List<WebElement> products = driver.findElements(By.xpath("//div[@class='_4rR01T']")); // XPath for product names
    List<WebElement> prices = driver.findElements(By.xpath("//div[@class='_30jeq3']")); // XPath for product prices

      XSSFWorkbook wb = new XSSFWorkbook();
      XSSFSheet sh = wb.createSheet("Products");
     
      Row header = sh.createRow(0);
      header.createCell(0).setCellValue("Product Name");
      header.createCell(1).setCellValue("Price");
    
      for(int i=0;i<products.size();i++)
      {
   	   String s= products.get(i).getText();
   	   String s1= prices.get(i).getText();
   	   Row row=sh.createRow(i++);
   	   row.createCell(0).setCellValue(s); // 0 indicates row number
   	   row.createCell(1).setCellValue(s1); // 1 also indicates row number
   	   System.out.println(s+"  " +s1);
      }
      @SuppressWarnings("unused")
	String filepath = "src/test/resources/testresult/Products.xlsx";
      FileOutputStream output = new FileOutputStream("filepath");
      wb.write(output);
      // closing the file is also imp
      output.close();
     wb.close();
    System.out.println("excel file successfull");
      driver.quit();

	}

}
