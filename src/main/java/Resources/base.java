package Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class base {

	public WebDriver driver;
	public Properties prop;

	public WebDriver initializeDriver(String browser) throws Exception {
		
		 prop= new Properties();
		 FileInputStream fis=new FileInputStream("E:\\eclipse-workspace\\SeleniumImplementation\\src\\main\\java\\Resources\\data.properties");

		 prop.load(fis);
		
		
		if (browser.toUpperCase().equals("CHROME")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browser.toUpperCase().equals("FIREFOX")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browser.toUpperCase().equals("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();

		} else if (browser.toUpperCase().equals("EDGE")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		return driver;

	}
	
	
	public ArrayList<String> getData(String testcaseName) throws IOException {
		// fileInputStream argument
		ArrayList<String> a = new ArrayList<String>();

		FileInputStream fis = new FileInputStream(
				"E:\\SUDALAI SELVAM\\Study\\Study document\\REAL TIME\\Social website credentials.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("Sheet1")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				// Identify Testcases coloum by scanning the entire 1st row

				Iterator<Row> rows = sheet.iterator();// sheet is collection of rows
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();// row is collection of cells
				int k = 0;
				int coloumn = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();

					if (value.getStringCellValue().equalsIgnoreCase("Social Website")) {
						coloumn = k;

					}

					k++;
				}

				//// once coloumn is identified then scan entire testcase coloum to identify
				//// purcjhase testcase row
				while (rows.hasNext()) {

					Row r = rows.next();

					if (r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testcaseName)) {

						//// after you grab purchase testcase row = pull all the data of that row and
						//// feed into test

						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							Cell c = cv.next();
							if (c.getCellTypeEnum() == CellType.STRING) {

								a.add(c.getStringCellValue());
							} else {

								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));

							}
						}
					}

				}

			}
		}
		return a;

	}
	
	
	public String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+System.currentTimeMillis()+".png";
		FileUtils.copyFile(source,new File(destinationFile));
		return destinationFile;


	}
	
	


}
