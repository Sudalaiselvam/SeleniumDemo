package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {
	
	
	public WebDriver driver;
	
	By searchtextbox = By.xpath("//*[@type='text']");
	By sr = By.xpath("//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[2]/form/ul/li");
	
	
	public SearchPage(WebDriver driver) {
		
		this.driver=driver;
		
	}
	
	public void dropdowncoupon() {
		// TODO Auto-generated method stub

	}
	
	
	public WebElement searchbox() {
		
		return driver.findElement(searchtextbox);
				
	}

}
