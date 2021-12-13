package Academy;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mongodb.operation.CurrentOpOperation;

import PageObjects.LoginPage;
import PageObjects.SearchPage;
import Resources.base;

public class Homepage extends base {

	public WebDriver driver;

	public static Logger log =LogManager.getLogger(base.class.getName());
	@BeforeTest
	public void initialize() throws Exception {

		driver = initializeDriver("chrome");
	}

    //Launch the URL

	@Test(priority = 1)
	public void LaunchURL() {

		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
		log.info("Launch the URL");
		System.out.println("Launch the URL");
		driver.navigate().to("https://www.flipkart.com/");
		System.out.println("Title of web application is " + driver.getTitle() + "\n" + "URL of web application is"
				+ driver.getCurrentUrl());

	}

	// Login the credentials - Using Excel/POM (screenshot 1)
	@Test(priority = 2)
	public void Logincredentials() throws Exception {

		System.out.println("\n" + "Login the credentials Using Excel,POM");
		LoginPage lp = new LoginPage(driver);
		ArrayList<String> data = getData("Flipkart");
		lp.getEmail().sendKeys(data.get(1));
		lp.getPassword().sendKeys(data.get(2));
		lp.getLogin().click();
		Thread.sleep(2000);
		getScreenShotPath("Beforeimage", driver);

	}

	// Search box Type as catergory name - Using File/ Dropdown
	@Test(priority = 3)
	public void productname() throws Exception {

		System.out.println("\n" + "Send the value in category box using file,dropdown");
		SearchPage sp = new SearchPage(driver);
		sp.searchbox().sendKeys(prop.getProperty("Brand"));
		Thread.sleep(3000);
		List<WebElement> sr = driver
				.findElements(By.xpath("//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[2]/form/ul/li"));
		for (WebElement s : sr) {

			if (s.getText().toUpperCase().contains("MOBILES")) {

				String att = s.getAttribute("innerText");
				System.out.println(att);
				s.click();
				break;

			}
			Thread.sleep(4000);
		}

		Thread.sleep(3000);

	}

	// Navigate to respective page - Print all product name
	@Test(priority = 4)
	public void printallproductname() {

		System.out.println("\n" + "print all product name");
		List<WebElement> ele1 = driver.findElements(By.xpath("//*[@class='_4rR01T']"));
		ListIterator<WebElement> li = ele1.listIterator();
		while (li.hasNext()) {
			System.out.println(li.next().getText());
		}

	}

	// Click on product - Using Widow handling
	@Test(priority = 5)
	public void clickproductsusingwindowshandling() throws Exception {
		System.out.println("\n" + "click on respective product using windows handling");
		Thread.sleep(200);

		List<WebElement> ele2 = driver.findElements(By.xpath("//*[@class='_4rR01T']"));
		for (int s = 0; s <= 10; s++) {
			ele2.get(s).click();
		}

		Set<String> ele3 = driver.getWindowHandles();
		List<String> List = new ArrayList<>(ele3);
		driver.switchTo().window(List.get(List.size() - 6));
	}

	// 7.Send the pincode - Using file - Then check
	@Test(priority = 6)
	public void sendpincodeUsingfilerobotclass() throws Exception {

		System.out.println("\n" + "send pincode using file");
		driver.findElement(By.xpath("//*[@id='pincodeInputId']")).sendKeys(prop.getProperty("Pincode"));
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		System.out.println("Deliver by  " + driver.findElement(By.xpath("//*[@class='_1TPvTK']")).getText());

	}

	// Click on “ Add to cart”
	@Test(priority = 7)
	public void clickaddtocartbtn() throws Exception {

		System.out.println("\n" + "Click on add to cart button");
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@class='_2KpZ6l _2U9uOA _3v1-ww']")).click();

		getScreenShotPath("Afterimage", driver);
	}

	// Navigate to “Home” Screen (Screenshot 2)
	@Test(priority = 8)
	public void navigatetohomescreen() throws Exception {

		System.out.println("\n" + "Navigate to home screen");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@alt='Flipkart']")).click();

		getScreenShotPath("Finalimage", driver);
	}

	@AfterTest
	public void teardown() throws Exception {

		Thread.sleep(1000);
		driver.quit();;

	}

}
